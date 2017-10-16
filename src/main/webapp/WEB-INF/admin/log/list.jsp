﻿<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/_common.jspf" %>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="../common/_meta.jspf" />
		<title>系统日志</title>
	</head>
	<body>
		<nav class="breadcrumb"><i class="Hui-iconfont Hui-iconfont-home2"></i> 主页 
			<span class="c-gray en">&gt;</span> 系统管理 
			<span class="c-gray en">&gt;</span> 系统日志 
			<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
				<i class="Hui-iconfont Hui-iconfont-huanyipi"></i>
			</a>
		</nav>
		<div class="page-container">
			<form id="searchForm" name="searchForm" action="${ROOT}/admin/log/list.html" method="get">
				<div class="text-c">
					<input type="text" name="name" id="name" value="${param.name}" placeholder=" 操作用户" style="width:150px" class="input-text">
					<span class="select-box inline">
						<select name="action" id="action" class="select">
							<option value="" ${empty param.action ? 'selected' : ''}>全部操作</option>
                            <option value="创建内容" ${param.action == '创建内容' ? 'selected' : ''}>创建内容</option>
                            <option value="修改内容" ${param.action == '修改内容' ? 'selected' : ''}>修改内容</option>
                            <option value="更新状态" ${param.action == '更新状态' ? 'selected' : ''}>更新状态</option>
                            <option value="删除内容" ${param.action == '删除内容' ? 'selected' : ''}>删除内容</option>
                            <option value="物理删除" ${param.action == '物理删除' ? 'selected' : ''}>物理删除</option>
                            <option value="用户登录" ${param.action == '用户登录' ? 'selected' : ''}>用户登录</option>
                            <option value="定时任务" ${param.action == '定时任务' ? 'selected' : ''}>定时任务</option>
                            <option value="接口调用" ${param.action == '接口调用' ? 'selected' : ''}>接口调用</option>
						</select>
					</span>
					<input type="text" name="detail" id="detail" value="${param.detail}" placeholder=" 内容关键字" style="width:200px" class="input-text"> 
					操作时间：
					<input type="text" name="ge_createDate" id="ge_createDate" value="${param.ge_createDate}" class="input-text Wdate" 
						style="width:160px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', startDate:'%y-%M-%d 00:00:00'})">
					-
					<input type="text" name="le_createDate" id="le_createDate" value="${param.le_createDate}" class="input-text Wdate" 
						style="width:160px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', startDate:'%y-%M-%d 23:59:59'})">
					<button id="search" class="btn btn-success" type="submit">
						<i class="Hui-iconfont Hui-iconfont-search2"></i> 查询
					</button>
				</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="r">共有数据：<strong>${total}</strong> 条，每页显示：<strong>${numPerPage}</strong> 条</span>
			</div>
			<div class="mt-10">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="80">用户</th>
							<th width="100">操作</th>
							<th width="300">内容</th>
							<th width="150">IP</th>
							<th width="150">操作时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="entity" items="${list}">
							<tr class="text-c">
								<td>${entity.name}</td>
			                    <td>${entity.action}</td>
			                    <td>${myfn:subStrReplace(entity.detail, 60, '...')}</td>
			                    <td>${entity.ip}</td>
			                    <td><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
				var action = $("#action").val();
				var detail = $("#detail").val();
				var ge_createDate = $("#ge_createDate").val();
				var le_createDate = $("#le_createDate").val();
				laypage({  
				  	cont: 'pageTag', //分页容器的id  
				  	pages: pages,    //总页数  
				  	curr: currpage,  //当前页  
				  	skip: true,      //是否开启跳页
				  	skin: '#00AA91', //当前页的颜色
				  	jump: function(e, first) {
				      	if (!first) {
				        	location.href = '?pageNum=' + e.curr + '&numPerPage=' + numPerPage
				        			+ '&name=' + name + '&action=' + action + '&detail=' + detail
				        			+ '&ge_createDate=' + ge_createDate + '&le_createDate=' + le_createDate;
				      	}  
				  	}  
				});
			}   
		</script>
	</body>
</html>