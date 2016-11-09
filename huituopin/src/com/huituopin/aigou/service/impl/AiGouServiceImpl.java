package com.huituopin.aigou.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.aigou.dao.IProductCommentDao;
import com.huituopin.aigou.dao.IProductOrderDao;
import com.huituopin.aigou.dao.IProductOrderDetailDao;
import com.huituopin.aigou.dao.IProductPictureDao;
import com.huituopin.aigou.dao.IShoppingCartDao;
import com.huituopin.aigou.dao.IProductDao;
import com.huituopin.aigou.dao.IProductTypeDao;
import com.huituopin.aigou.entity.ProductComment;
import com.huituopin.aigou.entity.ProductOrder;
import com.huituopin.aigou.entity.ProductOrderDetail;
import com.huituopin.aigou.entity.ProductPicture;
import com.huituopin.aigou.entity.ShoppingCart;
import com.huituopin.aigou.entity.Product;
import com.huituopin.aigou.entity.ProductType;
import com.huituopin.aigou.service.IAiGouService;
import com.huituopin.aigou.service.IProductService;
import com.huituopin.bank.entity.Credicts;
import com.huituopin.bank.service.ICredictsService;
import com.huituopin.common.constants.PictureType;
import com.huituopin.common.constants.ProductOrderStatus;
import com.huituopin.common.utils.GenerateOrderNumber;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.service.IUserService;
import com.huituopin.yjya.dao.IRuleDao;
import com.huituopin.yjya.entity.Rule;

@Transactional(readOnly=false)
@Service
public class AiGouServiceImpl implements IAiGouService{

	@Autowired
	private IProductTypeDao productTypeDao;
	@Autowired
	private IProductDao productDao;
	@Autowired
	private IShoppingCartDao shoppingCartDao;
	@Autowired
	private IProductOrderDao productOrderDao;
	@Autowired
	private IProductCommentDao productCommentDao;
	@Autowired
	private IProductOrderDetailDao podDao;
	@Autowired
	private IProductPictureDao ppDao;
	
	@Autowired
	private IRuleDao ruleDao;
	@Autowired
	private ICredictsService credictsService;
	@Autowired
	private IUserService userService; 
	
	@Override
	public List<ProductType> getAllProductType() {
		return productTypeDao.getAllProductType();
	}

	@Override
	/*public List<Object> getAllProduct(int type,String keyword,int start,int num) {
		return productDao.getAllProduct(type,keyword,start,num);
	}*/
	public List<Object> getAllProduct(int type,String keyword) {
		return productDao.getAllProduct(type,keyword);
	}
	@Override
	public Product getProductById(int pId) {
		return productDao.getProductById(pId);
	}

	@Override
	public boolean insertShoppingCart(int userId, int pId, int pNum) {
		ShoppingCart sc = null;
		if(shoppingCartDao.getSCByUserIdAndPId(userId, pId)!=null){
			sc = shoppingCartDao.getSCByUserIdAndPId(userId, pId);
			sc.setProductNum((short) (sc.getProductNum() + pNum));
			if(shoppingCartDao.updateSC(sc)){
				return true;
			}else{
				return false;
			}
		}else{
			sc = new ShoppingCart();
			sc.setUserId(userId);
			sc.setProductId(pId);
			sc.setProductNum((short) pNum);
			if(shoppingCartDao.insert(sc)){
				return true;
			}else{
				return false;
			}
		}
	}


	/**
	 * 1.插入一条ProductOrder记录
	 * 2.插入相应的ProductOrderDetail信息
	 */
	@Override
	public Integer insertProductOrder(int userId, String saId, String pId, String pNum,String pCount,String resource) {
		String pIds[] = pId.split("_");
		String pNums[] = pNum.split("_");
		synchronized(this){
			for(int i=0;i<pIds.length;i++){
				Product product = productDao.getProductById(Integer.valueOf(pIds[i]));
				if(product.getProductStock()-product.getProductSaleNum() < Integer.valueOf(pNums[i])){
					return 0 - i; //如果某件产品剩余数量少于要购买的数量，则直接返回该产品不足
				}
			}
			for (int i = 0; i < pIds.length; i++) {
				System.out.println("pIds的长度："+pIds.length+"---------------");
				Product p = productDao.getProductById(Integer.valueOf(pIds[i]));;
				int newSaleNum = p.getProductSaleNum()
						+ Integer.valueOf(pNums[i]);
				p.setProductSaleNum(newSaleNum);
				if (newSaleNum == p.getProductStock()) {
					p.setProductIsOver(true);
					System.out.println("该产品已销售完");
				}
				if (productDao.updateProduct(p)) {
					System.out.println("更新成功");
				} else {
					System.out.println("更新失败");
				}
			}
			ProductOrder po = new ProductOrder();
			po.setProductOrderNum(GenerateOrderNumber.Generate(0, userId));
			//对float进行格式控制，只要两位小数
			DecimalFormat df = new DecimalFormat();
			String style="0.00";
			df.applyPattern(style);
			float allCount = Float.valueOf(pCount);
			po.setProductOrderAllCount(allCount);
			po.setProductOrderState(ProductOrderStatus.UNPAY);
			po.setShippingAddId(Integer.valueOf(saId));
			po.setUserId(userId);
			po.setProductOrderPPrice(allCount);
			po.setProductOrderCredictUsed(0);
			short productNum = 0;
			for(int i=0;i<pIds.length;i++){
				/*插入订单详情信息*/
				productNum+=Short.valueOf(pNums[i]);
				ProductOrderDetail pod = new ProductOrderDetail();
				pod.setPoNum(po.getProductOrderNum());
				pod.setProductId(Integer.valueOf(pIds[i]));
				pod.setProductNum(Short.valueOf(pNums[i]));
				if(podDao.insertPOD(pod)){
					if(resource.equals("sc")){
						 if(shoppingCartDao.deleteSCByUserIdAndPId(userId,pod.getProductId())){
							 continue;
						 }else{
							 return 0;
						 }
					}
					continue;
				}else{
					return 0;
				}
			}
			po.setProductsNum(productNum);//这里存的商品的总数量
			if(!productOrderDao.insertPO(po)){
				return 0;
			}
			return po.getProductOrderId();
		}
	}

	@Override
	public List<HashMap<String, Object>> getPOByUserIdAndStatus(int userId,
			short status) {
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		List<ProductOrder> poList = productOrderDao.getByUserIdAndStatus(userId, status);
		for(int i=0;i<poList.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			List<Object> podList = podDao.getByPONum(poList.get(i).getProductOrderNum());
			map.put("po", poList.get(i));
			map.put("podInfo", podList);
			map.put("podNum", podList.size());
			list.add((HashMap<String, Object>) map);
		}
		return list;
	}

	@Override
	public List<Object> getPODByUserIdAndStatus(int userId, short status) {
		return podDao.getByUserIdAndStatus(userId, status);
	}

	
	@Override
	public boolean cancelOrder(int poId) {
		return productOrderDao.deletePOByPOId(poId);
	}

	@Override
	public List<Object> getSCByUserId(int userId) {
		return shoppingCartDao.getAllByUserId(userId);
	}

	@Override
	public boolean deleteSCBySCId(int scId) {
		return shoppingCartDao.deleteSCById(scId);
	}

	@Override
	public List<HashMap<String, Object>> generatePOByPIdAndPNum(String pId,
			String pNum) {
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		String pIds[] = pId.split("_");
		String pNums[] = pNum.split("_");
		//float count=0;
		for(int i=0;i<pIds.length;i++){
			Map<String,Object> map = new HashMap<String,Object>();
			System.out.println("------------------"+pIds[i]+"------------------");
			Product product = getProductById(Integer.valueOf(pIds[i]));
			map.put("product", product);
			map.put("pNum", pNums[i]);
			//count+=(product.getProductPrice() * Integer.valueOf(pNums[i]));
			list.add((HashMap<String, Object>) map);
		}
		
		return list;
	}

	@Override
	public List<Object> getPODInfoByPODId(int podId) {
		return  podDao.getPODInfoByPODId(podId);
	}

	@Override
	public boolean insertProductComment(int userId,String pId, String podId,
			String detailInfo, String picPaths) {
		ProductOrderDetail pod = podDao.getPODByPODId(Integer.valueOf(podId));
		ProductComment pc = new ProductComment();
		pc.setPodId(pod.getPoDetailId());
		pc.setProductId(Integer.valueOf(pId));
		pc.setUserId(userId);
		pc.setProductComContent(detailInfo);
		if(!productCommentDao.insertPC(pc)){
			return false;
		}
		//把评论图片插入到图片表中
		if(picPaths!=""){
			String[] arrPicPaths = picPaths.split(";");
			for(int i=0;i<arrPicPaths.length;i++){
				ProductPicture pp = new ProductPicture();
				pp.setProductId(pc.getProductComId());
				pp.setProductPicPath(arrPicPaths[i]);
				pp.setProductPicType(PictureType.COMMENTPICTURE);
				if(!insertProductPicture(pp)){
					return false;
				}
			}
		}
		//更改ProductOrderDetail中的状态
		pod.setProductIsComment(true);
		if(podDao.updatePOD(pod)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean insertProductPicture(ProductPicture pp) {
		return ppDao.insertPP(pp);
	}

	@Override
	public List<HashMap<String, Object>> getProductCommentByUserId(int userId) {
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		List<Object[]> pcInfoList = productCommentDao.getPCInfoByUserId(userId);
		for(int i=0;i<pcInfoList.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pcInfo", pcInfoList.get(i));
			List<ProductPicture> ppList = ppDao.getByPODId((Integer) pcInfoList.get(i)[3]);
			map.put("ppList", ppList);
			list.add((HashMap<String, Object>) map);
		}
		return list;
	}

	@Override
	public List<HashMap<String, Object>> getProductCommentByPId(int pId) {
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		List<Object[]> pcInfoList = productCommentDao.getPCByPId(pId);
		for(int i=0;i<pcInfoList.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pcInfo", pcInfoList.get(i));
			List<ProductPicture> ppList = ppDao.getByPODId((Integer) pcInfoList.get(i)[0]);
			map.put("ppList", ppList);
			list.add((HashMap<String, Object>) map);
		}
		return list;
	}

	@Override
	public List<ProductOrderDetail> getByPOId(int poId) {
		ProductOrder po= productOrderDao.getPOByPOId(poId);
		List<ProductOrderDetail> list = podDao.getAllByPONum(po.getProductOrderNum());
		return list;
	}

	@Override
	public ProductOrder getProductOrderById(Integer id) {
		// TODO Auto-generated method stub
		return productOrderDao.getPOByPOId(id);
	}

	@Override
	public void updateProductOrder(ProductOrder productOrder) {
		// TODO Auto-generated method stub
		productOrderDao.updatePO(productOrder);
	}

	@Override
	public boolean updateProductOrderStatus(int poId, short status) {
		ProductOrder po = productOrderDao.getPOByPOId(poId);
		po.setProductOrderState(status);
		if(productOrderDao.updatePO(po)){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public List<ProductOrder> getPayedByUserId(int userId) {
		return productOrderDao.getPayedByUserId(userId);
	}

	@Override
	public List<Product> getProductsByPOId(int poId) {
		return productOrderDao.getProductsByPOId(poId);
	}

	@Override
	public List<ProductOrderDetail> getPODByPONum(String poNum) {
		return podDao.getAllByPONum(poNum);
	}

	@Override
	public String getStockInfoByPOId(int poId) {
		String result = "";
		ProductOrder po = getProductOrderById(poId);
		List<Product> pList = getProductsByPOId(poId);
		List<ProductOrderDetail> podList = getPODByPONum(po.getProductOrderNum());
		for(int i=0;i<pList.size();i++){
			if(podList.get(i).getProductNum()>(pList.get(i).getProductStock()-pList.get(i).getProductSaleNum())){
				Product p = getProductById(podList.get(i).getProductId());
				result=p.getProductName()+"已售完";
				return result;
			}
		}
		synchronized (this) {
			for(int i=0;i<pList.size();i++){
				Product p = getProductById(podList.get(i).getProductId());
				int newSaleNum = p.getProductSaleNum()
						+ podList.get(i).getProductNum();
				p.setProductSaleNum(newSaleNum);
				if (newSaleNum == p.getProductStock()) {
					p.setProductIsOver(true);
					System.out.println("该产品已销售完");
				}
				if (productDao.updateProduct(p)) {
					System.out.println("更新成功");
				} else {
					System.out.println("更新失败");
				}
			}
		}
		return result;
	}

	@Override
	public int duihuan(int userId, String saId, String pId, String pPrice,String pRealCount, String credictUsed) {
		int result;
		/**在这下面添加上检测该用户的积分是否满足兑换条件，不满足的话，请把result的值设为no，并立即return，否则顺序执行下面的代码**/
		LovingPeople lovingPeople  = userService.searchLpInfoByUserID(userId);
		if(Integer.valueOf(credictUsed) > lovingPeople.getLoveCredicts()){
			result = -1;//-1表示积分不够
			return result;
		}
		synchronized(this){
			Product p = productDao.getProductById(Integer.valueOf(pId));
			if(p.getProductIsOver()==true){
				result = -2;
			}else{
				int newSaleNum = p.getProductSaleNum()+1;
				p.setProductSaleNum(newSaleNum);
				if (newSaleNum == p.getProductStock()) {
					p.setProductIsOver(true);
				}
				productDao.updateProduct(p);
				ProductOrder po = new ProductOrder();
				po.setProductOrderNum(GenerateOrderNumber.Generate(0, userId));
				//兑换商品分两种情况，如果是完全兑换的情况，订单状态为已支付，否则订单状态为未支付
				if(pRealCount.equals("0.0")){
					po.setProductOrderState(ProductOrderStatus.PAIED);
					//完全兑换，不需要支付，则需要在此对用户积分信息做出更改
					int num = lovingPeople.getLoveCredicts()
							- Integer.valueOf(credictUsed);
					lovingPeople.setLoveCredicts((short) num);
					userService.updataLovingPeopleDetailInfo(lovingPeople);
		
					Credicts credicts = new Credicts();
					credicts.setCredictsImg(p.getProductMainpicPath()); // 图片
					credicts.setCredictsIsDelete(false);// 删除标记
					credicts.setCredictsChanges("-"
							+ credictUsed);// 积分变化
					credicts.setCredictsDate(new Date());// 日期
					credicts.setUserId(userId); // 用户ID
					credicts.setProductId(p.getProductId());// 物品Id
					credicts.setCredictsType(1);// 积分类型 1：支出 0：收入
					credicts.setCredictsRemark("交易使用");// 物品名称
					credicts.setCredictsName(p.getProductName());// 备注 交易使用
					credictsService.insertOneData(credicts);
				}else{
					po.setProductOrderState(ProductOrderStatus.UNPAY);
				}
				po.setShippingAddId(Integer.valueOf(saId));
				po.setUserId(userId);
				po.setProductsNum((short) 1);
				po.setProductOrderAllCount(Float.valueOf(pRealCount));
				po.setProductOrderPPrice(Float.valueOf(pPrice));
				po.setProductOrderCredictUsed(Integer.valueOf(credictUsed));
				ProductOrderDetail pod = new ProductOrderDetail();
				pod.setPoNum(po.getProductOrderNum());
				pod.setProductId(Integer.valueOf(Integer.valueOf(pId)));
				pod.setProductNum((short) 1);
				if(productOrderDao.insertPO(po) && podDao.insertPOD(pod)){
					result=po.getProductOrderId();
				}else{
					result=0;//0表示出错了
				}
			}
		}
		return result;
	}

	@Override
	public String duihuan_paycancel(int pId) {
		Product p = productDao.getProductById(pId);
		int newSaleNum = p.getProductSaleNum()-1;
		p.setProductSaleNum(newSaleNum);
		p.setProductIsOver(false);
		if(productDao.updateProduct(p))
			return "true";
		else 
			return "fail";
	}

	@Override
	public String payment_cancel(String pId, String pNum) {
		String pIds[] = pId.split("_");
		String pNums[] = pNum.split("_");
		for(int i=0;i<pIds.length;i++){
			Product product = productDao.getProductById(Integer.valueOf(pIds[i]));
			int newSaleNum = product.getProductSaleNum()-Integer.valueOf(pNums[i]);
			product.setProductSaleNum(newSaleNum);
			product.setProductIsOver(false);
			if(!productDao.updateProduct(product))
				return "fail";
		}
		return "true";
	}

	@Override
	public String payment_order_cancel(int poId) {
		ProductOrder po = getProductOrderById(poId);
		List<Product> pList = getProductsByPOId(poId);
		List<ProductOrderDetail> podList = getPODByPONum(po.getProductOrderNum());
		for(int i=0;i<pList.size();i++){
			Product p = getProductById(podList.get(i).getProductId());
			int newSaleNum = p.getProductSaleNum()
					- podList.get(i).getProductNum();
			p.setProductSaleNum(newSaleNum);
			p.setProductIsOver(false);
			if (!productDao.updateProduct(p)) 
				return  "fail";
		}
		return "true";
	}
}
