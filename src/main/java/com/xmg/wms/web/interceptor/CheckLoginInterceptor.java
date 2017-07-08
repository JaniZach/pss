package com.xmg.wms.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xmg.wms.domain.Employee;

public class CheckLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//自定义的登陆与否控制拦截器
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		Employee emp = (Employee) session.get("EMPLOYEE_IN_SESSION");
		if(emp==null){
			return Action.LOGIN;
		}else{
			return invocation.invoke();
		}
	}
}
