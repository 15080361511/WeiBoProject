package com.qzz.weibo.dao;

import java.util.List;

import com.qzz.weibo.entity.W_friend;
import com.qzz.weibo.util.BaseDao;

public class W_friendDao {
	/**
	 * ͨ���ǳƲ����ҵĺ���
	 * @param nickName
	 * @return
	 */
	public List<W_friend> queryMyFriend(String nickName) {
		return (List<W_friend>) BaseDao.select("select * from FRIEND_VIEW where FRIENDOWNERNAME = ?", W_friend.class, nickName);
	}
}
