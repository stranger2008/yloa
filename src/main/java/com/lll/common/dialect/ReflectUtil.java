package com.lll.common.dialect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.log4j.Logger;

public class ReflectUtil
{

 private static final Logger logger = Logger.getLogger(ReflectUtil.class);

 public static void setFieldValue(Object target, String fname, Class ftype, Object fvalue)
 {
     if(target == null || fname == null || "".equals(fname) || fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))
         return;
     Class clazz = target.getClass();
     try
     {
         Method method = clazz.getDeclaredMethod((new StringBuilder()).append("set").append(Character.toUpperCase(fname.charAt(0))).append(fname.substring(1)).toString(), new Class[] {
             ftype
         });
         if(!Modifier.isPublic(method.getModifiers()))
             method.setAccessible(true);
         method.invoke(target, new Object[] {
             fvalue
         });
     }
     catch(Exception me)
     {
         try
         {
             Field field = clazz.getDeclaredField(fname);
             if(!Modifier.isPublic(field.getModifiers()))
                 field.setAccessible(true);
             field.set(target, fvalue);
         }
         catch(Exception fe)
         {
             if(logger.isDebugEnabled())
                 logger.debug(fe);
         }
     }
 }
}
