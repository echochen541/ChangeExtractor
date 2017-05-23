/**   
 * Copyright © 2017 Software Engineering Lab, Fudan University. All rights reserved.
 * @Project: change-extractor
 * @author: echo   
 * @date: May 23, 2017 3:57:14 PM 
 */
package cn.edu.fudan.changeextractor.controller;

import java.util.List;

import cn.edu.fudan.changeextractor.model.git.GitCommit;
import cn.edu.fudan.changeextractor.model.git.GitRepository;

/**
 * @ClassName: ChangeExtractor
 * @Description: TODO
 * @author: echo
 * @date: May 23, 2017 3:57:14 PM
 */
public class ChangeExtractor {
	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private GitRepository repository;
	private List<GitCommit> commitList;

	public void analyzeCommits() {
		return;
	}

	/**
	 * @return the repository
	 */
	public GitRepository getRepository() {
		return repository;
	}

	/**
	 * @param repository
	 *            the repository to set
	 */
	public void setRepository(GitRepository repository) {
		this.repository = repository;
	}

	/**
	 * @return the commitList
	 */
	public List<GitCommit> getCommitList() {
		return commitList;
	}

	/**
	 * @param commitList
	 *            the commitList to set
	 */
	public void setCommitList(List<GitCommit> commitList) {
		this.commitList = commitList;
	}
}
