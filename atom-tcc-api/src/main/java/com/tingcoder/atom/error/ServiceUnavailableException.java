package com.tingcoder.atom.error;

import com.tingcoder.atom.Procedure;
import lombok.Getter;

public class ServiceUnavailableException extends CoordinatorException {
    private static final long serialVersionUID = 8744170308586376825L;

    @Getter
    private Procedure proc = null;

    public ServiceUnavailableException(Procedure proc, long uuid) {
        super(new StringBuilder().append("service ")
                .append(proc.getService()).append(" is not available,txId ").append(uuid).toString());
        this.proc = proc;
    }
}