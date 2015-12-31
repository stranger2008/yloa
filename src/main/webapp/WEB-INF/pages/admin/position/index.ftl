<html>
<head>
<title>职位管理</title>
</head>
<body>

	<h2>职位管理</h2>
	
	<@s.form action="${ctx}admin/position/index.action" method="post" id="indexForm">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="searchtable">
		<tr>
			<td>
				
				<@s.text name="position.pos_name"/>：<@s.textfield name="pos_name_search"/>
				
				<input type="button" class="tjbutton" value="搜索" onclick="listsearch()"/>
			</td>
		</tr>
	</table>
	
	<div class="batchbutton">
		<input type="button" class="tjbutton" value="删除" onclick="delinfo('${ctx}admin/position/delete.action')"/>
		<input type="button" class="tjbutton" value="排序" onclick="savesort('${ctx}admin/position/sort.action')"/>
		<input type="button" class="tjbutton" value="新增" onclick="linktourl('${ctx}admin/position/add.action')"/>
	</div>
	
	<table width="100%" cellpadding="2" cellspacing="0" class="pagerlist">
		<tr>
			<th width="70"><input type="checkbox" name="topcheckall" onclick="checkall(this)" id="topcheckall"/></th>
			<th width="70"><@s.text name="position.sort_no"/></th>
		    <th><@s.text name="position.pos_name"/></th>
			<th>编辑</th>
		</tr>
		
		<#list pagevo.list as position>
		<tr>
			<td width="70"><input type="checkbox" name="position.pos_id" id="checkall_${position_index+1}" value="${(position.pos_id)?if_exists}"/></td>
			<td><input type="text" name="position.sort_no" size="3" maxlenth="4" value="${(position.sort_no)?if_exists}"/></td>
    		<td>${(position.pos_name)?if_exists}</td>
			<td><a href="###" onclick="linktourl('${ctx}admin/position/view.action?position.pos_id=${(position.pos_id)?if_exists}')">编辑</a></td>
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

