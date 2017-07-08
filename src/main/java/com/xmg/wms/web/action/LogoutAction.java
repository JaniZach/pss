package com.xmg.wms.web.action;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class LogoutAction extends BaseAction {

	private static final long serialVersionUID = 1809454662013887229L;

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		//退出登陆,清空session
		session.remove("EMPLOYEE_IN_SESSION");
		session.remove("EXPRESSIONS_IN_SESSION");
		return Action.LOGIN;
	}
}
