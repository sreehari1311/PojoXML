/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.pojoxml.core.processor.xmltopojo.dom;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.pojoxml.core.PojoXmlInitInfo;
import org.pojoxml.util.ClassUtil;
import org.pojoxml.util.StringUtil;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class is used for making xml content to an Object. using DOM processor
 * 
 * @author SreeHari
 * @version 1.0
 */
public class XmlToObjectProcessor extends ObjectProcessor {
	/**
	 * class for making the object
	 */
	private Class clas;

	/**
	 * real POJO object
	 */
	private Object object;

	/**
	 * contains the element name as key and class as value
	 */
	private Map setterMethodMap;

	/**
	 * current processing element name
	 */
	private String currentNodeName;

	/**
	 * current processing element value.
	 */
	private Object currentNodeValue;

	/**
	 * Composition for array processing
	 */
	private XmlToArrayProcessor arrayProcessor;
	/**
	 * 
	 */
	private XmlToCollectionProcessor collectionProcessor;

	/**
	 * indicator for currently processing is the arrays.
	 */
	private boolean arrayReading;

	/**
	 * determine child has no value
	 */
	private boolean isChildEmpty;
	/**
	 * 
	 */
	private boolean collectionReading;

	/**
	 * 
	 */
	private static final boolean ARRAY_READING = true;
	/**
     * 
     */
	private static final boolean ARRAY_NOT_READING = false;
	/**
     * 
     */
	private static final boolean CHILD_EMPTY = true;
	/**
	 * 
	 */
	private static final boolean CHILD_NOT_EMPTY = false;
	/**
	 * 
	 */
	private static final boolean COLLECTION_READING = true;
	/**
	 * 
	 */
	private static final boolean COLLECTION_NOT_READING = false;

	/**
	 * Constructor that initialize main processing
	 * 
	 * @param clas
	 *            class for creating the pojo object from xml.
	 * @param rootNode
	 *            root node.
	 */
	public XmlToObjectProcessor(Class clas, PojoXmlInitInfo initInfo) {
		super(initInfo);
		this.clas = clas;
		this.arrayReading = ARRAY_NOT_READING;
		this.isChildEmpty = CHILD_EMPTY;
		this.collectionReading = COLLECTION_NOT_READING;
		if(clas != null){
		init();
		readChildren(rootNode);
		checkPendingOperation();
		}
	}

	/**
	 * initializing method.
	 */
	private void init() {
		if(clas == null)
			return;
		setSetterMethodMap(ClassUtil.getSetterAndClass(clas));
		setObject(ClassUtil.createObject(clas));
	}

	/**
	 * method for checking is there any pending operation.
	 */
	private void checkPendingOperation() {
	 	if (isArrayReading())
			processArray();
		if (isCollectionReading())
			processCollection();

	}

	/**
	 * supporting method for recursing reading
	 * 
	 * @param node
	 *            child node
	 */
	private void readNode(Node node) {
		readChildren(node);
	}

	/**
	 * read children recursively
	 * 
	 * @param node
	 *            Child Node  
	 */
	private void readChildren(Node node) {
		if (!node.hasChildNodes())
			return;
		setCurrentNodeName(node.getNodeName());
		NodeList childNodes = node.getChildNodes();
		isChildEmpty = CHILD_NOT_EMPTY;
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = (Node) childNodes.item(i);
		 	if (child.getNodeType() == Node.ELEMENT_NODE) {
				if (processElementNodeContinue(child)) {
					continue;
				}
				readNode(child);
				continue;
			} else if (child.getNodeType() == Node.TEXT_NODE ) {
				setCurrentNodeValue(child.getNodeValue());
			}
		}
		if (getCurrentNodeName() != null) {
			setValuesToCurrentObject(getCurrentNodeName(),
					getCurrentNodeValue());
			setCurrentNodeName(null);
		}
	}

	/**
	 * 
	 * @param valueClass
	 * @param child
	 * @return boolean
	 */
	private boolean processElementNodeContinue(Node child) {

		Class valueClass = (Class) setterMethodMap.get(getElementName(child));
		final boolean CONTINUE = true;
		final boolean NOT_CONTINUE = false;
		if(valueClass == null)
			return CONTINUE;
		String setterName = getActualValueFromAleasName( child.getNodeName());
	 	if (ClassUtil.isArray(valueClass)) {
			
			if(ClassUtil.isPrimitiveArray(valueClass)&& child.getTextContent().trim().length()==0){
	  			return CONTINUE;
			}
			setArrayReading(ARRAY_READING);
			if(child.getTextContent().trim().length()==0)
				addArray(setterName, valueClass, null);
		}
		if (ClassUtil.isCollection(valueClass)) {
			setCollectionReading(COLLECTION_READING);
			PojoXmlInitInfo init = new PojoXmlInitInfo();
			init.setCollectionClassMap(getInitInfo().getCollectionClassMap());
			init.setRootNode(child);
			 
			Object collectionValueObject = new XmlToCollectionHandler(init,setterName).processCollection();
			addCollection(setterName, valueClass, collectionValueObject);
			processCollection();
			return CONTINUE;
		}
		if (!ClassUtil.isPrimitiveOrWrapper(valueClass)) {
			 			
		 if (ClassUtil.isArray(valueClass)) {
			 	Class valClass = valueClass.getComponentType();
			 	PojoXmlInitInfo initInfo = new PojoXmlInitInfo();
				initInfo.setRootNode(child);
				int length = -1;
				if (child.getChildNodes().getLength() == 0)
					length = 0;
				if(length == 0)
					return CONTINUE;
			 	XmlToObjectProcessor newObject = new XmlToObjectProcessor(valClass,initInfo);
				Object innerObject = newObject.getObject();
				 
				addArray(setterName, valueClass, innerObject);
			 	return CONTINUE;
			} 
			setInnerObject(valueClass, child);
			return CONTINUE;
		}
		return NOT_CONTINUE;
	}

	/**
	 * Method for setting value to pojo object
	 * 
	 * @param setterName
	 *            setter method of the pojo object
	 * @param value
	 *            value to set
	 */
	private void setValuesToCurrentObject(String setterName, Object value) {
		Class valueClass = (Class) setterMethodMap.get(StringUtil
				.initCap(setterName));
	 	if (arrayReading) {
		 	if (ClassUtil.isArray(valueClass)) {
		 		
				valueClass = (Class) setterMethodMap.get(StringUtil
						.initCap(setterName));
				addArray(setterName, valueClass, value);
				return;
			} else {
				processArray();
			}
		}

		if (ClassUtil.isPrimitiveOrWrapper(valueClass)) {
		 	ClassUtil.invokeSetter(clas, object, setterName, ClassUtil
					.ObjectFromValue(value, valueClass), valueClass);
		}
	}

	/**
	 * for processing the inner object
	 * 
	 * @param innerClas
	 *            inner object class
	 * @param node
	 *            child node
	 */
	private void setInnerObject(Class innerClas, Node node) {
		int length = -1;
	 	String nodeName = getActualValueFromAleasName(node.getNodeName());
		if (node.getChildNodes().getLength() == 0)
			length = 0;
		getInitInfo().setRootNode(node);
		XmlToObjectProcessor newObject = new XmlToObjectProcessor(innerClas,
				getInitInfo());
		Object innerObject = newObject.getObject();
		if (length == 0)
			innerObject = null;
		if (isArrayReading()) {
			if (!addArray(nodeName, innerClas, innerObject))
				ClassUtil.invokeSetter(this.clas, this.object, nodeName, innerObject, innerClas);
		} else{
			 ClassUtil.invokeSetter(this.clas, this.object,nodeName ,
					innerObject, innerClas);
		}

	}

	/**
	 * method for adding the array values into XmlToArrayProcessor, further
	 * processing
	 * 
	 * @param setterName
	 *            array node value
	 * @param valueClass
	 *            value class
	 * @param value
	 *            array value
	 * @see XmlToArrayProcessor
	 */
	private boolean addArray(String setterName, Class valueClass, Object value) {
		if (arrayProcessor == null){
			
			arrayProcessor = new XmlToArrayProcessor(setterName, valueClass);
			setArrayReading(ARRAY_READING);
		}
	 	if (arrayProcessor.getSetterName().equals(setterName)) {
			arrayProcessor.addArrayValue(value);
		} else {
			processArray();
			valueClass = (Class) setterMethodMap.get(StringUtil
					.initCap(setterName));
			if (ClassUtil.isArray(valueClass))
				addArray(setterName, valueClass, value);
			else
				return false;
		}
		return true;
	}

	private boolean addCollection(String setterName, Class valueClass,
			Object value) {

		if (collectionProcessor == null) {
			collectionProcessor = new XmlToCollectionProcessor(setterName,
					valueClass);
		}
		if (checkCollectionContainsObject(setterName, valueClass, value))
			return true;
		Object alreadyPresentedValue = ClassUtil.invokeGetter(
				collectionProcessor.getSetterName(), clas, object);
		if (alreadyPresentedValue != null) {
			collectionProcessor.setCollectionData(alreadyPresentedValue);
		}
		collectionProcessor.addCollection(value);
		return true;
	}

	private boolean checkCollectionContainsObject(String setterName,
			Class valueClass, Object value) {
		if (ClassUtil.isCollection(value)) {

			List list = (List) value;
			Iterator itr = list.iterator();
			while (itr.hasNext()) {
				addCollection(setterName, valueClass, itr.next());
			}
			return true;
		}
		return false;
	}

	/**
	 * after extracting all array values from the xml. this method triggers the
	 * processing of the array.
	 * 
	 * @see XmlToArrayProcessor
	 */
	private void processArray() {

		
		Object arrayObject = arrayProcessor.processArray();
		 
	  	ClassUtil.invokeSetter(clas, object, arrayProcessor.getSetterName(),arrayObject, arrayProcessor.getArrayClass());
		setArrayReading(ARRAY_NOT_READING);
		arrayProcessor = null;
	}

	private void processCollection() {
		Object collectionObject = collectionProcessor.getCollectionData();
	 	ClassUtil.invokeSetter(clas, object,collectionProcessor.getSetterName(), collectionObject,collectionProcessor.getCollectionClass());
		setCollectionReading(COLLECTION_NOT_READING);
		collectionProcessor = null;
	}

	/**
	 * getter method
	 * 
	 * @return current node name
	 */
	public String getCurrentNodeName() {
		
		return getActualValueFromAleasName(currentNodeName);
	}

	/**
	 * setter method for the current node Name
	 * 
	 * @param currentNodeName
	 */
	public void setCurrentNodeName(String currentNodeName) {
		
		this.currentNodeName = currentNodeName;
	}

	/**
	 * getter method
	 * 
	 * @return setter method map
	 */
	public Map getSetterMethodMap() {
		return setterMethodMap;
	}

	/**
	 * for setting the setterMethodMap
	 * 
	 * @param setterMethodMap
	 */
	public void setSetterMethodMap(Map setterMethodMap) {
		this.setterMethodMap = setterMethodMap;
	}

	/**
	 * getter method for clas
	 * 
	 * @return class
	 */
	public Class getClas() {
		return clas;
	}

	/**
	 * setter method for the pojo creation class
	 * 
	 * @param clas
	 */
	public void setClas(Class clas) {
		this.clas = clas;
	}

	/**
	 * setting the actual pojo object
	 * 
	 * @param object
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * getter method for the pojo object
	 * 
	 * @return pojo object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * method for setting current node value.
	 * 
	 * @param currentNodeValue
	 */
	public void setCurrentNodeValue(Object currentNodeValue) {
		this.currentNodeValue = currentNodeValue;
	}

	/**
	 * getter method for current node value
	 * 
	 * @return current node value
	 */
	public Object getCurrentNodeValue() {
		return currentNodeValue;
	}

	/**
	 * method for getting the parsed pojo object
	 * 
	 * @return pojo object
	 */
	public Object getParsedObject() {
		if (isChildEmpty)
			return null;
		return this.object;
	}

	/**
	 * setting the indicator for array reading
	 * 
	 * @param arrayReading
	 */
	public void setArrayReading(boolean arrayReading) {
		this.arrayReading = arrayReading;
	}

	/**
	 * getter method for checking the arrayreading
	 * 
	 * @return true currently array is processing
	 */
	public boolean isArrayReading() {
		return arrayReading;
	}

	/**
	 * 
	 * @param collectionReading
	 */
	public void setCollectionReading(boolean collectionReading) {
		this.collectionReading = collectionReading;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isCollectionReading() {
		return collectionReading;
	}
}