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

package org.pojoxml.core.processor.pojotoxml;

import org.pojoxml.core.PojoXmlInitInfo;
import org.pojoxml.util.XmlConstant;
import org.pojoxml.util.XmlUtil;

/**
 * This is a abstract class.
 * serves as super class for the ClassToXmlProcessor,CollectionToXmlProcessor,ArrayToXmlProcessor.
 * Its contains the common methods for generating the xml file.
 * 
 * @author SreeHari
 * @since 1.0
 *
 */
abstract class XmlProcessor {
 
	
	/**
	 * initialization info
	 */
	private PojoXmlInitInfo pojoXmlInitInfo;
	
	/**
	 * class name for alias processing
	 */
	private String className;
	/**
	 * For holding the generated xml
	 */
	private StringBuffer xmlContent;
	
	/**
	 * current elemenentName. 
	 */
	private String elementName;

	/**
	 * holds the complete attributes name and values  
	 */
 	private String attributes = "";
 	
	/**
	 * for generating the pretty xml this holds the space indentation 
	 */
	private int space;

	/**
	 * holds  whether attributes contains in the given object
	 */
	private boolean attribute = false;
	
	/**
	 * CDATA indicator
	 */
	private boolean cdataEnabled = false;
	
	/**
	 * For CDATA helper
	 */
	private boolean stringFound = false;
	
	/**
	 * 
	 * @return boolean
	 */
	public boolean isCdataEnabled() {
		return cdataEnabled;
	}

	/**
	 * 
	 * @param cdataEnabled
	 */
	public void setCdataEnabled(boolean cdataEnabled) {
		this.cdataEnabled = cdataEnabled;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isStringFound() {
		return stringFound;
	}

	/**
	 * 
	 * @param stringFound
	 */
	public void setStringFound(boolean stringFound) {
		this.stringFound = stringFound;
	}

	/**
	 * Default constructor for initializing the xmlContent  
	 */
	public XmlProcessor(){
		xmlContent = new StringBuffer();
	}
 	
	/**
	 * Constructor for initializing the instance variables
	 *
	 * @param elementName for holding the name of current element
	 * @param space for holding xml content indentation space
	 * @param pojoXmlInitInfo initialization info
	 */
 	public XmlProcessor (String elementName,int space,PojoXmlInitInfo pojoXmlInitInfo){
		this(elementName,XmlConstant.NOTHING,space,pojoXmlInitInfo);
	}
 	
 	/**
	 * Constructor for initializing the instance variables
	 *
	 * @param elementName for holding the name of current element
	 * @param attrib attribute name
	 * @param space for holding xml content indentation space
	 * @param pojoXmlInitInfo initialization info
	 */
 	public XmlProcessor (String elementName,String attrib,int space,PojoXmlInitInfo pojoXmlInitInfo){
 		this.setPojoXmlInitInfo(pojoXmlInitInfo);
		this.elementName = elementName;
		this.attributes = attrib;
		this.space = space;
		xmlContent = new StringBuffer();
	}
 	 
 	/**
	 * This method will check the given element contains value or not.
	 * if its empty it will generates the empty element.
	 * 
	 * @param object holds value object for checking
	 * @param elementName xml element name.
	 * @return true if its empty/false not empty.
	 */
	public boolean isEmptyElement(Object object,String elementName){
		if(object == null) {
			writeXmlContent( createEmptyElementWithNL(elementName,attributes,getSpace()));
			return true;
		}
		return false;
	}
	/**
	 * For processing the collection array. 
	 * @param collectionEelement collection element presented in the array.
	 */
	public void processCollection(Object collectionEelement){
		CollectionToXmlProcessor collectionToXmlProcessor = new CollectionToXmlProcessor(getSpace());
		collectionToXmlProcessor.setCdataEnabled(isCdataEnabled());
	 	writeXmlContent(collectionToXmlProcessor.process(collectionEelement));
 	}
		
	/**
	 * For processing object presented in the array.
	 * @param object Pojo Object presented in the given array.
	 */
	public void processObject(Object object){
		ClassToXmlProcessor classToXmlProcessor = new ClassToXmlProcessor(getSpace(),getPojoXmlInitInfo());
		classToXmlProcessor.setCdataEnabled(isCdataEnabled());
		writeXmlContent(classToXmlProcessor.process(object));
	}
	
	/**
	 * For processing object presented in the array.
	 * @param object Pojo Object presented in the given array.
	 */
	public void processObject(Object object,String elementName){
		System.out.println("ELM: "+elementName+" "+getAliasName(elementName));
		ClassToXmlProcessor classToXmlProcessor = new ClassToXmlProcessor(getSpace(),getAliasName(elementName),getPojoXmlInitInfo());
		classToXmlProcessor.setCdataEnabled(isCdataEnabled());
		writeXmlContent(classToXmlProcessor.process(object));
	}
	/**
	 * For processing object presented in the array.
	 * @param object Pojo Object presented in the given array.
	 */
	public void processObject(Object otherObj,String classElementName,String attrib){
	 	ClassToXmlProcessor processor = new ClassToXmlProcessor(getAliasName(classElementName),attrib,getSpace(),getPojoXmlInitInfo());
		processor.setAttributes(attrib);
		processor.setCdataEnabled(isCdataEnabled());
		writeXmlContent(processor.process(otherObj));
	}	

	/**
	 * This method will check the given element contains value or not.
	 * if its empty it will generates the empty element.Its overloaded method with attribute
	 * Name.
	 * 
	 * @param object holds value object for checking
	 * @param elementName xml element name.
	 * @param attrib contains the attribute name and value.
	 * @return true if its empty/false not empty.
	 */
	public boolean isEmptyElement(Object object,String elementName,String attrib){
		if(object == null) {
			writeXmlContent( createEmptyElementWithNL(elementName,attrib,getSpace()));
			return true;
		}
		return false;
	}
	

	/**
	 * this method will write the given xml content to StringBuffer
	 * 
	 * @param xml holds the xml elements.
	 */
	public void writeXmlContent(String xml) {
		if(xml!=null)
		xmlContent.append(xml);
	}
	
	/**
	 * returns the actual xml content 
	 * @return actual xml content
	 */
	public String getXmlContent() {
		return xmlContent.toString();
	}
	
	/**
	 * For setting xml content.
	 * @param xmlContent 
	 */
	public void setXmlContent(StringBuffer xmlContent) {
		this.xmlContent = xmlContent;
	}
	
	
	/**
	 * Overridden method for getting the xml content
	 * @param string
	 */
	public String toString(){
		return xmlContent.toString();
	}
	
 	/**
	 * For incrementing one tab indentation space
	 */
	public void incrementSpace(){
		space++;
	}
	
	/**
	 * For decrementing one tab indentation space
	 */
	public void decrementSpace(){
		space --;
	}
	
	/**
	 * Abstract method for implementing the reading process
	 * @return abstract
	 */
	public abstract String process(Object object);
	
	/**
	 * For getting space
	 * @return int
	 */
	public int getSpace() {
		return space;
	}
	
	/**
	 * For setting space.
	 * @param space
	 */
	public void setSpace(int space) {
		this.space = space;
	}
	/**
	 * For getting element name.
	 * @return string
	 */
	public String getElementName() {
		return elementName;
	}
	
	/**
	 * For setting element name
	 * @param elementName
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
	/**
	 * For setting attribute value
	 * @param attribute
	 */
	public void setAttribute(boolean attribute) {
		this.attribute = attribute;
	}
	
	/**
	 * For getting attribute value.
	 * @return boolean
	 */
	public boolean isAttribute() {
		return attribute;
	}


	/**
	 * For setting attributes
	 * @param attributes
	 */
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	/**
	 * For getting attributes.
	 * @return String
	 */
	public String getAttributes() {
		return attributes;
	}
	
	/**
	 * setter method
	 * @param pojoXmlInitInfo
	 */
	public void setPojoXmlInitInfo(PojoXmlInitInfo pojoXmlInitInfo) {
		this.pojoXmlInitInfo = pojoXmlInitInfo;
	}

	/**
	 * getter method
	 * @return pojoXmlInitInfo
	 */
	public PojoXmlInitInfo getPojoXmlInitInfo() {
		return pojoXmlInitInfo;
	}
	
	/**
	 * method for getting alias name
	 * @param fieldName actual field name
	 * @return alias name
	 */
	public String getAliasName(String fieldName){
	 	Object aliasName =pojoXmlInitInfo.getAliasName(getClassName()+"."+fieldName) ;
		if(aliasName == null){
			return fieldName;
		}else
		{
			return aliasName.toString();
		}
	}
	/**
	 * getting alias name for the class
	 * @param className class name
	 * @return alias name for the class
	 */
	public String getClassAliasName(String className){
	 	Object aliasName =pojoXmlInitInfo.getAliasName(className) ;
		if(aliasName == null){
		 return null;
		}else{
			return aliasName.toString();
		}
	}
	/**
	 * xml tag generation method
	 * @param elementName element name
	 * @param elementAttributes attributes
	 * @param elementValue element value
	 * @param spac space space for adding space
	 * @return generated tag. 
	 */
	protected String getElementWithNL(String elementName,String elementAttributes ,String elementValue,final int spac ){
		
		return XmlUtil.getElementWithNL(getAliasName(elementName),elementAttributes ,elementValue,spac);
	}
	/**
	 * 
	 * @param rootName
	 * @param attributes
	 * @param space
	 * @return String
	 */
	protected String getStartTagWithNL(String rootName,String attributes,int space){
		 
		return XmlUtil.getStartTagWithNL(getAliasName(rootName), attributes, space);
	}
	/**
	 * 
	 * @param rootName
	 * @param space
	 * @return String
	 */
	protected String getCloseTag(String rootName, int space){
		return XmlUtil.getCloseTag(getAliasName(rootName), space);
	}
	/**
	 * 
	 * @param rootName
	 * @param space
	 * @return String
	 */
	protected String getCloseTagWithNL(String rootName, int space){
		return XmlUtil.getCloseTagWithNL(getAliasName(rootName), space);
	}
	
	/**
	 * 
	 * @param elementName
	 * @param attributes
	 * @param space
	 * @return String
	 */
	protected String createEmptyElementWithNL(String elementName,String attributes,int space){
		return XmlUtil.createEmptyElementWithNL(getAliasName(elementName), attributes,space);
	}
	/**
	 * 
	 * @param elementName
	 * @param attribute
	 * @param space
	 * @return String
	 */
	protected String getStartTag(String elementName,String attribute,int space){
		return XmlUtil.getStartTag(getAliasName(elementName),attribute,space);
	}
	/**
	 * 
	 * @param className
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 
	 * @return String
	 */
	public String getClassName() {
		return className;
	}
 }
