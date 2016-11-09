package com.huituopin.dsze.dao;

import java.util.List;

import com.huituopin.dsze.entity.DszeSupport;

public interface IDszeSupportDao {
	public boolean addDszeSupport(DszeSupport dszeSupport);

	public boolean checkOrder(String dszeSupOrder);

	public List<DszeSupport> getDszeSupportByUserId(int userId);

	public List<DszeSupport> getDszeSupportByDszeId(int id);
}
