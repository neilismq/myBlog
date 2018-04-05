<%@page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <script src="/resource/showdown.js"></script>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            background-color: #f5f5d5;
            font-family: Georgia, serif;
            letter-spacing: -0.01em;
            word-spacing: 0.2em;
            line-height: 1.8em;
            font-size: 62.5%;
            color: #111111;
            width: 98%;
        }

        body.one-column #container {
            width: 65%;
            min-width: 640px;
            max-width: 960px;
            margin: 0 auto;
            background-color: #f5f5d5;
            padding: 1em;
        }

        div {
            margin: 0;
            padding: 0;
            list-style-type: none;
            text-align: left;
            text-decoration: none;
            border-bottom: none;
            font-weight: normal;
            font-style: normal;
            border: none;
            color: #111111;
        }

        #header {
            border-bottom: 1px solid #d3d3d3;
            margin: 1em;
            float: left;
            height: 5em;
            width: 100%;
        }

        #content {
            clear: both;
            margin: 1.5em 1em 1em 1em;
        }
    </style>
</head>
<body class="one-column">
<div style="display: none">
    <textarea id="content1" style="height: 300px;width: 400px" onblur="compile();"></textarea>
    <span id="result" style=" float: right;height: 300px;width: 400px"></span>
</div>
<div id="container">
    <div id="container-inner">
        <div id="header">
            <div id="header-inner">
                <div class="header-content">
                    <div id="header-name">
                        赵志强的网络日志
                        <span>
                     »
                    <a href="http://www.baidu.com">首页</a>
                </span>
                        <span>
                     »
                    <a href="http://www.baidu.com">档案</a>
                </span>
                    </div>
                </div>
            </div>
        </div>
        <div id="content">

        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function compile() {
        var content = document.getElementById("content1").value;
        var converter = new showdown.Converter();
        var html = converter.makeHtml(content);
        document.getElementById("result").innerHTML = html;
    }
</script>