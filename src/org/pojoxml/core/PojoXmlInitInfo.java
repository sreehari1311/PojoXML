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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

/**
 * Global initialization passed over every modules
 * @author SreeHari
 * @since 1.0
 */
public class PojoXmlInitInfo {

	/**
	 * for holding collection class
	 */
	private Map collectionClassMap;
	
	/**
	 * for holding variable alias names
	 */
	private Map fieldAliasMap;
	
	/**
	 * getter method for collection map
	 * @return map
	 */
	public Map getCollectionClassMap() {
		return collectionClassMap;
	}
	
	/**
	 * 
	 * @param collectionClassMap
	 */
	public void setCollectionClassMap(Map collectionClassMap) {
		this.collectionClassMap = collectionClassMap;
	}

	/**
	 * 
	 */
	private Node rootNode;
	
	/**
	 * 
	 */
	public PojoXmlInitInfo(){
		collectionClassMap = Collections.synchronizedMap(new HashMap());
		fieldAliasMap = Collections.synchronizedMap(new HashMap());
 	}
	
	/**
	 * 
	 * @param elementName
	 * @param cls
	 */
	public void addCollectionClass(String elementName,Class cls){
	 	collectionClassMap.put(elementName, cls);
	}
	
	/**
	 * 
	 * @param fieldName
	 * @param aliasName
	 */
	public void addFieldAlias(String fieldName,String aliasName){
	     fieldAliasMap.put(fieldName, aliasName);
	}
	
	/**
	 * 
	 * @param elementName
	 * @return Class
	 */
	public Class getCollectionClass(String elementName){
		return (Class)collectionClassMap.get(elementName);
	}
	
	/**
	 * 
	 * @param rootNode
	 */
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}
	
	/**
	 * 
	 * @return Node
	 */
	public Node getRootNode() {
		return rootNode;
	}
	
	/**
	 * 
	 * @param fieldName
	 * @return Object
	 */
	public Object getAliasName(String fieldName){
		if(fieldAliasMap == null)
			return null;
		return fieldAliasMap.get(fieldName);
	}
	
	/**
	 * 
	 * @param elementName
	 * @return Object
	 */
	public Object classForCollection(String elementName){
		if(collectionClassMap == null)
			return null;
		 
		return collectionClassMap.get(elementName);
	}
	
	/**
	 * 
	 * @param fieldAliasMap
	 */
	public void setFieldAliasMap(Map fieldAliasMap) {
		this.fieldAliasMap = fieldAliasMap;
	}
	
	/**
	 * 
	 * @return Map
	 */
	public Map getFieldAliasMap() {
		return fieldAliasMap;
	}
}