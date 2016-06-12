/*
 * Copyright 2002-2007 the original author or authors.
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
package org.pojoxml.util;
/**
 * This class is used for genarating the xml file from the object
 * 
 * @author Sree Hari
 * @since 1.0
 */
public class XmlUtil {
	
	/**
	 * 
	 * @param elementName
	 * @param elementValue
	 * @param space
	 * @return string
	 */
	public static  String getElement(String elementName,String elementValue,int space) {
		return getSpace(space)+XmlConstant.START_TAG+elementName+XmlConstant.CLOSE_TAG+elementValue+XmlConstant.END_TAG+elementName+XmlConstant.CLOSE_TAG;
	}
	
	/**
	 * 
	 * @param elementName
	 * @param elementValue
	 * @param space
	 * @return String
	 */
 
		public static  String getElementWithNL(String elementName,String attributes,String elementValue,int space) {
			if(elementValue.equals(""))
				return createEmptyElementWithNL(elementName,space);
			return getSpace(space)+XmlConstant.START_TAG+elementName+attributes+XmlConstant.CLOSE_TAG+elementValue+XmlConstant.END_TAG+elementName+XmlConstant.CLOSE_TAG+XmlConstant.NL;
		}
		/**
		 * 
		 * @param elementName
		 * @param space
		 * @return String
		 */
		public static String getCloseTag(String elementName,int space){
		return getSpace(space)+XmlConstant.END_TAG+elementName+XmlConstant.CLOSE_TAG;
	}
		
		/**
		 * 
		 * @param elementName
		 * @param space
		 * @return String
		 */
	public static String getCloseTagWithNL(String elementName,int space){
		return getSpace(space)+XmlConstant.END_TAG+elementName+XmlConstant.CLOSE_TAG+XmlConstant.NL;
	}
	
	/**
	 * 
	 * @param elementName
	 * @param space
	 * @return String
	 */
	public static String getStartTag(String elementName,int space){
		return getSpace(space)+XmlConstant.START_TAG+elementName+XmlConstant.CLOSE_TAG;
	}
	/**
	 * 
	 * @param elementName
	 * @param space
	 * @return String
	 */
	public static String getStartTag(String elementName,String attributes,int space){
		return getSpace(space)+XmlConstant.START_TAG+elementName+attributes+XmlConstant.CLOSE_TAG;
	}
	
	 
	/**
	 * 
	 * @param elementName
	 * @param space
	 * @return String
	 */
	public static String getStartTagWithNL(String elementName,String attributes,int space){
		return getSpace(space)+XmlConstant.START_TAG+elementName+attributes+XmlConstant.CLOSE_TAG+XmlConstant.NL;
	}
	/**
	 * 
	 * @param elementName
	 * @param space
	 * @return String
	 */
	public static String createEmptyElement(String elementName,int space) {
		return getSpace(space)+XmlConstant.START_TAG+elementName+XmlConstant.EMPTY_TAG;
	}
	
	/**
	 * 
	 * @param elementName
	 * @param space
	 * @return String
	 */
	public static String createEmptyElementWithNL(String elementName,int space) {
		return getSpace(space)+XmlConstant.START_TAG+elementName+XmlConstant.EMPTY_TAG+XmlConstant.NL;
	}
	 
	/**
	 * 
	 * @param elementName
	 * @param space
	 * @return String
	 */
	public static String createEmptyElementWithNL(String elementName,String attributes,int space) {
		return getSpace(space)+XmlConstant.START_TAG+elementName+attributes+XmlConstant.EMPTY_TAG+XmlConstant.NL;
	}
	/**
	 * 
	 * @param space
	 * @return String
	 */
	public static String getSpace(int space){
		String spc = "";
		for (int i=0;i<space;i++){
			spc += XmlConstant.SPACE;
		}
		return spc;
	}
	public static String getEncoding(String encoding){
		if(encoding == null){
			return XmlConstant.DEFAULT_ENCODING;
		}
		return encoding;
	}
}
