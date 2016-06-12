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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Sreehari
 * @since 1.0
 *
 */
public class XmlWriter {

	/**
	 * 
	 */
	private String fileName;
	
	/**
	 * 
	 */
	private File file;
	
	/**
	 * 
	 */
	private FileWriter writer;
	
	/**
	 * 
	 */
	private BufferedWriter buffer;
	
	/**
	 * 
	 * @param fileName
	 */
	public XmlWriter(String fileName){
		this.fileName = fileName;
		init();
	}
	
	/**
	 * 
	 */
	private void init(){
		file = new File(fileName);
		try {
			writer = new FileWriter(file);
			buffer = new BufferedWriter(writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param content
	 */
	public void write(String content){
		try {
			writer.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void close(){
		Assert.isNull(writer, "Intialise before closing");
		try{
		buffer.flush();
		writer.close();
		fileName = null;
		file = null;
		writer = null;
		buffer = null;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
