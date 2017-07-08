<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/artdialog/jquery.artDialog.js?skin=opera"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-系统菜单管理</title>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<s:debug/>
<%@include file="/WEB-INF/views/common/common_dialog.jsp"%>
 <s:form id="searchForm" action="systemMenu" namespace="/" method="post">
     <%--防止点击翻页导致回到根目录,传递qo.parentId到后台--%>
     <s:hidden name="qo.parentId"/>
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_bottom">
                        <%--新增的时候,需要将qo.parentId传递过去,以用来回显--%>
                        <s:url namespace="/" action="systemMenu_input" var="inputUrl">
                            <s:param name="qo.parentId" value="qo.parentId"/>
                        </s:url>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:property value="#inputUrl"/>"/>
                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                               data-url="<s:url namespace="/" action="systemMenu_batchDelete"/>"/>
                    </div>
                </div>
            </div>
        </div>
        当前:<s:a namespace="/" action="systemMenu">根目录</s:a>
        <s:iterator value="#parentMenus">
            >
            <s:a namespace="/" action="systemMenu">
                <%--点击链接时,需要将qo.parentId传递到后台--%>
                <s:param name="qo.parentId" value="id"/>
                <s:property value="name"/>
            </s:a>
        </s:iterator>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all" /></th>
                        <th>编号</th>
                        <s:if test="qo.parentId<0">
                            <th>菜单编码</th>
                        </s:if>
                        <th>菜单名称</th>
                        <th>上级菜单</th>
                        <th>URL</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#result.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td>
                            <s:if test="qo.parentId<0">
                                <td><s:property value="sn"/> </td>
                            </s:if>
                            <td><s:property value="name"/> </td>
                            <td><s:property value="parentName"/></td>
                            <td><s:property value="url"/> </td>
                            <td>
                                <s:a namespace="" action="systemMenu_input">
                                    <s:param name="systemMenu.id" value="id"></s:param>
                                    <%--编辑的时候同样应将qo.parentId传递过去--%>
                                    <s:param name="qo.parentId" value="qo.parentId"></s:param>
                                    编辑</s:a>
                                <s:url namespace="" action="systemMenu_delete" var="deleteUrl">
                                    <s:param name="systemMenu.id" value="id"></s:param>
                                </s:url>
                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">
                                    删除
                                </a>
                                <%--查看子菜单操作,需要将当前id传过去--%>
                                <s:a namespace="/" action="systemMenu">
                                    查看子菜单
                                    <s:param name="qo.parentId" value="id"/>
                                </s:a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="/WEB-INF/views/common/common_page.jsp" %>
    </div>
</s:form>
</body>
</html>
