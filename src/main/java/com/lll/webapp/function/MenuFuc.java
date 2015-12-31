package com.lll.webapp.function;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lll.common.util.SessionUtil;
import com.lll.model.Menu;
import com.lll.service.IMenuService;

/**
 * @function 功能  菜单管理
 * @author  创建人 李良林
 * @date  创建日期  2011-09-28
 */

public class MenuFuc extends CreateSpringContext{
	
	//从显示的一级菜单里找出第一个菜单
	public static String getOneMenu(List oneMenuList,HttpServletRequest request){
		IMenuService menuService = (IMenuService)getContext().getBean("menuService");
		String one_menu_id = "";
		if(request.getParameter("one_menu_id") != null){
			one_menu_id = request.getParameter("one_menu_id");
		}else if(!SessionUtil.get(request,"one_menu_id").equals("")){
			one_menu_id = SessionUtil.get(request,"one_menu_id");
		}else{
			one_menu_id = menuService.getFirstMenuByList(oneMenuList);
		}
		SessionUtil.put(request, "one_menu_id", one_menu_id);
		return one_menu_id;
	}
	
	//获取管理列表的搜索字段，放入隐藏表单中，以便进入修改页面修改信息返回列表后，搜索条件不变
	public static String getListSearchHiddenField(HttpServletRequest request){
		Enumeration reqParamNames = request.getParameterNames();   
		StringBuffer sb = new StringBuffer();
	    while(reqParamNames.hasMoreElements()){
	    	String fieldName  = (String)reqParamNames.nextElement();
	    	String fieldValue = "";
	    	if(request.getParameter(fieldName)!=null){
	    		fieldValue = request.getParameter(fieldName);
	    	}
	    	if(fieldName.endsWith("_search")){
	    		sb.append("<input type='hidden' name='"+fieldName+"' value='"+fieldValue+"'/>\n");
	    	}
	    }
	    return sb.toString();
	}
	
	static String locationStr = "";
	
	//根据操作权限和菜单数据生成管理后台的网页位置导航
	public static String getNavString(Map rMap,String ctx){
		StringBuffer sb = new StringBuffer();
		String right_name = "",menu_id = "",url = "",type = "",right_id = "";
		if(rMap.get("right_id") != null){
			right_id = rMap.get("right_id").toString();
		}
		if(rMap.get("right_name") != null){
			right_name = rMap.get("right_name").toString();
		}
		if(rMap.get("menu_id") != null){
			menu_id = rMap.get("menu_id").toString();
		}
		if(rMap.get("url") != null){
			url = rMap.get("url").toString();
		}
		if(rMap.get("type") != null){
			type = rMap.get("type").toString();
		}
		
		if(!menu_id.equals("")){
			sb.append(getMenuLocation(menu_id));
		}
		locationStr = "";
		if(!right_name.equals("")){
			sb.append(" > "+right_name);
		}
		if(type.equals("0")){
			if(url.indexOf(",") > -1) {
				url = url.substring(0, url.indexOf(","));
			}
			sb.append("<a href=\"###\" onclick=\"addoperate('"+right_id+"');\">[+]</a>");
		}
		
		return sb.toString();
	}
	
	public static String getMenuLocation(String menu_id){
		IMenuService menuService = (IMenuService)getContext().getBean("menuService");
		Menu menu = menuService.get(menu_id);
		if(menu != null){
			String menu_name = menu.getMenu_name();
			String menu_level = menu.getMenu_level();
			String up_menu_id = menu.getUp_menu_id();
			String jhao = " > ";
			if(menu_level.equals("1")){
				jhao = "";
			}
			locationStr = jhao + menu_name  + locationStr;
			if(!menu_level.equals("1")){
				getMenuLocation(up_menu_id);
			}
		}
		return locationStr;
	}
}
