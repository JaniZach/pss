//员工相关的js
    //角色列表的移动
$(function () {
    $("#select").click(function () {
        $(".all_role option:selected").appendTo($(".selected_role"));
    });
    $("#selectAll").click(function () {
        $(".all_role option").appendTo($(".selected_role"));
    });
    $("#deselect").click(function () {
        $(".selected_role option:selected").appendTo($(".all_role"));
    });
    $("#deselectAll").click(function () {
        $(".selected_role option").appendTo($(".all_role"));
    });
});
    //角色列表的去重
$(function () {
    //获取左列表的全部option
    var selectedOpts = $(".selected_role option");
    //将其转换为一个数组(获取左列表的id数组)
    var ids = $.map(selectedOpts,function (item) {
        return $(item).val();
    });
    //遍历右列表
    $.each($(".all_role option"),function (key,item) {
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
        //让右列表中所有option的selected属性为true
        $(".selected_role option").prop("selected",true);
    });
});