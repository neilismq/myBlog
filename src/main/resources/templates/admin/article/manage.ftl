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
        <section id="add-article" style="margin: 2rem 2rem 0 2rem;" class="content">

            <div style="padding-left: 1rem">
                <!-- Button trigger modal -->
                <div class="row">
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-primary btn-success"
                                id="addArticleBtn">
                            新增
                        </button>
                        <button type="button" class="btn btn-primary"
                                id="editArticleBtn">
                            编辑
                        </button>
                        <button type="button" class="btn btn-danger" id="deleteArticleBtn">
                            删除
                        </button>
                    </div>
                    <div class="col-sm-4"></div>
                    <div class="col-sm-2">
                        <form action="${adminPath}/article/manage/search" method="GET">
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
                                                            <td class="">${(article.commentCount)!0}</td>
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
        <section id="edit-aritcle-area" style="margin: 3rem 2rem 0 2rem;" hidden="hidden">
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
                <input type="hidden" name="articleId">
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
                            <select multiple="multiple" name="tagIds" class="form-control select2" id="tags"
                                    style="width: 100%">

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
            // var $li = $("aside.main-sidebar ul.sidebar-menu > li:eq(1) > ul > li:eq(1)");
            // $li.addClass('active');
            // $li.parent().attr('style', 'display:block');
            // $li.parent().parent().addClass('menu-open');

            var $li = $("a[href='${adminPath}/article/manage']").parent();
            $li.addClass('active');
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

            /**
             * 删除确认
             */
            $("#ackDeleteArticleBtn").on("click", function () {
                $.post("${adminPath}/article/delete", {articleId: $($("#article-table  tr[choose='1']")[0]).attr("articleId")}, function (data) {
                    if (data.code == 200) {
                        $('#deleteArticleModal').modal('hide');
                        location.replace(location.href);
                    }
                    alert(data.message);
                });
            });

            $("#addArticleBtn").on('click',function () {
                $("input[name='articleId'").val('');
                <#--$.post('http://39.107.101.131/${adminPath}/article/dropdown/query', {articleId: null}, function (data) {-->
                $.post('/${adminPath}/article/dropdown/query', {articleId: null}, function (data) {
                    $(".select2").select2({data: data.body.results});
                });
                $("#add-article").attr("hidden", "hidden");
                $("#edit-aritcle-area").removeAttr("hidden");

            });
            /**
             * 编辑按钮点击事件绑定
             */
            $("#editArticleBtn").on('click', function () {
                var $val = $($("#article-table  tr[choose='1']")[0]).attr("articleId");
                if (!$val) {
                    alert("请先选择文章");
                    return;
                }
                $("input[name='articleId'").val($val);
                // $.post('http://39.107.101.131/${adminPath}/article/dropdown/query', {articleId: $val}, function (data) {
                $.post('/${adminPath}/article/dropdown/query', {articleId: $val}, function (data) {
                    $(".select2").select2({data: data.body.results});
                });
                $.post("${adminPath}/article/query", {articleId: $val}, function (data) {
                    if (data.code == 200) {
                        var article = data.body.article;
                        $("[name='title']").val(article.title);
                        $("#article-real-content").val(article.content);
                        var converter = new showdown.Converter(),
                            html = converter.makeHtml(article.content);
                        $("div.modal-content").html(html);
                        $("#add-article").attr("hidden", "hidden");
                        $("#edit-aritcle-area").removeAttr("hidden");
                    } else {
                        alert(data.message);
                    }
                });
            });
            //文章预览
            $("#article-real-content").keyup(function () {
                var converter = new showdown.Converter(),
                    html = converter.makeHtml($("#article-real-content").val());
                $("div.modal-content").html(html);
            });
            //文章发布
            $("#releaseBtn").on('click', function () {
                $("#isDraft").val("0");
                submitForm();
                setTimeout(function () {
                    location.replace(location.href);
                },300);
            });
            //存为草稿
            $("#saveDraftBtn").on('click', function () {
                $("#isDraft").val("1");
                submitForm();
                setTimeout(function () {
                    location.replace(location.href);
                },300);
            });
            $("#cancleBtn").on('click', function () {
                location.replace(location.href);
            });

            ZZQ.html5upload.init();
        });

        function submitForm() {
            $.post("${adminPath}/article/save", $("#aritcle-form").serialize(), function (data) {
                alert(data.message);
            });
        }

        function doClick(pageNum) {
            var selectKey = $("input[name='selectKey']").val();
            var addr;
            if (location.port) {
                addr = "http://" + location.host + ":" + location.port + "/${adminPath}/article/manage/" + pageNum;
            } else {
                addr = "http://" + location.host + "/${adminPath}/article/manage/" + pageNum;
            }
            if (selectKey) {
                location.href = addr + "?selectKey=" + selectKey;
            } else {
                location.href = addr;
            }
        }

        var ZZQ = {};
        ZZQ.html5upload = (function () {
            var _ID_UPLOAD_BOX = "article-real-content";
            var _CLASS_PROGRESS = "progress";
            var _CLASS_PERCENTAGE = "percentage";

            var _tip_no_drag = "将文件拖拽至此区域，即可上传！";
            var _tip_drag_over = "释放鼠标立即上传！";

            var _uploadEle = null;

            /**
             * 初始化对象与事件
             * @private
             */
            function _init() {
                _uploadEle = document.getElementById(_ID_UPLOAD_BOX);
                _uploadEle.ondragenter = _onDragEnter;
                _uploadEle.ondragover = _onDragOver;
                _uploadEle.ondragleave = _onDragLeave;
                _uploadEle.ondrop = _onDrop;
                //_setStatusNoDrag();
            };


            /**
             * 正在拖拽状态
             * @private
             */
            function _setDragOverStatus() {
                // if (_checkContatinsElements()) return;
                // _uploadEle.innerText = _tip_drag_over;
                // _uploadEle.style.border = "2px dashed #777";
                // $(_uploadEle).css({lineHeight: $(_uploadEle).height() + "px"});
            }

            function _setStatusNoDrag() {
                if (_checkContatinsElements()) return;
                _uploadEle.innerText = _tip_no_drag;
                _uploadEle.style.border = "2px dashed #777";
                $(_uploadEle).css({lineHeight: $(_uploadEle).height() + "px"});
            }

            /**
             * 上传文件
             * @private
             */
            function _setDropStatus() {
                if (_checkContatinsElements()) return;
                _uploadEle.innerText = "";
                _uploadEle.style.border = "1px solid #444";
                $(_uploadEle).css({lineHeight: "1em"});
                $(_uploadEle).append("<ul></ul>");
            };


            /**
             * 判断是否已经上传文件了
             * @private
             */
            function _checkContatinsElements() {
                return !!$(_uploadEle).find("li").size();

            }

            /**
             * 当被鼠标拖动的对象进入其容器范围内时触发此事件
             * @private
             */
            function _onDragEnter(ev) {
                _setDragOverStatus();
            }

            /**
             * 当某被拖动的对象在另一对象容器范围内拖动时触发此事件
             * @private
             */
            function _onDragOver(ev) {
                //ondragover中必须组织事件的默认行为，默认地，无法将数据/元素放置到其他元素中。
                ev.preventDefault();
            }

            /**
             * 当被鼠标拖动的对象离开其容器范围内时触发此事件
             * @private
             */
            function _onDragLeave(ev) {
                // _setStatusNoDrag();
            }

            /**
             * 在一个拖动过程中，释放鼠标键时触发此事件
             * @private
             */
            function _onDrop(ev) {
                //drop 事件的默认行为是以链接形式打开，所以也需要阻止其默认行为。
                ev.preventDefault();
                //_setDropStatus();
                //拿到拖入的文件
                var files = ev.dataTransfer.files;
                var len = files.length;
                for (var i = 0; i < len; i++) {
                    //页面上显示需要上传的文件
                    _showUploadFile(files[i]);
                }
            }

            /**
             * 页面上显示需要上传的文件
             * @private
             */
            function _showUploadFile(file) {
                var reader = new FileReader();
//        console.log(file)
//        console.log(reader);

                //判断文件类型
                if (file.type.match(/image*/)) {
                    reader.onload = function (e) {
                        var formData = new FormData();
                        // var li = $("#template li").clone();
                        // var img = li.find("img");
                        // var progress = li.find(".progress");
                        // var percentage = li.find(".percentage");
                        // percentage.text("0%");
                        // img.attr("src", e.target.result);
                        // $("ul", $(_uploadEle)).append(li);
                        // $(_uploadEle).find("li").size() == 10 && $(_uploadEle).width(($(_uploadEle).width() + 8) + "px").css("overflow", "auto");
                        //设置上传的文件名称key
                        formData.append("file", file);

                        //上传文件到服务器
                        //_uploadToServer(formData, li, progress, percentage);
                        $.ajax({
                            url: "${adminPath}/article/upload",
                            type: "POST",
                            data: formData,
                            /**
                             *必须false才会自动加上正确的Content-Type
                             */
                            contentType: false,
                            /**
                             * 必须false才会避开jQuery对 formdata 的默认处理
                             * XMLHttpRequest会对 formdata 进行正确的处理
                             */
                            processData: false,
                            success: function (data) {
                                alert("上传" + data.message);
                                if (data.code == 200) {
                                    var $content = $("#" + _ID_UPLOAD_BOX).val();
                                    var $val = $content + "\n" + "![](" + data.body.imgUrl + ")";
                                    $("#" + _ID_UPLOAD_BOX).val($val);
                                    var converter = new showdown.Converter(),
                                        html = converter.makeHtml($val);
                                    $("div.modal-content").html(html);
                                }
                            },
                            error: function () {
                                alert("上传失败！");
                            }
                        });
                    };
                    reader.readAsDataURL(file);
                } else {
                    alert("此" + file.name + "不是图片文件！");
                }
            }

            /**
             * 上传文件到服务器
             * @private
             */
            function _uploadToServer(formData, li, progress, percentage) {
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "/${adminPath}/article/upload", true);
                <#--xhr.open("POST", "http://39.107.101.131/${adminPath}/article/upload", true);-->
                xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest', 'Content-Type', 'multipart/form-data;');

                //HTML5新增的API，存储了上传过程中的信息
                xhr.upload.onprogress = function (e) {
                    var percent = 0;
                    if (e.lengthComputable) {
                        //更新页面显示效果
                        percent = 100 * e.loaded / e.total;
                        progress.height(percent);
                        percentage.text(percent + "%");
                        percent >= 100 && li.addClass("done");
                    }
                };
                xhr.send(formData);
            }


            //把init方法公布出去
            return {
                init: _init
            }


        })();
    </script>
</@override>


<@extends name="../index.ftl" />


