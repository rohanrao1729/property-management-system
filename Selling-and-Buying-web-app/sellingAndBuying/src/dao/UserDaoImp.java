package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Payment;
import entity.User;
import util.DBConnection;

public class UserDaoImp implements UserDao{
	
	Connection conn=null;
	Statement statement=null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement =null;

	@Override
	public boolean register(User u) {
		boolean flag = false;
		try {
			String sql = "INSERT INTO usr(mail_id,name,password,pin,state,place) VALUES('"+u.getMail_id()+"','"+u.getName()+"','"+u.getPassword()+"',"+u.getPin()+",'"+u.getState()+"','"+u.getPlace()+"')";
			Connection conn  = DBConnection.openConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
			conn.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean login(String mail_id, String password) {
		boolean flag = false;
		try {
			String sql = "SELECT password from usr WHERE mail_id='"+mail_id+"'";
			Connection conn  = DBConnection.openConnection();
			statement = conn.createStatement();
			resultSet= statement.executeQuery(sql);
			String pass = null;
			while(resultSet.next()) {
				pass= resultSet.getString("password");
			}
			if(pass.equals(password)) {
				flag=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public User get(String mail) {
		User user = null;
		try {
			user = new User();
			String sql = "SELECT * from usr WHERE mail_id='"+mail+"'";
			Connection conn  = DBConnection.openConnection();
			statement = conn.createStatement();
			resultSet= statement.executeQuery(sql);
			String pass = null;
			while(resultSet.next()) {
				user.setMail_id(resultSet.getString("mail_id"));
				user.setName(resultSet.getString("name"));
				user.setPin(resultSet.getInt("pin"));
				user.setPlace(resultSet.getString("place"));
				user.setState(resultSet.getString("state"));
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public ArrayList<Payment> get_payments(String owner) {
		ArrayList<Payment> payments= new ArrayList<>();
		try {
			String sql = "select * from bought_or_rent where owner_id='"+owner+"' and title in (select title from "
					+ "property where owner_id='"+owner+"' and mark_as_done=1)";
			Connection conn  = DBConnection.openConnection();
			statement = conn.createStatement();
			resultSet= statement.executeQuery(sql);
			while(resultSet.next()) {
				Payment p = new Payment();
				p.setBuyer(resultSet.getString("mail_id"));
				p.setOwner(owner);
				p.setTitle(resultSet.getString("title"));
				p.setPon(resultSet.getInt("pon"));
				payments.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return payments;
	}
	

}
