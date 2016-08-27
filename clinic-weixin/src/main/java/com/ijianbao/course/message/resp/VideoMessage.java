package com.ijianbao.course.message.resp;

import com.ijianbao.course.message.resp.BaseMessage;
import com.ijianbao.course.message.resp.Video;

public class VideoMessage extends BaseMessage {
	// ��Ƶ
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
