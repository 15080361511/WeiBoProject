package com.qzz.weibo.service;

import java.util.List;

import com.qzz.weibo.dao.W_messageDao;
import com.qzz.weibo.entity.W_message;
import com.qzz.weibo.util.BaseDao;

public class W_messageService {
	private W_messageDao messageDao = new W_messageDao();
	/**
	 * ͨ�������˺��ռ��˲�����Ϣ��¼
	 * @param sendName
	 * @param receiveName
	 * @return
	 */
	public List<W_message> queryMessageByName(String sendName,String receiveName) {
		return messageDao.queryMessageByName(sendName, receiveName);
	}
	/**
	 * �������Ϣ
	 * @param message
	 * @return
	 */
	public boolean addMessage(W_message message) {
		return messageDao.addMessage(message);
	}
}
