package com.huituopin.dsze.service;

import java.util.List;

import com.huituopin.dsze.entity.Dsze;

public interface IDszeServicePC {
	
	/**
	 * 获取重大救助项目总页数，每页pageSize个项目
	 * @param pageSize
	 * @return
	 */
	public int getDszePage(int pageSize);
	
	/**
	 * 获取指定页数的项目列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Object> getDszeList(int pageNo,int pageSize);

}
