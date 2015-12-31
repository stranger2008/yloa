<html>
<head>
<title>角色管理</title>
</head>
<body>

	<h2>角色管理</h2>
	
	<@s.form action="${ctx}admin/role/index.action" method="post" id="indexForm">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="searchtable">
		<tr>
			<td>
				
				<@s.text name="role.role_name"/>：<@s.textfield name="role_name_search"/>
				
				<input type="button" class="tjbutton" value="搜索" onclick="listsearch()"/>
			</td>
		</tr>
	</table>
	
	<div class="batchbutton">
		<input type="button" class="tjbutton" value="删除" onclick="delinfo('${ctx}admin/role/delete.action')"/>
		<input type="button" class="tjbutton" value="新增" onclick="linktourl('${ctx}admin/role/add.action')"/>
	</div>
	
	<table width="100%" cellpadding="2" cellspacing="0" class="pagerlist">
		<tr>
			<th width="70"><input type="checkbox" name="topcheckall" onclick="checkall(this)" id="topcheckall"/></th>
			
		    <th><@s.text name="role.role_name"/></th>
		    
			<th>编辑</th>
		</tr>
		
		<#list pagevo.list as role>
		<tr>
			<td width="70"><input type="checkbox" name="role.role_id" id="checkall_${role_index+1}" value="${(role.role_id)?if_exists}"/></td>
			
    		<td>${(role.role_name)?if_exists}</td>
    		
			<td><a href="###" onclick="linktourl('${ctx}admin/role/view.action?role.role_id=${(role.role_id)?if_exists}')">编辑</a></td>
		</tr>
		</#list>
		
	</table>
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="pagertable">
		<tr>
			<td align="left">
				${(pagevo.pageString)?if_exists}
			</td>
		</tr>
	</table>
	
	
	</@s.form>
	
</body>
</html>

