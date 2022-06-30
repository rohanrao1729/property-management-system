<%
	String mail = (String)session.getAttribute("mail");
	if(mail==null || mail.isEmpty()){
		response.sendRedirect("/sellingAndBuying");
	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="./views/css/post.css">
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
  <nav class="navbar navbar-light bg-primary">
    <a href="#" class="btn " id="menu-toggle"><img src="./views/images/ham.png" width="30" height="30" alt=""></a>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
      <img src="./views/images/sbb.png" width="30" height="30" alt="">
    </a>
    <span class="navbar-text mb-0 h4 mx-auto text-dark">Update Post</span>
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
  <div id="page-content-wrapper">
  <div class="container mb-5">
    <form action="${pageContext.request.contextPath}/PostController?action=UPDATEPOST&mail=${post.owner_id}&title1=${post.title}"  method="POST" id="post-creation" class="mt-5" >

        <ul class="list-group shadow-lg">

            <li class="list-group-item ">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4">
                    <label for="title" class="text-info">Title: </label>
                  </div>
                  <div class="col">
                    <input name="title" placeholder="title" value="${post.title}" type="text" class="form-control"/>
                  </div>
                </div>
            </li>
            
            <li class="list-group-item ">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4 ">
                    <label for="desc" class="text-info">Description</label>
                  </div>
                  <div class="col">
                    <input name="desc" placeholder="description" value="${post.description}" type="text" class="form-control"/>
                  </div>
                </div>
            </li>

            <li class="list-group-item ">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4">
                    <label for="price" class="text-info">Price: </label>
                  </div>
                  <div class="col">
                    <input name="price" placeholder="price" value="${post.price}" type="text" class="form-control"/>
                  </div>
                </div>
            </li>
            
            <li class="list-group-item ">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4">
                    <label for="type" class="text-info">Property Type: </label>
                  </div>
                  <div class="col">
                    <input name="type" placeholder="ex: car,bike,land etc" value="${post.type}" type="text" class="form-control"/>
                  </div>
                </div>
            </li>
            
            
            
            <li class="list-group-item ">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4">
                    <label for="sorr" class="text-info">sell or rent: </label>
                  </div>
                  <div class="col-sm-1">
                  	<h4>sell : </h4>
                  </div>
                  <div class="col-sm-1">

                    <input name="sorr" value="sell" type="radio" class="form-control"/>
                  </div>
                  <div class="col-sm-1">
                  	<h4>rent : </h4>
                  </div>
                  <div class="col-sm-1">
                    <input name="sorr" type="radio" value="rent" class="form-control"/>
                  </div>
                </div>
            </li>

            <!-- address starts here-->
            <li class="list-group-item">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4">
                    <label for="address" class="text-info">Address : </label>
                  </div>
                  <div class="col ">
                    <ul class="list-group shadow" id="address">
                      <li class="list-group-item ">
                        <div class="row pl-5 pt-1">
                          <div class="col-sm-4">
                            <label for="pin" class="text-info">Pin Code : </label>
                          </div>
                          <div class="col">
                            <input name="pin" placeholder="pin" value="${post.pin}" type="text" class="form-control"/>
                          </div>
                        </div>
                      </li>
                      <li class="list-group-item ">
                        <div class="row pl-5 pt-1">
                          <div class="col-sm-4">
                            <label for="state" class="text-info">State : </label>
                          </div>
                          <div class="col">
                            <input name="state" placeholder="state" value="${post.state}" type="text" class="form-control"/>
                          </div>
                        </div>
                      </li>
                      <li class="list-group-item ">
                        <div class="row pl-5 pt-1">
                          <div class="col-sm-4">
                            <label for="place" class="text-info">Place: </label>
                          </div>
                          <div class="col">
                            <input name="place" placeholder="place" value="${post.place}" type="text" class="form-control"/>
                          </div>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              </li>
              <!-- address ends here-->
        </ul>

    

        <button type="submit" class="py-3 mt-4 btn btn-lg btn-dark btn-block">Update</button>
        
      </form>  
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