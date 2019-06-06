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
    <title>11111</title>
    <link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="css/blog.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div id="container-common">
        <div id="header">
            <div id="navbar">
                <ol class="breadcrumb">
                    <li>赵志强的网络日志</li>
                    <li><a href="/article">首页</a></li>
                    <li><a href="/archivesIndex">档案</a></li>
                </ol>
            </div>
            <hr/>
        </div>
        <div>
            <div style="float: left;width: 63%">
                <div class="archives-content-article">
                        <a href="article/${article.id}"  ><h2><strong>${article.title!''}</strong></h2></a>
                    <hr/>
                    <div style="text-align: right">
                        <span style="margin-right: 0.5rem">分类:&nbsp;&nbsp;&nbsp;<a href="archives/${(tag.id)!''}">${(tag.name)!'剪贴板'}</a></span>
                    </div>
                    <div class="archives-content-article-content">

                    </div>
                    <div style="margin: 2rem 0">
                        <a href="article?articleId=${article.id}">继续阅读全文 »</a>
                    </div>
                    <hr/>
                    <div style="font-size: 20%">
                        ${article.createTime?datetime} | <a href="article/${article.id}#comment-${(currentArticleFirstComment.commentId)!''}" style="font-size: 20%">留言（${commentCount}）</a>
                    </div>
                    <div id="recentArticleList">
                        <h2><strong>最近的${recentArticles?size}篇文章（共${artcileCount!0}篇）</strong></h2>
                        <hr/>
                        <ul>
                            <#list recentArticles  as item>
                                <li>${item.createTime?date}：<a href="article/${item.id}">${item.title!''}</a>（${item.commentCount}条评论）</li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="archives-category" style="float: right;width: 26%">
                <div id="archives-category-inner"
                     style="border-radius: 2rem;border: solid 1px ;border-color: #0f0f0f33 ">
                    <div style="margin: 2rem">
                        <h3><strong><i>分类</i></strong></h3>
                        <hr/>
                        <div style="min-height:25rem;">
                            <ul>
                                <#list tags as item>
                                <li><a href="archives/${item.id}">${item.name}</a>（${item.articleCount}）</li>
                                </#list>
                            </ul>
                    </div>
                </div>
            </div>
        </div>
        <div style="clear: both"></div>
        <#include "../footer.ftl">
    </div>
</div>
<script src="js/jquery.js"></script>
<script src="js/showdown.js"></script>
<script src="plugins/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var aa = "${article.content?j_string}";
            var converter = new showdown.Converter(),
                html = converter.makeHtml(aa);
            $(".archives-content-article-content").html(html);
        });
    </script>
</body>
</html>
