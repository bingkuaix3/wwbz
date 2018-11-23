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
							<li><a href="<%=basePath%>mana/addbanner?id=0" class="active">新增轮播</a></li>
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
						<h1 class="page-header">新增首页轮播</h1>
					</div>
					<!-- /.col-lg-12 -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading"></div>
								<div class="panel-body">
									<div class="row">
										<div class="col-lg-6">
											<form role="form" action="<%=basePath%>mana/savebanner"
												method="post" enctype="multipart/form-data"
												onsubmit="return validate_required()">
												<div class="form-group">
													<label>轮播名称</label> <input class="form-control"
														placeholder="请输入轮播名称" name="name"
														value="${requestScope.bn.name }" id="name">
													<input type="hidden" name="id" id="id"
														value="${requestScope.bn.id }">
												</div>
												<div class="form-group">
													<label>轮播图片(请保证所有轮播图片比例)</label> <input type="file"
														name="url" id="url"
														onchange="changImg(event,this.id)"> <img alt="再无图片"
														id="myImg" src="${requestScope.bn.url}" height="100px"
														style="margin-top: 2%">
												</div>
												<div class="form-group">
													<label>轮播顺序</label>
													<input class="form-control" value="${requestScope.bn.index}" name="index" id="index">
												</div>
												<div class="form-group">
													<label>轮播类别</label> <select class="form-control" id="kind" name="kind">
														<option value="-1">请选择轮播种类</option>
														<option value="0">小程序页面</option>
														<option value="1">公众号文章</option>
														<option value="2">新页面（图片）</option>
													</select>
												</div>
												<div class="form-group" id="one">
													<label>可链接小程序页面</label> <select class="form-control" id="page" name="page">
														<option value="">请选择小程序页面</option>
														<!-- <option value="../../pages/tree/tree">微金豆</option> -->
														<option value="../../pages/address/address">地址管理页面</option>
														<option value="../../pages/archives/archives">健康档案页面（分享页）</option>
														<option value="../../pages/cart/cart">购物车页面</option>
														<option value="../../pages/dealer/dearler">二维码页面（申请/展示）</option>
														<option value="../../pages/diabetes/diabetes">糖尿病介绍页面</option>
														<option value="../../pages/letter/letter">给糖尿病友一封信页面</option>
														<option value="../../pages/newxt/newxt">新增血糖页面</option>
														<option value="../../pages/order/order">订单页面</option>
														<option value="../../pages/reward/reward">积分详情页面</option>
														<option value="../../pages/sharelist/sharelist">健康档案分享列表页（详细）</option>
														<option value="../../pages/store/store">服务中心页面</option>
														<option value="../../pages/bargain/bargain">砍价活动页面</option>
														<!-- <option value="../../pages/diabetesactivity/diabetesactivity">血糖仪活动页面</option>
														<option value="../../pages/invite/invite">邀请函</option> -->
													</select>
												</div>
												<div class="form-group" id="two">
													<label>公众号文章链接</label> 
													<input class="form-control"
														placeholder="请输入文章网络地址" name="address"
														value="${requestScope.bn.content }" id="address">
												</div>
												<div class="form-group" id="three">
													<label>新页面图片（长图）</label> <input type="file"
														name="lpic" id="lpic"
														onchange="changImg(event,this.id)"> <img alt="再无图片"
														id="myLimg" src="${requestScope.bn.content}" height="100px"
														style="margin-top: 2%">
												</div>
												<button type="submit" class="btn btn-default">修改</button>
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
					$("#one").hide()
					$("#two").hide()
					$("#three").hide()
					$("#kind").val(${requestScope.bn.kind })
					if($("#kind").val()=="0"){
						$("#one").hide()
						$("#two").hide()
						$("#three").hide()
						$("#one").show()
						var value="${requestScope.bn.content }";
						$("option[value='${requestScope.bn.content }']").attr("selected","selected")
					}else if($("#kind").val()=="1"){
						$("#one").hide()
						$("#two").hide()
						$("#three").hide()
						$("#two").show()
					}else if($("#kind").val()=="2"){
						$("#one").hide()
						$("#two").hide()
						$("#three").hide()
						$("#three").show()
					}else if($("#kind").val()=="-1"){
						$("#one").hide()
						$("#two").hide()
						$("#three").hide()
					}
				
					$('#dataTables-example').DataTable({
						responsive : true
					});
					
					$("#kind").change(function() {
						if($(this).val()=="0"){
							$("#one").hide()
							$("#two").hide()
							$("#three").hide()
							$("#one").show()
						}else if($(this).val()=="1"){
							$("#one").hide()
							$("#two").hide()
							$("#three").hide()
							$("#two").show()
						}else if($(this).val()=="2"){
							$("#one").hide()
							$("#two").hide()
							$("#three").hide()
							$("#three").show()
						}else if($(this).val()=="-1"){
							$("#one").hide()
							$("#two").hide()
							$("#three").hide()
						}
					});
					$("#page").change(function() {
						
					});
				})
				function backto() {
					window.history.go(-1);
				}
				function validate_required() {
					if($("#id").val()==0){
						if ($("#name").val() == ""
							|| $("#url").val() == ""||$("#kind").val()=="-1"||$("#index").val()=="") {
						alert("请补全信息")
						return false
					} else {
						if($("#kind").val()=="0"){
							if($("#page").val()==""){
								alert("请选择页面")
								return false
							}else{
	
								return true
							}
						}else if($("#kind").val()=="1"){
							if($("#address").val()==""){
								alert("请填写网址")
								return false
							}else{

								return true
							}
						}else if($("#kind").val()=="2"){
							if($("#lpic").val()==""){
								alert("请上传图片")
								return false
							}else{

								return true
							}
						}
						
					}
					}else{
						if ($("#name").val() == ""
							||$("#kind").val()=="-1"||$("#index").val()=="0") {
						alert("请补全信息")
						return false
					} else {
						if($("#kind").val()=="0"){
							if($("#page").val()==""){
								alert("请选择页面")
								return false
							}else{
	
								return true
							}
						}else if($("#kind").val()=="1"){
							if($("#address").val()==""){
								alert("请填写网址")
								return false
							}else{

								return true
							}
						}else if($("#kind").val()=="2"){
							if($("#lpic").val()==""){
								return true
							}else{

								return true
							}
						}
						
					}
					}
					
				}
				function changImg(e, id) {
					for (var i = 0; i < e.target.files.length; i++) {
						var file = e.target.files.item(i);
						if (!(/^image\/.*$/i.test(file.type))) {
							continue; //不是图片 就跳出这一次循环  
						}
						//实例化FileReader API  
						var freader = new FileReader();
						freader.readAsDataURL(file);
						if (id == "url") {
							freader.onload = function(e) {
								$("#myImg").attr("src", e.target.result);
							}
						} else if (id == "lpic") {
							freader.onload = function(e) {
								$("#myLimg").attr("src", e.target.result);
							}

						

						}

					}
				}
			</script>
</body>
</html>