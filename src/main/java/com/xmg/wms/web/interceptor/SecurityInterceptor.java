package com.xmg.wms.web.interceptor;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xmg.wms.domain.Employee;
import com.xmg.wms.util.RequiredPermission;
import com.xmg.wms.util.UserContext;

@SuppressWarnings("unchecked")
public class SecurityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//如果是超级管理员,进行放行
		/*Map<String, Object> session = invocation.getInvocationContext().getSession();
		Employee emp = (Employee) session.get("EMPLOYEE_IN_SESSION");*/
		Employee emp = UserContext.getCurrentUser();
		if(emp.getAdmin()){
			return invocation.invoke();
		}
		String methodName = invocation.getProxy().getMethod();
		//对于没有贴标签的方法,进行放行
		Method method = invocation.getProxy().getAction().getClass().getMethod(methodName);
		if(!method.isAnnotationPresent(RequiredPermission.class)){
			return invocation.invoke();
		}
		//对于具有权限的访问,进行放行
		Set<String> set = UserContext.getPermissionSet();
		String actionName = invocation.getProxy().getAction().getClass().getName();
		String expression = actionName+":"+methodName;
		if(set.contains(expression)){
			return invocation.invoke();
		}
		return "refuse";
	}
}
