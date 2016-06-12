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

import java.util.List;

import org.pojoxml.core.PojoXmlInitInfo;
import org.pojoxml.util.MapUtils;
import org.pojoxml.util.StringUtil;
import org.w3c.dom.Node;

/**
 * This class is for the super class for the dom processing.
 * Contains common functions for the Xml To POJO conversion
 * 
 * @author SreeHari
 * @since 1.0
 */
abstract class ObjectProcessor {
	
	/**
	 * initialization inof
	 */
	private PojoXmlInitInfo initInfo;
	/**
	 *  root node
	 */
	Node rootNode;
	
	/**
	 * constructor
	 * @param initInfo initialization info
	 */
	public ObjectProcessor(PojoXmlInitInfo initInfo){
		this.initInfo = initInfo;
		this.rootNode = initInfo.getRootNode();
	}
	
	/**
	 * getting actual class name from alias name
	 * @param tagName tag name
	 * @return actual variable or clsass name
	 */
	protected String getActualValueFromAleasName(String tagName){
		if(initInfo.getFieldAliasMap() == null)
			return tagName;
		
		List matchedValueList =MapUtils.getKeysFromValue(initInfo.getFieldAliasMap(), tagName);
		if(matchedValueList!=null && matchedValueList.size() ==1){
			String actualTag = matchedValueList.iterator().next().toString();
			int intex = actualTag.lastIndexOf('.');
			return actualTag.substring(intex+1);
		}else{
			return tagName;
		}
		
	}
 	/**
	 * For getting corresponding getter method for the given element.
	 * @param child child node
	 * @return getter method name
	 */
	public String getElementName(Node child){
	 	return StringUtil.initCap(getActualValueFromAleasName(child.getNodeName()));
	}
	/**
	 * 
	 * @param initInfo
	 */
	public void setInitInfo(PojoXmlInitInfo initInfo) {
		this.initInfo = initInfo;
	}
	
	/**
	 * 
	 * @return PojoXmlInitInfo
	 */
	public PojoXmlInitInfo getInitInfo() {
		return initInfo;
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
}
