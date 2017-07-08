package com.xmg.wms.util;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.wms.domain.Employee;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//抽取一个工具类,用来 存储/获取 用户及其所属的权限set到session中
public class UserContext {
    private UserContext(){}//工具类结扎...
    /*//抽取出session
    private static Map<String, Object> session = ActionContext.getContext().getSession();
    此处不可以抽取出session,因为session有存在时间,session销毁后再次登陆,会报 session already invalidated*/
    //存储角色信息到session中
    public static void setCurrentUser(Employee emp){
        ActionContext.getContext().getSession().put("EMPLOYEE_IN_SESSION",emp);
    }
    //储存角色的权限信息到session中
    public static void setCurrentPermissionSet(Set<String> expressionSet){
        ActionContext.getContext().getSession().put("EXPRESSIONS_IN_SESSION",expressionSet);
    }
    public static Employee getCurrentUser(){
        return (Employee) ActionContext.getContext().getSession().get("EMPLOYEE_IN_SESSION");
    }
    public static Set<String> getPermissionSet(){
        return (HashSet<String>) ActionContext.getContext().getSession().get("EXPRESSIONS_IN_SESSION");
    }
    public static void setUserName(String username){
        ActionContext.getContext().getSession().put("USER_NAME",username);
    }
}
