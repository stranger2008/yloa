package com.lll.common.codeBuild;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lll.common.util.DbUtil;
import com.lll.common.util.FileUtil;
import com.lll.common.util.PropertiesUtil;
import com.lll.common.util.RandomStrUtil;
import com.lll.model.Menu;
import com.lll.model.Roleright;
import com.lll.service.IMenuService;
import com.lll.service.IRolerightService;
import com.lll.webapp.function.CreateSpringContext;

/**
 * 功能：生成本框架的基础代码，包括java类，xml文件，前台文件
 * date:2011-07-10
 * @author 李良林
 *
 */

public class CodeEngine {
	
	//表明前缀
	private String DBPREFIX = "yl_";
	
	//数据库表名
	private String TABLENAME = "config",TABLEKEY = "val_id",FUNNAME = "系统参数值",AUTHOR = "李良林";
	
	//列表显示字段，列表搜索字段
	private String INDEXDISFIELD = "val_key,val_value",SEARCHFIELD = "val_key,val_value",UPMENUID = "1121211112";
	
	//表名第一个字母大写
	private final String CLASSNAME;
	
	//主键第一个字母大写
	private final String TABLEKEYUPPER;
	
	//当前日期
	private final String DATE;
	
	//项目的根目录
	private static String rootpath = PropertiesUtil.getRootpath().replace("WebRoot/", "");
	
	//模板路径
	private static String templatePath = Constants.TEMPLATEPATH;
	
	//文件工具类
	private FileUtil fileUtil;
	
	//数据库工具类
	private DbUtil dbOperate;
	
	//替换内容
	private String fileContent;
	
	public CodeEngine(){
		CLASSNAME = oneWordUpper(TABLENAME);
		TABLEKEYUPPER = oneWordUpper(TABLEKEY);
		DATE = getDate();
		fileUtil = new FileUtil();
		dbOperate = new DbUtil();
	}
	
	/*
	 * 主入口方法
	 */
	public static void main(String args[]){
		
		CodeEngine codeEngine = new CodeEngine();
		//codeEngine.delete();
		codeEngine.generate();
	}
	
	public void delete(){
		String className = oneWordUpper(TABLENAME);
		//java类、ibatis sql文件自动生成
		fileUtil.delFile(Constants.ACTION_SAVEPATH+File.separator+className+"Action.java");
		fileUtil.delFile(Constants.DAO_SAVEPATH+File.separator+"I"+className+"Dao.java");
		fileUtil.delFile(Constants.DAOIMPL_SAVEPATH+File.separator+className+"Dao.java");
		fileUtil.delFile(Constants.SERVICE_SAVEPATH+File.separator+"I"+className+"Service.java");
		fileUtil.delFile(Constants.SERVICEIMPL_SAVEPATH+File.separator+className+"Service.java");
		fileUtil.delFile(Constants.POJO_SAVEPATH+File.separator+className+".java");
		fileUtil.delFile(Constants.SQLMAP_SAVEPATH+File.separator+TABLENAME+".xml");
		fileUtil.delFolder(Constants.PAGE_PATH+CLASSNAME);
		createSqlMapFile("sqlmapxml.txt",Constants.SQL_PATH,"1");
		createSqlMapFile("validate.txt",Constants.VALIDATE_PATH,"1");
		//删除数据库数据
		deleteDbData();
		deleteMessageFile("message.txt",Constants.MESSAGE_PATH);
	}
	
	//开始生成代码
	public void generate(){
		String className = oneWordUpper(TABLENAME);
		//java类、ibatis sql文件自动生成
		createFile("actionClass.txt",className+"Action.java",Constants.ACTION_SAVEPATH);
		createFile("daoInterface.txt","I"+className+"Dao.java",Constants.DAO_SAVEPATH);
		createFile("daoImpl.txt",className+"Dao.java",Constants.DAOIMPL_SAVEPATH);
		createFile("serviceInterface.txt","I"+className+"Service.java",Constants.SERVICE_SAVEPATH);
		createFile("serviceImpl.txt",className+"Service.java",Constants.SERVICEIMPL_SAVEPATH);
		createFile("pojoClass.txt",className+".java",Constants.POJO_SAVEPATH);
		createFile("sqlmap.txt",TABLENAME+".xml",Constants.SQLMAP_SAVEPATH);			
		createSqlMapFile("sqlmapxml.txt",Constants.SQL_PATH,"");
		createSqlMapFile("validate.txt",Constants.VALIDATE_PATH,"");
		//运营商后台页面
		createFoldFile(Constants.PAGE_PATH,"index.txt","index.ftl");
		createFoldFile(Constants.PAGE_PATH,"insert.txt","insert.ftl");
		createFoldFile(Constants.PAGE_PATH,"update.txt","update.ftl");
		//插入数据库数据
		insertDbData();
		createMessageFile("message.txt",Constants.MESSAGE_PATH);
	}
	
	public void deleteDbData(){
		IMenuService menuService = (IMenuService)CreateSpringContext.getContext().getBean("menuService");
		IRolerightService rolerightService = (IRolerightService)CreateSpringContext.getContext().getBean("rolerightService");
		String menu_name = this.FUNNAME + "管理";
		Map mMap = new HashMap();
		mMap.put("menu_name", menu_name);
		List mList = menuService.getList(mMap);
		if(mList != null && mList.size() > 0){
			Map tMap = (HashMap)mList.get(0);
			String menu_id = "";
			if(tMap.get("menu_id") != null){
				menu_id = tMap.get("menu_id").toString();
				menuService.delete(menu_id);
				rolerightService.deleteByMenuId(menu_id);
			}
		}
	}
	
	public void insertDbData(){
		//插入菜单表数据
		IMenuService menuService = (IMenuService)CreateSpringContext.getContext().getBean("menuService");
		Menu menu = menuService.get(this.UPMENUID);
		String mLevel = menu.getMenu_level();
		String menu_id = "",menu_name = "",syscode = "",up_menu_id = "",menu_level = "",sort_no = "",url = "",target = "";
		target = "_self";
		sort_no = "0";
		up_menu_id = this.UPMENUID;
		syscode = "sys";
		menu_id = RandomStrUtil.getNumberRand();
		menu_name = this.FUNNAME + "管理";
		menu_level = String.valueOf(Integer.parseInt(mLevel)+1);
		url = "admin/"+TABLENAME+"/index.action";
		Menu menu1 = new Menu();
		menu1.setMenu_id(menu_id);
		menu1.setMenu_name(menu_name);
		menu1.setSyscode(syscode);
		menu1.setUp_menu_id(up_menu_id);
		menu1.setMenu_level(menu_level);
		menu1.setSort_no(sort_no);
		menu1.setUrl(url);
		menu1.setTarget(target);
		menuService.insert(menu1);
		
		//插入权限数据表
		IRolerightService rolerightService = (IRolerightService)CreateSpringContext.getContext().getBean("rolerightService");
		Roleright update = new Roleright();
		update.setRight_id(RandomStrUtil.getNumberRand());
		update.setRight_name(this.FUNNAME+"修改");
		update.setMenu_id(menu_id);
		update.setUrl("/admin/"+TABLENAME+"/view.action,/admin/"+TABLENAME+"/update.action");
		update.setType("1");
		update.setRemark("");
		rolerightService.insert(update);
		
		Roleright insert = new Roleright();
		insert.setRight_id(RandomStrUtil.getNumberRand());
		insert.setRight_name(this.FUNNAME+"新增");
		insert.setMenu_id(menu_id);
		insert.setUrl("/admin/"+TABLENAME+"/add.action,/admin/"+TABLENAME+"/insert.action");
		insert.setType("0");
		insert.setRemark("");
		rolerightService.insert(insert);
		
		Roleright index = new Roleright();
		index.setRight_id(RandomStrUtil.getNumberRand());
		index.setRight_name(this.FUNNAME+"列表");
		index.setMenu_id(menu_id);
		index.setUrl("/admin/"+TABLENAME+"/index.action");
		index.setType("0");
		index.setRemark("");
		rolerightService.insert(index);
		
		Roleright delete = new Roleright();
		delete.setRight_id(RandomStrUtil.getNumberRand());
		delete.setRight_name(this.FUNNAME+"删除");
		delete.setMenu_id(menu_id);
		delete.setUrl("/admin/"+TABLENAME+"/delete.action");
		delete.setType("1");
		delete.setRemark("");
		rolerightService.insert(delete);
	}
	
	public void createFoldFile(String creathPath,String templateName,String fileName){
		//模板的详细地址
		String file_path = creathPath+TABLENAME;
		//创建文件夹
		String path=fileUtil.createFolder(file_path);
		//模板的详细地址
		String template_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(template_path);
		//替换模板中的标签为具体代码
		fileCon = replaceTemplate(fileCon);
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(file_path,fileName,fileCon);
		System.out.println(file_path+"/"+fileName+"文件生成成功");
	}
	
	public void deleteMessageFile(String templateName,String sqlMapFile){
		String oldCon= fileUtil.readTxt(sqlMapFile);
		String[] constr = oldCon.split("\\n");
		StringBuffer newCon = new StringBuffer();
		for(int i=0;i<constr.length;i++){
			if(constr[i].indexOf(this.TABLENAME+".") > -1){
				continue;
			}
			newCon.append(constr[i]+"\n");
		}
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(sqlMapFile,"", newCon.toString());
		System.out.println(sqlMapFile+"文件内容删除成功");
	}
	
	public void createMessageFile(String templateName,String sqlMapFile){
		//模板的详细地址
		String file_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(file_path);
		//替换模板中的标签为具体代码
		fileContent = replaceTemplate(fileCon);
		String oldCon= fileUtil.readTxt(sqlMapFile);
		oldCon = oldCon + fileContent;
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(sqlMapFile,"", oldCon);
		System.out.println(sqlMapFile+"文件更新成功");
	}
	
	public void createSqlMapFile(String templateName,String sqlMapFile,String type){
		//模板的详细地址
		String file_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(file_path);
		//替换模板中的标签为具体代码
		fileContent = replaceTemplate(fileCon);
		String oldCon= fileUtil.readTxt(sqlMapFile);
		if(type.equals("1")){
			if(oldCon.indexOf(fileContent) > -1){
				oldCon = oldCon.replace(fileContent, "");
			}
		}else{
			if(oldCon.indexOf(fileContent)==-1){
				oldCon = replaceTemplate(oldCon);
			}
		}
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(sqlMapFile,"", oldCon);
		System.out.println(sqlMapFile+"文件更新成功");
	}
	
	//生成所有表的验证规则
	public void createAllTableValidateXml(){
		
		DbUtil dbUtil = new DbUtil();
		String dbName = dbUtil.getDbName();
		List tList = dbUtil.queryList("SHOW TABLES;");
		StringBuffer sb = new StringBuffer();
		if(tList!=null && tList.size()>0){
			for(int j=0;j<tList.size();j++){
				HashMap tMap = (HashMap)tList.get(j);
				String tableName = "";
				if(tMap.get("Tables_in_"+dbName)!=null){
					tableName = tMap.get("Tables_in_"+dbName).toString();
				}
				sb.append("");
			}
		}	
	}
	
	public void addXmlCode(String templateName,String xmlName){
		//模板的详细地址
		String file_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(file_path);
		//替换模板中的标签为具体代码
		fileCon = replaceTemplate(fileCon);
		
		//得到配置文件的绝对路径
		String xml_path = rootpath+Constants.SRC_PATH+xmlName;
		//得到配置文件的内容
		String xmlFileCon = fileUtil.readTxt(xml_path);
		//替换模板中的标签为具体代码
		xmlFileCon = xmlFileCon.replace(Constants.XML_TAGBODY,fileCon+Constants.XML_TAGBODY);
		
		fileUtil.writeTxt(rootpath+Constants.SRC_PATH,xmlName, xmlFileCon);
		System.out.println(xml_path+"文件更新成功");
	}
	
	
	/*
	 * templateName:模板名称
	 * fileName：生成后的文件名
	 * fileCon：文件内容
	 * filePath：生成后的文件存放地址
	 */
	public void createFile(String templateName,String fileName,String filePath){
		//模板的详细地址
		String file_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(file_path);
		//替换模板中的标签为具体代码
		fileCon = replaceTemplate(fileCon);
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(rootpath+filePath,fileName, fileCon);
		System.out.println(rootpath+filePath+"/"+fileName+"文件生成成功");
	}
	
	public String replaceTemplate(String fileCon){
		fileCon = fileCon.replace("{DBPREFIX}", DBPREFIX);
		fileCon = fileCon.replace("{TABLENAME}", TABLENAME);
		fileCon = fileCon.replace("{CLASSNAME}", CLASSNAME);
		fileCon = fileCon.replace("{FUNNAME}", FUNNAME);
		fileCon = fileCon.replace("{AUTHOR}", AUTHOR);
		fileCon = fileCon.replace("{DATE}", DATE);
		fileCon = fileCon.replace("{TABLEKEYUPPER}", TABLEKEYUPPER);
		fileCon = fileCon.replace("{TABLEKEY}", TABLEKEY);
		fileCon = fileCon.replace("</sqlMapConfig>", fileContent+"</sqlMapConfig>");	
		fileCon = fileCon.replace("</validatebody>", fileContent+"</validatebody>");
		
		String tagname = "fieldlist";
		while(fileCon.indexOf(tagname) > -1){
			fileCon = replaceLoopField(fileCon,tagname);
		}
		tagname = "fieldkeylist";
		while(fileCon.indexOf(tagname) > -1){
			fileCon = replaceLoopField(fileCon,tagname);
		}
		tagname = "searchlist";
		while(fileCon.indexOf(tagname) > -1){
			fileCon = replaceLoopField(fileCon,tagname);
		}
		tagname = "indexdislist";
		while(fileCon.indexOf(tagname) > -1){
			fileCon = replaceLoopField(fileCon,tagname);
		}
		return fileCon;
	}
	
	//循环替换表字段
	public String replaceLoopField(String fileCon,String tagname){
		String startTag = "{"+tagname+"}";
		String enTag = "{/"+tagname+"}";
		if(fileCon.indexOf(startTag) == -1) return fileCon;
		int i = fileCon.indexOf(startTag);
		int j = fileCon.indexOf(enTag)+enTag.length();
		//得到标签体
		String tagBody = fileCon.substring(i,j);
		String bodyCon = tagBody.replace(startTag, "").replace(enTag, "");
		
		//取出此表数据库描述
		ArrayList fieldList = dbOperate.getTableDescList(this.DBPREFIX+TABLENAME);
		
		StringBuffer sb = new StringBuffer();
		
		if(fieldList!=null && fieldList.size()>0){
			HashMap fieldMap = new HashMap();
			for(int k=0;k<fieldList.size();k++){
				fieldMap = (HashMap)fieldList.get(k);
				String field = "",Null = "",Type = "",Key = "";
				if(fieldMap.get("field")!=null){
					field = fieldMap.get("field").toString();
				}
				if(fieldMap.get("Null")!=null){
					Null = fieldMap.get("Null").toString();
				}
				if(fieldMap.get("Type")!=null){
					Type = fieldMap.get("Type").toString();
				}
				if(fieldMap.get("Key")!=null){
					Key = fieldMap.get("Key").toString();
				}
				
				if(field.equals("")) continue;
				if(tagname.equals("fieldlist") && field.equals(TABLEKEY)){
					continue;
				}
				if(tagname.equals("searchlist") && !isExistGroup(SEARCHFIELD,field)){
					continue;
				}
				if(tagname.equals("indexdislist") && !isExistGroup(INDEXDISFIELD,field)){
					continue;
				}
				
				String type = "",length = "";
				int pos = Type.indexOf("(");
				if(pos == -1){
					type = Type;
					length = "10000";
				}else{
					type = Type.substring(0,pos);
					length = Type.replace(type, "").replace("(", "").replace(")", "");
				}
				String texttype = "textfield";
				if(type.equals("varchar") && Integer.parseInt(length) >= 300){
					texttype = "textarea";
				}
				if(type.equals("mediumint")){
					type = "int";
				}
				if(type.equals("char") || type.equals("varchar") || type.equals("text")){
					type = "string";
				}
				
				String temp = bodyCon;
				//直接替换字段
				temp = temp.replace("[field_name]", field);
				//替换第一个字母大写的字段
				temp = temp.replace("[fieldname]", oneWordUpper(field));
				temp = temp.replace("[field_name_length]", length);
				temp = temp.replace("[field_name_type]", type);
				temp = temp.replace("[field_text_type]", texttype);
				//新增修改页面替换是否必填*号
				temp = temp.replace("[field_name_must]", Null.equals("NO")?"<font>*</font>":"");
				temp = temp.replace("[field_name_required]", Null.equals("NO")?"true":"false");
				//替换SQLMap.txt
				if(k==fieldList.size()-1){
					temp = temp.replace("[field_node]", "");	
				}else{
					temp = temp.replace("[field_node]", ",");	
				}
				
				sb.append(temp);
			}
		}
		fileCon = fileCon.replace(tagBody, sb.toString());
		
		return fileCon;
	}
	
	//让传进来的字符串第一个字母大写
	public String oneWordUpper(String str){
		if(str.length()==0) return "";
		String oneWord = str.substring(0,1);
		return oneWord.toUpperCase()+str.substring(1,str.length());
	}
	
	//得到当前的日期
	public String getDate(){
		return new java.util.Date().toString();
	}
	
	public boolean isExistGroup(String fieldGro,String field){
		String fid[] = fieldGro.split(",");
		boolean ret = false;
		for(int i=0;i<fid.length;i++){
			if(fid[i].equals(field)){
				ret = true;
				break;
			}
		}
		return ret;
	}
	
}
