package userController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChatDao;
import dao.ChatDaoImp;
import entity.Chat;

public class ChatController extends HttpServlet {
	
	RequestDispatcher dispatcher=null;
	ChatDao chatDao=null;
	
	
	public ChatController() {
		chatDao = new ChatDaoImp();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		switch(action) {
		case "STORECHAT":
			storechat(request,response);
			break;
		case "RELOAD":
			reloadData(request,response);
			break;
		case "RELOADALL":
			reloadAllData(request,response);
			break;
		}
	}


	private void reloadAllData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mail = request.getParameter("mail");
		String title = request.getParameter("title");
		ArrayList<String> mails = chatDao.get_all_chat_mails(mail, title);
		if(!mails.isEmpty() && mails!=null) {
			for(String m:mails) {
				
				PrintWriter writer = response.getWriter();
				writer.print("<li class=\"list-group-item \" >\r\n"
						+ "                  <div class=\"row pl-5 pt-1\">\r\n"
						+ "                    <div class=\"col-sm-2\">\r\n"
						+ "                      <img src=\"./views/images/profile.jpg \" width=\"30px\" height=\"30px\" class=\""+title+"\" id=\""+m+"\">\r\n"
						+ "                    </div>\r\n"
						+ "                    <div class=\"col\">\r\n"
						+ "                      <span id=\"pin\" class=\"text-dark\">"+m+"</span>\r\n"
						+ "                    </div>\r\n"
						+ "                  </div>\r\n"
						+ "                </li>");
			}
		}
		
	}


	private void reloadData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String owner_id = request.getParameter("owner");
		String title = request.getParameter("title");
		HttpSession session = request.getSession();
		String loggedUser = (String) session.getAttribute("mail");
		if(!owner_id.equals(loggedUser)) {
			ArrayList<Chat> chats = chatDao.getChats(loggedUser,owner_id,title);
			for(Chat chat: chats) {
				PrintWriter out = response.getWriter();
				if(chat.getOwn_or_cli()==0) {
					out.print("<p class=\"text-right\">you ---- <g>"+chat.getMessage()+"</g></p>");
				}else {
					out.print("<p class=\"text-left\">owner ---- <g>"+chat.getMessage()+"</g></p>");
				}
			}
		}else {
			ArrayList<Chat> chats = chatDao.getChats(request.getParameter("curMail"),owner_id,title);
			for(Chat chat: chats) {
				PrintWriter out = response.getWriter();
				if(chat.getOwn_or_cli()==1) {
					out.print("<p class=\"text-right\">you ---- <g>"+chat.getMessage()+"</g></p>");
				}else {
					out.print("<p class=\"text-left\">client ---- <g>"+chat.getMessage()+"</g></p>");
				}
			}
		}
	}


	private void storechat(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mail = (String) session.getAttribute("mail");
		Chat chat = new Chat();
		chat .setOwner_id(request.getParameter("owner"));
		chat.setMail_id(mail);
		chat.setMessage(request.getParameter("msg"));
		chat.setTitle(request.getParameter("title"));
		int own_or_cli ;
		if(request.getParameter("owner").equals(mail)) {
			own_or_cli = 1;
			chat.setMail_id(request.getParameter("curMail"));
		}else {
			own_or_cli= 0;
		}
		chat.setOwn_or_cli(own_or_cli);
		if(chatDao.saveChat(chat)) {
			System.out.println("successfully saved");
		}
		else {
			System.out.println("failed to save");
		}
	}

}
