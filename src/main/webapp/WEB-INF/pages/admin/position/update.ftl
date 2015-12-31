<html>
<head>
<title>职位修改</title>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#updateForm").validate({
			rules: {
				"position.pos_name": {
					required: true,
					maxlength: 50
				},"position.sort_no": {
					required: true,
					maxlength: 11
				},"position.pos_desc": {
					required: false,
					maxlength: 300
				}
  			},
  			messages: {
  				"position.pos_name": {
			    	required: "请输入<@s.text name="position.pos_name"/>",
			    	maxlength: jQuery.format("<@s.text name="position.pos_name"/>不能大于{0}个字符")
			    },"position.sort_no": {
			    	required: "请输入<@s.text name="position.sort_no"/>",
			    	maxlength: jQuery.format("<@s.text name="position.sort_no"/>不能大于{0}个字符")
			    },"position.pos_desc": {
			    	required: "请输入<@s.text name="position.pos_desc"/>",
			    	maxlength: jQuery.format("<@s.text name="position.pos_desc"/>不能大于{0}个字符")
			    }
  				
   			}
		});
	});
</script>
<@s.form action="${ctx}admin/position/update.action" method="post" id="updateForm">
	<table width="100%" cellpadding="0" cellspacing="2" class="edittab">
		<tr><td><h2>职位修改</h2></td></tr>
		
		
		<tr><th><@s.text name="position.pos_name"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="position.pos_name"/>
		</td></tr>
		
		<tr><th><@s.text name="position.sort_no"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="position.sort_no"/>
		</td></tr>
		
		<tr><th><@s.text name="position.pos_desc"/>：</th></tr>
		<tr><td>
			<@s.textarea name="position.pos_desc"/>
		</td></tr>
		
		
		<tr><td>
			<@s.hidden name="position.pos_id" />
			${listSearchHiddenField?if_exists}
			<input type="submit" class="tjbutton" value="提交" />
		</td></tr>
	</table>
</@s.form>
</body>
</html>

