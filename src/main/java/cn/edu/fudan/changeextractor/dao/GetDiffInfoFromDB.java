package cn.edu.fudan.changeextractor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.edu.fudan.changeextractor.model.git.GitCommit;
import cn.edu.fudan.changeextractor.model.git.GitRepository;

public class GetDiffInfoFromDB {
	Connection conn = null;
	Statement stmt;

	public GetDiffInfoFromDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/co-change", "root", "root");
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<GitCommit> GetCommitInfo(int repo_id) {
		ArrayList<GitCommit> ret = new ArrayList<GitCommit>();
		try {
			ResultSet resultSet3 = stmt.executeQuery("select commit_id from change_file where repository_id =  "
					+ repo_id + " and change_type = 'MODIFY' GROUP BY commit_id");

			ArrayList<String> commit_ids = new ArrayList<String>();
			while (resultSet3.next()) {
				String commit_id = resultSet3.getString("commit_id");

				Statement statement = conn.createStatement();
				ResultSet resultSet2 = statement.executeQuery("select parent_commit_id from commit_parent where commit_id = \""
						+ commit_id + "\"" + "and repository_id = " + repo_id);

				ArrayList<String> parent = new ArrayList<String>();
				while (resultSet2.next()) {
					parent.add(resultSet2.getString("parent_commit_id"));
				}
				resultSet2.close();
				if (parent.size() == 1) {
					commit_ids.add(commit_id);
					GitCommit GitCommit = new GitCommit(commit_id, parent.get(0), new ArrayList<String>());
					ret.add(GitCommit);
				}

			}
			resultSet3.close();

			resultSet3 = stmt.executeQuery("select commit_id,file_name from change_file where repository_id = " + repo_id
					+ " and change_type = 'MODIFY'");
			String laString = "";
			int last = -1;
			while (resultSet3.next()) {
				String fileName = resultSet3.getString("file_name");
				String commit_id = resultSet3.getString("commit_id");

				if (fileName.endsWith(".java"))
					if (laString.equals(commit_id) && last != -1) {
						ret.get(last).getFilePathList().add(fileName);
					} else {
						last = commit_ids.indexOf(commit_id);
						laString = commit_id;
						ret.get(last).getFilePathList().add(fileName);

					}

			}
			resultSet3.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;

	}

	public ArrayList<GitRepository> GetRepoInfo() {
		ArrayList<GitRepository> ret = new ArrayList<GitRepository>();
		try {
			ResultSet resultSet = stmt
					.executeQuery("select repository_id,repository_name,repository_path from repository");
			while (resultSet.next()) {
				int repo_id = resultSet.getInt("repository_id");
				String repo_name = resultSet.getString("repository_name");
				String repo_address = resultSet.getString("repository_path");
				GitRepository repository = new GitRepository(repo_id, repo_name, repo_address);
				ret.add(repository);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;

	}

	public void finishAll() {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GetDiffInfoFromDB getDiffInfoFromDB = new GetDiffInfoFromDB();
		ArrayList<GitRepository> repositories = getDiffInfoFromDB.GetRepoInfo();
		ArrayList<GitCommit> commits = getDiffInfoFromDB.GetCommitInfo(738);
		getDiffInfoFromDB.finishAll();
		System.out.println("finish!");
	}
}
