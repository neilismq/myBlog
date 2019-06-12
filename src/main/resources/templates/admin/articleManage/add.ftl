<@override name="content">
    <div>
        <section class="content-header">
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li>article</li>
                <li class="active">add</li>
            </ol>
        </section>
    </div>
    <section id="add-article" style="margin: 2rem 2rem 0 2rem;" class="content">
        <div style="margin-top: 1rem">
            <button class="btn btn-primary" style="float: right">取消</button>
            <button class="btn btn-primary" style="float: right;margin-right: 10px">存为草稿</button>
            <button class="btn btn-primary" style="float: right;margin-right: 10px">发布</button>
            <button class="btn btn-primary" style="float: right;margin-right: 10px" id="article_preview"
                    data-toggle="modal"
                    data-target="#modal-info">预览
            </button>
            <div style="clear: both"></div>
        </div>
        <form role="form">
            <div class="box box-default">
                <div class="box-body">
                    <div class="form-group">
                        <label for="article_title">标题</label>
                        <input class="form-control" id="article_title" placeholder="请输入标题" style="border-radius: 2px">
                    </div>
                    <div class="form-group">
                        <label for="tags">选择标签</label>
                        <select multiple="multiple" name="tagNames[]" class="form-control select2" id="tags">
                            <#if tags??>
                                <#list tags as tag>
                                    <option value="${tag.id}">${tag.name}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="form-group" >
                        <label for="article-real-content">正文</label>
                        <textarea class="form-control" id="article-real-content"
                                  style="min-height: 300px;border-radius: 2px"></textarea>
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

</@override>


<@override name="js">
    <script type="text/javascript">
        $(document).ready(function () {
            //document.getElementById("article-manage-link").click();
            // $('#article-manage-link').click();
            var $li = $("aside.main-sidebar ul.sidebar-menu li:eq(2) ul li:eq(0)");
            $li.addClass('active');
            $li.parent().attr('style', 'display:block');
            $li.parent().parent().addClass('menu-open');
            $('.select2').select2();

            $("#article-real-content").keyup(function () {
                var converter = new showdown.Converter(),
                    html = converter.makeHtml($("#article-real-content").val());
                $("div.modal-content").html(html);
            });
            ZZQ.html5upload.init();

        });
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
                            url: "admin/article/upload",
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
                                    var $content=$("#"+_ID_UPLOAD_BOX).val();
                                    $("#"+_ID_UPLOAD_BOX).val($content+"\n"+"![]("+data.body.imgUrl+")")
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
                xhr.open("POST", "http://localhost/blog/admin/article/upload", true);
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


        function paste_img(e) {
            if (e.clipboardData && e.clipboardData.items) {
                var imageContent = e.clipboardData.getData('image/png');
                ele = e.clipboardData.items;
                for (var i = 0; i < ele.length; ++i) {
                    //粘贴图片
                    if (ele[i].kind == 'file' && ele[i].type.indexOf('image/') !== -1) {
                        var blob = ele[i].getAsFile();
                        window.URL = window.URL || window.webkitURL;
                        var blobUrl = window.URL.createObjectURL(blob);
                        // 显示到div中，此时是显示的本地图片数据，并没有上传到服务器
                        var new_img = document.createElement('img');
                        new_img.setAttribute('src', blobUrl);
                        new_img.setAttribute('blobdata', blob);
                        // 移动div光标到新元素后面
                        insertHtmlAtCaret(new_img);
                        // 直接上传，当然你也可以不在这上传，可以点击按钮在上传
                        uploadImg(blob);
                    }
                }
            } else {
                alert('不支持的浏览器');
            }
            return true;
        }

        //聊天内容框 插入文本或者其他元素后，移动置光标到最新处
        function insertHtmlAtCaret(childElement) {
            var sel, range;
            if (window.getSelection) {
                // IE9 and non-IE
                sel = window.getSelection();
                if (sel.getRangeAt && sel.rangeCount) {
                    range = sel.getRangeAt(0);
                    range.deleteContents();

                    var el = document.createElement("div");
                    el.appendChild(childElement);
                    var frag = document.createDocumentFragment(), node, lastNode;
                    while ((node = el.firstChild)) {
                        lastNode = frag.appendChild(node);
                    }

                    range.insertNode(frag);
                    if (lastNode) {
                        range = range.cloneRange();
                        range.setStartAfter(lastNode);
                        range.collapse(true);
                        sel.removeAllRanges();
                        sel.addRange(range);
                    }
                }
            } else if (document.selection && document.selection.type != "Control") {
                // IE < 9
                //document.selection.createRange().pasteHTML(html);
            }
        }

        var createStandardXHR = function () {
            try {
                return new window.XMLHttpRequest();
            } catch (e) {
                return false;
            }
        };
        var createActiveXHR = function () {
            try {
                return new window.ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                return false;
            }
        };

        var xhr;

        function createXHR() {
            var temp = createStandardXHR() || createActiveXHR();

            if (window.XDomainRequest === undefined) {
                return temp;
            } else {
                return new XDomainRequest();
            }
        }

        //前端上传方法
        function uploadImg(obj) {

            xhr = createXHR();
            if (xhr) {
                xhr.onerror = err;
                xhr.ontimeout = timeo;
                xhr.onprogress = progres;
                xhr.onload = loadd;
                xhr.timeout = timeo;

            } else {
                alert("Failed to create");
            }

            //发送的数据
            var fd = new FormData();
            fd.append("image", obj, "imgtest.png");

            //使用ajax发送
            xhr.open('POST', 'admin/article/upload', true);//第二个参数是服务器处理action，各个语言提供方式不一样，我这是.net mvc 后台处理的，具体方法见步骤5
            xhr.send(fd);
        }

        function err() {
            // alert("XDR onerror");
        }

        function timeo() {
            // alert("XDR ontimeout");
        }

        function loadd() {
            //  alert("上传完成");
            // alert("Got: " + xhr.responseText);
        }

        function progres() {
            //alert("XDR onprogress");
        }

        function stopdata() {
            xhr.abort();
        }
    </script>
</@override>


<@extends name="../index.ftl" />


