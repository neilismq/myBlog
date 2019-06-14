<@override name="content">
    <div>
        <section class="content-header">
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li>article</li>
                <li class="active">manage</li>
            </ol>
        </section>
    </div>
    <div>
        <section id="add-article" style="margin: 2rem 2rem 0 2rem;" class="content" hidden>

            <div style="padding-left: 1rem">
                <!-- Button trigger modal -->
                <div class="row">
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addTagModal"
                                id="addTagBtn">
                            编辑
                        </button>
                        <button type="button" class="btn btn-danger" id="deleteArticleBtn">
                            删除
                        </button>
                    </div>
                    <div class="col-sm-4"></div>
                    <div class="col-sm-2">
                        <form action="admin/article/manage/search" method="post">
                            <input class="form-control input-group-sm" placeholder="请输入文章名称" name="selectKey"
                                   value="${selectKey!''}">
                        </form>
                    </div>
                </div>
                <!-- Modal -->
                <div class="modal fade" id="deleteArticleModal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document" aria-hidden="true">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel"><b>删除文章</b></h4>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" id="deleteTagId">
                                您确定要删除<span style="color:red;font-weight: bold" id="deleteArticleName"></span>文章吗？
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-info" id="ackDeleteArticleBtn">确定</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="box zq-margin-top-1">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <!-- /.box-header -->
                            <div class="box-body">
                                <div id="article-table-wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table id="article-table" class="table table-bordered table-hover dataTable"
                                                   role="grid" aria-describedby="example2_info">
                                                <thead>
                                                <tr role="row">
                                                    <th class="" tabindex="0" rowspan="1" colspan="1">
                                                        文章名称
                                                    </th>
                                                    <th class="" tabindex="0" rowspan="1" colspan="1">
                                                        标签
                                                    </th>
                                                    <th class="" tabindex="0" rowspan="1" colspan="1">
                                                        评论数量
                                                    </th>
                                                    <th class="" tabindex="0" rowspan="1" colspan="1">
                                                        作者
                                                    </th>
                                                    <th class="" tabindex="0" rowspan="1" colspan="1">
                                                        是否发布
                                                    </th>
                                                    <th class="" tabindex="0" rowspan="1" colspan="1">
                                                        编写日期
                                                    </th>
                                                    <th class="" tabindex="0" rowspan="1" colspan="1">
                                                        修改日期
                                                    </th>

                                                </tr>
                                                </thead>
                                                <tbody>
                                                <#assign articles=pageInfo.list>
                                                <#if articles?? && articles?size gt 0>
                                                    <#list articles as article>
                                                        <tr role="row" articleId="${article.id}">
                                                            <td class="">${(article.title)!''}</td>
                                                            <td class="">
                                                                <#if article.tags?? && article.tags?size gt 0>
                                                                    <#list article.tags as tag>
                                                                        <#if tag_index == 0>
                                                                            <span>${tag.name}</span>
                                                                        <#else>
                                                                            <span>| ${tag.name}</span>
                                                                        </#if>
                                                                    </#list>
                                                                <#else>
                                                                    <span>剪贴板</span>
                                                                </#if>
                                                            </td>
                                                            <td class="">${article.commentCount}</td>
                                                            <td class="">${(article.author)!''}</td>
                                                            <td class="">${(article.isDraft=='0')?string('是','否')}</td>
                                                            <td>${article.createTime?date}</td>
                                                            <td>${((article.updateTime?date)!'')}</td>
                                                        </tr>
                                                    </#list>
                                                </#if>
                                                </tbody>
                                                <tfoot>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="dataTables_paginate paging_simple_numbers"
                                                 id="example2_paginate">
                                                <ul class="pagination" style="float: right;">
                                                    <li class="paginate_button previous <#if pageInfo.navigateFirstPage==1>disabled</#if>">
                                                        <a onclick="doClick(${pageInfo.navigateFirstPage-5})">Previous</a>
                                                    </li>
                                                    <#list pageInfo.navigateFirstPage..pageInfo.navigateLastPage as i>
                                                        <li class="paginate_button <#if i==pageInfo.pageNum> active</#if>">
                                                            <a onclick="doClick(${i})">${i}</a></li>
                                                    </#list>
                                                    <li class="paginate_button next <#if pageInfo.navigateLastPage==pageInfo.pages>disabled</#if>">
                                                        <a onclick="doClick(${pageInfo.navigateLastPage+5})">Next</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                        </div>
                    </div>
                    <!-- /.box -->
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>

        </section>
        <section id="edit-aritcle-area" style="margin: 3rem 2rem 0 2rem;" >
            <div style="margin-top: 1rem">
                <button class="btn btn-primary" style="float: right" id="cancleBtn">取消</button>
                <button class="btn btn-primary" style="float: right;margin-right: 10px" id="saveDraftBtn">存为草稿</button>
                <button class="btn btn-primary" style="float: right;margin-right: 10px" id="releaseBtn">发布</button>
                <button class="btn btn-primary" style="float: right;margin-right: 10px" id="article_preview"
                        data-toggle="modal"
                        data-target="#modal-info">预览
                </button>
                <div style="clear: both"></div>
            </div>
            <form role="form" id="aritcle-form">
                <input type="hidden" id="isDraft" name="isDraft">
                <div class="box box-default">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="article_title">标题</label>
                            <input class="form-control" id="article_title" placeholder="请输入标题"
                                   style="border-radius: 2px"
                                   name="title">
                        </div>
                        <div class="form-group">
                            <label for="tags">标签</label>
                            <select multiple="multiple" name="tagIds" class="form-control select2" id="tags">
                                <#if tags??>
                                    <#list tags as tag>
                                        <option value="${tag.id}">${tag.name}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="article-real-content">正文</label>
                            <textarea class="form-control" id="article-real-content"
                                      style="min-height: 300px;border-radius: 2px" name="content"></textarea>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal modal-info fade" id="modal-info" style="display: none;">
                <div class="modal-dialog">
                    <div class="modal-content" style="min-height: 100%;padding: 2rem">

                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>

        </section>
    </div>
</@override>


<@override name="js">
    <script type="text/javascript">
        $(document).ready(function () {
            //document.getElementById("article-manage-link").click();
            // $('#article-manage-link').click();
            var $li = $("aside.main-sidebar ul.sidebar-menu > li:eq(1) > ul > li:eq(1)");
            $li.addClass('active');
            $li.parent().attr('style', 'display:block');
            $li.parent().parent().addClass('menu-open');
            /**
             * 点击选择行
             */
            $("#article-table > tbody > tr").on("click", function () {
                var isChoose = $(this).attr("choose");
                if (isChoose) {
                    $(this).css("background-color", "");
                    $(this).removeAttr("choose");
                    return;
                }
                $(this).parent().find("tr").each(function (n, item) {
                    $(this).css("background-color", "");
                    $(this).removeAttr("choose");
                });
                $(this).css("background-color", "#3385ff");
                $(this).attr("choose", "1");
            });
            /**
             * 绑定删除框事件
             */
            $("#deleteArticleBtn").on("click", function () {
                var $selectLi = $("#article-table  tr[choose='1']")[0];
                if (!$selectLi) {
                    alert("请先选择文章");
                    return;
                }
                $("#deleteArticleName").html($("tr[choose='1']").find("td").eq(0).text());
                $('#deleteArticleModal').modal('show');
            });

            $("#ackDeleteArticleBtn").on("click", function () {
                $.post("admin/article/delete", {articleId: $($("#article-table  tr[choose='1']")[0]).attr("articleId")}, function (data) {
                    if (data.code == 200) {
                        $('#deleteArticleModal').modal('hide');
                        location.reload();
                    }
                    alert(data.message);
                });
            });
        });

        function doClick(pageNum) {
            var selectKey = $("input[name='selectKey']").val();
            var addr;
            if (location.port) {
                addr = "http://" + location.host + ":" + location.port + "/admin/article/manage/" + pageNum;
            } else {
                addr = "http://" + location.host + "/admin/article/manage/" + pageNum;
            }
            if (selectKey) {
                location.href = addr + "?selectKey=" + selectKey;
            } else {
                location.href = addr;
            }
        }
    </script>
</@override>


<@extends name="../index.ftl" />


