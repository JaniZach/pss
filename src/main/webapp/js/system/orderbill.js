// 当点击搜索图片,弹出货品信息
$(function () {
   $("#edit_table_body").on("click",".searchproduct",function () {
       //获取得到事件源所在行
       var currentTr = $(this).closest("tr");
       var url = "/product_selectProduct.action";
       $.dialog.open (url,{
           id:"selectProduct",
           title:"货品选择",
           width:950,
           height:680,
           close:function () {
               //获取子窗口传过来Json参数
               var productJson = $.dialog.data("productJson");
               if(productJson){
                   //将Json对象的值为页面赋值
                   currentTr.find("[tag=pid]").val(productJson.id);
                   currentTr.find("[tag=name]").val(productJson.name);
                   currentTr.find("[tag=brand]").html(productJson.brandName);
                   currentTr.find("[tag=costPrice]").val(productJson.costPrice);
               }
           }
       });
   }).on("change","[tag=number],[tag=costPrice]",function () {
       //为costPrice和number添加一个值改变事件(或失去焦点事件)
       var number = $(this).closest("tr").find("[tag=number]").val();
       var costPrice = $(this).closest("tr").find("[tag=costPrice]").val();
       var amount = number * costPrice;
       $(this).closest("tr").find("[tag=amount]").html(amount.toFixed(2));
   }).on("click",".removeItem",function () {
       //为 removeItem删除明细 按钮添加事件
       if($("#edit_table_body").size()>1){
           $(this).closest("tr").remove();
       }else {
           //清空行中的数据
           $(this).closest("tr").find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
           $(this).closest("tr").find("[tag=brand],[tag=amount]").html("");
       }
   });
});
//保存多条数据的处理办法
$(function () {
   $("#editForm").submit(function () {
       //迭代所有的行
       $.each($("#edit_table_body tr"),function (index,item) {
           $(item).find("[tag=pid]").prop("name","orderBill.items["+index+"].product.id");
           $(item).find("[tag=costPrice]").prop("name","orderBill.items["+index+"].costPrice");
           $(item).find("[tag=number]").prop("name","orderBill.items["+index+"].number");
           $(item).find("[tag=remark]").prop("name","orderBill.items["+index+"].remark");
       });
   });
});
//为 添加明细 按钮添加事件
$(function () {
    $(".appendRow").click(function () {
        var cloneTr = $("#edit_table_body tr:first").clone();
        //清空行中的数据
        cloneTr.find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
        cloneTr.find("[tag=brand],[tag=amount]").html("");
        $("#edit_table_body").append(cloneTr);
    });
});
//添加时间控件
$(function () {
   $("[name='orderBill.vdate']").addClass("Wdate").click(function () {
       WdatePicker({
           "skin":"ext",
           "maxDate":new Date()
       });
   });
});