package com.qzz.weibo.dao;

import java.util.List;

import com.qzz.weibo.entity.W_weibo;
import com.qzz.weibo.entity.W_zan;
import com.qzz.weibo.util.BaseDao;

public class W_zanDao {
	/**
	 * ͨ��΢��id�͵����˵��ǳƻ�ȡ��΢��
	 * @param weiboId
	 * @param zanName
	 * @return
	 */
	public List<W_zan> queryByNameAndId(int weiboId,String zanName) {
		return (List<W_zan>) BaseDao.select("select * from W_ZAN where WEIBOID=? and ZANNAME = ?",W_zan.class, weiboId,zanName);
	}
	/**
	 * ��ӵ��޼�¼
	 * @param weiboId
	 * @param zanName
	 * @return
	 */
	public boolean addZan(int weiboId,String zanName) {
		return BaseDao.execute("insert into W_ZAN values(null,?,?)", weiboId,zanName)>0;
	}
	
	/**
	 * ɾ�����޼�¼
	 */
	public boolean deleteZan(int weiboId,String zanName) {
		return BaseDao.execute("delete from W_ZAN where WEIBOID = ? and ZANNAME = ?", weiboId,zanName)>0;
	}
	
	/***
	 * ��ѯĳ�˵��޹�������΢��
	 * @param nickname ��¼���ǳ�
	 * @return
	 */
	public List<W_weibo> queryMyZAN(String nickname){
		return (List<W_weibo>)BaseDao.select("select * from ZAN_VIEW where ZANNAME=?", W_weibo.class, nickname);
	}
}
