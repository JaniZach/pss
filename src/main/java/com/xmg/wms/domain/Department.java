package com.xmg.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Department extends BaseDomain {
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	private static final long serialVersionUID = -9148984747602597105L;

	private String name;
	private String sn;
	@Override
	public String toString() {
		return "Department [name=" + name + ", sn=" + sn + "]";
	}
	
}
