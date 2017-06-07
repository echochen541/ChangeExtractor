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
			conn = DriverManager.getConnection("jdbc:mysql://10.131.252.156:3306/fdroid", "root", "root");
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<GitCommit> GetCommitInfo(int repo_id) {
		ArrayList<GitCommit> ret = new ArrayList<>();
		try {
			ResultSet resultSet3 = stmt.executeQuery("select commit_id from changefile where repository_id =  "
					+ repo_id + " and type = 'MODIFY' GROUP BY commit_id ORDER BY commit_id");

			ArrayList<String> commit_ids = new ArrayList<>();
			while (resultSet3.next()) {
				String commit_id = resultSet3.getString("commit_id");

				Statement statement = conn.createStatement();
				ResultSet resultSet2 = statement.executeQuery("select parent_id from commitparent where commit_id = \""
						+ commit_id + "\"" + "and repository_id = " + repo_id);

				ArrayList<String> parent = new ArrayList<>();
				while (resultSet2.next()) {
					parent.add(resultSet2.getString("parent_id"));
				}
				resultSet2.close();
				if (parent.size() == 1) {
					commit_ids.add(commit_id);
					GitCommit GitCommit = new GitCommit(commit_id, parent.get(0), new ArrayList<String>());
					ret.add(GitCommit);
				}

			}
			resultSet3.close();

			resultSet3 = stmt.executeQuery("select commit_id,file_name from changefile where repository_id = " + repo_id
					+ " and type = 'MODIFY' ORDER BY commit_id");
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

			/*
			 * ResultSet resultSet = stmt.executeQuery(
			 * "select commit_id from gitcommit where repository_id = " +
			 * repo_id); while (resultSet.next()) { String commit_id =
			 * resultSet.getString("commit_id"); Statement statement =
			 * conn.createStatement(); ResultSet resultSet2 =
			 * statement.executeQuery(
			 * "select parent_id from commitparent where commit_id = \"" +
			 * commit_id + "\"" + "and repository_id = " + repo_id);
			 * 
			 * ArrayList<String> parent = new ArrayList<>(); while
			 * (resultSet2.next()) {
			 * parent.add(resultSet2.getString("parent_id")); }
			 * resultSet2.close(); if (parent.size() == 1) { ResultSet
			 * resultSet3 = statement .executeQuery(
			 * "select file_name from changefile where commit_id = \"" +
			 * commit_id + "\"" + "and repository_id = " + repo_id +
			 * " and type = 'MODIFY'"); ArrayList<String> file_names = new
			 * ArrayList<>(); while (resultSet3.next()) { String fileName =
			 * resultSet3.getString("file_name"); if
			 * (fileName.endsWith(".java")) file_names.add(fileName); }
			 * resultSet3.close(); GitCommit GitCommit = new
			 * GitCommit(commit_id, parent.get(0), file_names);
			 * ret.add(GitCommit); }
			 * 
			 * } resultSet.close();
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;

	}

	public ArrayList<GitRepository> GetRepoInfo() {
		ArrayList<GitRepository> ret = new ArrayList<>();
		try {
			ResultSet resultSet = stmt
					.executeQuery("select repository_id,repository_name,local_address from repository");
			while (resultSet.next()) {
				int repo_id = resultSet.getInt("repository_id");
				String repo_name = resultSet.getString("repository_name");
				String repo_address = resultSet.getString("local_address");
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
