<@override name="content">
    <div id="archives-content">
        <div id="archives-content-title">
            <div>${(currentTag.name)!'剪贴板'}(${(articleCount?c)!0}篇)</div>
        </div>
        <div id="archives-content-detail">
            <#if articleList??>
                <#list articleList as articleMap>
                    <div class="archives-content-detail">
                        <h3>${articleMap.year}</h3>
                        <hr/>
                        <ul>
                            <#if (articleMap.articleList)??>
                                <#list articleMap.articleList as article >
                                    <li><span><a href="front/article/${(article.id)!''}">${article.title!''}</a><span>（@${(article.createTime)?date}）</span>
                                    </li>
                                </#list>
                            </#if>
                        </ul>
                    </div>
                </#list>
            <#else>
                <h3 style="margin:5rem auto">暂无内容</h3>
            </#if>
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
                                <li><a href="front/archives/${tag.id}">${tag.name}</a>（${tag.articleCount}）</li>
                            </#list>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</@override>
<@extends name="./template.ftl" />


