package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Follow;
import util.DBConnection;

public class FollowDaoImp implements FollowDao {
	Connection conn=null;
	Statement statement=null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement =null;
	@Override
	public boolean saveFollow(String mail, String owner, String title) {
		boolean flag = false;
		
		try {
			
			String sql = "Insert into follow values(?,?,?)";
			conn = DBConnection.openConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, owner);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, mail);
			preparedStatement.executeUpdate();
			flag = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	@Override
	public boolean deleteFollow(String mail, String owner, String title) {
		boolean flag = false;
		
		try {
			
			String sql = "delete from follow where mail_id='"+mail+"' and owner_id= '"+owner+"' and title='"+title+"'";
			conn = DBConnection.openConnection();
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			flag = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	@Override
	public boolean isFollowing(String mail, String owner, String title) {
boolean flag = false;
		
		try {
			
			String sql = "select * from follow where mail_id='"+mail+"' and owner_id= '"+owner+"' and title='"+title+"'";
			conn = DBConnection.openConnection();
			statement = conn.createStatement();
			resultSet= statement.executeQuery(sql);
			
			if(resultSet.next()) {
				flag = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	@Override
	public ArrayList<Follow> get(String mail) {
		ArrayList<Follow> follows = new ArrayList<Follow>();
		try {
			String sql = "select * from follow where mail_id='"+mail+"'";
			conn = DBConnection.openConnection();
			statement = conn.createStatement();
			resultSet= statement.executeQuery(sql);
			while(resultSet.next()) {
				Follow follow = new Follow();
				follow.setMail_id(mail);
				follow.setOwner_id(resultSet.getString("owner_id"));
				follow.setTitle(resultSet.getString("title"));
				follows.add(follow);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return follows;
	}

}
