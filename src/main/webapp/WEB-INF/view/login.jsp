<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>love</title>
    <link href="favicon.ico" rel="shortcut icon" />
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.4.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style=" background: url(http://global.bing.com/az/hprichbg/rb/RavenWolf_EN-US4433795745_1920x1080.jpg) no-repeat center center fixed; background-size: 100%;">


<div class="modal-dialog modal-sm" style="margin-top: 10%;">
    <div class="modal-content">
        <div class="modal-header">

            <h4 class="modal-title text-center" id="myModalLabel">登录</h4>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/dologin">
        <div class="modal-body" id = "model-body">
            <div class="form-group">
                <input type="text" class="form-control" name="usercode" placeholder="用户名" autocomplete="off">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="密码" autocomplete="off">
            </div>
            <div class="captcha">
                <input type="text" class="form-control" style="width:60%;display: inline-block" name="code" placeholder="验证码" autocomplete="off">
                <img src="${pageContext.request.contextPath}/captcha" width="100" height="35" onclick="this.src='${pageContext.request.contextPath}/captcha?'+Math.random()">
            </div>
        </div>
        <div class="modal-footer">
            <div class="form-group">
                <input type="submit" class="btn btn-primary form-control" value="登录">
            </div>
        </div>
        </form>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

</body>
</html>