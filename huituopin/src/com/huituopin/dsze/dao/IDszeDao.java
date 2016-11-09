package com.huituopin.dsze.dao;

import java.util.Date;
import java.util.List;

import com.huituopin.dsze.entity.Dsze;
import com.huituopin.dsze.entity.DszeComment;
import com.huituopin.dsze.entity.DszeSupport;
import com.huituopin.common.dao.BaseDao;
import com.huituopin.common.utils.Page;

public interface IDszeDao extends BaseDao<Dsze, String>{


	public Dsze getDszeById(int id);

	public void add(Dsze dsze);

	public void deleteDszeById(int id);

	public List<Dsze> getDszeList(Page page);

	public List<Dsze> getAllDszeList();

	public List<DszeSupport> getDszeSupportByDszeId(int id,
			Page page);

	public List<DszeSupport> getDszeSupport(Page page);

	public List<DszeComment> getDszeComment(int dszeId);

	public DszeComment getDszeCommentById(int id);

    public List<DszeComment> getDszeCommentByDszeCommentId(int id);

	public List<DszeComment> getDszeCommentsByDszeId(int id);

	public List<Dsze> getDszeByOptions(Page page,
			Date dszeLaunchTime, Date dszeStopTime, Integer state,
			String key);
	
	public List<Object> getDszeListPC(Page page);

}
