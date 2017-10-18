﻿﻿<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/_common.jspf" %>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="../common/_meta.jspf" />
		<title>科目管理</title>
	</head>
	<body>
		<nav class="breadcrumb"><i class="Hui-iconfont Hui-iconfont-home2"></i> 主页 
			<span class="c-gray en">&gt;</span> 科目管理
			<a class="btn btn-refresh radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
				<i class="Hui-iconfont Hui-iconfont-huanyipi"></i>
			</a>
		</nav>
		<div class="page-container">
			<form id="searchForm" name="searchForm" action="${ROOT}/admin/subject/list.html" method="get">
				<div class="text-c">
					<input type="text" name="name" id="name" value="${param.name}" placeholder=" 科目名称关键词" style="width:150px" class="input-text">
					
					<button id="search" class="btn btn-success" type="submit">
						<i class="Hui-iconfont Hui-iconfont-search2"></i> 查询
					</button>
				</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<a class="btn btn-primary radius" href="javascript:;" onclick="add_subject()">
						<i class="Hui-iconfont Hui-iconfont-add"></i> 添加科目
					</a>
				</span>
				<span class="r">共有数据：<strong>${total}</strong> 条，每页显示：<strong>${numPerPage}</strong> 条</span>
			</div>
			<div class="mt-10">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="100">科目ID</th>
							<th width="300">科目名称</th>
							<th width="100">排序</th>
							<th width="100">修改人</th>
							<th width="150">修改时间</th>
							<th width="150">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="entity" items="${list}">
							<tr class="text-c">
								<td>${entity.subjectId}</td>
			                    <td>${entity.name}</td>
			                    <td>${entity.seq}</td>
			                    <td>${entity.updateName}</td>
			                    <td><fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                    <td class="f-16 bk-blue">
			                    	<a title="修改" class="ml-5" href="javascript:;" onclick="edit_subject(${entity.subjectId})">
			                    		<i class="Hui-iconfont Hui-iconfont-edit"></i>
			                    	</a> 
			                    	<a title="删除" class="ml-5" href="javascript:;" onclick="del_subject(this, ${entity.subjectId})">
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
				var name = $("#name").val();
				laypage({
				  	cont: 'pageTag', //分页容器的id  
				  	pages: pages,    //总页数  
				  	curr: currpage,  //当前页  
				  	skip: true,      //是否开启跳页
				  	skin: '#00AA91', //当前页的颜色
				  	jump: function(e, first) {
				      	if (!first) {
				        	location.href = '?pageNum=' + e.curr + '&numPerPage=' + numPerPage
				        			+ '&name=' + name;
				      	}
				  	}  
				});
			} 
			// 添加
			function add_subject() {
				layer.open({
			      	type: 2,
					title: '添加科目',
					maxmin: true,
					area: ['450px', '400px'],
					content: 'toAdd.html'
				});
			}
			// 修改
			function edit_subject(id) {
				layer.open({
			      	type: 2,
					title: '修改科目',
					maxmin: true,
					area: ['450px', '400px'],
					content: 'toModify.html?subjectId=' + id
				});
			}
			//删除
			function del_subject(obj, id) {
				layer.confirm('确认要删除吗？', function(index) {
					$.ajax({
						type: 'GET',
						url: 'delete.html?subjectId=' + id,
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