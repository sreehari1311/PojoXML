package org.pojoxml.core.processor.pojotoxml.set;

 
import java.util.LinkedHashSet;

import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;
import org.pojoxml.core.processor.pojotoxml.list.ListInn;
 import org.pojoxml.test.util.Path;
import org.pojoxml.test.util.XmlFiletReaderUtil;

import junit.framework.TestCase;

public class SetTestCase extends TestCase{
	String pth =Path.ROOT+"set\\";
	
	public void testSetTestCaseForAll(){
		SetTst innObj = new SetTst();
		innObj.setName("xxx");
		LinkedHashSet list = new LinkedHashSet();
		list.add(getInner());
		innObj.setLinkedSet( list);
		innObj.setSss(11);
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(innObj, pth+"SetWithInnerObjectTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"SetWithInnerObject.xml"), XmlFiletReaderUtil.readFile(pth+"SetWithInnerObjectTO.xml"));

	}
	
	public void testSetTestCaseForAllBack(){
		 
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addCollectionClass( "ListInn", ListInn.class);
		SetTst obj = (SetTst) pojoXml.getPojoFromFile(pth+"SetWithInnerObject.xml", SetTst.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"SetWithInnerObject.xml"),pojoXml.getXml(obj));

	}
	
	public ListInn getInner(){
		ListInn obj = new ListInn();
		obj.setAddress("Mumbai");
		obj.setSalary(55000.45f);
		return obj;
	}
}
