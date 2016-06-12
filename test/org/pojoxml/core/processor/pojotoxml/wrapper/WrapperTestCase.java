package org.pojoxml.core.processor.pojotoxml.wrapper;

import java.math.BigInteger;

import junit.framework.TestCase;

import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;
 
import org.pojoxml.test.util.Path;
import org.pojoxml.test.util.XmlFiletReaderUtil;

public class WrapperTestCase extends TestCase {
	String pth =Path.ROOT+"wrappers\\";
	
	public void testAllWrapper(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(getWrapper(), pth+"AllWrappersTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappers.xml"), XmlFiletReaderUtil.readFile(pth+"AllWrappersTO.xml"));
 	}
	public void testAllWrapperBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Wrappers wrap = (Wrappers)pojoXml.getPojoFromFile(pth+"AllWrappersTO.xml", Wrappers.class);
		assertEquals(pojoXml.getXml(wrap), XmlFiletReaderUtil.readFile(pth+"AllWrappers.xml"));
 	}
	public void testWithOneNull (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Wrappers wrap = getWrapper();
		wrap.setDoubleValue(null);
		pojoXml.saveXml(wrap, pth+"AllWrappersOneNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersOneNull.xml"), XmlFiletReaderUtil.readFile(pth+"AllWrappersOneNullTO.xml"));
 	}
	public void testWithOneNullBack (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Wrappers wrap = (Wrappers)pojoXml.getPojoFromFile(pth+"AllWrappersOneNullTO.xml", Wrappers.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersOneNull.xml"), pojoXml.getXml(wrap));
 	}
	public void testWithMultiNull (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Wrappers wrap = getWrapper();
		wrap.setIntValue(null);
		wrap.setDoubleValue(null);
		pojoXml.saveXml(wrap, pth+"AllWrappersMultiNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersMultiNull.xml"), XmlFiletReaderUtil.readFile(pth+"AllWrappersMultiNullTO.xml"));
 	}
	public void testWithMultiNullBack (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Wrappers wrap = (Wrappers)pojoXml.getPojoFromFile(pth+"AllWrappersMultiNullTO.xml", Wrappers.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersMultiNull.xml"), pojoXml.getXml(wrap));
 	}
	public void testWithAllNull (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Wrappers wrap = new Wrappers();
	 	pojoXml.saveXml(wrap, pth+"AllWrappersAllNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersAllNull.xml"), XmlFiletReaderUtil.readFile(pth+"AllWrappersAllNullTO.xml"));
 	}
	public void testWithAllNullBack (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Wrappers wrap = (Wrappers)pojoXml.getPojoFromFile(pth+"AllWrappersAllNullTO.xml", Wrappers.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersAllNull.xml"), pojoXml.getXml(wrap));
 	}
	
	public void testWrappersWithSomeAlias (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Wrappers wrap = getWrapper();
		pojoXml.addFieldAlias(Wrappers.class,"shortValue", "short");
		pojoXml.addFieldAlias(Wrappers.class,"intValue", "int");
 		pojoXml.addFieldAlias(Wrappers.class,"longValue", "long");
 		pojoXml.addFieldAlias(Wrappers.class,"floatValue", "float");
		 pojoXml.addFieldAlias(Wrappers.class,"doubleValue", "double");
		pojoXml.saveXml(wrap, pth+"AllWrappersSomeAliasTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersSomeAlias.xml"), XmlFiletReaderUtil.readFile(pth+"AllWrappersSomeAliasTO.xml"));
 	}
	public void testWrappersWithSomeAliasBack (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		 
		pojoXml.addFieldAlias(Wrappers.class,"shortValue", "short");
		pojoXml.addFieldAlias(Wrappers.class,"intValue", "int");
 		pojoXml.addFieldAlias(Wrappers.class,"longValue", "long");
 		pojoXml.addFieldAlias(Wrappers.class,"floatValue", "float");
		 pojoXml.addFieldAlias(Wrappers.class,"doubleValue", "double");
		Wrappers wrap = (Wrappers) pojoXml.getPojoFromFile(pth+"AllWrappersSomeAliasTO.xml", Wrappers.class);
		 
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersSomeAlias.xml"),pojoXml.getXml(wrap));
 	}
	public void testWrappersWithAllAlias (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Wrappers wrap = getWrapper();
		pojoXml.addClassAlias(Wrappers.class, "wrapper");
		pojoXml.addFieldAlias(Wrappers.class,"byteValue", "byte");
		pojoXml.addFieldAlias(Wrappers.class,"shortValue", "short");
		pojoXml.addFieldAlias(Wrappers.class,"intValue", "int");
		pojoXml.addFieldAlias(Wrappers.class,"charValue", "char");
		pojoXml.addFieldAlias(Wrappers.class,"longValue", "long");
		pojoXml.addFieldAlias(Wrappers.class,"booleanValue", "boolean");
		pojoXml.addFieldAlias(Wrappers.class,"floatValue", "float");
		pojoXml.addFieldAlias(Wrappers.class,"doubleValue", "double");
		pojoXml.saveXml(wrap, pth+"AllWrappersAllAliasTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersAllAlias.xml"), XmlFiletReaderUtil.readFile(pth+"AllWrappersAllAliasTO.xml"));
 	}
	public void testWrappersWithAllAliasBack (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		 
		pojoXml.addClassAlias(Wrappers.class, "wrapper");
		pojoXml.addFieldAlias(Wrappers.class,"byteValue", "byte");
		pojoXml.addFieldAlias(Wrappers.class,"shortValue", "short");
		pojoXml.addFieldAlias(Wrappers.class,"intValue", "int");
		pojoXml.addFieldAlias(Wrappers.class,"charValue", "char");
		pojoXml.addFieldAlias(Wrappers.class,"longValue", "long");
		pojoXml.addFieldAlias(Wrappers.class,"booleanValue", "boolean");
		pojoXml.addFieldAlias(Wrappers.class,"floatValue", "float");
		pojoXml.addFieldAlias(Wrappers.class,"doubleValue", "double");
		Wrappers wrap = (Wrappers)pojoXml.getPojoFromFile(pth+"AllWrappersAllAliasTO.xml", Wrappers.class);
		 
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersAllAlias.xml"),pojoXml.getXml(wrap));
 	}
	
	public void testWrappersWithAliasNull (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Wrappers wrap = getWrapper();
		wrap.setByteValue(null);
		wrap.setBooleanValue(null);
		pojoXml.addClassAlias(Wrappers.class, "wrapper");
		pojoXml.addFieldAlias(Wrappers.class,"byteValue", "byte");
		pojoXml.addFieldAlias(Wrappers.class,"shortValue", "short");
		pojoXml.addFieldAlias(Wrappers.class,"intValue", "int");
		pojoXml.addFieldAlias(Wrappers.class,"charValue", "char");
		pojoXml.addFieldAlias(Wrappers.class,"longValue", "long");
		pojoXml.addFieldAlias(Wrappers.class,"booleanValue", "boolean");
		pojoXml.addFieldAlias(Wrappers.class,"floatValue", "float");
		pojoXml.addFieldAlias(Wrappers.class,"doubleValue", "double");
		pojoXml.saveXml(wrap, pth+"AllWrappersAliasNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersAliasNull.xml"), XmlFiletReaderUtil.readFile(pth+"AllWrappersAliasNullTO.xml"));
	}
	public void testWrappersWithAliasNullBack (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		 
		pojoXml.addClassAlias(Wrappers.class, "wrapper");
		pojoXml.addFieldAlias(Wrappers.class,"byteValue", "byte");
		pojoXml.addFieldAlias(Wrappers.class,"shortValue", "short");
		pojoXml.addFieldAlias(Wrappers.class,"intValue", "int");
		pojoXml.addFieldAlias(Wrappers.class,"charValue", "char");
		pojoXml.addFieldAlias(Wrappers.class,"longValue", "long");
		pojoXml.addFieldAlias(Wrappers.class,"booleanValue", "boolean");
		pojoXml.addFieldAlias(Wrappers.class,"floatValue", "float");
		pojoXml.addFieldAlias(Wrappers.class,"doubleValue", "double");
		Wrappers wrap = (Wrappers)pojoXml.getPojoFromFile(pth+"AllWrappersAliasNullTO.xml", Wrappers.class);
		 
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersAliasNull.xml"),pojoXml.getXml(wrap));
 	}
	
	public void testWrappersWithCDATA(){
		WrappersCDATA wrap = new WrappersCDATA();
		wrap.setBigInt(new BigInteger("1000000000"));
		wrap.setName("rekha mol");
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.enableCDATA(true);
		pojoXml.saveXml(wrap, pth+"AllWrappersCDATATO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersCDATA.xml"), XmlFiletReaderUtil.readFile(pth+"AllWrappersCDATATO.xml"));
	}
	public void testWrappersWithCDATABack(){
		
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.enableCDATA(true);
		WrappersCDATA wrap =  (WrappersCDATA)pojoXml.getPojoFromFile(pth+"AllWrappersCDATATO.xml", WrappersCDATA.class);
		 
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersCDATA.xml"),pojoXml.getXml(wrap));
	}
	
	public void testWrappersWithAliasCDATA(){
		WrappersCDATA wrap = new WrappersCDATA();
		wrap.setBigInt(new BigInteger("1000000000"));
		wrap.setName("rekha mol");
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addFieldAlias(WrappersCDATA.class, "name", "NAME");
		pojoXml.enableCDATA(true);
		pojoXml.saveXml(wrap, pth+"AllWrappersCDATAAliasTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersCDATAAlias.xml"), XmlFiletReaderUtil.readFile(pth+"AllWrappersCDATAAliasTO.xml"));
	}
	public void testWrappersWithAliasCDATABack(){
		
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addFieldAlias(WrappersCDATA.class, "name", "NAME");
		pojoXml.enableCDATA(true);
		WrappersCDATA wrap =  (WrappersCDATA)pojoXml.getPojoFromFile(pth+"AllWrappersCDATAAliasTO.xml", WrappersCDATA.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"AllWrappersCDATAAlias.xml"),pojoXml.getXml(wrap));
	}
	private Wrappers getWrapper(){
		Wrappers wrap = new Wrappers();
		wrap.setByteValue(new Byte("10"));
		wrap.setShortValue(new Short("11"));
		wrap.setIntValue(new Integer("12"));
		wrap.setLongValue(new Long("1234567890"));
		wrap.setFloatValue(new Float("11.23f"));
		wrap.setCharValue(new Character('F'));
		wrap.setDoubleValue(new Double("1112.68"));
		wrap.setBooleanValue(new Boolean("true"));
		return wrap;
	}
}
