package com.qzz.weibo.service;

import java.util.List;

import com.qzz.weibo.dao.W_relationDao;
import com.qzz.weibo.entity.W_relation;
import com.qzz.weibo.util.BaseDao;

public class W_relationService {
	private W_relationDao relationDao = new W_relationDao();
	/**
	 * ���������ҹ�ע���˵���Ϣ
	 * @param aName
	 * @return
	 */
	public List<W_relation> queryMyAllPointer(String aName) {
		return relationDao.queryMyAllPointer(aName);
	}
}
