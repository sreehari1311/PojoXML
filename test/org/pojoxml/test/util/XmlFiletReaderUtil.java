package org.pojoxml.test.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
 
import java.io.InputStream;

public class XmlFiletReaderUtil {
	public static String readXml(String xmlFile) {
		StringBuffer xml = new StringBuffer();
		try {
			InputStream ins = ClassLoader.getSystemClassLoader()
					.getResourceAsStream(xmlFile);
			BufferedInputStream buffer = new BufferedInputStream(ins);
			int i = 0;
			while ((i = buffer.read()) != -1) {
				xml.append( i);
			}
			ins.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return xml.toString();
	}
	
	public static String readFile(String xmlFile){
		StringBuffer fileContent = new StringBuffer();
		try{
			FileReader reader = new FileReader(new File(xmlFile));
			BufferedReader buffer = new  BufferedReader(reader);
			String line = "";
			int i=0;
			while((line=buffer.readLine())!=null){
				if(i!=0)
					fileContent.append("\n");
				i++;
				fileContent.append(line);
			}
			reader.close();
		}catch(Exception ex){
			
		}
		return fileContent.toString();
	}
}
