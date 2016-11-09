package com.huituopin.wechat.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huituopin.activity.dao.IActivitySupportDao;
import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.activity.service.IActivityService;
import com.huituopin.activity.service.IActivitySupportService;
import com.huituopin.activity.service.impl.ActivityServiceImpl;
import com.huituopin.aigou.entity.Product;
import com.huituopin.aigou.entity.ProductOrder;
import com.huituopin.aigou.entity.ProductOrderDetail;
import com.huituopin.aigou.service.IAiGouService;
import com.huituopin.aigou.service.IProductService;
import com.huituopin.bank.dao.ICredictsDao;
import com.huituopin.bank.entity.Credicts;
import com.huituopin.bank.service.ICredictsService;
import com.huituopin.common.constants.ProductOrderStatus;
import com.huituopin.dsze.entity.Dsze;
import com.huituopin.dsze.entity.DszeSupport;
import com.huituopin.dsze.service.IDszeService;
import com.huituopin.dsze.service.IDszeSupportService;
import com.huituopin.user.dao.ILovingPeopleDao;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.service.IUserService;
import com.huituopin.wechat.core.service.PayAPI;
import com.huituopin.wechat.core.service.WechatConfig;
import com.huituopin.wechat.core.utils.PayUtil;
import com.huituopin.wechat.entity.BaseResult;
import com.huituopin.wechat.entity.PayNotify;
import com.huituopin.wechat.entity.Unifiedorder;
import com.huituopin.wechat.entity.UnifiedorderResult;
import com.huituopin.wechat.http.HttpEngine;
import com.huituopin.yjya.dao.IRuleDao;
import com.huituopin.yjya.entity.Rule;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/*
 * 微信支付
 */
@Controller
@RequestMapping("/wexinPay")
public class PayController {

	@Autowired
	private IActivitySupportService activitySupportService;

	@Autowired
	private IActivityService activityService;

	@Autowired
	private IDszeSupportService dszeSupportService;

	@Autowired
	private IDszeService dszeService;
	@Autowired
	private IAiGouService aiGouService;
	@Autowired
	private IProductService productService;

	@Autowired
	private IRuleDao ruleDao;
	@Autowired
	private ICredictsService credictsService;
	@Autowired
	private IUserService userService;

	/*
	 * 微信网页内支付接口 ,需要传递参数 1. 商品信息 2. 价格
	 */
	@RequestMapping("/webPay")
	@ResponseBody
	public String activitySupportPay(HttpServletRequest request, Model model) {
		WechatConfig wechatConfig = WechatConfig.getInstance();

		String appid = wechatConfig.getAppID(); // appid
		System.out.println("1" + appid);
		String mch_id = wechatConfig.getMch_id(); // 微信支付商户号
		String key = wechatConfig.getKey();// API密钥
		String notify_url = wechatConfig.getNotify_url();
		String openid = (String) request.getSession().getAttribute("openid");
		System.out.println("2" + openid);
		String totalFee = request.getParameter("price");
		float tf = Float.parseFloat(totalFee) * 100;
		totalFee = Integer.toString((int) tf);
		String body = request.getParameter("body");
		String detail = request.getParameter("detail");
		String attach = request.getParameter("attach");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String Out_trade_no = ymd + String.valueOf(System.currentTimeMillis());
		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder
				.setNonce_str(UUID.randomUUID().toString().replace("-", ""));
		unifiedorder.setBody(body);
		unifiedorder.setDetail(detail);
		unifiedorder.setAttach(attach);
		unifiedorder.setOut_trade_no(Out_trade_no);
		unifiedorder.setTotal_fee(totalFee);// 单位分
		unifiedorder.setSpbill_create_ip(request.getRemoteAddr());// IP
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type("JSAPI");// JSAPI，NATIVE，APP，WAP
		unifiedorder.setOpenid(openid);
		String json = "";

		try {
			// 1返回统一订单结果对象
			UnifiedorderResult unifiedorderResult = PayAPI.payUnifiedorder(
					unifiedorder, key);
			System.out.println("6" + unifiedorderResult);
			// 2 根据统一订单结果 prepay_id 等，返回请求支付的 json 数据
			json = PayUtil.generateMchPayJsRequestJson(
					unifiedorderResult.getPrepay_id(), appid, key);
			System.out.println(json);
		} catch (Exception e) {
		}

		return json;

	}

	@RequestMapping("payTest")
	public String test() {
		ActivitySupport activitySupport = new ActivitySupport();
		activitySupport.setActivityId(1);
		activitySupport.setActSupComment("哈哈");
		activitySupport.setActSupMoney(120);
		activitySupport.setUserId(46);
		activitySupportService.addActivitySupport(activitySupport);
		return "/wechat/error";
	}

	@RequestMapping("/payCall")
	public void payCall(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		BufferedReader bf = new BufferedReader(new InputStreamReader(
				request.getInputStream(), "UTF-8"));
		// 最好在将字节流转换为字符流的时候 进行转码
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = bf.readLine()) != null) {
			buffer.append(line);
		}
		bf.close();
		String xml = buffer.toString();
		System.out.println("支付测试:1" + xml);

		XStream xs1 = new XStream(new DomDriver());
		xs1.alias("xml", PayNotify.class);
		PayNotify reData = (PayNotify) xs1.fromXML(xml);
		System.out.println(reData.toString());

		JSONObject json = new JSONObject(reData.getAttach());

		System.out.println("支付测试:2" + json.toString());

		if (json.get("type").equals("activity")) {

			int activityId = json.getInt("id");
			int userId = json.getInt("userId");
			String activityName = json.getString("name");
			String comment = json.getString("comment");
			Integer price = reData.getTotal_fee() / 100; // 捐助的钱
			String actSupOrder = reData.getOut_trade_no();// 订单号

			synchronized (PayController.class) {
				boolean flag = activitySupportService.checkOrder(actSupOrder);

				if (flag) {
					BaseResult baseResult = new BaseResult();
					baseResult.setReturn_code("SUCCESS");
					baseResult.setReturn_msg("OK");
					XStream xs2 = new XStream(new DomDriver());
					xs2.alias("xml", BaseResult.class);
					String result = xs2.toXML(baseResult);
					System.out.println("支付测试:5" + result);
					response.getOutputStream().write(result.getBytes("UTF-8"));
				} else {
					ActivitySupport activitySupport = new ActivitySupport();
					activitySupport.setActivityId(activityId);
					activitySupport.setActivityName(activityName);
					activitySupport.setActSupComment(comment);
					activitySupport.setActSupMoney(price);
					activitySupport.setUserId(userId);
					activitySupport.setActSupOrder(actSupOrder);
					activitySupport.setActSupTime(new Date());
					System.out.println("支付测试:4" + activityId + comment + price
							+ userId);

					Activity activity = activityService
							.getActivityById(activityId);
					Integer activityRaised = activity.getActivityRaised();
					Integer sum = activity.getActivitySuportNum();
					if (activityRaised != null) {
						activity.setActivityRaised(activityRaised + price);
					}
					if (sum != null) {
						activity.setActivitySuportNum(sum + 1);
					}

					if (activitySupportService
							.addActivitySupport(activitySupport)) {

						activityService.updateActivity(activity);

						// 对相应的积分改变
						LovingPeople lovingPeople = userService
								.searchLpInfoByUserID(userId);
						Rule rule = ruleDao.getRuleById(5);
						int num = lovingPeople.getLoveCredicts()
								+ rule.getGetCredictNum();
						lovingPeople.setLoveCredicts((short) num);
						userService.updataLovingPeopleDetailInfo(lovingPeople);

						Credicts credicts = new Credicts();
						credicts.setCredictsImg(activity.getActivityMainPic()); // 图片
						credicts.setCredictsIsDelete(false);// 删除标记
						credicts.setCredictsChanges("+"
								+ rule.getGetCredictNum());// 积分变化
						credicts.setCredictsDate(new Date());// 日期
						credicts.setUserId(userId); // 用户ID
						credicts.setProductId(activity.getActivityId());// 物品Id
						credicts.setCredictsType(0);// 积分类型 1：支出 0：收入
						credicts.setCredictsRemark("捐助重大救助");// 备注 捐助获得
						credicts.setCredictsName(activity.getActivityName());// 物品名称
						credictsService.insertOneData(credicts);

						BaseResult baseResult = new BaseResult();
						baseResult.setReturn_code("SUCCESS");
						baseResult.setReturn_msg("OK");
						XStream xs2 = new XStream(new DomDriver());
						xs2.alias("xml", BaseResult.class);
						String result = xs2.toXML(baseResult);
						System.out.println("支付测试:5" + result);
						response.getOutputStream().write(
								result.getBytes("UTF-8"));
					}
				}

			}
		}
		if (json.get("type").equals("dsze")) {

			int dszeId = json.getInt("id");
			int userId = json.getInt("userId");
			String dszeName = json.getString("name");
			String comment = json.getString("comment");
			Integer price = reData.getTotal_fee() / 100; // 捐助的钱
			String dszeSupOrder = reData.getOut_trade_no();// 订单号

			synchronized (PayController.class) {
				boolean flag = dszeSupportService.checkOrder(dszeSupOrder);

				if (flag) {
					BaseResult baseResult = new BaseResult();
					baseResult.setReturn_code("SUCCESS");
					baseResult.setReturn_msg("OK");
					XStream xs2 = new XStream(new DomDriver());
					xs2.alias("xml", BaseResult.class);
					String result = xs2.toXML(baseResult);
					System.out.println("支付测试:5" + result);
					response.getOutputStream().write(result.getBytes("UTF-8"));
				} else {
					DszeSupport dszeSupport = new DszeSupport();
					dszeSupport.setDszeId(dszeId);
					dszeSupport.setDszeName(dszeName);
					dszeSupport.setDszeSupComment(comment);
					dszeSupport.setDszeSupMoney(price);
					dszeSupport.setUserId(userId);
					dszeSupport.setDszeSupOrder(dszeSupOrder);

					Dsze dsze = dszeService.getDszeById(dszeId);
					Integer dszeRaised = dsze.getDszeRaised();
					Integer sum = dsze.getDszeSuportNum();
					if (dszeRaised != null) {
						dsze.setDszeRaised(dszeRaised + price);
					}
					if (sum != null) {
						dsze.setDszeSuportNum(sum + 1);
					}

					if (dszeSupportService.addDszeSupport(dszeSupport)) {

						dszeService.updateDsze(dsze);

						// 对相应的积分改变
						LovingPeople lovingPeople = userService
								.searchLpInfoByUserID(userId);
						Rule rule = ruleDao.getRuleById(5);
						int num = lovingPeople.getLoveCredicts()
								+ rule.getGetCredictNum();
						lovingPeople.setLoveCredicts((short) num);
						userService.updataLovingPeopleDetailInfo(lovingPeople);

						Credicts credicts = new Credicts();
						credicts.setCredictsImg(dsze.getDszeMainPic()); // 图片
						credicts.setCredictsIsDelete(false);// 删除标记
						credicts.setCredictsChanges("+"
								+ rule.getGetCredictNum());// 积分变化
						credicts.setCredictsDate(new Date());// 日期
						credicts.setUserId(userId); // 用户ID
						credicts.setProductId(dsze.getDszeId());// 物品Id
						credicts.setCredictsType(0);// 积分类型 1：支出 0：收入
						credicts.setCredictsRemark("捐助滴水之恩");// 备注 捐助获得
						credicts.setCredictsName(dsze.getDszeName());// 物品名称
						credictsService.insertOneData(credicts);

						BaseResult baseResult = new BaseResult();
						baseResult.setReturn_code("SUCCESS");
						baseResult.setReturn_msg("OK");
						XStream xs2 = new XStream(new DomDriver());
						xs2.alias("xml", BaseResult.class);
						String result = xs2.toXML(baseResult);
						System.out.println("支付测试:5" + result);
						response.getOutputStream().write(
								result.getBytes("UTF-8"));
					}
				}

			}
		}
		if (json.get("type").equals("aigou")) {

			int productOrderId = json.getInt("id");
			String transactionId = reData.getTransaction_id();

			synchronized (PayController.class) {
				ProductOrder productOrder = aiGouService
						.getProductOrderById(productOrderId);
				if (productOrder.getProductOrderState() == ProductOrderStatus.PAIED) {
					BaseResult baseResult = new BaseResult();
					baseResult.setReturn_code("SUCCESS");
					baseResult.setReturn_msg("OK");
					XStream xs2 = new XStream(new DomDriver());
					xs2.alias("xml", BaseResult.class);
					String result = xs2.toXML(baseResult);
					System.out.println("支付测试:5" + result);
					response.getOutputStream().write(result.getBytes("UTF-8"));
				} else {
					LovingPeople lovingPeople = userService
							.searchLpInfoByUserID(productOrder.getUserId());
					productOrder.setProductOrderState(ProductOrderStatus.PAIED);
					productOrder.setTransactionId(transactionId);
					productOrder.setProductOrderPaytime(new Date());

					aiGouService.updateProductOrder(productOrder);
					/**
					 * 更新product数量
					 */

					List<Product> list = aiGouService
							.getProductsByPOId(productOrderId);
					List<ProductOrderDetail> poList = aiGouService
							.getPODByPONum(productOrder.getProductOrderNum());
					/**
					 * 检查该订单是否含有积分兑换，如果包含积分兑换，则扣除爱心人士相应积分
					 */
					if (productOrder.getProductOrderCredictUsed() != 0) {
						int num = lovingPeople.getLoveCredicts()
								- productOrder.getProductOrderCredictUsed();
						lovingPeople.setLoveCredicts((short) num);
						userService.updataLovingPeopleDetailInfo(lovingPeople);

						Credicts credicts = new Credicts();
						credicts.setCredictsImg(list.get(0).getProductMainpicPath()); // 图片
						credicts.setCredictsIsDelete(false);// 删除标记
						credicts.setCredictsChanges("-"
								+ productOrder.getProductOrderCredictUsed());// 积分变化
						credicts.setCredictsDate(new Date());// 日期
						credicts.setUserId(productOrder.getUserId()); // 用户ID
						credicts.setProductId(list.get(0).getProductId());// 物品Id
						credicts.setCredictsType(1);// 积分类型 1：支出 0：收入
						credicts.setCredictsRemark("交易使用");// 物品名称
						credicts.setCredictsName(list.get(0).getProductName());// 备注 交易使用
						credictsService.insertOneData(credicts);
					}

					for (int i = 0; i < list.size(); i++) {
						Product p = list.get(i);
						/*int newSaleNum = p.getProductSaleNum()
								+ poList.get(i).getProductNum();
						p.setProductSaleNum(newSaleNum);
						if (newSaleNum == p.getProductStock()) {
							p.setProductIsOver(true);
							System.out.println("该产品已销售完");
						}
						if (productService.updateProduct(p)) {
							System.out.println("更新成功");
						} else {
							System.out.println("更新失败");
						}*/

						// 对相应的积分改变
						Rule rule = ruleDao.getRuleById(4);
						int num = (lovingPeople.getLoveCredicts() + rule
								.getGetCredictNum()
								* poList.get(i).getProductNum());
						lovingPeople.setLoveCredicts((short) num);
						userService.updataLovingPeopleDetailInfo(lovingPeople);
						Credicts credicts = new Credicts();
						credicts.setCredictsImg(p.getProductMainpicPath()); // 图片
						credicts.setCredictsIsDelete(false);// 删除标记
						credicts.setCredictsChanges("+"
								+ rule.getGetCredictNum());// 积分变化
						credicts.setCredictsDate(new Date());// 日期
						credicts.setUserId(productOrder.getUserId()); // 用户ID
						credicts.setProductId(p.getProductId());// 商品Id
						credicts.setCredictsType(0);// 积分类型 1：支出 0：收入
						credicts.setCredictsRemark("交易获得");// 备注 捐助获得
						credicts.setCredictsName(p.getProductName());// 物品名称
						credictsService.insertOneData(credicts);

					}

					BaseResult baseResult = new BaseResult();
					baseResult.setReturn_code("SUCCESS");
					baseResult.setReturn_msg("OK");
					XStream xs2 = new XStream(new DomDriver());
					xs2.alias("xml", BaseResult.class);
					String result = xs2.toXML(baseResult);
					System.out.println("支付测试:5" + result);
					response.getOutputStream().write(result.getBytes("UTF-8"));
				}
			}

		}
	}

}
