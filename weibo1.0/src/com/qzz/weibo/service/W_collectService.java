package com.qzz.weibo.service;

import java.util.List;

import com.qzz.weibo.dao.W_collectDao;
import com.qzz.weibo.entity.W_collect;
import com.qzz.weibo.util.BaseDao;

public class W_collectService {
	private W_collectDao collectDao = new W_collectDao();
	/**
	 * ����ղؼ�¼
	 * @param collect
	 * @return
	 */
	public boolean addCollect(W_collect collect) {
		return collectDao.addCollect(collect);
	}
	/**
	 * ͨ��΢��id���ղ��˵��ǳ�����ѯ΢��
	 * @param collect
	 * @return
	 */
	public List<W_collect> queryCollect(W_collect collect){
		return collectDao.queryCollect(collect);
	}
	/**
	 * ɾ�����޼�¼
	 */
	public boolean deleteCollect(W_collect collect) {
		return collectDao.deleteCollect(collect);
	}
}
