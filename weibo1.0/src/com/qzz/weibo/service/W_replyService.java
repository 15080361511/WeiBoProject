package com.qzz.weibo.service;

import java.util.List;

import com.qzz.weibo.dao.W_replyDao;
import com.qzz.weibo.entity.W_reply;
import com.qzz.weibo.util.BaseDao;

public class W_replyService {
	private W_replyDao replyDao = new W_replyDao();
	/**
	 * ���ӻظ�
	 * @param reply
	 * @return
	 */
	public boolean addReply(W_reply reply) {
		return replyDao.addReply(reply);
	}
	/**
	 * ͨ������id���һظ�
	 * @param commentId
	 * @return
	 */
	public List<W_reply> queryReply(int commentId) {
		return replyDao.queryReply(commentId);
	}
}
