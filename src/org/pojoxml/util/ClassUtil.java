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

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pojoxml.exeception.ClassHandlerException;
/**
 * ClassUtil is a utility class for invoking the Given POJO methods.
 * using the reflection API. This class will class will handle all reflection API.
 * 
 * @author SreeHari
 * @since 1.0
 *
 */
public abstract class ClassUtil {
	
	/** getter method prefix*/
	private static final String GET = "get";
	
	/**Holds all primitive and wrapper Class type*/
	private static final Set primitiveType;
	
	/**Holds all primitive and wrapper Class type*/
	private static final Set wrapperType;
	
	/**
	 * holds primitive and wrapper 
	 */
	private static final Map primWraperMap;
	
	/**Holds Set and List Class Type */
	private static final Set collectionType;
	
	/**Holds setter method prefix*/
	private static final String SET = "set";	
	
	/**static initialization block for type and collectionType */
	static {
		primitiveType = new HashSet();
		wrapperType = new HashSet();
		collectionType = new HashSet();
		primWraperMap = new HashMap();
		
		primitiveType.add(byte.class);
		primitiveType.add(byte[].class);
		primitiveType.add(int.class);
		primitiveType.add(int[].class);
		primitiveType.add(short.class);
		primitiveType.add(short[].class);
		primitiveType.add(long.class);
		primitiveType.add(long[].class);
		primitiveType.add(float.class);
		primitiveType.add(float[].class);
		primitiveType.add(double.class);
		primitiveType.add(double[].class);
		primitiveType.add(char.class);
		primitiveType.add(char[].class);
		primitiveType.add(boolean.class);
		primitiveType.add(boolean[].class);
		
		wrapperType.add(java.lang.String.class);
		wrapperType.add(java.lang.Integer.class);
	  	wrapperType.add(java.lang.Long.class);
		wrapperType.add(java.lang.Short.class);
		wrapperType.add(java.lang.Character.class);
		wrapperType.add(java.lang.Boolean.class);
		wrapperType.add(java.lang.Byte.class);
		wrapperType.add(java.lang.Double.class);
		wrapperType.add(java.lang.Float.class);
		wrapperType.add(java.math.BigInteger.class);
		wrapperType.add(java.math.BigDecimal.class);
		wrapperType.add(java.util.Date.class);
		wrapperType.add(java.sql.Date.class);
		
		collectionType.add(java.util.List.class);
		collectionType.add(java.util.Set.class);
		collectionType.add(java.util.ArrayList.class);
		collectionType.add(java.util.LinkedList.class);
		collectionType.add(java.util.Vector.class);
		collectionType.add(java.util.TreeSet.class);
		collectionType.add(java.util.HashSet.class);
		collectionType.add(java.util.LinkedHashSet.class);
		
		primWraperMap.put(int.class,Integer.class);
		primWraperMap.put(short.class, Short.class);
		primWraperMap.put(byte.class, Byte.class);
		primWraperMap.put(long.class, Long.class);
		primWraperMap.put(char.class, Character.class);
		primWraperMap.put(boolean.class, Boolean.class);
		primWraperMap.put(double.class, Double.class);
		primWraperMap.put(float.class,Float.class);
 	}

	/**
	 * This method will return all the declared fields in the given Class
	 * 
	 * @param clas class to get the type
	 * @return fields all the declared instance variable information as array
	 */
	public static Field[]  getFields(Class clas){
		Assert.isNull(clas, "Class should not be Null");
		return clas.getDeclaredFields();
	}
	
	/**
	 * This method gives all instance variables declared in the given POJO.
	 * @param clas class of the pojo object
	 * @return vars variable names of all instance variables.
	 */
	public static String[] getInstanceVariables(Class clas){
		Field fields [] = clas.getDeclaredFields();
		int length = fields.length;
		String [] vars = new String[length];
		for(int i=0;i<length;i++){
			vars[i] =  fields[i].getName();
		}
		return vars;
	}
	
	
	
	/**
	 * Returns the name of the class without package name.
	 * 
	 * @param clas class of the pojo
	 * @return name class Name
	 */
	public static String getClassName(Class clas){
		
		String name = clas.getName();
		int index = name.lastIndexOf('.'); 
		return name.substring(index+1);
	}
	private static Object handleCharacterDefault(Object charObj){
		if(charObj != null){
	 	 	if(charObj.getClass() == Character.class){
	 	 		Character character = (Character)charObj;
	 	 	 	char ch = character.charValue();
		 	  	 if(ch == '\u0000')
		 	  		charObj = new Character(' '); 
		 		return charObj;
		 	}
		}
		return charObj;
	}
	/**
	 * This method will invoke the getter method of given  field name.
	 * 
	 * @param fieldName instance variable name
	 * @param clas class of the POJO
	 * @param object pojo object for invoking getter method
	 * @return retObj return value as object
	 * @throws PojoXmlException Runtime exception will throw.
	 */
	public static Object invokeGetter(String fieldName,Class clas,Object object) throws ClassHandlerException {
		Object retObj = null;
		 	Method method;
			try 
			  {
				method = clas.getMethod(getGetterMethod(fieldName),new Class[]{});
				retObj = method.invoke(object, new Object[]{});
				retObj = handleCharacterDefault(retObj);
			}
			catch (SecurityException e) 
			{
				throw new ClassHandlerException("Security Problem: "+e);
			} 
			catch (NoSuchMethodException e) {
				try {
					method = clas.getMethod("is"+StringUtil.initCap(fieldName),new Class[]{});
				 	retObj = method.invoke(object, new Object[]{});
				 	
					} 
				catch ( Exception e1)
				{
					System.out.println("Warning: No getter Method available for field :"+ fieldName+"\n");
				}
			} 
			catch (IllegalArgumentException e) 
			{
				throw new ClassHandlerException("Illegal argument exception :"+e);
			} 
			catch (IllegalAccessException e) 
			{
				throw new ClassHandlerException("Illegal access exception :"+e);
			} 
			catch (InvocationTargetException e) 
			{
				throw new ClassHandlerException("Invocation Target exception :"+e);
			}
		return retObj;
	}
	
	/**
	 * Method will executes iterator() method of given Collection types
	 * such as List,Set
	 * 
	 * @param object pojo Object
	 * @return iterator Iterator object for traverse.
	 * @throws ClassHandlerException
	 */
	public static Iterator getIterator(Object object) throws ClassHandlerException
	{
		Iterator iterator = null;
		Class clas = null;
		if(object instanceof List)
		  {
		 	clas = List.class;
		  }
		else if(object instanceof Set)
		  {
			clas = Set.class;
		  }
		try 
		  {
			Method method = clas.getMethod("iterator", new Class[]{});
		 	iterator = (Iterator)method.invoke(object, new Object[]{});
		  }  
		catch (IllegalArgumentException e) 
		  {
			throw new ClassHandlerException(e);
		  } 
		catch (IllegalAccessException e) 
		  {
			throw new ClassHandlerException(e);
		  } 
		catch (InvocationTargetException e)
		{
			throw new ClassHandlerException(e);
		}catch (SecurityException e) {
			throw new ClassHandlerException(e);
		} catch (NoSuchMethodException e) {
			throw new ClassHandlerException(e);
		}
		
		return iterator;
	}
	
	/**
	 * This method will give getter method for the given property
	 * 
	 * @param fieldName instance variable name in the POJO
	 * @return getter method name.
	 */
	public static String getGetterMethod(String fieldName){
		return GET+StringUtil.initCap(fieldName);
	}
	
	/**
	 * check whether the type of given variable name is primitive or wrapper 
	 * @param clas class of given POJO
	 * @param fieldName variable name
	 * @return boolean returns true if its primitive else false
	 * @throws ClassHandlerException
	 */
	public static boolean isPrimitiveOrWrapper(Class clas,String fieldName) throws ClassHandlerException{
		Class typeClass = null;
		try {
			
			typeClass = clas.getDeclaredField(fieldName).getType();
			if(typeClass.isArray())
				return false;
			
		} catch (SecurityException e) {
			throw new ClassHandlerException(e);
		} catch (NoSuchFieldException e) {
			throw new ClassHandlerException("No fields available: "+e);
		}
		return primitiveType.contains(typeClass)||wrapperType.contains(typeClass);
	}
	
	/**
	 * Check whether Given class instance variable is collection type or not.  
	 *
	 * @param clas class of the Pojo
	 * @param fieldName instance variable name of the given pojo object
	 * @return boolean returns true/false if its Collection object returns true else false
	 */
	public static boolean isCollection(Class clas,Object object,String fieldName) throws ClassHandlerException{
	 	boolean retVal = false;
		Object retObject = null;
	 	Class listClass = null;
		Class setClass = null;
		
		try {
		 	listClass = Class.forName("java.util.List");
			setClass = Class.forName("java.util.Set");
			retObject = invokeGetter(fieldName, clas, object);
			if (listClass.isInstance(retObject))
				retVal = true;
			if(setClass.isInstance(retObject))
				retVal = true;
		} catch (ClassNotFoundException e1) {
	 		throw new ClassHandlerException(e1);
		}
		
		 return retVal;
	}
	/**
	 * 
	 * @param collectionObject
	 * @return boolean
	 */
	public static boolean isCollection(Object collectionObject) {
		boolean retVal = false;
		Class listClass = null;
		Class setClass = null;
		try {
			listClass = Class.forName("java.util.List");
			setClass = Class.forName("java.util.Set");
						if (listClass.isInstance(collectionObject))
				retVal = true;
			if(setClass.isInstance(collectionObject))
				retVal = true;
		} catch (ClassNotFoundException e) {
			throw new ClassHandlerException(e);
		}
		return retVal;
 	}
	
	
	/**
	 * Gives the Class of the property.
	 *  
	 * @param property instance variable name.
	 * @param clas Pojo Class
	 * @return typeClass returns Class of the instance variable 
	 */
	public static Class getClassFromProperty(String property,Class clas){
		Class typeClass = null;
		try {
			typeClass = clas.getDeclaredField(property).getType();
		} catch (SecurityException e) {
			 throw new ClassHandlerException(e);
		} catch (NoSuchFieldException e) {
			throw new ClassHandlerException(e);
		}
		return typeClass;
	}
	
	/**
	 * Returns the declared variable names and its class in a map
	 * 
	 * @param clas Class of Pojo Object
	 * @return map 
	 */
	public static Map getFieldsAndType(Class clas){
		Map map = new HashMap();
		Field [] fields = clas.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			String fieldName = fields[i].getName();
			Class type =fields[i].getType();
			map.put(fieldName, type);
		}
		return map;
	} 
	/**
	 * 
	 * @param clas
	 * @return map
	 */
	public static Map getSetterAndClass(Class clas){
		Map setterMap = new HashMap();
		Method [] method =clas.getDeclaredMethods(); 
		int length = method.length;
		for(int i=0;i<length;i++){
			Method setMethod = method[i];
			if(setMethod.getName().startsWith("set")){
 				setterMap.put(StringUtil.subString(setMethod.getName(),3),setMethod.getParameterTypes()[0]);
			}
		}
		return setterMap;
	}
	/**
	 * This method will invokes the setter method of given property. with given parameter.
	 * 
	 * @param fieldName property Name
	 * @param clas  class of POJO
	 * @param object pojo object
	 * @param paramValue Parameter value
	 * @param paramClass parameter class
	 */
	public static void invokeSetter(Class clas,Object object,String fieldName,Object paramValue,Class paramClass){
		try{
		 	Method method = clas.getMethod(getSetterMethod(fieldName),new Class[]{paramClass});
			method.invoke(object, new Object[]{paramValue});
		}catch (SecurityException e) {
			throw new ClassHandlerException("Security Problem: "+e);
		} catch (NoSuchMethodException e) {
			System.out.print("Warning: No setter Method available for field :"+ fieldName+"\n" +e );
		} catch (IllegalArgumentException e) {
			throw new ClassHandlerException("Illegal argument exception :"+e);
		} catch (IllegalAccessException e) {
			throw new ClassHandlerException("Illegal access exception :"+e);
		} catch (InvocationTargetException e) {
			throw new ClassHandlerException("Invocation Target exception :"+e);
		}
	}
	
	/**
	 * Returns setter method name of the given instance variable name.
	 * 
	 * @param fieldName instance property 
	 * @return method name
	 */
	public static String getSetterMethod(String fieldName){
		return SET+StringUtil.initCap(fieldName); 
	}
	
	/**
	 *  This method will check whether the given field is array.
	 * @param clas pojo class
	 * @param fieldName field Name
	 * @return retVal true if the field name is array else false.
	 */
	public static boolean isArray(Class clas, String fieldName){
		boolean retVal = false;
		try {
			retVal = clas.getDeclaredField(fieldName).getType().isArray();
	 	} catch (SecurityException e) {
			 throw new ClassHandlerException(e);
		} catch (NoSuchFieldException e) {
			throw new ClassHandlerException(e);
		}
		return retVal;
	}
	
	/**
	 * 
	 * @param arrayObject
	 * @return int
	 */
	public static int getArrayLength (Object arrayObject) {
		int length = 0;
		length = Array.getLength(arrayObject); 
		return length;
	}
	/**
	 * 
	 * @param arrayObject
	 * @return boolean
	 */
	public static boolean isWrapperArray(Object oneArrayObject){
		boolean wrapperRet = false;
	 	Class clas = null;
		if(oneArrayObject!=null)
		clas = oneArrayObject.getClass();
		wrapperRet = wrapperType.contains(clas);
		return wrapperRet;
	}
	
	/**
	 * 
	 * @param arrayObject
	 * @return boolean
	 */
	public static boolean isCollectionArray(Object firstObject) throws ClassHandlerException{
		boolean baseCollection = false;
		boolean deriveCollection = false;
	 	Class clas = null;
		if(firstObject!=null)
		firstObject.getClass();
		baseCollection = collectionType.contains(clas);
		try {
			Class listClass = Class.forName("java.util.List");
			Class setClass = Class.forName("java.util.Set");
			if (listClass.isInstance(firstObject))
				deriveCollection = true;
			if(setClass.isInstance(firstObject))
				deriveCollection = true;
		} catch (ClassNotFoundException e1) {
	 		throw new ClassHandlerException(e1);
		}
		return baseCollection||deriveCollection;
	}
	/**
	 * 
	 * @param clas
	 * @return object
	 * @throws ClassHandlerException
	 */
	public static Object createObject(Class clas) throws ClassHandlerException{
		Object object = null;
		try {
			object = clas.newInstance();
		} catch (InstantiationException e) {
			 try {
					clas = clas.getComponentType();
					object = clas.newInstance();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			
			
		} catch (IllegalAccessException e) {
			throw new ClassHandlerException(e);
		}
		return object;
	}
	/**
	 * 
	 * @param value
	 * @param valueClass
	 * @return object
	 */
	public static Object ObjectFromValue(Object value,Class valueClass){Object object = null;
	if(valueClass == Integer.TYPE){
		object = new Integer(value.toString());
	}
	else if(valueClass == Short.TYPE){
		object = new Short(value.toString());
	}
	else if(valueClass == Byte.TYPE){
		object = new Byte(value.toString());
	}
	else if(valueClass == Boolean.TYPE){
		object = new Boolean(value.toString());
	}
	else if(valueClass == Float.TYPE){
		object = new Float(value.toString());
	}
	else if(valueClass == Long.TYPE){
		object = new Long(value.toString());
	}
	else if(valueClass == Character.TYPE || valueClass == Character.class){
		object = new Character(value.toString().charAt(0));
	}
	else if(valueClass == Double.TYPE){
		object = new Double(value.toString());
	}
	else if(valueClass == String.class){
		object = value;
	}
	else if(wrapperType.contains(valueClass)){
		try {
			Constructor construtor = valueClass.getConstructor(new Class[]{String.class});
	 		object = construtor.newInstance(new Object[] {value});
	 	} catch (SecurityException e) {
 			e.printStackTrace();
		} catch (NoSuchMethodException e) {
 			e.printStackTrace();
		} catch (IllegalArgumentException e) {
 			e.printStackTrace();
		} catch (InstantiationException e) {
 			e.printStackTrace();
		} catch (IllegalAccessException e) {
 			e.printStackTrace();
		} catch (InvocationTargetException e) {
 			e.printStackTrace();
		}
	}
	else
		object = value.toString();
	return object;
}
	
	/**
	 * 
	 * @param clas
	 * @return boolean
	 */
	public static boolean isPrimitiveOrWrapper(Class clas){
		 if(isArray(clas)){
			Class componentType = clas.getComponentType();
			if(componentType!=null)
				return primitiveType.contains(componentType)||wrapperType.contains(componentType);
		} 
	 	return primitiveType.contains(clas)||wrapperType.contains(clas);
	}
	/**
	 * 
	 * @param clas
	 * @return boolean
	 */
	public static boolean isArray(Class clas){
		boolean retValue =  false;
		if(clas!=null)
		retValue = clas.isArray();
		return retValue;
	}

	public static boolean isPrimitiveArray(Class clas){
		boolean retValue = false;
		retValue = primitiveType.contains(clas);
		return retValue;
	}
	/**
	 * 
	 * @param clas
	 * @param length
	 * @return object
	 */
	public static Object createArray(List valueList,Class clas)throws ClassHandlerException{
		
		Object arrayObject = null;
		 if(!isPrimitiveOrWrapper(clas) && !(clas.equals(char.class))&&clas.getComponentType()!= null )
			 clas = clas.getComponentType();
	 	arrayObject = Array.newInstance(clas, valueList.size());
		Iterator itr = valueList.iterator();
		int i =0;
		Class wrapperClass = (Class)primWraperMap.get(clas);
		if(wrapperClass == null)
			wrapperClass = clas;
				while(itr.hasNext()){
			Object value = itr.next() ;
			try {
				 Constructor construtor = null;
				Object setObject = null;
				if(clas.equals(char.class)||clas.equals(Character.class)){
					if(value == null)
						 setObject = null;
					else
						setObject = new Character(value.toString().charAt(0));
				}
				else if(isPrimitiveOrWrapper(clas)){
			 	construtor = wrapperClass.getConstructor(new Class[]{String.class});
				if(value== null)
				 setObject = null;
				else
				setObject = construtor.newInstance(new String[]{value.toString()});
				}
				else {
					setObject = value;
				}
			  	Array.set(arrayObject,i,setObject);
			 	
		 	} catch (SecurityException e) {
				throw new ClassHandlerException(e);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}catch (IllegalArgumentException e) {
	 			e.printStackTrace();
			} catch (InstantiationException e) {
	 			e.printStackTrace();
			} catch (IllegalAccessException e) {
	 			e.printStackTrace();
			} catch (InvocationTargetException e) {
	 			e.printStackTrace();
			}
			i++;
		}
		return arrayObject;
	}

	/**
	 * 
	 * @param clas
	 * @return boolean
	 */
	public static boolean isCollection(Class clas) {
		boolean retVal = false;
		retVal = collectionType.contains(clas);
		return retVal;
 	}
	
	public static Class getPrimitiveClass(Class clas){
		Class retClass = null;
		if(primWraperMap.containsKey(clas));
			retClass = (Class)primWraperMap.get(clas);
		return retClass;
	}
	
}