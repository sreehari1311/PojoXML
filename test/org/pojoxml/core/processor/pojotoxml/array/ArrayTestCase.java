package org.pojoxml.core.processor.pojotoxml.array;
 
import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;
import org.pojoxml.test.util.Path;
import org.pojoxml.test.util.XmlFiletReaderUtil;

import junit.framework.TestCase;

public class ArrayTestCase extends TestCase{


	String pth =Path.ROOT+"arrays\\";
 	
	  public void testPrimitiveArray(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(getPrimitiveArray(), pth+"ArrayPrimitivesTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayPrimitives.xml"), XmlFiletReaderUtil.readFile(pth+"ArrayPrimitivesTO.xml"));
	}
	
	public void testPrimitiveArrayBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		PrimitiveArray pa = (PrimitiveArray) pojoXml.getPojoFromFile(pth+"ArrayPrimitivesTO.xml", PrimitiveArray.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayPrimitives.xml"), pojoXml.getXml(pa));
	}
	public void testPrimitiveArrayWithNull(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(getPrimitiveArrayWithNull() , pth+"ArrayPrimitivesWithNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayPrimitivesWithNull.xml"), XmlFiletReaderUtil.readFile(pth+"ArrayPrimitivesWithNullTO.xml"));
	} 
	
	public void testPrimitiveArrayWithNullBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		PrimitiveArray pa = (PrimitiveArray) pojoXml.getPojoFromFile(pth+"ArrayPrimitivesWithNullTO.xml", PrimitiveArray.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayPrimitivesWithNull.xml"), pojoXml.getXml(pa));
	}
	public void testPrimitiveArrayWithAlias(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addFieldAlias(PrimitiveArray.class, "byteValue", "bytes");
		pojoXml.addFieldAlias(PrimitiveArray.class, "charValue", "chars");
		pojoXml.saveXml(getPrimitiveArrayWithNull() , pth+"ArrayPrimitivesWithAliasTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayPrimitivesWithAlias.xml"), XmlFiletReaderUtil.readFile(pth+"ArrayPrimitivesWithAliasTO.xml"));
	} 
	
	public void testPrimitiveArrayWithAliasBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addFieldAlias(PrimitiveArray.class, "byteValue", "bytes");
		pojoXml.addFieldAlias(PrimitiveArray.class, "charValue", "chars");
		PrimitiveArray pa = (PrimitiveArray) pojoXml.getPojoFromFile(pth+"ArrayPrimitivesWithAliasTO.xml", PrimitiveArray.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayPrimitivesWithAlias.xml"), pojoXml.getXml(pa));
	}
	public void testWrapperArray(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(getWrapperArray() , pth+"ArrayWrapperTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayWrapper.xml"), XmlFiletReaderUtil.readFile(pth+"ArrayWrapperTO.xml"));
	}
	
	public void testWrapperArrayBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		WrapperArray pa = (WrapperArray) pojoXml.getPojoFromFile(pth+"ArrayWrapperTO.xml", WrapperArray.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayWrapper.xml"), pojoXml.getXml(pa));
	}
	
	public void testWrapperArrayWithNull(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(getWrapperArrayWithNull()   , pth+"ArrayWrapperWithNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayWrapperWithNull.xml"), XmlFiletReaderUtil.readFile(pth+"ArrayWrapperWithNullTO.xml"));
	}
	
	public void testWrapperArrayWithNullBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		WrapperArray pa = (WrapperArray) pojoXml.getPojoFromFile(pth+"ArrayWrapperWithNullTO.xml", WrapperArray.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayWrapperWithNull.xml"), pojoXml.getXml(pa));
	}
	
	public void testWrapperArrayWithMultiNull(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.saveXml(getWrapperArrayWithMultiNull()   , pth+"ArrayWrapperWithMultiNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayWrapperWithMultiNull.xml"), XmlFiletReaderUtil.readFile(pth+"ArrayWrapperWithMultiNullTO.xml"));
	}
	
	public void testWrapperArrayWithMultiNullBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		WrapperArray pa = (WrapperArray) pojoXml.getPojoFromFile(pth+"ArrayWrapperWithMultiNullTO.xml", WrapperArray.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayWrapperWithMultiNull.xml"), pojoXml.getXml(pa));
	}
	
	public void testWrapperArrayWithAlias(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addFieldAlias(WrapperArray.class, "longValue", "longs");
		pojoXml.addFieldAlias(WrapperArray.class, "floatValue","floats");
		pojoXml.addFieldAlias(WrapperArray.class, "intValue","ints");
		pojoXml.saveXml(getWrapperArrayWithMultiNull()   , pth+"ArrayWrapperWithAliasTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayWrapperWithAlias.xml"), XmlFiletReaderUtil.readFile(pth+"ArrayWrapperWithAliasTO.xml"));
	}
	 
	public void testWrapperArrayWithAliasBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addFieldAlias(WrapperArray.class, "longValue", "longs");
		pojoXml.addFieldAlias(WrapperArray.class, "floatValue","floats");
		pojoXml.addFieldAlias(WrapperArray.class, "intValue","ints");
		WrapperArray pa = (WrapperArray) pojoXml.getPojoFromFile(pth+"ArrayWrapperWithAliasTO.xml", WrapperArray.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayWrapperWithAlias.xml"), pojoXml.getXml(pa));
	}
	
	public void testObjectArray (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		ObjectArray objArray = new ObjectArray();
		objArray.setAge(1);
		objArray.setName("RR");
		objArray.setWrapperArray(new WrapperArray[]{getWrapperArray(),getWrapperArray()});
 		pojoXml.saveXml(objArray   , pth+"ArrayObjectTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayObject.xml"), XmlFiletReaderUtil.readFile(pth+"ArrayObjectTO.xml"));
	}

	public void testObjectArrayBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		ObjectArray pa = (ObjectArray) pojoXml.getPojoFromFile(pth+"ArrayObjectTO.xml", ObjectArray.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayObject.xml"), pojoXml.getXml(pa));
	}
	
	public void testObjectArrayWithAlias (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		ObjectArray objArray = new ObjectArray();
		objArray.setAge(1);
		objArray.setName("RR");
		objArray.setWrapperArray(new WrapperArray[]{getWrapperArray(),getWrapperArray()});
		pojoXml.addFieldAlias(ObjectArray.class, "wrapperArray", "wrap");
 		pojoXml.saveXml(objArray   , pth+"ArrayObjectWithAliasTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayObjectWithAlias.xml"), XmlFiletReaderUtil.readFile(pth+"ArrayObjectWithAliasTO.xml"));
	}

 	public void testObjectArrayWithAliasBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addFieldAlias(ObjectArray.class, "wrapperArray", "wrap");
		ObjectArray pa = (ObjectArray) pojoXml.getPojoFromFile(pth+"ArrayObjectWithAliasTO.xml", ObjectArray.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayObjectWithAlias.xml"), pojoXml.getXml(pa));
	} 
 	public void testObjectArrayWithAliasNull (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		ObjectArray objArray = new ObjectArray();
		objArray.setAge(1);
		objArray.setName("RR");
		objArray.setWrapperArray(new WrapperArray[]{null,getWrapperArray(),null});
		pojoXml.addFieldAlias(ObjectArray.class, "wrapperArray", "wrap");
 		pojoXml.saveXml(objArray   , pth+"ArrayObjectWithAliasNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayObjectWithAliasNull.xml"), XmlFiletReaderUtil.readFile(pth+"ArrayObjectWithAliasNullTO.xml"));
	}

 	public void testObjectArrayWithAliasNullBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addFieldAlias(ObjectArray.class, "wrapperArray", "wrap");
		ObjectArray pa = (ObjectArray) pojoXml.getPojoFromFile(pth+"ArrayObjectWithAliasNullTO.xml", ObjectArray.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayObjectWithAliasNull.xml"), pojoXml.getXml(pa));
	}
 	public void testObjectArrayWithAliasAllNull (){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		ObjectArray objArray = new ObjectArray();
		objArray.setAge(1);
		objArray.setName("RR");
		objArray.setWrapperArray(new WrapperArray[]{null,null,null});
		pojoXml.addFieldAlias(ObjectArray.class, "wrapperArray", "wrap");
 		pojoXml.saveXml(objArray   , pth+"ArrayObjectWithAliasAllNullTO.xml");
		assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayObjectWithAliasAllNull.xml"), XmlFiletReaderUtil.readFile(pth+"ArrayObjectWithAliasAllNullTO.xml"));
	}

 	public void testObjectArrayWithAliasAllNullBack(){
		PojoXml pojoXml = PojoXmlFactory.createPojoXml();
		pojoXml.addFieldAlias(ObjectArray.class, "wrapperArray", "wrap");
		ObjectArray pa = (ObjectArray) pojoXml.getPojoFromFile(pth+"ArrayObjectWithAliasAllNullTO.xml", ObjectArray.class);
	 	assertEquals(XmlFiletReaderUtil.readFile(pth+"ArrayObjectWithAliasAllNull.xml"), pojoXml.getXml(pa));
	}

  	private PrimitiveArray getPrimitiveArray(){
		PrimitiveArray p = new PrimitiveArray();
		p.setByteValue(new byte[]{10,11});
		p.setShortValue(new short[]{12,13});
		p.setIntValue(new int[]{14,15} );
		p.setLongValue(new long[]{1234567890});
		p.setFloatValue(new float[]{11.2f,11.3f});
		p.setCharValue(new char[]{'F','A'});
		p.setDoubleValue(new double[]{1112.68});
		p.setBooleanValue(new boolean[]{true,false});
		return p;
	}
	private PrimitiveArray getPrimitiveArrayWithNull(){
		PrimitiveArray p = new PrimitiveArray();
	 	p.setShortValue(new short[]{12,13});
		p.setIntValue(new int[]{14,15} );
		p.setLongValue(new long[]{1234567890});
	 	p.setCharValue(new char[]{'F','A'});
		p.setDoubleValue(new double[]{1112.68});
	 	return p;
	}
	private WrapperArray getWrapperArray(){
		WrapperArray wrap = new WrapperArray();
		wrap.setByteValue(new Byte[]{new Byte("10"),new Byte("11")});
		wrap.setShortValue(new Short[] {new Short ("12"),new Short("13")});
		wrap.setIntValue(new Integer[]{new Integer("14"),new Integer("15")});
		wrap.setLongValue(new Long[]{new Long("1234567890")});
		wrap.setFloatValue(new Float[]{new Float("11.2"), new Float("11.3")});
		wrap.setCharValue(new Character[]{new Character('F'),new Character('A')});
		wrap.setDoubleValue(new Double[]{new Double("1112.68")});
		wrap.setBooleanValue(new Boolean[]{new Boolean("true"),new Boolean("false")});
		return wrap;
	}
	private WrapperArray getWrapperArrayWithNull(){
		WrapperArray wrap = new WrapperArray();
		wrap.setShortValue(new Short[] {new Short ("12"),new Short("13")});
		wrap.setIntValue(new Integer[]{new Integer("14"),new Integer("15"),null});
		wrap.setLongValue(new Long[]{new Long("1234567890")});
		wrap.setFloatValue(new Float[]{new Float("11.2"), new Float("11.3")});
		wrap.setCharValue(new Character[]{new Character('F'),new Character('A')});
		wrap.setDoubleValue(new Double[]{new Double("1112.68")});
 		return wrap;
	}
	private WrapperArray getWrapperArrayWithMultiNull(){
		WrapperArray wrap = new WrapperArray();
		wrap.setShortValue(new Short[] {new Short ("12"),new Short("13")});
		wrap.setIntValue(new Integer[]{new Integer("14"),new Integer("15"),null});
		wrap.setLongValue(new Long[]{null});
		wrap.setFloatValue(new Float[]{null,null});
		wrap.setCharValue(new Character[]{new Character('F'),new Character('A')});
		wrap.setDoubleValue(new Double[]{new Double("1112.68")});
 		return wrap;
	}
}
