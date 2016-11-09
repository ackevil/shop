package com.huituopin.yjya.dao;

import java.util.List;

import com.huituopin.common.dao.BaseDao;
import com.huituopin.common.utils.Page;
import com.huituopin.yjya.entity.ClothType;

public interface IClothTypeDao extends BaseDao<ClothType,String>{
	/**
	 * 插入一條新的clothType
	 */
		public boolean insert(ClothType clothType);
	/**
	 * 修改一條ClothType記錄
	 */
		public boolean updateClothType(ClothType clothType);
	/**
	 * 根據ID刪除一條ClothType記錄
	 */
		public boolean deleteClothTypeById(short clothTypeId);
	/**
	 * 根據clothTypeId獲取一條ClothType記錄
	 */
		public ClothType getClothTypeById(short clothTypeId);
	/**
	 * 獲取所有ClothType記錄
	 */
		public List<ClothType> getAllClothType(String keyword,Page page);
		
		/**
		 * 获取所有ClothType不带分页
		 * @return
		 */
		public List<ClothType> getAllClothType();
	
}
