package com.tingcoder.atom;


import com.tingcoder.atom.error.ParticipantException;

public interface TxParticipant {
	
	void cancel(Long uuid) throws ParticipantException;
	
	void confirm(Long uuid) throws ParticipantException;
	
	void expired(Long uuid) throws ParticipantException;
}