package com.qzz.weibo.service;

import java.util.List;

import com.qzz.weibo.dao.W_weiboDao;
import com.qzz.weibo.entity.W_weibo;
import com.qzz.weibo.util.BaseDao;

public class W_weiboService {
	private W_weiboDao weiboDao = new W_weiboDao();
	
	/**
	 * �����ҵ�����΢������
	 * @return
	 */
	public List<W_weibo> queryMyWb() {
		return weiboDao.queryMyWb();
	}
	
	/***
	 * ���һ�����ݵ�΢������
	 * @param w ΢������
	 * @return
	 */
//	public boolean addWeiBo(W_weibo w) {
//		return weiboDao.addWeiBo(w);
//	}
}
