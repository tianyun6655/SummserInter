package com.ijianbao.course.message.resp;

import com.ijianbao.course.message.resp.BaseMessage;
import com.ijianbao.course.message.resp.Music;

public class MusicMessage extends BaseMessage {
	// ����
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
