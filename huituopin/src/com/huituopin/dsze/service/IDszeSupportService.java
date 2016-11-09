package com.huituopin.dsze.service;

import java.util.List;

import com.huituopin.dsze.entity.DszeSupport;

public interface IDszeSupportService {

	public boolean addDszeSupport(DszeSupport dszeSupport);

	public boolean checkOrder(String dszeSupOrder);
	
	public List<DszeSupport> getDszeSupportByUserId(int userId);
	public List<DszeSupport> getDszeSupportByDszeId(int id);
}
