<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%session.setAttribute("name", "jia") ;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="bootstrap-3.3.7-dist/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="css/login.css" />
		<link rel="stylesheet" href="css/visitor.css" />
		<link rel="stylesheet" type="text/css" href="css/more.css"/>
		<script src="backdrop/pixi.js" type="text/javascript"></script>
       <script src="backdrop/quicksketch.min.js" type="text/javascript"></script>
       <script src="backdrop/introBG.js" type="text/javascript"></script>
		<meta charset="utf-8" />
		<title></title>
		<style type="text/css">
		</style>
	</head>

	<body class="home">
		<!--头部logo以及导航栏开始-->
		<div id="header">
			<div class="container">
				<div class="row clearfix">
					<div class="col-md-9 column  sousuo">
						<img style="height: 40px;widows: 50px;" src="img/visitor_img/logo.PNG" />
						<input id="serach" type="search" placeholder="大家都在搜：鹿晗、关晓彤分手">
						<input type="button" class="btnsearch" name="" id="" value="" />

					</div>
					<div class="col-md-3 column daohan3">
						<ul class="nav navbar-nav navbar-right">
							<li>
								<a href="index.jsp"><span class="glyphicon glyphicon-home"></span>首页</a>
							</li>
							<li>
								<a href="regiter.jsp">注册</a>
							</li>
							<li class="nav navbar-nav navbar-right">
								<a id="loginmain" href="#modal-container-580488" role="button" class="btn" data-toggle="modal">登录</a>
							</li>

						</ul>
					</div>
				</div>
			</div>

		</div>
		<!--头部logo以及导航栏结束-->
		<!--
            	作者：offline
            	时间：2017-10-11
            	描述：导航开始
           -->

		<!--
            	作者：offline
            	时间：2017-10-11
            	描述：导航结束
            -->

		<div class="container">
			<div class="col-md-12 column" style="margin-top: 80px;">
				<div class="row clearfix">
					<!--
            	作者：offline
            	时间：2017-10-11
            	描述：左侧列表开始
            -->
					<div class="col-md-2 " style=" text-align: center;margin: 0px;width: 130px;height: 38px;">
						<div class="list lefelist" style="border: none;">
							<a href="#" class="list-group-item " style="background-color:rgb(254,84,53);color: ghostwhite;"><strong>热门</strong> </a>
						</div>
						<div class="list lefelist" style="border: none;">
							<a href="#" class="list-group-item "><strong>头条</strong></a>
						</div>
						<div class="list lefelist" style="border: none;">
							<a href="#" class="list-group-item  "><strong>榜单</strong></a>
						</div>
						<div class="list lefelist" style="border: none;">
							<a href="#" class="list-group-item  "><strong>明星</strong></a>
						</div>

					</div>
					<!--
            	作者：offline
            	时间：2017-10-11
            	描述：左侧列表结束
            -->

					<!--
            	作者：offline
            	时间：2017-10-11
            	描述：中间列表开始
            -->
					<div class="col-md-7 column">
						<div class="carousel slide" id="carousel-299699">
							<div class="carousel-inner">
								<div class="item">
									<img alt="" src="img/visitor_img/2.jpg" style="width: 700px; height: 300px;" />
									<div class="carousel-caption">
										<h4>
								细数娱乐圈好姐妹
							</h4>
									</div>
								</div>
								<div class="item active">
									<img alt="" src="img/visitor_img/1.jpg" style="width: 700px; height: 300px;" />
									<div class="carousel-caption">
										<h4>
								世预赛阿根廷惊醒晋级
							</h4>
									</div>
								</div>
								<div class="item">
									<img alt="" src="img/visitor_img/3.jpg" style="width: 700px; height: 300px;" />
									<div class="carousel-caption">
										<h4>
								失联大学生
							</h4>
									</div>
								</div>
							</div>
							<a class="left carousel-control" href="#carousel-299699" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
							<a class="right carousel-control" href="#carousel-299699" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
							<ol class="carousel-indicators">
								<li data-slide-to="0" data-target="#carousel-299699">
								</li>
								<li data-slide-to="1" data-target="#carousel-299699" class="active">
								</li>
								<li data-slide-to="2" data-target="#carousel-299699">
								</li>
							</ol>

						</div>

						<!--中间的下面的内容开始-->
						<div class="carousel slide" style="margin-top: 20px;">
							<table class="table">
								<!--第一行 -->
								<tr>
									<div class="col-md-12 " id="zhongjianneirong">
										<div class="col-md-3" id="tupian" style="height: 120px;">
											<!-- 第一行左边图片 开始-->
											<img src="img/visitor_img/1.1.jpg" style="height: 120px;width: 160px;" />
											<!-- 第一行左边图片结束  -->
										</div>
										<!-- 第一行右边开始  -->
										<div class="col-md-9" style="height: 120px;" id="tupianyoubianneirong">
											<form class="form-horizontal" role="form">
												<!-- 第一行右边 图片开始  -->
												<!-- 第一行右边上面的微博具体内容开始  -->
												<div class="form-group" style="height: 60px;text-align: left;line-height: 65px;">

													<div class="panel-body">
														我们相见时十年的重逢！！！
													</div>

												</div>
												<!-- 第一行右边上面的微博具体内容结束  -->
												<!-- 第一行右边下面的微博发布人头像、名字、时间、转发、评论、点赞开始  -->
												<div class="form-group" style="height: 55px;text-align: left;line-height: 40px;">

													<ul class="list-inline">
														<li style="margin-left: 10px;"><img src="img/visitor_img/10.jpg" style="border-radius:50%; overflow:hidden; width: 24px;height: 24px;" /></li>
														<li style="padding: 0px;">韩庚</li>
														<li>今天10:25</li>
														<li><span class="glyphicon glyphicon-new-window"></span></li>
														<li><span id="praise-txt">1455</span></li>
														<li><span class="glyphicon glyphicon-comment"></span> </li>
														<li><span id="praise-txt">1455</span></li>
														<li><span class="glyphicon glyphicon-thumbs-up"></span></li>
														<li><span id="praise-txt">1455</span></li>
													</ul>

												</div>
												<!-- 第一行右边下面的微博发布人头像、名字、时间、转发、评论、点赞结束  -->
											</form>
										</div>
										<!-- 第一行右边边图片结束  -->

									</div>
									&nbsp;&nbsp;&nbsp;
									<td></td>
								</tr>

								<!--第一行结束 -->
							</table>
						</div>
						<!--中间的下面的内容结束-->
					</div>
					<!--
            	作者：offline
            	时间：2017-10-11
            	描述：中间列表结束
            -->

					<!--
            	作者：offline
            	时间：2017-10-11
            	描述：右侧列表开始
            -->

					<div class="col-md-3 column" style="background-color:rgb(242,242,245); width: 340px;">
						<!--
            	作者：offline
            	时间：2017-10-11
            	描述：右侧的登录框开始
           -->
						<form class="form-horizontal" role="form" style="background-color: rgb(255,255,255); margin:6px; margin-top: 10px; padding: 10px;" action="WeiBoServlet?op=homepage">
							<input type="hidden" name="op" value="homepage">
							<div class="form" style="text-align:  center; height: 35px;line-height: 30px;">
								<h3 style=" border-bottom: 3px solid orangered;">账号登录</h3>
							</div>
							<div class="form-group div-bor" id="name_main" style="padding: 2px 15px;">

								<input type="text" class="form-control class" id="inputEmail3" placeholder="用户名" />
							</div>
							<div class="form-group" id="password_main" style="padding: 2px 15px;">
								<input type="password" class="form-control" id="inputPassword3" placeholder="密码" />
							</div>
							<div class="form-group" style="padding: 0px 15px;">
								<div class="checkbox">
									<label style="margin-right: 130px;">
										<input type="checkbox" class="danger" />记住我</label>
									<label><a href="#">忘记密码</a></label>
								</div>

							</div>
							<div class="form-group" style="text-align: center;">
								<div class=" col-sm-10">
									<button type="submit" class="btn btn-default " style="background-color: rgb(255,129,64); width: 280px;" >登录</button>
								</div>
								<div class=" col-sm-10" style="text-align: left;">
									还没有微博? <a href="regiter.jsp">立即注册</a>
								</div>
							</div>
						</form>

						<!--
            	作者：offline
            	时间：2017-10-11
            	描述：右侧的登录框结束
            -->
						<div id="" style="height: 20px;width: 340px; background: white;margin-left: -15px;border-bottom: 1px solid red;">

						</div>
						<!--
            	作者：offline
            	时间：2017-10-11
            	描述：右侧的微博新鲜事情开始
            -->
						<!--
                    	作者：offline
                    	时间：2017-10-12
                    	描述：第一行开始
                    -->

						<div class="form-group" style="margin-top: 10px;">

							<div class="form" style="text-align: left; height: 35px;line-height: 30px;">
								<h4 style="font-weight:bolder;">微博新鲜事</h4>
							</div>
							<table class="table">
								<tr>
									<div class="col-md-12 column">
										<div class="row clearfix">
											<!-- 第一行右边开始  -->
											<div class="col-md-7" style="text-align: left; height: 88px;">
												<a href="#"><span>手动阀撒地方都是发生的</span> </a>
												<br />
												<br />
												<span id="">10月11日 10:25</span>
												<!-- 第一行右边 图片开始  -->
											</div>
											<!-- 第一行左边图片 开始-->
											<div class="col-md-5">
												<!-- 第一行左边图片 开始-->
												<img src="img/visitor_img/6.jpg" / style="width: 110px;height: 88px;">
												<!-- 第一行左边图片结束  -->
											</div>
										</div>
									</div>
								</tr>
							</table>
							<div class="form" style="text-align: center;text-decoration: none; height: 35px;line-height: 30px;">
								<a href="#">
									<h6>查看更多></h6></a>
							</div>

						</div>
						<!--
                    	作者：offline
                    	时间：2017-10-12
                    	描述：第一行结束
                    -->
						<div id="" style="height: 20px;width: 340px; background: white;margin-left: -15px;border-bottom: 1px solid red ;">

						</div>
						<!--
            	作者：offline
            	时间：2017-10-11
            	描述：右侧的微博新鲜事结束
       -->

						<!--
            	作者：offline           	
            	描述：右侧的微博实时热点开始
            -->
						<!--
                    	作者：offline
                    	时间：2017-10-12
                    	描述：第一行开始
                    -->

						<div class="form-group" style="margin-top: 10px;">

							<div class="form" style="text-align: left; height: 35px;line-height: 30px;">
								<h4 style="font-weight:bolder;">微博实时热点</h4>
							</div>
							<table class="table">
								<tr>
									<div class="col-md-12 column">
										<div class="row clearfix">
											<!-- 第一行右边开始  -->
											<div class="col-md-4" style="text-align: left; ">
												<!-- 第一行左边图片 开始-->
												<img src="img/visitor_img/8.jpg" / style="width: 66px;height:66px;">
												<!-- 第一行左边图片结束  -->
											</div>
											<!-- 第一行左边图片 开始-->
											<div class="col-md-8">
												<a href="#"><span>手动阀撒地方都是发生的</span> </a>
												<br />
												<br />
												<span id="">10月12日 10:25</span>
											</div>
										</div>
									</div>
								</tr>
							</table>
							<div class="form" style="text-align: center;text-decoration: none; height: 35px;line-height: 30px;">
								<a href="#">
									<h6>查看更多></h6></a>
							</div>
						</div>
						<!--
                    	作者：offline
                    	时间：2017-10-12
                    	描述：第一行结束
                    -->

						<!--
            	作者：offline
            	时间：2017-10-11
            	描述：右侧的右侧的微博实时热点结束
       -->

					</div>
					<!--
            	作者：offline
            	时间：2017-10-11
            	描述：右侧列表结束
            -->
				</div>
			</div>
		</div>
		</div>
		<!--
        	作者：offline
        	时间：2017-10-13
        	描述：登录模态窗口设计开始
        -->
		<div class="modal fade" id="modal-container-580488" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">
										账号登录
									</h4>
					</div>
					<div class="modal-body">
						<form>
							<div id="name">
								<input type="text" id="textname" placeholder="用户名" />
							</div>
							<div id="password">
								<input type="password" id="pwd" placeholder="密码" />
							</div>
							<div id="changeuser">
								<input type="radio" name="cu" id="" value="user" checked="" />用户&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="cu" id="" value="admin" />管理员
							</div>
							<div id="btn_up">
								<input type="submit" id="log" value="登录" />

							</div>
							<div id="zc_wj" style="text-align: center;">
								<a class="a1" href="regiter.jsp" id="xiaomireg">注册账号</a>&nbsp;
							</div>
						</form>
					</div>

				</div>

			</div>

		</div>
		<!--
        	作者：offline
        	时间：2017-10-13
        	描述：登录模态窗口设计结束
        -->

	</body>

</html>