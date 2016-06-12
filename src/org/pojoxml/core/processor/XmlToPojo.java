package org.pojoxml.core.processor;
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

 import java.io.File;
import java.io.IOException;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.pojoxml.core.PojoXmlException;
import org.pojoxml.core.PojoXmlInitInfo;
import org.pojoxml.core.processor.xmltopojo.dom.XmlToObjectProcessor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


/**
 * This class is used for invoking the parsers and get the POJO object from the xml
 * @author Sreehari
 * @since 1.0
 *
 */
public class XmlToPojo {

	/**
	 * class type to creates pojo object
	 */
	private Class clas;
	
	/**
	 * Constructor
	 * @param clas
	 */
	public XmlToPojo(Class clas){
		this.clas = clas;
	 
	}
	
	/**
	 *  method for parsing xml from file
	 * @param fileName full path name
	 * @return POJO object
	 * @throws PojoXmlException
	 */
	public Object parseXMLFromFile(String fileName,PojoXmlInitInfo initInfo)  {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
 		DocumentBuilder docBuilder = null;
		Document document = null;
 		factory.setCoalescing(true);
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		factory.setNamespaceAware(true);
		factory.setValidating(false);
  		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			 throw new PojoXmlException("Configuration issue : "+e);
		}
		try {
			document = docBuilder.parse( new File(fileName));
		} catch (SAXException e) {
			throw new PojoXmlException("Parser Initialization issue : "+e);
			 
		} catch (IOException e) {
			throw new PojoXmlException("Parser Initialization issue : "+e);
			 
		}
		Node root = document.getDocumentElement();
		initInfo.setRootNode(root);
		XmlToObjectProcessor objectCreator = new XmlToObjectProcessor(clas,initInfo);
		return objectCreator.getParsedObject();
 
  	}
	/**
	 * method for parsing from xml string  
	 * @param xmlData xml string
	 * @param initInfo initialization info
	 * @return POJO object
	 * @throws PojoXmlException
	 */
	public Object parseXMLFromXmlData(String xmlData,PojoXmlInitInfo initInfo)  {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
 		DocumentBuilder docBuilder = null;
		Document document = null;
 		factory.setCoalescing(true);
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		factory.setNamespaceAware(true);
		factory.setValidating(false);
  		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new PojoXmlException("Configuration issue : "+e);
		}
		try {
			document = docBuilder.parse(xmlData);
		} catch (SAXException e) {
			throw new PojoXmlException("Parser Initialization issue : "+e);
		} catch (IOException e) {
			throw new PojoXmlException("Parser Initialization issue : "+e);
		}
		Node root = document.getDocumentElement();
		initInfo.setRootNode(root);
		XmlToObjectProcessor objectCreator = new XmlToObjectProcessor(clas,initInfo);
		return objectCreator.getParsedObject();
 
  	}

	/**
	 * setter method
	 * @param clas
	 */
	public void setClas(Class clas) {
		this.clas = clas;
	}
	
	/**
	 * getter method.
	 * @return Class
	 */
	public Class getClas() {
		return clas;
	}

	
}

