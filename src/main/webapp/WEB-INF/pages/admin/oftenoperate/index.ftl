<html>
<head>
<title>常用操作管理</title>
</head>
<body>

	<h2>常用操作管理</h2>
	
	<@s.form action="${ctx}admin/oftenoperate/index.action" method="post" id="indexForm">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="searchtable">
		<tr>
			<td>
				
				<@s.text name="oftenoperate.oper_name"/>：<@s.textfield name="oper_name_search"/>
				
				<@s.text name="oftenoperate.oper_url"/>：<@s.textfield name="oper_url_search"/>
				
				<input type="button" class="tjbutton" value="搜索" onclick="listsearch()"/>
			</td>
		</tr>
	</table>
	
	<div class="batchbutton">
		<input type="button" class="tjbutton" value="删除" onclick="delinfo('${ctx}admin/oftenoperate/delete.action')"/>
		<input type="button" class="tjbutton" value="排序" onclick="savesort('${ctx}admin/oftenoperate/sort.action')"/>
		<input type="button" class="tjbutton" value="新增" onclick="linktourl('${ctx}admin/oftenoperate/add.action')"/>
	</div>
	
	<table width="100%" cellpadding="2" cellspacing="0" class="pagerlist">
		<tr>
			<th width="70"><input type="checkbox" name="topcheckall" onclick="checkall(this)" id="topcheckall"/></th>
			<th width="70"><@s.text name="organize.sort_no"/></th>
		    <th><@s.text name="oftenoperate.oper_name"/></th>
		    
		    <th><@s.text name="oftenoperate.oper_url"/></th>
		    
			<th>编辑</th>
		</tr>
		
		<#list pagevo.list as oftenoperate>
		<tr>
			<td width="70"><input type="checkbox" name="oftenoperate.trade_id" id="checkall_${oftenoperate_index+1}" value="${(oftenoperate.trade_id)?if_exists}"/></td>
			<td><input type="text" name="oftenoperate.sort_no" size="3" maxlenth="4" value="${(oftenoperate.sort_no)?if_exists}"/></td>
    		<td>${(oftenoperate.oper_name)?if_exists}</td>
    		
    		<td>${(oftenoperate.oper_url)?if_exists}</td>
    		
			<td><a href="###" onclick="linktourl('${ctx}admin/oftenoperate/view.action?oftenoperate.trade_id=${(oftenoperate.trade_id)?if_exists}')">编辑</a></td>
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

