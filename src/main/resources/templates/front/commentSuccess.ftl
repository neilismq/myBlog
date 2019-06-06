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
                        <a href="article">首页</a>
                    </li>
                    <li>
                        <a href="archivesIndex">档案</a>
                    </li>
                </ol>
            </div>
            <hr/>
        </div>

        <div>
            评论成功！点击返回<a href="article/${article.id}#comment-${comment.commentId}">#</a>或者<a href="${base}/article">首页</a>
        </div>


    </div>
</div>
<script src="js/jquery.js"></script>
<script src="plugins/bootstrap/js/bootstrap.js"></script>
</body>

</html>
