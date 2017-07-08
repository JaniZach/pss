<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>销售报表条状图</title>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/highcharts/highcharts.js"></script>
    <script type="text/javascript" src="/js/highcharts/highcharts-3d.js"></script>
    <script type="text/javascript">
        $(function () {
            var chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'container',
                    type: 'column',
                    margin: 75,
                    options3d: {
                        enabled: true,
                        alpha: 15,
                        beta: 15,
                        depth: 50,
                        viewDistance: 25
                    }
                },
                title: {
                    text: '销售报表'
                },
                subtitle: {
                    text: '按照<s:property value="#groupTypeName" escapeHtml="false"/>分组'
                },
                tooltip: {
                    valueSuffix: '元'
                },
                plotOptions: {
                    column: {
                        depth: 25
                    }
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
                series: [{
                    name: '销售额',
                    data: <s:property value="#listY" escapeHtml="false"/>
                }]
            });

            function showValues() {
                $('#R0-value').html(chart.options.chart.options3d.alpha);
                $('#R1-value').html(chart.options.chart.options3d.beta);
            }

            // Activate the sliders
            $('#R0').on('change', function () {
                chart.options.chart.options3d.alpha = this.value;
                showValues();
                chart.redraw(false);
            });
            $('#R1').on('change', function () {
                chart.options.chart.options3d.beta = this.value;
                showValues();
                chart.redraw(false);
            });

            showValues();
        });
    </script>
</head>
<body>
<div id="container"></div>
<div id="sliders">
    <table>
        <tr><td>Alpha Angle</td><td><input id="R0" type="range" min="0" max="45" value="15"/> <span id="R0-value" class="value"></span></td></tr>
        <tr><td>Beta Angle</td><td><input id="R1" type="range" min="0" max="45" value="15"/> <span id="R1-value" class="value"></span></td></tr>
    </table>
</div>
</body>
</html>
