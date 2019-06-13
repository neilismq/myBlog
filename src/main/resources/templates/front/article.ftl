<@override name="content">
    <div class="content-header"
         <#if (!nextArticle?? && preArticle??) || (!preArticle?? && nextArticle??)>style="height:4.3rem;"<#else>
        style="height:6.8rem"
            </#if>>
        <div class="category">
            <span>分类:</span>
            <a href="front/archives/${(tag.id)!''}">${(tag.name)!'剪贴板'}</a>
        </div>
        <div class="pre-next-article">
            <ul>
                <#if preArticle??>
                    <li>
                        <span>上一篇：</span>
                        <a href="front/article/${preArticle.id!''}">${preArticle.title!''}</a>
                    </li>
                </#if>
                <#if nextArticle??>
                    <li>
                        <span>下一篇：</span>
                        <a href="front/article/${nextArticle.id}">${nextArticle.title!''}</a>
                    </li>
                </#if>
            </ul>
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
                    <li>版权声明：自由转载-非商用-非衍生-保持署名（<a
                                href="http://creativecommons.org/licenses/by-nc-nd/3.0/deed.zh">创意共享3.0许可证</a>）
                    </li>
                    <li>发表日期： <span title="2018-12-12T08:31:46+08:00">${article.createTime?date}</span></li>
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
                                <div class="inner">
                                    <div class="comment-header">
                                        <div class="asset-meta">
                                            <p>
                                                <#if comment.personalSite?? && comment.personalSite!=''>
                                                    <a class="comment-username" href="${comment.personalSite!'#'}"
                                                       target="_blank">
                                                        ${comment.name!'未知用户'}
                                                    </a>
                                                <#else>
                                                    <span class="comment-username">${comment.name!'未知用户'}</span>
                                                </#if>
                                                <span>说：</span>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="comment-content" id="comment-quote-${comment.commentId}">
                                        ${comment.content!''}
                                    </div>
                                    <div class="comment-item-footer">
                                        <span>${comment.commentCreateTime?datetime}</span>|
                                        <a href="front/article/${article.id}#comment-${comment.commentId}">#</a>|
                                        <a href="#comment-text" title="引用${comment.name!'未知用户'}的这条留言"
                                           onclick="return CommentQuote('comment-quote-${comment.commentId}','${comment.name}');">引用</a>
                                    </div>
                                    <hr/>
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
            <div class="comments-open" id="comments-open">
                <h2 class="comments-open-header">我要发表看法</h2>
                <hr/>
                <div class="comments-open-content">
                    <form method="post" action="front/comment"
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
                                        <textarea id="comment-text" name="content"
                                                  style="height:20rem;width:90%"></textarea>
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

</@override>
<@override name="js">
    <script type="text/javascript">
        var bar = 0;
        var line = "||";
        var amount = "||";
        $(document).ready(function () {
            var aa = "${article.content?j_string}";
            var converter = new showdown.Converter(),
                html = converter.makeHtml(aa);
            $("#article-content").html(html);
            var comment_user_name = getCookie("comment_user_name");
            var comment_user_email = getCookie("comment_user_email");
            var comment_user_home = getCookie("comment_user_home");
            if (comment_user_name != "") {
                $("#comment-author").val(comment_user_name);
            }
            if (comment_user_email != "") {
                $("#comment-email").val(comment_user_email);
            }
            if (comment_user_home != "") {
                $("#comment-url").val(comment_user_home);
            }
            if (comment_user_name != "" || comment_user_email != "" || comment_user_home != "") {
                $("#comment-bake-cookie").attr("checked", true);
            } else {
                $("#comment-bake-cookie").attr("checked", false);

            }
            // $("#comment-2")[0].scrollIntoView(true);
        });

        function pleaseWait() {
            if (document.getElementById('comment-text').value.length > 1200) {
                alert('留言请勿超过1200字！');
                return false;
            }

            if (!document.forms["comments_form"].name.value) {
                alert('请填写“您的大名”！');
                return false;
            }

            if (document.forms["comments_form"].email.value.length < 7 || document.forms["comments_form"].email.value.indexOf("@") == -1) {
                alert('请正确填写电子邮件地址！');
                return false;
            }

            if (document.getElementById('comment-text').value.length < 2) {
                alert('留言不得为空！');
                return false;
            }

            document.getElementById("comment-submit").style.visibility = "hidden";
            document.getElementById("wait").style.display = "block";
            stick_count();
            if (document.forms["comments_form"].armor)
                document.forms["comments_form"].armor.value = '493b793496197fb44387464d8c8c8cb4091a0b94';
            return true;
        }

        function setCookie(name, value, expires, path, domain, secure) {
            var curCookie = name + "=" + escape(value) + (expires ? "; expires=" + expires : "") +
                (path ? "; path=" + path : "") + (domain ? "; domain=" + domain : "") + (secure ? "secure" : "");
            document.cookie = curCookie;
        }

        function rememberMe(f) {
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

        function forgetMe(f) {
            deleteCookie('comment_user_name', '/', '');
            deleteCookie('comment_user_email', '/', '');
            deleteCookie('comment_user_home', '/', '');
            f.email.value = '';
            f.name.value = '';
            f.personalSite.value = '';
        }

        function deleteCookie(name, path, domain) {
            if (getCookie(name))
                document.cookie = name + "=" + ((path) ? "; path=" + path : "") +
                    ((domain) ? "; domain=" + domain : "") + "; expires=Thu, 01-Jan-70 00:00:01 GMT";
        }

        function getCookie(name) {
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

        function stick_count() {
            bar = bar + 2;
            amount = amount + line;
            document.forms["comments_form"].chart.value = amount;
            document.forms["comments_form"].percent.value = bar + "%";
            if (bar < 99) {
                setTimeout("stick_count()", 200);
            } else {
            }
        }

        function CommentQuote(v, f) {

            window.location.href = window.location.href + "#comment-text";

            string = document.forms["comments_form"].content.value;
            string2 = ignoreSpaces(RemoveHTML(RemoveBlockquote(document.getElementById(v).innerHTML)));
            document.forms["comments_form"].content.value = "<blockquote>\n<pre>引用" + f + "的发言：</pre>\n" + string2 + "\n</blockquote>\n\n" + string;

            return true;
        }

        function ignoreSpaces(string) {
            var temp = "";
            string = '' + string;
            splitstring = string.split("  "); //双引号之间是个空格；
            for (i = 0; i < splitstring.length; i++)
                temp += splitstring[i];
            return temp;
        }

        function RemoveHTML(strText) {
            var regEx = /<[^>]*>/g;
            return strText.replace(regEx, "");
        }

        function RemoveBlockquote(strText) {
            var regEx = /<blockquote>(.|\n|\r)*<\/blockquote>/ig;
            regEx.multiline = true;
            return strText.replace(regEx, "");
        }
    </script>
</@override>
<@extends name="./index.ftl" />