<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>11111</title>
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/blog.css" rel="stylesheet">
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
                <div id="archives-content">
                    <div id="archives-content-title">
                            <div>(篇)</div>
                    </div>
                    <div id="archives-content-detail">

                    </div>
                </div>
                <div id="archives-category">
                    <div id="archives-category-inner" style="border-radius: 2rem;border: solid 1px ;border-color: #0f0f0f33 ">
                        <div style="margin: 2rem">
                        <h3><strong><i>分类</i></strong></h3>
                        <hr/>
                        <div style="min-height:25rem;">
                            <ul>
                                <#if tags??>
                                    <#list tags as tag>
                                    <li><a href="/archives?tagId=${tag.id!''}">${tag.name!''}</a></li>
                                    </#list>
                                </#if>
                            </ul>
                        </div>
                        </div>
                    </div>
                </div>
                    <div style="clear: both"></div>
                </div>
            <#include "../footer.html">
            </div>
        </div>
        <script src="../js/jquery-3.3.1.js"></script>
        <script src="../js/showdown.js"></script>
        <script src="../js/bootstrap.js"></script>
    </body>
</html>
