/**   
 * Copyright © 2017 Software Engineering Lab, Fudan University. All rights reserved.
 * @Project: change-extractor
 * @author: echo   
 * @date: May 26, 2017 3:44:35 PM 
 */
package cn.edu.fudan.changeextractor.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.edu.fudan.changeextractor.model.db.ChangeOperation;

/**
 * @ClassName: ChangeOperationDao
 * @Description: TODO
 * @author: echo
 * @date: May 26, 2017 3:44:35 PM
 */
public class ChangeOperationDAO {
	private static SqlSessionFactory sessionFactory;
	private static Reader reader;
	static {
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertChangeOperation(ChangeOperation changeOperation) {
		SqlSession sqlSession = sessionFactory.openSession();
		ChangeOperationMapper changeMapper = sqlSession.getMapper(ChangeOperationMapper.class);
		changeMapper.insert(changeOperation);
		sqlSession.commit();
		System.out.println(changeOperation);
	}
}
