<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>111</title>
    <link href="${base}/plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${base}/css/blog.css" rel="stylesheet">
</head>
<body style="background-color: rgb(43,105,166)">
<div style="width: 50%;margin: 0 auto;">
    <h1 style="color: azure;text-space: 2rem;text-align: center;margin: 10rem auto 2rem auto">博客后台管理系统</h1>
    <div style="background-color: rgb(230,230,230);height: 22rem;border-radius: 0.5rem">
        <div style="float: left;width: 40%;height:22rem;">
            <img src="${base}/img/aaa.jpg" width="100%" height="100%"/>
        </div>
        <div style="float: right;width: 50%;height:22rem;">
            <form action="${base}/admin/login" method="post" id="loginForm">
                <div style="margin-top: 3rem">
                    <div>用户名：</div>
                    <div style="margin-top: 0.4rem"><input id="username" name="username"/></div>
                </div>
                <div style="margin-top: 1rem">
                    <div>密码：</div>
                    <div style="margin-top: 0.4rem"><input id="password" name="password"/></div>
                </div>
                <div style="margin-top: 1rem">
                    <input type="submit" value="登录" id="loginBtn">
                    <!--<input type="button" value="注册">-->
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${base}/js/jquery.js"></script>
<script src="${base}/plugins/bootstrap/js/bootstrap.js"></script>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        $("#loginForm").submit(function () {
            var username = $("#username").val();
            var password = $("#password").val();
            if (!username || !password) {
                alert("账号密码不能为空！");
                return false;
            }
            return true;
        });
    });
</script>