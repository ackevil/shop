package com.huituopin.common.utils;

import java.util.ArrayList;
import java.util.List;

public class EchartDataPie {
	public List<String> legend = new ArrayList<String>();// 数据分组
	public List<String> category = new ArrayList<String>();// 横坐标
	public List<SeriesPie> seriespie = new ArrayList<SeriesPie>();// 纵坐标

	public EchartDataPie(List<String> legendList, List<String> categoryList,
			List<SeriesPie> seriesList) {
		super();
		this.legend = legendList;
		this.category = categoryList;
		this.seriespie = seriesList;
	}
}
