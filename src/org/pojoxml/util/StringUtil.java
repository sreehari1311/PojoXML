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

package org.pojoxml.util;

/**
 * 
 * @author Sreehari
 * @since 1.0
 *
 */
public class StringUtil {
	
	public static String[] toStringArray(Object[] objects){
		String stringArr[] = new String[objects.length];
		for(int i=0;i<objects.length;i++){
			stringArr[i] = objects[i].toString();
		}
		return stringArr;
	}
	/**
	 * 
	 * @param str
	 * @return string
	 */
	public static String initCap(String str){
		char chars[] = str.toCharArray();
		chars[0] =  Character.toUpperCase(chars[0]);
		return new String(chars);
	}

	/**
	 * 
	 * @param str
	 * @return string
	 */
	public static String initSmall(String str){
		char chars[] = str.toCharArray();
		chars[0] =  Character.toLowerCase(chars[0]);
		return new String(chars);
	}
	/**
	 * 
	 * @param object
	 * @return string
	 */
	public static String getActualValue(Object object,boolean cdataEnabled){
		if(object == null){
			return "";
		}
		if(cdataEnabled){
			if(object.getClass().equals(String.class)){
				return "<![CDATA["+object.toString()+"]]>";
			}
		}
		return object.toString();
	}
	
	/**
	 * 
	 * @param string
	 * @param index
	 * @return String
	 */
	public static String subString(String string,int index){
		if(string == null)
			return null;
		return string.substring(index);
	}
}
