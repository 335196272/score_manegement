﻿<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/_common.jspf" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>学生成绩管理系统</title>
		<meta name="keywords" content="">
		<meta name="description" content="">
		<link rel="Bookmark" href="/score/images/favicon.ico" >
		<link rel="Shortcut Icon" href="/score/images/favicon.ico" />
		<jsp:include page="common/_meta.jspf" />
	</head>
	<body>
		<%-- 顶部模块 --%>
		<header class="navbar-wrapper">
			<div class="navbar navbar-fixed-top">
				<div class="container-fluid cl"> 
					<a class="logo navbar-logo f-l mr-10 hidden-xs" href="#">学生成绩管理系统</a> 
					<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">
						<i class="Hui-iconfont Hui-iconfont-menu"></i>
					</a>
					<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
						<ul class="cl">
							<li><img alt="用户头像" height="42" width="42" src="${currentUser.picUrl}"></li>
							<li class="dropDown dropDown_hover"> 
								<a href="#" class="dropDown_A">
									${currentUser.userName}
									<i class="Hui-iconfont Hui-iconfont-arrow2-bottom"></i>
								</a>
								<ul class="dropDown-menu menu radius box-shadow">
									<li><a href="javascript:;" onclick="modifyPassword()">修改密码</a></li>
									<li><a href="javascript:;" onclick="userInfo()">个人信息</a></li>
									<li><a href="user/logout.html">退出</a></li>
								</ul>
							</li>
							<li id="Hui-skin" class="dropDown right dropDown_hover"> 
								<a href="javascript:;" class="dropDown_A" title="换肤">
									<i class="Hui-iconfont Hui-iconfont-pifu" style="font-size:18px"></i>
								</a>
								<ul class="dropDown-menu menu radius box-shadow">
									<li><a href="javascript:;" data-val="default" title="默认（绿色）">默认（绿色）</a></li>
									<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
									<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
									<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
									<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
								</ul>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</header>
		
		<%-- 菜单模块 --%>
		<aside class="Hui-aside">
			<div class="menu_dropdown bk_2">
				<dl id="menu-home">
					<dt>
						<span>
							<a data-href="welcome.html" data-title="主页" href="javascript:void(0)">
								<i class="Hui-iconfont Hui-iconfont-home"></i>
								<span style="font-size:16px;">主页</span>
							</a>
						</span>
					</dt>
				</dl>
				<dl id="menu-score">
					<dt>
						<span>
							<a data-href="score/list.html" data-title="成绩管理" href="javascript:void(0)"> 
								<i class="Hui-iconfont Hui-iconfont-order"></i>
								<span style="font-size:16px;">成绩管理</span>
							</a>
						</span>
					</dt>
				</dl>
				<dl id="menu-statistics">
					<dt>
						<i class="Hui-iconfont Hui-iconfont-ad"></i>
						<span style="font-size:16px;">数据统计</span>
						<i class="Hui-iconfont Hui-iconfont-arrow2-bottom menu_dropdown-arrow"></i>
					</dt>
					<dd>
						<ul>
							<li>
								<a data-href="charts/expensesTrend.html" data-title="支出趋势图" href="javascript:void(0)">
									<span style="font-size:16px;">支出趋势图</span>
								</a>
							</li>
							<li>
								<a data-href="charts/expensesAndRevenue.html" data-title="年度收支统计图" href="javascript:void(0)">
									<span style="font-size:16px;">年度收支统计图</span>
								</a>
							</li>
							<li>
								<a data-href="charts/categoryStatistics.html" data-title="分类支出统计图" href="javascript:void(0)">
									<span style="font-size:16px;">分类支出统计图</span>
								</a>
							</li>
							<li>
								<a data-href="charts/yearStatistics.html" data-title="往年收支比较" href="javascript:void(0)">
									<span style="font-size:16px;">往年收支比较</span>
								</a>
							</li>
							<li>
								<a data-href="charts/payMethodStatistics.html" data-title="支付方式统计图" href="javascript:void(0)">
									<span style="font-size:16px;">支付方式统计图</span>
								</a>
							</li>
						</ul>
					</dd>
				</dl>
				<dl id="menu-password">
					<dt>
						<span>
							<a data-href="7.html" data-title="科目管理" href="javascript:void(0)">
								<i class="Hui-iconfont Hui-iconfont-system"></i>
								<span style="font-size:16px;">科目管理</span>
							</a>
						</span>
					</dt>
				</dl>
				<dl id="menu-score">
					<dt>
						<span>
							<a data-href="score/list.html" data-title="考试管理" href="javascript:void(0)"> 
								<i class="Hui-iconfont Hui-iconfont-feedback2"></i>
								<span style="font-size:16px;">考试管理</span>
							</a>
						</span>
					</dt>
				</dl>
				<dl id="menu-borrow">
					<dt>
						<span>
							<a data-href="classes/list.html" data-title="班级管理" href="javascript:void(0)">
								<i class="Hui-iconfont Hui-iconfont-log"></i>
								<span style="font-size:16px;">班级管理</span>
							</a>
						</span>
					</dt>
				</dl>
				<dl id="menu-borrow">
					<dt>
						<span>
							<a data-href="2.html" data-title="学生管理" href="javascript:void(0)">
								<i class="Hui-iconfont Hui-iconfont-practice"></i>
								<span style="font-size:16px;">学生管理</span>
							</a>
						</span>
					</dt>
				</dl>
				<dl id="menu-system">
					<dt>
						<i class="Hui-iconfont Hui-iconfont-manage2"></i>
						<span style="font-size:16px;">系统管理</span>
						<i class="Hui-iconfont Hui-iconfont-arrow2-bottom menu_dropdown-arrow"></i>
					</dt>
					<dd>
						<ul>
							<li>
								<a data-href="setting/list.html" data-title="系统设置" href="javascript:void(0)">
									<span style="font-size:16px;">系统设置
								</a>
							</li>
							<li>
								<a data-href="log/list.html" data-title="系统日志" href="javascript:void(0)">
									<span style="font-size:16px;">系统日志</span>
								</a>
							</li>
						</ul>
					</dd>
				</dl>
			</div>
		</aside>
		
		<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
		
		<section class="Hui-article-box">
			<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
				<div class="Hui-tabNav-wp">
					<ul id="min_title_list" class="acrossTab cl">
						<li class="active">
							<span title="主页" data-href="welcome.html">主页</span>
							<em></em>
						</li>
					</ul>
				</div>
				<div class="Hui-tabNav-more btn-group">
					<a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;">
						<i class="Hui-iconfont Hui-iconfont-arrow2-left"></i>
					</a>
					<a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;">
						<i class="Hui-iconfont Hui-iconfont-arrow2-right"></i>
					</a>
				</div>
			</div>
			<div id="iframe_box" class="Hui-article">
				<div class="show_iframe">
					<div style="display:none" class="loading"></div>
					<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
				</div>
			</div>
		</section>
		
		<div class="contextMenu" id="Huiadminmenu">
			<ul>
				<li id="closethis">关闭当前 </li>
				<li id="closeall">关闭全部 </li>
			</ul>
		</div>
		
		<jsp:include page="common/_footer.jspf" />
		
		<!--请在下方写此页面业务相关的脚本-->
		<script type="text/javascript" src="/score/js/jquery.contextmenu.r2.js"></script>
		<script type="text/javascript">
			// 修改密码
			function modifyPassword() {
				layer.open({
					type: 2,
					title: '修改密码',
					maxmin: true,
					area: ['400px','300px'],
					content: 'user/toModifyPassword.html'
				});
			}
			// 个人信息
			function userInfo() {
				layer.open({
					type: 2,
					title: '个人信息',
					maxmin: true,
					area: ['400px','320px'],
					content: 'user/toUserInfo.html'
				});
			}
		</script> 
	</body>
</html>