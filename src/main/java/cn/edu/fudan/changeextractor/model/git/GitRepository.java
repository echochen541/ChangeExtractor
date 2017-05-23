/**   
 * Copyright © 2017 Software Engineering Lab, Fudan University. All rights reserved.
 * @Project: change-extractor
 * @author: echo   
 * @date: May 23, 2017 10:37:18 AM 
 */
package cn.edu.fudan.changeextractor.model.git;

/**
 * @ClassName: GitRepository
 * @Description: model of git repository
 * @author: echo
 * @date: May 23, 2017 10:37:18 AM
 */
public class GitRepository {
	/**
	 * @fieldName: repositoryId
	 * @fieldType: int
	 * @Description: primary key of repository
	 */
	private int repositoryId;
	/**
	 * @fieldName: repositoryName
	 * @fieldType: String
	 * @Description: repository name
	 */
	private String repositoryName;
	/**
	 * @fieldName: repositoryPath
	 * @fieldType: String
	 * @Description: local repository path
	 */
	private String repositoryPath;
	
	/** 
	 * @Title:GitRepository
	 * @Description:constructor  
	 */
	public GitRepository() {
	}

	/** 
	 * @Title:GitRepository
	 * @Description:constructor
	 * @param repositoryId
	 * @param repositoryName
	 * @param repositoryPath 
	 */
	public GitRepository(int repositoryId, String repositoryName, String repositoryPath) {
		this.repositoryId = repositoryId;
		this.repositoryName = repositoryName;
		this.repositoryPath = repositoryPath;
	}

	/**
	 * @return the repositoryId
	 */
	public int getRepositoryId() {
		return repositoryId;
	}

	/**
	 * @param repositoryId the repositoryId to set
	 */
	public void setRepositoryId(int repositoryId) {
		this.repositoryId = repositoryId;
	}

	/**
	 * @return the repositoryName
	 */
	public String getRepositoryName() {
		return repositoryName;
	}

	/**
	 * @param repositoryName the repositoryName to set
	 */
	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	/**
	 * @return the repositoryPath
	 */
	public String getRepositoryPath() {
		return repositoryPath;
	}

	/**
	 * @param repositoryPath the repositoryPath to set
	 */
	public void setRepositoryPath(String repositoryPath) {
		this.repositoryPath = repositoryPath;
	}
}