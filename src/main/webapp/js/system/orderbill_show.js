//show页面保证所有数据为可读模式
$(function () {
   $("*").prop("readOnly",true);
});
//为 返回列表按钮 添加点击事件
$(function () {
    $("#backbutton").click(function () {
        window.history.back();
    });
});
