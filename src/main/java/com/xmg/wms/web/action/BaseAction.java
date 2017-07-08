package com.xmg.wms.web.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	protected static final String LIST = "list";
	//批量删除--保存删除员工id的集合
	@Setter
	@Getter
	protected List<Long> ids = new ArrayList<>();
	//抽取回复页面ajax请求信息的方法
	public void putMsg(String msg) throws Exception{
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(msg);
	}
	//抽取将值放入context区域的方法
	public void putContext(String key,Object value){
		ActionContext.getContext().put(key,value);
	}
	//抽取将值以Json格式返回的方法
	public void putJson(Object obj) throws Exception{
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(obj));
	}
}
