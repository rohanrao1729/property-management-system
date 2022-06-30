package userController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FollowDao;
import dao.FollowDaoImp;
import dao.PostDao;
import dao.PostDaoImp;
import dao.UserDao;
import dao.UserDaoImp;
import entity.DisplayPost;
import entity.Follow;
import entity.Payment;
import entity.Post;
import entity.User;


public class UserController extends HttpServlet {
	
	RequestDispatcher dispatcher=null;
    UserDao userDao = null;
    FollowDao followDao= null;
    PostDao postDao= null;
    
    

	public UserController() {
		userDao = new UserDaoImp();
		followDao = new FollowDaoImp();
		postDao = new PostDaoImp();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		switch(action) {
		case "PROFILE":
			displayProfile(request,response);
		break;
		case "LOGOUT":
			user_logout(request,response);
		break;
		case "DASH":
			displayDashboard(request,response);
		break;
		case "PAYMENT":
			payment(request,response);
			break;
		
		}
		
	}

	private void payment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mail = (String) session.getAttribute("mail");
		ArrayList<Payment> ps = userDao.get_payments(mail);
		request.setAttribute("ps", ps);
		dispatcher = request.getRequestDispatcher("/views/payment.jsp");
		dispatcher.forward(request, response);
		
		
	}

	private void displayDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mail = (String) session.getAttribute("mail");
		int emp = 1;
		ArrayList<Follow> follows = followDao.get(mail);
		ArrayList<Post> posts = new ArrayList<>();
		if(follows.size()!=0 ) {
			for(Follow follow : follows) {
				Post post = postDao.get_post(follow.getOwner_id(), follow.getTitle());
				post.setId(post.getIds().get(0));
				posts.add(post);
			}
			emp=0;
		}
		
		request.setAttribute("posts", posts);
		
		request.setAttribute("emp", emp);
		dispatcher = request.getRequestDispatcher("/views/dashboard.jsp");
		dispatcher.forward(request, response);
		
	}

	private void user_logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.setAttribute("message", "successfully logged out");
		dispatcher = request.getRequestDispatcher("/home-guest.jsp");
		dispatcher.forward(request, response);
		
	}

	private void displayProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mail = (String)session.getAttribute("mail");
		User user = userDao.get(mail);
		if(user!=null) {
			request.setAttribute("name", user.getName());
			request.setAttribute("mail", user.getMail_id());
			request.setAttribute("pin", user.getPin());
			request.setAttribute("place", user.getPlace());
			request.setAttribute("state", user.getState());
			
			PostDaoImp postDaoImp = new PostDaoImp();
			
			ArrayList<DisplayPost> posts = postDaoImp.get_image_ids(mail);
			request.setAttribute("posts", posts);
			
		}
		dispatcher = request.getRequestDispatcher("/views/profile.jsp");
		dispatcher.forward(request, response);
		
	}

	private void user_login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mail = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(userDao.login(mail, password)) {
			session.setAttribute("mail", mail);
			request.setAttribute("message","successfully logged in");
			response.sendRedirect("/sellingAndBuying/UserController?action=DASH");
		}else {
			request.setAttribute("message", "there is a problem with your login");
			dispatcher = request.getRequestDispatcher("/home-guest.jsp");
			System.out.println("hi2");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		
		switch(action) {
		case "REGISTER":
			user_registration(request,response);
		break;
		case "LOGIN":
			user_login(request,response);
		break;
		}
	}

	private void user_registration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mail= request.getParameter("email");
		session.setAttribute("mail", mail);
		User user = new User();
		user.setMail_id(mail);
		user.setName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setPin(Integer.parseInt(request.getParameter("pin")));
		user.setPlace(request.getParameter("place"));
		user.setState(request.getParameter("state"));
		
		if(userDao.register(user)) {
			response.sendRedirect("/sellingAndBuying/UserController?action=DASH");
		}else {
			request.setAttribute("message", "there is a problem with registration");
			dispatcher = request.getRequestDispatcher("/home-guest.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		
		
	}

}
