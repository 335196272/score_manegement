﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/_common.jspf" %>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="../common/_meta.jspf" />
		<title>成绩管理</title>
	</head>
	<body>
		<nav class="breadcrumb"><i class="Hui-iconfont Hui-iconfont-home2"></i> 主页 
			<span class="c-gray en">&gt;</span> 成绩管理
			<a class="btn btn-refresh radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
				<i class="Hui-iconfont Hui-iconfont-huanyipi"></i>
			</a>
		</nav>
		<div class="page-container">
			<form id="searchForm" name="searchForm" action="${ROOT}/admin/score/list.html" method="get">
				<div class="text-c">
					<input type="text" name="studentName" id="studentName" value="${param.studentName}" placeholder=" 学生姓名" style="width:150px" class="input-text"> &nbsp;
					<span class="select-box inline">
						<select name="classesId" id="classesId" class="select">
							<option value="0" ${param.classesId == '0' ? 'selected' : ''}>班级</option>
							<c:forEach var="classes" items="${classesList}">
								<option value="${classes.classesId}" ${param.classesId == classes.classesId ? 'selected' : ''}>${classes.name}</option>
							</c:forEach>
						</select>
					</span> &nbsp;
					<span class="select-box inline">
						<select name="examId" id="examId" class="select">
							<c:forEach var="exam" items="${examList}">
								<option value="${exam.examId}" ${param.examId == exam.examId ? 'selected' : ''}>${exam.name}</option>
							</c:forEach>
						</select>
					</span>
					
					<button id="search" class="btn btn-success" type="submit">
						<i class="Hui-iconfont Hui-iconfont-search2"></i> 查询
					</button>
				</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<a class="btn btn-primary radius" href="javascript:;" onclick="import_score()">
						<i class="Hui-iconfont Hui-iconfont-add"></i> 导入成绩
					</a>
				</span>
				<span class="r">共有数据：<strong>${total}</strong> 条，每页显示：<strong>${numPerPage}</strong> 条</span>
			</div>
			<div class="mt-10">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="150">班级</th>
							<th width="200">考试</th>
							<th width="80">座号</th>
							<th width="100">姓名</th>
							<th width="100">总分</th>
							<th width="80">名次</th>
							<th width="100">更新者</th>
							<th width="150">更新时间</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="entity" varStatus="idx" items="${list}">
							<c:if test="${idx.index == 0}">
								<c:set var="color" value="success" />
							</c:if>
							<c:if test="${idx.index > 0}">
								<c:choose>
									<c:when test="${entity.classesId == list[idx.index-1].classesId}">
										<c:choose>
											<c:when test="${color=='success'}">
												<c:set var="color" value="success" />
											</c:when>
											<c:otherwise>
												<c:set var="color" value="danger" />
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${color=='success'}">
												<c:set var="color" value="danger" />
											</c:when>
											<c:otherwise>
												<c:set var="color" value="success" />
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:if>
							<tr class="text-c ${color}">
								<td>${entity.classesName}</td>
								<td>${entity.examName}</td>
								<td>${entity.studentNo}</td>
								<td>${entity.studentName}</td>
			                    <td>
			                    	${entity.score.unscaledValue() == 0 ? '<font color="red">缺考</font>' : entity.score}
			                    </td>
			                    <td>${entity.rank}</td>
			                    <td>${entity.updateName}</td>
			                    <td><fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                    <td class="f-16 bk-blue">
			                    	<a title="修改" class="ml-5" href="javascript:;" onclick="edit_score(${entity.scoreId})">
			                    		<i class="Hui-iconfont Hui-iconfont-edit"></i>
			                    	</a> 
			                    	<a title="删除" class="ml-5" href="javascript:;" onclick="del_score(this, ${entity.scoreId})">
			                    		<i class="Hui-iconfont Hui-iconfont-del3"></i>
			                    	</a>
			                    </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<c:if test="${total > 0}">
				<div class="mt-10" id="pageTag" style="text-align:center;"></div>
			</c:if>
		</div>
		
		<jsp:include page="../common/_footer.jspf" />
		<script type="text/javascript">
			// 分页
			var totalNum = ${total};
			if (totalNum > 0) {
				var numPerPage = ${numPerPage};
				var pages = Math.ceil((totalNum / numPerPage)); 
				var currpage = ${pageNum};
				var studentName = $("#studentName").val();
				var classesId = $("#classesId").val();
				var examId = $("#examId").val();
				laypage({
				  	cont: 'pageTag', //分页容器的id  
				  	pages: pages,    //总页数  
				  	curr: currpage,  //当前页  
				  	skip: true,      //是否开启跳页
				  	skin: '#00AA91', //当前页的颜色
				  	jump: function(e, first) {
				      	if (!first) {
				        	location.href = '?pageNum=' + e.curr + '&numPerPage=' + numPerPage
				        			+ '&studentName=' + studentName + '&classesId=' + classesId
				        			+ '&examId=' + examId;
				      	}
				  	}  
				});
			} 
			// 导入
			function import_score() {
				layer.open({
			      	type: 2,
					title: '添加成绩',
					maxmin: true,
					area: ['450px', '400px'],
					content: 'toImport.html'
				});
			}
			// 修改
			function edit_score(id) {
				layer.open({
			      	type: 2,
					title: '修改成绩',
					maxmin: true,
					area: ['450px', '400px'],
					content: 'toModify.html?scoreId=' + id
				});
			}
			//删除
			function del_score(obj, id) {
				layer.confirm('确认要删除吗？', function(index) {
					$.ajax({
						type: 'GET',
						url: 'delete.html?scoreId=' + id,
						dataType: 'json',
						success: function(data) {
							if (data.status == 200) {
								$(obj).parents("tr").remove();
								layer.msg(data.msg, {icon:1, time:1000});
							} else {
	                        	layer.msg(data.msg, {icon:2, time:2000});
		                    }
						},
						error:function(data) {
							layer.msg(data.msg, {icon:2, time:2000});
						},
					});		
				});
			}
		</script>
	</body>
</html>