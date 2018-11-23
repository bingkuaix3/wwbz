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
							<li><a href="<%=basePath%>mana/applycontrol">经销商功能设置</a></li>
							<li><a href="<%=basePath%>mana/applyquestion">经销商答卷</a></li>
							<li><a href="<%=basePath%>mana/applylist">经销商申请列表</a></li>
							<li><a href="<%=basePath%>mana/dealapply" class="active">经销商列表</a></li>
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
						<h1 class="page-header">申请详情</h1>
					</div>
					<!-- /.col-lg-12 -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading"></div>
								<div class="panel-body">
									<div class="row">
										<div class="col-lg-6">
											<form role="form" action="<%=basePath%>mana/passarchives"
												method="post" 
												onsubmit="return validate_required()">
												<div class="form-group">
													<label>照片</label> 
													<div><img alt="" src="${requestScope.archives.wxicon }" style="width:80px;height:80px"></div>
												</div>
												<div class="form-group">
													<label>申请者姓名:&nbsp&nbsp&nbsp&nbsp${requestScope.archives.name }</label>
													<input type="hidden" name="id" id="id"
														value="${requestScope.archives.id }">
												</div>
												<div class="form-group">
													<label>性别:&nbsp&nbsp&nbsp&nbsp<span> <c:choose>
																<c:when test="${requestScope.archives.sex==1}">
														男
													</c:when>
																<c:otherwise>
															 女
													</c:otherwise>
															</c:choose>
													</span></label>

												</div>
												<div class="form-group">
													<label>民族:&nbsp&nbsp&nbsp&nbsp${requestScope.archives.nation }</label>
												</div>
												<div class="form-group">
													<label>身高:&nbsp&nbsp&nbsp&nbsp${requestScope.archives.height }cm</label>
												</div>
												
												<div class="form-group">
													<label>体重:&nbsp&nbsp&nbsp&nbsp${requestScope.archives.weight }Kg</label>
												</div>
												
												<div class="form-group">
													<label>联系电话:&nbsp&nbsp&nbsp&nbsp${requestScope.archives.tel }</label>
												</div>
												<div class="form-group">
													<label>曾经空腹血糖（FPG）:&nbsp&nbsp&nbsp&nbsp${requestScope.archives.fpg }mmo/l</label>
												</div>
												<div class="form-group">
													<label>曾经餐后血糖（VPG）:&nbsp&nbsp&nbsp&nbsp${requestScope.archives.vpg }mmo/l</label>
												</div>
												<div class="form-group">
													<label>血压:&nbsp&nbsp&nbsp&nbsp${requestScope.archives.low }-${requestScope.archives.high }</label>
												</div>

												<div class="form-group">
													<label>病历:</label>
													<c:forEach var="mi" items="${requestScope.illlist}">
														<div>疾病名称:${mi.ill }&nbsp&nbsp&nbsp&nbsp药物或措施:${mi.med }&nbsp&nbsp&nbsp&nbsp最终结果:${mi.result }</div>
													</c:forEach>
												</div>
												<div class="form-group">
													<label>记录图片</label>
													<div id="picdiv"
														style="display: flex; flex-direction: row; justify-content: flex-start; align-items: center;">
												
													</div>
												</div>
												<div class="form-group">
													<!-- <input type="radio" value="2" name="check">不予通过 -->
													<input type="radio" value="-1" name="check">不予通过
													<input type="radio" value="3" name="check">通过审核（不展示首页）
													<input type="radio" value="4" name="check">展示首页
												</div>
												<div class="form-group">
													<input type="number" class="form-control" value="${requestScope.archives.order}" name="order" id="order">
												</div>
												<button type="submit" class="btn btn-default">通过审核</button>
												<button type="button" class="btn btn-default"
													onclick="backto()">返回</button>
											</form>
										</div>
										<div class="col-lg-6">
											<div class="panel-heading">血糖记录</div>
											<c:forEach var="mi" items="${requestScope.list}" varStatus="item">
												<div style="background: #f2f2f2;margin-top:10px">
													<div>${mi.time}</div>
													<div>
														<label>曾经空腹血糖（FPG）:&nbsp&nbsp&nbsp&nbsp${mi.fpg}mmo/l</label>&nbsp&nbsp&nbsp&nbsp
														<label>曾经餐后血糖（VPG）:&nbsp&nbsp&nbsp&nbsp${mi.vpg}mmo/l</label>&nbsp&nbsp&nbsp&nbsp
														<label>曾经餐后血糖（VPG）:&nbsp&nbsp&nbsp&nbsp${mi.vpg}mmo/l</label>
													</div>
													<div>
														<label>血压:&nbsp&nbsp&nbsp&nbsp${mi.low }-${mi.high }</label>
													</div>
													<div>
														<label>描述:&nbsp&nbsp&nbsp&nbsp${mi.des }</label>
													</div>
													<div style="display: flex; flex-direction: row; justify-content: flex-start; align-items: center;">
														<c:forEach var="pic" items="${mi.pic}" varStatus="i">
															<img src="${pic}" style='widht:100px;height:100px'>
														</c:forEach>
													</div>
												</div>
											</c:forEach>
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
					$("#com").find("option[value='${requestScope.record.com }']").attr("selected",true);
					var tep=(${requestScope.archives.pic}+"").split(",");
					for(var i=0;i<tep.length;i++){
						var str="<img src='"+tep[i]+"' style='widht:100px;height:100px'>";
						$("#picdiv").append(str);
					}
					var result=${requestScope.archives.state}
					if(result==-1){
						$("input[value='-1']").attr("checked","checked")
					}else if(result==3){
						$("input[value='3']").attr("checked","checked")
					}else if(result==4){
						$("input[value='4']").attr("checked","checked")
					}
					
				})
				function backto() {
					window.history.go(-1);
				}
				function validate_required() {
					if($("input[name='check']:checked").val()==undefined){
						alert("请选择审核结果")
						return false
					}else{
						if($("#order").val()==""){
							alert("序号为空")
							return false

						}
						return true
					}
				}
				
			</script>
</body>
</html>