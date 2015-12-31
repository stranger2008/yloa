<html>
<head>
<title>系统参数管理</title>
</head>
<body>

	<h2>系统参数管理</h2>
	
	<@s.form action="${ctx}admin/parach/index.action" method="post" id="indexForm">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="searchtable">
		<tr>
			<td>
				
				<@s.text name="parach.ch_name"/>：<@s.textfield name="ch_name_search"/>
				
				<input type="button" class="tjbutton" value="搜索" onclick="listsearch()"/>
			</td>
		</tr>
	</table>
	<#if (ylconfig.sysmodel)?if_exists == 'dev'>
	<div class="batchbutton">
		<input type="button" class="tjbutton" value="删除" onclick="delinfo('${ctx}admin/parach/delete.action')"/>
		<input type="button" class="tjbutton" value="新增" onclick="linktourl('${ctx}admin/parach/add.action')"/>
	</div>
	</#if>
	
	<table width="100%" cellpadding="2" cellspacing="0" class="pagerlist">
		<tr>
			<#if (ylconfig.sysmodel)?if_exists == 'dev'>
			<th width="70"><input type="checkbox" name="topcheckall" onclick="checkall(this)" id="topcheckall"/></th>
			</#if>
		    <th><@s.text name="parach.ch_name"/></th>
			<th>编辑</th>
		</tr>
		
		<#list pagevo.list as parach>
		<tr>
			<#if (ylconfig.sysmodel)?if_exists == 'dev'>
			<td width="70"><input type="checkbox" name="parach.ch_code" id="checkall_${parach_index+1}" value="${(parach.ch_code)?if_exists}"/></td>
			</#if>
    		<td>${(parach.ch_name)?if_exists}</td>
			<td>
				<#if (ylconfig.sysmodel)?if_exists == 'dev'>
				<a href="###" onclick="linktourl('${ctx}admin/parach/view.action?parach.ch_code=${(parach.ch_code)?if_exists}')">编辑</a>&nbsp;
				</#if>
				<a href="###" onclick="linktourl('${ctx}admin/paravalue/index.action?ch_code_search=${(parach.ch_code)?if_exists}')">参数值配置</a>
			</td>
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

