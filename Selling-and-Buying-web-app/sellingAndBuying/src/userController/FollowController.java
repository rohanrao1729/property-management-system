package userController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FollowDao;
import dao.FollowDaoImp;


public class FollowController extends HttpServlet {
	FollowDao followDao = null;
	RequestDispatcher dispatcher= null;
    public FollowController() {
    	
        followDao = new FollowDaoImp();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch(action) {
		case "SAVEFOLLOW":
			saveFollow(request,response);
			break;
		case "DELETEFOLLOW":
			deleteFollow(request,response);
			break;
		}
	}


	private void deleteFollow(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String owner = request.getParameter("owner");
		String title = request.getParameter("title");
		String mail = (String) session.getAttribute("mail");
		if(followDao.deleteFollow(mail, owner, title)) {
			System.out.println("follow deleted");
		}else {
			System.out.println("failed to delete follow");
		}
		
	}


	private void saveFollow(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String owner = request.getParameter("owner");
		String title = request.getParameter("title");
		String mail = (String) session.getAttribute("mail");
		if(followDao.saveFollow(mail, owner, title)) {
			System.out.println("follow saved");
		}else {
			System.out.println("failed to save follow");
		}
	}

}
