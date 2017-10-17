package com.qzz.weibo.dao;

import java.util.List;

import com.qzz.weibo.entity.W_comment;
import com.qzz.weibo.util.BaseDao;

/**
 * ���۵����ݿ������
 * @author Administrator
 *
 */
public class W_commentDao {
	/**
	 * ��������
	 * @return
	 */
	public boolean addComment(W_comment comment) {
		return BaseDao.execute("insert into W_COMMENT values(null,?,?,?)", comment.getWEIBOID(),comment.getCOMMENTNAME(),comment.getCOMMCONTENT())>0;
	}
	/**
	 * ͨ��΢��id��������
	 */
	public List<W_comment> queryCmById(int weiboId) {
		return (List<W_comment>) BaseDao.select("select * from W_COMMENT where WEIBOID =?", W_comment.class, weiboId);
	}
}
