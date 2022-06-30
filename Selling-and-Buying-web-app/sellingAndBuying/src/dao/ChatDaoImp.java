package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Chat;
import util.DBConnection;

public class ChatDaoImp implements ChatDao{
	Connection conn=null;
	Statement statement=null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement =null;
	@Override
	public boolean saveChat(Chat chat) {
		boolean flag = false;
		try {
			conn = DBConnection.openConnection();
			preparedStatement = conn.prepareStatement("insert into chat(owner_id,title,mail_id,message,own_or_cli) values(?,?,?,?,?)");
			preparedStatement.setString(1, chat.getOwner_id());
			preparedStatement.setString(2, chat.getTitle());
			preparedStatement.setString(3, chat.getMail_id());
			preparedStatement.setString(4, chat.getMessage());
			preparedStatement.setInt(5, chat.getOwn_or_cli());		
			preparedStatement.executeUpdate();
			preparedStatement.close();
			flag=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public ArrayList<Chat> getChats(String mail,String owner,String title) {
		ArrayList<Chat> chats = null;
		try {
			chats = new ArrayList<Chat>();
			conn = DBConnection.openConnection();
			statement = conn.createStatement();
			String sql = "select * from chat where mail_id='"+mail+"' and owner_id ='"+owner+"' and title='"+title+"' order by chat_id" ;
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Chat chat = new Chat();
				chat.setMail_id(mail);
				chat.setOwn_or_cli(resultSet.getInt("own_or_cli"));
				chat.setMessage(resultSet.getString("message"));
				chat.setTitle(resultSet.getString("title"));
				chat.setOwner_id(resultSet.getString("owner_id"));
				chats.add(chat);
			}
			statement.close();
			}catch(Exception e) {
			e.printStackTrace();
		}
		return chats;
 	}
	@Override
	public ArrayList<String> get_all_chat_mails(String mail, String title) {
		ArrayList<String> mails = new ArrayList<String>();
		try {
			String sql = "select distinct mail_id from chat where owner_id='"+mail+"' and title= '"+title+"'";
			
			conn = DBConnection.openConnection();
			statement = conn.createStatement();
			resultSet= statement.executeQuery(sql);
			while(resultSet.next()) {
				mails.add(resultSet.getString("mail_id"));
			}
			statement.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mails;
	}

}
