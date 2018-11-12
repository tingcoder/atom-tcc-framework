package com.tingcoder.atom;

import com.tingcoder.atom.error.CoordinatorException;
import com.tingcoder.atom.error.SystemException;
import com.tingcoder.atom.model.TxActitity;
import com.tingcoder.atom.model.TxTransaction;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TxManager implements ApplicationContextAware, InitializingBean {

    @Autowired
    private TxCoordinator txCoordinator;

    private ApplicationContext applicationContext;

    private Map<Object, String> participantObjToServiceName = new HashMap<Object, String>();

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
                    String interClassName = interClass.getName();
                    participantObjToServiceName.put(participant, interClassName);
                }
            }
        }
    }

    private List<Procedure> getProcedureLists(List<TxParticipant> participants) {
        List<Procedure> procedureList = new ArrayList<Procedure>();
        for (TxParticipant txParticipant : participants) {
            String serviceName = participantObjToServiceName.get(txParticipant);
            Procedure procedure = new Procedure();
            procedure.setService(serviceName);
            procedure.setVersion("");

            procedureList.add(procedure);
        }
        return procedureList;
    }

    public TxTransaction createTransaction(String txName) throws CoordinatorException {
        TxActitity txActitity = applicationContext.getBean(txName, TxActitity.class);
        return createTransaction(txActitity);
    }

    public TxTransaction createTransaction(TxActitity txActitity) throws CoordinatorException {
        TxTransaction txTranaction = new TxTransaction();
        txTranaction.setProcedureList(txActitity.getProcedureList());
        return txTranaction;
    }

    public void register(TxActitity txActitity) {
        List<Procedure> procedureList = getProcedureLists(txActitity.getParticipantList());
        txActitity.setProcedureList(procedureList);
    }

    private void checkBegin(TxTransaction tx) {
        if (tx.getUuid() == -1) {
            throw new SystemException("事务未开始", "事务并未开始，无法执行后续操作");
        }
    }

    public void confirm(TxTransaction tx) throws CoordinatorException {
        checkBegin(tx);
        txCoordinator.confirm(tx);
    }

    public void confirm(TxTransaction tx, long timeout) throws CoordinatorException {
        checkBegin(tx);
        txCoordinator.confirm(tx, timeout);
    }

    public void cancel(TxTransaction tx) throws CoordinatorException {
        checkBegin(tx);
        txCoordinator.cancel(tx);
    }

    public void cancel(TxTransaction tx, long timeout) throws CoordinatorException {
        checkBegin(tx);
        txCoordinator.cancel(tx, timeout);
    }


}