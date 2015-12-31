<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<#include "/WEB-INF/pages/jsinc.ftl">
<#include "/WEB-INF/pages/cssinc.ftl">
${head}
<title>${title}-管理中心</title>
</head>
<body>
<!--top开始-->
<table width="100%" class="top" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td class="logo">
            <h2>Yaling！</h2>
            <p>Control Panel</p>
        </td>
        <td>
            <table width="100%" cellpadding="0" cellspacing="0" border="0" class="navtable">
                <tr>
                    <td class="nav">
						<#list oneMenuList?if_exists as menu>
							<#if menu.menu_id != one_menu_id>
									<div class="noselli"><a href="${ctx}admin/menu/jumppage.action?one_menu_id=${(menu.menu_id)?if_exists}" target="${(menu.target)?if_exists}">${(menu.menu_name)?if_exists}</a></div>
									<div class="navbar"></div>
						    <#else>
						      		<div class="selectli_l"></div>
									<div class="selectli"><a href="${ctx}admin/menu/jumppage.action?one_menu_id=${(menu.menu_id)?if_exists}" target="${(menu.target)?if_exists}">${(menu.menu_name)?if_exists}</a></div>
									<div class="selectli_r"></div>
									<div class="navbar"></div>
						    </#if>
						</#list>
                    </td>
                    <td class="welcome">你好，创始人<b>${session_user_name?if_exists}</b><a href="${ctx}adminlogout.action">[退出]</a></td>
                </tr>
                <tr>
                    <td class="home">
                            ${operate_location_path?if_exists}
                    </td>
                    <td align="right" class="search">
                        <input type="text" class="sear_t" /><input type="button" class="tjbutton"  value="搜索"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>


<!--正文开始-->
<table width="100%" cellpadding="0" cellspacing="0" class="cont">
<tr>
    <!--左边导航开始-->
    <td class="lmenu" valign="top">
      <div>
        <ul>
			<#list twoMenuList?if_exists as menu>
				<li class="jiantwonav<#if menu.menu_id == left_menu_id?if_exists> libackg</#if>" onclick="showHide(this,'${(menu.menu_id)?if_exists}','${ctx}${(menu.url)?if_exists}')" id="mlevel_${(menu.menu_id)?if_exists}"><a href="##" target="${(menu.target)?if_exists}">${(menu.menu_name)?if_exists}</a></li>
			</#list>
			<#list threeMenuList?if_exists as menu>
				<li <#if menu.menu_id == left_menu_id?if_exists>class="libackg"</#if> id="${(menu.up_menu_id)?if_exists}_uid_${(menu.menu_id)?if_exists}"><a href="${ctx}${(menu.url)?if_exists}" target="${(menu.target)?if_exists}">${(menu.menu_name)?if_exists}</a></li>
			</#list>
			<#list oftenoperateList?if_exists as oftenoperate>
				<li><a href="${ctx}${(oftenoperate.oper_url)?if_exists}">${(oftenoperate.oper_name)?if_exists}</a></li>
			</#list>
        </ul>
      </div>

    </td>
	<td class="contbar"><span></span></td>

    <td class="right_cont" valign="top">
    	${body}
    </td>
        
</tr>    
</table>

<table width="100%" class="bottom" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td align="center">
			Powered by Yaling Oa! &nbsp; © 2013-2014 lilianglin Inc.
        </td>
    </tr>
</table>

<form id="commonForm" method="post">
	${listSearchHiddenField?if_exists}
</form>

</body>
</html>
<script type="text/javascript">
	$(document).ready(function(){
		//获取所有的id以mlevel_开头的li元素
		var ml = $("li[id^='mlevel_']");
		for(var i=0;i<ml.length;i++){
			var mlId_tmp = ml[i].id.replace('mlevel_','');
			var mldown = $("li[id^='"+mlId_tmp+"']");
			//没有下级则去掉样式
			if(mldown.length == 0){
				if($("#mlevel_"+mlId_tmp).attr("class").indexOf("libackg") > -1){
					$("#mlevel_"+mlId_tmp).attr("class","libackg");
				}else{
					$("#mlevel_"+mlId_tmp).removeClass();
				}
			}
			//存在下级则把相应的下级li移至上级li后面
			if(mldown.length > 0){
				for(var j=0;j<mldown.length;j++){
					$(mldown[j]).insertAfter("#mlevel_"+mlId_tmp);
				}
			}
		}
		
	});
	
	function showHide(val,menu_id,url){
		var mldown = $("li[id^='"+menu_id+"_uid']");
		if(mldown.length > 0){
			if(val.className == "jiantwonav"){
				val.className = "jiatwonav";
				$("li[id^='"+menu_id+"']").slideUp();
			}else{
				val.className = "jiantwonav";
				$("li[id^='"+menu_id+"']").slideDown();
			}
		}else{
			window.location.href=url;
		}
	}
	
	function showalert(actionMessage){
		if(actionMessage!=''){
	       	sAlert(actionMessage);
		}
	}
	showalert('${actionmessage?if_exists}');
	
	function addoperate(right_id){
		$.ajax({
			type:"post",
			url:"${ctx}admin/oftenoperate/addop.action",
			data:"right_id="+right_id,
			dataType:"html",
			success:function(data){
				var tipstr = "";
				if(data == "1"){
					tipstr = "常用操作重复添加";
				}else if(data == "2"){
					tipstr = "常用操作添加成功";
				}else{
					tipstr = "常用操作添加失败";
				}
				showalert(tipstr);
			}
		});
	}
	
</script>