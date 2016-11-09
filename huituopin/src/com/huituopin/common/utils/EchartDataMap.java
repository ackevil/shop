package com.huituopin.common.utils;

import java.util.ArrayList;
import java.util.List;
  
public class EchartDataMap {
  
    public List<String> legend = new ArrayList<String>();//数据分组  
    public List<String> category = new ArrayList<String>();//横坐标  
	public List<SeriesMap> seriesmap = new ArrayList<SeriesMap>();// 纵坐标
      
      
	public EchartDataMap(List<String> legendList, List<String> categoryList,
			List<SeriesMap> seriesList) {
        super();  
        this.legend = legendList;  
        this.category = categoryList;  
		this.seriesmap = seriesList;
    }  
      
}  