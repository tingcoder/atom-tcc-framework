package com.beingmate.tinytcc.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class HeuristicsInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private long uuid;
    private Action action;
}
