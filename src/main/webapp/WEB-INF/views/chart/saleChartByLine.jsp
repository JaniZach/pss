<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>销售报表线性图</title>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/highcharts/highcharts.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#container').highcharts({
                title: {
                    text: '销售报表',
                    x: -20 //center
                },
                subtitle: {
                    text: '按照<s:property value="#groupTypeName" escapeHtml="false"/>分组',
                    x: -20
                },
                xAxis: {
                    categories: <s:property value="#listX" escapeHtml="false"/>
                },
                yAxis: {
                    title: {
                        text: '销售总额(￥)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: '元'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '销售额',
                    data: <s:property value="#listY" escapeHtml="false"/>
                }]
            });
        });
    </script>
</head>
<body>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>
