<html>
<head>
<title>部门管理</title>
</head>
<body>

	<h2>部门管理</h2>
	
	<@s.form action="${ctx}admin/organize/index.action" method="post" id="indexForm">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="searchtable">
		<tr>
			<td>
				<@s.text name="organize.org_name"/>：<@s.textfield name="org_name_search"/>
				<@s.text name="organize.up_org_id"/>：
				<select name="up_org_id_search">
					<option value=''>请选择</option>
					${orgSelect}
				</select>
				<input type="button" class="tjbutton" value="搜索" onclick="listsearch()"/>
			</td>
		</tr>
	</table>
	
	<div class="batchbutton">
		<input type="button" class="tjbutton" value="删除" onclick="delinfo('${ctx}admin/organize/delete.action')"/>
		<input type="button" class="tjbutton" value="排序" onclick="savesort('${ctx}admin/organize/sort.action')"/>
		<input type="button" class="tjbutton" value="新增" onclick="linktourl('${ctx}admin/organize/add.action')"/>
	</div>
	
	<table width="100%" cellpadding="2" cellspacing="0" class="pagerlist">
		<tr>
			<th width="70"><input type="checkbox" name="topcheckall" onclick="checkall(this)" id="topcheckall"/></th>
			<th width="70"><@s.text name="organize.sort_no"/></th>
		    <th><@s.text name="organize.org_name"/></th>
			<th>编辑</th>
		</tr>
		
		<#list organizeList as organize>
		<tr id="${(organize.org_level)?if_exists}_${(organize.org_id)?if_exists}_${(organize.up_org_id)?if_exists}">
			<td width="70"><input type="checkbox" name="organize.org_id" id="checkall_${organize_index+1}" value="${(organize.org_id)?if_exists}"/></td>
			<td><input type="text" name="organize.sort_no" size="3" maxlenth="4" value="${(organize.sort_no)?if_exists}"/></td>
    		<td id="td_${(organize.org_id)?if_exists}">${(organize.org_name)?if_exists}</td>
			<td><a href="###" onclick="linktourl('${ctx}admin/organize/view.action?organize.org_id=${(organize.org_id)?if_exists}')">编辑</a></td>
		</tr>
		</#list>
		
		<#list organizeList as organize>
		
		<script>
			$($(".pagerlist tr[id$='${(organize.org_id)?if_exists}']")).insertAfter("#${(organize.org_level)?if_exists}_${(organize.org_id)?if_exists}_${(organize.up_org_id)?if_exists}");
			var org_level = ${(organize.org_level)?if_exists};
			if(org_level > 1){
				$("#td_${(organize.org_id)?if_exists}").css("padding-left", ((org_level-1)*20)+"px");
			}
		</script>
		
		</#list>
		
	</table>
	
	</@s.form>
	
</body>
</html>

