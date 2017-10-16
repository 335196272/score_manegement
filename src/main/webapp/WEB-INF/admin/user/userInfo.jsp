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
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input class="input-text" type="text" name="userName" value="${entity.userName}" maxlength="10">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机号：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input class="input-text" type="text" name="phoneNumber" value="${entity.phoneNumber}" maxlength="11">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>电子邮箱：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input class="input-text" type="text" name="email" value="${entity.email}">
					</div>
				</div>
				<div class="row cl">
				    <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户头像：</label>
				    <div class="formControls col-xs-8 col-sm-9">
				     	<input type="hidden" name="picUrl" id="picUrl"/>
				     	<span class="btn-upload">
						  	<a href="javascript:void();" class="btn btn-primary radius">
						  		<i class="Hui-iconfont">&#xe642;</i> 浏览文件
						  	</a>
						  	<input class="input-file" type="file" multiple onchange="setImg(this);">
						</span>
				     	<img id="picShow" src="${entity.picUrl}" width="45" height="45"/>
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
			//用于进行图片上传，返回地址
			function setImg(obj) {
	            var f = $(obj).val();
	            if (f == null || f == undefined || f == '') {
	                return false;
	            }
	            if (!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f)) {
	            	layer.msg('类型必须是图片！(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)', {icon:2, time:2000});
	                $(obj).val('');
	                return false;
	            }
	            if (obj.files[0].size > 1024000) {
	            	layer.msg('上传图片大小不能超过1M！', {icon:2, time:2000});
	            	return false;
	            }
	            var data = new FormData();
	            $.each($(obj)[0].files, function(i, file) {
	                data.append('file', file);
	            });
	            $.ajax({
	                type: "POST",
	                url: "uploadImg.html",
	                data: data,
	                cache: false,
	                contentType: false,
	                processData: false,
	                dataType:"json",
	                success: function(data) {
	                    if (data.status == 200) {
                            $("#picUrl").val(data.msg); // 将地址存储好
                            $("#picShow").attr("src", data.msg); // 显示图片  
                        } else {
                        	layer.msg(data.msg, {icon:2, time:2000});
                        	$("#url").val("");
                        	$(obj).val('');
	                    }
	                },
	                error: function(XMLHttpRequest, textStatus, errorThrown) {
	                	layer.msg("上传失败，请检查网络后重试！", {icon:2, time:2000});
	                    $("#url").val("");
	                    $(obj).val('');
	                }
	            });
	        }
			// 校验并提交表单
			$(function() {
				$("#form-add").validate({
					rules : {
						userName : {
							required : true,
							minlength : 2,
							maxlength : 10
						},
						phoneNumber : {
							required : true,
							isMobile : true
						},
						email : {
							required : true,
							email: true
						},
					},
					onkeyup : false,
					focusCleanup : false,
					success : "valid",
					submitHandler : function(form) {
						$(form).ajaxSubmit({
							type : 'POST',
							dataType : "json",
							url : "modifyUserInfo.html" ,
							success : function(data) {
								layer.msg(data.msg, {icon:1, time:1000}, next);
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