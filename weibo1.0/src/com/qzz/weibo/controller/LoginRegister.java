package com.qzz.weibo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.qzz.weibo.service.W_UserInfoService;
import com.qzz.weibo.service.W_usersService;
import com.qzz.weibo.util.DataUtil;

/**
 * Servlet implementation class LoginRegister
 */
@WebServlet("/LoginRegister")
public class LoginRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginRegister() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		// �ж�op��ֵ
		if (request.getParameter("op") != null) {
			String op = request.getParameter("op");
			// ע�Ṧ����֤�û����Ƿ����
			if (op.equals("usname")) {
				W_usersService us = new W_usersService();
				String phone = "";
				String usname = "";
				phone = request.getParameter("phone");
				if (phone != "") {
					int i = us.RegisterPhone(phone);
					if (i == 1) {
						usname = "�û����Ѵ���!!!";
					} else {
						usname = "�û�������";
					}
				} else {
					usname = "�û�������Ϊ��";
				}
				Gson gson = new Gson();
				String jsonString = gson.toJson(usname);
				// ʹ��printWriter����
				PrintWriter out = response.getWriter();
				out.print(jsonString);
				out.close();
			} // ע�Ṧ��
				// ע�Ṧ����֤�û��ǳ��Ƿ����
			if (op.equals("nickname")) {
				W_UserInfoService uis = new W_UserInfoService();
				String nickname = "";
				String msg = "";
				nickname = new String(request.getParameter("nickname").getBytes("ISO-8859-1"),
						"UTF-8");
				
				System.out.println("nickname"+nickname);
				if (!nickname.equals("")) {
					int i = uis.isExisNickname(nickname);
					System.out.println("i"+i);
					if (i == 1) {
						msg = "���ǳ��Ѵ���!!!";
					} else {
						msg = "���ǳƿ���";
					}
				} else {
					msg = "�ǳƲ���Ϊ��";
				}
				Gson gson = new Gson();
				String jsonString = gson.toJson(msg);
				// ʹ��printWriter����
				PrintWriter out = response.getWriter();
				out.print(jsonString);
				out.close();
			} // ע�Ṧ��
			// ע�Ṧ����֤�û���֤���Ƿ���ȷ
			if (op.equals("number")) {
				String number = DataUtil.number;//ͼƬ���ɵ���֤��
				String num = request.getParameter("number");//�û��������֤��	
				String msg = "";
				if (!num.equals("")) {
					if(num.equals(number)) {
						msg="��֤����ȷ";
					}
					else {
						msg="��֤���������";
					}
				}
				else {
					msg="��֤�벻��Ϊ��";
				}
				Gson gson = new Gson();
				String jsonString = gson.toJson(msg);
				// ʹ��printWriter����
				PrintWriter out = response.getWriter();
				out.print(jsonString);
				out.close();
			} // ע�Ṧ��
			else if (op.equals("regst")) {
				W_usersService us = new W_usersService();
				String username = request.getParameter("phone");
				String password = request.getParameter("pwd");
				String nickname = request.getParameter("nickname");
				boolean flag = us.userRegister(username, password, nickname);
				if (flag) {
					// JOptionPane.showMessageDialog(null, "ע��ɹ�");
					request.setAttribute("name", username);
					request.setAttribute("pwd", password);
					response.getWriter().print("<script language='javascript'>alert('ע��ɹ�');</script>");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					JOptionPane.showMessageDialog(null, "ʧ��");
					request.getRequestDispatcher("regiter.jsp").forward(request, response);
				}

			} // ��¼����
			else if (op.equals("loginm")) {
				W_usersService us = new W_usersService();
				String name = request.getParameter("namem");
				String pwd = request.getParameter("pwdm");
				PrintWriter out = response.getWriter();
				int j = us.RegisterPhone(name);
				if (j == 0) {
					System.out.println("��������ʺŲ�����!");
					//JOptionPane.showMessageDialog(null, "��������ʺŲ����ڣ�", "û���ʺ���Ϣ", JOptionPane.ERROR_MESSAGE);
					out.println("<script>alert('��������ʺŲ�����!');location.href='index.jsp'</script>");
				} else {
					String pwdyz = us.queryUserpwdByName(name);
					if (pwdyz.equals(pwd)) {
						W_UserInfoService userinfoser = new W_UserInfoService();
						String username = userinfoser.getNickNameByUserName(name);
						if (username.equals("null")) {
							response.sendRedirect("homepage.jsp");
						} else {
							session.setAttribute("username", username);
							response.sendRedirect("WeiBoServlet?op=homepage");
						}

					} else {
						//JOptionPane.showMessageDialog(null, "��������������󣡣�", "�������", JOptionPane.ERROR_MESSAGE);
						System.out.println("�����������������!!");
						out.println("<script>alert('�����������������!');location.href='index.jsp'</script>");
					}
				}

			} else if (op.equals("logintreply")) {
				W_usersService us = new W_usersService();
				String name = request.getParameter("namet");
				System.out.println("username" + name);
				String pwd = request.getParameter("pwdt");
				int j = us.RegisterPhone(name);
				if (j == 0) {
					JOptionPane.showMessageDialog(null, "��������ʺŲ����ڣ�", "û���ʺ���Ϣ", JOptionPane.ERROR_MESSAGE);
					request.setAttribute("name", name);
					request.setAttribute("pwd", pwd);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					String pwdyz = us.queryUserpwdByName(name);
					if (pwdyz.equals(pwd)) {
						W_UserInfoService userinfoser = new W_UserInfoService();
						String username = userinfoser.getNickNameByUserName(name);
						session.setAttribute("username", username);
						System.out.println("more��������¼");
						// response.getWriter().print("<script
						// language='javascript'>history.go(-2);</script>");
						response.getWriter().println(
								"<script language='javascript'>window.history.go(-1);window.history.go(0)</script>");
					}

					else {
						JOptionPane.showMessageDialog(null, "��������������󣡣�", "�������", JOptionPane.ERROR_MESSAGE);
						request.setAttribute("name", name);
						request.setAttribute("pwd", pwd);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				}

			} else if (op.equals("logint")) {
				W_usersService us = new W_usersService();
				String name = request.getParameter("namet");
				String pwd = request.getParameter("pwdt");
				String typ = request.getParameter("cu");
				// �жϹ���Ա���û�
				if (typ.equals("user")) {
					int j = us.RegisterPhone(name);
					// ��֤�Ƿ�����û���Ϣ
					if (j == 0) {
						JOptionPane.showMessageDialog(null, "��������ʺŲ����ڣ�", "û���ʺ���Ϣ", JOptionPane.ERROR_MESSAGE);
						request.setAttribute("name", name);
						request.setAttribute("pwd", pwd);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					} else {
						// ��������֤�û���Ϣ�Ƿ���ȷ
						String pwdyz = us.queryUserpwdByName(name);
						if (pwdyz.equals(pwd)) {
							W_UserInfoService userinfoser = new W_UserInfoService();
							String username = userinfoser.getNickNameByUserName(name);
							if (username.equals("null")) {
								response.sendRedirect("homepage.jsp");
							} else {
								session.setAttribute("username", username);
								response.sendRedirect("WeiBoServlet?op=homepage");
							}

						} else {
							JOptionPane.showMessageDialog(null, "��������������󣡣�", "�������", JOptionPane.ERROR_MESSAGE);
							request.setAttribute("name", name);
							request.setAttribute("pwd", pwd);
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}
					}
				} else {
					int j = us.selAdmin(name);
					if (j == 0) {
						JOptionPane.showMessageDialog(null, "��������ʺŲ����ڣ�", "û���ʺ���Ϣ", JOptionPane.ERROR_MESSAGE);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					} else {
						String pwdyz = us.queryUserpwdByName(name);
						// ��¼�ɹ��Ļ�
						if (pwdyz.equals(pwd)) {
							W_UserInfoService userinfoser = new W_UserInfoService();
							String username = userinfoser.getNickNameByUserName(name);
							if (username.equals("null")) {
								response.sendRedirect("homepage.jsp");
							} else {
								session.setAttribute("username", username);
								response.sendRedirect("WeiBoServlet?op=homepage");
							}

						} else {
							JOptionPane.showMessageDialog(null, "��������������󣡣�", "�������", JOptionPane.ERROR_MESSAGE);
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}
					}
				}
			}
		}
	}
}
