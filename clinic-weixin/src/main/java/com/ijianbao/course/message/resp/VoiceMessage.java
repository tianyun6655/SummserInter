package com.ijianbao.course.message.resp;

import com.ijianbao.course.message.resp.BaseMessage;
import com.ijianbao.course.message.resp.Voice;

public class VoiceMessage extends BaseMessage {
	// ����
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
