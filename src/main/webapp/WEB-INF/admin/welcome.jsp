<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/_common.jspf" %>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="common/_meta.jspf" />
	</head>
	<body>
		<jsp:include page="common/_footer.jspf" />
		<div class="page-container">
			<p class="f-20 text-success">欢迎登录系统</p>
			<p>
				最近一次考试为：${exam.name} &nbsp;&nbsp; 
				考试时间为：<fmt:formatDate value="${exam.examTime}" pattern="yyyy-MM-dd"/>
			</p>
			<table class="table table-border table-bordered table-bg">
				<thead>
					<tr>
						<th colspan="7" scope="col">考试信息统计：</th>
					</tr>
					<tr class="text-c">
						<th>统计</th>
						<th>前50名人数</th>
						<th>前250名人数</th>
						<th>前500名人数</th>
						<th>前750名人数</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="scoreRankVo" items="${scoreRankVoList}">
						<tr class="text-c">
							<td>${scoreRankVo.classesName}</td>
							<td>${scoreRankVo.rankList[0]}</td>
							<td>${scoreRankVo.rankList[1]}</td>
							<td>${scoreRankVo.rankList[2]}</td>
							<td>${scoreRankVo.rankList[3]}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="f-14 c-error">
				注：当前图表用于描绘最近一次考试中，各个班级、各分数段人数所占比例。
			</div>
			<div id="container" style="min-width: 700px; height: 400px"></div>
		</div>
		<script type="text/javascript">
			// 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('container'));
	        myChart.showLoading({text: 'Loding...'});
	     	// 指定图表的配置项和数据
	        var option = {
        	    tooltip : {
        	        trigger: 'axis',
        	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        	        }
        	    },
        	    legend: {
        	        data: ['前50名人数', '前250名人数','前500名人数','前750名人数']
        	    },
        	    grid: {
        	        left: '3%',
        	        right: '4%',
        	        bottom: '3%',
        	        containLabel: true
        	    },
        	    xAxis:  {
        	        type: 'value'
        	    },
        	    yAxis: {
        	        type: 'category',
        	        data: ${scoreRankChartVo.yAxis}
        	    },
        	    series: [
        	        {
        	            name: '前50名人数',
        	            type: 'bar',
        	            stack: '总量',
        	            label: {
        	                normal: {
        	                    show: true,
        	                    position: 'insideRight'
        	                }
        	            },
        	            data: ${scoreRankChartVo.data50}
        	        },
        	        {
        	            name: '前250名人数',
        	            type: 'bar',
        	            stack: '总量',
        	            label: {
        	                normal: {
        	                    show: true,
        	                    position: 'insideRight'
        	                }
        	            },
        	            data: ${scoreRankChartVo.data250}
        	        },
        	        {
        	            name: '前500名人数',
        	            type: 'bar',
        	            stack: '总量',
        	            label: {
        	                normal: {
        	                    show: true,
        	                    position: 'insideRight'
        	                }
        	            },
        	            data: ${scoreRankChartVo.data500}
        	        },
        	        {
        	            name: '前750名人数',
        	            type: 'bar',
        	            stack: '总量',
        	            label: {
        	                normal: {
        	                    show: true,
        	                    position: 'insideRight'
        	                }
        	            },
        	            data: ${scoreRankChartVo.data750}
        	        }
        	    ]
        	};
	        myChart.setOption(option);
	     	// 使用刚指定的配置项和数据显示图表。
	        myChart.hideLoading();
		</script>
	</body>
</html>