package com.xmg.wms.web.action;

import com.xmg.wms.service.IEmployeeService;

import com.xmg.wms.util.MD5;
import lombok.Setter;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1809454662013887229L;
	@Setter
	private String username;
	@Setter
	private String password;
	@Setter
	private IEmployeeService empService;

	@Override
	public String execute() throws Exception {
		try{
			if(password!=null){
				password= MD5.encode(password);
			}
			empService.checkLogin(username,password);
		}catch (RuntimeException e){
			e.printStackTrace();
			super.addActionError(e.getMessage());
			//System.out.println(e.getMessage());
			return LOGIN;
		}
		return SUCCESS;
	}
}
