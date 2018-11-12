package com.tingcoder.atom.error;

import com.tingcoder.atom.Procedure;
import com.tingcoder.atom.TccCode;
import com.tingcoder.atom.TccUtils;
import lombok.Getter;

public class HeuristicsException extends Exception {
	private static final long serialVersionUID = -1620721172886005574L;

	@Getter
	private short code;
	private Procedure proc;
	private String msg;

	public HeuristicsException(short code, Procedure proc, String msg) {
		super();
		this.code = code;
		this.proc = proc;
		this.msg = msg;
	}
	
	public HeuristicsException() {
		super();
		this.code = TccUtils.UNDEFINED;
	}
	
	public HeuristicsException(Procedure proc, String msg) {
		super();
		this.code = TccUtils.UNDEFINED;
		this.proc = proc;
		this.msg = msg;
	}
	@Override
	public String getMessage() {
		return msg;
	}
	
	public static HeuristicsException getException(HeuristicsType type, Procedure proc, String msg) {
		switch (type) {
			case SERVICE_NOT_FOUND: 
				return new HeuristicsException(TccCode.getServiceNotFound(proc.getIndex()), proc, msg);
			case TIMEOUT: 
				return new HeuristicsException(TccCode.getTimeout(proc.getIndex()), proc, msg);
			case UNDEFINED:
				return new HeuristicsException(proc, msg);
			default:
				throw new UnsupportedOperationException("normal HeuristicsException must has a code");
		} 
	}
	
	public static HeuristicsException getException(short code, Procedure proc, String msg) {
		return new HeuristicsException(code, proc, msg);
	}
}