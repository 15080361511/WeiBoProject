package com.qzz.weibo.service;

import java.util.List;

import com.qzz.weibo.dao.W_weiboDao;
import com.qzz.weibo.entity.W_type;
import com.qzz.weibo.entity.W_weibo;
import com.qzz.weibo.util.BaseDao;
import com.qzz.weibo.util.PageData;

public class W_weiboService {
	private W_weiboDao weiboDao = new W_weiboDao();
	
	/**
	 * �����ҵ�����΢������
	 * @return
	 */
	public List<W_weibo> queryAllWb() {
		return weiboDao.queryAllWb();
	}
	
	/**
	 * ͨ���û�������΢��
	 */
	public List<W_weibo> queryWbByName(String sendName) {
		return weiboDao.queryWbByName(sendName);
	}
	
	/**
	 * ͨ��id����΢��
	 */
	public List<W_weibo> queryWbById(int id) {
		return weiboDao.queryWbById(id);
	}
	
	/***
	 * ����΢�����һ�����ݵ�΢������
	 * @param w ΢������
	 * @return
	 */
	public boolean addWeiBo(W_weibo w) {
		return weiboDao.addWeiBo(w);
	}
	
	/***
	 * ת��΢�����һ�����ݵ�΢������
	 * @param w ΢������
	 * @return
	 */
	public boolean forwardWeiBo(W_weibo w) {
		return weiboDao.forwardWeiBo(w);
	}
	
	/***
	 * �����������Ʋ�ѯ���ͱ��
	 * @param typeName
	 * @return
	 */
	public int queryTypeIdByName(String typeName) {
		return weiboDao.queryTypeIdByName(typeName);
	}
	
	/**
	 *  ����΢�����ͱ�Ż�ȡ�������΢��
	 *
	 * @return
	*/
	public List<W_weibo> queryWebBytype(int contenttypeid){
		return weiboDao.queryWebBytype(contenttypeid);
	}
	
	/**
	 *  ����΢�����ͱ�Ż�ȡ�ҵ��������΢��
	 *
	 * @return
	*/
	public  List<W_weibo> queryMyWebBytype(int contenttypeid,String sendName){
		String sql="select * from WB_VIEW where TYPEID =? and SENDNAME =?";
		return weiboDao.queryMyWebBytype(contenttypeid, sendName);
	}
	
	/**
	 * �޸�΢�����ղء�ת�������ۡ�������
	 * @param weibo
	 * @return
	 */
	public boolean updateWeiboById(W_weibo weibo) {
		return weiboDao.updateWeiboById(weibo);
	}
	/**
	 * ģ����ѯ
	 * @param word
	 * @return
	 */
	public List<W_weibo> queryWbByWord(String word,String sendName) {
		return weiboDao.queryWbByWord(word,sendName);
	}
}
