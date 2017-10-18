package com.qzz.weibo.dao;

import java.util.List;

import com.qzz.weibo.entity.W_collect;
import com.qzz.weibo.util.BaseDao;

public class W_collectDao {
	/**
	 * ����ղؼ�¼
	 * @param collect
	 * @return
	 */
	public boolean addCollect(W_collect collect) {
		return BaseDao.execute("insert into W_COLLECT values(null,?,?,?)", collect.getWEIBOID(),collect.getUSERNAME(),collect.getCOLLECTTIME())>0;
	}
	
	/**
	 * ͨ��΢��id���ղ��˵��ǳ�����ѯ΢��
	 * @param collect
	 * @return
	 */
	public List<W_collect> queryCollect(W_collect collect){
		return (List<W_collect>) BaseDao.select("select * from W_COLLECT where WEIBOID=? and USERNAME = ?", W_collect.class, collect.getWEIBOID(),collect.getUSERNAME());
	}
	
	/**
	 * ɾ�����޼�¼
	 */
	public boolean deleteCollect(W_collect collect) {
		return BaseDao.execute("delete from W_COLLECT where WEIBOID = ? and USERNAME = ?", collect.getWEIBOID(),collect.getUSERNAME())>0;
	}
}
