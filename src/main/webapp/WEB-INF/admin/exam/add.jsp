<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/_common.jspf" %>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="../common/_meta.jspf" />
	</head>
	<body>
		<article class="page-container">
			<form class="form form-horizontal" id="form-add">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>考试名称：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" name="name" maxlength="20">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>考试时间：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" name="examTime" value="" class="input-text Wdate" 
						style="width:160px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd', startDate:'%y-%M-%d'})">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>满分分数：</label>
					<div class="formControls col-xs-8 col-sm-9 skin-minimal">
						<div class="radio-box">
							<input type="radio" id="fullMarks-1" value="100" name="fullMarks" checked>
							<label for="loanFlag-1">100分</label>
						</div>
						<div class="radio-box">
							<input type="radio" id="fullMarks-2" value="120" name="fullMarks">
							<label for="loanFlag-2">120分</label>
						</div>
						<div class="radio-box">
							<input type="radio" id="fullMarks-3" value="150" name="fullMarks">
							<label for="loanFlag-3">150分</label>
						</div>
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
			$(function() {
				$('.skin-minimal input').iCheck({
					checkboxClass : 'icheckbox-blue',
					radioClass : 'iradio-blue',
					increaseArea : '20%'
				});
				$("#form-add").validate({
					rules : {
						name : {
							required : true,
							minlength : 2,
							maxlength : 20
						},
						examTime : {
							required : true
						},
						fullMarks : {
							required : true
						}
					},
					onkeyup : false,
					focusCleanup : false,
					success : "valid",
					submitHandler : function(form) {
						$(form).ajaxSubmit({
							type : 'post',
							dataType : "json",
							url : "add.html" ,
							success : function(data) {
								if (data.status == 200) {
									layer.msg(data.msg, {icon:1, time:1000}, next);
		                        } else {
		                        	layer.msg(data.msg, {icon:2, time:2000});
			                    }
							},
			                error: function(data) {
								layer.msg('error!', {icon:2, time:2000});
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