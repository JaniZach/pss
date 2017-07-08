package com.xmg.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Permission extends BaseDomain {
	private static final long serialVersionUID = -2600377886840019782L;
	private String name;
	private String expression;
	@Override
	public String toString() {
		return "Permission [name=" + name + ", expression=" + expression + "]";
	}
	
}
