package com.huituopin.yjya.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.bank.dao.ICredictsDao;
import com.huituopin.bank.dao.impl.CredictsDaoImpl;
import com.huituopin.bank.entity.Credicts;
import com.huituopin.bank.service.ICredictsService;
import com.huituopin.common.constants.ClothStatus;
import com.huituopin.common.constants.PictureType;
import com.huituopin.common.constants.RuleName;
import com.huituopin.common.utils.GenerateOrderNumber;
import com.huituopin.common.utils.Page;
import com.huituopin.user.dao.ILovingPeopleDao;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
import com.huituopin.yjya.dao.IClothCommentDao;
import com.huituopin.yjya.dao.IClothDao;
import com.huituopin.yjya.dao.IClothOrderDao;
import com.huituopin.yjya.dao.IClothPictureDao;
import com.huituopin.yjya.dao.IClothTypeDao;
import com.huituopin.yjya.dao.IRuleDao;
import com.huituopin.yjya.entity.Cloth;
import com.huituopin.yjya.entity.ClothComment;
import com.huituopin.yjya.entity.ClothOrder;
import com.huituopin.yjya.entity.ClothPicture;
import com.huituopin.yjya.entity.ClothType;
import com.huituopin.yjya.entity.Rule;
import com.huituopin.yjya.service.IYjyaService;
import com.ibm.db2.jcc.a.c;

@Transactional(readOnly=false)
@Service
public class YjyaServiceImpl implements IYjyaService{

	@Autowired
	private IClothTypeDao clothTypeDao;
	@Autowired
	private IClothDao clothDao;
	@Autowired
	private IClothPictureDao clothPictureDao;
	@Autowired
	private IClothOrderDao clothOrderDao;
	@Autowired
	private IClothCommentDao clothCommentDao;
	@Autowired
	private IRuleDao ruleDao;
	@Autowired
	private ICredictsService credictsService;
	@Autowired
	private IUserService userService; 

	@Override
	public List<ClothType> getClothTypeList(String keyword,Page page) {
		return clothTypeDao.getAllClothType(keyword,page);
	}

	@Override
	public ClothType getClothTypeById(short id) {
		return clothTypeDao.getClothTypeById(id);
	}

	@Override
	public boolean insertClothType(ClothType clothType) {
		return clothTypeDao.insert(clothType);
	}

	@Override
	public boolean updateClothType(ClothType clothType) {
		return clothTypeDao.updateClothType(clothType);
	}

	@Override
	public boolean deleteClothTypeById(short clothTypeId) {
		return clothTypeDao.deleteClothTypeById(clothTypeId);
	}

	@Override
	public List<Cloth> getClothList(Page page) {
		return clothDao.getAllCloth(page);
	}

	@Override
	public List<Object> getClothInfoList(Page page,Date datetimeStart,Date datetimeEnd,Integer type1,Integer state,String key) {
		return clothDao.getAllClothInfo(page,datetimeStart,datetimeEnd,type1,state,key);
	}

	@Override
	public List<Cloth> getTargetClothList(int begin, int end) {
		return clothDao.getTargetClothList(begin, end);
	}

	@Override
	public Cloth getClothById(int id) {
		return clothDao.getClothByID(id);
	}

	@Override
	public List<Cloth> getClothList() {
		return clothDao.getAllCloth();
	}

	@Override
	public List<Cloth> getClothsByUserId(int userId) {
		return clothDao.getClothsByUserId(userId);
	}

	@Override
	public List<ClothPicture> getDetailPicsByClothId(int clothId) {
		return clothPictureDao.getDetailPicsByClothId(clothId);
	}

	@Override
	public List<ClothPicture> getCommentPicsByClothId(int clothId) {
		return clothPictureDao.getCommentPicsByClothId(clothId);
	}

	@Override
	public int insertCloth(Cloth cloth) {
		return clothDao.insert(cloth);
	}
 
	@Override
	public boolean insertClothPicture(ClothPicture clothPicture) {
		return clothPictureDao.insertClothPicture(clothPicture);
	}
	
	@Override
	public String receiveCloth(String clothId, String saId,int userId) {
		String result = "";
		//在领取之前对该衣物的状态进行检测，只有该衣物是未领取状态，才能领取
		synchronized(this){
			short clothStatus = getCSByClothId(Integer.valueOf(clothId));
			if(clothStatus != 0){
				//表示该衣物已被领取
				result = "empty";
			}else{
				ClothOrder co = new ClothOrder();
				//生成订单号
				String orderNumber = GenerateOrderNumber.Generate(Integer.valueOf(clothId),userId);
				co.setClothOrderNum(orderNumber);
				co.setUserId(userId);
				co.setClothId(Integer.valueOf(clothId));
				co.setShippingAddId(Integer.valueOf(saId));
				co.setClothOrderState(ClothStatus.RECEIVED);
				if(insertClothOrder(co)){
					result = "true";
					
				}else{
					result = "fail";
				}
			}
		}
		
		return result;
	}

	@Override
	public boolean insertClothOrder(ClothOrder clothOrder) {
		boolean result;
		if(clothOrderDao.insertClothOrder(clothOrder)){
			Cloth cloth = clothDao.getClothByID(clothOrder.getClothId());
			cloth.setClothState(ClothStatus.RECEIVED);
			if(clothDao.updateCloth(cloth))
				result = true;
			else
				result = false;
			//对相应的积分改变
			int userId = cloth.getUserId();
			LovingPeople lovingPeople  = userService.searchLpInfoByUserID(userId);
			Rule rule = ruleDao.getRuleById(3);
			int num = lovingPeople.getLoveCredicts()+rule.getGetCredictNum();
			lovingPeople.setLoveCredicts((short) num);
			userService.updataLovingPeopleDetailInfo(lovingPeople);
			Credicts credicts = new Credicts();
			credicts.setCredictsImg(cloth.getClothMainpicPath()); //图片
			credicts.setCredictsIsDelete(false);//删除标记
			credicts.setCredictsChanges("+"+rule.getGetCredictNum());//积分变化
			credicts.setCredictsDate(new Date());//日期
			credicts.setUserId(userId); //用户ID
			credicts.setProductId(cloth.getClothId());//物品Id
			credicts.setCredictsType(0);//积分类型  1：支出  0：收入
			credicts.setCredictsRemark("捐助被领取获得");//备注  捐助获得
			credicts.setCredictsName(cloth.getClothName());//物品名称
			credictsService.insertOneData(credicts);
			
		}else
			result = false;
		
		return result;
	}
	
	@Override
	public short getCSByClothId(int clothId) {
		Cloth cloth = clothDao.getClothByID(clothId);
		return cloth.getClothState();
	}

	@Override
	public List<Cloth> getClothsByTypes(String type1, String type2, String type3) {
		return clothDao.getClothsByTypes(type1, type2, type3);
	}

	@Override
	public List<Cloth> getClothsByUserIDAndStatus(int userId, short status) {
		return clothDao.getClothsByUserIDAndStatus(userId, status);
	}

	@Override
	public List<Object> getOrderInfoByUserIDAndStatus(int userId, short status) {
		return clothOrderDao.getCOsByUserIDAndStatus(userId, status);
	}

	@Override
	public ClothOrder getClothOrderByCOId(int coId) {
		return clothOrderDao.getClothOrderByCOId(coId);
	}
	@Override
	public boolean sendCloth(int coId,short expressNameId,String expressNum){
		ClothOrder co = getClothOrderByCOId(coId);
		co.setClothOrderState(ClothStatus.ONPOST);
		co.setLogisticsCompanyId(expressNameId);
		co.setLogisticsNum(expressNum);
		Cloth cloth = getClothById(co.getClothId());
		cloth.setClothState(ClothStatus.ONPOST);
		if(clothOrderDao.updateClothOrder(co)&& clothDao.updateCloth(cloth)){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public List<Object> getReceivedOrderInfoByPoorUserID(int userId) {
		return clothOrderDao.getReceivedCOsByPoorUserIDAndStatus(userId);
	}

	@Override
	public List<Object> getReceptionOrderInfoByPoorUserID(int userId) {
		return clothOrderDao.getReceptionCOsByPoorUserIDAndStatus(userId);
	}

	@Override
	public boolean updateClothOrderStatus(int coId, short status) {
		ClothOrder co = clothOrderDao.getClothOrderByCOId(coId);
		co.setClothOrderState(status);
		Cloth cloth = clothDao.getClothByID(co.getClothId());
		cloth.setClothState(status);
		if(clothOrderDao.updateClothOrder(co) && clothDao.updateCloth(cloth))
			return true;
		else
			return false;
	}

	@Override
	public boolean insertClothComment(int coId, String detailInfo,
			String picPaths) {
		ClothOrder co = getClothOrderByCOId(coId);
		ClothComment cc = new ClothComment();
		cc.setClothId(co.getClothId());
		cc.setPoorId(co.getUserId());
		cc.setClothComContent(detailInfo);
		if(!clothCommentDao.insert(cc)){
			return false;
		}
		//把所有图片存到图片表中
		if(picPaths!=""){
			String[] arrPicPaths = picPaths.split(";");
			for(int i=0;i<arrPicPaths.length;i++){
				ClothPicture cp = new ClothPicture();
				cp.setClothId(co.getClothId());
				cp.setClothPicPath(arrPicPaths[i]);
				cp.setClothPicType(PictureType.COMMENTPICTURE);
				if(!insertClothPicture(cp)){
					return false;
				}
			}
		}
		//更改ClothOrder表中的状态
		co.setClothOrderState(ClothStatus.COMMENTED);
		Cloth cloth = getClothById(co.getClothId());
		cloth.setClothState(ClothStatus.COMMENTED);
		if(clothOrderDao.updateClothOrder(co) && clothDao.updateCloth(cloth))
			return true;
		else
			return false;
	}
	
	
	/**
	 * 根据clothId获取该衣物相关评论信息
	 * 1.判断一下该cloth状态，如果是未评价，则返回null，前台显示暂无评价
	 * 2.如果已评价，根据ClothOrder表中的userId信息，查找到该贫困人士头像以及昵称
	 * 3.根据ClothId在ClothComment表中查找评论详情
	 * 4.根据ClothId在ClothPicture表中查找相关图片
	 * @param clothId
	 * @return
	 */
	@Override
	public Map<String, Object> getCommentInfoByClothId(int clothId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Cloth cloth = getClothById(clothId);
		if(cloth.getClothState()!=ClothStatus.COMMENTED)
			return map;
		List<Object> commentInfoList = clothCommentDao.getCommentInfo(clothId);
		List<ClothPicture> commentPicList = clothPictureDao.getCommentPicsByClothId(clothId);
		map.put("commentInfoList", commentInfoList);
		map.put("commentPicList", commentPicList);
		return map;
	}

	@Override
	public ClothOrder getClothOrderByClothId(int clothId) {
		return clothOrderDao.getClothOrderByCOId(clothId);
	}

	@Override
	public boolean deleteClothOrder(int coId) {
		return clothOrderDao.deleteClothOrderByCOId(coId);
	}

	/**
	 * 1.根据userId从ClothComment表中获取评价内容，
	 * 2.根据clothPicture中获取评论图片
	 */
	@Override
	public Map<String,Object> getAllCommentByUserId(int userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<List<ClothPicture>> list = new ArrayList<List<ClothPicture>>();
		List<ClothComment> commentInfoList = clothCommentDao.getCommentInfoByUserId(userId);
		if(commentInfoList!=null){
			for(int i = 0; i < commentInfoList.size() ; i++){
				ClothComment cc = commentInfoList.get(i);
				List<ClothPicture> pictureList = clothPictureDao.getCommentPicsByClothId(cc.getClothId());
				list.add(pictureList);
			}
		}
		map.put("commentInfoList", commentInfoList);
		map.put("list", list);
		return map;
	}

	@Override
	public List<ClothType> getAllClothType() {
		return clothTypeDao.getAllClothType();
	}

	@Override
	public List<Object> getCommentInfoList(Page page) {
		return clothCommentDao.getAllCommentInfo(page);
	}

	@Override
	public Map<String, Object> getRule() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("1", ruleDao.getCredictNum(RuleName.LIMITNUM));
		map.put("2", ruleDao.getCredictNum(RuleName.SHARECREDICT));
		map.put("3", ruleDao.getCredictNum(RuleName.RECEPTEDCREDICT));
		return map;
	}

	@Override
	public boolean updateRule(int id, int value) {
		Rule rule = ruleDao.getRuleById(id);
		rule.setGetCredictNum(value);
		if(ruleDao.updateRule(rule)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean donateCloth(int userId, String clothName, short type1,
			short type2, short type3, String detailInfo, String picPaths) {
		Cloth cloth = new Cloth();
		cloth.setUserId(userId);
		cloth.setClothName(clothName);
		cloth.setClothType1(type1);
		cloth.setClothType2(type2);
		cloth.setClothType3(type3);
		cloth.setClothState(ClothStatus.UNCLAIMED);
		cloth.setClothDetailinfo(detailInfo);
		//第一张图片默认为主图
		String[] arrPicPaths = picPaths.split(";");
		String mainPicPath = arrPicPaths[0];
		cloth.setClothMainpicPath(mainPicPath);
		if(insertCloth(cloth)==0){
			return false; 
		}
		//把所有图片存到图片表中
		for(int i=0;i<arrPicPaths.length;i++){
			ClothPicture cp = new ClothPicture();
			cp.setClothId(cloth.getClothId());
			cp.setClothPicPath(arrPicPaths[i]);
			cp.setClothPicType(PictureType.DETAILPICTURE);
			if(!insertClothPicture(cp)){
				return false;
			}
		}
		//衣物类型对应数量+1
		ClothType clothType = getClothTypeById(cloth.getClothType1());
		clothType.setClothTypeNum(clothType.getClothTypeNum()+1);
		
		//对相应的积分改变
		LovingPeople lovingPeople  = userService.searchLpInfoByUserID(userId);
		Rule rule = ruleDao.getRuleById(6);
		int num = lovingPeople.getLoveCredicts()+rule.getGetCredictNum();
		lovingPeople.setLoveCredicts((short) num);
		userService.updataLovingPeopleDetailInfo(lovingPeople);
		Credicts credicts = new Credicts();
		credicts.setCredictsImg(cloth.getClothMainpicPath()); //图片
		credicts.setCredictsIsDelete(false);//删除标记
		credicts.setCredictsChanges("+"+rule.getGetCredictNum());//积分变化
		credicts.setCredictsDate(new Date());//日期
		credicts.setUserId(userId); //用户ID
		credicts.setProductId(cloth.getClothId());//物品Id
		credicts.setCredictsType(0);//积分类型  1：支出  0：收入
		credicts.setCredictsRemark("捐助获得");//备注  捐助获得
		credicts.setCredictsName(cloth.getClothName());//物品名称
		credictsService.insertOneData(credicts);

		
		if(updateClothType(clothType)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteClothById(String clothIds) {
		boolean result=true;
		String[] clothList = clothIds.split(";");
		int i=0;
		if(clothList[0].equals("全选") || clothList[0].equals("取消")){
			i=1;
		}
		for(;i<clothList.length;i++){
			if(!clothDao.deleteClothById(Integer.valueOf(clothList[i]))){
				result=false;
			}
		}
		return result;
	}

	@Override
	public boolean isUpTheLimit(int userId) {
		int limit = ruleDao.getCredictNum(1);
		if(clothOrderDao.getReceivedCOsByPoorUserIDAndStatus(userId)==null){
			return false;
		}
		int getNum = clothOrderDao.getReceivedCOsByPoorUserIDAndStatus(userId).size();
		if(getNum == limit){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<Object> getClothByPoorId(int userId) {
		return clothDao.getClothByPoorId(userId);
	}

	@Override
	public List<Object> getCommentInfoByUserIdAdmin(int userId) {
		return clothCommentDao.getCommentInfoByUserIdAdmin(userId);
	}

	@Override
	public boolean setStautsComments(String commentIds,short status) {
		boolean result=true;
		String[] commentList = commentIds.split(";");
		int i=0;
		if(commentList[0].equals("全选") || commentList[0].equals("取消")){
			i=1;
		}
		for(;i<commentList.length;i++){
			ClothComment cc = clothCommentDao.getClothCommentByCCId(Integer.valueOf(commentList[i]));
			cc.setClothComState(status);
			if(!clothCommentDao.updateClothComment(cc)){
				result = false;
				break;
			}
		}
		return result;
	}
	@Override
	public List<Object> getLpDonateInfo(Page page, Date datetimeStart,Date datetimeEnd, Integer type1, Integer state, String key,String userId) {
		return clothDao.getLpDonateInfo(page,datetimeStart,datetimeEnd,type1,state,key,userId);
	}

	@Override
	public boolean deleteClothTypes(String clothTypes) {
		boolean result=true;
		String[] clothTypeList = clothTypes.split(";");
		int i=0;
		if(clothTypeList[0].equals("全选") || clothTypeList[0].equals("取消")){
			i=1;
		}
		for(;i<clothTypeList.length;i++){
			ClothType ct = clothTypeDao.getClothTypeById(Short.valueOf(clothTypeList[i]));
			ct.setClothTypeIsDelete(true);
			if(!clothTypeDao.updateClothType(ct)){
				result = false;
				break;
			}
		}
		return result;
	}


	@Override
	public boolean stickCloth(int clothId) {
		Cloth cloth = clothDao.getClothByID(clothId);
		cloth.setClothOrder(true);
		cloth.setClothOrderTime(new Date());
		return clothDao.updateCloth(cloth);
	}


	@Override
	public List<Rule> getCridictsRule() {
		return ruleDao.getCridictsRule();
	}

	
	

	
	
	
	
	
}
