<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<#include "/WEB-INF/pages/jsinc.ftl">
<#include "/WEB-INF/pages/cssinc.ftl">
<link href="${ctx}inc/css/login.css" rel="stylesheet" type="text/css" />
<title>登录</title>

<script type="text/javascript">
	$(document).ready(function(){
		$("#adminloginform").validate({
	        rules: {
	            "user.user_name": {
	                remote: {
	                	url:"${ctx}checkusername.action",
	                	type:"get",
	                	data:{
							user_name:function(){
								return $('#user_name').val();
							}
	                	}
	            	}
	            },
	            "user.passwd": {
	                remote: {
	                	url:"${ctx}checkpasswd.action",
	                	type:"get",
	                	data:{
							user_name:function(){
								return $('#user_name').val();
							},
							passwd:function(){
								return $('#passwd').val();
							}
	                	}
	            	}
	            },
	            imgcode: {
	                remote: "${ctx}checkcode.action"
	            }
	        },
	        messages: {
	            imgcode: {
	                remote: "验证码不正确"
	            },
	            "user.user_name": {
	                remote: "用户名不存在"
	            },
	            "user.passwd": {
	                remote: "密码输入不正确"
	            }
	        }
		});
	});
</script>

</head>
<body>

<form action="${ctx}adminlogin.action" method="post" id="adminloginform">

<!--登录开始-->
<div class="login">
	<div class="login_l f_left">
    	<h2><span>Yaling OA</span> 管理中心</h2>
		<p>Yaling OA是瑞宝通公司推出的JAVA版电子办公系统，帮助企业快捷方便管理公司业务。</p>
    </div>
    <div class="login_r f_left">
        <ul>
        	<li><span>用户名：</span><input type="text" name="user.user_name" id="user_name" class="required" maxlength="20"/></li>
        	<li><span>密&nbsp;&nbsp;码：</span><input type="password" name="user.passwd" id="passwd" class="required" maxlength="20"/></li>
        	<li><span>验证码：</span><input type="text" name="imgcode" id="imgcode" class="required" maxlength="4"/>&nbsp;<#include "/WEB-INF/pages/imgcode.ftl"></li>
            <li><span></span><input type="submit" name="logsub" value="登录" class="submit_btn"/></li>
        </ul>
    </div>
</div>
<div class="clear"></div>

<!--底部begin-->
<div class="w980">
    <div class="bottom"> 
      <p>2013-2014© PALAISPA  All Rights Reserved</p>
    </div>
</div>
<!--底部end-->

</form>

</body>
</html>
