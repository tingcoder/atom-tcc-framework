package com.tingcoder.atom.model;

import com.tingcoder.atom.Procedure;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TxTransaction implements Serializable {
    private static final long serialVersionUID = -1777509208773831338L;

    protected long uuid = -1;
    private List<Procedure> procedureList;
}