//时间控件点击事件
$(function () {
    $("[name='orderQo.beginDate']").addClass("Wdate").click(function () {
        WdatePicker({
            "skin":"ext",
            "maxDate":new Date()||$("[name='orderQo.endDate']").val()
        });
    });
    $("[name='orderQo.endDate']").addClass("Wdate").click(function () {
        WdatePicker({
            "skin":"ext",
            "minDate":$("[name='orderQo.beginDate']").val(),
            "maxDate":new Date()
        });
    });
});
$(function () {
    $("[name='saleQo.beginDate']").addClass("Wdate").click(function () {
        WdatePicker({
            "skin":"ext",
            "maxDate":new Date()||$("[name='saleQo.endDate']").val()
        });
    });
    $("[name='saleQo.endDate']").addClass("Wdate").click(function () {
        WdatePicker({
            "skin":"ext",
            "minDate":$("[name='saleQo.beginDate']").val(),
            "maxDate":new Date()
        });
    });
});
//为图表添加一个change事件
$(function () {
   $(".btn_chart").change(function () {
       if($(this).val()=='line'){
           var url = "/chart_saleChartByLine.action?"+$("#searchForm").serialize();
           $.dialog.open(url,{
               id:"saleChartByLine",
               title:"销售报表",
               width:900,
               height:450
           });
       }else if($(this).val()=='pie'){
           var url = "/chart_saleChartByPie.action?"+$("#searchForm").serialize();
           $.dialog.open(url,{
               id:"saleChartByLine",
               title:"销售报表",
               width:900,
               height:450
           });
       }else if($(this).val()=='column'){
           var url = "/chart_saleChartByColumn.action?"+$("#searchForm").serialize();
           $.dialog.open(url,{
               id:"saleChartByColumn",
               title:"销售报表",
               width:900,
               height:460
           });
       }
   });
});