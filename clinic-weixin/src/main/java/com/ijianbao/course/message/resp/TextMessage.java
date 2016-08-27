package com.ijianbao.course.message.resp;

import com.ijianbao.course.message.resp.BaseMessage;

public class TextMessage extends BaseMessage {
	// �ظ�����Ϣ����
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
