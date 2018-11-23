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
<script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp&key=LLMBZ-SZ7KI-X7WG7-5YFHV-T4SHS-TTBCM"></script>
<script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp&key=LLMBZ-SZ7KI-X7WG7-5YFHV-T4SHS-TTBCM&libraries=drawing,geometry,autocomplete,convertor
"></script>
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
					<%-- 	<li><a href="#"><i class="glyphicon glyphicon-globe"></i>
							线下店管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/storelist">线下店列表</a></li>
							<li><a href="<%=basePath%>mana/addstore?id=0" class="active">线下店新增</a></li>
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
											<div class="form-group">
												<label>申请人:${requestScope.dl.name }</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>年龄:${requestScope.dl.age }</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
												<c:choose>
													<c:when test="${requestScope.dl.sex==1}">
														<label>性别:男</label>
													</c:when>
													<c:otherwise>
														<label>性别:女</label>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="form-group">
												<label>联系电话:${requestScope.dl.tel }</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>微信号:${requestScope.dl.wechat }</label>
											</div>
											<div class="form-group">
												<label>合作模式:${requestScope.dl.kind }</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
												<label>实付定金:${requestScope.dl.pay }元</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>预测销售额（3个月）:${requestScope.dl.forecast }万元</label>
											</div>
											<div class="form-group">
												<label>模式细节:</label>
												<div style="text-align: justify">${requestScope.dl.policy }</div>
											</div>
											<div class="form-group">
												<label>调查答案:</label>
												<c:forEach var="mis" items="${requestScope.answer}">
													<div><span>${mis.question }</span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<span>${mis.answer }</span></div>
												</c:forEach>
											</div>
											
											<div class="form-group">
												<label>申请理由/工作思路:</label>
												<div style="text-align: justify">${requestScope.dl.des }</div>
											</div>
											<div class="form-group">
												<label>服务中心所在城市:${requestScope.dl.city }</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>服务中心名称:${requestScope.dl.storename }</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>服务中心详细地址:${requestScope.dl.storeaddress }</label>
											</div>
											<div class="form-group">
												<label>展示图片:</label>
												<div>
													<c:forEach var="mis" items="${requestScope.pic}">
														<img src="${mis}" style='widht: 200px; height: 200px;' alt="无" >
													</c:forEach>
												</div>
											</div>
											<c:choose>
												<c:when test="${requestScope.dl.isapply==1}">
													<div class="form-group">
														<button class="btn btn-danger"
															onclick="updatedel(${requestScope.dl.id })">不予通过</button>
														<button class="btn btn-primary"
															onclick="updatepass(${requestScope.dl.id })">通过</button>
													</div>
												</c:when>
												<c:otherwise>
													<div class="form-group">
														<button class="btn btn-danger"
															onclick="del(${requestScope.dl.id })">不予通过</button>
														<button class="btn btn-primary"
															onclick="pass(${requestScope.dl.id })">通过（无服务中心功能）</button>
														<button class="btn btn-success"
															onclick="realpass(${requestScope.dl.id })">合作（包含服务中心功能）</button>
													</div>
												</c:otherwise>
											</c:choose>
										<input type="hidden" value="${requestScope.dl.latitude }" id="latitude">
										<input type="hidden" value="${requestScope.dl.longitude }" id="longitude">
										</div>
										<div class="col-lg-6">
										<div id="container" style="width: 100%;height:800px;overflow: hidden;margin:0;font-family:"微软雅黑";"></div>
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
					var markersArray = []
					if(${requestScope.dl.id }==0){
						map = new qq.maps.Map(
								document.getElementById("container"),
								{
								center: new qq.maps.LatLng(43.845267324527164, 126.56627152101893),
								zoom: 13,
								draggable: true,
								scrollwheel: true,
								disableDoubleClickZoom: true
								}
								);
						qq.maps.event.addListener(map, 'click', function(event) {
							 
							$("#longitude").val(event.latLng.getLng())
					        $("#latitude").val(event.latLng.getLat())
						       var marker=new qq.maps.Marker({
						                position:event.latLng, 
						                map:map
						          });    
						      qq.maps.event.addListener(map, 'click', function(event) {
						            marker.setMap(null);      
						    });
						    });
					}else{
						if(${requestScope.dl.latitude }==0){
							map = new qq.maps.Map(
									document.getElementById("container"),
									{
									center: new qq.maps.LatLng(43.845267324527164, 126.56627152101893),
									zoom: 13,
									draggable: true,
									scrollwheel: true,
									disableDoubleClickZoom: true
									}
									);
							qq.maps.event.addListener(map, 'click', function(event) {
								 
								$("#longitude").val(event.latLng.getLng())
						        $("#latitude").val(event.latLng.getLat())
							       var marker=new qq.maps.Marker({
							                position:event.latLng, 
							                map:map
							          });    
							      qq.maps.event.addListener(map, 'click', function(event) {
							            marker.setMap(null);      
							    });
							    });
						}else{
							map = new qq.maps.Map(
									document.getElementById("container"),
									{
									center: new qq.maps.LatLng(${requestScope.dl.latitude }, ${requestScope.dl.longitude } ),
									zoom: 13,
									draggable: true,
									scrollwheel: true,
									disableDoubleClickZoom: true
									}
									);
							 
							var marker=new qq.maps.Marker({
				                position:new qq.maps.LatLng(${requestScope.dl.latitude },  ${requestScope.dl.longitude }),
				                map:map
				                
				          })
							qq.maps.event.addListener(map, 'click', function(event) {
								 
								$("#longitude").val(event.latLng.getLng())
						        $("#latitude").val(event.latLng.getLat())
							       var marker=new qq.maps.Marker({
							                position:event.latLng, 
							                map:map
							          });    
							      qq.maps.event.addListener(map, 'click', function(event) {
							            marker.setMap(null);      
							    });
							    });
						}
						 
						
					}
					
						
	
				})
				function backto() {
					window.history.go(-1);
				}
				function validate_required() {
					 $("#longitude").attr("disabled",false);
					 $("#latitude").attr("disabled",false);
					if($("#name").val()==""||$("#city").val()==""||$("#address").val()==""||$("#tel").val()==""||$("#longitude").val()==""||$("#latitude").val()==""){
						alert("请补全信息")
						
						$("#longitude").attr("disabled","disabled");
						 $("#latitude").attr("disabled","disabled");
						 return false
					}else {
						if(!(/^1[34578]\d{9}$/.test($("#tel").val()))){
							alert("请输入正确电话")
							
							$("#longitude").attr("disabled","disabled");
							 $("#latitude").attr("disabled","disabled");
							 return false
						}else{
							return true
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
				function del(i){
					window.location.href = "<%=basePath%>mana/applycontentdel?id=" + i;
				}
				function pass(i){
					if($("#latitude").val()==0||$("#longitude").val()==0){
						alert("请在地图上选择服务中心位置")
					}else{
						 window.location.href = "<%=basePath%>mana/applycontentpass?id=" + i+"&latitude="+$("#latitude").val()+"&longitude="+$("#longitude").val();
					}
					
					
				}
				function realpass(i){
					if($("#latitude").val()==0||$("#longitude").val()==0){
						alert("请在地图上选择服务中心位置")
					}else{
						window.location.href = "<%=basePath%>mana/applycontentrealpass?id=" + i+"&latitude="+$("#latitude").val()+"&longitude="+$("#longitude").val(); 
					}
					 
				}
				function updatepass(i){
					window.location.href = "<%=basePath%>mana/updatepass?id=" + i	
				}
				function updatedel(i){
					window.location.href = "<%=basePath%>mana/updatedel?id=" + i
				}
			</script>
</body>
</html>