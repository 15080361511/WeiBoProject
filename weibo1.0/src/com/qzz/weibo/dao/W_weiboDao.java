package com.qzz.weibo.dao;

import java.util.List;

import com.qzz.weibo.entity.W_type;
import com.qzz.weibo.entity.W_users;
import com.qzz.weibo.entity.W_weibo;
import com.qzz.weibo.util.BaseDao;

public class W_weiboDao {
	/**
	 * 查找我的所有微博内容
	 * @return
	 */
	public List<W_weibo> queryMyWb() {
		return (List<W_weibo>)BaseDao.select("select * from WB_VIEW", W_weibo.class, null);
	}
	
	/**
	 * 通过用户名查找微博
	 */
	public List<W_weibo> queryWbByName(String sendName) {
		return (List<W_weibo>)BaseDao.select("select * from WB_VIEW where SENDNAME = ?", W_weibo.class, sendName);
	}
	
	/**
	 * 通过id查找微博
	 */
	public List<W_weibo> queryWbById(int id) {
		return (List<W_weibo>)BaseDao.select("select * from WB_VIEW where WEIBOID = ?", W_weibo.class, id);
	}
	/**
	 * 查找所有用户
	 * @return
	 */
	public List<W_users> queryAllUsers() {
		List<W_users> list = (List<W_users>)BaseDao.select("select * from W_users", W_users.class, null);
		System.out.println(list);
		return list;
	}
	
	/***
	 * 发布微博向微博表中插入一条记录
	 * @param w 微博对象
	 * @return
	 */
	public boolean addWeiBo(W_weibo w) {
		String sql = "insert into W_WEIBO values(null,null,?,?,?,?,0,0,0,?,'否',null,0,?)";
		return BaseDao.execute(sql, w.getCONTENT(),w.getSENDNAME(),w.getIMAGE(),w.getPUBLISHTIME(),w.getTYPEID())>0;
	}
	
	/***
	 * 转发微博向微博表中插入一条记录
	 * @param w 微博对象
	 * @return
	 */
	public boolean forwardWeiBo(W_weibo w) {
		String sql = "insert into W_WEIBO values(null,?,?,?,null,?,0,0,0,?,'是',?,0)";
		return BaseDao.execute(sql,w.getFWDCONTENT(), w.getCONTENT(),w.getSENDNAME(),w.getPUBLISHTIME(),w.getTYPEID(),w.getFWDWEIBOID())>0;
	}
	
	/***
	 * 根据类型名称查询类型编号
	 * @param typeName
	 * @return
	 */
	public int queryTypeIdByName(String typeName) {
		String sql = "select * from W_TYPE where TYPENAME = ?";
		List<W_type> list = (List<W_type>)BaseDao.select(sql, W_type.class, typeName);
		return list.get(0).getTYPEID();
	}
	
	/**
	 * 修改微博的收藏、转发、评论、点赞数
	 * @param weibo
	 * @return
	 */
	public boolean updateWeiboById(W_weibo weibo) {
		return BaseDao.execute("update W_weibo set COLLECTNUM=?,FORWARDNUM=?, COMMENTNUM = ?,ZANNUM=? where WEIBOID=?", weibo.getCOLLECTNUM(),weibo.getFORWARDNUM(),weibo.getCOMMENTNUM(),weibo.getZANNUM(),weibo.getWEIBOID())>0;
	}
}
