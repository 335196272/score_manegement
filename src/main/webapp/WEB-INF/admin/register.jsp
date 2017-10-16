<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
		<meta charset="utf-8">
		<title>成绩管理系统 | 注册</title>
		<meta name="description" content="成绩管理">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="icon" href="/score/images/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="/score/images/favicon.ico" />
		<link rel="stylesheet" href="/score/css/login/reset.css" />
		<link rel="stylesheet" href="/score/css/login/supersized.css" />
		<link rel="stylesheet" href="/score/css/login/style.css" />
		<style>
			#vcode>img { cursor: pointer; margin-bottom: -15px; border-radius: 5px; }
		</style>
	</head>
	<body id="body">
		<div class="page-container" style="margin: 100px auto 0px;">
			<h1>Register</h1>
			<form id="_form" action="" method="post">
				<input type="text" name="userName" id="userName" onblur="checkUserName()" placeholder="用户名"> 
				<input type="text" name="phoneNumber" id="phoneNumber" onblur="checkPhoneNumber()" placeholder="手机号">
				<input type="text" name="email" id="email" onblur="checkEmail()" placeholder="邮箱"> 
				<input type="password" name="password" id="password" onblur="checkPassword()" placeholder="密码"> 
				<input type="password" id="confirmPassword" onblur="checkConfirmPassword()" placeholder="确认密码">
				<button type="button" class="register">注册</button>
				<button type="button" id="login">登录</button>
				<div class="error">
					<span>+</span>
				</div>
			</form>
		</div>
	
		<script src="/score/js/jquery1.9.1.min.js"></script>
		<script src="/score/js/login/supersized.3.2.7.min.js"></script>
		<script src="/score/js/login/supersized-init.js"></script>
		<script src="/score/lib/layer/layer.js"></script>
		<script>
			function checkUserName() {
				var userName = $("#userName").val();
				var validate = /^[\u0391-\uFFE5]+$/;
				if (userName.length < 2 || !validate.test(userName)) {
					layer.msg('用户名要求中文，且长度至少为两个字符！');
					$('userName').focus();
	    			return false;
				}
				$.ajax({
		        	url:"user/checkUserName.html",
		        	data:{
		        		userName: userName
		        	},
		        	type:"get",
		        	dataType:"json",
		        	success:function(result) {
			    		if (result && result.msg == "true") {
			    			layer.msg('用户名已经被注册！');
			    			$('userName').focus();
			    			return false;
			    		}
		        	},
		        	error:function(e) {
		        		layer.msg('网络异常！', new Function());
		        	}
		        });
				return true;
			}
			function checkPhoneNumber() {
				var phoneNumber = $("#phoneNumber").val();
				var validate = /^(((13[0-9]{1})|(15[0-35-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
				if (phoneNumber.length != 11 || !validate.test(phoneNumber)) {
					layer.msg('请输入正确格式的手机号码！');
					$('phoneNumber').focus();
	    			return false;
				}
				$.ajax({
		        	url:"user/checkPhoneNumber.html",
		        	data:{
		        		phoneNumber: phoneNumber
		        	},
		        	type:"get",
		        	dataType:"json",
		        	success:function(result) {
			    		if (result && result.msg == "true") {
			    			layer.msg('手机号已经被注册！');
			    			$('phoneNumber').focus();
			    			return false;
			    		}
		        	},
		        	error:function(e) {
		        		layer.msg('网络异常！', new Function());
		        	}
		        });
				return true;
			}
			function checkEmail() {
				var email = $("#email").val();
				var validate = /^[\w]+(\.[\w]+)*@[\w]+(\.[\w]+)+$/;
				if (!validate.test(email)) {
					layer.msg('请输入正确格式的电子邮箱！');
					$('phoneNumber').focus();
	    			return false;
				}
				$.ajax({
		        	url:"user/checkEmail.html",
		        	data:{
		        		email: email
		        	},
		        	type:"get",
		        	dataType:"json",
		        	success:function(result) {
			    		if (result && result.msg == "true") {
			    			layer.msg('该电子邮箱已经被注册！');
			    			$('email').focus();
			    			return false;
			    		}
		        	},
		        	error:function(e) {
		        		layer.msg('网络异常！', new Function());
		        	}
		        });
				return true;
			}
			function checkPassword() {
				var password = $("#password").val();
				if (password.length < 6) {
					layer.msg('密码长度不小于6位字符！');
					$('password').focus();
	    			return false;
				}
				return true;
			}
			function checkConfirmPassword() {
				var password = $("#password").val();
				var confirmPassword = $("#confirmPassword").val();
				if (password != confirmPassword) {
					layer.msg('两次密码输出不一样！');
					$('confirmPassword').focus();
	    			return false;
				}
				return true;
			}
			jQuery(document).ready(
				function() {
					$('.register').click(
						function() {
							var a = checkUserName();
							var b = checkPhoneNumber();
							var c = checkEmail();
							var d = checkPassword();
							var e = checkConfirmPassword();
							if (!a || !b || !c || !d || !e) {
								return;
							}
							var load = layer.load();
							$.post("user/register.html", $("#_form").serialize(), function(result) {
								layer.close(load);
								if (result && result.status != 200) {
									return layer.msg(result.message, function() { }), !1;
								} else {
									layer.msg('注册成功！');
									window.location.href = "index.html" || "/";
								}
							}, "json");
						});
					$("#login").click(function() {
						window.location.href = "login.html";
					});
				});
		</script>
	</body>
</html>