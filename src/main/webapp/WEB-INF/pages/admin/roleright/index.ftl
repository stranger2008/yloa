<html>
<head>
<title>操作权限管理</title>
</head>
<body>

	<h2>操作权限管理</h2>
	
	<@s.form action="${ctx}admin/roleright/index.action" method="post" id="indexForm">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="searchtable">
		<tr>
			<td>
				
				<@s.text name="roleright.right_name"/>：<@s.textfield name="right_name_search"/>
				
				<@s.text name="roleright.menu_id"/>：
				<select name="menu_id_search">
					<option value=''>请选择</option>
					${menuSelect}
				</select>
				
				<@s.text name="roleright.url"/>：<@s.textfield name="url_search"/>
				
				<input type="button" class="tjbutton" value="搜索" onclick="listsearch()"/>
			</td>
		</tr>
	</table>
	
	<div class="batchbutton">
		<input type="button" class="tjbutton" value="删除" onclick="delinfo('${ctx}admin/roleright/delete.action')"/>
		<input type="button" class="tjbutton" value="新增" onclick="linktourl('${ctx}admin/roleright/add.action')"/>
	</div>
	
	<table width="100%" cellpadding="2" cellspacing="0" class="pagerlist">
		<tr>
			<th width="70"><input type="checkbox" name="topcheckall" onclick="checkall(this)" id="topcheckall"/></th>
			
		    <th><@s.text name="roleright.right_name"/></th>
		    
		    <th><@s.text name="roleright.menu_id"/></th>
		    
		    <th><@s.text name="roleright.url"/></th>
		    
			<th>编辑</th>
		</tr>
		
		<#list pagevo.list as roleright>
		<tr>
			<td width="70"><input type="checkbox" name="roleright.right_id" id="checkall_${roleright_index+1}" value="${(roleright.right_id)?if_exists}"/></td>
			
    		<td>${(roleright.right_name)?if_exists}</td>
    		
    		<td>${(roleright.menu_name)?if_exists}</td>
    		
    		<td>${(roleright.url)?if_exists}</td>
    		
			<td><a href="###" onclick="linktourl('${ctx}admin/roleright/view.action?roleright.right_id=${(roleright.right_id)?if_exists}')">编辑</a></td>
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

