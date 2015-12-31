package com.lll.common.codeBuild;

/**
 * 功能：存放代码生成工具所需要的所有常量
 * date:2011-07-10
 * @author 李良林
 *
 */
public class Constants {
	
	/*
	 * 链接数据库信息
	 */
	
	public static final String TYPE = "mysql";
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String URL = "jdbc:mysql://localhost:3306/llloa?useUnicode=true&characterEncoding=UTF-8";
	
	public static final String USERNAME = "root";
	
	public static final String PASSWD = "111111";
	
	//src根目录
	public static final String SRC_PATH = "src/";
	
	//源码根目录
	public static final String ROOT_SAVEPATH = SRC_PATH+"com/lll/";
	
	/*
	 * 模板路径
	 */
	public static final String TEMPLATEPATH = ROOT_SAVEPATH+"common/codeBuild/";
	
	/*
	 * 生成后文件存放的位置
	 */
	
	//action类存放的位置
	public static final String ACTION_SAVEPATH = ROOT_SAVEPATH+"webapp/action";
	
	//pojo类存放的位置
	public static final String POJO_SAVEPATH = ROOT_SAVEPATH+"model";
	
	//ibatis的sql.xml文件存放的位置
	public static final String SQLMAP_SAVEPATH = POJO_SAVEPATH+"/sql";
	
	//dao接口存放的位置
	public static final String DAO_SAVEPATH = ROOT_SAVEPATH+"dao";
	
	//dao实现类存放的位置
	public static final String DAOIMPL_SAVEPATH = DAO_SAVEPATH+"/impl";
	
	//service接口存放的位置
	public static final String SERVICE_SAVEPATH = ROOT_SAVEPATH+"service";
	
	//service实现类存放的位置
	public static final String SERVICEIMPL_SAVEPATH = SERVICE_SAVEPATH+"/impl";
	
	//spring配置文件里要替换的标签代码
	public static final String XML_TAGBODY = "<!--tagbody-->";
	
	//sqlMap.xml文件路径
	public static final String SQL_PATH =SRC_PATH+"sqlmap.xml";
	
	//fieldValidate.xml文件路径
	public static final String VALIDATE_PATH =SRC_PATH+"fieldValidate.xml";
	
	//globalMessages_zh_CN.properties文件路径
	public static final String MESSAGE_PATH =SRC_PATH+"globalMessages_zh_CN.properties";
	
	//运营商后台页面路径
	public static final String PAGE_PATH ="WebRoot/WEB-INF/pages/admin/";
	
	
}
