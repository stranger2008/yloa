<html>
<head>
<title>菜单管理</title>
</head>
<body>

	<h2>菜单管理</h2>
	
	<@s.form action="${ctx}admin/menu/index.action" method="post" id="indexForm">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="searchtable">
		<tr>
			<td>菜单名称：<@s.textfield name="menu_name_search" maxlength="20"/><input type="button" class="tjbutton" value="搜索" onclick="listsearch()"/></td>
		</tr>
	</table>
	
	<div class="batchbutton">
		<input type="button" class="tjbutton" value="删除" onclick="delinfo('${ctx}admin/menu/delete.action')"/>
		<input type="button" class="tjbutton" value="排序" onclick="savesort('${ctx}admin/menu/sort.action')"/>
		<input type="button" class="tjbutton" value="新增" onclick="linktourl('${ctx}admin/menu/add.action')"/>
	</div>
	
		<table width="100%" cellpadding="2" cellspacing="0" class="pagerlist">
			<tr>
				<td width="70"><input type="checkbox" name="topcheckall" onclick="checkall(this)" id="topcheckall"/></td>
				<td width="70"><b>显示顺序</b></td>
				<td width="70%"><b>菜单名称</b></td>
				<td><b>编辑</b></td>
			</tr>
			
			<#list menuList as menu>
			<tr id="${(menu.menu_level)?if_exists}_${(menu.menu_id)?if_exists}_${(menu.up_menu_id)?if_exists}">
				<td width="70"><input type="checkbox" name="menu.menu_id" id="checkall_${menu_index+1}" value="${(menu.menu_id)?if_exists}"/></td>
				<td width="70"><input type="text" name="menu.sort_no" size="3" maxlenth="4" value="${(menu.sort_no)?if_exists}"/></td>
	    		<td id="td_${(menu.menu_id)?if_exists}">${(menu.menu_name)?if_exists}</td>
	    		<td><a href="###" onclick="linktourl('${ctx}admin/menu/view.action?menu.menu_id=${(menu.menu_id)?if_exists}')">编辑</a></td>
			</tr>
			</#list>
			
			<#list menuList as menu>
			<script>
				$($(".pagerlist tr[id$='${(menu.menu_id)?if_exists}']")).insertAfter("#${(menu.menu_level)?if_exists}_${(menu.menu_id)?if_exists}_${(menu.up_menu_id)?if_exists}");
				var menu_level = ${(menu.menu_level)?if_exists};
				if(menu_level > 1){
					$("#td_${(menu.menu_id)?if_exists}").css("padding-left", ((menu_level-1)*20)+"px");
				}
			</script>
			</#list>
			
		</table>
	
	</@s.form>
	
</body>
</html>

