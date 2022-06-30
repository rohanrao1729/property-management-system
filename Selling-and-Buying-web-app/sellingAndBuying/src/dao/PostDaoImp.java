package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

import entity.DisplayPost;
import entity.Post;


import util.DBConnection;

public class PostDaoImp implements PostDao{
	Connection conn=null;
	Statement statement=null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement =null;
	@Override
	public boolean savePosts(Post post) {
		boolean flag=false;
		try {
			conn = DBConnection.openConnection();
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement("insert into property values(?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1,post.getOwner_id());
			preparedStatement.setString(2, post.getTitle());
			preparedStatement.setString(3, post.getDescription());
			preparedStatement.setInt(4, post.getPrice());
			preparedStatement.setInt(5,post.getPin());
			preparedStatement.setString(6, post.getState());
			preparedStatement.setString(7,post.getPlace());
			preparedStatement.setInt(8,post.getMark_as_done());
			preparedStatement.setString(9,post.getType());
			preparedStatement.setString(10,post.getSell_or_rent());
			
			preparedStatement.executeUpdate();
            
            ArrayList<FileItem> files = post.getFiles();
            for(FileItem file:files) {
            	preparedStatement = conn.prepareStatement("insert into images(owner_id,title,image) values(?,?,?)");
            	preparedStatement.setString(1,post.getOwner_id());
            	preparedStatement.setString(2,post.getTitle());
            	 preparedStatement.setBinaryStream(3, file.getInputStream(), (int) file.getSize());
                 preparedStatement.executeUpdate();
                 
            }
            conn.commit();
            
            flag = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public ArrayList<DisplayPost> get_image_ids(String mail) {
		ArrayList<DisplayPost> posts = new ArrayList<DisplayPost>();
		try {
			conn = DBConnection.openConnection();
			statement = conn.createStatement();
			String sql= "select title,description,price,sell_or_rent,property_type from property where owner_id='"+mail+"'";
			resultSet = statement.executeQuery(sql);
			ArrayList<String> titles = new ArrayList<String>();
			while(resultSet.next()) {
				DisplayPost post = new DisplayPost();
				titles.add(resultSet.getString("title"));
				post.setDescription(resultSet.getString("description"));
				post.setPrice(resultSet.getInt("price"));
				post.setTitle(resultSet.getString("title"));
				post.setSell_or_rent(resultSet.getString("sell_or_rent"));
				post.setType(resultSet.getString("property_type"));
				posts.add(post);
				
			}
			int c=0;
			for(String title:titles) {
				sql= "select id from images where owner_id='"+mail+"' and title='"+title+"'";
				resultSet = statement.executeQuery(sql);
				resultSet.next();
				posts.get(c).setId(resultSet.getInt("id"));
				c++;
				
			}
			
		}catch(Exception e) {
			posts= null;
			e.printStackTrace();
		}
		
		return posts;
	}
	@Override
	public Blob get_image_blob(int id) {
		Blob  b=null;
		try {
			conn = DBConnection.openConnection();
			preparedStatement = conn.prepareStatement("select image from images where id ="+id);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
            b = rs.getBlob("image");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	@Override
	public Post get_post(String mail, String title) {
		Post post = null;
		try {
			String sql= "select * from property where owner_id='"+mail+"' and title='"+title+"'";
			conn = DBConnection.openConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.next();
			post = new Post();
			post.setTitle(title);
			post.setOwner_id(mail);
			post.setDescription(resultSet.getString("description"));
			post.setPin(resultSet.getInt("pin"));
			post.setPlace(resultSet.getString("place"));
			post.setState(resultSet.getString("state"));
			post.setPrice(resultSet.getInt("price"));
			post.setSell_or_rent(resultSet.getString("sell_or_rent"));
			post.setType(resultSet.getString("property_type"));
			post.setIds(get_all_image_ids(mail, title));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return post;
	}
	
	private ArrayList<Integer> get_all_image_ids(String mail, String title){
		ArrayList<Integer> ids = null;
		try {
			ids = new ArrayList<>();
			conn = DBConnection.openConnection();
			statement = conn.createStatement();
			String sql = "select id from images where owner_id='"+mail+"' and title='"+title+"'";
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				ids.add(resultSet.getInt("id"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ids;
	}
	@Override
	public ArrayList<DisplayPost> get_all_posts(String query) {
		ArrayList<DisplayPost> list = null;
		try {
			list = new ArrayList<>();
			conn = DBConnection.openConnection();
			statement = conn.createStatement();
			String sql= "select * from property where mark_as_done=0 and (title like '%"+query+"%' or description like '%"+query+"%' or property_type like '%"+query+"%' or "
					+ "sell_or_rent like '%"+query+"%' or price like '%"+query+"%')";
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				DisplayPost post = new DisplayPost();
				post.setDescription(resultSet.getString("description"));
				post.setPrice(resultSet.getInt("price"));
				String title = resultSet.getString("title");
				post.setTitle(title );
				String mail = resultSet.getString("owner_id");
				post.setMail(mail);
				post.setSell_or_rent(resultSet.getString("sell_or_rent"));
				post.setType(resultSet.getString("property_type"));
				list.add(post);
			}
			for(DisplayPost post:list) {
				sql = "select id from images where owner_id='"+post.getMail()+"' and title ='"+post.getTitle()+"'";
				resultSet= statement.executeQuery(sql);
				resultSet.next();
				post.setId(resultSet.getInt("id"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public boolean deletePost(String mail, String title) {
		boolean flag = false;
		try {
			String sql = "delete from images where owner_id='"+mail+"' and title='"+title+"'";
			conn = DBConnection.openConnection();
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			sql = "delete from follow where owner_id='"+mail+"' and title='"+title+"'";
			statement.executeUpdate(sql);
			sql = "delete from chat where owner_id='"+mail+"' and title='"+title+"'";
			statement.executeUpdate(sql);
			sql = "delete from property where owner_id='"+mail+"' and title='"+title+"'";
			statement.executeUpdate(sql);
			conn.commit();
			conn.setAutoCommit(true);
			flag = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean update_post(String mail, String title,Post post) {
		boolean flag= false;
		try {
			String sql ="update property set title='"+post.getTitle()+"',description='"+post.getDescription()+"',"
					+ "price="+post.getPrice()+",property_type='"+post.getType()+"',sell_or_rent='"+post.getSell_or_rent()+"',"
							+ "pin="+post.getPin()+",state='"+post.getState()+"',place='"+post.getPlace()+"'";
			conn = DBConnection.openConnection();
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			flag=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean mark_as_done(String mail, String title, String buyer) {
		boolean flag = false;
		try {
			String sql = "update property set mark_as_done="+1+"where owner_id='"+mail+"' and title='"+title+"'";
			conn = DBConnection.openConnection();
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			sql = "insert into bought_or_rent values('"+mail+"','"+title+"','"+buyer+"',0)";
			statement.executeUpdate(sql);
			conn.commit();
			conn.setAutoCommit(true);
			flag =true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
