<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    <s:if test="hasActionMessages()">
    $(function () {
        $.dialog({
            title:"温馨提示",
            icon:"face-smile",
            content:"<s:property value="actionMessages[0]"/>",
            ok:true
        });
    });
    </s:if>
</script>
<script type="text/javascript">
    <s:if test="hasActionErrors()">
    $(function () {
        $.dialog({
            title:"温馨提示",
            icon:"face-smile",
            content:"<s:property value="actionErrors[0]"/>",
            ok:true
        });
    });
    </s:if>
</script>
<%--<script>
    <s:if test="hasActionErrors()">
    $(function () {
        $.dialog({
            title:"温馨提示",
            content:"<s:property value="actionErrors[0]"/>",
            ok:true
        });
    });
    </s:if>
</script>--%>
<style>
    .alt td {
        background: black !important;
    }
</style>