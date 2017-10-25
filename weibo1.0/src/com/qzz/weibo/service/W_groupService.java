package com.qzz.weibo.service;

import java.util.List;

import com.qzz.weibo.dao.W_groupDao;
import com.qzz.weibo.entity.W_group;
import com.qzz.weibo.util.BaseDao;

public class W_groupService {
	private W_groupDao groupDao = new W_groupDao();
	/**
	 * ͨ���ǳƲ��ҷ���
	 * @param nickName
	 * @return
	 */
	public List<W_group> queryGroupByName(String nickName) {
		return groupDao.queryGroupByName(nickName);
	}
	/**
	 * ��������
	 * @param nickName
	 * @param groupName
	 * @return
	 */
	public boolean addGroup(String nickName,String groupName) {
		return groupDao.addGroup(nickName, groupName);
	}
}
