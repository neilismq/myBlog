<@override name="content">
    <div>
        <section class="content-header">
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li>tag</li>
                <li class="active">manage</li>
            </ol>
        </section>
    </div>
    <section id="add-article" style="margin: 2rem 2rem 0 2rem;" class="content">

        <!-- Modal -->
        <div class="modal fade" id="addTagModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document" aria-hidden="true">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel"><b>添加标签</b></h4>
                    </div>
                    <div class="modal-body">
                        <form role="form">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="tagName" class="col-sm-2 control-label">标签名称</label>

                                    <div class="col-sm-10">
                                        <input type="text" style="display: none" id="tagId">
                                        <input type="text" class="form-control" id="tagName" placeholder="标签名称">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" id="saveTagBtn">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="deleteTagModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document" aria-hidden="true">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel"><b>删除标签</b></h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="deleteTagId">
                        您确定要删除<span style="color:red;font-weight: bold" id="deleteTageName"></span>吗？
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" id="ackDeleteTagBtn">确定</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>

        <div style="padding-left: 1rem">
            <div class="row">
                <div class="col-sm-6">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addTagModal"
                            id="addTagBtn">
                        添加标签
                    </button>
                </div>
                <div class="col-sm-4"></div>
                <div class="col-sm-2">
                    <form action="${adminPath}/tag/manage/1" method="GET">
                        <input class="form-control input-group-sm" placeholder="请输入标签名称" name="selectKey"
                               value="${selectKey!''}">
                    </form>
                </div>
            </div>
        </div>
        <div class="box" style="margin-top: 1rem">
            <!-- /.box-header -->
            <div class="box-body">
                <table class="table table-bordered">
                    <tbody>
                    <tr>
                        <th style="width: 50px">序号</th>
                        <th>标签名称</th>
                        <th>文章数量</th>
                        <th style="width: 150px">操作</th>
                    </tr>
                    <#assign tags=pageInfo.list />
                    <#if tags?? >
                        <#list tags as tag>
                            <tr tagId="${tag.id}">
                                <td>${tag_index+1}</td>
                                <td>${tag.name}</td>
                                <td>${tag.articleCount!0}</td>
                                <td>
                                    <button class="btn btn-info btn-sm alterTagNameBtn">修改</button>
                                    <button class="btn btn-danger btn-sm deleteTagNameBtn">删除</button>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>

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

    </section>

</@override>


<@override name="js">
    <script type="text/javascript">
        $(document).ready(function () {

            $("#addTagModal").on('show.bs.modal', function () {
                $("#tagName").val('');
                $("#tagId").val('');
            });
            $(".alterTagNameBtn").each(function (n, item) {
                $(item).on('click', function () {
                    $('#addTagModal').modal('show');
                    var $tagName = $(this).parent().parent().find("td:eq(1)").text();
                    var $tagId = $(this).parent().parent().attr('tagId');
                    $("#tagName").val($tagName);
                    $("#tagId").val($tagId);
                });
            });
            $(".deleteTagNameBtn").each(function (n, item) {
                $(item).on('click', function () {
                    $('#deleteTagModal').modal('show');
                    var $tagName = $(this).parent().parent().find("td:eq(1)").text();
                    var $tagId = $(this).parent().parent().attr('tagId');
                    $("#deleteTageName").text($tagName);
                    $("#deleteTagId").val($tagId);
                });
            });

            $("#ackDeleteTagBtn").on('click', function () {
                $.post('${adminPath}/tag/delete', {tagId: $("#deleteTagId").val()}, function (data) {
                    alert(data.message);
                    if (data.code == 200) {
                        $('#deleteTagModal').modal('hide');
                        location.reload();
                    }
                })
            });
            $("#saveTagBtn").click(function () {
                var $name = $("#tagName").val();
                if (!$name) {
                    alert("标签名称不能为空");
                }
                var $tagId = $("#tagId").val();
                if ($tagId) {
                    //更新
                    $.post("${adminPath}/tag/update", {tagId: $tagId, tagName: $name}, function (data) {
                        alert(data.message);
                        if (data.code == 200) {
                            $('#addTagModal').modal('hide');
                            location.reload();
                        }
                    });
                } else {
                    //插入
                    $.post("${adminPath}/tag/add", {tagName: $name}, function (data) {
                        alert(data.message);
                        if (data.code == 200) {
                            $('#addTagModal').modal('hide');
                            location.reload();
                        }
                    });
                }
            });
            var $li = $("a[href^='${adminPath}/tag/manage']").parent();
            $li.addClass('active');

        });
        $("#article-real-content").keyup(function () {
            var converter = new showdown.Converter(),
                html = converter.makeHtml($("#article-real-content").val());
            $("div.modal-content").html(html);
        });

        function doClick(pageNum) {
            var selectKey = $("input[name='selectKey']").val();
            var addr;
            if (location.port) {
                addr = "http://" + location.host + ":" + location.port + "/${adminPath}/tag/manage/" + pageNum;
            } else {
                addr = "http://" + location.host + "/${adminPath}/tag/manage/" + pageNum;
            }
            if (selectKey) {
                location.href = addr + "?selectKey=" + selectKey;
            } else {
                location.href = addr;
            }
        }
        // $("#article-real-content")[0].addEventListener("paste",setClipboardText);
        //
        // function setClipboardText(event){
        //     event.preventDefault();
        //     if(event.clipboardData.items){
        //         alert("chrome");
        //         $.each(event.clipboardData.items,function (index,item) {
        //             console.log(item.kind +'   '+item.type )
        //         })
        //     }else {
        //         alert("non-chrome");
        //     }
        // };
    </script>
</@override>


<@extends name="../index.ftl" />


