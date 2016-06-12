/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pojoxml.util;

/**
 * Assertion utility class that assists in validating arguments.
 * Useful for identifying programmer errors early and clearly at runtime.
 * This class will checks parameter checks prior to executes the actual statements
 * 
 * @author SreeHari
 * @since 1.0
 *
 */
public class Assert {

	/**
	 * check whether the prameter name is null or not. if its null. this method will throw a Runtime
	 * exception {@link IllegalArgumentException} back to the called method
	 * 
	 * @param object parameter 
	 * @param message error message.
	 */
	public static void isNull(Object object,String message){
		if(object ==  null)
			throw new IllegalArgumentException(message);
	}
	
	/**
	 * This method will checks the given index is negative or not
	 * @param index 
	 * @param message error message
	 */
	public static void checkIndex(int index,String message){
		if(index < 0)
			throw new IndexOutOfBoundsException(message);
	}
}
