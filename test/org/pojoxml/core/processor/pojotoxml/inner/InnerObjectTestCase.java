package org.pojoxml.core.processor.pojotoxml.inner;

import junit.framework.TestCase;

import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;
import org.pojoxml.test.util.Path;
import org.pojoxml.test.util.XmlFiletReaderUtil;

public class InnerObjectTestCase extends TestCase {

	String pth =Path.ROOT+"innerObject\\";
	
	public void testInnerObject(){
		Obj obj = new Obj();
		obj.setPlace("Kerala");
		InnerObj innObj = new InnerObj();
		innObj.setAge(28);
		innObj.setName("hhh");
		obj.setInnObj(innObj);
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(obj, pth+"innerObjectTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"innerObject.xml"), XmlFiletReaderUtil.readFile(pth+"innerObjectTO.xml"));
	}
	public void testInnerObjectBack(){
		 
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Obj obj = (Obj)pojoXml.getPojoFromFile(pth+"innerObjectTO.xml", Obj.class);
		assertEquals(pojoXml.getXml(obj), XmlFiletReaderUtil.readFile(pth+"innerObject.xml"));
	}
	
	public void testInnerObjectWithNull(){
		Obj obj = new Obj();
		obj.setPlace("Kerala");
	 	PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(obj, pth+"innerObjectNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"innerObjectNull.xml"), XmlFiletReaderUtil.readFile(pth+"innerObjectNullTO.xml"));
	}
	public void testInnerObjectBackWithNull(){
		 
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Obj obj = (Obj)pojoXml.getPojoFromFile(pth+"innerObjectNullTO.xml", Obj.class);
		assertEquals(pojoXml.getXml(obj), XmlFiletReaderUtil.readFile(pth+"innerObjectNull.xml"));
	}
	public void testInnerObjectWithAlias(){
		Obj obj = new Obj();
		obj.setPlace("Kerala");
		InnerObj innObj = new InnerObj();
		innObj.setAge(28);
		innObj.setName("hhh");
		obj.setInnObj(innObj);
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addFieldAlias(Obj.class, "innObj", "INNEROBJECT");
		pojoXml.addFieldAlias(InnerObj.class,"name","NAM");
		pojoXml.saveXml(obj, pth+"innerObjectWithAliasTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"innerObjectWithAlias.xml"), XmlFiletReaderUtil.readFile(pth+"innerObjectWithAliasTO.xml"));
	}
	public void testInnerObjectWithAliasBack(){
		 
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		 pojoXml.addFieldAlias(Obj.class, "innObj", "INNEROBJECT");
		 pojoXml.addFieldAlias(InnerObj.class,"name","NAM");
		Obj obj = (Obj)pojoXml.getPojoFromFile(pth+"innerObjectWithAliasTO.xml", Obj.class);
		assertEquals(pojoXml.getXml(obj), XmlFiletReaderUtil.readFile(pth+"innerObjectWithAlias.xml"));
	}
	public void testInnerObjectWithAliasMain(){
		Obj obj = new Obj();
		obj.setPlace("Kerala");
		InnerObj innObj = new InnerObj();
		innObj.setAge(28);
		innObj.setName("hhh");
		obj.setInnObj(innObj);
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addClassAlias( Obj.class,   "INNEROBJECT");
		 pojoXml.saveXml(obj, pth+"innerObjectWithAliasMainTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"innerObjectWithAliasMain.xml"), XmlFiletReaderUtil.readFile(pth+"innerObjectWithAliasMainTO.xml"));
	}
	public void testInnerObjectWithAliasMainBack(){
		 
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addClassAlias( Obj.class,   "INNEROBJECT");
	 	Obj obj = (Obj)pojoXml.getPojoFromFile(pth+"innerObjectWithAliasMainTO.xml", Obj.class);
		assertEquals(pojoXml.getXml(obj), XmlFiletReaderUtil.readFile(pth+"innerObjectWithAliasMain.xml"));
	}
	
	/*public void testInnerObjectNullWithAliasMain(){
		Obj obj =null;
		 PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addClassAlias( Obj.class,   "INNEROBJECT");
		 pojoXml.saveXml(obj, pth+"innerObjectWithAliasMainNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"innerObjectWithAliasMainNull.xml"), XmlFiletReaderUtil.readFile(pth+"innerObjectWithAliasMainNullTO.xml"));
	}
	public void testInnerObjectNullWithAliasMainBack(){
		 
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addClassAlias( Obj.class,   "INNEROBJECT");
	 	Obj obj = (Obj)pojoXml.getPojoFromFile(pth+"innerObjectWithAliasMainNullTO.xml", Obj.class);
		assertEquals(pojoXml.getXml(obj), XmlFiletReaderUtil.readFile(pth+"innerObjectWithAliasMainNull.xml"));
	}*/
}
