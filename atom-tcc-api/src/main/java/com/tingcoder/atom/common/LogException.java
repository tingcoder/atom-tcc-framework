/**
 * @Description:			Exception for log operation
 * Copy Right:				Netease
 * Project:					TCC
 * JDK Version				jdk-1.6
 * @version					1.0
 * @author					huwei				
 */

package com.tingcoder.atom.common;


import com.tingcoder.atom.error.CoordinatorException;

public class LogException extends CoordinatorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8019056627152712074L;
	
	public LogException(String message, Throwable t) {
		super(message, t);
	}
	
	public LogException(String message) {
		super(message);
	}
}
