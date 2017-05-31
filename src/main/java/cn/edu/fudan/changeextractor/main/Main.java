/**   
 * Copyright © 2017 Software Engineering Lab, Fudan University. All rights reserved.
 * @Project: change-extractor
 * @author: echo   
 * @date: May 31, 2017 9:57:05 AM 
 */
package cn.edu.fudan.changeextractor.main;

import java.util.ArrayList;

import cn.edu.fudan.changeextractor.controller.ChangeExtractor;
import cn.edu.fudan.changeextractor.dao.GetDiffInfoFromDB;
import cn.edu.fudan.changeextractor.model.git.GitCommit;
import cn.edu.fudan.changeextractor.model.git.GitRepository;

/**
 * @ClassName: Main
 * @Description: TODO
 * @author: echo
 * @date: May 31, 2017 9:57:05 AM
 */
public class Main {
	public static void main(String[] args) {
		GetDiffInfoFromDB getDiffInfoFromDB = new GetDiffInfoFromDB();
		ArrayList<GitRepository> repositories = getDiffInfoFromDB.GetRepoInfo();
		for (int i = 0; i < 5; i++) {
			GitRepository repository = repositories.get(i);
			int repositoryId = repository.getRepositoryId();
			ArrayList<GitCommit> commits = getDiffInfoFromDB.GetCommitInfo(repositoryId);
			System.out.println(repository);
			ChangeExtractor changeExtractor = new ChangeExtractor(repository, commits);
			changeExtractor.extracChange();
		}
		System.out.println("finish!");
	}
}
