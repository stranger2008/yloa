<html>
<head>
<title>系统参数新增</title>
</head>
<body>

<script type="text/javascript">
	$(document).ready(function(){
		$("#updateForm").validate({
			rules: {
				"parach.ch_code": {
					required: true,
					maxlength: 50
				},"parach.ch_name": {
					required: true,
					maxlength: 50
				},"parach.is_edit": {
					required: true,
					maxlength: 1
				},"parach.value_len": {
					required: true,
					maxlength: 11
				}
  			},
  			messages: {
  				"parach.ch_code": {
			    	required: "请输入<@s.text name="parach.ch_code"/>",
			    	maxlength: jQuery.format("<@s.text name="parach.ch_code"/>不能大于{0}个字符")
			    },"parach.ch_name": {
			    	required: "请输入<@s.text name="parach.ch_name"/>",
			    	maxlength: jQuery.format("<@s.text name="parach.ch_name"/>不能大于{0}个字符")
			    },"parach.is_edit": {
			    	required: "请输入<@s.text name="parach.is_edit"/>",
			    	maxlength: jQuery.format("<@s.text name="parach.is_edit"/>不能大于{0}个字符")
			    },"parach.value_len": {
			    	required: "请输入<@s.text name="parach.value_len"/>",
			    	maxlength: jQuery.format("<@s.text name="parach.value_len"/>不能大于{0}个字符")
			    }
  				
   			}
		});
	});
</script>
<@s.form action="${ctx}admin/parach/insert.action" method="post" id="updateForm">
	<table width="100%" cellpadding="0" cellspacing="2" class="edittab">
		<tr><td><h2>系统参数新增</h2></td></tr>
		
		<tr><th><@s.text name="parach.ch_code"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="parach.ch_code"/>
		</td></tr>
		
		<tr><th><@s.text name="parach.ch_name"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="parach.ch_name"/>
		</td></tr>
		
		<tr><th><@s.text name="parach.is_edit"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.radio name="parach.is_edit" list=r"#{'0':'否','1':'是'}" value="1"/><span>不支持编辑则参数键值只能查看</span>
		</td></tr>
		
		<tr><th><@s.text name="parach.value_len"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="parach.value_len"/>
		</td></tr>
		
		<tr><td>
			<@s.token name="token"/>
			<input type="submit" class="tjbutton" value="提交" />
		</td></tr>
	</table>
</@s.form>
</body>
</html>

