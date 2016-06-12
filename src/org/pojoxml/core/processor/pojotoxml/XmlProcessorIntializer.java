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

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.pojoxml.util.ClassUtil;
import org.pojoxml.util.StringUtil;
import org.pojoxml.util.XmlConstant;
/**
 * This class is used for initializing pojo to xml conversion
 * @author SreeHari
 * @since 1.0
 *
 */
 class XmlProcessorIntializer {
	/**
	 * For extracting all instance variable from POJO object.
	 * 
	 * @param clas class for POJO
	 * @return initInfo 
	 * @see XmlProcessorInitInfo
	 */
	public static XmlProcessorInitInfo getInstanceVariablesAndAttributes(Class clas){
		Field fields [] = ClassUtil.getFields(clas);
		int length = fields.length;
 		List elementList = new ArrayList();
 		List attributeMayBeList = new ArrayList();
 	 	Map attributes = new HashMap();
 		XmlProcessorInitInfo initInfo = new XmlProcessorInitInfo();
 		boolean attribute = false;
 	 	
 		for(int i=0;i<length;i++){
 			if(fields[i].getName().startsWith(XmlConstant.ATTRIBUTE)){
 				attribute = true;
 				attributeMayBeList.add(fields[i].getName().substring(XmlConstant.ATTRIBUTE_LENGTH));
 			}
 			elementList.add(fields[i].getName());
 			 
		}
 		if(!attribute){
 			initInfo.setAttribute(false);
 			initInfo.setElements(StringUtil.toStringArray(elementList.toArray()));
 			return initInfo;
 		}
 		Iterator maybeItr = attributeMayBeList.iterator();
		while(maybeItr.hasNext()){
			String attributeName = maybeItr.next().toString();
			String attributeNameToCheck = StringUtil.initSmall(attributeName);
			boolean attributeFound = false;
			Iterator elementItr = elementList.iterator();
			String elementName  = null;
			while(elementItr.hasNext()){
				elementName = elementItr.next().toString();
				if(attributeNameToCheck.startsWith(elementName)){
					if(attributeNameToCheck.length()>elementName.length()){
						if(!attributes.containsKey(elementName)){
							List attribList = new ArrayList();
							attribList.add(XmlConstant.ATTRIBUTE+attributeName);
							attributes.put(elementName, attribList);
						}else{
						  List attribList =(List)attributes.get(elementName);
						  attribList.add(XmlConstant.ATTRIBUTE+attributeName);
						}
						attributeFound = true;
					}
				}
			}
			
			if(attributeFound){
				elementList.remove(XmlConstant.ATTRIBUTE+attributeName);
				initInfo.setAttribute(true);
			} 
	   	}
		initInfo.setAttributes(attributes);
		initInfo.setElements(StringUtil.toStringArray(elementList.toArray()));
 		return initInfo;
	}
}
