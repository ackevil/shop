package com.huituopin.aigou.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.aigou.dao.IProductDao;
import com.huituopin.aigou.entity.Product;
import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.common.utils.Page;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product, String> implements IProductDao {

	@Override
	public boolean addProduct(Product product) {

		try {
			save(product);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProduct(Page page) {
			String sql = "from Product where productIsDelete = 0";
			    Query query = createQuery(sql);
		        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
		        query.setMaxResults(page.getPageSizes());
		        
		        String sqlCount = "select count(*) from Product where product_is_delete = 0";
		        Query queryCount = this.createSQLQuery(sqlCount); 
		        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));

		        List<Product> list= (List<Product>) query.list();
				if(list.size()!=0)
					return list;
				else
					return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getSellingProduct(Page page) {
		String sql = "from Product where productIsDelete = 0 and productOnsaleTimeType = 1 and productStock != productSaleNum ";
	    Query query = createQuery(sql);
        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
        query.setMaxResults(page.getPageSizes());
        
        String sqlCount = "select count(*) from Product where product_is_delete = 0 and product_onsale_time_type = 1 and product_stock != product_sale_num";
        Query queryCount = this.createSQLQuery(sqlCount); 
        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));

        List<Product> list= (List<Product>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getSelledProduct(Page page) {
		String sql = "from Product  where productIsDelete = 0 and productOnsaleTimeType = 1 and productIsOver = 1 ";
	    Query query = createQuery(sql);
        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
        query.setMaxResults(page.getPageSizes());
        
        //String sqlCount = "select count(*) from Product where product_is_delete = 0 and product_onsale_time_type = 1 and product_stock = product_sale_num";
        String sqlCount = "select count(*) from Product where product_is_delete = 0 and product_onsale_time_type = 1 and product_is_over = 1";
        Query queryCount = this.createSQLQuery(sqlCount); 
        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));

        List<Product> list= (List<Product>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getSellProduct(Page page) {
		String sql = "from Product where productIsDelete = 0 and productOnsaleTimeType = 3";
	    Query query = createQuery(sql);
        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
        query.setMaxResults(page.getPageSizes());
        
        String sqlCount = "select count(*) from Product where product_is_delete = 0 and product_onsale_time_type =3";
        Query queryCount = this.createSQLQuery(sqlCount); 
        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));

        List<Product> list= (List<Product>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getSellingProductByOptions(Page page,
			Date productLaunchTime, Date productStopTime, Integer type,
			String key) {
		  
				String sql="from Product where  productIsDelete = 0 and productOnsaleTimeType = 1 and productStock != productSaleNum ";
				String sqlCount="select count(*) from Product where product_is_delete = 0 and product_onsale_time_type = 1 and product_stock != product_sale_num";
				if(productLaunchTime!=null&&productStopTime!=null){
						sql+=" and productIntime between ? and ? ";
						sqlCount+=" and product_intime between ? and ? ";
				}
				
				if(type!=null){
					sql+=" and productCategoryId="+type;
					sqlCount+=" and product_category_id="+type;
				}
				if(key!=null){
					sql+=" and productName like '%"+key+"%'";
					sqlCount+=" and product_name like '%"+key+"%'";
				}
				
				System.out.println(sql);
				Query query=createQuery(sql);
				if(productLaunchTime!=null&&productStopTime!=null)
				{
					query.setDate(0, productLaunchTime);
					query.setDate(1, productStopTime);
				}

				query.setFirstResult((page.getPage()-1)*page.getPageSizes());
				query.setMaxResults(page.getPageSizes());
				
				
				Query queryCount=createSQLQuery(sqlCount);
				if(productLaunchTime!=null&&productStopTime!=null)
				{
					queryCount.setDate(0, productLaunchTime);
					queryCount.setDate(1, productStopTime);
				}
				page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
				List<Product> list= (List<Product>)query.list();
				if(list.size()!=0){
					return list;
				}else{
					return null;
				}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getSelledProductByOptions(Page page,
			Date productLaunchTime, Date productStopTime, Integer type,
			String key) {
		String sql="from Product where  productIsDelete = 0 and productOnsaleTimeType = 2 and productStock = productSaleNum ";
		String sqlCount="select count(*) from Product where product_is_delete = 0 and product_onsale_time_type = 2 and product_stock = product_sale_num";
		if(productLaunchTime!=null&&productStopTime!=null){
				sql+=" and productIntime between ? and ? ";
				sqlCount+=" and product_intime between ? and ? ";
		}
		
		if(type!=null){
			sql+=" and productCategoryId="+type;
			sqlCount+=" and product_category_id="+type;
		}
		if(key!=null){
			sql+=" and productName like '%"+key+"%'";
			sqlCount+=" and product_name like '%"+key+"%'";
		}
		
		System.out.println(sql);
		Query query=createQuery(sql);
		if(productLaunchTime!=null&&productStopTime!=null)
		{
			query.setDate(0, productLaunchTime);
			query.setDate(1, productStopTime);
		}

		query.setFirstResult((page.getPage()-1)*page.getPageSizes());
		query.setMaxResults(page.getPageSizes());
		
		
		Query queryCount=createSQLQuery(sqlCount);
		if(productLaunchTime!=null&&productStopTime!=null)
		{
			queryCount.setDate(0, productLaunchTime);
			queryCount.setDate(1, productStopTime);
		}
		page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
		List<Product> list= (List<Product>)query.list();
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getSellProductByOptions(Page page,
			Date productLaunchTime, Date productStopTime, Integer type,
			String key) {
		String sql="from Product where  productIsDelete = 0 and productOnsaleTimeType = 3 ";
		String sqlCount="select count(*) from Product where product_is_delete = 0 and product_onsale_time_type = 3";
		if(productLaunchTime!=null&&productStopTime!=null){
				sql+=" and productIntime between ? and ? ";
				sqlCount+=" and product_intime between ? and ? ";
		}
		
		if(type!=null){
			sql+=" and productCategoryId="+type;
			sqlCount+=" and product_category_id="+type;
		}
		if(key!=null){
			sql+=" and productName like '%"+key+"%'";
			sqlCount+=" and product_name like '%"+key+"%'";
		}
		
		System.out.println(sql);
		Query query=createQuery(sql);
		if(productLaunchTime!=null&&productStopTime!=null)
		{
			query.setDate(0, productLaunchTime);
			query.setDate(1, productStopTime);
		}

		query.setFirstResult((page.getPage()-1)*page.getPageSizes());
		query.setMaxResults(page.getPageSizes());
		
		
		Query queryCount=createSQLQuery(sqlCount);
		if(productLaunchTime!=null&&productStopTime!=null)
		{
			queryCount.setDate(0, productLaunchTime);
			queryCount.setDate(1, productStopTime);
		}
		page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
		List<Product> list= (List<Product>)query.list();
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public Product getProductById(Integer id) {
	
		List<Product> ls = findBy("productId",id);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public void delProduct(Product product) {
		product.setProductIsDelete(true);
		update(product);
	}

	@Override
	public boolean updateProduct(Product product) {
	
		try {
			update(product);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	/*public List<Object> getAllProduct(int type,String keyword,int start,int num) {
		StringBuilder sql = new StringBuilder("select p.product_Id,p.product_name,p.product_price,p.product_mainpic_path,p.product_credit from Product p where p.product_is_delete = false and p.product_is_over = false ");
		if(type!=0){
			sql.append(" and p.product_category_id = "+ type);
		}
		if(keyword!=null){
			sql.append(" and p.product_name like '%"+keyword+"%'");
		}
		sql.append(" order by p.product_intime desc");
		Query query = createSQLQuery(sql.toString());
		query.setFirstResult(start);
		query.setMaxResults(num);
		List<Object> list= (List<Object>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}*/
	public List<Object> getAllProduct(int type,String keyword) {
		StringBuilder sql = new StringBuilder("select p.product_Id,p.product_name,p.product_price,p.product_mainpic_path,p.product_credit from Product p where p.product_is_delete = false and p.product_is_over = false and p.product_onsale_time_type = 1");
		if(type!=0){
			sql.append(" and p.product_category_id = "+ type);
		}
		if(keyword!=null){
			sql.append(" and p.product_name like '%"+keyword+"%'");
		}
		sql.append(" order by p.product_intime desc");
		Query query = createSQLQuery(sql.toString());
		List<Object> list= (List<Object>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}
	@Override
	public Product getProductById(int pId) {
		System.out.println("-------------------"+pId+"---------------");
		List<Product> list = findBy("productId",pId);
		if(list!=null){
			return list.get(0);
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getProductByPC(int pageNo, int pageSize, int type) {
		StringBuilder sql = new StringBuilder("select p.product_Id,p.product_name,p.product_price,p.product_mainpic_path,p.product_credit from Product p where p.product_is_delete = false and p.product_is_over = false and p.product_onsale_time_type = 1");
		if(type != 0){
			sql.append(" and p.product_category_id = " + type);
		}
		sql.append(" order by p.product_intime desc");
	    Query query = createSQLQuery(sql.toString());
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        
        List<Object> list= (List<Object>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}
}
