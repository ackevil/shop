package com.huituopin.yjya.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.yjya.dao.IClothDao;
import com.huituopin.yjya.entity.Cloth;
import com.huituopin.yjya.service.IYjyaServicePC;

@Transactional(readOnly=false)
@Service
public class YjyaServicePCImpl implements IYjyaServicePC{

	
	@Autowired
	private IClothDao clothDao;
	
	
	@Override
	public int getClothPage(int pageSize) {
		int pageNum=0;
		List<Cloth> clothList = null;
		clothList = clothDao.getAllCloth();
		if(clothList != null){
			pageNum = (int) Math.ceil(clothList.size()*1.0/pageSize);
		}
		return pageNum;
	}


	@Override
	public List<Cloth> getClothList(int pageNo,int pageSize, int ct1, int ct2, int ct3) {
		return clothDao.getClothsByPC(pageNo, pageSize, ct1, ct2, ct3);
	}


	@Override
	public int getClothNum(String ct1, String ct2, String ct3) {
		int num = 0;
		List<Cloth> clothList = clothDao.getClothsByTypes(ct1,ct2, ct3);
		if(clothList != null){
			num = clothList.size();
		}
		return num;
	}
	

}
