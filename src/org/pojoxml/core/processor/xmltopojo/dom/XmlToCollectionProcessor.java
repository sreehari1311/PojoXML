package org.pojoxml.core.processor.xmltopojo.dom;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;

import org.pojoxml.core.PojoXmlException;
import org.pojoxml.util.ClassUtil;

 
  
public class XmlToCollectionProcessor {

	 private Object collectionData;
	 private String setterName;
	 private Class collectionClass;
	 
	 public Object getCollectionData() {
		return collectionData;
	}

	public void setCollectionData(Object collectionData) {
		this.collectionData = collectionData;
	}

	public XmlToCollectionProcessor(String setterName,Class valueClass){
	 	 try {
	 		 this.setterName = setterName;
	 		 this.collectionClass = valueClass;
	 		Class listClass = Class.forName("java.util.List");
			Class setClass = Class.forName("java.util.Set");
		 	if(valueClass == listClass){
				collectionData = (java.util.List)new ArrayList();
			}else if(valueClass == setClass){
				collectionData = (java.util.Set) new HashSet();
			}
			else if(listClass.isInstance(valueClass.newInstance()) || setClass.isInstance(valueClass.newInstance()) ){
	 			collectionData = valueClass.newInstance();
	 		 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public void addCollection(Object value) throws PojoXmlException{
		 
		 if(value == null){
			 try{
					
					Method method = collectionData.getClass().getMethod("add", new Class[]{Object.class});
					method.invoke(collectionData, new Object[]{null});
				}catch(Exception ex){
					ex.printStackTrace();
				}
			 return;
		 }
		 Constructor construtor = null;
		 Class clas = value.getClass();
			Object setObject = null;
			Class wrapperClass = ClassUtil.getPrimitiveClass(clas);
			if(wrapperClass == null)
				wrapperClass = clas;
			if(clas.equals(char.class))
				setObject = new Character(value.toString().charAt(0));
			else if(ClassUtil.isPrimitiveOrWrapper(clas)){
			try {
				construtor = wrapperClass.getConstructor(new Class[]{String.class});
				setObject = construtor.newInstance(new String[]{value.toString()});
				
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else
			{
				setObject = value;
			}
			
			try{
				
				Method method = collectionData.getClass().getMethod("add", new Class[]{Object.class});
				method.invoke(collectionData, new Object[]{setObject});
			}catch(Exception ex){
				ex.printStackTrace();
			}
	 }

	public void setSetterName(String setterName) {
		this.setterName = setterName;
	}

	public String getSetterName() {
		return setterName;
	}

	public void setCollectionClass(Class collectionClass) {
		this.collectionClass = collectionClass;
	}

	public Class getCollectionClass() {
		return collectionClass;
	}
	
}
