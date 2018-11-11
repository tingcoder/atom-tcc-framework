package com.beingmate.tinytcc.error;

import com.beingmate.tinytcc.Procedure;
import lombok.Getter;

public class ServiceUnavailableException extends CoordinatorException {
    private static final long serialVersionUID = 8744170308586376825L;

    @Getter
    private Procedure proc = null;

    public ServiceUnavailableException(Procedure proc, long uuid) {
        super(new StringBuilder().append("service ")
                .append(proc.getService()).append(" is not available,uuid ").append(uuid).toString());
        this.proc = proc;
    }
}