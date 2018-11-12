package com.tingcoder.atom;

import com.tingcoder.atom.common.HeuristicsInfo;
import com.tingcoder.atom.error.CoordinatorException;

import java.util.List;

public interface Coordinator {

    /**
     * 开启一个分布式事务
     * @param sequenceId
     * @param procedures
     * @return 全局唯一的分布式事务ID
     * @throws CoordinatorException
     */
    long begin(int sequenceId, List<Procedure> procedures) throws CoordinatorException;

    short confirm(int sequenceId, long uuid, List<Procedure> procedures) throws CoordinatorException;

    short confirm(int sequenceId, long uuid, long timeout, List<Procedure> procedures) throws CoordinatorException;

    short cancel(int sequenceId, long uuid, List<Procedure> procedures) throws CoordinatorException;

    short cancel(int sequenceId, long uuid, long timeout, List<Procedure> procedures) throws CoordinatorException;

    List<HeuristicsInfo> getHeuristicExceptionList(long startTime, long endTime) throws CoordinatorException;

    void removeHeuristicExceptions(List<Long> txIdList) throws CoordinatorException;
}