//为 选择该商品 按钮添加点击事件
$(function () {
   $(".btn_select").on('click',function () {
       //console.debug(this);
      //获取json对象
       var productJson = $(this).data("product");
       //console.debug(productJson);
       if(productJson){
           //将json对象传递到父窗口
           $.dialog.data("productJson",productJson);
           //关闭子窗口
           $.dialog.close();
       }
   });
});