package com.qzz.weibo.dao;

import java.util.List;

import com.qzz.weibo.entity.W_relation;
import com.qzz.weibo.util.BaseDao;

public class W_relationDao {
	/**
	 * ���������ҹ�ע���˵���Ϣ
	 * @param aName
	 * @return
	 */
	public List<W_relation> queryMyAllPointer(String aName) {
		return (List<W_relation>) BaseDao.select("select * from relation_view where PERSONANAME =?", W_relation.class, aName);
	}
}
