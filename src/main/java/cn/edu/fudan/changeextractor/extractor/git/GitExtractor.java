/**   
 * Copyright © 2017 Software Engineering Lab, Fudan University. All rights reserved.
 * @Project: change-extractor
 * @author: echo   
 * @date: May 23, 2017 3:59:59 PM 
 */
package cn.edu.fudan.changeextractor.extractor.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevWalk;

/** 
 * @ClassName: GitExtractor 
 * @Description: TODO
 * @author: echo
 * @date: May 23, 2017 3:59:59 PM  
 */
public class GitExtractor {
	private Repository repository;
	private RevWalk revWalk;
	private String repoPath;
	private Git git;
}
