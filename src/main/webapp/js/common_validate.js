//input页面校验相关js
    //页面加载完成后
$(function () {
    $("#editForm").validate({
        rules:{
            'e.name':{
                required:true,
                minlength:2
            },
            'e.password':{
                required:true,
                minlength:4
            },
            repassword:{equalTo:"#password"},
            'e.email':{email:true},
            'e.age':{
                range:[16,60],
                digits:true
            }
        },
        messages:{
            'e.name':{
                required:"用户名不能为空",
                minlength:"用户名长度必须大于2"
            },
            'e.password':{
                required:"必须输入密码",
                minlength:"密码长度必须大于4"
            },
            repassword:"两次输入的密码必须相同",
            'e.email':"非法的邮箱格式",
            'e.age':{
                range:"年龄必须介于{0}与{1}之间",
                digits:"年龄必须为整数"
            }
        }
    });
});
