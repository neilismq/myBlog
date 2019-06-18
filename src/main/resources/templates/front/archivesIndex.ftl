<@override name="content">
    <div style="float: left;width: 63%">

        <div class="archives-content-article">
            <#if article??>
                <a href="front/article/${article.id}"><h2><strong>${article.title!''}</strong></h2></a>
                <hr/>
                <div style="text-align: right">
                            <span style="margin-right: 0.5rem">分类:&nbsp;&nbsp;&nbsp;<a
                                        href="front/archives/${(tag.id)!''}">${(tag.name)!'剪贴板'}</a></span>
                </div>
                <div class="archives-content-article-content">

                </div>
                <div style="margin: 2rem 0">
                    <a href="front/article/${article.id}">继续阅读全文 »</a>
                </div>
                <hr/>
                <div style="font-size: 20%">
                    ${article.createTime?datetime} | <a
                            href="front/article/${article.id}#comment<#if currentArticleFirstComment?? && currentArticleFirstComment.commentId??>-${currentArticleFirstComment.commentId}</#if> "
                            style="font-size: 20%">留言（${commentCount}）</a>
                </div>
                <div id="recentArticleList">
                    <h2><strong>最近的${recentArticles?size}篇文章（共${artcileCount!0}篇）</strong></h2>
                    <hr/>
                    <ul>
                        <#list recentArticles  as item>
                            <li>${item.createTime?date}：<a
                                        href="front/article/${item.id}">${item.title!''}</a>（${item.commentCount}条评论）
                            </li>
                        </#list>
                    </ul>
                </div>
            <#else>
                <h2>暂无内容</h2>
            </#if>
        </div>

    </div>
    <div id="archives-category" style="float: right;width: 26%">
        <div id="archives-category-inner"
             style="border-radius: 2rem;border: solid 1px ;border-color: #0f0f0f33 ">
            <div style="margin: 2rem">
                <h3><strong><i>分类</i></strong></h3>
                <hr/>
                <div style="min-height:20rem;">
                    <ul>
                        <#if tags??>
                            <#list tags as item>
                                <li><a href="front/archives/${item.id}">${item.name}</a>（${item.articleCount}）
                                </li>
                            </#list>
                        <#else>
                            <h2>暂无分类</h2>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
        <div class="my-info" style=" margin:2rem 0;min-height: 200px;border-radius: 2rem;border: solid 1px ;border-color: #0f0f0f33 ">
            <ul style="list-style: none;line-height: 4rem;margin: 4rem 0 ">
                <li><a><i class="fa fa-link"></i>&nbsp;友情链接</a></li>
                <li><a><i class="fa fa-copyright"></i>&nbsp;关于本站</a></li>
                <li><a><i class="fa fa-user-circle-o"></i>&nbsp;关于我</a></li>
            </ul>
        </div>
    </div>
    <div style="clear:both"></div>
</@override>
<@override name="js">
    <script type="text/javascript">
        $(document).ready(function () {
            var content = "${article.content?j_string}";
            var contentArray = content.split("\r\n");
            var showText = "";
            if (contentArray.length > 2) {
                showText = showText + contentArray[0] + "\r\n" + contentArray[1];
            } else {
                showText = content;
            }
            var converter = new showdown.Converter(),
                html = converter.makeHtml(showText);
            $(".archives-content-article-content").html(html);
        });
    </script>
</@override>
<@extends name="./template.ftl" />