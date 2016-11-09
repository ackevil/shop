package com.huituopin.aigou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.aigou.dao.ICollectionDao;
import com.huituopin.aigou.dao.ILogisticsDao;
import com.huituopin.aigou.entity.Collection;
import com.huituopin.aigou.service.ICollectionService;
@Transactional(readOnly=false)
@Service
public class CollectionServiceImpl implements ICollectionService {
	@Autowired
	private  ICollectionDao collectionDao;
	@Override
	public List<Object> searchInfoByUserId(int userId) {
		return collectionDao.searchInfoByUserId(userId);
	}

	@Override
	public boolean insertCollection(Collection collection) {
		return collectionDao.insertCollection(collection);
	}

	@Override
	public boolean delCollection(int collectionId) {
		return collectionDao.delCollection(collectionId);
	}

	@Override
	public Collection searchInfoByUserIdAndProductId(int userId, int productId) {
		return collectionDao.searchInfoByUserIdAndProductId(userId, productId);
	}

}
