/**   
 * Copyright © 2017 Software Engineering Lab, Fudan University. All rights reserved.
 * @Project: change-extractor
 * @author: echo   
 * @date: May 23, 2017 3:57:14 PM 
 */
package cn.edu.fudan.changeextractor.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ch.uzh.ifi.seal.changedistiller.ChangeDistiller;
import ch.uzh.ifi.seal.changedistiller.ChangeDistiller.Language;
import ch.uzh.ifi.seal.changedistiller.distilling.FileDistiller;
import ch.uzh.ifi.seal.changedistiller.model.entities.SourceCodeChange;
import cn.edu.fudan.changeextractor.dao.ChangeOperationDAO;
import cn.edu.fudan.changeextractor.extractor.git.GitExtractor;
import cn.edu.fudan.changeextractor.model.git.GitCommit;
import cn.edu.fudan.changeextractor.model.git.GitRepository;
import cn.edu.fudan.changeextractor.util.FileUtils;

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
		// input repository and commits
		GitRepository gitRepository = new GitRepository(738, "weiciyuan", "D:/root/BigCode/FdroidRepo/qii/weiciyuan");
		List<GitCommit> commitList = new ArrayList<GitCommit>();
		List<String> filePathList = new ArrayList<String>();

		filePathList.add("src/org/qii/weiciyuan/support/smileypicker/EmojiMap.java");
		commitList.add(new GitCommit("7490a7e9ab216275af7416b8acde453e84829560",
				"e7a00f3f17e24ad3319c0b9f0471c782aac9254e", new ArrayList<String>(filePathList)));
		ChangeExtractor changeExtractor = new ChangeExtractor(gitRepository, commitList);
		changeExtractor.extracChange();
	}

	public void extracChange() {
		int repositoryId = repository.getRepositoryId();
		// System.out.println(repositoryId);
		GitExtractor gitExtractor = new GitExtractor(repository.getRepositoryPath());
		// create temp directory to store files to be extracted
		String userDirPath = System.getProperty("user.dir");
		String tempDirPath = userDirPath + "/" + UUID.randomUUID().toString();
		File tempDir = new File(tempDirPath);
		tempDir.mkdirs();

		for (GitCommit gitCommit : commitList) {
			String parentCommitId = gitCommit.getparentCommitId();
			String commitId = gitCommit.getCommitId();
			System.out.println(repositoryId + "," + commitId);
			for (String filePath : gitCommit.getFilePathList()) {
				// System.out.println(filePath);
				byte[] content1 = gitExtractor.getFileContentByCommitId(parentCommitId, filePath);
				byte[] content2 = gitExtractor.getFileContentByCommitId(commitId, filePath);
				String randomString = UUID.randomUUID().toString();
				// create temp files before and after the commit
				File left = FileUtils.writeBytesToFile(content1, tempDirPath, randomString + ".v1");
				File right = FileUtils.writeBytesToFile(content2, tempDirPath, randomString + ".v2");

				FileDistiller distiller = ChangeDistiller.createFileDistiller(Language.JAVA);
				try {
					distiller.extractClassifiedSourceCodeChanges(left, right);
				} catch (Exception e) {
					System.err.println("Warning: error while change distilling. " + e.getMessage());
				}

				// delete temp files
				left.delete();
				right.delete();

				List<SourceCodeChange> changes = distiller.getSourceCodeChanges();
				ChangeOperationDAO.insertChanges(changes, repositoryId, commitId, filePath);
			}
			// System.out.println();
		}
		// delete temp directory
		tempDir.delete();
		System.out.println();
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

	/**
	 * @Title:ChangeExtractor
	 * @Description:TODO
	 */
	public ChangeExtractor() {
	}

	/**
	 * @Title:ChangeExtractor
	 * @Description:TODO
	 * @param repository
	 * @param commitList
	 */
	public ChangeExtractor(GitRepository repository, List<GitCommit> commitList) {
		this.repository = repository;
		this.commitList = commitList;
	}
}
