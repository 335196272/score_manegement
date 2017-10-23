<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/_common.jspf" %>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="../common/_meta.jspf" />
	</head>
	<body>
		<article class="page-container">
			<form class="form form-horizontal" id="form-import">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>班级：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<select name="classesId" id="classesId" class="select">
							<option value="0" ${param.classesId == '0' ? 'selected' : ''}>选择班级</option>
							<c:forEach var="classes" items="${classesList}">
								<option value="${classes.classesId}" ${param.classesId == classes.classesId ? 'selected' : ''}>${classes.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>考试：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<select name="examId" id="examId" class="select">
							<option value="0" ${param.examId == '0' ? 'selected' : ''}>选择考试</option>
							<c:forEach var="exam" items="${examList}">
								<option value="${exam.examId}" ${param.examId == exam.examId ? 'selected' : ''}>${exam.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">学生成绩：</label>
					<div class="formControls col-xs-8 col-sm-9"> 
						<input type="file" name="scoreFile" id="scoreFile"/>
					</div>
				</div>
				
				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
						<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
					</div>
				</div>
			</form>
		</article>
	
		<jsp:include page="../common/_footer.jspf" />
		
		<script type="text/javascript">
			// 校验并提交表单
			$(function() {
				$("#form-import").validate({
					rules : {
						classesId : {
							required : true
						},
						examId : {
							required : true
						},
						scoreFile : {
							required : true
						},
					},
					onkeyup : false,
					focusCleanup : false,
					success : "valid",
					submitHandler : function(form) {
						$(form).ajaxSubmit({
							type : 'POST',
							dataType : "json",
							url : "importFile.html" ,
							success : function(data) {
								if (data.status == 200) {
									layer.msg(data.msg, {icon:1, time:3000}, next);
								} else {
									layer.msg(data.msg, {icon:2, time:2000});
								}
							},
			                error: function(data) {
								layer.msg('系统发生错误!', {icon:2, time:2000});
							}
						});
						function next() {
							var index = parent.layer.getFrameIndex(window.name);
							parent.location.reload();
							parent.layer.close(index);
						}
					}
				});
			});
		</script> 
	</body>
</html>