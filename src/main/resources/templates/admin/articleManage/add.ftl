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
        <button class="btn btn-primary" style="float: right;margin-right: 10px" id="article_preview" data-toggle="modal"
                data-target="#modal-info">预览
        </button>
        <div style="clear: both"></div>
    </div>
    <div class="form-group">
        <label for="article_title">标题</label>
        <input class="form-control" id="article_title" placeholder="请输入标题" style="border-radius: 2px">
    </div>
    <div class="form-group">
        <label for="article-real-content">正文</label>
        <textarea class="form-control" id="article-real-content" style="min-height: 300px;border-radius: 2px">
        </textarea>
    </div>
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
    $("#article-real-content").keyup(function () {
        var converter = new showdown.Converter(),
            html = converter.makeHtml($("#article-real-content").val());
        $("div.modal-content").html(html);
    });

    $("#article-real-content").addEventListener("paste",setClipboardText);

    function setClipboardText(event){
        event.preventDefault();
        event.clipboardData.getData("text");

    };
</script>
</@override>


<@extends name="../template.ftl" />


