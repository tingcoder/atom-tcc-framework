package com.tingcoder.atom.model;

/***
 * 事务本地执行状态
 * @author yfeng
 * @date 2018-07-05 13:11
 */
public enum TxStatusEnum {
    /**
     * 已经注册
     */
    REGISTERED(0),

    /**
     * Try成功
     */
    TRY_SUCCESS(1),

    /**
     * 已经确认
     */
    CONFIRM(2),

    /**
     * 已经取消
     */
    CANCEL(3);

    private int code;

    TxStatusEnum(int code) {
        this.code = code;
    }
}