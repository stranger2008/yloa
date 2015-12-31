package com.lll.webapp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.lll.common.Constants;
import com.lll.common.Md5;
import com.lll.common.util.SessionUtil;
import com.lll.model.User;
import com.lll.service.IOrganizeService;
import com.lll.service.IPositionService;
import com.lll.service.IRoleService;
import com.lll.service.IUserService;

@Results( { 
	@Result(name = "adminlog", location = "/WEB-INF/pages/admin.ftl"),
	@Result(name = "adminindex", location = "/WEB-INF/pages/admin/index.ftl"),
	@Result(name = "index", location = "/WEB-INF/pages/admin/user/index.ftl"),
	@Result(name = "view", location = "/WEB-INF/pages/admin/user/update.ftl"),
	@Result(name = "add", location = "/WEB-INF/pages/admin/user/insert.ftl")
})
public class UserAction extends BaseAction {

	private static final long serialVersionUID = -467510686930191018L;

	@Autowired
	public IUserService userService;
	@Autowired
	public IPositionService positionService;
	@Autowired
	private IOrganizeService organizeService;
	@Autowired
	private IRoleService roleService;
	
	public User user;
	
	public String imgcode;
	
	public List positionList;
	
	public String orgSelect;
	
	public String oneLevelOrgId = "1111111111";
	
	public List roleList;
	
	public String user_name_search,role_id_search,org_id_search,state_code_search,start_date_search,end_date_search;

	@Action("admin")
	public String gopage() throws IOException {
		String user_id = SessionUtil.get(request,Constants.USER_ID);
		if(!user_id.trim().equals("")){
			response.sendRedirect(ctx+"admin/manager/index.action");
		}
		return SUCCESS;
	}
	
	@Action("/admin/user/add")
	public String add() throws Exception {
		orgSelect = getDownSelect(oneLevelOrgId,"");
		positionList = positionService.getList(new HashMap());
		roleList = roleService.getList(new HashMap());
		return "add";
	}
	
	@Action("/admin/user/insert")
	public String insert() throws Exception {
		if(checkFormField(user,"save")){
			return Constants.ACTIONERROR;
		}
		user.setPasswd(Md5.getMD5(user.getPasswd().getBytes()));
		userService.insert(user);
		this.addActionMessage("用户新增成功");
		return list();
	}
	
	@Action("/admin/user/index")
	public String list() throws Exception {
		Map map = new HashMap();
		
		if(!StringUtils.isBlank(user_name_search)){
			map.put("user_name", user_name_search);
		}
		if(!StringUtils.isBlank(role_id_search)){
			map.put("role_id", role_id_search);
		}
		if(!StringUtils.isBlank(org_id_search)){
			map.put("org_id", org_id_search);
		}
		if(!StringUtils.isBlank(state_code_search)){
			map.put("state_code", state_code_search);
		}
		if(!StringUtils.isBlank(start_date_search)){
			map.put("start_date", start_date_search);
		}
		if(!StringUtils.isBlank(end_date_search)){
			map.put("end_date", end_date_search);
		}
		
		map = this.pageTool(map);
		this.pagevo = userService.getPageList(map);
		
		orgSelect = getDownSelect(oneLevelOrgId,this.org_id_search);
		roleList = roleService.getList(new HashMap());
		
		return "index";
	}
	
	@Action("/admin/user/view")
	public String view() throws Exception {
		if(checkFormField(user,"view")){
			return Constants.ACTIONERROR;
		}
		positionList = positionService.getList(new HashMap());
		String user_id = user.getUser_id();
		user = userService.get(user_id);
		if(user != null){
			orgSelect = getDownSelect(oneLevelOrgId,user.getOrg_id());
		}
		roleList = roleService.getList(new HashMap());
		return "view";
	}
	
	@Action("/admin/user/update")
	public String update() throws Exception {
		if(checkFormField(user,"save")){
			return Constants.ACTIONERROR;
		}
		if(StringUtils.isBlank(user.getPasswd())){
			user.setPasswd(null);
		}else{
			user.setPasswd(Md5.getMD5(user.getPasswd().getBytes()));
		}
		userService.update(user);
		this.addActionMessage("用户修改成功");
		return list();
	}
	
	@Action("/admin/user/delete")
	public String delete() throws Exception {
		if(checkFormField(user,"view")){
			return Constants.ACTIONERROR;
		}
		String user_id = user.getUser_id();
		userService.delete(user_id);
		this.addActionMessage("用户删除成功");
		return list();
	}
	
	@Action("checkusername")
	public void checkusername() throws IOException {
		String user_name = "";
		if(request.getParameter("user_name") != null){
			user_name = request.getParameter("user_name");
		}
		
		Map map = new HashMap();
		map.put("user_name", user_name);
		List userList = userService.getList(map);
		
		if(userList != null && userList.size()>0){
			response.getWriter().print("true");
		}else{
			response.getWriter().print("false");
		}
	}
	
	@Action("checkpasswd")
	public void checkpasswd() throws IOException {
		String user_name = "",passwd = "";
		if(request.getParameter("user_name") != null){
			user_name = request.getParameter("user_name");
		}
		if(request.getParameter("passwd") != null){
			passwd = request.getParameter("passwd");
			passwd = Md5.getMD5(passwd.getBytes());
		}
		
		Map map = new HashMap();
		map.put("user_name", user_name);
		map.put("passwd", passwd);
		List userList = userService.getList(map);
		
		if(userList != null && userList.size()>0){
			response.getWriter().print("true");
		}else{
			response.getWriter().print("false");
		}
	}
	
	@Action("/admin/manager/index")
	public String goadminindex() throws Exception {
		return "adminindex";
	}
	
	@Action("adminlogout")
	public String adminlogout() throws Exception {
		this.addActionMessage("用户登出成功");
		SessionUtil.invalidate(request);
		return "adminlog";
	}
	
	@Action("adminlogin")
	public String login() throws Exception {
		if(checkFormField(user,"login")){
			return Constants.ACTIONERROR;
		}
		String user_name = user.getUser_name();
		String passwd = user.getPasswd();
		passwd = Md5.getMD5(passwd.getBytes());
		String imgcode = this.imgcode;
		
		Map map = new HashMap();
		map.put("user_name", user_name);
		List userList = userService.getList(map);
		
		if(userList == null || userList.size() == 0){
			this.addActionError("用户名不存在");
			return Constants.ACTIONERROR;
		}
		
		map.put("passwd", passwd);
		userList = userService.getList(map);
		if(userList == null || userList.size() == 0){
			this.addActionError("密码不正确");
			return Constants.ACTIONERROR;
		}
		
		if(imgcode == null || imgcode.equals("")){
			this.addActionError("请输入验证码");
			return Constants.ACTIONERROR;
		}
		String syscode = SessionUtil.get(request,"syscode");
		if(!imgcode.equals(syscode)){
			this.addActionError("验证码不正确");
			return Constants.ACTIONERROR;
		}
		
		Map userMap = (HashMap)userList.get(0);
		String user_id = "",state_code = "",menu_right = "",oper_right = "",user_type = "";
		if(userMap.get("user_id") != null){
			user_id = userMap.get("user_id").toString();
		}
		if(userMap.get("state_code") != null){
			state_code = userMap.get("state_code").toString();
		}
		if(userMap.get("menu_right") != null){
			menu_right = userMap.get("menu_right").toString();
		}
		if(userMap.get("oper_right") != null){
			oper_right = userMap.get("oper_right").toString();
		}
		if(userMap.get("user_type") != null){
			user_type = userMap.get("user_type").toString();
		}
		
		//state_code:1，正常 2，禁用
		if(state_code.equals("2")){
			this.addActionError("用户已禁用");
			return Constants.ACTIONERROR;
		}
		
		SessionUtil.put(request, Constants.USER_ID, user_id);
		SessionUtil.put(request, Constants.USER_NAME, user_name);
		//菜单权限
		SessionUtil.put(request, Constants.MENU_RIGHT, menu_right);
		//操作权限
		SessionUtil.put(request, Constants.OPER_RIGHT, oper_right);
		//user_type 0:普通管理员 1：超级管理员 
		//超级管理员不权限控制，拥有所有菜单和操作权限
		SessionUtil.put(request, Constants.USER_TYPE, user_type);
		
		this.addActionMessage("用户登入成功");
		response.sendRedirect(this.ctx+"admin/manager/index.action");
		
		return null;
	}
	
	StringBuffer downSel = new StringBuffer();
	
	public String getDownSelect(String up_org_id,String this_org_id){
		Map oMap = new HashMap();
		oMap.put("up_org_id", up_org_id);
		List list = organizeService.getList(oMap);
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				HashMap mMap = (HashMap)list.get(i);
				String org_id = "",org_name = "",org_level = "";
				if(mMap.get("org_id") != null){
					org_id = mMap.get("org_id").toString();
				}
				if(mMap.get("org_name") != null){
					org_name = mMap.get("org_name").toString();
				}
				if(mMap.get("org_level") != null){
					org_level = mMap.get("org_level").toString();
				}
				String m_sel = "";
				if(this_org_id != null && this_org_id.equals(org_id)){
					m_sel = " selected=\"true\" ";
				}
				downSel.append("<option value='"+org_id+"' "+m_sel+">"+getLineStr(org_level)+org_name+"</option>\n");
				getDownSelect(org_id,this_org_id);
			}
		}
		return downSel.toString();
	}
	
	public String getLineStr(String menu_level){
		int mLevel = Integer.parseInt(menu_level);
		String s = "";
		for(int i=0;i<mLevel;i++){
			s += "-";
		}
		return s;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getImgcode() {
		return imgcode;
	}

	public void setImgcode(String imgcode) {
		this.imgcode = imgcode;
	}

	public String getUser_name_search() {
		return user_name_search;
	}

	public void setUser_name_search(String user_name_search) {
		this.user_name_search = user_name_search;
	}

	public String getRole_id_search() {
		return role_id_search;
	}

	public void setRole_id_search(String role_id_search) {
		this.role_id_search = role_id_search;
	}

	public String getOrg_id_search() {
		return org_id_search;
	}

	public void setOrg_id_search(String org_id_search) {
		this.org_id_search = org_id_search;
	}

	public String getState_code_search() {
		return state_code_search;
	}

	public void setState_code_search(String state_code_search) {
		this.state_code_search = state_code_search;
	}

	public String getStart_date_search() {
		return start_date_search;
	}

	public void setStart_date_search(String start_date_search) {
		this.start_date_search = start_date_search;
	}

	public String getEnd_date_search() {
		return end_date_search;
	}

	public void setEnd_date_search(String end_date_search) {
		this.end_date_search = end_date_search;
	}
	
}
