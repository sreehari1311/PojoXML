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

package org.pojoxml.core.processor;

import org.pojoxml.core.PojoXmlInitInfo;
import org.pojoxml.core.processor.pojotoxml.ClassToXmlProcessor;
import org.pojoxml.util.XmlConstant;
import org.pojoxml.util.XmlUtil;
import org.pojoxml.util.XmlWriter;

/**
 * Main class initializing the POJO to xml conversion
 * @author SreeHari
 * @since 1.0
 */
public class PojoToXml {
	
	private PojoXmlInitInfo initInfo;
	/**
	 * writer object for writing generated xml to  a file.
	 */
	private XmlWriter writer;

	/**
	 * file writer Indicator
	 */
	private boolean fileWriter = false;

	/**
	 * For temporarily holding the xml content. 
	 */
	private StringBuffer xmlContent;
	
	/**
	 * character encoding format.
	 */
	private String encoding;
	
	/**
	 * indicator for CDATA enabling
	 */
	private boolean cDataEnabled;
	
	/**
	 * constructor
	 * @param pojoInitInfo initialization object
	 * @see PojoXmlInitInfo
	 */
	public PojoToXml(PojoXmlInitInfo pojoInitInfo){
		initInfo = pojoInitInfo;
	}
	
	/**
	 * For generating xml from given pojo
	 * @param object POJO 
	 * @return xml string
	 */
	public String getXml(Object object) {
		ClassToXmlProcessor processor = new ClassToXmlProcessor(XmlConstant.NO_SPACE,initInfo);
	 	processor.setCdataEnabled(cDataEnabled);
		writeXmlContent(processor.process(object));
		return xmlContent.toString();
	}

	/**
	 * Saving the generated xml file into the given path.
	 * @param object POJO object for generating xml
	 * @param fileName full path for saving xml content
	 */
	public void saveXml(Object object, String fileName) {
		ClassToXmlProcessor processor = new ClassToXmlProcessor(XmlConstant.NO_SPACE,initInfo);
		 processor.setCdataEnabled(cDataEnabled);
		fileWriter = true;
		writer = new XmlWriter(fileName);
		writeXmlContent(processor.process(object));
		this.close();
	}
	
	/**
	 * method for closing the file writer.  
	 */
	private void close() {
		writer.close();
	}

	/**
	 * writing xml content into a <code>StringBuffer</code>.
	 * @param xml
	 */
	public void writeXmlContent(String xml) {
		if(xmlContent == null){
			xmlContent = new StringBuffer(XmlConstant.HEAD_OPEN+XmlUtil.getEncoding(encoding)+XmlConstant.HEAD_CLOSE);
			xmlContent.append(XmlConstant.NL);
		}
		xmlContent.append(xml);
		if (fileWriter) {
			writer.write(xmlContent.toString());
			return;
		}
	}
    /**
     * setter method for character encoding
     * @param encoding
     */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
    /**
     * getter method for character encoding
     * @return String
     */
	public String getEncoding() {
		return encoding;
	}
 
	/**
	 * method for enabling CDATA section
	 * @param cDataEnabled
	 */
	public void setCDataEnabled(boolean cDataEnabled) {
		this.cDataEnabled = cDataEnabled;
	}

	/**
	 * getter method for CDATA
	 * @ return boolean
	 */
	public boolean isCDataEnabled() {
		return cDataEnabled;
	}

	/**
	 * setter method for initializing info
	 * @param initInfo initializing object
	 * @see PojoXmlInitInfo
	 */
	public void setInitInfo(PojoXmlInitInfo initInfo) {
		this.initInfo = initInfo;
	}
	
	/**
	 * getter method for init info.
	 * @return initInfo
	 */
	public PojoXmlInitInfo getInitInfo() {
		return initInfo;
	}
 
}
