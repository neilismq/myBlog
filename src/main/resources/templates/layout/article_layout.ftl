<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>赵志强的网络日志</title>
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/blog.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div id="container-inner">
                <div id="header">
                    <div id="navbar">
                        <ol class="breadcrumb">
                            <li>赵志强的网络日志</li>
                            <li>
                                <a href="/">首页</a>
                            </li>
                            <li>
                                <a href="/archives">档案</a>
                            </li>
                        </ol>
                    </div>
                    <hr/>
                    <div class="content-header">
                        <div class="category">
                            <span>分类:</span>
                            <a href="#">${tag.name}</a>
                        </div>
                        <div class="pre-next-article">
                            <ul>
                                <li>
                                    <span>上一篇：</span>
                                    <a href="#">${preArticle.title}</a>
                                </li>
                                <!--<li><span>下一篇：</span><a href="#">redis学习之旅</a></li>-->
                            </ul>
                        </div>
                    </div>
                </div>
                <div id="content">
                    <div id="content-title">
                        <h2>
                            <strong>${article.title!''}</strong>
                        </h2>
                    </div>
                    <hr/>
                    <div id="article">
                        <div id="article-header">
                            <div id="article-header-author"><span>作者：</span>${article.author}
                                <span></span>
                            </div>
                            <div id="article-header-date"><span>日期：</span>${article.createTime?date}
                                <span></span>
                            </div>
                        </div>
                        <div id="article-content">

                        </div>
                        <div id="article-footer">

                        </div>
                        <hr/>
                        <div id="comment">
                            <div id="comment-header">
                                <span>留言</span>
                                <span>（<#if comments??>#{comments?size}</#if>条）</span>
                            </div>
                            <hr/>
                            <div id="comment-list">
                                <#if comments??>
                                <#list comments as comment>
                                <div class="comment-item">
                                    <#if comment.personalSite?? && comment.personalSite!=''>
                                    <a class="comment-username" href="${comment.personalSite!'#'}" target="_blank">
                                        ${comment.name!'未知用户'}
                                    </a>
                                    <#else>
                                    <span class="comment-username">${comment.name!'未知用户'}</span>
                                </#if>
                                <span>说：</span>
                                <p class="indent">
                                    ${comment.content!''}
                                </p>
                                <div class="comment-item-footer">
                                    <span>${comment.commentCreateTime?datetime}</span>|
                                    <a href="#">#</a>|
                                    <a href="#">引用</a>
                                </div>
                                <hr/>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
            <div class="comments-open" id="comments-open">
                <h2 class="comments-open-header">我要发表看法</h2>
                <div class="comments-open-content">


                    <div id="comment-greeting"></div>

                    <form method="post" action="http://www.ruanyifeng.com/cgi-bin/mtos/mt-comments.cgi"
                          name="comments_form" id="comments-form" onsubmit="return pleaseWait();">
                        <input type="hidden" name="static" value="1">
                        <input type="hidden" name="entry_id" value="2046">
                        <input type="hidden" name="__lang" value="en">
                        <input type="hidden" name="parent_id" value="" id="comment-parent-id">
                        <input type="hidden" name="armor" value="1">
                        <input type="hidden" name="preview" value="">
                        <input type="hidden" name="sid" value="">
                        <div id="comments-open-data">
                            <div id="comments-open-text">
                                <p>
                                    <label for="comment-text">您的留言
                                        （HTML标签部分可用）
                                    </label>
                                </p>
                                <p>
                                    <textarea id="comment-text" name="text" rows="10" cols="50"></textarea>
                                </p>
                            </div>
                            <div id="comment-form-name">
                                <p>
                                    <label for="comment-author">您的大名：</label>
                                </p>
                                <p>
                                    <input id="comment-author" name="author" size="30" value="">
                                    <span class="hint">«-必填</span>
                                </p>
                            </div>
                            <div id="comment-form-email">
                                <p>
                                    <label for="comment-email">电子邮件：</label>
                                </p>
                                <p>
                                    <input id="comment-email" name="email" size="30" value="">
                                    <span class="hint">«-必填，不公开</span>
                                </p>
                            </div>
                            <div id="comment-form-url">
                                <p>
                                    <label for="comment-url">个人网址：</label>
                                </p>
                                <p>
                                    <input id="comment-url" name="url" size="30" value="">
                                    <span class="hint">«-我信任你，不会填写广告链接</span>
                                </p>
                            </div>
                            <div id="comment-form-remember-me">
                                <p>
                                    <label for="comment-bake-cookie">记住个人信息？</label>
                                    <input type="checkbox" id="comment-bake-cookie" name="bakecookie"
                                           onclick="!this.checked?forgetMe(document.comments_form):rememberMe(document.comments_form)"
                                           value="1" accesskey="r">
                                </p>
                            </div>
                        </div>
                        <div id="comment-form-reply" style="display:none">
                            <input type="checkbox" id="comment-reply" name="comment_reply" value=""
                                   onclick="mtSetCommentParentID()">
                            <label for="comment-reply" id="comment-reply-label"></label>
                        </div>
                        <div id="comments-open-captcha"></div>
                        <div id="comments-open-footer">
                            <div id="wait" style="display:none;">
                                <p>
                                    <strong>正在发表您的评论，请稍候</strong>
                                </p>
                                <p>
                                    <input type="text" name="percent" size="3"
                                           style="font-family:Arial; color:black;text-align:center; border-width:medium; border-style:none;">
                                    <input type="text" name="chart" size="46"
                                           style="font-family:Arial;font-weight:bolder; color:black; padding:0px; border-style:none;">
                                </p>
                            </div>

                            <p>
                                <input type="submit" accesskey="s" name="post" id="comment-submit" value="发表">
                                <span class="hint">«- 点击按钮</span>
                            </p>
                        </div>
                    </form>


                </div>
            </div>
        </div>
        </div>
        <@block name="content">

    </@block>
    <#include "../footer.html">
    </div>
    </div>
    <script src="../js/jquery-3.3.1.js"></script>
    <script src="../js/showdown.js"></script>
    <script src="../js/bootstrap.js"></script>
    </body>
    <@block name="js">
</@block>
</html>
