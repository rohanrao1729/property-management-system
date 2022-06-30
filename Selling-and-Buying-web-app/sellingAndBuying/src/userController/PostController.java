package userController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.FollowDao;
import dao.FollowDaoImp;
import dao.PostDao;
import dao.PostDaoImp;
import dao.UserDao;
import dao.UserDaoImp;
import entity.DisplayPost;
import entity.Post;



public class PostController extends HttpServlet {
	RequestDispatcher dispatcher=null;
	PostDao postDao=null;
	UserDao userDao = null;
	FollowDao followDao = null;
	
	
	
	public PostController() {
		postDao = new PostDaoImp();
		userDao = new UserDaoImp();
		followDao = new FollowDaoImp();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null)
			action="ALLPROPERTY";
		
		if(request.getParameter("query")!=null) {
			action="ALLPROPERTY";
		}
		switch(action) {
		case "OPENCREATEPOST":
			open_create_post(request,response);
		break;
		case "GETIMG":
			try {
				get_image(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		case "SINGLEPOST":
			display_post(request,response);
			break;
		case "ALLPROPERTY":
			get_all_posts(request,response);
			break;
		case "DELETE":
			delete_post(request,response);
			break;
		case "UPDATE":
			open_update_post(request,response);
			break;
		}
	}


	private void open_update_post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String title = request.getParameter("title");
		Post post = postDao.get_post(mail, title);
		request.setAttribute("post", post);
		dispatcher = request.getRequestDispatcher("/views/edit_post.jsp");
		dispatcher.forward(request, response);
	}


	private void delete_post(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mail = request.getParameter("mail");
		String title = request.getParameter("title");
		if(postDao.deletePost(mail, title)) {
			System.out.println("deleted post");
			response.sendRedirect("/sellingAndBuying/UserController?action=DASH");
		}else {
			System.out.println("fail deleted post");
			response.sendRedirect("/sellingAndBuying/UserController?action=DASH");
		}
		
	}


	private void get_all_posts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		if(query==null) {
			query="";
		}
		ArrayList<DisplayPost> posts = postDao.get_all_posts(query);
		if(posts!=null) {
			request.setAttribute("posts", posts);
			dispatcher = request.getRequestDispatcher("/views/all_posts.jsp");
			dispatcher.forward(request, response);
		}
	}


	private void display_post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mail = (String)session.getAttribute("mail");
		String title = request.getParameter("title");
		String gmail = request.getParameter("mail");

		if(gmail!=null) {
			boolean follow = followDao.isFollowing(mail, gmail, title);
			if(follow) {
				request.setAttribute("follow", "following");
				
			}else {
				request.setAttribute("follow", "follow");
			}
			mail= gmail;
			
		}
		
		
		Post post = postDao.get_post(mail, title);
		

		
		if(post!=null) {
			int first_id = post.getIds().get(0);
			ArrayList<Integer> ids = post.getIds();
			ids.remove(0);
			post.setIds(ids);
			post.setOwner_id(mail);
			post.setTitle(title);
			
			request.setAttribute("first_id", first_id);
			request.setAttribute("post", post);
			dispatcher = request.getRequestDispatcher("/views/post.jsp");
			dispatcher.forward(request, response);
		}
		
	}


	private void get_image(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		Blob b= postDao.get_image_blob(Integer.parseInt(request.getParameter("id")));
		response.setContentType("image/jpeg");
        response.setContentLength( (int) b.length());
        InputStream is = b.getBinaryStream();
        OutputStream os = response.getOutputStream();
        byte buf[] = new byte[(int) b.length()];
        is.read(buf);
        os.write(buf);
        os.close();
		
	}


	private void open_create_post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatcher = request.getRequestDispatcher("/views/create-post.jsp");
		dispatcher.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action) {
		case "CREATE":
			create_post(request,response);
			break;
		case "UPDATEPOST":
			update_post(request,response);
			break;
		case "MARK":
			mark_as_done(request,response);
			break;
		}
		
	}


	private void mark_as_done(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
        String mail = (String)session.getAttribute("mail");
        String title= request.getParameter("title");
        String markmail = request.getParameter("mark");
        System.out.println(title+" "+markmail);
        
        if(postDao.mark_as_done(mail, title, markmail)) {
        	response.sendRedirect("/sellingAndBuying/UserController?action=DASH");
        	System.out.println("success");
        }else {
        	response.sendRedirect("/sellingAndBuying/PostController?action=SINGLEPOST&title="+title+"&mail="+mail);
        }
        
		
	}


	private void update_post(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mail = request.getParameter("mail");
		String title = request.getParameter("title1");
		Post post = new Post();
		post.setDescription(request.getParameter("description"));
		post.setPin(Integer.parseInt(request.getParameter("pin")));
		post.setPlace(request.getParameter("place"));
		post.setState(request.getParameter("state"));
		post.setSell_or_rent(request.getParameter("sorr"));
		post.setTitle(request.getParameter("title"));
		post.setType(request.getParameter("type"));
		post.setPrice(Integer.parseInt(request.getParameter("price")));
		System.out.println(post.toString());
		if(postDao.update_post(mail, title, post)) {
			System.out.println("update successfull");
			response.sendRedirect("/sellingAndBuying/PostController?action=SINGLEPOST&title="+post.getTitle()+"&mail="+mail);
		}else {
			System.out.println("update failed");
			response.sendRedirect("/sellingAndBuying/PostController?action=SINGLEPOST&title="+title+"&mail="+mail);
		}
		
		
	}


	private void create_post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
        Post post = null;
        post = new Post();
        HttpSession session = request.getSession();
        String mail = (String)session.getAttribute("mail");
        
        try {
            // Apache Commons-Fileupload library classes
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu  = new ServletFileUpload(factory);

            if (! ServletFileUpload.isMultipartContent(request)) {
                System.out.println("sorry. No file uploaded");
                return;
            }

            // parse request
            List items = sfu.parseRequest(request);
            int l = items.size();
            ArrayList<FileItem> files = new ArrayList<>();
            for(int i=0;i<l-8;i++) {
            	FileItem file = (FileItem) items.get(i);
            	files.add(file);
            }
            
            String title = ((FileItem)items.get(l-8)).getString();
            String desc = ((FileItem)items.get(l-7)).getString();
            String price = ((FileItem)items.get(l-6)).getString();
            String type = ((FileItem)items.get(l-5)).getString();
            String sorr = ((FileItem)items.get(l-4)).getString();
            String pin = ((FileItem)items.get(l-3)).getString();
            String state = ((FileItem)items.get(l-2)).getString();
            String place = ((FileItem)items.get(l-1)).getString();
            
            
            post.setFiles(files);
            post.setSell_or_rent(sorr);;
            post.setDescription(desc);
            post.setTitle(title);
            post.setPrice(Integer.parseInt(price));
            post.setPin(Integer.parseInt(pin));
            post.setPlace(place);
            post.setState(state);
            post.setMark_as_done(0);
            post.setOwner_id(mail);
            post.setType(type);
           
           if(postDao.savePosts(post)) {
        	   response.sendRedirect("/sellingAndBuying/UserController?action=DASH");
           }else {
        	   response.sendRedirect("/sellingAndBuying/PostController?action=OPENCREATEPOST");
           }
           
           
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

}
