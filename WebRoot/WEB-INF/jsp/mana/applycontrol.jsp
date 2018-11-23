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
							<li><a href="<%=basePath%>mana/applycontrol"  class="active">经销商功能设置</a></li>
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
						<h1 class="page-header" >经销商功能设置</h1>
					</div>
					<!-- /.col-lg-12 -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading"></div>
								<div class="panel-body">
									<div class="row">
										<div class="col-lg-6">
											<div>
												<label>合作模式</label>
											</div>
											<button class="btn btn-primary" id="newcooperate">添加合作类别</button>
											<div id="newc">
												<div class="form-group"
													style="display: flex; flex-direction: row; just-content: flex-start; align-items: center; margin-top: 1%; margin-bottom: 1%">
													<input placeholder="请输入模式名称" class="form-control name"><input
														placeholder="请输入押金金额" type="number" class="form-control money"
														style="width: 30%">
												</div>
												<div class="form-group">
													<textarea rows="3" cols="3" placeholder="请输入合作简介"
														class="form-control des"></textarea>
												</div>
												<div>
													<input type="checkbox" class="pay" value="1">是否需要支付
												</div>
												<div>
													<input type="checkbox" class="hospital" value="1">是否为邀请者提供收益（医馆模式）
												</div>
												<div style="display: flex; flex-direction: row; just-content: flex-start; align-items: center;">
													<div style="flex-shrink:0">
														<input type="checkbox" class="head" value="1">是否获得二级收益（大区经理模式）
													</div>
													<input class="form-control" placeholder="请输入二级分配比例" id="pc" value="1">
												</div>
												<button class="btn btn-success newc" style="margin-top:1%;">确认新增</button>
											</div>
											<c:forEach var="mis" items="${requestScope.list}">
												<div>
													<div class="form-group"
														style="display: flex; flex-direction: row; just-content: flex-start; align-items: center; margin-top: 1%; margin-bottom: 1%">
														<input placeholder="请输入模式名称" class="form-control name"
															value="${mis.name }"><input placeholder="请输入押金金额"
															type="number" class="form-control money"
															style="width: 30%" value="${mis.money }"> <input
															type="hidden" value="${mis.id }" class="id">
													</div>
													<div class="form-group">
														<textarea rows="3" cols="3" placeholder="请输入合作简介"
															class="form-control des">${mis.des }</textarea>
													</div>
													<div>
														<c:choose>
															<c:when test="${mis.ispay==1}">
																<input type="checkbox" class="pay" checked>是否需要支付
															</c:when>
															<c:otherwise>
																<input type="checkbox" class="pay">是否需要支付
															</c:otherwise>
														</c:choose>
													</div>
													<div>
														<c:choose>
															<c:when test="${mis.ishospital==1}">
																<input type="checkbox" class="hospital" checked>是否为邀请者提供收益（医馆模式）
															</c:when>
															<c:otherwise>
																<input type="checkbox" class="hospital">是否为邀请者提供收益（医馆模式）
															</c:otherwise>
														</c:choose>
													</div>
													<div style="display: flex; flex-direction: row; just-content: flex-start; align-items: center;">
														<c:choose>
															<c:when test="${mis.ishead==1}">
																<div style="flex-shrink: 0">
																	<input type="checkbox" class="head" value="1" checked>是否获得二级收益（大区经理模式）
																</div>
																<input class="form-control pc" placeholder="请输入二级分配比例"
																	 value="${mis.percent}">
															</c:when>
															<c:otherwise>
																<div style="flex-shrink: 0">
																	<input type="checkbox" class="head" value="1">是否获得二级收益（大区经理模式）
																</div>
																<input class="form-control pc" placeholder="请输入二级分配比例"
																	 value="${mis.percent}">
															</c:otherwise>
														</c:choose>
													</div>
													<button class="btn btn-success change">确认修改</button>
													<button class="btn btn-danger del">删除</button>
												</div>
											</c:forEach>
											
										</div>
										<div class="col-lg-6">
											<div>
												<label>收益设置</label>
											</div>
											<button class="btn btn-primary" id="addallot">添加收益级别</button>
											<button class="btn btn-danger" id="delallot">重设级别及收益</button>
											<c:forEach var="mis" items="${requestScope.atl}">
												<div class="form-group"
													style="display: flex; flex-direction: row; just-content: flex-start; align-items: center; margin-top: 1%; margin-bottom: 1%">
													<input placeholder="请输入起始金额（万元）" class="form-control start"
														type="number" style="width: 30%" value="${mis.start }"><input
														placeholder="请输入终止金额（万元）" type="number"
														class="form-control end" style="width: 30%" value="${mis.end }"><input
														placeholder="请输入分配比例（%）" type="number"
														class="form-control percent" style="width: 30%" value="${mis.percent }">
														<input type="hidden" class="id" value="${mis.id }">
													<button class="btn btn-success changea">确认修改</button>
												</div>
											</c:forEach>
											<div id="newa">
												<div class="form-group"
													style="display: flex; flex-direction: row; just-content: flex-start; align-items: center; margin-top: 1%; margin-bottom: 1%">
													<input placeholder="请输入起始金额（万元）" class="form-control start"
														type="number" style="width: 30%"><input
														placeholder="请输入终止金额（万元）" type="number"
														class="form-control end" style="width: 30%"><input
														placeholder="请输入分配比例（%）" type="number"
														class="form-control percent" style="width: 30%">
													<button class="btn btn-success newa">确认新增</button>
												</div>

											</div>
										</div>
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
					$("#newc").hide()
					$("#newa").hide()
					$("#newcooperate").click(function(){
						$("#newc").show()
					})
					$("#addallot").click(function(){
						$("#newa").show()
					})
					$(".newc").click(function(){
						var money=$(this).parent().find(".money").val()
						if($(this).parent().find(".name").val()==""||$(this).parent().find(".money").val()==""||$(this).parent().find(".des").val()==""){
							alert("请输入内容")
						}else{
							if(money-parseInt(money)==0){
								$.ajax({
									type : "POST",
									url : "<%=basePath%>mana/addcooperate",
											data : {
												"name" : $(this).parent().find(".name").val(),
												"money":money,
												"des":$(this).parent().find(".des").val(),
												"pay":$(this).parent().find("input[class='pay']").is(':checked'),
												"hospital":$(this).parent().find("input[class='hospital']").is(':checked'),
												"head":$(this).parent().find("input[class='head']").is(':checked'),
												"percent":$(this).parent().find("#pc").val(),
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
					})
					$(".change").click(function(){
						
						var money=$(this).parent().find(".money").val()
						if($(this).parent().find(".name").val()==""||$(this).parent().find(".money").val()==""||$(this).parent().find(".des").val()==""){
							alert("请输入内容")
						}else{
							if(money-parseInt(money)==0){
								$.ajax({
									type : "POST",
									url : "<%=basePath%>mana/addcooperate",
											data : {
												"name" : $(this).parent().find(".name").val(),
												"money":money,
												"des":$(this).parent().find(".des").val(),
												"pay":$(this).parent().find("input[class='pay']").is(':checked'),
												"hospital":$(this).parent().find("input[class='hospital']").is(':checked'),
												"head":$(this).parent().find("input[class='head']").is(':checked'),
												"percent":$(this).parent().find(".pc").val(),
												"id":$(this).parent().find(".id").val()
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
								alert("请检查信息")
							}
						}
					})
					$(".del").click(function(){
						$.ajax({
							type : "POST",
							url : "<%=basePath%>mana/delcooperate",
									data : {
										"id":$(this).parent().find(".id").val()
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
					$(".newa").click(function(){
						var start=$(this).parent().find(".start").val()
						var end=$(this).parent().find(".end").val()
						var percent=$(this).parent().find(".percent").val()
						if(start==""||end==""||percent==""){
							alert("请填写信息")
						}else{
							if(start-parseInt(start)==0&&end-parseInt(end)==0&&percent-parseInt(percent)==0&&parseInt(start)<parseInt(end)){
								$.ajax({
									type : "POST",
									url : "<%=basePath%>mana/addallot",
											data : {
												"start":start,
												"end":end,
												"percent":percent,
												"id":0
											},
											dataType : 'json',//返回值是json
											async : false,//同步请求
											success : function(data) {
												if (data.result == 1) {
													//alert("登录成功！");
													window.location.reload();
												} else {
													alert(data.msg);
												}
											},
											error : function(data) {
												alert("login error!");
												alert(JSON.stringify(data));
											}
										}); 
							}else{
								alert("请检查信息")
							}
						}
					})
					$(".changea").click(function(){
						var start=$(this).parent().find(".start").val()
						var end=$(this).parent().find(".end").val()
						var percent=$(this).parent().find(".percent").val()
						if(start==""||end==""||percent==""){
							alert("请填写信息")
						}else{
							if(start-parseInt(start)==0&&end-parseInt(end)==0&&percent-parseInt(percent)==0&&parseInt(start)<parseInt(end)){
								$.ajax({
									type : "POST",
									url : "<%=basePath%>mana/addallot",
											data : {
												"start":start,
												"end":end,
												"percent":percent,
												"id":$(this).parent().find(".id").val()
											},
											dataType : 'json',//返回值是json
											async : false,//同步请求
											success : function(data) {
												if (data.result == 1) {
													//alert("登录成功！");
													window.location.reload();
												} else {
													alert(data.msg);
												}
											},
											error : function(data) {
												alert("login error!");
												alert(JSON.stringify(data));
											}
										}); 
							}else{
								alert("请检查信息")
							}
						}
					})
					$("#delallot").click(function(){
						$.ajax({
							type : "POST",
							url : "<%=basePath%>mana/delallot",
									
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
				
			</script>
</body>
</html>