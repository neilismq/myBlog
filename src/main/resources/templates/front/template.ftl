<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="${base}">
    <link rel="shortcut icon" href="img/favicon.ico"/>
    <link rel="bookmark" href="img/favicon.ico"/>
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>赵志强的网络日志</title>
    <link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <link href="css/blog.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div id="container-inner">
        <div id="header">
            <div id="navbar">
                <ol class="breadcrumb">
                    <li>赵志强的网络日志</li>
                    <li>
                        <a href="">首页</a>
                    </li>
                    <li>
                        <a href="front/archivesIndex">档案</a>
                    </li>
                </ol>
            </div>
            <hr/>
        </div>
        <div class="content-wrapper">
            <@block name="content"></@block>
        </div>
    </div>
    <#include "../footer.ftl">
</div>
<!-- jQuery 3 -->
<script src="js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="plugins/admin-lte/js/adminlte.js"></script>
<#--<script src="plugins/admin-lte/js/adminlte.min.js"></script>-->
<script src="js/showdown.js"></script>
<script src="plugins/select2/js/select2.full.js"></script>
<@block name="js"></@block>
</body>
</html>
