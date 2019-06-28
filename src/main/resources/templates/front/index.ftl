<@override name="content">
    <#if pageInfo ?? && pageInfo.size gt 0>
        <#list pageInfo.list as article>
            <div class="index-article-item">
                <h2>${(article.title)!''}</h2>
                <div style="margin: 1rem 0;" class="index-article-item-opacity-8 index-article-item-font-1-5"><i
                            class="fa fa-calendar"></i>&nbsp;&nbsp;<span
                            class="index-article-item-font-1-5">${article.createTime?date}</span><span
                            style="margin-left: 2rem;float: right"
                            class="index-article-item-font-1-5">阅读：${(article.visitCount)!0}</span></div>
                <div style="margin: 1rem 0;" class="index-article-item-opacity-8 index-article-item-font-1-5"><i
                            class="fa fa-tag"></i>&nbsp;
                    <#if article.tags?? && article.tags?size gt 0>
                        <#list article.tags as tag>
                            <#if (tag_index+1)==article.tags?size>
                                <a href="front/archives/${tag.id}" class="index-article-item-font-1-5">${tag.name}</a>
                            <#else>
                                <a href="front/archives/${tag.id}" class="index-article-item-font-1-5">${tag.name}</a> |
                            </#if>
                        </#list>
                    <#else>
                        <a href="front/archives/" class="index-article-item-font-1-5">剪贴板</a>
                    </#if>
                </div>
                <div class="show_content" content="${article.content}"></div>
                <div style="margin: 1rem 0 "><a href="front/article/${article.id}">阅读全文>></a></div>
            </div>
        </#list>
    <#else>
        <h2 style="text-align: center">暂无内容</h2>
    </#if>
    <#if pageInfo?? && (pageInfo.list)?? && pageInfo.list?size gt 0>
        <div style="text-align: center">
            <a href="<#if pageInfo.isFirstPage>javascript:void(0)<#else>front/page/${pageInfo.prePage}</#if>">
                <i class="fa fa-chevron-left"></i></a>
            <span>&nbsp;${pageInfo.pageNum} / ${pageInfo.pages}&nbsp;</span>
            <a href="<#if pageInfo.isLastPage>javascript:void(0)<#else>front/page/${pageInfo.nextPage}</#if>">
                <i class="fa fa-chevron-right"></i></a>
        </div>
    </#if  >
</@override>
<@override name="js">
    <script type="text/javascript">
        $().ready(function () {
            $(".show_content").each(function (index, item) {
                var content = $(this).attr("content");
                var converter = new showdown.Converter(),
                    html = converter.makeHtml(content);
                $(this).html(html);
            });
        });
    </script>
</@override>
<@extends name="./template.ftl" />