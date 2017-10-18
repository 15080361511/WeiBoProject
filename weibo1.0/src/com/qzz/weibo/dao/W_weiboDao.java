package com.qzz.weibo.dao;

import java.util.List;

import com.qzz.weibo.entity.W_type;
import com.qzz.weibo.entity.W_users;
import com.qzz.weibo.entity.W_weibo;
import com.qzz.weibo.util.BaseDao;

public class W_weiboDao {
	/**
	 * �����ҵ�����΢������
	 * @return
	 */
	public List<W_weibo> queryMyWb() {
		return (List<W_weibo>)BaseDao.select("select * from WEIBO_VIEW", W_weibo.class, null);
	}
	
	/**
	 * ͨ���û�������΢��
	 */
	public List<W_weibo> queryWbByName(String sendName) {
		return (List<W_weibo>)BaseDao.select("select * from WEIBO_VIEW where SENDNAME = ?", W_weibo.class, sendName);
	}
	
	/**
	 * ͨ��id����΢��
	 */
	public List<W_weibo> queryWbById(int id) {
		return (List<W_weibo>)BaseDao.select("select * from WEIBO_VIEW where WEIBOID = ?", W_weibo.class, id);
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
		String sql = "insert into W_WEIBO values(null,null,?,?,?,?,0,0,0,?,'��',null,0)";
		return BaseDao.execute(sql, w.getCONTENT(),w.getSENDNAME(),w.getIMAGE(),w.getPUBLISHTIME(),w.getTYPEID())>0;
	}
	
	/***
	 * �����������Ʋ�ѯ���ͱ��
	 * @param typeName
	 * @return
	 */
	public int queryTypeIdByName(String typeName) {
		String sql = "select * from W_TYPE where TYPENAME = ?";
		List<W_type> list = (List<W_type>)BaseDao.select(sql, W_type.class, typeName);
		return list.get(0).getTYPEID();
	}
	
	/**
	 * �޸�΢�����ղء�ת�������ۡ�������
	 * @param weibo
	 * @return
	 */
	public boolean updateWeiboById(W_weibo weibo) {
		return BaseDao.execute("update W_weibo set COLLECTNUM=?,FORWARDNUM=?, COMMENTNUM = ?,ZANNUM=? where WEIBOID=?", weibo.getCOLLECTNUM(),weibo.getFORWARDNUM(),weibo.getCOMMENTNUM(),weibo.getZANNUM(),weibo.getWEIBOID())>0;
	}
}
