package com.lj.app.core.common.util;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-base.xml")
@Transactional  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class AbstractBaseSpringTransactionTestCase {

	@BeforeClass 
	public static void setUpRunEnv() {
		//TODO:设置单元测试运行环境
		//IDataBaseCreater dataBaseCreater = new MysqlDataBaseCreater();
		//dataBaseCreater.create();
	}
}
