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
						<h1 class="page-header">销售分成及历史记录页面</h1>
					</div>
					<!-- /.col-lg-12 -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading"></div>
								<div class="panel-body">
									<div class="row">
										<div class="col-lg-6">
											<div class="panel panel-default">
												<div class="panel-heading">经销商基本信息</div>
												<div class="panel-body">
													<div class="form-group">
														<label>经销商:${requestScope.dealer.name }</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>年龄:${requestScope.dealer.age }</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
														<c:choose>
															<c:when test="${requestScope.dealer.sex==1}">
																<label>性别:男</label>
															</c:when>
															<c:otherwise>
																<label>性别:女</label>
															</c:otherwise>
														</c:choose>
													</div>
													<div class="form-group">
														<label>联系电话:${requestScope.dealer.tel }</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>微信号:${requestScope.dealer.wechat }</label>
													</div>
													<div class="form-group">
														<label>合作模式:${requestScope.dealer.kind }</label> <label>实付定金:${requestScope.dealer.pay }元</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>预测销售额（3个月）:${requestScope.dealer.forecast }万元</label>
													</div>
													<div class="form-group">
														<label>模式细节:</label>
														<div style="text-align: justify">${requestScope.dealer.policy }</div>
													</div>

													<div class="form-group">
														<label>服务中心所在城市:${requestScope.dealer.city }</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>服务中心名称:${requestScope.dealer.storename }</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>服务中心详细地址:${requestScope.dealer.storeaddress }</label>
													</div>
												</div>
											</div>
											<div class="panel panel-default">
												<div class="panel-heading"
													style="display: flex; flex-direction: row; justify-content: space-between; align-items: center">
													<span>销售分成</span>
													<button class="btn btn-primary"
														onclick="detail('${requestScope.dealer.openid }')">查看销售订单细节</button>
												</div>
												<div class="panel-body">
													<div class="form-group">
														<label>历史总销售额:${requestScope.totalmoney}元</label>
													</div>
													<div class="form-group">
														<label>当前计算模式:</label>
														<c:forEach var="mi" items="${requestScope.allot}">
															<div>${mi.start }万~${mi.end }万
																&nbsp&nbsp&nbsp&nbsp分成比例:${mi.percent }%</div>
														</c:forEach>
													</div>
													<div class="form-group">
														<label>未分配总销售额:</label>
														<div>${requestScope.unliquidated }元</div>
													</div>
													
													<div class="form-group">
														<label>历史邀请总销售额:${requestScope.htotal }元 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp当前计算模式:销售邀请销售额${requestScope.hpercent }%分成</label>
													</div>
													<div class="form-group">
														<label>未分配邀请销售额:</label>
														<div>${requestScope.hunliquidated}元</div>
													</div>
													<div class="form-group">
														<label>此次应分配金额:</label>
														<div>${requestScope.money}元（销售）+${requestScope.hmoney}元（邀请）=
<fmt:formatNumber type="number" value="${requestScope.money+requestScope.hmoney}" pattern="0.00" maxFractionDigits="2"/>元</div>
													</div>
													<div class="form-group">
														<c:choose>
															<c:when test="${requestScope.dealer.account==0}">
																<label>发放分成:(暂无打款信息,请联系经销商填写信息)</label>
															</c:when>
															<c:otherwise>
																<div>
																	<label>开户行:${requestScope.dealer.bank}</label>
																</div>
																<div>
																	<label>所属支行:${requestScope.dealer.branch}</label>
																</div>
																<div>
																	<label>银行账号:${requestScope.dealer.account}</label>
																</div>
																<div>
																	<label>姓名:${requestScope.dealer.bankname}</label>
																</div>
																<label>发放分成:</label>
																<div
																	style="display: flex; flex-direction: row; justify-content: flex-start; align-items: center">
																	<input class="form-control" style="width: 40%;"
																		placeholder="请输入本次记录名称" id="name">
																		<input type="hidden" value="${requestScope.dealer.unliquidated }" id="money">
																	<button class="btn btn-primary" style="margin-left: 2%"
																		onclick="pay(${requestScope.dealer.id },${requestScope.unliquidated},${requestScope.money},${requestScope.hunliquidated },${requestScope.hmoney })">确认发放</button>
																</div>
															</c:otherwise>
														</c:choose>
														
													</div>
												</div>
												
											</div>
											
										</div>
										<div class="col-lg-6">
										<div class="panel panel-default">
											<div class="panel-heading">分成记录</div>
											<div class="panel-body">
												<c:forEach var="mi" items="${requestScope.dl}">
													<div>${mi.name }:于${mi.time }根据(${mi.allot })分配销售额${mi.money }元(实际分配${mi.out }元);根据1%分配邀请销售额${mi.headmoney }(实际分配${mi.headout }元)：共<fmt:formatNumber type="number" value="${mi.out+mi.headout}" pattern="0.00" maxFractionDigits="2"/>元</div>
												</c:forEach>
											</div>
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
							|| $("#url").val() == ""||$("#kind").val()=="-1") {
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
							||$("#kind").val()=="-1") {
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
				function detail(openid){
					window.location.href = "<%=basePath%>mana/allotdetail?openid=" +openid;
					
				}
				function pay(id,total,money,htotal,hmoeny){
					if($("#name").val()==""){
						alert("请输入本次分配名称")
					}else{
						if(money==0&&hmoeny==0){
							alert("没有可分配分成")
						}else{
							 $.ajax({
								type : "POST",
								url : "<%=basePath%>mana/divide",
										data : {
											"id" : id,
											"name": $("#name").val(),
											"bank": "${requestScope.dealer.bank }",
											"branch": "${requestScope.dealer.branch }",
											"bankname": "${requestScope.dealer.bankname}",
											"account": "${requestScope.dealer.account }",
											"total":total,
											"money":money,
											"htotal":htotal,
											"hmoeny":hmoeny,
										},
										dataType : 'json',//返回值是json
										async : false,//同步请求
										success : function(data) {
											if (data.result == 1) {
												alert("记录成功");
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
						
					}
					
				}
			</script>
</body>
</html>