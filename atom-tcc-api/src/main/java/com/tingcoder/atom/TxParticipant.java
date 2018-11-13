package com.tingcoder.atom;


import com.tingcoder.atom.error.ParticipantException;
import com.tingcoder.atom.model.TxStatusEnum;

public interface TxParticipant {

    void cancel(Long txId) throws ParticipantException;

    void confirm(Long txId) throws ParticipantException;

    void expired(Long txId) throws ParticipantException;

    TxStatusEnum getStatus(Long txId);
}