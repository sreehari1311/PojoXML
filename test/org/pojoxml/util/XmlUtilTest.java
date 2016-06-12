package org.pojoxml.util;

import junit.framework.TestCase;

public class XmlUtilTest extends TestCase {

	public void testTags(){
		assertEquals("<test>test</test>", XmlUtil.getElement("test","test", 0 ));
 	}
}
