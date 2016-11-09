package com.huituopin.yjya.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.yjya.dao.IRuleDao;
import com.huituopin.yjya.entity.Rule;
@Repository
public class RuleDaoImpl extends BaseDaoImpl<Rule,String> implements IRuleDao{

	@Override
	public boolean updateRule(Rule rule) {
		try {
			update(rule);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int getCredictNum(int id) {
		List<Rule> ls = findBy("ruleId",id);
		if(ls.size()!=0)
			return (int)ls.get(0).getGetCredictNum();
		else
			return 0;
	}

	@Override
	public Rule getRuleById(int id) {
		List<Rule> ls = findBy("ruleId",id);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public List<Rule> getCridictsRule() {
		List<Rule> ls = findAll();
		if(ls!=null)
			return ls;
		else
			return null;
	}
	
	

}
