package com.tingcoder.atom;

import com.tingcoder.atom.common.HeuristicsInfo;
import com.tingcoder.atom.error.CoordinatorException;
import com.tingcoder.atom.model.TxTransaction;

import java.util.List;

public interface TxCoordinator {

    /**
     * 开启一个分布式事务
     *
     * @param procedures
     * @return 全局唯一的分布式事务ID
     * @throws CoordinatorException
     */
    long begin(List<Procedure> procedures) throws CoordinatorException;

    short confirm(TxTransaction tx) throws CoordinatorException;

    short confirm(TxTransaction tx, long timeout) throws CoordinatorException;

    short cancel(TxTransaction tx) throws CoordinatorException;

    short cancel(TxTransaction tx, long timeout) throws CoordinatorException;

    List<HeuristicsInfo> getHeuristicExceptionList(long startTime, long endTime) throws CoordinatorException;

    void removeHeuristicExceptions(List<Long> txIdList) throws CoordinatorException;
}