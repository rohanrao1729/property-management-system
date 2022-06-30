<%
	String mail = (String)session.getAttribute("mail");
	if(mail==null || mail.isEmpty()){
		response.sendRedirect("/sellingAndBuying");
	}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="./views/css/sidebar.css">
    <link rel="stylesheet" href="./views/css/prof.css">
    <link
    rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
    crossorigin="anonymous"
    
  />
<title>Insert title here</title>
</head>
<body>
	
	 <!-- navigation to other pages starts here -->
  <nav class="navbar navbar-light bg-primary">
  <a href="#" class="btn " id="menu-toggle"><img src="./views/images/ham.png" width="30" height="30" alt=""></a>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
      <img src="./views/images/sbb.png" width="30" height="30" alt="">
    </a>
    
    <span class="navbar-text mb-0 h4 mx-auto text-dark">Your Dashboard</span>
  </nav>
  <!-- navigation to other pages ends here -->
  <div id="wrapper">
    <div id="sidebar-wrapper">
        <div id="sidebar">
                <div class="sidebar__title">
                  <div class="sidebar__img">
                    <img src="./views/images/img1.jpg" alt="logo" height="30px" width="30px" />
                  </div>
                  
                </div>
        
                <div class="sidebar__menu">
                  <div class="sidebar__link   active_menu_link">
                    <i class="fa fa-home"></i>
                    <a href="${pageContext.request.contextPath}/">Dashboard</a>
                  </div>
                  <div class="sidebar__link ">
                    <i class="fa fa-user-secret" aria-hidden="true"></i>
                    <a href="${pageContext.request.contextPath}/UserController?action=PROFILE">user profile</a>
                  </div>
                  <div class="sidebar__link">
                    <i class="fa fa-search"></i>
                    <a href="${pageContext.request.contextPath}/PostController?action=ALLPROPERTY">search properties</a>
                  </div>
                  <div class="sidebar__link">
                    <i class="fa fa-plus-circle"></i>
                    <a href="${pageContext.request.contextPath}/PostController?action=OPENCREATEPOST">Create Post</a>
                  </div>
                  <div class="sidebar__link">
                    <i class="fa fa-sticky-note"></i>
                    <a href="${pageContext.request.contextPath}/UserController?action=PAYMENT">payment Notes</a>
                  </div>

                  <div class="sidebar__link">
                    <i class="fa fa-sign-out"></i>
                    <a href="${pageContext.request.contextPath}/UserController?action=LOGOUT">Logout</a>
                  </div>
                
                  
                </div>
                </div>
     </div>
   <div id="page-content-wrapper" >
   <h3 class="mt-5">${message}</h3>
       <div class="container" style="background: #f3f4f6;">
       <main>
        <div class="main__container">
          <!-- MAIN TITLE STARTS HERE -->

          <div class="main__title">
            <img src="./views/images/rent_imga.png" alt="" />
            <div class="main__greeting">
              <h1>Hello	<%=(String)session.getAttribute("mail")%></h1>
              <p>Welcome to your admin dashboard</p>
            </div>
          </div>

          <!-- MAIN TITLE ENDS HERE -->


         </div>
      </main>
      
       </div>
      
      <div class="container">
      	<c:if test="${emp==1}">
      		<h1 class="text-center mt-5">Your feed is empty</h1>
      	</c:if>
      	<ul class="nav nav-tabs mt-5" id="myTab" role="tablist">
      <li class="nav-item">
        <a class="nav-link active" id="posts-tab" data-toggle="tab" href="#posts" role="tab" aria-controls="posts" aria-selected="true">Following Posts</a>
      </li>
    
    </ul>

    <!-- posts tab starts here -->
    <div class="tab-content border-left border-right border-bottom mb-5 shadow" id="myTabContent">
      <div class="tab-pane fade show active" id="posts" role="tabpanel" aria-labelledby="posts-tab">
        <div class="row row-cols-4" id="cards" >
        	
        	<c:forEach items="${posts}" var="post">
            
	            <div class="card mt-3 mb-3 mr-5 shadow col " style="width: 18rem;">
	              <a href="${pageContext.request.contextPath}/PostController?action=SINGLEPOST&title=${post.title}&mail=${post.owner_id}" class="mt-2"><img class="card-img-top img-fluid" src="${pageContext.request.contextPath}/PostController?action=GETIMG&id=${post.id}" width="300px" height="250px"alt="Card image cap"></a>
	              <div class="card-body">
	                <h5 class="card-title">${post.title}</h5>
	                <p>Price : ${post.price}</p>
	                <p>Type : ${post.type}</p>
	                <p>sell or rent : ${post.sell_or_rent}</p>
	                <p class="card-text">${post.description}</p>
	              </div>
	            </div>
	            
	        </c:forEach>

        </div>
      </div>
    </div>
      </div>
      
      
      </div>
    </div>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
            <!-- menu toggler script starts-->

        <script>
          $("#menu-toggle").click(function(e){
              e.preventDefault();
              $("#wrapper").toggleClass("menuDisplayed");
          });
      </script>
  
  
      <!-- menu toggler script ends-->
</body>
</html>