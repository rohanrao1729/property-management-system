package dao;

import java.util.ArrayList;

import entity.Chat;

public interface ChatDao {
	boolean saveChat(Chat chat);
	ArrayList<Chat> getChats(String mail,String owner,String title);
	ArrayList<String> get_all_chat_mails(String mail,String title);
}
