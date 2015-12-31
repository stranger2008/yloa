<html>
<head>
<title>角色修改</title>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#updateForm").validate({
			rules: {
				"role.role_name": {
					required: true,
					maxlength: 30
				},"role.menu_right": {
					required: false,
					maxlength: 10000
				},"role.oper_right": {
					required: false,
					maxlength: 10000
				},"role.remark": {
					required: false,
					maxlength: 200
				}
  			},
  			messages: {
  				"role.role_name": {
			    	required: "请输入<@s.text name="role.role_name"/>",
			    	maxlength: jQuery.format("<@s.text name="role.role_name"/>不能大于{0}个字符")
			    },"role.menu_right": {
			    	required: "请输入<@s.text name="role.menu_right"/>",
			    	maxlength: jQuery.format("<@s.text name="role.menu_right"/>不能大于{0}个字符")
			    },"role.oper_right": {
			    	required: "请输入<@s.text name="role.oper_right"/>",
			    	maxlength: jQuery.format("<@s.text name="role.oper_right"/>不能大于{0}个字符")
			    },"role.remark": {
			    	required: "请输入<@s.text name="role.remark"/>",
			    	maxlength: jQuery.format("<@s.text name="role.remark"/>不能大于{0}个字符")
			    }
  				
   			}
		});
		$("input[name='rr_checkall']").click( function () {
			if($(this).attr('checked')){
				$(".pagerlist :checkbox").attr('checked','true');
			}else{
				$(".pagerlist :checkbox").attr('checked','');
			}
		});
		$(".pagerlist input[id^='menu_']").click(function(){
			
			//得到当前点击复选框的ID
			var thisid = $(this).attr("id");
			var thisidstr = thisid.split("_");
			var tid = thisidstr[1];
			
			//选择当前菜单下的操作权限
			if($("#"+thisid).attr("checked")){
				$("input[id^='chright_"+tid+"']").attr("checked","true");
			}else{
				$("input[id^='chright_"+tid+"']").attr("checked","");
			}
			
			//选择当前菜单，用ajax到后台递归找到所有下级菜单，实现控制是否选择
			//且控制相应的下级菜单的操作权限复选框
			$.ajax({
				type:"post",
				url:"${ctx}admin/menu/getdownmenu.action",
				data:"up_menu_id_search="+tid,
				dataType:"html",
				success:function(data){
					var dstr = data.split(",");
					for(var i=0;i<dstr.length;i++){
						if(dstr[i] != ""){
							if($("#"+thisid).attr("checked")){
								$("#menu_"+dstr[i]).attr("checked","true");
								$("input[id^='chright_"+dstr[i]+"']").attr("checked","true");
							}else{
								$("#menu_"+dstr[i]).attr("checked","");
								$("input[id^='chright_"+dstr[i]+"']").attr("checked","");
							}
						}
					}
				}
			});
		});
	});
	
</script>
<@s.form action="${ctx}admin/role/update.action" method="post" id="updateForm">

	<#list rolerightList as roleright>
		<span id="right_${(roleright.menu_id)?if_exists}">
			<input type="checkbox" name="role.oper_right"  value="${(roleright.right_id)?if_exists}" id="chright_${(roleright.menu_id)?if_exists}_${roleright_index+1}"/>
			${(roleright.right_name)?if_exists}
		</span>
	</#list>
	
	

	<table width="100%" cellpadding="0" cellspacing="2" class="edittab">
		<tr><td><h2>角色修改</h2></td></tr>
		
		
		<tr><th><@s.text name="role.role_name"/>：<font>*</font></th></tr>
		<tr><td>
			<@s.textfield name="role.role_name"/>
		</td></tr>
		
		<tr><th><@s.text name="role.menu_right"/>/<@s.text name="role.oper_right"/>：</th></tr>
		<tr><td>
			<input type="checkbox" name="rr_checkall"/>全选
			<table width="100%" cellpadding="2" cellspacing="0" class="pagerlist">
			<#list menuList as menu>
			<tr id="${(menu.menu_level)?if_exists}_${(menu.menu_id)?if_exists}_${(menu.up_menu_id)?if_exists}">
	    		<td id="td_${(menu.menu_id)?if_exists}">
	    			<input type="checkbox" name="role.menu_right" value="${(menu.menu_id)?if_exists}" id="menu_${(menu.menu_id)?if_exists}"/>
	    			${(menu.menu_name)?if_exists}
	    		</td>
			</tr>
			</#list>
			
			<#list menuList as menu>
			<script>
				$($(".pagerlist tr[id$='${(menu.menu_id)?if_exists}']")).insertAfter("#${(menu.menu_level)?if_exists}_${(menu.menu_id)?if_exists}_${(menu.up_menu_id)?if_exists}");
				var menu_level = ${(menu.menu_level)?if_exists};
				if(menu_level > 1){
					$("#td_${(menu.menu_id)?if_exists}").css("padding-left", ((menu_level-1)*20)+"px");
				}
				$($("span[id='right_${(menu.menu_id)?if_exists}']")).appendTo("#td_${(menu.menu_id)?if_exists}");
			</script>
			</#list>
			</table>
		</td></tr>
		
		<tr><th><@s.text name="role.remark"/>：</th></tr>
		<tr><td>
			<@s.textarea name="role.remark"/>
		</td></tr>
		
		
		<tr><td>
			<@s.hidden name="role.role_id" />
			${listSearchHiddenField?if_exists}
			<input type="submit" class="tjbutton" value="提交" />
		</td></tr>
	</table>
</@s.form>

<script>
	//回选菜单权限和操作权限
	function showmenuright(){
		var mright = "${(role.menu_right)?if_exists}";
		var dstr = mright.split(",");
		for(var i=0;i<dstr.length;i++){
			if(dstr[i] != ""){
				$("#menu_"+dstr[i]).attr("checked","true");
			}
		}
		
		var oright = "${(role.oper_right)?if_exists}";
		var ostr = oright.split(",");
		for(var i=0;i<ostr.length;i++){
			if(ostr[i] != ""){
				$("input[value='"+ostr[i]+"']").attr("checked","true");
			}
		}
	}
	showmenuright();
</script>

</body>
</html>

