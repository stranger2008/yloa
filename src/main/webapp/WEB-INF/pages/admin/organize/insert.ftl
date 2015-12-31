<html>
<head>
<title>部门新增</title>
</head>
<body>

<script type="text/javascript">
	$(document).ready(function(){
		$("#updateForm").validate({
			rules: {
				"organize.org_name": {
					required: true,
					maxlength: 50
				},"organize.sort_no": {
					required: true,
					maxlength: 11
				},"organize.org_desc": {
					required: false,
					maxlength: 300
				}
  			},
  			messages: {
  				"organize.org_name": {
			    	required: "请输入<@s.text name="organize.org_name"/>",
			    	maxlength: jQuery.format("<@s.text name="organize.org_name"/>不能大于{0}个字符")
			    },"organize.sort_no": {
			    	required: "请输入<@s.text name="organize.sort_no"/>",
			    	maxlength: jQuery.format("<@s.text name="organize.sort_no"/>不能大于{0}个字符")
			    },"organize.org_desc": {
			    	required: "请输入<@s.text name="organize.org_desc"/>",
			    	maxlength: jQuery.format("<@s.text name="organize.org_desc"/>不能大于{0}个字符")
			    }
  				
   			}
		});
	});
</script>
<@s.form action="${ctx}admin/organize/insert.action" method="post" id="updateForm">
	<table width="100%" cellpadding="0" cellspacing="2" class="edittab">
		<tr><td><h2>部门新增</h2></td></tr>
		
		<tr><th><@s.text name="organize.org_name"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="organize.org_name"/>
		</td></tr>
		
		<tr><th><@s.text name="organize.up_org_id"/>：</th></tr>
		<tr><td>
			<select name="organize.up_org_id">
				<option value='${oneLevelOrgId}'>请选择</option>
				${orgSelect}
			</select>
			<span>不选择表示添加一级部门</span>
		</td></tr>
		
		<tr><th><@s.text name="organize.sort_no"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="organize.sort_no"/>
		</td></tr>
		
		<tr><th><@s.text name="organize.org_desc"/>：</th></tr>
		<tr><td>
			<@s.textarea name="organize.org_desc"/>
		</td></tr>
		
		<tr><td>
			<@s.token name="token"/>
			<input type="submit" class="tjbutton" value="提交" />
		</td></tr>
	</table>
</@s.form>
</body>
</html>

