<@override name="content">
</@override>
<@override name="js">
<script type="text/javascript">
    $(document).ready(function () {
        $("#convert").on("click", function () {
            // var converter = new showdown.Converter(),
            //     html = converter.makeHtml($("#input").val());
            // $("#output").html(html);
            //md = converter.makeMd('<a href="https://patreon.com/showdownjs">Please Support us!</a>');

        })
        var converter = new showdown.Converter(),
            html = converter.makeHtml("${article.content!''}");
        $("#article-content").html(html);

    });
</script>
</@override>
<@extends name="../layout/archives_layout.ftl"/>