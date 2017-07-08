//主页面相关的js
//设置跳转
$(function () {
    //设置按钮点击
    $("#TabPage2 li").click(function () {

        $.each($("#TabPage2 li"), function (index, item) {
            //将全部li的样式移除
            $(item).removeClass("selected");
            //将全部li中图片还原
            $(item).find("img").prop("src", "/images/common/" + (index + 1) + ".jpg");
        });
        //设置点击的li的样式
        $(this).addClass("selected");
        //修改点击的li中图片
        $(this).find("img").prop("src", "/images/common/" + ($(this).index() + 1) + "_hover.jpg");

        //修改菜单标题
        $("#nav_module img").prop("src", "/images/common/module_" + ($(this).index() + 1) + ".png");

        //点击时候,跳转到指定的模块
        var sn = $(this).data("rootmenu");
        loadMenus(sn);
    });
});
//zTree的设置
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    async: {
        //开启异步加载节点数据
        enable: true,
        //指定url,并将sn的值用qo.parentSn传递到后台查询
        url: "/systemMenu_queryMenusByParentSn.action",
        autoParam: ["sn=qo.parentSn"]
    },
    callback: {
        onClick: function (event, treeId, treeNode) {
            //event指的是事件对象
            //tree指的是其Id,指的是绑定zTree对象的id
            //treeNode表示点击的节点,包含节点的全部信息,id,pid,name,等等
            if (treeNode.action) {
                var action = treeNode.action;
                //实现刷新页面
                $("#rightMain").prop("src", "/" + action + ".action");
                //设置当前位置的层级菜单(目前是二级目录,所以使用treeNode获取父节点及其自身)
                $("#here_area").html("当前位置：" + treeNode.getParentNode().name + "&nbsp;>&nbsp;" + treeNode.name);
            }
        }
    }
};
//业务模块
var zNode1 = [
    //设置父目录,isParent:true
    {id: 1, pId: 0, name: "业务模块", isParent: true, open: true, sn: "business"}
];
//系统模块
var zNode2 = [
    {id: 2, pId: 0, name: "系统管理模块", isParent: true, open: true, sn: "system"}
];
//报表模块
var zNode3 = [
    {id: 3, pId: 0, name: "其他", isParent: true, open: true, sn: "chart"}
];
var zNodes = {
    "business": zNode1,
    "systemManage": zNode2,
    "charts": zNode3
};
//用于显示系统菜单的函数
function loadMenus(nodeSn) {
    $.fn.zTree.init($("#dleft_tab1"), setting, zNodes[nodeSn]);
}
$(function () {
    //初始化的时候显示业务模块
    loadMenus("business");
});
// -----------------------------------------------------------------------------
/*$(function () {
   $("#show_hide_btn").click(function () {
       $("#left_menu_cnt").toggle(5000);
   });
});*/
$(function () {
    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function () {
        switchSysBar();
    });
});
/**
 * 隐藏或者显示侧边栏
 *
 */
function switchSysBar(flag) {
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if (flag == true) { // flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({
            width: '280px'
        });
        $('#top_nav').css({
            width: '77%',
            left: '304px'
        });
        $('#main').css({
            left: '280px'
        });
    } else {
        if (left_menu_cnt.is(":visible")) {
            left_menu_cnt.hide(10, 'linear');
            side.css({
                width: '60px'
            });
            $('#top_nav').css({
                width: '100%',
                left: '60px',
                'padding-left': '28px'
            });
            $('#main').css({
                left: '60px'
            });
            $("#show_hide_btn").find('img').attr('src',
                '/images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({
                width: '280px'
            });
            $('#top_nav').css({
                width: '77%',
                left: '304px',
                'padding-left': '0px'
            });
            $('#main').css({
                left: '280px'
            });
            $("#show_hide_btn").find('img').attr('src',
                '/images/common/nav_hide.png');
        }
    }
}
$(function () {
    // 加载日期
    setInterval(function(){
        var time = new Date();
        var myYear = time.getFullYear();
        var myMonth = time.getMonth() + 1;
        var myDay = time.getDate();
        var week = time.getDay();
        var hours = time.getHours();
        var minutes = time.getMinutes();
        var seconds = time.getSeconds();
        $("#day_day").html(myYear + "年" + myMonth + "月" + myDay + "日"
            +"周" + week+"&nbsp;"+ hours + "时" + minutes + "分" + seconds + "秒")
    }, 1000);
});