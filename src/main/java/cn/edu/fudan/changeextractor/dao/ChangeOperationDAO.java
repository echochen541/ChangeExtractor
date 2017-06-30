/**   
 * Copyright © 2017 Software Engineering Lab, Fudan University. All rights reserved.
 * @Project: change-extractor
 * @author: echo   
 * @date: May 26, 2017 3:44:35 PM 
 */
package cn.edu.fudan.changeextractor.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ch.uzh.ifi.seal.changedistiller.model.entities.SourceCodeChange;
import cn.edu.fudan.changeextractor.model.db.ChangeOperationWithBLOBs;

/**
 * @ClassName: ChangeOperationDao
 * @Description: TODO
 * @author: echo
 * @date: May 26, 2017 3:44:35 PM
 */
public class ChangeOperationDAO {
	private static SqlSessionFactory sessionFactory;
	private static Reader reader;
	private static SqlSession sqlSession;
	private static ChangeOperationMapper changeMapper;

	static {
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			sqlSession = sessionFactory.openSession();
			changeMapper = sqlSession.getMapper(ChangeOperationMapper.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void insertChanges(List<SourceCodeChange> changes, int repositoryId, String commitId,
			String filePath) {
		if (changes != null) {
			for (SourceCodeChange change : changes) {
				// System.out.println();

				ChangeOperationWithBLOBs operation = new ChangeOperationWithBLOBs(0, repositoryId, commitId, filePath,
						change.getRootEntity().getType().toString(), change.getParentEntity().getType().toString(),
						change.getChangeType().toString(), change.getSignificanceLevel().toString(),
						change.getChangedEntity().getType().toString(),
						change.getRootEntity().getUniqueName().toString(),
						change.getParentEntity().getUniqueName().toString(),
						change.getChangedEntity().getUniqueName().toString());
				// System.out.println(operation.toString());
				try {
					changeMapper.insert(operation);
					sqlSession.commit();
				} catch (Exception e) {
					System.out.println("insert: " + operation);
					e.printStackTrace();
				}
			}
		}
	}
}
