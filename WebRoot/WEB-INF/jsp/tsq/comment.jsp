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
					
						<li><a href="#"><i class="glyphicon glyphicon-signal"></i>
							老用户评论<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="<%=basePath%>tsq/commentlist" >筛选列表</a></li>
							<li><a href="<%=basePath%>tsq/dealcommentlist">通过列表</a></li>
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
						<h1 class="page-header">老客户评论详情</h1>
					</div>
					<!-- /.col-lg-12 -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading"></div>
								<div class="panel-body">
									<div class="row">
										<div class="col-lg-6">
											<form role="form" action="<%=basePath%>tsq/passcomment"
												method="post" onsubmit="return validate_required()">
												<div class="form-group">
													<label>姓名：${requestScope.ct.nickname }</label>
												</div>
												<div class="form-group">
													<label>头像</label> <img src="${requestScope.ct.wxicon }"
														style="width: 40px; height: 40px"></img>
												</div>
												<div class="form-group">
													<label>城市：${requestScope.ct.city }</label>
												</div>
												<div class="form-group">
													<label>曾患病：${requestScope.ct.ill }</label>
												</div>
												<div class="form-group">
													<label>使用产品：<c:choose>
															<c:when test="${requestScope.ct.cl==1 }">长乐胶囊、</c:when>
															<c:otherwise></c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${requestScope.ct.jt==1 }">降糖奇、</c:when>
															<c:otherwise></c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${requestScope.ct.dw==1 }">多维粉、</c:when>
															<c:otherwise></c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${requestScope.ct.nd==1 }">鹰嘴纳豆</c:when>
															<c:otherwise></c:otherwise>
														</c:choose></label>
													<div class="form-group">
														<label>服用时间：${requestScope.ct.history }</label>
													</div>
													<div class="form-group">
														<label>评价：</label>
														<div>
															<textarea class="form-control" name="des" id="des">${requestScope.ct.content }</textarea>
														</div>
														<input type="hidden" name="id" value="${requestScope.ct.id }" id="id">
													</div>
													<div class="form-group">
														<label>展示图片:</label>
														<div>
															<c:forEach var="mis" items="${requestScope.pic}">
																<img src="${mis}" style='widht: 200px; height: 200px;'
																	alt="无">
															</c:forEach>
														</div>
													</div>
													<button type="submit" class="btn btn-default">修改</button>
													<button type="submit" class="btn btn-default">通过</button>
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
			window.location.href = "<%=basePath%>tsq/comment?id=" + i;
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
		function validate_required() {
			if($("#des").val()==""){
				alert("请补全信息")
				return false
			}else{
				return true
			}
		}
	</script></body>
</html>