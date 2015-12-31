<html>
<head>
<title>用户修改</title>
<script language="javascript" type="text/javascript" src="/inc/com/calendar/WdatePicker.js"></script>
</head>
<body>

<script type="text/javascript">
	$(document).ready(function(){
		$("#updateForm").validate({
			rules: {
				"user.passwd": {
					minlength: 6
				},
				sure_passwd: {
				    minlength: 6,
				    equalTo: "#user_passwd"
				},
   				"user.email": "email"
  			},
  			messages: {
  				"user.passwd": {
			    	minlength: jQuery.format("密码不能小于{0}个字 符")
			    },
			    sure_passwd: {
			    	minlength: "确认密码不能小于{0}个字符",
			    	equalTo: "两次输入密码不一致"
			    },
   				"user.email": "邮箱格式不正确"
   			}
		});
	});
</script>
<@s.form action="${ctx}admin/user/update.action" method="post" id="updateForm">
	<table width="100%" cellpadding="0" cellspacing="2" class="edittab">
		<tr><td><h2>用户修改：${(user.user_name)?if_exists}</h2></td></tr>
		
		<tr><th>密码：</th></tr>
		<tr><td>
			<input type="password" name="user.passwd" id="user_passwd"/><span>不输代表不修改密码</span>
		</td></tr>
		
		<tr><th>确认密码：</th></tr>
		<tr><td>
			<input type="password" name="sure_passwd"/>
		</td></tr>
		
		<tr><th>部门：</th></tr>
		<tr><td>
			<select name="user.org_id">
				<option value='${oneLevelOrgId}'>请选择</option>
				${orgSelect}
			</select>
		</td></tr>
		
		<tr><th>角色：</th></tr>
		<tr><td>
			<@s.select name="user.role_id" list="roleList" listValue="role_name" listKey="role_id" headerKey="0" headerValue="请选择" />
		</td></tr>
		
		<tr><th>职位：</th></tr>
		<tr><td>
			<@s.select name="user.pos_id" list="positionList" listValue="pos_name" listKey="pos_id" headerKey="0" headerValue="请选择" />
		</td></tr>
		
		<tr><th>姓名：</th></tr>
		<tr><td>
			<@s.textfield name="user.real_name" maxlength="20"/>
		</td></tr>
		
		<tr><th>头像：</th></tr>
		<tr><td>
			<@s.textfield name="user.img_path" maxlength="20"/>
		</td></tr>
		
		<tr><th>生日：</th></tr>
		<tr><td>
			<@s.textfield name="user.birth" maxlength="20" cssClass="Wdate" onclick="WdatePicker({readOnly:true})" />
		</td></tr>
		
		<tr><th>办公室电话：</th></tr>
		<tr><td>
			<@s.textfield name="user.office_phone" maxlength="20"/>
		</td></tr>
		
		<tr><th>传真：</th></tr>
		<tr><td>
			<@s.textfield name="user.fax" maxlength="20"/>
		</td></tr>
		
		<tr><th>手机：</th></tr>
		<tr><td>
			<@s.textfield name="user.cellphone" maxlength="20"/>
		</td></tr>
		
		<tr><th>家庭电话：</th></tr>
		<tr><td>
			<@s.textfield name="user.home_phone" maxlength="20"/>
		</td></tr>
		
		<tr><th>邮箱：</th></tr>
		<tr><td>
			<@s.textfield name="user.email" maxlength="60" cssClass="w320"/>
		</td></tr>
		
		<tr><th>地址：</th></tr>
		<tr><td>
			<@s.textfield name="user.address" maxlength="50" cssClass="w320"/>
		</td></tr>
		
		<tr><th>邮编：</th></tr>
		<tr><td>
			<@s.textfield name="user.post_code" maxlength="10"/>
		</td></tr>
		
		<tr><th>状态：</th></tr>
		<tr><td>
			<@s.radio name="user.state_code" list=r"#{'1':'正常','2':'禁用'}"/><span>禁用则无法登录</span>
		</td></tr>
		
		<tr><td>
			<@s.hidden name="user.user_name"/>
			<@s.hidden name="user.user_id" />
			${listSearchHiddenField?if_exists}
			<input type="submit" class="tjbutton" value="提交" />
		</td></tr>
	</table>
</@s.form>
</body>
</html>

