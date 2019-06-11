
winopen("/index.php?m=&c=User&a=add", 704,600);
/*打开弹出窗口*/
function winopen(url, w, h) {
    url = fix_url(url);
    $("body").addClass("modal-open");
    $("div.shade").show();
    var _body = $("body").eq(0);
    if ($("#dialog").length == 0) {
        if (!is_mobile()) {
            _body.append("<div id=\"dialog\" ><iframe class=\"myFrame\" src='" + url + "' style='width:" + w + "px;height:100%' scrolling='auto' ></iframe></div>");
            $("#dialog").css({
                "width" : w,
                "height" : h,
                "position" : "fixed",
                "z-index" : "3000",
                "top" : ($(window).height() / 2 - h / 2),
                "left" : (_body.width() / 2 - w / 2),
                "background-color" : "#ffffff",
                // "border":"1px solid #ddd"
            });
        } else {
            $("div.shade").css("width", _body.width());
            _body.append("<div id=\"dialog\" ><iframe class=\"myFrame\" src='" + url + "' style='width:100%;height:100%' scrolling='auto' ></iframe></div>");
            $("#dialog").css({
                "width" : _body.width(),
                "height" : h,
                "position" : "fixed",
                "z-index" : "3000",
                "top" : 0,
                "left" : 0,
                "background-color" : "#ffffff"
            });
        }
    } else {
        $("#dialog").show();
    }
}

/* 关闭弹出窗口*/
function myclose() {
    parent.winclose();
}

function fix_url(url) {
    var ss = url.split('?');
    url = ss[0] + "?";
    for (var i = 1; i < ss.length; i++) {
        url += ss[i] + "&";
    }
    if (ss.length > 0) {
        url = url.substring(0, url.length - 1);
    }
    return url;
}

/* 判断是否是移动设备 */
function is_mobile() {
    return navigator.userAgent.match(/mobile/i);
}

function winclose() {
    $("body").removeClass("modal-open");
    $("div.shade").hide();
    $("#dialog").html("");
    $("#dialog").remove();
}
