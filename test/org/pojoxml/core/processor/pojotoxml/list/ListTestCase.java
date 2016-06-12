package org.pojoxml.core.processor.pojotoxml.list;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
 
import junit.framework.TestCase;

import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;
import org.pojoxml.test.util.Path;
import org.pojoxml.test.util.XmlFiletReaderUtil;

public class ListTestCase  extends TestCase{
	String pth =Path.ROOT+"list\\";
	
	public void testAllWrappers(){
		SimpleList list = new SimpleList();
		LinkedList linked = new LinkedList();
		linked.add(new Byte("10"));
		linked.add(new Short("11"));
		linked.add(new Integer("12"));
		linked.add(new Long("1234567890"));
		linked.add(new Float("11.23f"));
		linked.add(new Character('F'));
		linked.add(new Double("1112.68"));
		linked.add(new Boolean("true"));
		linked.add(new String("rk"));
		linked.add(new BigDecimal("12.12"));
		linked.add(new BigInteger("33"));
		list.setLinked(linked);
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(list, pth+"ListWithAllWrapperTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithAllWrapper.xml"), XmlFiletReaderUtil.readFile(pth+"ListWithAllWrapperTO.xml"));
	}
	public void testAllWrappersBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		SimpleList list = (SimpleList) pojoXml.getPojoFromFile(pth+"ListWithAllWrapperTO.xml", SimpleList.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithAllWrapper.xml"),pojoXml.getXml(list));
	}
	public void testAllWrappersWithOneNull(){
		SimpleList list = new SimpleList();
		LinkedList linked = new LinkedList();
		linked.add(null);
		linked.add(new Short("11"));
		linked.add(new Integer("12"));
		linked.add(new Long("1234567890"));
		linked.add(new Float("11.23f"));
		linked.add(new Character('F'));
		linked.add(new Double("1112.68"));
		linked.add(new Boolean("true"));
		linked.add(new String("rk"));
		linked.add(new BigDecimal("12.12"));
		linked.add(null);
		list.setLinked(linked);
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(list, pth+"ListWithAllWrapperOneNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithAllWrapperOneNull.xml"), XmlFiletReaderUtil.readFile(pth+"ListWithAllWrapperOneNullTO.xml"));
	}
	public void testAllWrappersWithOneNullBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		SimpleList list = (SimpleList) pojoXml.getPojoFromFile(pth+"ListWithAllWrapperOneNullTO.xml", SimpleList.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithAllWrapperOneNull.xml"),pojoXml.getXml(list));
	}
	public void testAllWrappersWithAllNull(){
		SimpleList list = new SimpleList();
		LinkedList linked = new LinkedList();
		linked.add(null);
		linked.add(null);
		linked.add(null);
		list.setLinked(linked);
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(list, pth+"ListWithAllWrapperAllNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithAllWrapperAllNull.xml"), XmlFiletReaderUtil.readFile(pth+"ListWithAllWrapperAllNullTO.xml"));
	}
	public void testAllWrappersWithAllNullBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		SimpleList list = (SimpleList) pojoXml.getPojoFromFile(pth+"ListWithAllWrapperAllNullTO.xml", SimpleList.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithAllWrapperAllNull.xml"),pojoXml.getXml(list));
	}
	
	public void testListWithInnerObject(){
		ListInnerObj innObj = new ListInnerObj();
		innObj.setName("xxx");
		ArrayList list = new ArrayList();
		list.add(getInner());
		innObj.setList(list);
		innObj.setSss(11);
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(innObj, pth+"ListWithInnerObjectTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithInnerObject.xml"), XmlFiletReaderUtil.readFile(pth+"ListWithInnerObjectTO.xml"));

	}
	
	public void testListWithInnerObjectBack(){
		 
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addCollectionClass( "ListInn", ListInn.class);
		ListInnerObj obj = (ListInnerObj) pojoXml.getPojoFromFile(pth+"ListWithInnerObjectTO.xml", ListInnerObj.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithInnerObject.xml"),pojoXml.getXml(obj));

	}
	
	//multiple is also handled here
	public void testListWithInnerObjectNull(){
		ListInnerObj innObj = new ListInnerObj();
		innObj.setName("xxx");
		ArrayList list = new ArrayList();
		list.add(null);
		list.add(getInner());
		list.add(getInner());
		list.add(null);
		innObj.setList(list);
		innObj.setSss(11);
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(innObj, pth+"ListWithInnerObjectNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithInnerObjectNull.xml"), XmlFiletReaderUtil.readFile(pth+"ListWithInnerObjectNullTO.xml"));

	}
	
	public void testListWithInnerObjectNullBack(){
		 
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addCollectionClass( "ListInn", ListInn.class);
		ListInnerObj obj = (ListInnerObj) pojoXml.getPojoFromFile(pth+"ListWithInnerObjectNullTO.xml", ListInnerObj.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithInnerObjectNull.xml"),pojoXml.getXml(obj));

	}
	
	public void testListWithCDATAInnerObject (){
		ListInnerObj innObj = new ListInnerObj();
		innObj.setName("xxx");
		ArrayList list = new ArrayList();
	 	list.add(getInner());
		list.add(getInner());
	 	innObj.setList(list);
		innObj.setSss(11);
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.enableCDATA(true);
		pojoXml.saveXml(innObj, pth+"ListWithInnerObjectCDATATO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithInnerObjectCDATA.xml"), XmlFiletReaderUtil.readFile(pth+"ListWithInnerObjectCDATATO.xml"));

	}
	
	public void testListWithCDATAInnerObjectBack(){
		 
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.enableCDATA(true);
		pojoXml.addCollectionClass( "ListInn", ListInn.class);
		ListInnerObj obj = (ListInnerObj) pojoXml.getPojoFromFile(pth+"ListWithInnerObjectCDATATO.xml", ListInnerObj.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithInnerObjectCDATA.xml"),pojoXml.getXml(obj));

	}
	
	public void testListWithCDATAInnerObjectAlias (){
		ListInnerObj innObj = new ListInnerObj();
		innObj.setName("xxx");
		ArrayList list = new ArrayList();
	 	list.add(getInner());
		list.add(getInner());
	 	innObj.setList(list);
		innObj.setSss(11);
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addClassAlias(ListInn.class, "listInput");
		pojoXml.enableCDATA(true);
		pojoXml.saveXml(innObj, pth+"ListWithInnerObjectCDATAAliasTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithInnerObjectCDATAAlias.xml"), XmlFiletReaderUtil.readFile(pth+"ListWithInnerObjectCDATAAliasTO.xml"));

	}
	
	public void testListWithCDATAInnerObjectAliasBack(){
		 
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addClassAlias(ListInn.class, "listInput");
		pojoXml.enableCDATA(true);
		pojoXml.addCollectionClass( "listInput", ListInn.class);
		ListInnerObj obj = (ListInnerObj) pojoXml.getPojoFromFile(pth+"ListWithInnerObjectCDATAAliasTO.xml", ListInnerObj.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ListWithInnerObjectCDATAAlias.xml"),pojoXml.getXml(obj));

	}
	
	public ListInn getInner(){
		ListInn obj = new ListInn();
		obj.setAddress("Mumbai");
		obj.setSalary(55000.45f);
		return obj;
	}
}
