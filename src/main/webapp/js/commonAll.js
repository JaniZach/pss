//由于批量删除提交的数组是ids[]格式的,需要设置
jQuery.ajaxSettings.traditional = true;
//抽取页面中相同的js代码
$(function () {
    //为btn_input绑定一个跳转到input页面的事件-->跳转到input页面
    $(".btn_input").click(function () {
        //此处的url不可以写死,因此页面将需要跳转的url传过来
        var url = $(this).data("url");
        window.location.href = url;
    });
    //为btn_page绑定一个提交页面的事件-->分页相关按钮
    $(".btn_page").click(function () {
        //解决点击跳转按钮,提交的currentPage为空的问题,使用||符号
        var pageNo = $(this).data("page")||$("[name='qo.currentPage']").val();
        $("[name='qo.currentPage']").val(pageNo);
        $("#searchForm").submit();
    });
    //为页面大小添加一个onchange事件

    $(":input[name='qo.pageSize']").change(function () {
        $("#searchForm").submit();
    });
    //批量删除--为btn_batchDelete绑定一个点击事件
    $(".btn_batchDelete").click(function () {
        var url = $(this).data("url");
        var ids = new Array();
        //获取所有批量删除的id
        /*var checkEle = $(".acb:checked").get();
        for(var i=0;i<checkEle.length;i++){
            var deleteId = $(checkEle[i]).data("id");
            ids.push(deleteId);
        }*/
        //方法二,使用$.map方法获取ids
        ids = $.map($(".acb:checked"),function (item,index,arr) {
           return  $(item).data("id");
        });
        //判断ids是否为空
        if(ids.length==0){
            $.dialog({
                title:"温馨提示",
                content:"请选择需要删除的数据",
                ok:true
            });
            return;
        }
        $.dialog({
            title:"温馨提示",
            content:"是否进行批量删除?",
            ok:function () {
                $.post(url,{
                    "ids":ids
                },function (msg) {
                    //删除成功后,加载页面
                    $.dialog({
                        title:"温馨提示",
                        icon:"succeed",
                        content:msg,
                        ok:function () {
                            window.location.reload();
                        }
                    });
                });
            },
            cancel:true
        });
    });
});
$(function () {
   $(".btn_delete").click(function () {
       var url = $(this).data("url");
       $.dialog({
           title:"温馨提示",
           content:"确定进行删除吗?",
           ok:function () {
               $.get(url,function (msg) {
                   $.dialog({
                      title:"温馨提示",
                       icon:"succeed",
                      content:msg,
                      ok:function () {
                           window.location.reload();
                      }
                   });
               });
           },
           cancel:true
       });
   });
});
//为all按钮绑定一个onchange事件
$(function () {
   $("#all").change(function () {
       //注意checked是自带的属性,应使用prop(),非自带属性,应使用attr()
       $("[name='IDCheck']").prop("checked",$(this).prop("checked"));
   });
});
//为 审核按钮 添加一个点击事件
$(function () {
   $(".btn_audit").click(function () {
       var url = $(this).data("url");
       $.dialog({
           title:"温馨提示",
           content:"是否审核?",
           ok:function () {
               $.get(url,function (data) {
                  $.dialog({
                      title:"温馨提示",
                      content:data,
                      ok:function () {
                          window.location.reload();
                      }
                  });
               });
           },
           cancel:true
       });
   });
});
//时间控件点击事件
$(function () {
    $("[name='qo.beginDate']").addClass("Wdate").click(function () {
        WdatePicker({
            "skin":"ext",
            "maxDate":new Date()||$("[name='qo.endDate']").val()
        });
    });
    $("[name='qo.endDate']").addClass("Wdate").click(function () {
        WdatePicker({
            "skin":"ext",
            "minDate":$("[name='qo.beginDate']").val(),
            "maxDate":new Date()
        });
    });
});