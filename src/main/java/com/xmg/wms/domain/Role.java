package com.xmg.wms.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Role extends BaseDomain {

	private static final long serialVersionUID = -2238500655156781403L;
	private String name;
	private String sn;
	private List<Permission> permissions = new ArrayList<>();
	private List<SystemMenu> menus = new ArrayList<>();
	@Override
	public String toString() {
		return "Role [name=" + name + ", sn=" + sn + "]";
	}
}
