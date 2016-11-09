package com.huituopin.yjya.dao;

import java.util.List;

import com.huituopin.common.dao.BaseDao;
import com.huituopin.yjya.entity.Rule;

public interface IRuleDao extends BaseDao<Rule,String>{
	/**
	 * 更新Rule信息
	 * @param rule
	 * @return
	 */
	public boolean updateRule(Rule rule);
	
	/**
	 * 根据id查找对应的积分
	 * @param id
	 * @return
	 */
	public int getCredictNum(int id);
	
	/**
	 * 根据Id获取该Rule
	 * @param id
	 * @return
	 */
	public Rule getRuleById(int id);
	/**
	 * 爱心银行获取积分规则
	 */
	public List<Rule> getCridictsRule();
}
