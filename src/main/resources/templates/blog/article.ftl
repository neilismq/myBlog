<@override name="content">
</@override>
<@override name="js">
<script type="text/javascript">
    $(document).ready(function () {
        var aa="${article.content?j_string}";
        var converter = new showdown.Converter(),
            html = converter.makeHtml(aa);
        $("#article-content").html(html);

    });
</script>
</@override>
<@extends name="../layout/article_layout.ftl"/>
