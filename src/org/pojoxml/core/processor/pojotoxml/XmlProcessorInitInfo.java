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

import java.util.Map;

/**
 * This class is used for holding the data while initializing the POJO to xml process.
 * @author Sreehari
 * @since 1.0
 */
class XmlProcessorInitInfo {

	
 	/**
	 * Hold all element names
	 */
	public String[] elements;
	
	/**
	 * Hold all attribute name and value.
	 */
	private Map attributes;
	
	/**
	 * Indicator for attribute.
	 */
	private boolean attribute = false;
	
	/**
	 * For getting elements
	 * @return String
	 */
	public String[] getElements() {
		return elements;
	}
	
	/**
	 * For setting elements
	 * @param elements
	 */
	public void setElements(String[] elements) {
		this.elements = elements;
	}
	
	/**
	 * For getting attributes.
	 * @return Map
	 */
	public Map getAttributes() {
		return attributes;
	}
	
	/**
	 * For setting attributes.
	 * @param attributes
	 */
	public void setAttributes(Map attributes) {
		this.attributes = attributes;
	}
	
	/**
	 * For getting attribute
	 * @return boolean
	 */
	public boolean isAttribute() {
		return attribute;
	}
	
	/**
	 * For setting attribute
	 * @param attribute
	 */
	public void setAttribute(boolean attribute) {
		this.attribute = attribute;
	}
 
}
