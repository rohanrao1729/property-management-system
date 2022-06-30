package dao;

import java.util.ArrayList;

import entity.Follow;

public interface FollowDao {
	boolean saveFollow(String mail,String owner,String title);
	boolean deleteFollow(String mail,String owner,String title);
	boolean isFollowing(String mail,String owner,String title);
	ArrayList<Follow> get(String mail);
	
}
