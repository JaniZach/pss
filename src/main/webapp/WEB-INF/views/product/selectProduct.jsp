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
    <%--导入图片弹出层的框架--%>
    <script type="text/javascript" src="/js/fancyBox/jquery.fancybox.pack.js"></script>
    <link rel="stylesheet" type="text/css" href="/js/fancyBox/jquery.fancybox.css" media="screen"/>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/system/selectProduct.js"></script>
    <script type="text/javascript">
        $(function () {
            $('.fancybox').fancybox();
        });
    </script>
    <title>PSS-商品选择页面</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<s:debug/>
<%@include file="/WEB-INF/views/common/common_dialog.jsp" %>
<s:form id="searchForm" action="product_selectProduct" namespace="/" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        编码/名称
                        <s:textfield name="qo.keyword" class="ui_input_txt02"/>
                        品牌
                        <s:select list="#brands" listKey="id" listValue="name" name="qo.brandId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>货品图片</th>
                        <th>货品名称</th>
                        <th>货品编码</th>
                        <th>货品品牌</th>
                        <th>成本价格</th>
                        <th>销售价格</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#result.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"
                                       data-id="<s:property value="id"/>"/></td>
                            <td>
                                <a class="fancybox" href="<s:property value="imagePath"/>">
                                    <img src="<s:property value="smallImagePath"/>" width="80">
                                </a>
                            </td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="brand.name"/></td>
                            <td><s:property value="costPrice"/></td>
                            <td><s:property value="salePrice"/></td>
                            <td>
                                <input type="button" value="选择该商品" class="ui_input_btn01 btn_select"
                                data-product="<s:property value="productJson"/>">
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
