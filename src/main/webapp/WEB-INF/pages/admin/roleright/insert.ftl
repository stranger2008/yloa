<html>
<head>
<title>操作权限新增</title>
</head>
<body>

<script type="text/javascript">
	$(document).ready(function(){
		$("#updateForm").validate({
			rules: {
				"roleright.right_name": {
					required: true,
					maxlength: 20
				},"roleright.menu_id": {
					required: true,
					maxlength: 10
				},"roleright.url": {
					required: true,
					maxlength: 100
				},"roleright.type": {
					required: true,
					maxlength: 1
				},"roleright.remark": {
					required: false,
					maxlength: 200
				}
  			},
  			messages: {
  				"roleright.right_name": {
			    	required: "请输入<@s.text name="roleright.right_name"/>",
			    	maxlength: jQuery.format("<@s.text name="roleright.right_name"/>不能大于{0}个字符")
			    },"roleright.menu_id": {
			    	required: "请选择<@s.text name="roleright.menu_id"/>",
			    	maxlength: jQuery.format("<@s.text name="roleright.menu_id"/>不能大于{0}个字符")
			    },"roleright.url": {
			    	required: "请输入<@s.text name="roleright.url"/>",
			    	maxlength: jQuery.format("<@s.text name="roleright.url"/>不能大于{0}个字符")
			    },"roleright.type": {
			    	required: "请输入<@s.text name="roleright.type"/>",
			    	maxlength: jQuery.format("<@s.text name="roleright.type"/>不能大于{0}个字符")
			    },"roleright.remark": {
			    	required: "请输入<@s.text name="roleright.remark"/>",
			    	maxlength: jQuery.format("<@s.text name="roleright.remark"/>不能大于{0}个字符")
			    }
  				
   			}
		});
	});
</script>
<@s.form action="${ctx}admin/roleright/insert.action" method="post" id="updateForm">
	<table width="100%" cellpadding="0" cellspacing="2" class="edittab">
		<tr><td><h2>操作权限新增</h2></td></tr>
		
		<tr><th><@s.text name="roleright.right_name"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="roleright.right_name"/>
		</td></tr>
		
		<tr><th><@s.text name="roleright.menu_id"/>：<font>*</font></th></tr>
		<tr><td>
			<select name="roleright.menu_id">
				<option value=''>请选择</option>
				${menuSelect}
			</select>
		</td></tr>
		
		<tr><th><@s.text name="roleright.url"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="roleright.url" cssClass="w320"/>
		</td></tr>
		
		<tr><th><@s.text name="roleright.type"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.radio name="roleright.type" list=r"#{'0':'是','1':'否'}" value="0" />
			是否支持添加到快捷操作
		</td></tr>
		
		<tr><th><@s.text name="roleright.remark"/>：</th></tr>
		<tr><td>
			<@s.textarea name="roleright.remark"/>
		</td></tr>
		
		<tr><td>
			<@s.token name="token"/>
			<input type="submit" class="tjbutton" value="提交" />
		</td></tr>
	</table>
</@s.form>
</body>
</html>

