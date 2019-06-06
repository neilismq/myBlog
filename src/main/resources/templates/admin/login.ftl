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
<body>
        <!--style="background-color: rgb(43,105,166)"-->
<div style="width: 50%;margin: 0 auto;">
    <h1 style="color: azure;text-space: 2rem;text-align: center;margin: 10rem auto 2rem auto">321312321</h1>
    <div style="background-color: rgb(230,230,230);height: 22rem;border-radius: 0.5rem">
        <div style="float: left;width: 40%;height:22rem;">
            <img src="${base}/img/aaa.jpg" width="100%" height="100%"/>
        </div>
        <div style="float: right;width: 50%;height:22rem;">
            <form action="${base}/admin/login" method="post" id="loginForm">
                <div style="margin-top: 3rem">
                    <div>用户名：</div>
                    <div style="margin-top: 0.4rem"><input name="username"/></div>
                </div>
                <div style="margin-top: 1rem">
                    <div>密码：</div>
                    <div style="margin-top: 0.4rem"><input id="password" name="password" type="password"/></div>
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
<script src="${base}/plugins/jquery-validation/jquery.validate.js"></script>
<script src="${base}/plugins/bootstrap/js/bootstrap.js"></script>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        $("#loginForm").validate({
            rules: {
                username: {
                    required: true,
                    minlength: 5
                },
                password: {
                    required: true,
                    minlength: 5
                },
            },
            messages: {
                username: {
                    required: "请输入用户名",
                    minlength: "账号长度至少为5位"
                },
                password: {
                    required: "请输入密码",
                    minlength: "密码长度至少为5位"
                }
            },
            errorElement: "em",
            errorPlacement: function (error, element) {
                // Add the `help-block` class to the error element
                error.addClass("help-block");

                if (element.prop("type") === "checkbox") {
                    error.insertAfter(element.parent("label"));
                } else {
                    error.insertAfter(element);
                }
            },
            highlight: function (element, errorClass, validClass) {
                $(element).parents().addClass("has-error").removeClass("has-success");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parents().addClass("has-success").removeClass("has-error");
            },
            submitHandler: function (form) {
                $.post(form.action, $(form).serialize(), function (data, status, xhr) {
                    if (data.code == "200") {
                        window.location.href = "${base}"+data.body.adminIndexUrl;
                    } else {
                        alert(data.message);
                    }
                }, "json");
            }
        });
    });
</script>