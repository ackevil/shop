package com.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huituopin.user.dao.IUserDao;
import com.huituopin.user.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:src/springmvc.xml")
public class TestUserDao extends AbstractTransactionalJUnit4SpringContextTests{
	//取得要测试的Dao类
	@Resource
	private IUserDao userDao;
	@Before
	public void setUp() throws Exception{
		
	}
	@After
	public void tearDown() throws Exception{
		
	}
	/**
	 *测试方法 
	 */
	@Test
	public void testInsert(){
		User user = new User();
		user.setUserPhone("13598071091");
		user.setUserPwd("123asd");
		user.setUserType(true);
		user.setUserIsDelete(false);
		user.setUserIsOnline(false);
		user.setUserSigntime(new Date());
		if(userDao.insert(user)){
			System.out.println("插入成功");
		}else{
			System.out.println("插入失败");
		}
	}
	
}
