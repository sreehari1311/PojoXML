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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pojoxml.util.ClassUtil;
 
/**
 * This class is used for processing the array if any array is found
 * @author SreeHari
 * @since 1.0
 *
 */
public class XmlToArrayProcessor {

	/**
	 * for holding the array values
	 */
	private List arrayHandler;
	
	/**
	 * class for the array value
	 */
	private Class arrayClass;
	
	/**
	 * setter method name for the array object to set
	 */
	private String setterName;
	
	/**
	 * for getting the actual class
	 */
	private static Map arrayClassMap;
	
	/**
	 * static block for initializing the array block
	 */
	static{
		arrayClassMap = new HashMap();
		arrayClassMap.put(byte[].class, Byte.TYPE);
		arrayClassMap.put(Byte[].class, Byte.class);
		
		arrayClassMap.put(short[].class, Short.TYPE);
		arrayClassMap.put(Short[].class, Short.class);
		
		arrayClassMap.put(int[].class,Integer.TYPE);
		arrayClassMap.put(Integer[].class,Integer.class);
		
		arrayClassMap.put(long[].class, Long.TYPE);
		arrayClassMap.put(Long[].class, Long.class);
		
		arrayClassMap.put(float[].class,Float.TYPE);
		arrayClassMap.put(Float[].class,Float.class);
		
		arrayClassMap.put(double[].class, Double.TYPE);
		arrayClassMap.put(Double[].class, Double.class);
		
		arrayClassMap.put(String[].class,String.class);
		
		arrayClassMap.put(char[].class, Character.TYPE);
		arrayClassMap.put(Character[].class, Character.class);
		
		arrayClassMap.put(boolean[].class, Boolean.TYPE);
		arrayClassMap.put(Boolean[].class, Boolean.class);
		
		arrayClassMap.put(BigDecimal[].class, BigDecimal.class);
		arrayClassMap.put(BigInteger[].class, BigInteger.class);
	}
	
	/**
	 * Constructor for initializing the array processor
	 * @param setterName setter method name for the array
	 * @param arrayClass array class to set
	 */
	public XmlToArrayProcessor(String setterName,Class arrayClass){
		arrayHandler = new ArrayList();
		this.arrayClass = arrayClass;
		this.setSetterName(setterName);
	}
	
	/**
	 * for adding one value to array for further processing
	 * @param arrayValue array value object
	 */
	public void addArrayValue(Object arrayValue) {
		if(arrayValue instanceof List){
			arrayHandler = (List) arrayValue;
		 	return;
		}
		arrayHandler.add(arrayValue);
	}
	
	/**
	 * method for creating array class from group of values.
	 * @return array object
	 */
	public Object processArray(){
		Class cls = (Class)arrayClassMap.get(arrayClass);
		
		if (cls == null)
			cls = arrayClass;
		if((arrayHandler == null ))
			return null;
 		return ClassUtil.createArray(arrayHandler,cls );
	}
		
	/**
	 * setter method for array handler
	 * @param arrayHandler
	 */
	public void setArrayHandler(List arrayHandler) {
		this.arrayHandler = arrayHandler;
	}

	/**
	 * getter method for array handler
	 * @return list
	 */
	public List getArrayHandler() {
		return arrayHandler;
	}
 
	/**
	 * setter method for array class
	 * @param arrayClass
	 */
	public void setArrayClass(Class arrayClass) {
		this.arrayClass = arrayClass;
	}
 
	/**
	 * getter method for the array class
	 * @return Class
	 */
	public Class getArrayClass() {
		
		return arrayClass;
	}
	
	/**
	 * setter method for array setter
	 * @param setterName
	 */
	public void setSetterName(String setterName) {
		this.setterName = setterName;
	}
	
	/**
	 * getter method for the setter method name
	 * @return string
	 */
	public String getSetterName() {
		return setterName;
	}
}
