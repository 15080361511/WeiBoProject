package com.qzz.weibo.dao;

import java.util.List;

import com.qzz.weibo.entity.W_users;
import com.qzz.weibo.entity.W_weibo;
import com.qzz.weibo.util.BaseDao;

public class W_weiboDao {
	/**
	 * �����ҵ�����΢������
	 * @return
	 */
	public List<W_weibo> queryMyWb() {
		return (List<W_weibo>)BaseDao.select("select * from W_WEIBO", W_weibo.class, null);
	}
	
	/**
	 * ���������û�
	 * @return
	 */
	public List<W_users> queryAllUsers() {
		List<W_users> list = (List<W_users>)BaseDao.select("select * from W_users", W_users.class, null);
		System.out.println(list);
		return list;
	}
	
	/***
	 * ��΢�����в���һ����¼
	 * @param w ΢������
	 * @return
	 */
	public boolean addWeiBo(W_weibo w) {
		String sql = "insert into W_WEIBO values(null,null,?,?,?,?,0,0,0,1,'��',null)";
		return BaseDao.execute(sql, w.getCONTENT(),w.getSENDNAME(),w.getIMAGE(),w.getPUBLISHTIME())>0;
	}
}
