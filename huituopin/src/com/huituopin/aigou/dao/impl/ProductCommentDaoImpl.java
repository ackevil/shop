package com.huituopin.aigou.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.aigou.dao.IProductCommentDao;
import com.huituopin.aigou.entity.ProductComment;
import com.huituopin.aigou.entity.ProductPicture;
import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.common.utils.Page;
@Repository
public class ProductCommentDaoImpl extends BaseDaoImpl<ProductComment, String> implements IProductCommentDao{

	@Override
	public boolean insertPC(ProductComment pc) {
		try {
			save(pc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProductComment getPCByPCId(int pcId) {
		String sql = "from ProductComment pc where pc.productComIsDelete = 0 and pc.productComId = " + pcId;
		Query query = createQuery(sql);
		List<ProductComment> list = query.list();
		if(list != null){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean deletePCByPCId(int pcId) {
		ProductComment pc = getPCByPCId(pcId);
		pc.setProductComIsDelete(true);
		if(updatePC(pc)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updatePC(ProductComment pc) {
		try {
			update(pc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPCByPId(int pId) {
		StringBuilder sql = new StringBuilder("select pc.product_com_id,user.user_wc_nickname,user.user_wc_avatar,pc.product_com_content,p.product_specification_value,pc.product_com_intime from ProductComment pc left join User user on pc.user_id = user.user_id left join Product as p on p.product_id = pc.product_id where pc.product_id = ");
		sql.append(pId);
		sql.append(" order by pc.product_com_intime desc");
		Query query = createSQLQuery(sql.toString());
		List<Object[]> list = query.list();
		if(list != null){
			return list;
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPCInfoByUserId(int userId) {
		StringBuilder sql = new StringBuilder("select p.product_name,p.product_mainpic_path,p.product_specification_value,pc.product_com_id,pc.product_com_content,pc.product_com_intime,p.product_price from ProductComment pc left join Product p on pc.product_id = p.product_id where pc.product_com_is_delete = 0 and pc.user_id = "+userId);
		sql.append(" order by pc.product_com_intime desc");
		Query query = createSQLQuery(sql.toString());
		List<Object[]> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllComment(Page page) {
		StringBuilder sql = new StringBuilder("select pc.product_com_intime,pc.product_com_content,user.user_wc_nickname,p.product_name,p.product_price from ProductComment pc left join User user on user.user_id = pc.user_id left join Product p on p.product_id = pc.product_id where pc.product_com_is_delete = 0 order by pc.product_com_intime desc");
		Query query = createSQLQuery(sql.toString());
        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
        query.setMaxResults(page.getPageSizes());
        
        Query queryCount = this.createSQLQuery(sql.toString()); 
        //page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
        List<Object> list= (List<Object>) query.list();
		if(list.size()!=0){
			page.setMaxRows(queryCount.list().size());
			return list;
		}else{
			page.setMaxRows(0);
			return null;
		}
	}

}
