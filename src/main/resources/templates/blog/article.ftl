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
                                <a href="/article">首页</a>
                            </li>
                            <li>
                                <a href="/archives">档案</a>
                            </li>
                        </ol>
                    </div>
                    <hr/>
                    <div class="content-header" <#if (!nextArticle?? && preArticle??) || (!preArticle?? && nextArticle??)>style="height:4.3rem;"<#else> style="height:6.8rem"</#if>>
                        <div class="category">
                            <span>分类:</span>
                            <a href="/archives?tagId=${tag.id}">${tag.name}</a>
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
                        <div id="article-footer" style="background-color: #8bc1ed; padding: 0.7rem;border-radius: 2rem;">
                            <h3 style="margin-left: 1.2rem"><strong>文档信息</strong></h3>
                            <ul style="border-top: solid 1px #54414733">
                                <li>版权声明：自由转载-非商用-非衍生-保持署名（<a href="http://creativecommons.org/licenses/by-nc-nd/3.0/deed.zh">创意共享3.0许可证</a>）</li>
                                <li>发表日期： <span title="2018-12-12T08:31:46+08:00">${article.createTime?date}</span></li>
                                <li>更多内容： <a href="/archives"> 档案</a>  »
                                    <a href="/archives?tagId=${tag.id}">${tag.name!''}</a>
                                </li>
                                <li>社交媒体：<a href="#" target="_blank"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAk1JREFUeNqsVc9rE0EU/mZ3dhPNJl2bVIk/Km09aFBE8GLvnrwJXvwLxIsgCP4Fnit6E8Gb0IMHwUsL9SSNgiAoooJIpE1Joo1h82uT7IxvxmRN3Q32kAdvdrI773vfe+/bDTu9WioDcDAda3Ja8piepQ1avCkCeny0Y+R9KenKwOmHHLvPaKn7AtVOoO8dOWDCMpg+41gMQv5FNEJACgoE8MsPNLDBhgfoutMOsJDmWLmU037C4TApciljwQ/kHoohQxV06+wMXNvE7WINuaSpvdYVKLgWnl3O4zAxU3Z1IYVGT+B5qYWV9z2kbQaT/cOwTsxOUUYF+mB5Dkmi8HG3h2q9hxsFNwRTdvQgxxnXRrMvIdkEhllis/qtiWuLDm4WZnBlPoWnXz28LHdw0uGR7m9Wu3j0qYGEubfn4UlBfXO4gTZltRNMg9w9f0i7kNFxllsBlS0x75gYfxyWTBUjT2W5CSMSPBrQuG21+hiMDS8CeCzF8eSLp13uQ3Ab1IqMFZN8tFHPmgOBF99bYP8Be1Xp6t7OJszJgKpPSiZvaj7uf2hguz2IBQuozDvFH6RDBjuK9wdQDvt0nMpW+8efGyh5UcAeZb2+UcHbnz5Jx4wdlp4yJYU3kLiQtfBwOYeLc8nIwfXtNu69q2Oz4mMxbemYOOOjKarhvq51KUjg3GwiFPIuvcNF6pnSnWK0lOEabNLgQh1aJFAhlGB9rG110B2+oyRNPc0sZbRNFltmLKDKqj4Qrm1ojzOxDz2pyPQ0P7CK4c40/wJ+CzAAGYrXsvfFXR4AAAAASUVORK5CYII=" style="border:0;"> twitter</a>，<a href="http://weibo.com/ruanyf" target="_blank"><img src="data:image/png;base64,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" style="border:0;"> weibo</a></li>
                            </ul>
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
                                <div id="comment-${comment.commentId}" class="comment-item">
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
                                    <a href="http://localhost/article?id=${article.id}#comment-${comment.commentId}">#</a>|
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
                <hr/>
                <div class="comments-open-content">
                    <form method="post" action="/comment"
                          name="comments_form" id="comments-form" onsubmit="return pleaseWait();">
                        <div style="display: none">
                            <input name="articleId" value="${article.id}"/>
                        </div>
                        <div id="comments-open-data">
                            <div id="comments-open-text">
                                <p>
                                    <label for="comment-text">您的留言
                                        （HTML标签部分可用）
                                    </label>
                                </p>
                                <p>
                                    <textarea id="comment-text" name="content" rows="10" cols="50"></textarea>
                                </p>
                            </div>
                            <div id="comment-form-name">
                                <p>
                                    <label for="comment-author">您的大名：</label>
                                </p>
                                <p>
                                    <input id="comment-author" name="name" size="30" value="">
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
                                    <input id="comment-url" name="personalSite" size="30" value="">
                                    <span class="hint">«-我信任你，不会填写广告链接</span>
                                </p>
                            </div>
                            <div id="comment-form-remember-me">
                                <p>
                                    <label for="comment-bake-cookie">记住个人信息？</label>
                                    <input type="checkbox" id="comment-bake-cookie" name="bakecookie"
                                           onclick="!this.checked?forgetMe(document.comments_form):rememberMe(document.comments_form)">
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
 <script type="text/javascript">
        var bar = 0;
        var line = "||";
        var amount ="||";
        $(document).ready(function () {
        var aa="${article.content?j_string}";
        var converter = new showdown.Converter(),
        html = converter.makeHtml(aa);
        $("#article-content").html(html);
        var comment_user_name=getCookie("comment_user_name");
        var comment_user_email=getCookie("comment_user_email");
        var comment_user_home=getCookie("comment_user_home");
        if( comment_user_name!=""){
        $("#comment-author").val(comment_user_name);
        }
        if( comment_user_email!=""){
        $("#comment-email").val(comment_user_email);
        }
        if( comment_user_home!=""){
        $("#comment-url").val(comment_user_home);
        }
        if(comment_user_name!="" ||  comment_user_email!="" || comment_user_home!=""){
        $("#comment-bake-cookie").attr("checked", true);
        }else {
        $("#comment-bake-cookie").attr("checked", false);

        }
        // $("#comment-2")[0].scrollIntoView(true);
        });
        function pleaseWait() {
        if(document.getElementById('comment-text').value.length>1200){
        alert('留言请勿超过1200字！');
        return false;
        }

        if(!document.forms["comments_form"].name.value){
        alert('请填写“您的大名”！');
        return false;
        }

        if(document.forms["comments_form"].email.value.length<7 || document.forms["comments_form"].email.value.indexOf("@")==-1){
        alert('请正确填写电子邮件地址！');
        return false;
        }

        if(document.getElementById('comment-text').value.length<2){
        alert('留言不得为空！');
        return false;
        }

        document.getElementById("comment-submit").style.visibility="hidden";
        document.getElementById("wait").style.display="block";
        stick_count();
        if (document.forms["comments_form"].armor)
        document.forms["comments_form"].armor.value = '493b793496197fb44387464d8c8c8cb4091a0b94';
        return true;
        }
        function setCookie (name, value, expires, path, domain, secure) {
        var curCookie = name + "=" + escape(value) + (expires ? "; expires=" + expires : "") +
        (path ? "; path=" + path : "") + (domain ? "; domain=" + domain : "") + (secure ? "secure" : "");
        document.cookie = curCookie;
        }
        function rememberMe (f) {
        var now = new Date();
        now.setTime(now.getTime() + 365 * 24 * 60 * 60 * 1000);
        now = now.toGMTString();
        if (f.name != undefined)
        setCookie('comment_user_name', f.name.value, now, '/', '', '');
        if (f.email != undefined)
        setCookie('comment_user_email', f.email.value, now, '/', '', '');
        if (f.personalSite != undefined)
        setCookie('comment_user_home', f.personalSite.value, now, '/', '', '');
        }
        function forgetMe (f) {
        deleteCookie('comment_user_name', '/', '');
        deleteCookie('comment_user_email', '/', '');
        deleteCookie('comment_user_home', '/', '');
        f.email.value = '';
        f.name.value = '';
        f.personalSite.value = '';
        }
        function deleteCookie (name, path, domain) {
        if (getCookie(name))
        document.cookie = name + "=" + ((path) ? "; path=" + path : "") +
        ((domain) ? "; domain=" + domain : "") + "; expires=Thu, 01-Jan-70 00:00:01 GMT";
        }
        function getCookie (name) {
        var prefix = name + '=';
        var c = document.cookie;
        var nullstring = '';
        var cookieStartIndex = c.indexOf(prefix);
        if (cookieStartIndex == -1)
        return nullstring;
        var cookieEndIndex = c.indexOf(";", cookieStartIndex + prefix.length);
        if (cookieEndIndex == -1)
        cookieEndIndex = c.length;
        return unescape(c.substring(cookieStartIndex + prefix.length, cookieEndIndex));
        }

        function stick_count(){
        bar= bar+2;
        amount =amount + line;
        document.forms["comments_form"].chart.value=amount;
        document.forms["comments_form"].percent.value=bar+"%";
        if (bar<99)
        {setTimeout("stick_count()",200);}
        else
        {}
        }
</script>
</html>
