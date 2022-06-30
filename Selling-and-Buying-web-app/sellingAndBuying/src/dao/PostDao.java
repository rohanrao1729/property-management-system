package dao;

import java.sql.Blob;
import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

import entity.DisplayPost;
import entity.Post;

public interface PostDao {
	boolean savePosts(Post post);
	ArrayList<DisplayPost> get_image_ids(String mail);
	Blob get_image_blob(int id);
	Post get_post(String mail,String title);
	ArrayList<DisplayPost> get_all_posts(String query);
	boolean deletePost(String mail,String title);
	boolean update_post(String mail,String title,Post post);
	boolean mark_as_done(String mail,String title,String buyer);
}
