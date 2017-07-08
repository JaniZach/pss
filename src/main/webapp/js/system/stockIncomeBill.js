//入库单js操作
//为 添加明细按钮 绑定点击事件
$(function () {
    $(".appendRow").click(function () {
        var cloneTr = $("#edit_table_body tr:first").clone();
        //清空克隆行的数据
        cloneTr.find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
        cloneTr.find("[tag=brand],[tag=amount]").html("");
        $("#edit_table_body").append(cloneTr);
    });
});
//为行中的各事件源绑定事件
$(function () {
    $("#edit_table_body").on("click", ".searchproduct", function () {
        //为图片绑定一个点击事件
        var currentTr = $(this).closest("tr");
        var url = "/product_selectProduct.action";
        $.dialog.open(url, {
            id: "selectProduct",
            title: "货品选择列表",
            width: 950,
            height: 680,
            close:function () {
                // 获取子窗口传过来的信息
                var productJson = $.dialog.data("productJson");
                if(productJson){
                    currentTr.find("[tag=name]").val(productJson.name);
                    currentTr.find("[tag=pid]").val(productJson.id);
                    currentTr.find("[tag=costPrice]").val(productJson.costPrice);
                    currentTr.find("[tag=brand]").html(productJson.brandName);
                }
            }

        });
    }).on("change","[tag=costPrice],[tag=number]",function () {
        //绑定一个值改变事件,自动获取金额小计
        var currentTr = $(this).closest("tr");
        var costPrice = currentTr.find("[tag=costPrice]").val();
        var number = currentTr.find("[tag=number]").val();
        var amount = costPrice * number;
        currentTr.find("[tag=amount]").html(amount.toFixed(2));
    }).on("click",".removeItem",function () {
        //为 删除明细 绑定一个点击事件
        var currentTr = $(this).closest("tr");
        if($("#edit_table_body tr").size()>1){
            currentTr.remove();
        }else{
            //清空当前行的数据
            currentTr.find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
            currentTr.find("[tag=brand],[tag=amount]").html("");
        }
    });
});
//为表单绑定提交事件
$(function () {
    $("#editForm").submit(function () {
        $.each($("#edit_table_body tr"),function (index,item) {
            var currentTr = $(item);
            currentTr.find("[tag=pid]").prop("name","stockIncomeBill.items["+index+"].product.id");
            currentTr.find("[tag=costPrice]").prop("name","stockIncomeBill.items["+index+"].costPrice");
            currentTr.find("[tag=number]").prop("name","stockIncomeBill.items["+index+"].number");
            currentTr.find("[tag=remark]").prop("name","stockIncomeBill.items["+index+"].remark");
        });
    });
});
//绑定时间控件
$(function () {
    $("[name='stockIncomeBill.vdate']").addClass("Wdate").click(function () {
        WdatePicker({
            "skin": "ext",
            "maxDate": new Date()
        });
    });
});