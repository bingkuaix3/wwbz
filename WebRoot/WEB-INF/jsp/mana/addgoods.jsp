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
					<li><a href="#"><i
							class="glyphicon glyphicon-shopping-cart"></i> 商品管理<span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/category" >商品类别管理</a></li>
							<li><a href="<%=basePath%>mana/goods">商品列表</a></li>
							<c:choose>
								<c:when test="${ requestScope.goods.kind==0 }">
									<li><a href="<%=basePath%>mana/addgoods?id=0&kind=0"
										class="active">新增商品（正常）</a></li>
									<li><a href="<%=basePath%>mana/addgoods?id=0&kind=1">新增商品（积分）</a></li>
								</c:when>
								<c:when test="${ requestScope.goods.kind==1 }">
									<li><a href="<%=basePath%>mana/addgoods?id=0&kind=0">新增商品（正常）</a></li>
									<li><a href="<%=basePath%>mana/addgoods?id=0&kind=1"
										class="active">新增商品（积分）</a></li>
								</c:when>
							</c:choose>
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
						<h1 class="page-header">
							<c:choose>
								<c:when test="${ requestScope.goods.kind==0 }">
									新增商品(正常)
								</c:when>
								<c:when test="${ requestScope.goods.kind==1 }">
									新增商品（积分）
								</c:when>
							</c:choose>
						</h1>
					</div>
					<!-- /.col-lg-12 -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading"></div>
								<div class="panel-body">
									<div class="row">
										<div class="col-lg-6">
											<form role="form" action="<%=basePath%>mana/savegoods"
												method="post" enctype="multipart/form-data"
												onsubmit="return validate_required()">
												<div class="form-group">
													<label>商品名称</label> <input class="form-control"
														placeholder="请输入商品名称" name="mission_name"
														value="${requestScope.goods.name }" id="mission_name">
													<input type="hidden" name="mission_id" id="mission_id"
														value="${requestScope.goods.id }">
														<input type="hidden" name="mission_kind" id="mission_kind"
														value="${ requestScope.goods.kind }">
												</div>
												<div class="form-group">
												<label>商品类别</label>
													<select class="form-control" name="categroy" id="categroy">
														<c:forEach var="mi" items="${requestScope.cl}" >
															<c:choose>
																<c:when test="${ mi.id== requestScope.goods.categroyid}">
																	<option value="${mi.id }" selected>${mi.name }</option>
																</c:when>
																<c:otherwise>
																	<option value="${mi.id }">${mi.name }</option>
																</c:otherwise>
															</c:choose>

														</c:forEach>
													</select>
												</div> 
												<div class="form-group">
													<label> <c:choose>
															<c:when test="${ requestScope.goods.kind==0 }">
									价格(元)
								</c:when>
															<c:when test="${ requestScope.goods.kind==1 }">
									积分（豆）
								</c:when>
														</c:choose></label> <input class="form-control" placeholder="请输入商品价格"
														name="mission_price" step="0.01"
														value="${requestScope.goods.price }" id="mission_price"
														type="number">
												</div>
												<div class="form-group">
													<label>商品简介</label>
													<textarea class="form-control" rows="3" name="mission_des"
														id="customer_des">${requestScope.goods.des}</textarea>
												</div>
												<div class="form-group">
													<label>金豆抵用（数量）</label><input class="form-control"
														placeholder="请输入金豆数量" name="offset"
														value="${requestScope.goods.offset }"
														id="offset" type="number">
												</div>
												<div class="form-group">
													<label>商品规格</label><input class="form-control"
														placeholder="请输入商品规格" name="mission_standard"
														value="${requestScope.goods.standard }"
														id="mission_standard">
												</div>
												<div class="form-group">
													<label>商品封面(方形)</label> <input type="file"
														name="mission_pic" id="mission_pic" data="1"
														onchange="changImg(event,this.id)"> <img
														alt="再无图片" id="myImg" src="${requestScope.goods.pic}"
														height="100px" style="margin-top: 2%">
												</div>
												<div class="form-group">
													<label>商品详情</label> <input type="file"
														name="mission_diagram" id="mission_diagram"
														onchange="changImg(event,this.id)"> <img
														alt="再无图片" id="diagram"
														src="${requestScope.goods.diagram}" height="100px"
														style="margin-top: 2%">
												</div>
										</div>
										<div class="col-lg-6">

											<div class="form-group">
												<label>商品轮播图1（请保证轮播图比例统一）</label> <input type="file"
													name="mission_imgf" id="mission_imgf"
													onchange="changImg(event,this.id)"> <img alt="再无图片"
													id="imgf" src="${requestScope.goods.imgf}" height="100px"
													style="margin-top: 2%">
											</div>
											<div class="form-group">
												<label>商品轮播图2（请保证轮播图比例统一）</label> <input type="file"
													name="mission_imgs" id="mission_imgs"
													onchange="changImg(event,this.id)"> <img alt="再无图片"
													id="imgs" src="${requestScope.goods.imgs}" height="100px"
													style="margin-top: 2%">
											</div>
											<div class="form-group">
												<label>商品轮播图3（请保证轮播图比例统一）</label> <input type="file"
													name="mission_imgt" id="mission_imgt"
													onchange="changImg(event,this.id)"> <img alt="再无图片"
													id="imgt" src="${requestScope.goods.imgt}" height="100px"
													style="margin-top: 2%">
											</div>

										</div>
										<button type="submit" class="btn btn-default">修改</button>
										<button type="button" class="btn btn-default"
											onclick="backto()">返回</button>
										</form>
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

				})
				function backto() {
					window.history.go(-1);
				}
				function validate_required() {
					if($("#offset").val()>$("#mission_price").val()*100){
						alert("金豆数量过多！")
						return false
					}
					if ($("#mission_id").val() == 0) {
						if ($("#mission_pic").val() == ""
								|| $("#mission_diagram").val() == ""
								|| $("#mission_imgf").val() == ""
								|| $("#mission_imgs").val() == ""
								|| $("#mission_imgt").val() == "") {
							alert("请上传图片")
							return false
						}
					}
					if ($("#mission_name").val() == ""
							|| $("#mission_price").val() == ""
							|| $("#mission_des").val() == ""
							|| $("#offset").val() == "") {
						alert("请提交正确信息")
						return false
					} else {
						if ($("#mission_pic").val() == ""
								|| $("#mission_diagram").val() == ""
								|| $("#mission_imgf").val() == ""
								|| $("#mission_imgs").val() == ""
								|| $("#mission_imgt").val() == "") {
							//alert("请上传图片")
							var con = confirm("您还没有上传任务图片，是否继续提交？");
							return con;
						} else {
							return true
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
						if (id == "mission_pic") {
							freader.onload = function(e) {
								$("#myImg").attr("src", e.target.result);
							}
						} else if (id == "mission_diagram") {
							freader.onload = function(e) {
								$("#diagram").attr("src", e.target.result);
							}

						} else if (id == "mission_imgf") {
							freader.onload = function(e) {
								$("#imgf").attr("src", e.target.result);
							}

						} else if (id == "mission_imgs") {
							freader.onload = function(e) {
								$("#imgs").attr("src", e.target.result);
							}

						} else if (id == "mission_imgt") {
							freader.onload = function(e) {
								$("#imgt").attr("src", e.target.result);
							}

						}

					}

				}
			</script>
</body>
</html>