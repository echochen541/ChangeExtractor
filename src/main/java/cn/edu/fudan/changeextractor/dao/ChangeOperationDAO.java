/**   
 * Copyright © 2017 Software Engineering Lab, Fudan University. All rights reserved.
 * @Project: change-extractor
 * @author: echo   
 * @date: May 26, 2017 3:44:35 PM 
 */
package cn.edu.fudan.changeextractor.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ch.uzh.ifi.seal.changedistiller.model.entities.SourceCodeChange;
import cn.edu.fudan.changeextractor.model.db.ChangeOperation;

/**
 * @ClassName: ChangeOperationDao
 * @Description: TODO
 * @author: echo
 * @date: May 26, 2017 3:44:35 PM
 */
public class ChangeOperationDAO {
	public static void insertChangeOperation(ChangeOperation changeOperation) {
		try {
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
					.build(Resources.getResourceAsReader("mybatis-config.xml"));
			SqlSession sqlSession = sessionFactory.openSession();
			ChangeOperationMapper changeMapper = sqlSession.getMapper(ChangeOperationMapper.class);
			changeMapper.insert(changeOperation);
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(changeOperation);
	}

	public static void insertChanges(List<SourceCodeChange> changes, int repositoryId, String commitId,
			String filePath) {
		if (changes != null) {
			try {
				SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
						.build(Resources.getResourceAsReader("mybatis-config.xml"));
				SqlSession sqlSession = sessionFactory.openSession();
				ChangeOperationMapper changeMapper = sqlSession.getMapper(ChangeOperationMapper.class);
				for (SourceCodeChange change : changes) {
					ChangeOperation operation = new ChangeOperation(repositoryId, commitId, filePath,
							change.getRootEntity().getType().toString(),
							change.getRootEntity().getUniqueName().toString(),
							change.getParentEntity().getType().toString(),
							change.getParentEntity().getUniqueName().toString(), change.getChangeType().toString(),
							change.getSignificanceLevel().toString(), change.getChangedEntity().getType().toString(),
							change.getChangedEntity().getUniqueName().toString());
					changeMapper.insert(operation);
					sqlSession.commit();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
