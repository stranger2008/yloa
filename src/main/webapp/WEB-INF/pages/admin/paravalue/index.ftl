<html>
<head>
<title>系统参数值管理</title>
</head>
<body>

	<h2>[${(parach.ch_name)?if_exists}]系统参数值管理</h2>
	
	<@s.form action="${ctx}admin/paravalue/index.action" method="post" id="indexForm">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="searchtable">
		<tr>
			<td>
				<@s.hidden name="ch_code_search"/>
				<@s.text name="paravalue.val_key"/>：<@s.textfield name="val_key_search"/>
				
				<@s.text name="paravalue.val_value"/>：<@s.textfield name="val_value_search"/>
				
				<input type="button" class="tjbutton" value="搜索" onclick="listsearch()"/>
			</td>
		</tr>
	</table>
	<#if (parach.is_edit)?if_exists == '1'>
	<div class="batchbutton">
		<input type="button" class="tjbutton" value="删除" onclick="delinfo('${ctx}admin/paravalue/delete.action')"/>
		<input type="button" class="tjbutton" value="新增" onclick="linktourl('${ctx}admin/paravalue/add.action')"/>
	</div>
	</#if>
	
	<table width="100%" cellpadding="2" cellspacing="0" class="pagerlist">
		<tr>
			<#if (parach.is_edit)?if_exists == '1'>
			<th width="70"><input type="checkbox" name="topcheckall" onclick="checkall(this)" id="topcheckall"/></th>
			</#if>
		    <th><@s.text name="paravalue.val_key"/></th>
		    
		    <th><@s.text name="paravalue.val_value"/></th>
		    <#if (parach.is_edit)?if_exists == '1'>
			<th>编辑</th>
			</#if>
		</tr>
		
		<#list pagevo.list as paravalue>
		<tr>
			<#if (parach.is_edit)?if_exists == '1'>
			<td width="70"><input type="checkbox" name="paravalue.val_id" id="checkall_${paravalue_index+1}" value="${(paravalue.val_id)?if_exists}"/></td>
			</#if>
    		<td>${(paravalue.val_key)?if_exists}</td>
    		
    		<td>${(paravalue.val_value)?if_exists}</td>
    		<#if (parach.is_edit)?if_exists == '1'>
			<td><a href="###" onclick="linktourl('${ctx}admin/paravalue/view.action?paravalue.val_id=${(paravalue.val_id)?if_exists}')">编辑</a></td>
			</#if>
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

