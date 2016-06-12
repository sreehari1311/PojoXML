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

import org.pojoxml.core.processor.PojoToXml;
import org.pojoxml.core.processor.XmlToPojo;

/**
 * Main implementation class for accessing the conversion classes like PojoToXml
 * and XmlToPojo
 * 
 * @author SreeHari
 * @since 1.0
 * @see {@link org.pojoxml.core.processor.PojoToXml}
 * @see {@link org.pojoxml.core.processor.XmlToPojo}
 */
class PojoXmlImpl implements PojoXml {

	/**
	 * for saving initialization info
	 */
	private PojoXmlInitInfo pojoXmlInitInfo;
	
	/**
	 * character encoding
	 */
	private String ecoding;
	
	/**
	 * CDATA indecator
	 */
	private boolean enableCDATA;

	/**
	 * Constructor for initializing 
	 */
	public PojoXmlImpl() {
		pojoXmlInitInfo = new PojoXmlInitInfo();
	}

	/**
	 * Gives POJO from xml content
	 * @param xmlContent actual xml string to convert as pojo object
	 * @param objectClass POJO class Type
	 * @return POJO Object
	 */
 	public Object getPojo(String xmlContent, Class objectClass) {
		XmlToPojo xmlToPojo = new XmlToPojo(objectClass);
		return xmlToPojo.parseXMLFromFile(xmlContent, pojoXmlInitInfo);
	}

	/**
	 * Gives POJO object from given file path
	 * @param fileName full path to locate xml file
	 * @param objectClass POJO object Class Type
	 * @return POJO object
	 */
	public Object getPojoFromFile(String fileName, Class objectClass) {
		XmlToPojo xmlToPojo = new XmlToPojo(objectClass);
		return xmlToPojo.parseXMLFromFile(fileName, pojoXmlInitInfo);

	}

	/**
	 * Gives xml message from the given object
	 * 
	 * @param object
	 *            Pojo object to convert as xml.
	 * @return xml content.
	 */
	public String getXml(Object object) {
		if (object == null)
			throw new PojoXmlException("null value can not convert as Xml");
		PojoToXml pojoToXml = new PojoToXml(pojoXmlInitInfo);
		pojoToXml.setEncoding(getEcoding());
		pojoToXml.setCDataEnabled(isEnableCDATA());
		return pojoToXml.getXml(object);
	}

	/**
	 * Generates the xml file from given object and saves in specified location
	 * 
	 * @param object
	 *            contains pojo object to convert as xml.
	 * @param fileName
	 *            holds the full path to store the xml file
	 * @return nothing
	 */
	public void saveXml(Object object, String fileName) {
		if (object == null)
			throw new PojoXmlException("null value can not convert as Xml");
		PojoToXml pojoToXml = new PojoToXml(pojoXmlInitInfo);
		pojoToXml.setEncoding(getEcoding());
		pojoToXml.setCDataEnabled(isEnableCDATA());
		pojoToXml.saveXml(object, fileName);
	}

	/**
	 * setter method for character encoding
	 * @param encoding character encoding type
	 */
	public void setEncoding(String encoding) {
		setEcoding(encoding);

	}

	/**
	 * 
	 * @param ecoding
	 */
	public void setEcoding(String ecoding) {
		this.ecoding = ecoding;
	}

	/**
	 * 
	 * @return String
	 */
	public String getEcoding() {
		return ecoding;
	}

    /**
     * method for checking whether CDATA section is enabled or not.
     * @return true means CDATA is enabled/false CDATA section is disabled
     */
	public boolean isEnableCDATA() {
		return enableCDATA;
	}

	/**
	 * indicator for adding CDATA to all string type values
	 * @param enableCDATA true means enable false means disable
	 *        default false
	 */
	public void enableCDATA(boolean enableCDATA) {
		this.enableCDATA = enableCDATA;

	}

	/**
	 * Adding the class type for identifying the type of object contained in the collection.
	 * So that PojoXml creates a new instance of given class and set values to it. and add it to
	 * collection
	 * 
	 * @param elementName actual element name presented inside the collection
	 * @param clas class Type for the object inside the collection
	 * @throws PojoXmlException if the given class is null
	 */
	public void addCollectionClass(String elementName, Class clas) {
		if (clas == null) {
			throw new PojoXmlException("Provide Class type for the field alias");
		}
		pojoXmlInitInfo.addCollectionClass(elementName, clas);
	}
	
	/**
	 * Used for giving alias names to variables defined in the POJO object.
	 * 
	 * @param cls POJO class Type
	 * @param fieldName actual variable name
	 * @param aliasName alias name for the variable
	 * @throws PojoXmlException if the given class is null
	 */
 	public void addFieldAlias(Class cls, String fieldName, String aliasName) {
		if (cls == null) {
			throw new PojoXmlException("Provide Class type for the field alias");
		}
		pojoXmlInitInfo.addFieldAlias(cls.getName() + "." + fieldName,
				aliasName);
	}

 	/**
	 * Add alias name for the root class.
	 * @param cls POJO clas Type
	 * @param aliasName alias name for the class
	 * @throws PojoXmlException if the given class is null
	 */
	public void addClassAlias(Class cls, String aliasName) {
		if (cls == null) {
			throw new PojoXmlException("Provide Class type for the field alias");
		}
		pojoXmlInitInfo.addFieldAlias(cls.getName(), aliasName);
	}

}
