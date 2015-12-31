<html>
<head>
<title>系统参数值新增</title>
</head>
<body>

<script type="text/javascript">
	$(document).ready(function(){
		$("#updateForm").validate({
			rules: {
				"paravalue.ch_code": {
					required: true,
					maxlength: 50
				},"paravalue.val_key": {
					required: true,
					maxlength: 50
				},"paravalue.val_value": {
					required: true,
					maxlength: ${(parach.value_len)?if_exists}
				},"paravalue.rsrv1": {
					required: false,
					maxlength: 50
				},"paravalue.rsrv2": {
					required: false,
					maxlength: 50
				}
  			},
  			messages: {
  				"paravalue.ch_code": {
			    	required: "请输入<@s.text name="paravalue.ch_code"/>",
			    	maxlength: jQuery.format("<@s.text name="paravalue.ch_code"/>不能大于{0}个字符")
			    },"paravalue.val_key": {
			    	required: "请输入<@s.text name="paravalue.val_key"/>",
			    	maxlength: jQuery.format("<@s.text name="paravalue.val_key"/>不能大于{0}个字符")
			    },"paravalue.val_value": {
			    	required: "请输入<@s.text name="paravalue.val_value"/>",
			    	maxlength: jQuery.format("<@s.text name="paravalue.val_value"/>不能大于{0}个字符")
			    },"paravalue.rsrv1": {
			    	required: "请输入<@s.text name="paravalue.rsrv1"/>",
			    	maxlength: jQuery.format("<@s.text name="paravalue.rsrv1"/>不能大于{0}个字符")
			    },"paravalue.rsrv2": {
			    	required: "请输入<@s.text name="paravalue.rsrv2"/>",
			    	maxlength: jQuery.format("<@s.text name="paravalue.rsrv2"/>不能大于{0}个字符")
			    }
  				
   			}
		});
	});
</script>
<@s.form action="${ctx}admin/paravalue/insert.action" method="post" id="updateForm">
	<table width="100%" cellpadding="0" cellspacing="2" class="edittab">
		<tr><td><h2>[${(parach.ch_name)?if_exists}]系统参数值新增</h2></td></tr>
		
		<tr><th><@s.text name="paravalue.val_key"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="paravalue.val_key"/>
		</td></tr>
		
		<tr><th><@s.text name="paravalue.val_value"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textarea name="paravalue.val_value"/>
		</td></tr>
		
		<tr><th><@s.text name="paravalue.rsrv1"/>：</th></tr>
		<tr><td>
			<@s.textfield name="paravalue.rsrv1"/>
		</td></tr>
		
		<tr><th><@s.text name="paravalue.rsrv2"/>：</th></tr>
		<tr><td>
			<@s.textfield name="paravalue.rsrv2"/>
		</td></tr>
		
		<tr><td>
			<@s.hidden name="paravalue.ch_code" value="${ch_code_search?if_exists}"/>
			${listSearchHiddenField?if_exists}
			<@s.token name="token"/>
			<input type="submit" class="tjbutton" value="提交" />
		</td></tr>
	</table>
</@s.form>
</body>
</html>

