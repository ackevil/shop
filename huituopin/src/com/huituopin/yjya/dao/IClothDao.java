package com.huituopin.yjya.dao;

import java.util.Date;
import java.util.List;

import com.huituopin.common.dao.BaseDao;
import com.huituopin.common.utils.Page;
import com.huituopin.yjya.entity.Cloth;

public interface IClothDao extends BaseDao<Cloth, String>{
	/**
	 * 插入一条Cloth
	 */
	public int insert(Cloth cloth);
	
	/**
	 * 修改一条Cloth数据
	 */
	public boolean updateCloth(Cloth cloth);
	
	/**
	 * 根据ID删除一条Cloth记录
	 */
	public boolean deleteClothById(int clothId);
	
	/**
	 * 根據CLothId獲取Cloth
	 */
	public Cloth getClothByID(int clothId);
	
	/**
	 * 获取所有的Cloth
	 * @return
	 */
	public List<Cloth> getAllCloth();
	
	/**
	 * 获取Cloth列表(带分页)
	 * 
	 */
	public List<Cloth> getAllCloth(Page page);
	
	/**
	 * 获取所有的Cloth的相关信息
	 * @param page
	 * @return
	 */
	public List<Object> getAllClothInfo(Page page,Date datetimeStart,Date datetimeEnd,Integer type1,Integer state,String key);
	
	/**
	 * 获取指定区间数量的Cloth列表
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Cloth> getTargetClothList(int begin,int end);
	
	/**
	 * 根据爱心人士的Id获取该用户所有捐赠衣物的记录
	 * @param userId
	 * @return
	 */
	public List<Cloth> getClothsByUserId(int userId);
	
	/**
	 * 根据类别获取Cloth列表
	 * @param clothType1
	 * @param clothType2
	 * @param clothType3
	 * @return
	 */
	public List<Cloth> getClothsByTypes(String clothType1,String clothType2,String clothType3);
	/**
	 * 根据用爱心人士ID和ClothState获取Cloths
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Cloth> getClothsByUserIDAndStatus(int userId, short status);
	
	/**
	 * 获取爱心人士 捐助记录中的捐助的物品
	 * @param page
	 * @param datetimeStart
	 * @param datetimeEnd
	 * @param type1
	 * @param state
	 * @param key
	 * @return
	 */
	List<Object> getLpDonateInfo(Page page, Date datetimeStart,Date datetimeEnd, Integer type1, Integer state, String key,String userId);
	/**
	 * 获取贫困人士领取的衣物信息
	 * @param userId
	 * @return
	 */
	public List<Object> getClothByPoorId(int userId);
	
	//******************pc 端****************************
	/**
	 * 获取指定页数，类别的衣物列表信息
	 * @param pageNo
	 * @param pageSize
	 * @param ct1
	 * @param ct2
	 * @param ct3
	 * @return
	 */
	public List<Cloth> getClothsByPC(int pageNo,int pageSize,int ct1,int ct2,int ct3);
	
}
