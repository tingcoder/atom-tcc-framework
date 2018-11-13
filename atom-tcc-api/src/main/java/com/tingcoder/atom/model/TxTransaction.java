package com.tingcoder.atom.model;

import com.tingcoder.atom.Procedure;
import com.tingcoder.atom.error.SystemException;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/***
 * @author yfeng
 * @date 2018-07-05 12:55
 */
@Data
public class TxTransaction implements Serializable {
    private static final long serialVersionUID = -1777509208773831338L;

    protected long txId = -1;
    private List<Procedure> procedureList;

    public void checkBegin() {
        if (this.txId == -1) {
            throw new SystemException("事务未开始", "事务并未开始，无法执行后续操作");
        }
    }
}