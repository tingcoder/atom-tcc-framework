package com.beingmate.tinytcc;

import com.beingmate.tinytcc.error.ParticipantException;

public interface Participant {
	
	void cancel(Long uuid) throws ParticipantException;
	
	void confirm(Long uuid) throws ParticipantException;
	
	void expired(Long uuid) throws ParticipantException;
}