<html>
<head>
<title>常用操作修改</title>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#updateForm").validate({
			rules: {
				"oftenoperate.sort_no": {
					required: true,
					maxlength: 11
				},"oftenoperate.oper_name": {
					required: true,
					maxlength: 30
				},"oftenoperate.oper_url": {
					required: true,
					maxlength: 200
				}
  			},
  			messages: {
  				"oftenoperate.sort_no": {
			    	required: "请输入<@s.text name="oftenoperate.sort_no"/>",
			    	maxlength: jQuery.format("<@s.text name="oftenoperate.sort_no"/>不能大于{0}个字符")
			    },"oftenoperate.oper_name": {
			    	required: "请输入<@s.text name="oftenoperate.oper_name"/>",
			    	maxlength: jQuery.format("<@s.text name="oftenoperate.oper_name"/>不能大于{0}个字符")
			    },"oftenoperate.oper_url": {
			    	required: "请输入<@s.text name="oftenoperate.oper_url"/>",
			    	maxlength: jQuery.format("<@s.text name="oftenoperate.oper_url"/>不能大于{0}个字符")
			    }
  				
   			}
		});
	});
</script>
<@s.form action="${ctx}admin/oftenoperate/update.action" method="post" id="updateForm">
	<table width="100%" cellpadding="0" cellspacing="2" class="edittab">
		<tr><td><h2>常用操作修改</h2></td></tr>
		
		
		<tr><th><@s.text name="oftenoperate.sort_no"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="oftenoperate.sort_no"/>
		</td></tr>
		
		<tr><th><@s.text name="oftenoperate.oper_name"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="oftenoperate.oper_name"/>
		</td></tr>
		
		<tr><th><@s.text name="oftenoperate.oper_url"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="oftenoperate.oper_url" cssClass="w320"/>
		</td></tr>
		
		
		<tr><td>
			<@s.hidden name="oftenoperate.trade_id" />
			${listSearchHiddenField?if_exists}
			<input type="submit" class="tjbutton" value="提交" />
		</td></tr>
	</table>
</@s.form>
</body>
</html>

