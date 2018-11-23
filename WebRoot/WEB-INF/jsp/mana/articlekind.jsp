<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no">
<title>限时活动</title>
<!-- Bootstrap Core CSS -->
<link href="<%=basePath%>vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="<%=basePath%>vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">
<!-- DataTables CSS -->
<link
	href="<%=basePath%>vendor/datatables-plugins/dataTables.bootstrap.css"
	rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link
	href="<%=basePath%>vendor/datatables-responsive/dataTables.responsive.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<%=basePath%>dist/css/sb-admin-2.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="<%=basePath%>vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- jQuery -->
<script src="<%=basePath%>vendor/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<%=basePath%>vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="<%=basePath%>vendor/metisMenu/metisMenu.min.js"></script>
<!-- DataTables JavaScript -->
<script src="<%=basePath%>vendor/datatables/js/jquery.dataTables.min.js"></script>
<script
	src="<%=basePath%>vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script
	src="<%=basePath%>vendor/datatables-responsive/dataTables.responsive.js"></script>
<!-- Custom Theme JavaScript -->
<script src="<%=basePath%>dist/js/sb-admin-2.js"></script>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="navbar-brand" href="index.html">无微不至管理系统</div>
		</div>
		<!-- /.navbar-header -->



		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<%-- <li><a href="#"><i class="glyphicon glyphicon-time"></i>
							限时抢购<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/mission">活动列表</a></li>
							<li><a href="<%=basePath%>mana/addmission?id=0"
								class="active">新增活动</a></li>
						</ul> <!-- /.nav-second-level --></li> --%>
						<li><a href="#"><i class="glyphicon glyphicon-expand"></i>
							轮播管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/bannerlist" >轮播列表</a></li>
							<li><a href="<%=basePath%>mana/addbanner?id=0" >新增轮播</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-copyright-mark"></i>
							积分管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/reward" >积分系数</a></li>
							<li><a href="<%=basePath%>mana/rewardlist" >积分增减</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-shopping-cart"></i>
							商品管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/category">商品类别管理</a></li>	
							<li><a href="<%=basePath%>mana/goods" >商品列表</a></li>
							<li><a href="<%=basePath%>mana/addgoods?id=0&kind=0">新增商品（正常）</a></li>
							<li><a href="<%=basePath%>mana/addgoods?id=0&kind=1">新增商品（积分）</a></li>
							<li><a href="<%=basePath%>mana/addgoodsrecycle">商品回收站</a></li>
							<li><a href="<%=basePath%>mana/oldcommentlist">老用户评论（未归类）</a></li>
							<li><a href="<%=basePath%>mana/dealoldcommentlist">老用户评论（已归类）</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-file"></i>
							文章管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
						<li><a href="<%=basePath%>mana/articlekind" class="active">文章类别</a></li>
							<li><a href="<%=basePath%>mana/articlelist">文章列表</a></li>
							<li><a href="<%=basePath%>mana/addarticle?id=0">新增文章</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-thumbs-up"></i>
							健康档案<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/archiveslist">申请分享列表</a></li>
							<li><a href="<%=basePath%>mana/archivesdeal">通过审核列表</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-signal"></i>
							订单管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/orderlist">未处理订单</a></li>
							<li><a href="<%=basePath%>mana/deallist">已处理订单</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<%-- <li><a href="#"><i class="glyphicon glyphicon-globe"></i>
							线下店管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/storelist">线下店列表</a></li>
							<li><a href="<%=basePath%>mana/addstore?id=0">线下店新增</a></li>
						</ul> <!-- /.nav-second-level --></li> --%>
						<li><a href="#"><i class="glyphicon glyphicon-qrcode"></i>
							经销商管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/applycontrol">经销商功能设置</a></li>
							<li><a href="<%=basePath%>mana/applyquestion">经销商答卷</a></li>
							<li><a href="<%=basePath%>mana/applylist">经销商申请列表</a></li>
							<li><a href="<%=basePath%>mana/dealapply">经销商列表</a></li>
							<li><a href="<%=basePath%>mana/invite">经销商邀请关系（修改）</a></li>
							<li><a href="<%=basePath%>mana/dealerupdatelist">升级大区经理申请列表</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>
							小程序基本设置<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/basicwwbz">基本内容设置</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-gift"></i>
							砍价活动<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/bargainlist">砍价活动列表</a></li>
							<li><a href="<%=basePath%>mana/addbargain?id=0">新增砍价活动</a></li>
							<li><a href="<%=basePath%>mana/bargainapply">订单申请</a></li>
							<li><a href="<%=basePath%>mana/bargaindeal">订单完成</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-tint"></i>
							糖尿病活动<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/diabetes">糖尿病答卷</a></li>
							<li><a href="<%=basePath%>mana/diabetesprelist">血糖仪未处理列表</a></li>
							<li><a href="<%=basePath%>mana/diabetesdeallist">血糖仪已处理列表</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>
							管理小程序(管理)<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/basic">基本内容设置</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-expand"></i>
							轮播管理(管理)<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/bannerlistgl" >轮播列表</a></li>
							<li><a href="<%=basePath%>mana/addbannergl?id=0" >新增轮播</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-file"></i>
							首页文章管理(管理)<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/articlelistgl">文章列表</a></li>
							<li><a href="<%=basePath%>mana/addarticlegl?id=0">新增文章</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="<%=basePath%>mana/invitationlist" ><i class="	glyphicon glyphicon-envelope"></i>
							邀请函列表<span class="fa arrow"></span></a>
						 <!-- /.nav-second-level --></li>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">文章类别管理</h1>
					</div>
					<!-- /.col-lg-12 -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<button class="btn" id="nk">新增文章类别</button>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-lg-6">
											<c:forEach var="mi" items="${requestScope.akl}">
												<form role="form" action="<%=basePath%>mana/addarticlekind"
													method="post" enctype="multipart/form-data"
													onsubmit="return validate_required(this)">
													<div class="from-group"
														style="display: flex; flex; direction: row; just-content: flex-start; align-items: center; margin-top: 10px">
														<img src="${mi.icon }" height="30px"
															style="margin-top: 2%"> <input type="text"
															placeholder="请输入类别名称" class="form-control" name="name"
															value="${mi.name }"> <input type="hidden"
															value="${mi.id }" name="id"> <input type="file"
															name="icon" id="icon" onchange="changImg(event)">
														<button type="submit" class="btn btn-default">修改</button>
														<view class="btn btn-default" onclick="del(${mi.id })">删除</view>
													</div>
												</form>
											</c:forEach>
											<form role="form" action="<%=basePath%>mana/addarticlekind"
												method="post" enctype="multipart/form-data"
												onsubmit="return validate_required(this)">
												<div class="from-group" id="new"
													style="display: flex; flex; direction: row; just-content: flex-start; align-items: center; margin-top: 10px">
													<input type="text" placeholder="请输入类别名称"
														class="form-control" name="name"> <input
														type="hidden" value="0"
														name="id"> <input type="file" name="icon"
														id="icon" onchange="changImg(event)"> 
													<button type="submit" class="btn btn-default">添加</button>
												</div>
											</form>
										</div>
										<div class="col-lg-6"></div>
										<!-- /.panel-body -->
									</div>
									<!-- /.panel -->
								</div>
								<!-- /.col-lg-12 -->
							</div>
							<!-- /.row -->
						</div>
						<!-- /.row -->
					</div>
					<!-- /.container-fluid -->
				</div>
				<!-- /#page-wrapper -->
			</div>
			<!-- /#wrapper -->
			<!-- Page-Level Demo Scripts - Tables - Use for reference -->
			<script>
				$(document).ready(function() {
					$('#dataTables-example').DataTable({
						responsive : true
					});
					$("#new").hide();
					$("#nk").click(function(){
						$("#new").show();
					})
					<%-- $("#add").click(function(){
						if($(this).parent().children("input").val()==""){
							alert("新类别不能为空")
						}else{
							$.ajax({
								type : "POST",
								url : "<%=basePath%>mana/addarticlekind",
										data : {
											"name" : $(this).parent().children("input").val()
										},
										dataType : 'json',//返回值是json
										async : false,//同步请求
										success : function(data) {
											if (data.result == 1) {
												//alert("登录成功！");
												window.location.reload();
											} else {
												alert(msg);
											}
										},
										error : function(data) {
											alert("login error!");
											alert(JSON.stringify(data));
										}
									});
						}
					}) --%>
					$(".change").click(function(){
						if($(this).parent().children("input").val()==""){
							alert("类别名称为空")
						}else{
							$.ajax({
								type : "POST",
								url : "<%=basePath%>mana/changearticlekind",
										data : {
											"name" : $(this).parent().children("input").val(),
											"id":$(this).attr("data")
										},
										dataType : 'json',//返回值是json
										async : false,//同步请求
										success : function(data) {
											if (data.result == 1) {
												//alert("登录成功！");
												window.location.reload();
											}else {
												alert(msg);
											}
										},
										error : function(data) {
											alert("login error!");
											alert(JSON.stringify(data));
										}
									});
						}
						
					})
					$(".del").click(function(){
						$.ajax({
							type : "POST",
							url : "<%=basePath%>mana/delarticlekind",
									data : {
										"id" : $(this).attr("data")
									},
									dataType : 'json',//返回值是json
									async : false,//同步请求
									success : function(data) {
										if (data.result == 1) {
											//alert("登录成功！");
											window.location.reload();
										}else if(data.result == 2){
											alert("此类目下还有文章,不能删除");
										}  else {
											alert(msg);
										}
									},
									error : function(data) {
										alert("login error!");
										alert(JSON.stringify(data));
									}
								});
					})
				})
				function backto() {
					window.history.go(-1);
				}
				function del(id) {
					$.ajax({
						type : "POST",
						url : "<%=basePath%>mana/delarticlekind",
								data : {
									"id" : id
								},
								dataType : 'json',//返回值是json
								async : false,//同步请求
								success : function(data) {
									if (data.result == 1) {
										//alert("登录成功！");
										window.location.reload();
									}else if(data.result == 2){
										alert("此类目下还有文章,不能删除");
									}  else {
										alert(msg);
									}
								},
								error : function(data) {
									alert("login error!");
									alert(JSON.stringify(data));
								}
							});
		
				}
				function changImg(e) {
						
					for (var i = 0; i < e.target.files.length; i++) {
						var file = e.target.files.item(i);
						if (!(/^image\/.*$/i.test(file.type))) {
							continue; //不是图片 就跳出这一次循环  
						}
						//实例化FileReader API  
						var freader = new FileReader();
						freader.readAsDataURL(file);
						freader.onload = function(e) {
							
							$("#icon0").attr("src", e.target.result);
						}
					}

				}
				function validate_required(form) {
					if(form.id.value==0){
						if(form.name.value==""||form.icon.value==""){
							alert("请检查信息")
							return false
						}else{
							return true
						}  
					}else{
						if(form.name.value==""){
							alert("请检查信息")
							return false
						}else{
							return true
						}  
					}
				 	
					
				}
				
			</script>
</body>
</html>