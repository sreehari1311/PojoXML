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

import java.util.Collection;
import java.util.Iterator;

import org.pojoxml.core.PojoXmlException;
import org.pojoxml.core.PojoXmlInitInfo;
import org.pojoxml.util.ClassUtil;
import org.pojoxml.util.StringUtil;
import org.pojoxml.util.XmlConstant;
 

/**
 * This class is used for processing collection objects(java.util.List,java.util.Set)
 * implementations.This is supporting class for POJO to xml file. 
 * 
 * @author Sreehari
 * @since 1.0
 *
 */
class CollectionToXmlProcessor extends XmlProcessor {

	/**
	 * Indicator for collection object contains pojo object
	 */
	private boolean collectionObjectFound = false;
	
	/**
	 * holds collection object class type
	 */
	private Class collectionObjectClass;
	
	/**
	 * Constructor.  
	 * 
	 * @param space for indentation space
	 * @see ArrayToXmlProcessor#process()
	 */
	public CollectionToXmlProcessor(int space){
		setSpace(space);
	}
	
	/**
	 * constructor for creating the xml processor.
	 * 
	 * @param elementName xml element name.
	 * @param space space for indentation, xml alignment.
	 */
	public CollectionToXmlProcessor(String elementName, int space,PojoXmlInitInfo pojoXmlInit) {
		super(elementName,space,pojoXmlInit);
	}

	/**
	 * Overridden method for accessing the process method for generating xml content.
	 * @param collection object
	 */
	public String process(Object collectionObject) {
		if (isEmptyElement(collectionObject, getElementName())) {
			return  createEmptyElementWithNL(getElementName(),getAttributes(),getSpace());
		}
	 	processCollectionObject(collectionObject);
 		return getXmlContent();
	}
	
	/**
	 * supporting method for processing the collection object.
	 * 
	 * @param collectionObject
	 * @throws PojoXmlException if collection contains again collection (inner collection)
	 *  
	 */
	public void processCollectionObject(Object collectionObject) {
		collectionObjectInterospect(collectionObject);
		Iterator itr = ClassUtil.getIterator(collectionObject);
		Collection collection = (Collection) collectionObject;
		int index = 0;
		int length = collection.size();
		while (itr.hasNext()) {
			Object listOrSet = itr.next();
			if(listOrSet == null){
				processNullObjectInCollection(index, length, getElementName(),listOrSet);
				index++;
				continue;
			}
		 	if (ClassUtil.isPrimitiveOrWrapper(listOrSet.getClass())) {
				processCollectionPrimitive(index, length, getElementName(),listOrSet);
			}else if(ClassUtil.isCollection(listOrSet)){
				throw new PojoXmlException("Inner Collection object is not supported.");
			} else {
				if (index == 0){
					writeXmlContent( getStartTag(getElementName(),getAttributes(),getSpace()));
					writeXmlContent(XmlConstant.NL);
					incrementSpace(); 
				}
		 		processObject(listOrSet);
				writeXmlContent(XmlConstant.NL);
		 		if(index == length-1){
					decrementSpace();
					writeXmlContent(getCloseTagWithNL(getElementName(),getSpace()));
				}
			}
			index++;
		}
 	}

	/**
	 * method for checking collection object before processing
	 * @param collectionObject
	 */
	public void collectionObjectInterospect(Object collectionObject) {
		Iterator itr = ClassUtil.getIterator(collectionObject);
	  	while (itr.hasNext()) {
			Object listOrSet = itr.next();
			if(listOrSet == null){
		 		continue;
			}
		 	if (ClassUtil.isPrimitiveOrWrapper(listOrSet.getClass())) {
		 	}else if(ClassUtil.isCollection(listOrSet)){
				throw new PojoXmlException("Inner Collection object is not supported.");
			} else {
				setCollectionObjectClass(listOrSet.getClass());
				setCollectionObjectFound(true);
	 	 	}
	 	}
 	}
	/**
	 * For processing the primitive values contains in a collection object.
	 * 
	 * @param startIndex start index
	 * @param length size of the collection
	 * @param elementName collection element name from the POJO
	 * @param object value (primitives or wrappers) contained in the collection.
	 */
	private void processCollectionPrimitive(int startIndex, int length,String elementName, Object object) {
		 
		writeXmlContent( getStartTag(elementName,getAttributes(),getSpace()));
		writeXmlContent(StringUtil.getActualValue(object,isCdataEnabled()));
		writeXmlContent( getCloseTagWithNL(getElementName(),XmlConstant.NO_SPACE));
		 
 	}
	
	/**
	 * for processing null object in the collection
	 * @param index start index
	 * @param length size of the collection
	 * @param elementName element name
	 * @param listOrSet list or set object
	 */
	private void processNullObjectInCollection(final int index, final int length,String  elementName,Object listOrSet){
		if(listOrSet == null){
			if(isCollectionObjectFound()){
			 	if (index == 0){
					 
					writeXmlContent( getStartTag(getElementName(),getAttributes(),getSpace()));
					writeXmlContent(XmlConstant.NL);
					incrementSpace(); 
				} 
				writeXmlContent( createEmptyElementWithNL(ClassUtil.getClassName(getCollectionObjectClass()),getAttributes(),getSpace()));
				if(index == length-1){
					decrementSpace();
					writeXmlContent( getCloseTagWithNL(getElementName(),getSpace()));
				}
				
			}else{
				writeXmlContent( createEmptyElementWithNL(elementName, getAttributes(),getSpace()));
				return;
			}
		}
	 
		
	}
	/**
	 * setter method
	 * @param collectionObjectFound
	 */
	public void setCollectionObjectFound(boolean collectionObjectFound) {
		this.collectionObjectFound = collectionObjectFound;
	}

	/**
	 * getter method
	 * @return boolean
	 */
	public boolean isCollectionObjectFound() {
		return collectionObjectFound;
	}

	/**
	 * setter method
	 * @param collectionObjectClass
	 */
	public void setCollectionObjectClass(Class collectionObjectClass) {
		this.collectionObjectClass = collectionObjectClass;
	}

	/**
	 * getter method
	 * @return class
	 */
	public Class getCollectionObjectClass() {
		return collectionObjectClass;
	}
}