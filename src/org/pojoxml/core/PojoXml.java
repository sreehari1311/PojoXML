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

package org.pojoxml.core;

/**
 * PojoXml interface is contains the method for invoking the core library functionality
 * <p>
 * pojo to xml and xml back to pojo 
 * 
 * @author SreeHari
 * @since 1.0
 */
public interface PojoXml {

	/**
	 * For generating the String  from the given POJO.
	 * 
	 * @param pojoObject
	 *           POJO object for generating the xml file.
	 * @return string contains the xml content.
	 */
	public String getXml(Object pojoObject);

	/**
	 * Saves the generated xml file in the given path.
	 * 
	 * @param object
	 *            object for generating xml file.
	 * @param fileName
	 *            name of the file to save as xml. Give name as fully qualified.
	 *            for example C:\temp\file.xml
	 */
	public void saveXml(Object object, String fileName);

	/**
	 * Gives the POJO from the given xml file.
	 * 
	 * @param xmlContent
	 *            contains xml data to be converted as POJO.
	 * @param objectClass
	 *            is to generate POJO class.
	 * @return POJO object.
	 */
	public Object getPojo(String xmlContent, Class objectClass);

	/**
	 * Generating POJO object from given xml file path.
	 * 
	 * @param fileName
	 *            full path name of xml file.
	 * @param objectClass
	 *            for making POJO object from given file.
	 * @return POJO object.
	 */
	public Object getPojoFromFile(String fileName, Class objectClass);

	/**
	 * Adding encoding for the xml header for example UTF-8
	 * 
	 * @param encoding
	 *            character encoding default is UTF-8
	 */
	public void setEncoding(String encoding);

	/**
	 * indicator for adding CDATA to all string type values
	 * @param enableCDATA true means enable false means disable
	 *        default false
	 */
	public void enableCDATA(boolean enableCDATA);

	/**
	 * Adding the class type for identifying the type of object contained in the collection.
	 * So that PojoXml creates a new instance of given class and set values to it. and add it to
	 * collection
	 * 
	 * @param elementName actual element name presented inside the collection
	 * @param clas class Type for the object inside the collection
	 * @throws PojoXmlException if the given class is null
	 */
	public void addCollectionClass(String elementName, Class clas);
 
	/**
	 * Used for giving alias names to variables defined in the POJO object.
	 * 
	 * @param cls POJO class Type
	 * @param fieldName actual variable name
	 * @param aliasName alias name for the variable
	 * @throws PojoXmlException if the given class is null
	 */
	public void addFieldAlias(Class cls, String fieldName, String aliasName);

	/**
	 * Add alias name for the root class.
	 * @param cls POJO clas Type
	 * @param aliasName alias name for the class
	 * @throws PojoXmlException if the given class is null
	 */
	public void addClassAlias(Class cls, String aliasName);
}
