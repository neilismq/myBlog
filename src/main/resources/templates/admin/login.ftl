<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>赵志强的网络日志</title>
    <base href="${base}">
    <link rel="shortcut icon" href="img/favicon.ico"/>
    <link rel="bookmark" href="img/favicon.ico"/>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="plugins/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="plugins/admin-lte/css/AdminLTE.min.css">
    <!-- iCheck -->

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    <style>
        .back-img{
            background:url(img/city.jpg) no-repeat center center;
            background-size: cover;
            background-attachment: local;
        }
    </style>
</head>
<body class="hold-transition back-img">
<div class="login-box">
    <div class="login-logo">
        <span><b>后台管理系统</b></span>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <!--    <p class="login-box-msg">填写登录信息</p>-->
        <form action="admin/login" method="post" id="loginForm">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="用户名" name="username">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="密码" name="password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat" id="loginBtn">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <div class="social-auth-links text-center">
            <!--      <p>- OR -</p>-->
            <!--      <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using-->
            <!--        Facebook</a>-->
            <!--      <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using-->
            <!--        Google+</a>-->
        </div>
        <!-- /.social-auth-links -->

        <!--    <a href="#">I forgot my password</a><br>-->
        <!--    <a href="register.html" class="text-center">Register a new membership</a>-->

    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="js/jquery.js"></script>
<script src="js/md5.min.js"></script>
<script src="plugins/jquery-validation/jquery.validate.js"></script>
<script src="plugins/bootstrap/js/bootstrap.js"></script>
<script>

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
                var $username = $("input[name='username']").val();
                var $password = $("input[name='password']").val();
                $password = md5($password + "${salt}")
                $.post(form.action, {username:$username,password:$password}, function (data, status, xhr) {
                    if (data.code == "200") {
                        window.location.href = "${base}" + data.body.adminIndexUrl;
                    } else {
                        alert(data.message);
                    }
                }, "json");
            }
        });
    });

</script>
</body>
</html>
