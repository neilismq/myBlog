<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <!-- keywords 关键字优化 -->
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>赵志强的网络日志</title>
    <base href="${base}">
    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">

    <!-- No Baidu Siteapp-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="icon" type="image/png" href="i/favicon.png">

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="i/app-icon72x72@2x.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="apple-touch-icon-precomposed" href="i/app-icon72x72@2x.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="i/app-icon72x72@2x.png">
    <meta name="msapplication-TileColor" content="#0e90d2">

    <!-- Amaze UI CSS -->
    <!--<link rel="stylesheet" href="//cdn.amazeui.org/amazeui/2.1.0/css/amazeui.min.css">-->
    <link rel="stylesheet" href="amaze-ui/css/amazeui.css">
    <style>
        .my-head {
            margin-top: 40px;
            text-align: center;
        }

        @media screen and (min-width: 1000px) {
            .zq-font-normal {
                font-size: 1.6rem;
            }
        }

        @media only screen and (min-width: 641px) {
            .am-offcanvas {
                display: block;
                position: static;
                background: none;
            }

            .am-offcanvas-bar {
                position: static;
                width: auto;
                background: none;
                -webkit-transform: translate3d(0, 0, 0);
                -ms-transform: translate3d(0, 0, 0);
                transform: translate3d(0, 0, 0);
            }

            .am-offcanvas-bar:after {
                content: none;
            }

        }
    </style>
</head>
<body>
<header class="am-g my-head">
    <div class="am-u-sm-12 am-article am-u-lg-8 am-u-lg-push-2">
        <div id="navbar">
            <ol class="breadcrumb">
                <li>赵志强的网络日志</li>
                <li>
                    <a href="/article">首页</a>
                </li>
                <li>
                    <a href="/archivesIndex">档案</a>
                </li>
            </ol>
        </div>
        <hr/>
        <div class="content-header"
            <#if (!nextArticle?? && preArticle??) || (!preArticle?? && nextArticle??)>style="height:4.3rem;"<#else>
            style="height:6.8rem"
            </#if>>
            <div class="category">
                <span>分类:</span>
                <a href="/archives?tagId=${(tag.id)!''}">${(tag.name)!'剪贴板'}</a>
            </div>
            <div class="pre-next-article">
                <ul>
                <#if preArticle??>
                <li>
                    <span>上一篇：</span>
                    <a href="/article?articleId=${preArticle.id}">${preArticle.title!''}</a>
                </li>
                </#if>
            <#if nextArticle??>
            <li>
                <span>下一篇：</span>
                <a href="/article?articleId=${nextArticle.id}">${nextArticle.title!''}</a>
            </li>
            </#if>
                </ul>
            </div>
        </div>

    </div>
</header>

</body>
</html>