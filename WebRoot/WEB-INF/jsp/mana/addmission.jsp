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
				<%-- 	<li><a href="#"><i class="glyphicon glyphicon-time"></i>
							限时抢购<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/mission">活动列表</a></li>
							<li><a href="<%=basePath%>mana/addmission?id=0"
								class="active">新增活动</a></li>
						</ul> <!-- /.nav-second-level --></li> --%>
						<li><a href="#"><i class="glyphicon glyphicon-shopping-cart"></i>
							商品管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/goods">商品列表</a></li>
							<li><a href="<%=basePath%>mana/addgoods?id=0&kind=0">新增商品（正常）</a></li>
							<li><a href="<%=basePath%>mana/addgoods?id=0&kind=1">新增商品（积分）</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-file"></i>
							文章管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/articlelist">文章列表</a></li>
							<li><a href="<%=basePath%>mana/addarticle?id=0">新增文章</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-thumbs-up"></i>
							客户见证<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/customerlist">见证列表</a></li>
							<li><a href="<%=basePath%>mana/addcustomer?id=0">新增见证</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-signal"></i>
							订单管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/orderlist">未处理订单</a></li>
							<li><a href="<%=basePath%>mana/deallist">已处理订单</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-globe"></i>
							线下店管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/storelist">线下店列表</a></li>
							<li><a href="<%=basePath%>mana/addstore?id=0">线下店新增</a></li>
						</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"><i class="glyphicon glyphicon-qrcode"></i>
							经销商管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/applylist">经销商申请列表</a></li>
							<li><a href="<%=basePath%>mana/dealapply">经销商列表</a></li>
						</ul> <!-- /.nav-second-level --></li>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">限时活动</h1>
					</div>
					<!-- /.col-lg-12 -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading"></div>
								<div class="panel-body">
									<div class="row">
										<div class="col-lg-6">
											<form role="form" action="<%=basePath%>mana/savemission"
												method="post" enctype="multipart/form-data"
												onsubmit="return validate_required()">
												<div class="form-group">
													<label>限时商品名称</label> <input class="form-control"
														placeholder="请输入商品名称" name="mission_name"
														value="${requestScope.goods.name }" id="mission_name">
													<input type="hidden" name="mission_id" id="mission_id"
														value="${requestScope.goods.id }">
												</div>
												<div class="form-group">
													<label>商品图片(方形)</label> <input type="file"
														name="mission_pic" id="mission_pic"
														onchange="changImg(event)"> <img alt="再无图片"
														id="myImg" src="${requestScope.goods.pic}" height="100px"
														style="margin-top: 2%">
												</div>
												<div class="form-group">
													<label>商品图片(横板)</label> <input type="file"
														name="mission_diagram" id="mission_diagram"
														onchange="change(event)"> <img alt="再无图片"
														id="diagram" src="${requestScope.goods.diagram}"
														height="100px" style="margin-top: 2%">
												</div>
												<div class="form-group">
													<label>活动开始时间</label>
													<div
														style="display: flex; flex-direction: row; justify-content: flex-start; align-items: center;">
														<input class="form-control" style="width: 50%"
															name="mission_stime" id="mission_stime"
															value="<fmt:formatDate value="${requestScope.goods.stime}" pattern="yyyy/MM/dd HH:mm:ss" />">
													</div>
												</div>
												<div class="form-group">
													<label>活动结束时间</label>
													<div
														style="display: flex; flex-direction: row; justify-content: flex-start; align-items: center;">
														<input class="form-control" style="width: 50%"
															name="mission_etime" id="mission_etime"
															value="<fmt:formatDate value="${requestScope.goods.etime}" pattern="yyyy/MM/dd HH:mm:ss" />">
													</div>
												</div>
												<div class="form-group">
													<label>原价</label> <input class="form-control"
														placeholder="请输入活动商品原价" name="mission_price" step="0.01"
														value="${requestScope.goods.price }" id="mission_price"
														type="number">
												</div>
												<div class="form-group">
													<label>限时价格</label> <input class="form-control"
														placeholder="请输入限时价格" name="mission_sale" step="0.01"
														value="${requestScope.goods.sale }" id="mission_sale"
														type="number">
												</div>
												<div class="form-group">
													<label>活动商品数量</label> <input class="form-control"
														placeholder="请输入活动商品数量" name="mission_number"
														value="${requestScope.goods.number }" id="mission_number">
												</div>
												<div class="form-group">
													<label>活动简介</label>
													<textarea class="form-control" rows="3" name="mission_des"
														id="customer_des">${requestScope.goods.des}</textarea>
												</div>
												<c:choose>
													<c:when test="${requestScope.goods.state>0}">

													</c:when>
													<c:otherwise>
														<button type="submit" class="btn btn-default">修改</button>
													</c:otherwise>
												</c:choose>

												<button type="button" class="btn btn-default"
													onclick="backto()">返回</button>
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
					$("#mission_stime").click(function() {
						$(this).attr("type", "datetime-local")
					})
					$("#mission_stime").blur(function() {
						if ($(this).val() == "") {
							$(this).attr("type", "")
						}
					})
					$("#mission_etime").click(function() {
						$(this).attr("type", "datetime-local")
					})
					$("#mission_etime").blur(function() {
						if ($(this).val() == "") {
							$(this).attr("type", "")
						}
					})
				})
				function backto() {
					window.history.go(-1);
				}

				function validate_required() {

					if ($("#mission_id").val() == 0) {
						if ($("#mission_pic").val() == ""
								|| $("#diagram").val()=="") {
							alert("请上传图片")
							return false
						}
					}
					if ($("#mission_name").val() == ""
							|| $("#mission_stime").val() == ""
							|| $("#mission_etime").val() == ""
							|| $("#mission_price").val() == ""
							|| $("#mission_sale").val() == ""
							|| $("#mission_des").val() == ""
							|| $("#mission_number").val() == "") {

						alert("请提交正确信息")
						return false
					} else {
						if ($("#mission_stime").val().length < 17) {
							$("#mission_stime").val(
									($("#mission_stime").val() + ":00"))

						}
						if ($("#mission_etime").val().length < 17) {
							$("#mission_etime").val(
									($("#mission_etime").val() + ":00"))
						}
						if (Date.parse($("#mission_stime").val().replace("T",
								" ")) >= Date.parse($("#mission_etime").val()
								.replace("T", " "))
								|| Date.parse(new Date()) > Date.parse($(
										"#mission_stime").val().replace("T",
										" "))) {

							alert("请提交正确起止时间")
							return false
						} else {
							if ($("#mission_pic").val() == ""
									|| $("#diagram").val()=="") {
								//alert("请上传图片")
								var con = confirm("您还没有上传任务图片，是否继续提交？");
								return con;
							} else {
								return true
							}
						}

					}

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
							$("#myImg").attr("src", e.target.result);
						}

					}

				}
				function change(e) {

					for (var i = 0; i < e.target.files.length; i++) {
						var file = e.target.files.item(i);
						if (!(/^image\/.*$/i.test(file.type))) {
							continue; //不是图片 就跳出这一次循环  
						}
						//实例化FileReader API  
						var freader = new FileReader();
						freader.readAsDataURL(file);
						freader.onload = function(e) {
							$("#diagram").attr("src", e.target.result);
						}
					} 
				}
			</script>
</body>
</html>