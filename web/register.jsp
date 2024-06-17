<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>注册</title>
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link href="resources/css/register.css" type="text/css" rel="stylesheet"/>
    <script>
        function refreshCaptcha() {
            var image = document.getElementById("captchaImg");
            image.src = "code?a=" + new Date().getTime(); // 加入时间戳确保每次刷新都是新的验证码
        }

        function check(form) {
            var password = form.password.value;
            if (password.length < 6) {
                alert("密码长度不能少于6位！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h1 style="margin: 50px 80px; color: darkgray; font-family: cursive;">欢迎来到教务系统</h1>
<div class="main">
    <h5 class="title">
        <a href="login.jsp" id="login">登录</a>
        <b>&nbsp;·&nbsp;</b>
        <a href="register.jsp" id="register">教师注册</a>
    </h5>
    <form action="check_register" method="post" onsubmit="return check(this)">
        <div class="form-group">
            <input type="email" name="email" class="form-control email" placeholder="安全邮箱" required>
            <input type="password" name="password" class="form-control password1" placeholder="密码" required>
            <input type="text" name="code" placeholder="验证码" class="code" required>
            <img id="captchaImg" src="code" style="float: right; width: 90px; height: 50px; margin-top: 10px" onclick="refreshCaptcha()"/>
            <input type="submit" value="注册" class="btn btn-primary btn-lg btn-block we"/>
        </div>
    </form>
    <div style="font-size: smaller; color: gray; font-weight: 900">注册后系统将为您自动分配用户名, 作为登录凭证</div>
</div>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
