package com.lll.webapp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.lll.common.Constants;
import com.lll.common.util.RandomStrUtil;
import com.lll.model.Menu;
import com.lll.service.IMenuService;

@Results( { 
	@Result(name = "view", location = "/WEB-INF/pages/admin/menu/update.ftl"),
	@Result(name = "index", location = "/WEB-INF/pages/admin/menu/index.ftl"),
	@Result(name = "add", location = "/WEB-INF/pages/admin/menu/insert.ftl")
})
public class MenuAction extends BaseAction {

	private static final long serialVersionUID = 9118145742036205936L;
	
	@Autowired
	public IMenuService menuService;
	
	public List list;
	
	public Menu menu;
	
	public String menuSelect;
	
	public String oneLevelMneuId = "1111111111";
	
	public String menu_name_search;
	
	public String up_menu_id_search;
	
	public List menuList;
	
	@Action("/admin/menu/index")
	public String list() throws Exception {
		Map map = new HashMap();
		if(!StringUtils.isBlank(menu_name_search)){
			map.put("menu_name", menu_name_search);
		}
		this.menuList = menuService.getList(map);
		return "index";
	}
	
	@Action("/admin/menu/add")
	public String add() throws Exception {
		menuSelect = getDownSelect(oneLevelMneuId,"");
		return "add";
	}
	
	StringBuffer downMenuString = new StringBuffer();
	
	@Action("/admin/menu/getdownmenu")
	public String getDownMenuString() throws IOException{
		List list = menuService.getMenuListByUpmenuid(up_menu_id_search);
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				HashMap mMap = (HashMap)list.get(i);
				String menu_id = "";
				if(mMap.get("menu_id") != null){
					menu_id = mMap.get("menu_id").toString();
					this.up_menu_id_search = menu_id;
				}
				downMenuString.append(menu_id+",");
				getDownMenuString();
			}
		}
		response.getWriter().print(downMenuString.toString());
		return null;
	}
	
	StringBuffer downSel = new StringBuffer();
	
	public String getDownSelect(String up_menu_id,String this_menu_id){
		List list = menuService.getMenuListByUpmenuid(up_menu_id);
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				HashMap mMap = (HashMap)list.get(i);
				String menu_id = "",menu_name = "",menu_level = "";
				if(mMap.get("menu_id") != null){
					menu_id = mMap.get("menu_id").toString();
				}
				if(mMap.get("menu_name") != null){
					menu_name = mMap.get("menu_name").toString();
				}
				if(mMap.get("menu_level") != null){
					menu_level = mMap.get("menu_level").toString();
				}
				String m_sel = "";
				if(this_menu_id != null && this_menu_id.equals(menu_id)){
					m_sel = " selected=\"true\" ";
				}
				downSel.append("<option value='"+menu_id+"' "+m_sel+">"+getLineStr(menu_level)+menu_name+"</option>\n");
				//三级菜单不允许有下级菜单
				if(menu_level.equals("2")){
					continue;
				}
				getDownSelect(menu_id,this_menu_id);
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
	
	@Action("/admin/menu/view")
	public String view() throws Exception {
		if(checkFormField(menu,"view")){
			return Constants.ACTIONERROR;
		}
		String menu_id = menu.getMenu_id();
		menu = menuService.get(menu_id);
		if(menu != null){
			menuSelect = getDownSelect(oneLevelMneuId,menu.getUp_menu_id());
		}
		return "view";
	}
	
	@Action("/admin/menu/delete")
	public String delete() throws Exception {
		if(checkFormField(menu,"view")){
			return Constants.ACTIONERROR;
		}
		String menu_id = menu.getMenu_id();
		menuService.delete(menu_id);
		this.addActionMessage("菜单删除成功");
		return list();
	}
	
	@Action("/admin/menu/sort")
	public String updateSort() throws Exception {
		String menu_id = menu.getMenu_id();
		String sort_no = menu.getSort_no();
		String menu_idStr[]=menu_id.split(",");
		String sort_noStr[]=sort_no.split(",");
		List ruleList=new ArrayList();
		if(menu_idStr.length>0){
			for(int i=0;i<menu_idStr.length;i++){
				Map ruleMap = new HashMap();
				String m_temp = menu_idStr[i].trim();
				String s_temp = sort_noStr[i].trim();
				if(!"".equals(m_temp) && !"".equals(s_temp)){
					ruleMap.put("menu_id", m_temp);
					ruleMap.put("sort_no", s_temp);
				}
				ruleList.add(ruleMap);
			}
		}
		this.menuService.updateSort(ruleList);
		this.addActionMessage("菜单排序成功");
		return list();
	}
	
	@Action("/admin/menu/update")
	public String update() throws Exception {
		if(checkFormField(menu,"save")){
			return Constants.ACTIONERROR;
		}
		menu.setMenu_level(getMenulevel(menu.getUp_menu_id()));
		menuService.update(menu);
		this.addActionMessage("菜单修改成功");
		return list();
	}
	
	@Action("/admin/menu/insert")
	public String insert() throws Exception {
		menu.setMenu_id(RandomStrUtil.getNumberRand());
		if(checkFormField(menu,"save")){
			return Constants.ACTIONERROR;
		}
		menu.setMenu_level(getMenulevel(menu.getUp_menu_id()));
		menuService.insert(menu);
		this.addActionMessage("菜单新增成功");
		return list();
	}
	
	public String getMenulevel(String menu_id){
		String menu_level = "";
		if(menu_id.equals(oneLevelMneuId)){
			menu_level = "1";
		}else{
			Menu dMenu = menuService.get(menu_id);
			menu_level = String.valueOf(Integer.parseInt(dMenu.getMenu_level())+1);
		}
		return menu_level;
	}
	
	@Action("/admin/menu/jumppage")
	public String jumpPage() throws Exception{
		String menu_id = "";
		if(request.getParameter("one_menu_id") != null){
			menu_id = request.getParameter("one_menu_id");
		}
		List downList = menuService.getMenuListByUpmenuid(menu_id);
		
		String menuUrl = "";
		if(downList != null && downList.size()>0){
			HashMap dMap = (HashMap)downList.get(0);
			String twoMenuUrl = "",twoMenuId = "";
			if(dMap.get("url") != null){
				twoMenuUrl = dMap.get("url").toString();
				menuUrl = twoMenuUrl;
			}
			if(dMap.get("menu_id") != null){
				twoMenuId = dMap.get("menu_id").toString();
			}
			List threeList = menuService.getMenuListByUpmenuid(twoMenuId);
			if(threeList != null && threeList.size()>0){
				HashMap tMap = (HashMap)threeList.get(0);
				String threeMenuUrl = "";
				if(dMap.get("url") != null){
					threeMenuUrl = dMap.get("url").toString();
					menuUrl = threeMenuUrl;
				}
			}
		}
		response.sendRedirect(ctx+menuUrl);
		return null;
	}
	

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getMenu_name_search() {
		return menu_name_search;
	}

	public void setMenu_name_search(String menu_name_search) {
		this.menu_name_search = menu_name_search;
	}

	public String getUp_menu_id_search() {
		return up_menu_id_search;
	}

	public void setUp_menu_id_search(String up_menu_id_search) {
		this.up_menu_id_search = up_menu_id_search;
	}
	
	
}
