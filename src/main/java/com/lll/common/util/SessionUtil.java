package com.lll.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	
	public static String get(HttpServletRequest request,String name){
		HttpSession session = request.getSession();
		String value = "";
		if(session.getAttribute(name) != null){
			value = session.getAttribute(name).toString();
		}
		return value;
	}
	
	public static Object getObj(HttpServletRequest request,String name){
		HttpSession session = request.getSession();
		return session.getAttribute(name).toString();
	}
	
	public static void put(HttpServletRequest request,String name,Object value){
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}
	
	public static void invalidate(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
	}
	
}
