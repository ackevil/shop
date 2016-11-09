package com.huituopin.common.utils;

import java.util.List;
import javax.persistence.Entity;

@Entity
public class SeriesPie {

	public String name;

	public String type;

	public String radius;

	public String roseType;

	public List<String> data;// 这里要用int 不能用String 不然前台显示不正常（特别是在做数学运算的时候）

	protected SeriesPie() {
	}

	public SeriesPie(String name, String type, String radius, String roseType,
			List<String> data) {
		super();
		this.name = name;
		this.type = type;
		this.radius = radius;
		this.roseType = roseType;
		this.data = data;
	}
}
