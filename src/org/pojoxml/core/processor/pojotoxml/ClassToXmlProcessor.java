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

package org.pojoxml.core.processor.pojotoxml;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pojoxml.core.PojoXmlInitInfo;
import org.pojoxml.util.ClassUtil;
import org.pojoxml.util.StringUtil;
import org.pojoxml.util.XmlConstant;
 
 
/**
 * This is the main class for processing POJO. This class read the instance
 * variable defined in the given object and execute corresponding getter
 * methods. and get the values and generate the element by element based on the
 * name given to the instance level variables. If the given POJO contains
 * Objects or list or Set.  
 * 
 * @author SreeHari
 * @since 1.0
 */
public class ClassToXmlProcessor extends XmlProcessor {

	/**
	 * Holds the all declared instance variable name in the POJO object.
	 */
	private String tagNames[];

	/**
	 * Holds the root element Name.
	 */
	private String rootName;

	/**
	 * Object of the real POJO
	 */
	private Object object;
 	
	/**
 	 * Class of the POJO
 	 */
	private Class clas;

	/**
	 * contains the complete attributes list for the elements.
	 * elementName as key and attributes are as list.
	 */
	private Map attributeMap;
    
	/**
     * 
     * @param space
     */
	public ClassToXmlProcessor (int space,PojoXmlInitInfo pojoXmlInitInfo){
		this(null,XmlConstant.NOTHING,space,pojoXmlInitInfo);
	}
	/**
     * 
     * @param space
     */
	public ClassToXmlProcessor (int space,String elementName,PojoXmlInitInfo pojoXmlInitInfo){
		this(elementName,XmlConstant.NOTHING,space,pojoXmlInitInfo);
	}
 	/**
	 * Constructor for initializing the instance variables
	 *
	 * @param elementName for holding the name of current element
	 * @param attributes holds the attribute name and value list.
	 * @param clas holds the class type of the POJO object
	 * @param object contains POJO object
	 * @param space for holding xml content indentation space
	 */
	public ClassToXmlProcessor (String elementName,String attributes,int space,PojoXmlInitInfo pojoXmlInitInfo){
		super(elementName,attributes,space,pojoXmlInitInfo);
	}
	
	/**
	 * This method will initialize most of the instance variables and reading process. such as
	 * getting all the instance variable names and read the root object name.
	 */
	private void init() {
		XmlProcessorInitInfo initInfo = XmlProcessorIntializer.getInstanceVariablesAndAttributes(getClas());
		setTagNames(initInfo.elements);
		 if(initInfo.isAttribute()){
		 	setAttribute(initInfo.isAttribute());
			setAttributeMap(initInfo.getAttributes());
		}
		if (getElementName() == null) {
			String rootName = "";
			rootName = getClassAliasName(getClas().getName());
			if(rootName == null)
			setRootName(ClassUtil.getClassName(getClas()));
			else
			setRootName(rootName);
		} else {
		 	setRootName( getElementName());
		}
		 
		writeXmlContent(getStartTagWithNL(getRootName(),getAttributes(),getSpace()));
		incrementSpace();
	}
	
	/**
	 * This is overridden method. The main pojo to xml process action.
	 * {@link Override}{@link #process(Object)}
	 *  
	 *  @param object actual pojo object to convert as xml.
	 *  @return generated xml content from the given pojo object
	 */
	public String process(Object object) {
		if(object == null){
			writeXmlContent(getStartTagWithNL(getRootName(),getAttributes(),getSpace()));
			return getXmlContent();
		}
		this.clas = object.getClass();
		setClassName(clas.getName());
		this.object = object;
		init();
		toXml();
		return getXmlContent();
	}
	
	/**
	 * This method reads the tagNames one by one and executes the respective
	 * getters.
	 * 
	 * @see ClassToXmlProcessor#readCollection(String)
	 * @see ClassToXmlProcessor#readObjectGetters(String)
	 * @see ClassToXmlProcessor#readPrimitiveGetters(String)
	 */
	private void toXml() {
		for (int i = 0; i < tagNames.length; i++) {
 			String classElementName = tagNames[i];
 	 		if(ClassUtil.isPrimitiveOrWrapper(getClas(), classElementName)){
		 		readPrimitiveOrWrapperGetters(classElementName);
			} else if (ClassUtil.isArray(getClas(), classElementName)) {
	 			readArray(classElementName);
			} else if (ClassUtil.isCollection(getClas(), getObject(), classElementName)) {
	 	 		readCollection(classElementName);
			} else {
			   readObjectGetters(classElementName);
			}
	 	}
		decrementSpace();
		writeXmlContent(getCloseTag(getRootName(),getSpace()));
	}
	/**
	 * reads primitive getters and gets the value and generates the
	 * xml content.
	 * 
	 * @param elementName contains the primitive or wrapper instance variable name.
	 */
	public void readPrimitiveOrWrapperGetters(String elementName) {
		String elementValue =  StringUtil.getActualValue(ClassUtil.invokeGetter(elementName, clas, object),isCdataEnabled());
		String element =  getElementWithNL(elementName,elementAttributes(elementName),elementValue,getSpace());
		writeXmlContent(element);
	}
	
	/**
	 * For generating attributes name and values for the given element
	 *  
	 * @param elementName for generating the attributes.
	 * @return attribute name and values. 
	 */
	public String elementAttributes(String elementName){
		 if(!isAttribute() ||attributeMap==null|| !attributeMap.containsKey(elementName))
			return XmlConstant.NOTHING;
		String attributeNameAndValues = XmlConstant.NOTHING;
		List attr  = (List) attributeMap.get(elementName);
	 	Iterator itr = attr.iterator();
		
	 	while(itr.hasNext()){
			String attribProperty = itr.next().toString();
			String attributeValue =  StringUtil.getActualValue(ClassUtil.invokeGetter(attribProperty, clas, object),isCdataEnabled());
			String attributeName = attribProperty.substring(XmlConstant.ATTRIBUTE_LENGTH);
			attributeName = XmlConstant.SINGLE_SPACE+StringUtil.initSmall(attributeName.substring(elementName.length()));
			attributeNameAndValues += attributeName+XmlConstant.EQUAL+XmlConstant.QUOTE+attributeValue+XmlConstant.QUOTE;
		}
	   	return attributeNameAndValues;
	}
	
	/**
	 * For reading array.
	 * @param elementName elementName.
	 * @see ArrayToXmlProcessor
	 */
	public void readArray(String elementName){
		ArrayToXmlProcessor arrayToXmlProcessor = new ArrayToXmlProcessor(elementName,elementAttributes(elementName),getSpace(),getPojoXmlInitInfo());
		arrayToXmlProcessor.setCdataEnabled(isCdataEnabled());
		arrayToXmlProcessor.setClassName(getClassName());
		Object arrayObject =  ClassUtil.invokeGetter(elementName, getClas(), getObject());
		writeXmlContent(arrayToXmlProcessor.process(arrayObject));
		
	}
	 
	/**
	 * If the POJO contains the Collection elements like {@link List}
	 * or {@link Set} this method will call and iterate through the collection object.
	 * and gets object by object and generate the xml and append it to xmlContent
	 * 
	 * @param elementName contains the Collection instance variable name
	 */
	 void readCollection(String elementName){ 
		CollectionToXmlProcessor collectionToXmlProcessor = new CollectionToXmlProcessor(elementName,getSpace(),getPojoXmlInitInfo());
		collectionToXmlProcessor.setCdataEnabled(isCdataEnabled());
		collectionToXmlProcessor.setClassName(getClassName());
		if(isAttribute()){
			collectionToXmlProcessor.setAttributes(elementAttributes(elementName));
		}
		Object collectionObject = ClassUtil.invokeGetter(elementName,getClas(), getObject());
	 	writeXmlContent(collectionToXmlProcessor.process(collectionObject));
	 }
	 
	 /**
	  * If POJO contains the other Object other than wrapper or list. this method
	  * will call and initialize the object of the same class and call
	  * {@link #toString()} method and append the generated xml to
	  * {@link #xmlContent}
	  * 
	  * @param fieldName instance variable name of the object
	  */
	 void  readObjectGetters(String classElementName) {
	  	Object otherObj = ClassUtil.invokeGetter(classElementName, clas, object);
		String attrib = elementAttributes(classElementName);
		if (isEmptyElement(otherObj, classElementName,attrib)) {
		 	return;
		}
	 	processObject(otherObj, classElementName, attrib);
		writeXmlContent(XmlConstant.NL);
	 }
	
	 	
 	/**
	 * returns the instance variable names.for generating the xml element name
	 * @return tagNames. holds all element names.
	 */
	public String[] getTagNames() {
		return tagNames;
	}

	/**
	 * setting the tagNames.
	 * @param tagNames holds all the element names.
	 */
	public void setTagNames(String[] tagNames) {
		this.tagNames = tagNames;
	}

	/**
	 * setting root element name.
	 * @param rootName holds root element name.
	 */
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	/**
	 * for getting root element name.
	 * @return rootName
	 */
	public String getRootName() {
		return rootName;
	}

 
	/**
	 * For getting the object.
	 * @return object
	 */
	public Object getObject() {
		return object;
	}
	
	/**
	 * For setting the object.
	 * @param object
	 */
	public void setObject(Object object) {
		this.object = object;
	}
	
	/**
	 * For getting the class.
	 * @return class.
	 */
	public Class getClas() {
		return clas;
	}
	
	/**
	 * For setting the class.
	 * @param clas
	 */
	public void setClas(Class clas) {
		this.clas = clas;
	}

	/**
	 * For setting attribute map.
	 * @param attributeMap
	 */
	public void setAttributeMap(Map attributeMap) {
		this.attributeMap = attributeMap;
	}

	/**
	 * For getting attribute map.
	 * @return map
	 */
	public Map getAttributeMap() {
		return attributeMap;
	}	
}
