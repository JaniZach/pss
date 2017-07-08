//stockOutcomeBill采购入库单的查看页面相关的js
//将全部数据设置为只读
$(function () {
   $("*").prop("readOnly",true);
});
//返回列表页面按钮 绑定点击事件
$(function () {
   $("#backbutton").click(function () {
       window.history.back();
   });
});