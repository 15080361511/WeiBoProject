package com.qzz.weibo.dao;

import com.qzz.weibo.entity.W_users;
import com.qzz.weibo.util.BaseDao;

public class W_usersDao {
	// ��֤�Ƿ�����û��ʺ���Ϣ
	public Object RegisterPhone(String phone) {
		return BaseDao.getFirst("select count(1) from w_users where username=? and usertype='�û�'", phone);
	}
	//��ѯ����Ա��Ϣ�Ƿ����
	public Object selAdmin(String phone) {
		return BaseDao.getFirst("select count(1) from w_users where username=? and usertype='����Ա'", phone);
	}

	// ����û���Ϣ
	public boolean addUsers(String phone, String pwd) {
		return BaseDao.execute("insert into w_users values(null,?,?,'�û�')", phone, pwd) > 0;
	}
	//��ȡ�û�������Ϣ
	public Object queryUserpwdByName(String name) {
		return BaseDao.getFirst("select userpwd from W_users where username=?", name);
	}
}
