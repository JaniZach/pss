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
    <script type="text/javascript" src="/js/artdialog/iframeTools.js"></script>
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/system/chart.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-销售报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<s:debug/>
<%@include file="/WEB-INF/views/common/common_dialog.jsp" %>
<s:form id="searchForm" action="chart_saleChart" namespace="/" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="saleQo.beginDate" format="yyyy-MM-dd" var="_beginDate"/>
                        <s:textfield name="saleQo.beginDate" class="ui_input_txt02" value="%{#_beginDate}"/>
                        ~
                        <s:date name="saleQo.endDate" format="yyyy-MM-dd" var="_endDate"/>
                        <s:textfield name="saleQo.endDate" class="ui_input_txt02" value="%{#_endDate}"/>
                        货品
                        <s:textfield name="saleQo.keyword" class="ui_input_txt02"
                        placeholder="货品名称"/>
                        客户
                        <s:select list="#clients" listKey="id" listValue="name" name="saleQo.clientId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                        品牌
                        <s:select list="#brands" listKey="id" listValue="name" name="saleQo.brandId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                        图表
                        <s:select list="#{'line':'线性图','pie':'饼状图','column':'条形图'}" class="ui_select01 btn_chart"/>
                        分组
                        <s:select list="#groupTypes" name="saleQo.groupType" class="ui_select01"/>
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
                        <th>销售总数量</th>
                        <th>销售总金额</th>
                        <th>毛利润</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#list">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td><s:property value="groupType"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="grossProfit"/></td>
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
