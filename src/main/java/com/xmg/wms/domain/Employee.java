package com.xmg.wms.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Employee extends BaseDomain {
	private static final long serialVersionUID = -5490569875807039328L;
	private String name;
	private String password;
	private String email;
	private Integer age;
	private Boolean admin;
	private Department dept;
	private List<Role> roles = new ArrayList<>();
	//页面显示角色信息的方法
	public String getRoleNames(){
		//由于延迟加载的原因,此处需要使用this.getRoles(),而不是roles
		if(this.admin){
			return "[超级管理员]";
		}
		if(this.getRoles().size()==0){
			return "[暂未分配角色]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Role role : this.getRoles()) {
			sb.append(role.getName()).append(" ,");
		}
		sb.deleteCharAt(sb.length()-1).append("]");
		return sb.toString();
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", password=" + password + ", email="
				+ email + ", age=" + age + ", admin=" + admin + ", dept="
				+ dept + "]";
	}
}
