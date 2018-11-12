package com.tingcoder.atom.common;


import com.tingcoder.atom.error.CoordinatorException;
import lombok.Getter;

public class IllegalActionException extends CoordinatorException {

    private static final long serialVersionUID = 130940037250816868L;

    @Getter
    private long uuid;
    @Getter
    private Action first;
    @Getter
    private Action second;

    public IllegalActionException(long uuid, Action first, Action second) {
        super(new StringBuilder().append("tx ").append(uuid).append(" illegal action switch:")
                .append(first).append(" to ").append(second).toString());
        this.uuid = uuid;
        this.first = first;
        this.second = second;
    }

}