<html>
<head>
<title>日志管理</title>
<script language="javascript" type="text/javascript" src="/inc/com/calendar/WdatePicker.js"></script>
</head>
<body>

	<h2>日志管理</h2>
	
	<@s.form action="${ctx}admin/logs/index.action" method="post" id="indexForm">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0" class="searchtable">
		<tr>
			<td>
				
				<@s.text name="logs.content"/>：<@s.textfield name="content_search"/>
				
				<@s.text name="logs.ipaddr"/>：<@s.textfield name="ipaddr_search"/>
				
				<@s.text name="logs.user_name"/>：<@s.textfield name="user_name_search"/>
				<br/>
				<@s.text name="logs.in_date"/>：<@s.textfield cssClass="Wdate" name="in_date_start_search" id="txtstartDate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})" />
				-
				<@s.textfield cssClass="Wdate" name="in_date_end_search" id="txtendDate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"/>
				<input type="button" class="tjbutton" value="搜索" onclick="listsearch()"/>
			</td>
		</tr>
	</table>
	
	<div class="batchbutton">
		<input type="button" class="tjbutton" value="删除" onclick="delinfo('${ctx}admin/logs/delete.action')"/>
		<input type="button" class="tjbutton" value="清空" onclick="linktourl('${ctx}admin/logs/deleteall.action')"/>
	</div>
	
	<table width="100%" cellpadding="2" cellspacing="0" class="pagerlist">
		<tr>
			<th width="70"><input type="checkbox" name="topcheckall" onclick="checkall(this)" id="topcheckall"/></th>
			
		    <th><@s.text name="logs.content"/></th>
		    
		    <th><@s.text name="logs.ipaddr"/></th>
		    
		    <th><@s.text name="logs.in_date"/></th>
		    
		    <th><@s.text name="logs.user_name"/></th>
		    
		</tr>
		
		<#list pagevo.list as logs>
		<tr>
			<td width="70"><input type="checkbox" name="logs.log_id" id="checkall_${logs_index+1}" value="${(logs.log_id)?if_exists}"/></td>
			
    		<td>${(logs.content)?if_exists}</td>
    		
    		<td>${(logs.ipaddr)?if_exists}</td>
    		
    		<td>${(logs.in_date)?if_exists}</td>
    		
    		<td>${(logs.user_name)?if_exists}</td>
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

