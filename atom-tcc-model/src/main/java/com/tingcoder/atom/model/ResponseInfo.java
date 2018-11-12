package com.beingmate.tinytcc.model;

import com.beingmate.tinytcc.exception.SystemException;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/***
 * @author yfeng
 * @date 2018-11-02 12:18
 */
@Data
public class ResponseInfo<T> implements Serializable {

    private static final long serialVersionUID = -1777509208773831338L;

    private T data;
    private Boolean success;
    private String code;
    private String msg;
    private Date timestamp = new Date();

    public ResponseInfo(T data) {
        this.setSuccess(true);
        this.setCode("200");
        this.setData(data);
    }

    public static ResponseInfo fail(String code, String message) {
        ResponseInfo response = new ResponseInfo(null);
        response.setSuccess(false);
        response.setCode(code);
        response.setMsg(message);
        return response;
    }

    public void checkSuccess() {
        if (!this.success) {
            throw new SystemException(this.getCode(), this.getMsg());
        }
    }

    public static ResponseInfo success() {
        return new ResponseInfo(true);
    }
}