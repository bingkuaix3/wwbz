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
<%-- 					<li><a href="#"><i class="glyphicon glyphicon-time"></i>
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
							<li><a href="<%=basePath%>mana/category" >商品类别管理</a></li>
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
							<li><a href="<%=basePath%>mana/articlekind">文章类别</a></li>
							<li><a href="<%=basePath%>mana/articlelist">文章列表</a></li>
							<li><a href="<%=basePath%>mana/addarticle?id=0" >新增文章</a></li>
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
							<li><a href="<%=basePath%>mana/applyquestion" class="active">经销商答卷</a></li>
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
						<h1 class="page-header">经销商问答</h1>
					</div>
					<!-- /.col-lg-12 -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading"></div>
								<div class="panel-body">
									<div class="row">
										<div class="col-lg-8">
											<c:forEach var="mi" items="${requestScope.qlist}">
												<div>
													<div class="form-group"
														style="display: flex; flex-direction: row; just-content: flex-start; align-items: center">
														<input type="number" placeholder="排序" class="form-control"
															style="width: 10%" value="${mi.order }" ><input
															type="text" placeholder="题目" class="form-control"
															value="${mi.content }"> <input type="hidden"
															value="${mi.id }" class="id">
														<view class="btn btn-primary add" style="margin-left:2%">增加选项</view>
														<view class="btn btn-success change"
															style="margin-left:2%">确定修改</view>
														<view class="btn btn-danger del" style="margin-left:2%">删除此题</view>
													</div>
													<c:forEach var="mis" items="${requestScope.list}">
														<c:choose>
															<c:when test="${mis.parentid==mi.id}">
																<div style="display: flex; flex-direction: row; just-content: flex-start; align-items: center;margin-bottom: 1%;">
																	<input type="text" class="form-control" value="${mis.content }">
																	<input type="hidden" class="form-control" value="${mis.id }">
																	<view class="btn btn-success changeson"
															style="margin-left:2%">确定修改</view>
														<view class="btn btn-danger delson" style="margin-left:2%">删除此选项</view>
																</div>
         													</c:when>
															<c:otherwise>
         													</c:otherwise>
         												</c:choose>
													</c:forEach>
													<div class="from-control son"
														style="display: flex; flex-direction: row; just-content: flex-start; align-items: center; margin-bottom: 1%;">
														<input type="text" class="form-control">
														<view class="btn btn-success addson"
															style="margin-left:2%">新增</view>
													</div>
												</div>
											</c:forEach>
											<div class="form-group" id="new" style="display:flex;flex-direction:row;just-content:flex-start;align-items:center">
											<input type="number" placeholder="排序" class="form-control" style="width:10%" id="number"><input type="text" placeholder="题目" class="form-control" id="content"><view class="btn btn-default" onclick="add()">确定添加</view>
											</div>
										</div>
										
										<!-- /.panel-body -->
									</div>
									<button class="btn btn-primary" id="add">添加题目</button>
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
					$(".son").hide();
					$("#add").click(function(){
						$("#new").show();
					})
					$(".change").click(function(){
						if($(this).prev().prev().prev().val()==""){
							alert("题目不能为空")
						}else{
							var a=$(this).prev().prev().prev().prev().val()
						 if(a-parseInt(a)==0){
							 $.ajax({
									type : "POST",
									url : "<%=basePath%>mana/addapplyquestion",
											data : {
												"number" : $(this).prev().prev().prev().prev().val(),
												"content":$(this).prev().prev().prev().val(),
												"id":$(this).prev().prev().val()
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
						 }else{
							 alert("请输入整数")
						 }
							
						}
						
					})
					$(".del").click(function(){
						$.ajax({
							type : "POST",
							url : "<%=basePath%>mana/delapplyquestion",
									data : {
										"id":$(this).prev().prev().prev().val()
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
					})
					$(".add").click(function(){
						//$(".son").show();
						$(this).parent().parent().find(".son").show()
					})
					$(".addson").click(function(){
						 if($(this).prev().val()==""){
							alert("请填写内容")
						}else{
							$.ajax({
								type : "POST",
								url : "<%=basePath%>mana/addapplyquestionson",
										data : {
											"fatherid":$(this).parent().parent().find(".id").val(),
											"content":$(this).prev().val(),
											"id":0
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
					})
					$(".delson").click(function(){
						
						 $.ajax({
							type : "POST",
							url : "<%=basePath%>mana/delapplyquestion",
									data : {
										"id":$(this).prev().prev().val()
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
					})
					$(".changeson").click(function(){
						if($(this).prev().prev().val()==""){
							alert("请填写内容")
						}else{
							$.ajax({
								type : "POST",
								url : "<%=basePath%>mana/addapplyquestionson",
										data : {
											"fatherid":0,
											"content":$(this).prev().prev().val(),
											"id":$(this).prev().val()
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
					})
				})
				function backto() {
					window.history.go(-1);
				}
				function validate_required() {
					if($("#article_src").val()==""||$("#article_name").val()==""){
						alert("请补全信息")
						return false
					}else{
						return true
					}
				}
				function add(){
					if($("#number").val()==""||$("#content").val()==""){
						alert("请填写信息")
					}else{
						var a=$("#number").val()
						if(a-parseInt(a)==0){
							$.ajax({
								type : "POST",
								url : "<%=basePath%>mana/addapplyquestion",
										data : {
											"number" : $("#number").val(),
											"content":$("#content").val(),
											"id":0
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
						}else{
							alert("请输入整数")
						}
						
					}
				}
				
				
			</script>
</body>
</html>