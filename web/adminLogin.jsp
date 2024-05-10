<%--
   Created by IntelliJ IDEA.
   User: ~Ganinthe.Cheng
   Date: 2024/5/9
   Time: 上午9:38
   To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>管理员登录</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link href="resources/css/login.css" type="text/css" rel="stylesheet"/>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .main {
            text-align: center;
            display: flex;
            flex-direction: column;
            justify-content: space-between
        }
    </style>
</head>
<body>
<script>
    function check(form) {
        if (form.account.value === "") {
            alert("请输入账号！");
            return false;
        }
        if (form.password.value === "") {
            alert("请输入密码！");
            return false;
        }
        return true;
    }
</script>
<div class="main">
    <h1 style="color: darkgray; font-family: cursive;">欢迎管理员登录</h1>
    <form action="admin_check_login" method="post" onsubmit="return check(this)">
        <div class="form-group">
            <input type="text" name="account" class="form-control user" placeholder="请输入管理员账号">
            <input type="password" name="password" class="form-control password" placeholder="请输入密码">
            <input type="submit" value="登录" class="btn btn-primary btn-lg btn-block"/>
        </div>
    </form>
    <a href="index.jsp" style="margin-top: 20px; display: block;">返回首页</a>
</div>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
