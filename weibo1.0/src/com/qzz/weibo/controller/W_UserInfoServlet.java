package com.qzz.weibo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qzz.weibo.entity.W_userinfo;
import com.qzz.weibo.entity.W_weibo;
import com.qzz.weibo.service.W_UserInfoService;

/**
 * Servlet implementation class W_UserInfoServlet
 */
@WebServlet("/W_UserInfoServlet")
public class W_UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public W_UserInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 创建对象
		List<W_userinfo> list = new ArrayList<W_userinfo>();
		W_UserInfoService userinfoservice = new W_UserInfoService();
		
		HttpSession session = request.getSession(); 
		
		// 获取页面传过来的属性
		String username =session.getAttribute("name")+"";
		String nickname = (String) session.getAttribute("username");
		String realname = request.getParameter("realname");
		String sex = request.getParameter("sex");
		String remark = request.getParameter("remark");
		String qq = request.getParameter("qq");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String province = request.getParameter("province");
		

	

		// 将获取的值放进W_userinfo实体类中
		W_userinfo userinfo = null;

		if (request.getParameter("op") != null) {
			String op = request.getParameter("op");
			String touxiang="";
			// 如果传过来的值是getUserInfo则查询该用户下的基本信息
			if (op.equals("getUserInfo")) {
				userinfo = userinfoservice.getUserInfoByNikeName(nickname);
				touxiang = userinfo.getTOUXIANG();
				request.setAttribute("userinfo",userinfo);
				request.getRequestDispatcher("information.jsp").forward(request, response);
			}
			// 如果传过来的值是updateuserinfo则先将用户的基本信息更新，再重新查询出来显示
			else if (op.equals("updateuserinfo")) {
				 nickname = request.getParameter("nickname");
				 String privince = request.getParameter("privin");
				 String city1 = request.getParameter("c1");
				 String city2 = request.getParameter("c2");
				 String birth = request.getParameter("birth");
				 //System.out.println("用户名是"+username);
				 W_userinfo newuserinfo = new W_userinfo(username, nickname, realname, sex, birth, privince, city1, city2, email, qq, phone, remark, touxiang);
				 if(userinfoservice.updateUserinfo(newuserinfo)) {
					 session.setAttribute("username",newuserinfo.getNICKNAME());
					 PrintWriter out = response.getWriter();
					 out.print("<script>alert('修改成功');location.href='WeiBoServlet?op=queryMyWb'</script>");
				 }
 				
			}
		}

		
		

	}

}
