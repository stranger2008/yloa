package com.lll.common.validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lll.common.util.PropertiesUtil;

public class ObjValidate {

	public static String commonValidateField(Object obj, String methodName) {
		
		//获取国际话属性名称
		Map gmMap = PropertiesUtil.getGlobalMessageMap();

		StringBuffer tipstr = new StringBuffer();

		if(obj == null){
			tipstr.append("表单提交的对象为null！");
			return tipstr.toString();
		}
		
		// 获取对象名称，小写
		String className = obj.getClass().getSimpleName().toLowerCase();

		Map objMap = ValidateUtil.convertObjToMap(obj);

		// 从xml文档中获取验证规则ArrayList
		List validateList = FieldValidateFromXmlUtil.getObjValidateList(className, methodName);

		if (validateList != null && validateList.size() > 0) {
			for (int i = 0; i < validateList.size(); i++) {

				HashMap vtableMap = (HashMap) validateList.get(i);
				String name = "", type = "", length = "", required = "", cnname = "";
				if (vtableMap.get("name") != null)
					name = vtableMap.get("name").toString();
				if (vtableMap.get("type") != null)
					type = vtableMap.get("type").toString();
				if (vtableMap.get("length") != null)
					length = vtableMap.get("length").toString();
				if (vtableMap.get("required") != null)
					required = vtableMap.get("required").toString();
				
				//获取国际化字段的名字
				if(gmMap.get(className+"."+name) != null){
					cnname = gmMap.get(className+"."+name).toString();
				}

				// 获取表单提交的值
				String fieldValue = "";
				if (objMap.get(name) != null) {
					fieldValue = objMap.get(name).toString();
				}
				// 验证字段为必填
				if (required.equals("true") && fieldValue.trim().equals("")) {
					tipstr.append(cnname + "不能为空！");
				}
				if (!fieldValue.trim().equals("")) {
					if (length.indexOf(",") > 0) {
						length = length.substring(0, length.indexOf(","));
					}
					if (fieldValue.length() > Integer.parseInt(length)) {
						tipstr.append(cnname + "的长度限制在" + length + "个字符！");
					} else {
						if (type.equals("int")&& ValidateUtil.isDigital(fieldValue.trim())) {
							tipstr.append(cnname + "只能为数字(0-9)！");
						}
						// 验证字段类型为：email为电子邮件
						if (type.equals("email")&& ValidateUtil.isEmail(fieldValue.trim())) {
							tipstr.append(cnname + "格式不正确,格式如:xxxx@xxx.com！");
						}
						// 验证字段类型为：tel为固定电话
						if (type.equals("tel")&& ValidateUtil.isTelephone(fieldValue.trim())) {
							tipstr.append(cnname + "格式不正确,格式如:xxx-xxxxxxx！");
						}
						// 验证字段类型为：mobile为移动电话
						if (type.equals("mobile")&& ValidateUtil.isMobile(fieldValue.trim())) {
							tipstr.append(cnname + "格式不正确！");
						}
						// 验证字段类型为：double为浮点型
						if (type.equals("double")&& ValidateUtil.isDouble(fieldValue.trim())) {
							tipstr.append(cnname + "只能为浮点型！");
						}
						// 验证字段类型为：chinese为中文
						if (type.equals("chinese")&& ValidateUtil.isChinese(fieldValue.trim())) {
							tipstr.append(cnname + "只能为汉字！");
						}
						// 验证字段类型为：idcard为18位身份证格式
						if (type.equals("idcard")&& ValidateUtil.isIdcard_18(fieldValue.trim())) {
							tipstr.append(cnname + "只能为18位身份证格式！");
						}
						// 验证字段类型为：ip为IP格式
						if (type.equals("ip")&& ValidateUtil.isIP(fieldValue.trim())) {
							tipstr.append(cnname + "只能为IP地址格式！");
						}
						// 验证字段类型为：time为时间格式
						if (type.equals("time")&& ValidateUtil.isTime(fieldValue.trim())) {
							tipstr.append(cnname + "格式不正确,格式如:1900-01-01！");
						}
						// 验证字段类型为：alpha为字母
						if (type.equals("alpha")&& ValidateUtil.isAlpha(fieldValue.trim())) {
							tipstr.append(cnname + "只能为字母(a-z A-Z)！");
						}
						// 验证字段类型为：repeat为重复字符
						if (type.equals("repeat")&& ValidateUtil.hasRepeat(fieldValue.trim())) {
							tipstr.append(cnname + "不能出现重复！");
						}
						// 验证字段类型为：alphas 为数字、字母、下划线
						if (type.equals("alphas")&& ValidateUtil.isAlphas(fieldValue.trim())) {
							tipstr.append(cnname + "由数字、字母或下划线组成！");
						}
						// 验证字段类型为：rmb人民币类型
						if (type.equals("rmb")&& ValidateUtil.isRmb(fieldValue.trim())) {
							tipstr.append(cnname + "格式不正确,格式如:100.00！");
						}
						// 验证字段类型为：alphasfirst由字母开头和数字、字母、下划线结束
						if (type.equals("alphasfirst")&& ValidateUtil.isAlphasFirst(fieldValue.trim())) {
							tipstr.append(cnname + "以字母开头,由数字、字母或下划线组成！");
						}
						// 验证字段类型为：alphaslimt 数字、字母、下划线组成6-20位
						if (type.equals("alphaslimt")&& ValidateUtil.isAlphasLimtLength(fieldValue.trim())) {
							tipstr.append(cnname + "由6-32位的数字、字母或下划线组成！");
						}
						// 验证字段类型为：URL格式验证
						if (type.equals("url")&& ValidateUtil.isURL(fieldValue.trim())) {
							tipstr.append(cnname+ "格式不正确,格式如:http://www.abc.com！");
						}
					}
				}
			}
		}
		return tipstr.toString();
	}
}
