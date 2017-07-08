//permission相关的js
$(function () {
   $(".btn_reload").click(function () {
       var url = $(this).data("url");
       $.dialog({
           title:"温馨提示",
           icon:"face-smile",
           content:"权限加载时间较长,是否更新权限?",
           ok:function () {
               $.post(url,function () {
                    $.dialog({
                        title:"温馨提示",
                        icon:"succeed",
                        content:"权限加载完成!",
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
//权限列表相关
$(function () {
    $("#select").click(function () {
        $(".all_permission option:selected").appendTo($(".selected_permission"));
    });
    $("#selectAll").click(function () {
        $(".all_permission option").appendTo($(".selected_permission"));
    });
    $("#deselect").click(function () {
        $(".selected_permission option:selected").appendTo($(".all_permission"));
    });
    $("#deselectAll").click(function () {
        $(".selected_permission option").appendTo($(".all_permission"));
    });
});
//菜单列表相关
$(function () {
    $("#mselect").click(function () {
        $(".all_menu option:selected").appendTo($(".selected_menu"));
    });
    $("#mselectAll").click(function () {
        $(".all_menu option").appendTo($(".selected_menu"));
    });
    $("#mdeselect").click(function () {
        $(".selected_menu option:selected").appendTo($(".all_menu"));
    });
    $("#mdeselectAll").click(function () {
        $(".selected_menu option").appendTo($(".all_menu"));
    });
});
//权限列表的去重
$(function () {
    //获取左列表的全部option
    var selectedOpts = $(".selected_permission option");
    //将其转换为一个数组(获取左列表的id数组)
    var ids = $.map(selectedOpts,function (item) {
        return $(item).val();
    });
    //遍历右列表
    $.each($(".all_permission option"),function (key,item) {
        //key是索引,value才是真正的值
        //获取右侧列表中option的value
        var id = $(item).val();
        //使用inArray进行比较
        if($.inArray(id,ids)>=0){
            $(item).remove();
        }
    });
});
//菜单列表的去重
$(function () {
    //获取左列表的全部option
    var selectedOpts = $(".selected_menu option");
    //将其转换为一个数组(获取左列表的id数组)
    var ids = $.map(selectedOpts,function (item) {
        return $(item).val();
    });
    //遍历右列表
    $.each($(".all_menu option"),function (key,item) {
        //key是索引,value才是真正的值
        //获取右侧列表中option的value
        var id = $(item).val();
        //使用inArray进行比较
        if($.inArray(id,ids)>=0){
            $(item).remove();
        }
    });
});
$(function () {
    //当表单提交的时候,先执行一个方法,让右侧表全选中
    $("#editForm").submit(function () {
        //让右列表(权限和菜单)中所有option的selected属性为true
        $(".selected_permission option").prop("selected",true);
        $(".selected_menu option").prop("selected",true);
    });
});