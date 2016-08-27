package com.ijianbao.course.message.req;

import com.ijianbao.course.message.req.BaseMessage;

public class TextMessage extends BaseMessage {
	// ��Ϣ����
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
