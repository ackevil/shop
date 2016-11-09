package com.huituopin.yjya.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huituopin.common.utils.Page;
import com.huituopin.yjya.entity.Cloth;
import com.huituopin.yjya.entity.ClothOrder;
import com.huituopin.yjya.entity.ClothPicture;
import com.huituopin.yjya.entity.ClothType;
import com.huituopin.yjya.entity.Rule;

public interface IYjyaService {
	/**
	 * 獲取所有ClothType
	 * @return
	 */
	public List<ClothType> getClothTypeList(String keyword,Page page);
	
	/**
	 * 插入一條ClothType記錄
	 */
	public boolean insertClothType(ClothType clothType);
	/**
	 * 根據ID獲取ClothType
	 * @param id
	 * @return
	 */
	public ClothType getClothTypeById(short id);
	
	/**
	 * 更新ClothType的值
	 * @param clothType
	 * @return
	 */
	public boolean updateClothType(ClothType clothType);
	
	/**
	 * 根据Id删除一条ClothType记录
	 * @param clothTypeId
	 * @return
	 */
	public boolean deleteClothTypeById(short clothTypeId);
	
	/**
	 * 获取Cloth列表
	 * @return
	 */
	public List<Cloth> getClothList();
	
	/**
	 * 获取Cloth列表
	 * @param page
	 * @return
	 */
	public List<Cloth> getClothList(Page page);
	
	/**
	 * 获取所有Cloth相关信息
	 * @param page
	 * @return
	 */
	public List<Object> getClothInfoList(Page page,Date datetimeStart,Date datetimeEnd,Integer type1,Integer state,String key);
	
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
	 * 获取指定区间数量的Cloth
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Cloth> getTargetClothList(int begin,int end);
	
	/**
	 * 根据ID获取cloth
	 * @return
	 */
	public Cloth getClothById(int id);
	
	/**
	 * 根据userID获取Cloth
	 * @param userId
	 * @return
	 */
	public List<Cloth> getClothsByUserId(int userId);
	/**
	 * 根据clothId获取所有详情图片
	 * @param clothId
	 * @return
	 */
	public List<ClothPicture> getDetailPicsByClothId(int clothId);
	/**
	 * 根据clothId获取所有评论图片
	 * @param clothId
	 * @return
	 */
	public List<ClothPicture> getCommentPicsByClothId(int clothId);
	
	/**
	 * 插入一条Cloth数据
	 * @param cloth
	 * @return
	 */
	public int insertCloth(Cloth cloth);
	
	/**
	 * 插入一条ClothPicture数据
	 * @param clothPicture
	 * @return
	 */
	public boolean insertClothPicture(ClothPicture clothPicture);
	
	/**
	 * 领取衣物
	 * @return
	 */
	public String receiveCloth(String clothId,String saId,int userId);
	/**
	 * 插入一条ClothOrder数据
	 * @param clothOrder
	 * @return
	 */
	public boolean insertClothOrder(ClothOrder clothOrder);
	/**
	 * 根据clothID获取该衣物状态
	 * @param clothId
	 * @return
	 */
	public short getCSByClothId(int clothId);
	/**
	 * 根据衣物类型查找Cloth列表
	 * @param type1
	 * @param type2
	 * @param type3
	 * @return
	 */
	public List<Cloth> getClothsByTypes(String type1,String type2,String type3);
	
	/**
	 * 根据用爱心人士ID和ClothState获取Cloths
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Cloth> getClothsByUserIDAndStatus(int userId,short status);
	
	
	/**
	 * 根据爱心人士ID和ClothState获取ClothOrder信息
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Object> getOrderInfoByUserIDAndStatus(int userId, short status);
	
	/**
	 * 根据贫困ID获取该贫困人士领取的ClothOrder信息
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Object> getReceivedOrderInfoByPoorUserID(int userId);
	/**
	 * 根据贫困ID获取该贫困人士收到的Cloth信息
	 * @param userId
	 * @return
	 */
	public List<Object> getReceptionOrderInfoByPoorUserID(int userId);
	
	/**
	 * 根据订单Id获取该ClothOrder
	 * @param coId
	 * @return
	 */
	public ClothOrder getClothOrderByCOId(int coId);
	
	/**
	 * 根据ClothId获取该Cloth订单
	 * @param clothId
	 * @return
	 */
	public ClothOrder getClothOrderByClothId(int clothId);
	
	/**
	 * 更新物流信息到ClothOrder
	 * @param coId
	 * @param expressNameId
	 * @param expressNum
	 * @return
	 */
	public boolean sendCloth(int coId,short expressNameId,String expressNum);
	
	/**
	 * 根据clothOrderId修改该订单状态为status,同时修改Cloth表中，该cloth的状态
	 * @param coId
	 * @param status
	 * @return
	 */
	public boolean updateClothOrderStatus(int coId,short status);
	
	/**
	 * 插入一条衣服评论
	 * 1.在ClothComment中插入一条记录
	 * 2.在ClothPicture中插入相关评论图片
	 * 3.更改ClothOrder状态
	 * 4.更改Cloth状态
	 * @param coId
	 * @param detailInfo
	 * @param picPaths
	 * @return
	 */
	public boolean insertClothComment(int coId,String detailInfo,String picPaths);
	
	/**
	 * 根据clothId获取该衣物相关评论信息
	 * 1.判断一下该cloth状态，如果是未评价，则返回null，前台显示暂无评价
	 * 2.如果已评价，根据ClothOrder表中的userId信息，查找到该贫困人士头像以及昵称
	 * 3.根据ClothId在ClothComment表中查找评论详情
	 * 4.根据ClothId在ClothPicture表中查找相关图片
	 * @param clothId
	 * @return
	 */
	public Map<String, Object> getCommentInfoByClothId(int clothId);
	
	/**
	 * 根据coId删除该订单
	 * @param coId
	 * @return
	 */
	public boolean deleteClothOrder(int coId);
	/**
	 * 根据贫困人士Id获取所有评价
	 * @param userId
	 * @return
	 */
	public Map<String,Object> getAllCommentByUserId(int userId);
	
	/**
	 * 获取所有的衣物类别信息
	 * @return
	 */
	public List<ClothType> getAllClothType();
	
	/**
	 * 获取全部的评论信息
	 * @return
	 */
	public List<Object> getCommentInfoList(Page page);
	
	/**
	 * 获取规则
	 */
	public Map<String,Object> getRule();
	
	/**
	 * 修改规则
	 * @param id
	 * @param value
	 * @return
	 */
	public boolean updateRule(int id,int value);
	/**
	 * 捐赠衣物
	 * @param userId
	 * @param clothName
	 * @param type1
	 * @param type2
	 * @param type3
	 * @param detailInfo
	 * @param picPaths
	 * @return
	 */
	public boolean donateCloth(int userId,String clothName,short type1,short type2,short type3,String detailInfo,String picPaths);

	public boolean deleteClothById(String clothIds);
	
	/**
	 * 判斷該貧困人士領取衣服數量是否超出限制
	 */
	public boolean isUpTheLimit(int userId);
	/**
	 * 获取贫困人士领取的衣物信息
	 * @param userId
	 * @return
	 */
	public List<Object> getClothByPoorId(int userId);
	
	/**
	 * 获取贫困人士评论信息（后台）
	 * @param userId
	 * @return
	 */
	public List<Object> getCommentInfoByUserIdAdmin(int userId);
	
	/**
	 * 选中的comment统一调整状态
	 * @param commentIds
	 * @return
	 */
	public boolean setStautsComments(String commentIds,short status);
	
	/**
	 * 删除选中的产品类别
	 * @param clothTypes
	 * @return
	 */
	public boolean deleteClothTypes(String clothTypes);
	/**
	 * 爱心银行 获取积分规则
	 * @return
	 */
	public List<Rule> getCridictsRule();
	
	/**
	 * 衣物置顶功能
	 * @param clothId
	 * @return
	 */
	public boolean stickCloth(int clothId);
	
}
