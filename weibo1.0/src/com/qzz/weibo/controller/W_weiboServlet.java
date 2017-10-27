package com.qzz.weibo.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;
import com.qzz.weibo.dao.W_collectDao;
import com.qzz.weibo.entity.W_collect;
import com.qzz.weibo.entity.W_comment;
import com.qzz.weibo.entity.W_friend;
import com.qzz.weibo.entity.W_group;
import com.qzz.weibo.entity.W_message;
import com.qzz.weibo.entity.W_relation;
import com.qzz.weibo.entity.W_reply;
import com.qzz.weibo.entity.W_type;
import com.qzz.weibo.entity.W_userinfo;
import com.qzz.weibo.entity.W_weibo;
import com.qzz.weibo.entity.W_zan;
import com.qzz.weibo.service.W_UserInfoService;
import com.qzz.weibo.service.W_collectService;
import com.qzz.weibo.service.W_commentService;
import com.qzz.weibo.service.W_friendService;
import com.qzz.weibo.service.W_groupService;
import com.qzz.weibo.service.W_messageService;
import com.qzz.weibo.service.W_relationService;
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
	private W_messageService ms = new W_messageService();
	private W_groupService gs = new W_groupService();
	private W_relationService relationService = new W_relationService();

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
		PrintWriter out = response.getWriter();
		// �ж�op��ֵ
		if (request.getParameter("op") != null) {
			String op = request.getParameter("op");
			// �����ҵ���ҳ���ҷ���������΢��
			if (op.equals("queryMyWb")) {

				String sendName = (String) session.getAttribute("username");

				// ����õ����ӷ����־
				if (request.getParameter("addGroup") != null) {
//					String groupName = new String(request.getParameter("groupName").getBytes("ISO-8859-1"), "UTF-8");
					String groupName = request.getParameter("groupName");
					gs.addGroup(sendName, groupName);
				}
				// ��ѯ����΢��list
				if (request.getParameter("queryWbBy") == null) {
					list = ws.queryWbByName(sendName);
				} else if (request.getParameter("queryWbBy").equals("type")) {
					// �ҵ���ҳͨ�����Ͳ�ѯ
					int typeId = Integer.parseInt(request.getParameter("typeId"));
					list = ws.queryMyWebBytype(typeId,sendName);
				} else if (request.getParameter("queryWbBy").equals("word")) {
					// �ҵ���ҳģ����ѯ΢������
					String word = request.getParameter("serchContent");
					list = ws.queryWbByWord(word, sendName);
				}
				//�ҵĹ�ע����
				Object pointCount = relationService.queryPointerCount(sendName);
				//�ҵķ�˿����
				Object fansCount = relationService.queryFansCount(sendName);
				//�ҵ�΢������
				Object weiBoCount = ws.queryMyWbNum(sendName);
				// ��÷���list
				List<W_group> groupList = gs.queryGroupByName(sendName);
				request.setAttribute("groupList", groupList);
				W_userinfo myInfo = us.getUserInfoByNikeName(sendName);
				request.setAttribute("pointCount", pointCount);
				request.setAttribute("fansCount", fansCount);
				request.setAttribute("weiBoCount", weiBoCount);
				request.setAttribute("myInfo", myInfo);
				request.setAttribute("list", list);
				request.getRequestDispatcher("my_home.jsp").forward(request, response);
			} else if (op.equals("homepage")) {

				String nickname = "";
				nickname = session.getAttribute("username") + "";
				if(!nickname.equals("null")) {
				W_UserInfoService wus = new W_UserInfoService();
				W_userinfo userinfo = new W_userinfo();
				userinfo = wus.getUserInfoByNikeName(nickname);
				
				//�ҵĹ�ע����
				Object pointCount = relationService.queryPointerCount(nickname);
				//�ҵķ�˿����
				Object fansCount = relationService.queryFansCount(nickname);
				//�ҵ�΢������
				Object weiBoCount = ws.queryMyWbNum(nickname);
				
				request.setAttribute("pointCount", pointCount);
				request.setAttribute("fansCount", fansCount);
				request.setAttribute("weiBoCount", weiBoCount);
				request.setAttribute("userinfo", userinfo);
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
				}
				else {
					response.sendRedirect("index.jsp");
				}

			} else if (op.equals("queryAllWb")) {
				String nickName = (String) session.getAttribute("username");
				list = ws.queryAllWb();
				if (request.getParameter("queryWbBy")!=null) {
					if (request.getParameter("queryWbBy").equals("type")) {
						// ��ҳͨ�����Ͳ�ѯ
						int typeId = Integer.parseInt(request.getParameter("typeId"));
						list = ws.queryWebBytype(typeId);
					} else if (request.getParameter("queryWbBy").equals("word")) {
						// ��ҳģ����ѯ΢������
						String word = request.getParameter("serchContent");
						list = ws.queryWbByWord(word, nickName);
					}
				}
				request.setAttribute("list", list);
				request.getRequestDispatcher("mainpage.jsp").forward(request, response);
			}
			// op��ֵ����publish˵���ύ��������
			else if (op.equals("publish")) {
				Date date = new Date();
				String content = request.getParameter("content");
				content=content.replaceAll("������", "***");
				content=content.replaceAll("ɵ��", "**");
				content=content.replaceAll("С��", "**");
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
//				String successFlag = new String(request.getParameter("cmsuccess").getBytes("ISO-8859-1"), "UTF-8");
				String successFlag = request.getParameter("cmsuccess");
				// ���û������
				if (successFlag.equals("no")) {
					weiboId = Integer.parseInt((String) request.getParameter("weiboid"));
					// ����������۱�־Ϊyes������������
				} else if (successFlag.equals("yes")) {
					weiboId = Integer.parseInt((String) request.getParameter("weiboId"));
					String nikeName = (String) session.getAttribute("username");
//					String commentContent = new String(request.getParameter("commentContent").getBytes("ISO-8859-1"),"UTF-8");
					String commentContent = request.getParameter("commentContent");
					// String commentContent = request.getParameter("commentContent");
					// String commentContent = request.getParameter("commentContent");
					// ��ȡ��ǰϵͳʱ��
					String commentTime = sdf.format(new Date());
					W_comment comment = new W_comment(1, weiboId, nikeName, commentContent, commentTime, "22");
					wcs.addComment(comment);
				}
				
				//���΢���Ҳ��΢������ID,��ȡ����������͵�΢��
				List<W_weibo> list3 = new ArrayList<>();
				if (request.getParameter("contetypeid")!=null) {
					int typeid= Integer.parseInt(request.getParameter("contetypeid"));
					list3=ws.queryWebBytype(typeid);
					 int i=0;
					for(;i<list3.size();i++){
						 if(weiboId==(list3.get(i).getWEIBOID())){
							 list3.remove(i);	
						}
					}
				}
						
				list = ws.queryWbById(weiboId);
				W_weibo detailWb = list.get(0);
				// ��ȡ����΢����������������
				list2 = wcs.queryCmById(weiboId);
				// �õ������۵����лظ�����replyList��
				replyList = rs.queryAllReply();
				request.setAttribute("replyList", replyList);
				request.setAttribute("list2", list2);
				request.setAttribute("list3", list3);
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
				int zanNum = wei.getZANNUM();
				Gson g = new Gson();
				String jsonString = g.toJson(zanNum);
				// //������������仰����ζ����ͼ��һ�㲻��ҪJSON.parse
				// �����Ѿ������ص����ݱ����json����
				response.setContentType("application/json");
				out.print(jsonString);

			} else if (op.equals("collect")) {
				// ͨ����������΢��ID���ղ��˵��ǳƲ������ݿⲢ�Ҵ洢��collectList��
				String msg = "";
				int weiboId = Integer.parseInt(request.getParameter("weiboid"));
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
					msg = "�ղسɹ�";
					// �ղ�����һ
					wei.setCOLLECTNUM(wei.getCOLLECTNUM() + 1);
				} else {
					// ����������ݲ�Ϊ�գ���ɾ���ü�¼����ȡ���ղع���
					cts.deleteCollect(collect);
					msg = "ȡ���ղ�";
					// �ղ�����һ
					wei.setCOLLECTNUM(wei.getCOLLECTNUM() - 1);
				}
				// �޸ı���΢�����ղ���
				ws.updateWeiboById(wei);
				// ͨ���ǳƲ���΢��
				if (flag == null) {
					int collectNum = wei.getCOLLECTNUM();
					Gson g = new Gson();
					String jsonString = g.toJson(collectNum);
					// //������������仰����ζ����ͼ��һ�㲻��ҪJSON.parse
					// �����Ѿ������ص����ݱ����json����
					response.setContentType("application/json");
					out.print(jsonString);
					// } else if (flag.equals("1")) {
					// list = ws.queryAllWb();
					// request.setAttribute("list", list);
					// if (msg.equals("�ղسɹ�")) {
					//
					// response.getWriter().print(
					// "<script
					// language='javascript'>location.href='WeiBoServlet?op=queryAllWb';alert('�ղسɹ�');</script>");
					// // request.getRequestDispatcher("mainpage.jsp").forward(request, response);
					// } else {
					// response.getWriter().print(
					// "<script
					// language='javascript'>alert('ȡ���ղ�');location.href='WeiBoServlet?op=queryAllWb'</script>");
					// }
				}

			}
			// else if (op.equals("dianzan")) {
			// // ͨ����������΢��ID�͵����˵��ǳƲ������ݿⲢ�Ҵ洢��zanlist��
			// int weiboId = Integer.parseInt((String) request.getParameter("weiboid"));
			// String zanName = (String) session.getAttribute("username");
			// List<W_zan> zanList = wzs.queryByNameAndId(weiboId, zanName);
			// // ��ȡ����΢��
			// W_weibo wei = ws.queryWbById(weiboId).get(0);
			// // ͨ��zanList�ĳ������жϣ����������Ƿ�Ϊ��
			// if (zanList.size() == 0) {
			// // ����������Ϊ��ʱ���ͶԸ��ǳƺ�΢�����һ�����޼�¼
			// wzs.addZan(weiboId, zanName);
			// // ��������һ
			// wei.setZANNUM(wei.getZANNUM() + 1);
			// } else {
			// // ����������ݲ�Ϊ�գ���ɾ���ü�¼����ȡ�����޹���
			// wzs.deleteZan(weiboId, zanName);
			// // ��������һ
			// wei.setZANNUM(wei.getZANNUM() - 1);
			// }
			// // �޸ı���΢���ĵ�����
			// ws.updateWeiboById(wei);
			// // ͨ���ǳƲ���΢��
			// list = ws.queryAllWb();
			// request.setAttribute("list", list);
			// request.getRequestDispatcher("mainpage.jsp").forward(request, response);
			//
			// }
			// ɾ������
			else if (op.equals("deleteComment")) {
				// ���մ�����������id��ɾ��������
				int commentId = Integer.parseInt((String) request.getParameter("commentId"));
				wcs.deleteCmById(commentId);

				// ��ȡ����΢��
				int weiboId = Integer.parseInt((String) request.getParameter("cmweiboId"));
				list = ws.queryWbById(weiboId);

				W_weibo detailWb = list.get(0);
				// ��ȡ����΢����������������
				list2 = wcs.queryCmById(weiboId);
				// �õ������۵����лظ�����replyList��
				List<W_weibo> list3 = new ArrayList<>();
				int typeid= Integer.parseInt(request.getParameter("contetypeid"));
				list3=ws.queryWebBytype(typeid);
				 int i=0;
				for(;i<list3.size();i++){
					 if(weiboId==(list3.get(i).getWEIBOID())){
						 list3.remove(i);	
					}
				}		
				
				replyList = rs.queryAllReply();
				request.setAttribute("replyList", replyList);
				request.setAttribute("list2", list2);
				request.setAttribute("list3", list3);
				request.setAttribute("detailWb", detailWb);
				request.getRequestDispatcher("more.jsp").forward(request, response);
			} else if (op.equals("forward")) {
				int weiboId = Integer.parseInt(request.getParameter("weiboid"));
				// String content = request.getParameter("content");
//				String content = new String(request.getParameter("content").getBytes("ISO-8859-1"), "UTF-8");
				String content = request.getParameter("content");
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
//				String replyerA = new String(request.getParameter("replyerA").getBytes("ISO-8859-1"), "UTF-8");
//				String replyerB = new String(request.getParameter("replyerB").getBytes("ISO-8859-1"), "UTF-8");				
//				String replyContent = new String(request.getParameter("replyContent").getBytes("ISO-8859-1"), "UTF-8");
				String replyContent = request.getParameter("replyContent");
				String replyerA = request.getParameter("replyerA");
				String replyerB = request.getParameter("replyerB");
				String replyTime = sdf.format(new Date());
				W_reply reply = new W_reply(1, commentId, replyerA, replyerB, replyContent, replyTime, "SDFS");
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
				
				List<W_weibo> list3 = new ArrayList<>();
				if (request.getParameter("contetypeid")!=null) {
					int typeid= Integer.parseInt(request.getParameter("contetypeid"));
					list3=ws.queryWebBytype(typeid);
					 int i=0;
					for(;i<list3.size();i++){
						 if(weiboId==(list3.get(i).getWEIBOID())){
							 list3.remove(i);	
						}
					}	
				}
					
				request.setAttribute("list2", list2);
				request.setAttribute("replyList", replyList);
				request.setAttribute("detailWb", detailWb);
				request.setAttribute("list3", list3);
				request.getRequestDispatcher("more.jsp").forward(request, response);
			} else if (op.equals("querymycoll"))// ��ѯ���ղع���΢��
			{
				String nickname = (String) session.getAttribute("username");
				List<W_weibo> colllist = new W_collectService().queryMyColl(nickname);
				request.setAttribute("colllist", colllist);
				request.getRequestDispatcher("collectpage.jsp").forward(request, response);
			} else if (op.equals("querymyzan"))// ��ѯ�ҵ��޹���΢��
			{
				String nickname = (String) session.getAttribute("username");
				List<W_weibo> zanlist = new W_zanService().queryMyZAN(nickname);
				request.setAttribute("zanlist", zanlist);
				request.getRequestDispatcher("zanpage.jsp").forward(request, response);
			} else if (op.equals("chatpage")) {
				String nickName = (String) session.getAttribute("username");
				List<W_friend> friendList = fs.queryMyFriend(nickName);
				W_userinfo myList = us.getUserInfoByNikeName(nickName);
				request.setAttribute("firstName", friendList.get(0).getFRIENDNAME());
				request.setAttribute("friendList", friendList);
				request.setAttribute("mytouxiang", myList.getTOUXIANG());
				request.getRequestDispatcher("chat.jsp").forward(request, response);
			}
			// �����¼��ajax����
			else if (op.equals("chatcontent")) {
				String sendName = (String) session.getAttribute("username");
//				String receiveName = new String(request.getParameter("receiveName").getBytes("ISO-8859-1"), "UTF-8");
				String receiveName = request.getParameter("receiveName");
				List<W_message> sendMessageList = ms.queryMessageByName(sendName, receiveName);

				Gson g = new Gson();
				String jsonString = g.toJson(sendMessageList);
				// //������������仰����ζ����ͼ��һ�㲻��ҪJSON.parse
				// �����Ѿ������ص����ݱ����json����
				response.setContentType("application/json");
				out.print(jsonString);

			} else if (op.equals("sendMessage")) {
				String sendName = (String) session.getAttribute("username");
//				String receiveName = new String(request.getParameter("receiveName").getBytes("ISO-8859-1"), "UTF-8");
				String receiveName = request.getParameter("receiveName");
				String sendTime = sdf.format(new Date());
//				String content = new String(request.getParameter("sendContent").getBytes("ISO-8859-1"), "UTF-8");
				String content = request.getParameter("sendContent");
				W_message message = new W_message(0, sendName, receiveName, content, sendTime, "δ��", "���Ǽٵ�ͷ��");
				ms.addMessage(message);

				List<W_message> sendMessageList = ms.queryMessageByName(sendName, receiveName);

				Gson g = new Gson();
				String jsonString = g.toJson(sendMessageList);
				// //������������仰����ζ����ͼ��һ�㲻��ҪJSON.parse
				// �����Ѿ������ص����ݱ����json����
				response.setContentType("application/json");
				out.print(jsonString);
			}
			// �ҵ���ҳ�����б�����
			else if (op.equals("myHomeFriend")) {
				String nickName = (String) session.getAttribute("username");
				int groupId = Integer.parseInt(request.getParameter("groupId"));
				// ����û���Ϣ
				List<W_friend> myHomefriendList = fs.queryMyFriend(nickName, groupId);

				Gson g = new Gson();
				String jsonString = g.toJson(myHomefriendList);
				// //������������仰����ζ����ͼ��һ�㲻��ҪJSON.parse
				// �����Ѿ������ص����ݱ����json����
				response.setContentType("application/json");
				out.print(jsonString);
			}
			//ע����¼
			else if(op.equals("exit")) {
				session.removeAttribute("username");
				response.sendRedirect("index.jsp");
			}
			
			
		}

	}

}
