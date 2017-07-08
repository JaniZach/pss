//login相关的js
$(function () {
    $("#login_sub").click(function () {
        $("#submitForm").submit();
    });
    $("login_ret").click(function () {
        $("#submitForm").resetForm();
    });
});