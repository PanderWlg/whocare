<%--
  Created by IntelliJ IDEA.
  User: changqingshun
  Date: 2019-03-30
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Title</title>
	<link rel="stylesheet" href="webjars/layui/2.4.5/css/layui.css">
</head>
<body>
<form class="layui-form" method="post" action="login.jsp">
	<div class="layui-form-item">
		<label class="layui-form-label">用户名</label>
		<div class="layui-input-block">
			<input name="username" placeholder="请输入用户名"/>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">密码</label>
		<div class="layui-input-block">
			<input name="password" type="password" placeholder="请输入密码"/>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="form-login">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</div>

</form>
<script>
    layui.use('form', function () {
        var form = layui.form;
        form.on('submit(form-login)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
<script src="webjars/jquery/1.12.4/jquery.min.js"></script>
<script src="webjars/layui/2.4.5/layui.all.js"></script>
</body>

</html>
