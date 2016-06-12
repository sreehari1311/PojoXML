package org.pojoxml.core.processor.pojotoxml.wrapper;

import java.math.BigDecimal;
import java.math.BigInteger;

public class WrappersCDATA {

	private String name;
	private BigDecimal decimal;
	private BigInteger bigInt;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getDecimal() {
		return decimal;
	}
	public void setDecimal(BigDecimal decimal) {
		this.decimal = decimal;
	}
	public BigInteger getBigInt() {
		return bigInt;
	}
	public void setBigInt(BigInteger bigInt) {
		this.bigInt = bigInt;
	}
}
