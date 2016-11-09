package com.huituopin.dsze.service;

import java.util.Date;
import java.util.List;

import com.huituopin.dsze.entity.Dsze;
import com.huituopin.dsze.entity.DszeComment;
import com.huituopin.dsze.entity.DszeSupport;
import com.huituopin.common.utils.Page;


public interface IDszeService {
	public List<Dsze> getAllDszeList();
	public Dsze getDszeById(int id);
	public void addDsze(Dsze dsze);
	public void updateDsze(Dsze dsze);
	void delDsze(Dsze dsze);
	public void delDszeById(int id);
	public List<Dsze> getDsze(Page page);
	public List<DszeSupport> getDszeSupportByDszeId(int id,Page page);
	public List<DszeSupport> getDszeSupport(Page page);
    public List<DszeComment> getDszeComments(int dszeServiceId);
	public List<DszeComment> getDszeCommentsByDszeId(int id);
	public List<Dsze> getDszeByOptions(Page page,
			Date dszeLaunchTime, Date dszeStopTime, Integer state,
			String key);
}
