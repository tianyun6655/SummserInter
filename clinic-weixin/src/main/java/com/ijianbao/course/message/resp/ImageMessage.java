package com.ijianbao.course.message.resp;

import com.ijianbao.course.message.resp.BaseMessage;
import com.ijianbao.course.message.resp.Image;

public class ImageMessage extends BaseMessage {
	// ͼƬ
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
}
