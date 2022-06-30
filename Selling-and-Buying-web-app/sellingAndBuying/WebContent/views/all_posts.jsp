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
    <link rel="stylesheet" href="./views/css/grid.css">
    <link rel="stylesheet" href="./views/css/sidebar.css">
    <link
    rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
    crossorigin="anonymous"
    
  />
    <title>Document</title>
</head>
<body>
    <!-- navigation to other pages starts here -->
  <nav class="navbar navbar-expand-lg navbar-light bg-primary">
  <a href="#" class="btn " id="menu-toggle"><img src="./views/images/ham.png" width="30" height="30" alt=""></a>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
      <img src="./views/images/sbb.png" width="30" height="30" alt="">
    </a>
    <span class="navbar-text mb-0 h4 mx-auto text-dark">All Posts</span>
    <form action="" method="get" class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="query">
      <button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  
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
                  <div class="sidebar__link ">
                    <i class="fa fa-home"></i>
                    <a href="${pageContext.request.contextPath}/">Dashboard</a>
                  </div>
                  <div class="sidebar__link ">
                    <i class="fa fa-user-secret" aria-hidden="true"></i>
                    <a href="${pageContext.request.contextPath}/UserController?action=PROFILE">user profile</a>
                  </div>
                  <div class="sidebar__link active_menu_link">
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
  <div id="page-content-wrapper">

    <div class="grid">
        <c:forEach items="${posts}" var="post">
            <div class="card">
                <div class="image">
                    <a href="${pageContext.request.contextPath}/PostController?action=SINGLEPOST&title=${post.title}&mail=${post.mail}" class="mt-2"><img class="card-img-top img-fluid " src="${pageContext.request.contextPath}/PostController?action=GETIMG&id=${post.id}" width="300px" height="250px"alt="Card image cap"></a>
                </div>
                <hr/>
                <div class="title">
                    <h5 class="text-center text-success">${post.title}</h5>
                </div>
                <div class="text">
                    <div class="row ml-3">
                    	<div class="col">
                    	<span class="text-primary font-weight-bold text-justify">Price</span>
                    	</div>
                    	<div class="col">
                    	<p>: ${post.price}</p>
                    	</div>
                    </div>
                    <div class="row ml-3">
                    	<div class="col">
                    	<span class="text-primary font-weight-bold text-justify">sell_or_rent</span>
                    	</div>
                    	<div class="col">
                    	<p>: ${post.sell_or_rent}</p>
                    	</div>
                    </div>
                    <div class="row ml-3">
                    	<div class="col">
                    	<span class="text-primary font-weight-bold text-justify">property_type</span>
                    	</div>
                    	<div class="col">
                    	<p>: ${post.type}</p>
                    	</div>
                    </div>
                    <hr/>
                    <p class="card-text text-center">${post.description}</p>
                    
                </div>


            </div>
        </c:forEach>
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