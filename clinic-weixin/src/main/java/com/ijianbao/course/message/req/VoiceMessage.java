package com.ijianbao.course.message.req;

import com.ijianbao.course.message.req.BaseMessage;

public class VoiceMessage extends BaseMessage {
	// ý��ID
	private String MediaId;
	// ������ʽ
	private String Format;
	// ����ʶ������UTF8����
	private String Recognition;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
}
