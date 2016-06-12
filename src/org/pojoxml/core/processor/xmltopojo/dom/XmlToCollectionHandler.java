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

import java.util.ArrayList;
import java.util.List;

import org.pojoxml.core.PojoXmlException;
import org.pojoxml.core.PojoXmlInitInfo;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * For processing collection element
 * @author Sreehari
 * @since 1.0
 */
public class XmlToCollectionHandler {
	/**
	 * intialization info
	 */
	PojoXmlInitInfo initInfo;
	
	/**
	 * current node name
	 */
	private String currentNodeName;
	
	/**
	 * current node value
	 */
	private Object currentNodeValue;
	
	/**
	 * setter name for the collection object
	 */
	private String currentSetterName;
	
	/**
	 * for holding inner objects
	 */
	private List objects;
	
	/**
	 * constructor for initializing
	 * @param initInfo initialization infoo
	 * @param setterName
	 */
	public XmlToCollectionHandler(PojoXmlInitInfo initInfo,String setterName){
		this.initInfo = initInfo;
		objects = new ArrayList();
		this.currentSetterName = setterName;
	}
	
	/**
	 * processing collection object
	 * @return collectio object from xml
	 */
 	public Object processCollection(){
 		Node collectionNode = initInfo.getRootNode();
		readChildren(collectionNode);
		if(objects.size()>0)
			return objects;
 		return getCurrentNodeValue();
	}
	
 	/**
 	 * method for processing child node
 	 * @param node
 	 */
	private void readChildren(Node node) {
		if (!node.hasChildNodes())
			return;
		setCurrentNodeName(node.getNodeName() );
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = (Node) childNodes.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				 	Class clas= initInfo.getCollectionClass(child.getNodeName());
				 	if(clas == null){
				 		String fieldName = child.getNodeName();
				 		throw new PojoXmlException("Class for "+fieldName+" is not specified## Specify it using PojoXmlObj.addCollectionClass(\""+fieldName+"\","+fieldName+".class) method");
				 	}
				 	if(child.getChildNodes().getLength() == 0){
				 		objects.add(null);
				 	}else{
					initInfo.setRootNode(child);
					XmlToObjectProcessor newObject = new XmlToObjectProcessor(clas,initInfo);
					Object innerObject = newObject.getObject();
					objects.add(innerObject);
				 	}
				continue;
			} else if (child.getNodeType() == Node.TEXT_NODE) {
		 		setCurrentNodeValue(child.getNodeValue());
			}
		}
 	}
	/**
	 * setter method
	 * @param currentNodeName
	 */
	public void setCurrentNodeName(String currentNodeName) {
		this.currentNodeName = currentNodeName;
	}

	/**
	 * getter method
	 * @return String
	 */
	public String getCurrentNodeName() {
		return currentNodeName;
	}

	/**
	 * setter method
	 * @param currentNodeValue
	 */
	public void setCurrentNodeValue(Object currentNodeValue) {
		this.currentNodeValue = currentNodeValue;
	}

	/**
	 * getter method
	 * @return object
	 */
	public Object getCurrentNodeValue() {
		return currentNodeValue;
	}

	/**
	 * setter method
	 * @param currentSetterName
	 */
	public void setCurrentSetterName(String currentSetterName) {
		this.currentSetterName = currentSetterName;
	}

	/**
	 * getter method
	 * @return String
	 */
	public String getCurrentSetterName() {
		return currentSetterName;
	}
}