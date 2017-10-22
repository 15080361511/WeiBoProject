package com.qzz.weibo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.qzz.weibo.dao.W_collectDao;
import com.qzz.weibo.entity.W_collect;
import com.qzz.weibo.entity.W_comment;
import com.qzz.weibo.entity.W_friend;
import com.qzz.weibo.entity.W_reply;
import com.qzz.weibo.entity.W_userinfo;
import com.qzz.weibo.entity.W_weibo;
import com.qzz.weibo.entity.W_zan;
import com.qzz.weibo.service.W_UserInfoService;
import com.qzz.weibo.service.W_collectService;
import com.qzz.weibo.service.W_commentService;
import com.qzz.weibo.service.W_friendService;
import com.qzz.weibo.service.W_replyService;
import com.qzz.weibo.service.W_weiboService;
import com.qzz.weibo.service.W_zanService;
import com.qzz.weibo.util.DataUtil;

import sun.nio.cs.ext.ISO_8859_11;

/**
 * Servlet implementation class WeiBoServlet
 */
@WebServlet("/WeiBoServlet")
public class W_weiboServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private W_weiboService ws = new W_weiboService();
	private W_commentService wcs = new W_commentService();
	private W_zanService wzs = new W_zanService();
	private W_collectService cts = new W_collectService();
	private W_replyService rs = new W_replyService();
	private W_UserInfoService us = new W_UserInfoService();
	private W_friendService fs = new W_friendService();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public W_weiboServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		// �õ�΢�����ݵļ���
		List<W_weibo> list = new ArrayList<>();
		List<W_comment> list2 = new ArrayList<>();
		List<W_reply> replyList = new ArrayList<>();
		HttpSession session = request.getSession();
		// �ж�op��ֵ
		if (request.getParameter("op") != null) {
			String op = request.getParameter("op");
			// �����ҵ���ҳ���ҷ���������΢��
			if (op.equals("queryMyWb")) {
				
				String sendName = (String) session.getAttribute("username");
				// ����ѯ����΢��list�������
				list = ws.queryWbByName(sendName);
				request.setAttribute("list", list);
				request.setAttribute("sendName", sendName);
				request.getRequestDispatcher("my_home.jsp").forward(request, response);
			} else if (op.equals("homepage")) {
				String nickname="";
				nickname = session.getAttribute("username")+"";
				W_UserInfoService wus = new W_UserInfoService();
				W_userinfo userinfo = new W_userinfo();
				userinfo = wus.getUserInfoByNikeName(nickname).get(0);
				request.setAttribute("userinfo", userinfo);
				request.getRequestDispatcher("homepage.jsp").forward(request, response);

			} 
			else if (op.equals("queryAllWb")) {			
				list = ws.queryAllWb();
				request.setAttribute("list", list);
				request.getRequestDispatcher("mainpage.jsp").forward(request, response);
			}
			// op��ֵ����publish˵���ύ��������
			else if (op.equals("publish")) {
				Date date = new Date();
				String content = request.getParameter("content");
				String sendname = (String) request.getSession().getAttribute("username");
				String typeName = request.getParameter("type");
				int typeId = ws.queryTypeIdByName(typeName);
				W_weibo w = new W_weibo();
				w.setCONTENT(content);
				w.setSENDNAME(sendname);
				w.setPUBLISHTIME(sdf.format(date));
				w.setIMAGE(DataUtil.imgname);
				w.setTYPEID(typeId);
				if (ws.addWeiBo(w)) {
					response.getWriter().print(
							"<script language='javascript'>alert('�����ɹ�');parent.location.href='WeiBoServlet?op=homepage'</script>");
				}
				// ͨ��id���ҵ���΢������ת����΢������ϸ��Ϣҳ��,���۹���
			} else if (op.equals("queryWbById")) {
				int weiboId = 0;
				// �������۳ɹ���־
				String successFlag = new String(request.getParameter("cmsuccess").getBytes("ISO-8859-1"), "UTF-8");
				// ���û������
				if (successFlag.equals("no")) {
					weiboId = Integer.parseInt((String) request.getParameter("weiboid"));
					// ����������۱�־Ϊyes������������
				} else if (successFlag.equals("yes")) {
					weiboId = Integer.parseInt((String) request.getParameter("weiboId"));
					String nikeName = (String) session.getAttribute("username");
					String commentContent = new String(request.getParameter("commentContent").getBytes("ISO-8859-1"),
							"UTF-8");
					// String commentContent = request.getParameter("commentContent");
					// String commentContent = request.getParameter("commentContent");
					// ��ȡ��ǰϵͳʱ��
					String commentTime = sdf.format(new Date());			
					W_comment comment = new W_comment(1, weiboId, nikeName, commentContent, commentTime,"22");
					wcs.addComment(comment);
				}				
				list = ws.queryWbById(weiboId);
				W_weibo detailWb = list.get(0);
				// ��ȡ����΢����������������
				list2 = wcs.queryCmById(weiboId);
				// �õ������۵����лظ�����replyList��
				replyList = rs.queryAllReply();
				request.setAttribute("replyList", replyList);
				request.setAttribute("list2", list2);
				request.setAttribute("detailWb", detailWb);
				request.getRequestDispatcher("more.jsp").forward(request, response);
			} else if (op.equals("zan")) {
				// ͨ����������΢��ID�͵����˵��ǳƲ������ݿⲢ�Ҵ洢��zanlist��
				int weiboId = Integer.parseInt((String) request.getParameter("weiboid"));
				String zanName = (String) session.getAttribute("username");
				List<W_zan> zanList = wzs.queryByNameAndId(weiboId, zanName);
				// ��ȡ����΢��
				W_weibo wei = ws.queryWbById(weiboId).get(0);
				// ͨ��zanList�ĳ������жϣ����������Ƿ�Ϊ��
				if (zanList.size() == 0) {
					// ����������Ϊ��ʱ���ͶԸ��ǳƺ�΢�����һ�����޼�¼
					wzs.addZan(weiboId, zanName);
					// ��������һ
					wei.setZANNUM(wei.getZANNUM() + 1);
				} else {
					// ����������ݲ�Ϊ�գ���ɾ���ü�¼����ȡ�����޹���
					wzs.deleteZan(weiboId, zanName);
					// ��������һ
					wei.setZANNUM(wei.getZANNUM() - 1);
				}
				// �޸ı���΢���ĵ�����
				ws.updateWeiboById(wei);
				// ͨ���ǳƲ���΢��
				list = ws.queryWbByName(zanName);
				request.setAttribute("list", list);
				request.setAttribute("sendName", zanName);
				request.getRequestDispatcher("my_home.jsp").forward(request, response);
			}else if (op.equals("collect")) {
				//ͨ����������΢��ID���ղ��˵��ǳƲ������ݿⲢ�Ҵ洢��collectList��
				String msg="";
				int weiboId = Integer.parseInt((String) request.getParameter("weiboid"));
				String collectName = (String) session.getAttribute("username");
				String flag = request.getParameter("flag");
				String collectTime = sdf.format(new Date());
				W_collect collect = new W_collect(1, weiboId, collectName, collectTime);
				List<W_collect> collectList = cts.queryCollect(collect);
				// ��ȡ����΢��
				W_weibo wei = ws.queryWbById(weiboId).get(0);
				// ͨ��zanList�ĳ������жϣ����������Ƿ�Ϊ��
				if (collectList.size() == 0) {
					// ����������Ϊ��ʱ���ͶԸ��ǳƺ�΢�����һ���ղؼ�¼
					cts.addCollect(collect);
					msg="�ղسɹ�";
					//�ղ�����һ
					wei.setCOLLECTNUM(wei.getCOLLECTNUM()+1);
				}else {
					//����������ݲ�Ϊ�գ���ɾ���ü�¼����ȡ���ղع���
					cts.deleteCollect(collect);
					msg="ȡ���ղ�";
					//�ղ�����һ
					wei.setCOLLECTNUM(wei.getCOLLECTNUM()-1);
				}
				//�޸ı���΢�����ղ���
					ws.updateWeiboById(wei);
				//ͨ���ǳƲ���΢��	
					if(flag.equals("1")) {
						list = ws.queryAllWb();
						request.setAttribute("list", list);
						if(msg.equals("�ղسɹ�"))
						{
							
						response.getWriter().print("<script language='javascript'>location.href='WeiBoServlet?op=queryAllWb';alert('�ղسɹ�');</script>");
//						request.getRequestDispatcher("mainpage.jsp").forward(request, response);
						}
						else {
						response.getWriter().print("<script language='javascript'>alert('ȡ���ղ�');location.href='WeiBoServlet?op=queryAllWb'</script>");
						}
					}
					else {
						list = ws.queryWbByName(collectName);
						request.setAttribute("list", list);
						request.setAttribute("sendName", collectName);
						request.getRequestDispatcher("my_home.jsp").forward(request, response);
					}
				
			}
			else if (op.equals("dianzan")) {
				//ͨ����������΢��ID�͵����˵��ǳƲ������ݿⲢ�Ҵ洢��zanlist��
				int weiboId = Integer.parseInt((String) request.getParameter("weiboid"));
				String zanName = (String) session.getAttribute("username");
				List<W_zan> zanList = wzs.queryByNameAndId(weiboId, zanName);
				// ��ȡ����΢��
				W_weibo wei = ws.queryWbById(weiboId).get(0);
				// ͨ��zanList�ĳ������жϣ����������Ƿ�Ϊ��
				if (zanList.size() == 0) {
					// ����������Ϊ��ʱ���ͶԸ��ǳƺ�΢�����һ�����޼�¼
					wzs.addZan(weiboId, zanName);
					// ��������һ
					wei.setZANNUM(wei.getZANNUM() + 1);
				} else {
					// ����������ݲ�Ϊ�գ���ɾ���ü�¼����ȡ�����޹���
					wzs.deleteZan(weiboId, zanName);
					// ��������һ
					wei.setZANNUM(wei.getZANNUM() - 1);
				}
				//�޸ı���΢���ĵ�����
					ws.updateWeiboById(wei);
				//ͨ���ǳƲ���΢��	
					list = ws.queryAllWb();
					request.setAttribute("list", list);
					request.getRequestDispatcher("mainpage.jsp").forward(request, response);
			//ɾ������
			}else if (op.equals("deleteComment")) {
				//���մ�����������id��ɾ��������
				int commentId = Integer.parseInt((String) request.getParameter("commentId"));
				wcs.deleteCmById(commentId);

				// ��ȡ����΢��
				int weiboId = Integer.parseInt((String) request.getParameter("cmweiboId"));
				list = ws.queryWbById(weiboId);

				W_weibo detailWb = list.get(0);
				// ��ȡ����΢����������������
				list2 = wcs.queryCmById(weiboId);
				// �õ������۵����лظ�����replyList��
				replyList = rs.queryAllReply();
				request.setAttribute("replyList", replyList);
				request.setAttribute("list2", list2);
				request.setAttribute("detailWb", detailWb);
				request.getRequestDispatcher("more.jsp").forward(request, response);
			} else if (op.equals("forward")) {
				int weiboId = Integer.parseInt(request.getParameter("weiboid"));
				// String content = request.getParameter("content");
				String content = new String(request.getParameter("content").getBytes("ISO-8859-1"), "UTF-8");
				String sendname = (String) request.getSession().getAttribute("username");

				Date date = new Date();
				W_weibo wb = ws.queryWbById(weiboId).get(0);
				W_weibo oldwb = ws.queryWbById(wb.getBASEID()).get(0);
				W_weibo newwb = new W_weibo();
				newwb.setFWDCONTENT(oldwb.getCONTENT());
				newwb.setCONTENT(content);
				newwb.setSENDNAME(sendname);
				newwb.setPUBLISHTIME(sdf.format(date));
				newwb.setTYPEID(oldwb.getTYPEID());
				newwb.setFWDWEIBOID(weiboId);
				newwb.setBASEID(oldwb.getBASEID());
				if (ws.forwardWeiBo(newwb)) {
					response.getWriter().print(
							"<script language='javascript'>alert('ת���ɹ�');parent.location.href='WeiBoServlet?op=homepage'</script>");
				}
			} else if (op.equals("reply")) {
				// ���մ�������ֵ
				int commentId = Integer.parseInt(request.getParameter("commentId"));
				String replyerA = new String(request.getParameter("replyerA").getBytes("ISO-8859-1"), "UTF-8");
				String replyerB = new String(request.getParameter("replyerB").getBytes("ISO-8859-1"), "UTF-8");
				String replyContent = new String(request.getParameter("replyContent").getBytes("ISO-8859-1"), "UTF-8");
				String replyTime = sdf.format(new Date());
				W_reply reply = new W_reply(1, commentId, replyerA, replyerB, replyContent, replyTime,"SDFS");
				// ����service���ӻظ�
				rs.addReply(reply);
				// �õ������۵����лظ�����replyList��

				replyList = rs.queryAllReply();
				// ��ȡ����΢��
				int weiboId = Integer.parseInt((String) request.getParameter("weiboid"));
				list = ws.queryWbById(weiboId);

				W_weibo detailWb = list.get(0);
				// ��ȡ����΢����������������
				list2 = wcs.queryCmById(weiboId);

				request.setAttribute("list2", list2);
				request.setAttribute("replyList", replyList);
				request.setAttribute("detailWb", detailWb);
				request.getRequestDispatcher("more.jsp").forward(request, response);
			}
			else if (op.equals("querymycoll"))//��ѯ���ղع���΢��
			{				
				String nickname = (String) session.getAttribute("username");
				List<W_weibo> colllist = new W_collectService().queryMyColl(nickname);
				request.setAttribute("colllist", colllist);
				request.getRequestDispatcher("collectpage.jsp").forward(request, response);
			}
			else if (op.equals("querymyzan"))//��ѯ�ҵ��޹���΢��
			{				
				String nickname = (String) session.getAttribute("username");
				List<W_weibo> zanlist = new W_zanService().queryMyZAN(nickname);
				request.setAttribute("zanlist", zanlist);
				request.getRequestDispatcher("zanpage.jsp").forward(request, response);
			}else if (op.equals("chatpage")) {
				String nickName = (String) session.getAttribute("username");
				List<W_friend> friendList = fs.queryMyFriend(nickName);
				List<W_userinfo> myList = us.getUserInfoByNikeName(nickName);
				System.out.println(friendList);
				request.setAttribute("friendList", friendList);
				request.setAttribute("mytouxiang", myList.get(0).getTOUXIANG());
				request.getRequestDispatcher("chat.jsp").forward(request, response);
			}
		}

	}

}
