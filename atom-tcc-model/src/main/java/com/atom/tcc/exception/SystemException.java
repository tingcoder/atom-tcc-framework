package com.beingmate.tinytcc.exception;

import com.beingmate.tinytcc.model.ResponseInfo;
import lombok.Getter;

/***
 * @author yfeng
 * @date 2018-11-03 7:44
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 4106930048877805871L;

    @Getter
    private String errorCode;
    @Getter
    private String errorMessage;

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public SystemException(String code, String message) {
        super(code);
        this.errorCode = code;
        this.errorMessage = message;
    }

    public ResponseInfo buildResponse() {
        return ResponseInfo.fail(this.errorCode, this.errorMessage);
    }
}
