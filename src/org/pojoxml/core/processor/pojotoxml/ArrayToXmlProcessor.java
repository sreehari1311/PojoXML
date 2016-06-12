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

import org.pojoxml.core.PojoXmlInitInfo;
import org.pojoxml.util.ClassUtil;
import org.pojoxml.util.StringUtil;
import org.pojoxml.util.XmlConstant;
 

/**
 * This class is used for generating the xml content.which will invokes when an array element contains
 * in a POJO. process() method will invokes.
 * 
 * @author SreeHari
 * @since 1.0
 */
class ArrayToXmlProcessor extends XmlProcessor {
	
	 
	
	/**
	 * Constructor for setting the initial values.
	 * @param elementName elementName of the array
	 * @param space tab indentation space
	 */
	public ArrayToXmlProcessor(String elementName,String attrib,int space,PojoXmlInitInfo pojoXmlInitInfo) {
		super(elementName,attrib,space,pojoXmlInitInfo);
 	}
	
	/**
	 * Overridden method for generating xml from arrayObject.
	 * @param arrayObject contains array object
	 * @return xml string generated
	 */
	public String process(Object arrayObject) {
		
 		if(isEmptyElement(arrayObject, getElementName())) {
			return getXmlContent();
		}
		int length = ClassUtil.getArrayLength(arrayObject);
	 	 if( length == 0){
			return  createEmptyElementWithNL(getElementName(),getAttributes(),getSpace());
		} 
	 	boolean isPrimitive = ClassUtil.isPrimitiveOrWrapper(arrayObject.getClass());
		for(int i = 0;i < length ;i++){
			Object arrayElement = java.lang.reflect.Array.get(arrayObject,i);
			
			boolean isWrapperArray = ClassUtil.isWrapperArray(arrayElement);
			boolean isCollectionArray = ClassUtil.isCollectionArray(arrayElement);
			
		 	if(isNullArrayObject(arrayElement,arrayObject.getClass(),isPrimitive,isWrapperArray,isCollectionArray,i,length)){
		 		continue;
			}
			if(isPrimitive ||isWrapperArray){
				String elementValue = StringUtil.getActualValue(arrayElement,isCdataEnabled());
				writeXmlContent( getElementWithNL(getElementName(),getAttributes(),elementValue,getSpace()));
			}else if(isCollectionArray){
		 		writeXmlContent(getStartTagWithNL(getElementName(),getAttributes(),getSpace()));
		 		incrementSpace();
		 		processCollection(arrayElement);
		 		decrementSpace();
				writeXmlContent( getCloseTagWithNL(getElementName(),getSpace()));
		 	}else{
		 	 		processObject(arrayElement,getElementName());
		 	 		writeXmlContent(XmlConstant.NL);
		 		}
			}
		return getXmlContent();
	}
	
	/**
	 * private method for checking null value 
	 * @param arrayElement array object value
	 * @param arrayType class type of array
	 * @param isPrimitve true means primitive
	 * @param isWrapper true means wrapper
	 * @param isCollection true means collection object
	 * @param start start index of array
	 * @param length end index of array
	 * @return if array element is null return false
	 */
	private boolean isNullArrayObject(Object arrayElement,Class arrayType,boolean isPrimitve,boolean isWrapper,boolean isCollection,int start,int length){
		if(arrayElement!= null)
			return false;
		
		if(isPrimitve || isWrapper){
			writeXmlContent( createEmptyElementWithNL(getElementName(),getAttributes() ,getSpace()));
			return true;
		}else{
	 		writeXmlContent( createEmptyElementWithNL(getElementName(),getAttributes() ,getSpace()));
	 		return true;
		}
	 
	}
	
	
}