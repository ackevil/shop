package com.huituopin.common.utils;

import java.util.List;

public class SeriesMap {

	public String name;

	public String type;

	public String mapType;

	public List<String> data;// 这里要用int 不能用String 不然前台显示不正常（特别是在做数学运算的时候）

	public SeriesMap(String name, String type, String mapType,
				List<String> data) {
		super();
		this.name = name;
		this.type = type;
		this.mapType = mapType;
		this.data = data;
	}
}
