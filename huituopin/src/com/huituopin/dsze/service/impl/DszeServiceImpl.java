package com.huituopin.dsze.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.dsze.dao.IDszeDao;
import com.huituopin.dsze.entity.Dsze;
import com.huituopin.dsze.entity.DszeComment;
import com.huituopin.dsze.entity.DszeSupport;
import com.huituopin.dsze.service.IDszeService;
import com.huituopin.common.utils.Page;


@Transactional(readOnly=false)
@Service
public class DszeServiceImpl implements IDszeService {

	@Autowired
	private IDszeDao dszeDao;
	
	@Override
	public List<Dsze> getAllDszeList() {
		
		return dszeDao.getAllDszeList();
	}

	@Override
	public Dsze getDszeById(int id) {
		return dszeDao.getDszeById(id);
	}

	@Override
	public void addDsze(Dsze dsze) {
		 dszeDao.add(dsze);
	}
	
	@Override
	public void updateDsze(Dsze dsze) {
		dszeDao.update(dsze);
	}

	@Override
	public void delDsze(Dsze dsze) {
		dszeDao.delete(dsze);
	}

	@Override
	public void delDszeById(int id) {
		dszeDao.deleteDszeById(id);
	}

	@Override
	public List<Dsze> getDsze(Page page) {
		return dszeDao.getDszeList(page);
	}

	@Override
	public List<DszeSupport> getDszeSupportByDszeId(int id,
			Page page) {
		
		return dszeDao.getDszeSupportByDszeId(id,page);
	}

	@Override
	public List<DszeSupport> getDszeSupport(Page page) {
		return dszeDao.getDszeSupport(page);
	}
	
	@Override
	public List<DszeComment> getDszeComments(int dszeId) {
	    return dszeDao.getDszeComment(dszeId);
	}

    @Override
    public List<DszeComment> getDszeCommentsByDszeId(int id) {
        return dszeDao.getDszeCommentsByDszeId(id);
    }

	@Override
	public List<Dsze> getDszeByOptions(Page page,
			Date dszeLaunchTime, Date dszeStopTime, Integer state,
			String key) {
		 return dszeDao.getDszeByOptions(page,
					dszeLaunchTime,dszeStopTime,state,
					key);
	}

}
