<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>无微不至管理后台系统登录</title>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<!-- Bootstrap Core CSS -->
<link href="<%=basePath%>vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="<%=basePath%>vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">
<!-- Custom Fonts -->
<link href="<%=basePath%>vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="<%=basePath%>dist/css/sb-admin-2.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">请登录</h3>
					</div>
					<div class="panel-body">
						<form role="form">
							<fieldset>
								<div class="form-group">
									<c:choose>
										<c:when test="${empty sessionScope.manager}">
											<input class="form-control" placeholder="请输入账户名" id="name"
												value="" autofocus>
										</c:when>
										<c:otherwise>
											<input autofocus class="form-control" placeholder="请输入账户名"
												id="name" value="${sessionScope.manager }">
										</c:otherwise>
									</c:choose>
								</div>
								<div class="form-group">
									<c:choose>
										<c:when test="${empty sessionScope.mpassword}">
											<input class="form-control" placeholder="请输入密码"
												name="password" type="password" id="password" value="">
										</c:when>
										<c:otherwise>
											<input class="form-control" placeholder="请输入密码"
												name="password" type="password" id="password"
												value="${sessionScope.mpassword }">
										</c:otherwise>
									</c:choose>
								</div>
								<!-- Change this to a button or input when using this as a form -->
								<div href="index.html" class="btn btn-lg btn-success btn-block" id="login">登录</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$("#login").bind("click", function() {
				if ($("#name").val().trim() == "") {
					alert("请输入账户名!");
				}else if($("#password").val().trim() == ""){
					alert("请输入密码!");
				} else {
					//alert($("#name").val().trim())
					//alert($("#password").val().trim())
					//alert($("#remember").prop("checked"))
					$.ajax({
						type : "POST",
						url : "<%=basePath%>mana/login",
								data : {
									"name" : $("#name").val().trim(),
									"password" : $("#password").val().trim(),
								
								},
								dataType : 'json',//返回值是json
								async : false,//同步请求
								success : function(data) {
									if (data.msg == 0) {
										tomission();
									} else {
										$("#name").val("");
										$("#password").val("");
										alert(msg);
									}
								},
								error : function(data) {
									alert("login error!");
									alert(JSON.stringify(data));
								}
							});
						}
					});
		})
		function tomission(){
			window.location.href = "<%=basePath%>mana/orderlist";
		}
	</script>
</body>
</html>