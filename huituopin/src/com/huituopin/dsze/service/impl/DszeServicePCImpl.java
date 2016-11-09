package com.huituopin.dsze.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.dsze.dao.IDszeDao;
import com.huituopin.dsze.entity.Dsze;
import com.huituopin.dsze.service.IDszeServicePC;
import com.huituopin.common.utils.Page;

@Transactional(readOnly=false)
@Service
public class DszeServicePCImpl implements IDszeServicePC {

	@Autowired
	private IDszeDao dszeDao;
	
	@Override
	public int getDszePage(int pageSize) {
		int num = 0;
		List<Dsze> list = dszeDao.getAllDszeList();
		if(list != null){
			num = (int) Math.ceil(list.size()*1.0/pageSize);
		}
		return num;
	}

	@Override
	public List<Object> getDszeList(int pageNo, int pageSize) {
		Page page = new Page();
		page.pageSizes = pageSize;
		page.page = pageNo;
		return dszeDao.getDszeListPC(page);

	}

}
