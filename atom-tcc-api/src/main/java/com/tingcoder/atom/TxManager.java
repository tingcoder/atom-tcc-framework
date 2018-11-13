package com.tingcoder.atom;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.tingcoder.atom.error.CoordinatorException;
import com.tingcoder.atom.model.TxActitity;
import com.tingcoder.atom.model.TxTransaction;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yfeng
 * @date 2018-07-05 12:55
 */
@Component
public class TxManager implements ApplicationContextAware, InitializingBean {

    @Autowired
    private TxCoordinator txCoordinator;

    private ApplicationContext applicationContext;

    private Map<Object, Procedure> participantToProcedure = new HashMap<Object, Procedure>();

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void afterPropertiesSet() throws Exception {
        String[] beanNames = applicationContext.getBeanNamesForType(TxParticipant.class);
        for (String beanName : beanNames) {
            Object participant = applicationContext.getBean(beanName);
            Class participantClass = participant.getClass();
            Class[] inters = participantClass.getInterfaces();
            for (Class interClass : inters) {
                if (TxParticipant.class.isAssignableFrom(interClass)) {
                    ReferenceConfig referenceConfig = applicationContext.getBean("&" + beanName, ReferenceConfig.class);

                    Procedure procedure = new Procedure();
                    procedure.setService(interClass.getName());
                    if (!StringUtils.isEmpty(referenceConfig.getVersion())) {
                        procedure.setService(referenceConfig.getVersion());
                    }
                    if (!StringUtils.isEmpty(referenceConfig.getGroup())) {
                        procedure.setService(referenceConfig.getGroup());
                    }
                    participantToProcedure.put(participant, procedure);
                }
            }
        }
    }

    private List<Procedure> getProcedureLists(List<TxParticipant> participants) {
        List<Procedure> procedureList = new ArrayList<Procedure>();
        for (TxParticipant txParticipant : participants) {
            Procedure procedure = participantToProcedure.get(txParticipant);
            procedureList.add(procedure);
        }
        return procedureList;
    }

    public TxTransaction createTransaction(String txActitiyName) throws CoordinatorException {
        TxActitity txActitity = applicationContext.getBean(txActitiyName, TxActitity.class);
        return createTransaction(txActitity);
    }

    public TxTransaction createTransaction(TxActitity txActitity) throws CoordinatorException {
        TxTransaction txTranaction = new TxTransaction();
        txTranaction.setProcedureList(txActitity.getProcedureList());
        long txId = txCoordinator.begin(txActitity.getProcedureList());
        txTranaction.setTxId(txId);
        return txTranaction;
    }

    public void register(TxActitity txActitity) {
        List<Procedure> procedureList = getProcedureLists(txActitity.getParticipantList());
        txActitity.setProcedureList(procedureList);
    }

    public void confirm(TxTransaction tx) throws CoordinatorException {
        tx.checkBegin();
        txCoordinator.confirm(tx);
    }

    public void confirm(TxTransaction tx, long timeout) throws CoordinatorException {
        tx.checkBegin();
        txCoordinator.confirm(tx, timeout);
    }

    public void cancel(TxTransaction tx) throws CoordinatorException {
        tx.checkBegin();
        txCoordinator.cancel(tx);
    }

    public void cancel(TxTransaction tx, long timeout) throws CoordinatorException {
        tx.checkBegin();
        txCoordinator.cancel(tx, timeout);
    }
}