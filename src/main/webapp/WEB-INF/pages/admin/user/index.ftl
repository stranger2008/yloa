<html>
<head>
<title>用户管理</title>
<script language="javascript" type="text/javascript" src="/inc/com/calendar/WdatePicker.js"></script>
</head>
<body>

	<h2>用户管理</h2>
	
	<@s.form action="${ctx}admin/user/index.action" method="post" id="indexForm">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="searchtable">
		<tr>
			<td>
				用户名称：<@s.textfield name="user_name_search" maxlength="20"/>
				部门：
				<select name="org_id_search">
					<option value=''>请选择</option>
					${orgSelect}
				</select>
				角色：
				<@s.select name="role_id_search" list="roleList" listValue="role_name" listKey="role_id" headerKey="" headerValue="请选择" />
				状态：
				<@s.select name="state_code_search" list=r"#{'1':'正常','2':'禁用'}" headerKey="" headerValue="请选择" />
				<br/>
				添加时间：<@s.textfield cssClass="Wdate" name="start_date_search" id="txtstartDate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})" />
				-
				<@s.textfield cssClass="Wdate" name="end_date_search" id="txtendDate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"/>
				<input type="button" class="tjbutton" value="搜索" onclick="listsearch()"/>
			</td>
		</tr>
	</table>
	
	<div class="batchbutton">
		<input type="button" class="tjbutton" value="删除" onclick="delinfo('${ctx}admin/user/delete.action')"/>
		<input type="button" class="tjbutton" value="新增" onclick="linktourl('${ctx}admin/user/add.action')"/>
	</div>
	
	<table width="100%" cellpadding="2" cellspacing="0" class="pagerlist">
		<tr>
			<th width="70"><input type="checkbox" name="topcheckall" onclick="checkall(this)" id="topcheckall"/></th>
			<th>用户名称</th>
			<th>部门</th>
			<th>角色</th>
			<th>状态</th>
			<th>添加时间</th>
			<th>编辑</th>
		</tr>
		
		<#list pagevo.list as user>
		<tr>
			<td width="70"><#if user.user_type == 0><input type="checkbox" name="user.user_id" id="checkall_${user_index+1}" value="${(user.user_id)?if_exists}"/></#if></td>
			<td>${(user.user_name)?if_exists}<#if user.user_type == 1><font color="red">【超级管理员】</font></#if></td>
			<td>${(user.org_name)?if_exists}</td>
			<td>${(user.role_name)?if_exists}</td>
			<td>
				<#if user.state_code == 1>
			      正常
			    <#else>
			      禁用
			    </#if>
			</td>
			<td>${(user.in_date)?if_exists}</td>
			<td><a href="###" onclick="linktourl('${ctx}admin/user/view.action?user.user_id=${(user.user_id)?if_exists}')">编辑</a></td>
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

