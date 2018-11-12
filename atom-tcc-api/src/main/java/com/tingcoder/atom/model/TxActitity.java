package com.tingcoder.atom.model;

import com.tingcoder.atom.Procedure;
import com.tingcoder.atom.TxManager;
import com.tingcoder.atom.TxParticipant;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

@Data
public class TxActitity implements InitializingBean {
    private List<TxParticipant> participantList;
    private List<Procedure> procedureList;
    private TxManager txManager;

    public void afterPropertiesSet() throws Exception {
        txManager.register(this);
    }
}