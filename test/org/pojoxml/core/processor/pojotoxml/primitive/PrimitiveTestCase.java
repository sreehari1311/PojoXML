package org.pojoxml.core.processor.pojotoxml.primitive;

import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;
import org.pojoxml.test.util.Path;
import org.pojoxml.test.util.XmlFiletReaderUtil;

import junit.framework.TestCase;

public class PrimitiveTestCase extends TestCase {

	String pth =Path.ROOT+"primitives\\";


	public void testAllPrimitives(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(getPrimitiveObject(), pth+"AllPrimitivesTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllPrimitives.xml"), XmlFiletReaderUtil.readFile(pth+"AllPrimitivesTO.xml"));
		
	}
	
	public void testAllPrimitivesBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		Primitives prim = (Primitives)pojoXml.getPojoFromFile(pth+"AllPrimitivesTO.xml", Primitives.class);
		assertEquals(pojoXml.getXml(prim), XmlFiletReaderUtil.readFile(pth+"AllPrimitives.xml"));
	}
	
	public void testAllPrimitivesAlias(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addClassAlias(Primitives.class, "primitve");
		pojoXml.addFieldAlias(Primitives.class,"byteValue", "byte");
		pojoXml.addFieldAlias(Primitives.class,"shortValue", "short");
		pojoXml.addFieldAlias(Primitives.class,"intValue", "int");
		pojoXml.addFieldAlias(Primitives.class,"charValue", "char");
		pojoXml.addFieldAlias(Primitives.class,"longValue", "long");
		pojoXml.addFieldAlias(Primitives.class,"booleanValue", "boolean");
		pojoXml.addFieldAlias(Primitives.class,"floatValue", "float");
		pojoXml.addFieldAlias(Primitives.class,"doubleValue", "double");
		
		pojoXml.saveXml(getPrimitiveObject(), pth+"AllPrimitivesAleasTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllPrimitivesAleas.xml"), XmlFiletReaderUtil.readFile(pth+"AllPrimitivesAleasTO.xml"));
		
	}
	public void testAllPrimitivesAliasBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addClassAlias(Primitives.class, "primitve");
		pojoXml.addFieldAlias(Primitives.class,"byteValue", "byte");
		pojoXml.addFieldAlias(Primitives.class,"shortValue", "short");
		pojoXml.addFieldAlias(Primitives.class,"intValue", "int");
		pojoXml.addFieldAlias(Primitives.class,"charValue", "char");
		pojoXml.addFieldAlias(Primitives.class,"longValue", "long");
		pojoXml.addFieldAlias(Primitives.class,"booleanValue", "boolean");
		pojoXml.addFieldAlias(Primitives.class,"floatValue", "float");
		pojoXml.addFieldAlias(Primitives.class,"doubleValue", "double");
		
		Primitives prim = (Primitives)pojoXml.getPojoFromFile( pth+"AllPrimitivesAleasTO.xml", Primitives.class );
		assertEquals(pojoXml.getXml(prim), XmlFiletReaderUtil.readFile(pth+"AllPrimitivesAleas.xml"));
		
	}
	public void testAllPrimitivesSomeAlias(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addClassAlias(Primitives.class, "primitve");
	 	pojoXml.addFieldAlias(Primitives.class,"shortValue", "short");
		pojoXml.addFieldAlias(Primitives.class,"intValue", "int");
 		pojoXml.addFieldAlias(Primitives.class,"longValue", "long");
 		pojoXml.addFieldAlias(Primitives.class,"floatValue", "float");
		 pojoXml.addFieldAlias(Primitives.class,"doubleValue", "double");
		
		pojoXml.saveXml(getPrimitiveObject(), pth+"AllPrimitivesWithSomeAleasTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"AllPrimitivesWithSomeAleas.xml"), XmlFiletReaderUtil.readFile(pth+"AllPrimitivesWithSomeAleasTO.xml"));
		
	}
	public void testAllPrimitivesSomeAliasBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addClassAlias(Primitives.class, "primitve");
	 	pojoXml.addFieldAlias(Primitives.class,"shortValue", "short");
		pojoXml.addFieldAlias(Primitives.class,"intValue", "int");
 		pojoXml.addFieldAlias(Primitives.class,"longValue", "long");
 		pojoXml.addFieldAlias(Primitives.class,"floatValue", "float");
		pojoXml.addFieldAlias(Primitives.class,"doubleValue", "double");
		
		Primitives prim = (Primitives)pojoXml.getPojoFromFile( pth+"AllPrimitivesWithSomeAleasTO.xml", Primitives.class );
		assertEquals(pojoXml.getXml(prim), XmlFiletReaderUtil.readFile(pth+"AllPrimitivesWithSomeAleas.xml"));
		
	}
	private Primitives getPrimitiveObject(){
		Primitives p = new Primitives();
		p.setByteValue((byte)10);
		p.setShortValue((byte)11);
		p.setIntValue(12);
		p.setLongValue(1234567890);
		p.setFloatValue(11.23f);
		p.setCharValue('F');
		p.setDoubleValue(1112.68);
		p.setBooleanValue(true);
		return p;
	}
}
