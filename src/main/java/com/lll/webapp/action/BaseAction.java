package com.lll.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.lll.common.Constants;
import com.lll.common.page.PageVo;
import com.lll.common.util.IpseekUtil;
import com.lll.common.util.PropertiesUtil;
import com.lll.common.util.SessionUtil;
import com.lll.common.validate.ObjValidate;
import com.lll.model.Logs;
import com.lll.service.ILogsService;
import com.lll.service.IMenuService;
import com.lll.service.IOftenoperateService;
import com.lll.service.IRolerightService;
import com.lll.webapp.function.MenuFuc;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Results({
	@Result(name = "invalid.token", location = "/inc/token.jsp", type = "redirect"),
	@Result(name = "actionerror", location = "/WEB-INF/pages/actionerror.ftl")
})
@InterceptorRefs({
		@InterceptorRef(value = "token", params = { "includeMethods", "insert" }),
		@InterceptorRef("defaultStack")
})
public class BaseAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = -2982804752174965621L;

	HttpServletRequest request = ServletActionContext.getRequest();

	HttpServletResponse response = ServletActionContext.getResponse();

	public String ctx = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";

	//表单通用验证接口
	public boolean checkFormField(Object obj, String methodName) {
		String errorTip = ObjValidate.commonValidateField(obj, methodName);
		if (errorTip.equals("")) {
			return false;
		} else {
			super.addActionError(errorTip);
			return true;
		}
	}

	public List oneMenuList, twoMenuList, threeMenuList,oftenoperateList;

	@Autowired
	public IMenuService menuService;
	@Autowired
	private IRolerightService rolerightService;
	@Autowired
	private IOftenoperateService oftenoperateService;

	//一级菜单；保留搜索的域和值
	public String one_menu_id,left_menu_id,listSearchHiddenField,operate_location_path,actionmessage;

	public void prepare() throws Exception {
		//根据url判断是否是管理员后台，是管理员后台则查找菜单信息
		String url = request.getRequestURI();
		boolean urlfit = url.matches("\\/admin\\/[a-z]+\\/[a-z]+\\.action");
		if(urlfit){
			String user_id = SessionUtil.get(request,Constants.USER_ID);
			//未登录返回登录页面
			if(user_id.trim().equals("")){
				response.sendRedirect(ctx+"admin.action");
				return;
			}
			String menu_right = SessionUtil.get(request,Constants.MENU_RIGHT);
			String oper_right = SessionUtil.get(request,Constants.OPER_RIGHT);
			//user_type 0:普通管理员 1：超级管理员 
			//超级管理员不权限控制，拥有所有菜单和操作权限
			String user_type = SessionUtil.get(request,Constants.USER_TYPE);
			
			//根据url地址得到相应的操作权限ID
			Map rMap = rolerightService.getRightidByUrl(url);
			String right_id = "";
			if(rMap.get("right_id") != null){
				right_id = rMap.get("right_id").toString();
			}
			if(rMap.get("menu_id") != null){
				left_menu_id = rMap.get("menu_id").toString();
			}
			//超级管理员不参与控制
			if(!user_type.equals("1") && !right_id.equals("") && oper_right.indexOf(right_id) == -1){
				response.sendRedirect(ctx+"inc/rightcontrol.jsp");
				return;
			}
			
			//功能位置路径
			operate_location_path = MenuFuc.getNavString(rMap,ctx);
			
			//根据权限、帐号类型找出一级、二级、三级菜单
			oneMenuList = menuService.getOneMenuList(menu_right,user_type);
			one_menu_id = MenuFuc.getOneMenu(oneMenuList, request);
			twoMenuList = menuService.getTwomenuByRight(one_menu_id,menu_right,user_type);
			threeMenuList = menuService.getThreeMenuListByOneMenu(one_menu_id,menu_right,user_type);
			
			if(one_menu_id.equals(Constants.INDEX_MENUID)){
				oftenoperateList = oftenoperateService.getList(new HashMap());
			}
			
		}
		
		//保存request里的值
	    listSearchHiddenField = MenuFuc.getListSearchHiddenField(request);
	}

	// 当前页码
	public int pages_search = 1;
	public int pageSize_search = Constants.PAGESIZE;
	// 程序生成的分页字符串
	public PageVo pagevo;

	public Map pageTool(Map pageMap) {
		pageMap.put("start", pages_search);
		pageMap.put("limit",pageSize_search);
		return pageMap;
	}
	
	@Autowired
	private ILogsService logsService;
	
	public void addActionMessage(String aMessage){
		this.actionmessage = aMessage;
		//记录操作日志
		logsService.insert(new Logs(aMessage,IpseekUtil.getRealIp(request, response),SessionUtil.get(request, Constants.USER_NAME)));
	}
	
	public Map ylconfig = PropertiesUtil.getConfigMap();
	
}
