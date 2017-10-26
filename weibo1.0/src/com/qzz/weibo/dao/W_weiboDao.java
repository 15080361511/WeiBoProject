package com.qzz.weibo.dao;

import java.util.List;

import com.qzz.weibo.entity.W_type;
import com.qzz.weibo.entity.W_users;
import com.qzz.weibo.entity.W_weibo;
import com.qzz.weibo.util.BaseDao;
import com.qzz.weibo.util.PageData;

public class W_weiboDao {
	/**
	 * �����ҵ�����΢������
	 * @return
	 */
	public List<W_weibo> queryAllWb() {
		return (List<W_weibo>)BaseDao.select("select * from WB_VIEW", W_weibo.class, null);
	}
	
	/**
	 * ͨ���û�������΢��
	 */
	public List<W_weibo> queryWbByName(String sendName) {
		return (List<W_weibo>)BaseDao.select("select * from WB_VIEW where SENDNAME = ?", W_weibo.class, sendName);
	}
	
	/**
	 * ͨ��id����΢��
	 */
	public List<W_weibo> queryWbById(int id) {
		return (List<W_weibo>)BaseDao.select("select * from WB_VIEW where WEIBOID = ?", W_weibo.class, id);
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
	 * ����΢����΢�����в���һ����¼
	 * @param w ΢������
	 * @return
	 */
	public boolean addWeiBo(W_weibo w) {
		String sql = "insert into W_WEIBO values(null,null,?,?,?,?,0,0,0,?,'��',null,0,null)";
		return BaseDao.execute(sql, w.getCONTENT(),w.getSENDNAME(),w.getIMAGE(),w.getPUBLISHTIME(),w.getTYPEID())>0;
	}
	
	/***
	 * ת��΢����΢�����в���һ����¼
	 * @param w ΢������
	 * @return
	 */
	public boolean forwardWeiBo(W_weibo w) {
		String sql = "insert into W_WEIBO values(null,?,?,?,null,?,0,0,0,?,'��',?,0,?)";
		return BaseDao.execute(sql,w.getFWDCONTENT(), w.getCONTENT(),w.getSENDNAME(),w.getPUBLISHTIME(),w.getTYPEID(),w.getFWDWEIBOID(),w.getBASEID())>0;
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
	 *  ����΢�����ͱ�Ż�ȡ�������΢��
	 *
	 * @return
	*/
	public  List<W_weibo> queryWebBytype(int contenttypeid){
		String sql="select * from WB_VIEW where TYPEID =? ";
		return (List<W_weibo>)BaseDao.select(sql, W_weibo.class, contenttypeid);
	}
	
	/**
	 *  ����΢�����ͱ�Ż�ȡ�ҵ��������΢��
	 *
	 * @return
	*/
	public  List<W_weibo> queryMyWebBytype(int contenttypeid,String sendName){
		String sql="select * from WB_VIEW where TYPEID =? and SENDNAME =?";
		return (List<W_weibo>)BaseDao.select(sql, W_weibo.class, contenttypeid,sendName);
	}
	
	/**
	 * �޸�΢�����ղء�ת�������ۡ�������
	 * @param weibo
	 * @return
	 */
	public boolean updateWeiboById(W_weibo weibo) {
		return BaseDao.execute("update W_weibo set COLLECTNUM=?,FORWARDNUM=?, COMMENTNUM = ?,ZANNUM=? where WEIBOID=?", weibo.getCOLLECTNUM(),weibo.getFORWARDNUM(),weibo.getCOMMENTNUM(),weibo.getZANNUM(),weibo.getWEIBOID())>0;
	}
	/**
	 * ģ����ѯ
	 * @param word
	 * @return
	 */
	public List<W_weibo> queryWbByWord(String word,String sendName) {
		return (List<W_weibo>) BaseDao.select("select * from WB_VIEW where SENDNAME = ? and CONTENT like ?", W_weibo.class,sendName, "%"+word+"%");
	}
	/**
	 * ͨ���ǳƲ����ҵ�΢����
	 * @param sendName
	 * @return
	 */
	public Object queryMyWbNum(String sendName) {
		return BaseDao.getFirst("select count(1) from WB_VIEW where SENDNAME =?", sendName);
	}
}
