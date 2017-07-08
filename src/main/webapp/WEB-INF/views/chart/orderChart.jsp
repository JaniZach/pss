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
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/system/chart.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-订货报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<s:debug/>
<%@include file="/WEB-INF/views/common/common_dialog.jsp" %>
<s:form id="searchForm" action="chart_orderChart" namespace="/" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="orderQo.beginDate" format="yyyy-MM-dd" var="_beginDate"/>
                        <s:textfield name="orderQo.beginDate" class="ui_input_txt02" value="%{#_beginDate}"/>
                        ~
                        <s:date name="orderQo.endDate" format="yyyy-MM-dd" var="_endDate"/>
                        <s:textfield name="orderQo.endDate" class="ui_input_txt02" value="%{#_endDate}"/>
                        货品
                        <s:textfield name="orderQo.keyword" class="ui_input_txt02"
                        placeholder="货品名称"/>
                        供应商
                        <s:select list="#suppliers" listKey="id" listValue="name" name="orderQo.supplierId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                        品牌
                        <s:select list="#brands" listKey="id" listValue="name" name="orderQo.brandId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                        分组
                        <s:select list="#groupTypes" name="orderQo.groupType" class="ui_select01"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>分组类型</th>
                        <th>采购总数量</th>
                        <th>采购总金额</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#list">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td><s:property value="groupType"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</s:form>
</body>
</html>
