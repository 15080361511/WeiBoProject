package com.qzz.weibo.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.qzz.weibo.dao.W_weiboDao;
import com.qzz.weibo.entity.W_users;
import com.qzz.weibo.entity.W_weibo;
import com.qzz.weibo.service.W_weiboService;

public class weiboTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		W_weibo w = new W_weibo();
		w.setCONTENT("���ڷ�΢���ˣ��ÿ�ɭ");
		w.setSENDNAME("��������");
		Date date = new Date();
		String publishtime = sdf.format(date);
		w.setPUBLISHTIME(publishtime);
		
		
		
		W_weiboService ws = new W_weiboService();
		ws.addWeiBo(w);
		List<W_weibo> list = ws.queryMyWb();
		System.out.println(list.size());
		for (W_weibo w_weibo : list) {
			System.out.println(list);
		}		
		
		
		System.out.println("ʱ��"+new Date().getTime()+"");
		
		
		
		
		
	}

}
