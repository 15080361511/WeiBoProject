package com.qzz.weibo.dao;

import java.util.List;

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
}
