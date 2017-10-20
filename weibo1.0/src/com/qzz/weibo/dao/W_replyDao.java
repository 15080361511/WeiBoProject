package com.qzz.weibo.dao;

import java.util.List;

import com.qzz.weibo.entity.W_reply;
import com.qzz.weibo.util.BaseDao;

public class W_replyDao {
	/**
	 * ���ӻظ�
	 * @param reply
	 * @return
	 */
	public boolean addReply(W_reply reply) {
		return BaseDao.execute("insert into W_REPLY values(null,?,?,?,?,?)", reply.getCOMMENTID(), reply.getREPLYANAME(),
				reply.getREPLYBNAME(), reply.getREPLYCONTENT(), reply.getREPLYTIME()) > 0;
	}
	
	/**
	 * ͨ������id���һظ�
	 * @param commentId
	 * @return
	 */
	public List<W_reply> queryReply(int commentId) {
		return (List<W_reply>) BaseDao.select("select * from W_REPLY where COMMENTID = ?", W_reply.class, commentId);
	}
	
	/**
	 * ����ȫ������
	 * @return
	 */
	public List<W_reply> queryAllReply() {
		return (List<W_reply>) BaseDao.select("select * from W_REPLY", W_reply.class, null);
	}
}
