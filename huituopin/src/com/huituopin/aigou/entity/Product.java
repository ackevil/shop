package com.huituopin.aigou.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Product")
public class Product implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private String productName;
	private short productCategoryId;
	private float productPrice;
	private Integer productCredit;
	private String productMainpicPath;
	
	private String productPic1Path;
	private String productPic2Path;
	private String productPic3Path;
	
	
	private String productSpecificationName;
	private String productDetail;
	private String productSpecificationValue;
	private Integer productStock;
	private Integer productSaleNum;
	private boolean productIsDispalyStock;
	private boolean productFreightType;
	private float productFreightSame;
	private short productFreightTemplateId;
	private short productStopbuyNum;
	private short productOnsaleTimeType;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date productOnsaleTiming;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date productIntime;
	private boolean productIsDelete;
	private boolean productIsOver;
	

	private String productC1;
	private String productC2;

	// Constructors

	/** default constructor */
	public Product() {
		this.productSaleNum=0;
		this.productIsDelete=false;
		this.productIsOver=false;
	}

	/** minimal constructor */
	public Product(String productName, short productCategoryId,
			float productPrice, Integer productStock,
			boolean productIsDispalyStock, short productOnsaleTimeType,
			Date productIntime, boolean productIsDelete) {
		this.productName = productName;
		this.productCategoryId = productCategoryId;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productIsDispalyStock = productIsDispalyStock;
		this.productOnsaleTimeType = productOnsaleTimeType;
		this.productIntime = productIntime;
		this.productIsDelete = productIsDelete;
		this.productIsOver=false;
	}

	/** full constructor */
	public Product(String productName, short productCategoryId,
			float productPrice, Integer productCredit,
			String productMainpicPath, String productSpecificationName,
			String productDetail, String productSpecificationValue,
			Integer productStock, Integer productSaleNum,
			boolean productIsDispalyStock, boolean productFreightType,
			float productFreightSame, short productFreightTemplateId,
			short productStopbuyNum, short productOnsaleTimeType,
			Date productOnsaleTiming, Date productIntime,
			boolean productIsDelete, String productC1, String productC2
			) {
		this.productName = productName;
		this.productCategoryId = productCategoryId;
		this.productPrice = productPrice;
		this.productCredit = productCredit;
		this.productMainpicPath = productMainpicPath;
		this.productSpecificationName = productSpecificationName;
		this.productDetail = productDetail;
		this.productSpecificationValue = productSpecificationValue;
		this.productStock = productStock;
		this.productSaleNum = productSaleNum;
		this.productIsDispalyStock = productIsDispalyStock;
		this.productFreightType = productFreightType;
		this.productFreightSame = productFreightSame;
		this.productFreightTemplateId = productFreightTemplateId;
		this.productStopbuyNum = productStopbuyNum;
		this.productOnsaleTimeType = productOnsaleTimeType;
		this.productOnsaleTiming = productOnsaleTiming;
		this.productIntime = productIntime;
		this.productIsDelete = productIsDelete;
		this.productIsOver=false;
		this.productC1 = productC1;
		this.productC2 = productC2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "product_Id", unique = true, nullable = false)
	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name = "product_name", nullable = false, length = 200)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "product_category_id", nullable = false)
	public short getProductCategoryId() {
		return this.productCategoryId;
	}

	public void setProductCategoryId(short productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	@Column(name = "product_price", nullable = false, precision = 12, scale = 0)
	public float getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	@Column(name = "product_credit")
	public Integer getProductCredit() {
		return this.productCredit;
	}

	public void setProductCredit(Integer productCredit) {
		this.productCredit = productCredit;
	}

	@Column(name = "product_mainpic_path", length = 200)
	public String getProductMainpicPath() {
		return this.productMainpicPath;
	}

	public void setProductMainpicPath(String productMainpicPath) {
		this.productMainpicPath = productMainpicPath;
	}
	@Column(name = "product_pic1_path", length = 200)
	public String getProductPic1Path() {
		return productPic1Path;
	}

	public void setProductPic1Path(String productPic1Path) {
		this.productPic1Path = productPic1Path;
	}
	@Column(name = "product_pic2_path", length = 200)
	public String getProductPic2Path() {
		return productPic2Path;
	}

	public void setProductPic2Path(String productPic2Path) {
		this.productPic2Path = productPic2Path;
	}
	@Column(name = "product_pic3_path", length = 200)
	public String getProductPic3Path() {
		return productPic3Path;
	}

	public void setProductPic3Path(String productPic3Path) {
		this.productPic3Path = productPic3Path;
	}
	@Column(name = "product_specification_name", length = 100)
	public String getProductSpecificationName() {
		return this.productSpecificationName;
	}

	public void setProductSpecificationName(String productSpecificationName) {
		this.productSpecificationName = productSpecificationName;
	}

	@Column(name = "product_detail")
	public String getProductDetail() {
		return this.productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	@Column(name = "product_specification_value", length = 100)
	public String getProductSpecificationValue() {
		return this.productSpecificationValue;
	}

	public void setProductSpecificationValue(String productSpecificationValue) {
		this.productSpecificationValue = productSpecificationValue;
	}

	@Column(name = "product_stock", nullable = false)
	public Integer getProductStock() {
		return this.productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	@Column(name = "product_sale_num")
	public Integer getProductSaleNum() {
		return this.productSaleNum;
	}

	public void setProductSaleNum(Integer productSaleNum) {
		this.productSaleNum = productSaleNum;
	}

	@Column(name = "product_isDispaly_stock", nullable = false)
	public boolean getProductIsDispalyStock() {
		return this.productIsDispalyStock;
	}

	public void setProductIsDispalyStock(boolean productIsDispalyStock) {
		this.productIsDispalyStock = productIsDispalyStock;
	}

	@Column(name = "product_freight_type")
	public boolean getProductFreightType() {
		return this.productFreightType;
	}

	public void setProductFreightType(boolean productFreightType) {
		this.productFreightType = productFreightType;
	}

	@Column(name = "product_freight_same", precision = 12, scale = 0)
	public float getProductFreightSame() {
		return this.productFreightSame;
	}

	public void setProductFreightSame(float productFreightSame) {
		this.productFreightSame = productFreightSame;
	}

	@Column(name = "product_freight_template_id")
	public short getProductFreightTemplateId() {
		return this.productFreightTemplateId;
	}

	public void setProductFreightTemplateId(short productFreightTemplateId) {
		this.productFreightTemplateId = productFreightTemplateId;
	}

	@Column(name = "product_stopbuy_num")
	public short getProductStopbuyNum() {
		return this.productStopbuyNum;
	}

	public void setProductStopbuyNum(short productStopbuyNum) {
		this.productStopbuyNum = productStopbuyNum;
	}

	@Column(name = "product_onsale_time_type", nullable = false)
	public short getProductOnsaleTimeType() {
		return this.productOnsaleTimeType;
	}

	public void setProductOnsaleTimeType(short productOnsaleTimeType) {
		this.productOnsaleTimeType = productOnsaleTimeType;
	}

	@Column(name = "product_onsale_timing", length = 19)
	public Date getProductOnsaleTiming() {
		return this.productOnsaleTiming;
	}

	public void setProductOnsaleTiming(Date productOnsaleTiming) {
		this.productOnsaleTiming = productOnsaleTiming;
	}

	@Column(name = "product_intime", nullable = false, length = 19)
	public Date getProductIntime() {
		return this.productIntime;
	}

	public void setProductIntime(Date productIntime) {
		this.productIntime = productIntime;
	}

	@Column(name = "product_is_delete", nullable = false)
	public boolean getProductIsDelete() {
		return this.productIsDelete;
	}

	public void setProductIsDelete(boolean productIsDelete) {
		this.productIsDelete = productIsDelete;
	}
	@Column(name = "product_is_over")
	public boolean getProductIsOver() {
		return productIsOver;
	}

	public void setProductIsOver(boolean productIsOver) {
		this.productIsOver = productIsOver;
	}

	@Column(name = "product_c1", length = 200)
	public String getProductC1() {
		return this.productC1;
	}

	public void setProductC1(String productC1) {
		this.productC1 = productC1;
	}

	@Column(name = "product_c2", length = 200)
	public String getProductC2() {
		return this.productC2;
	}

	public void setProductC2(String productC2) {
		this.productC2 = productC2;
	}


	


}