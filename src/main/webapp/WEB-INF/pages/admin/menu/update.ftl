<html>
<head>
<title>菜单修改</title>
</head>
<body>

<script type="text/javascript">
	$(document).ready(function(){
		$("#updateForm").validate({
			rules: {
   				"menu.menu_name": "required",
   				"menu.sort_no": {
   					required: true,
   					digits: true
   				}
  			},
  			messages: {
   				"menu.menu_name": "请输入菜单名称",
   				"menu.sort_no": {
   					required: "请输入显示顺序",
   					digits: "显示顺序只能为整数"
   				}
   			}
		});
	});
</script>
<@s.form action="${ctx}admin/menu/update.action" method="post" id="updateForm">
	<table width="100%" cellpadding="0" cellspacing="2" class="edittab">
		<tr><td><h2>菜单修改</h2></td></tr>
		<tr><th>菜单名称：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="menu.menu_name" maxlength="20"/>
		</td></tr>
		<tr><th>上级菜单：</th></tr>
		<tr><td>
			<select name="menu.up_menu_id">
				<option value='${oneLevelMneuId}'>请选择</option>
				${menuSelect}
			</select>
			<span>不选择表示添加一级菜单</span>
		</td></tr>
		<tr><th>链接地址：</th></tr>
		<tr><td>
			<@s.textfield name="menu.url" cssClass="w320" maxlength="100"/>
		</td></tr>
		<tr><th>显示顺序：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="menu.sort_no" maxlength="4"/>
		</td></tr>
		<tr><th>打开方式：</th></tr>
		<tr><td>
			<@s.select name="menu.target" list=r"#{'_self':'当前窗口打开','_blank':'新窗口中打开'}"/>
			<@s.hidden name="menu.menu_id" />
			<@s.hidden name="menu.syscode" />
			${listSearchHiddenField?if_exists}
		</td></tr>
		<tr><td><input type="submit" class="tjbutton" value="提交" /></td></tr>
	</table>
</@s.form>
</body>
</html>

