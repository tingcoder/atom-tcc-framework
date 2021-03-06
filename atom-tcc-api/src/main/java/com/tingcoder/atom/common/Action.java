package com.tingcoder.atom.common;

import lombok.Getter;

public enum Action {

    REGISTERED(0),
    CONFIRM(1),
    CANCEL(2),
    EXPIRE(3);

    @Getter
    private int code;

    Action(int code) {
        this.code = code;
    }

    public static Action getAction(int code) {
        switch (code) {
            case 0:
                return REGISTERED;
            case 1:
                return CONFIRM;
            case 2:
                return CANCEL;
            case 3:
                return EXPIRE;
            default:
                throw new IllegalArgumentException("action must be 0-3,now is " + code);
        }
    }
}