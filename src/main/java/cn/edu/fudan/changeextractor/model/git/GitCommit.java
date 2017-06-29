/**   
 * Copyright © 2017 Software Engineering Lab, Fudan University. All rights reserved.
 * @Project: change-extractor
 * @author: echo   
 * @date: May 23, 2017 11:57:21 AM 
 */
package cn.edu.fudan.changeextractor.model.git;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GitCommit
 * @Description: TODO
 * @author: echo
 * @date: May 23, 2017 11:57:21 AM
 */
public class GitCommit {
	private String commitId;
	private String parentCommitId;
	private List<String> filePathList = new ArrayList<String>();

	public GitCommit() {
	}

	public GitCommit(String commitId, String parentCommitId, List<String> filePathList) {
		this.commitId = commitId;
		this.parentCommitId = parentCommitId;
		this.filePathList = filePathList;
	}

	public String getCommitId() {
		return commitId;
	}

	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	public String getparentCommitId() {
		return parentCommitId;
	}

	public void setparentCommitId(String parentCommitId) {
		this.parentCommitId = parentCommitId;
	}

	public List<String> getFilePathList() {
		return filePathList;
	}

	public void setFilePathList(List<String> filePathList) {
		this.filePathList = filePathList;
	}

	@Override
	public String toString() {
		return "GitCommit [commitId=" + commitId + ", parentCommitId=" + parentCommitId + ", filePathList="
				+ filePathList + "]";
	}
}
