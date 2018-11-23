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
<meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<title>任务管理</title>
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
<!-- Custom Fonts -->
<link href="<%=basePath%>vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="<%=basePath%>dist/css/sb-admin-2.css" rel="stylesheet">
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


		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<%-- <li><a href="#"><i class="glyphicon glyphicon-time"></i>
							限时抢购<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>mana/mission" >活动列表</a></li>
							<li><a href="<%=basePath%>mana/addmission?id=0">新增活动</a></li>
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
							<li><a href="<%=basePath%>mana/goods">商品列表</a></li>
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
							<li><a href="<%=basePath%>mana/archiveslist"  class="active">申请分享列表</a></li>
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
						<h1 class="page-header">申请分享列表</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">健康档案</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<table width="100%"
									class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>姓名</th>
											<th>头像</th>
											<th>更新时间</th>
											<th>状态</th>
											
										</tr>
									</thead>
									<tbody style="cursor: pointer;">
										<c:forEach var="mi" items="${requestScope.list}">
											<tr class="odd gradeX">
												<td onclick="clicklist(${mi.id})">${mi.name}</td>
												<td onclick="clicklist(${mi.id})"><img alt=""
													src="${mi.wxicon}" style="width: 30px; height: 30px"></td>
												<td onclick="clicklist(${mi.id})">${mi.time}</td>
												<td onclick="clicklist(${mi.id})">申请分享</td>
												
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!-- /.table-responsive -->
							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
					<!-- /.col-lg-12 -->
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
				responsive: true<%-- ,
				"bPaginate": true, //翻页功能
				"bLengthChange": true, //改变每页显示数据数量
				"bFilter": true, //过滤功能
				"bSort": true, //排序功能
				"bInfo": true,//页脚信息
				"bAutoWidth": true,//自动宽度
				aLengthMenu:[10,25,50,100],
				sAjaxSource:"<%=basePath%>mana/orderlistajax",
				 "iDisplayLength": 10,
				 "aoColumns": [{
		                "mData": 'goodsname',
		                "sTitle": "商品名称",
		                "bSortable": true,
		                "mRender": function ( data, type, full ) { 
	        				 return '<div onclick="clicklist('+full.id+')">'+full.goodsname+'</div>'; 
	      				} 
		            }, {
		                "mData": 'buytime',
		                "sTitle": "下单时间",
		                "bSortable": true
		                ,
		                "mRender": function ( data, type, full ) { 
	        				 return '<div onclick="clicklist('+full.id+')">'+full.buytime+'</div>'; 
	      				} 
		            }, {
		                "mData": 'number',
		                "sTitle": "数量",
		                "bSortable": true
		                ,
		                "mRender": function ( data, type, full ) { 
	        				 return '<div onclick="clicklist('+full.id+')">'+full.number+'</div>'; 
	      				} 
		                
		            }, {
		                "mData": 'money',
		                "sTitle": "消费金额",
		                "bSortable": true,
		                "mRender": function ( data, type, full ) { 
	       				 return '<div onclick="clicklist('+full.id+')">'+full.money+'</div>'; 
	     				} 
		                
		            }],
				"sLengthMenu": "每页显示 _MENU_ 条记录",
				"sZeroRecords": "抱歉， 没有找到",
				"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
				"sInfoEmpty": "没有数据",
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
				"sZeroRecords": "没有检索到数据",
				"sSearch": "名称:",
				"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "前一页",
				"sNext": "后一页",
				"sLast": "尾页"
				},
				"sPaginationType": "full_numbers",

				"oLanguage": {
				"sLengthMenu": "每页显示 _MENU_ 条记录",
				"sZeroRecords": "抱歉， 没有找到",
				"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
				"sInfoEmpty": "没有数据",
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
				"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "前一页",
				"sNext": "后一页",
				"sLast": "尾页"
				},
				"sZeroRecords": "没有检索到数据",
				"sProcessing": "<img src='./loading.gif' />"
				},
 --%>
				});
		});
		function clicklist(i) {
			//alert("进入" + i)
			window.location.href = "<%=basePath%>mana/archives?id=" + i;
		}
		function dellist(i) {
			var con = confirm("确定删除任务吗？")
			if (con == true) {
				//alert("确定")
				$.ajax({
					type : "POST",
					url : "<%=basePath%>mana/delmission",
							data : {
								"id" : i
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
		}
		
	</script>
</body>
</html>