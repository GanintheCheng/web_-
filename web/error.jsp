<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <style>
        /* 屏幕居中样式 */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .error-container {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container error-container">
    <h1>我们遇到了一点问题</h1>
    <p>我们对此感到抱歉. 请立刻联系管理员.</p>

    <!-- 联系管理员按钮 -->
    <form action="mailto:1837761918@qq.com" method="post" enctype="text/plain" style="margin-bottom: 20px">
        <button type="submit" class="btn btn-primary">联系我们</button>
    </form>

    <!-- 跳转到登录页面按钮 -->
    <form action="${pageContext.request.contextPath}/index.jsp">
        <button type="submit" class="btn btn-secondary">返回首页</button>
    </form>
</div>

<!-- 引入 Bootstrap JavaScript 文件 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
