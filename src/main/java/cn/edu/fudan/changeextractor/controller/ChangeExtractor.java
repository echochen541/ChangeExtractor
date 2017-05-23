/**   
 * Copyright © 2017 Software Engineering Lab, Fudan University. All rights reserved.
 * @Project: change-extractor
 * @author: echo   
 * @date: May 23, 2017 3:57:14 PM 
 */
package cn.edu.fudan.changeextractor.controller;

import java.util.ArrayList;
import java.util.List;

import cn.edu.fudan.changeextractor.extractor.git.GitExtractor;
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
		GitRepository gitRepository = new GitRepository(738, "weiciyuan", "D:/echo/workspace/git/big-code/weiciyuan");

		List<GitCommit> commitList = new ArrayList<GitCommit>();
		List<String> filePathList = new ArrayList<String>();

		filePathList.add("src/org/qii/weiciyuan/support/lib/sinasso/SsoHandler.java");
		commitList.add(new GitCommit("b8f4de854b12c4160a850c2217c779609c5eb445",
				"733ac4f71adfd05f390fbe13d98b02875105b31c", new ArrayList<String>(filePathList)));

		filePathList.clear();
		filePathList.add("src/org/qii/weiciyuan/ui/userinfo/UserInfoFragment.java");
		filePathList.add("src/org/qii/weiciyuan/ui/userinfo/UserInfoActivity.java");
		commitList.add(new GitCommit("90cc081f75458bb13a57ea0cde968331b2482284",
				"0cc93ab7919af0468e83ead8122118fabdccbb73", new ArrayList<String>(filePathList)));

		filePathList.clear();
		filePathList.add("src/org/qii/weiciyuan/support/lib/BlurImageView.java");
		filePathList.add("src/org/qii/weiciyuan/ui/userinfo/UserInfoFragment.java");
		commitList.add(new GitCommit("a4d53c57f88643f9c5d773d647d837b01fcbad21",
				"739e934e4a257a35b82b0ad54131b59daa7ab39a", new ArrayList<String>(filePathList)));

		GitExtractor gitExtractor = new GitExtractor(gitRepository.getRepositoryPath());

		for (GitCommit gitCommit : commitList) {
			String parentCommitId = gitCommit.getparentCommitId();
			String commitId = gitCommit.getCommitId();
			System.out.println(commitId);
			for (String filePath : gitCommit.getFilePathList()) {
				System.out.println(filePath);
				byte[] content1 = gitExtractor.getFileContentByCommitId(parentCommitId, filePath);
				byte[] content2 = gitExtractor.getFileContentByCommitId(commitId, filePath);
				System.out.println(new String(content1));
				System.out.println(new String(content2));
				
			}
		}
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
