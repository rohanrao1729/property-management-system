
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
    
    <link rel="stylesheet" href="./views/css/fullpost.css">
    <link rel="stylesheet" href="./views/css/sidebar.css">
    <link rel="stylesheet" href="./views/css/chat.css">
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
    <span class="navbar-text mb-0 h4 mx-auto text-dark">Post Title</span>
    <c:if test="${post.owner_id!= sessionScope.mail}">
	<button type="button" class="btn btn-primary" id="chat-icon" data-toggle="modal" data-target="">
	    <i class="fa fa-commenting-o"></i>
	  </button>
	  
	  </c:if>
	  <c:if test="${post.owner_id== sessionScope.mail}">
	  	<button type="button" class="btn btn-primary" onclick="reloadall('${post.owner_id}','${post.title}')" data-toggle="modal" data-target="#exampleModalLong">
		    <i class="fa fa-commenting-o"></i>
		 </button>
	  </c:if>
  </nav>
  <!-- navigation to other pages ends here -->
  
  <!-- Modal -->
  <c:if test="${post.owner_id== sessionScope.mail}">
  <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
          <button type="button" id="close"  class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <ul style="cursor: pointer;" class="list-group shadow" id="address" >
                
              </ul>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  </c:if>
  <!-- model ends here -->
  
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

    <div class="container mt-5 mb-5">
        <ul class="list-group shadow-lg">
            <li class="list-group-item ">
                <div id="carouselExampleIndicators" class="carousel slide  " data-ride="carousel">
                    <div class="carousel-inner">
                    
                    <div class="carousel-item active">
                        <img class="d-block mx-auto" src="${pageContext.request.contextPath}/PostController?action=GETIMG&id=${first_id}" width="700" height="350" alt="First slide">
                      </div>
                    <c:forEach items="${post.ids}" var="id">
                      <div class="carousel-item">
                        <img class="d-block mx-auto" src="${pageContext.request.contextPath}/PostController?action=GETIMG&id=${id}" width="700" height="350" alt="First slide">
                      </div>
                      
                      </c:forEach>
                      
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                      <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                      <span class="carousel-control-next-icon" aria-hidden="true"></span>
                      <span class="sr-only">Next</span>
                    </a>
                  </div>
            </li>
            <li class="list-group-item ">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4">
                    <label for="title" class="text-primary font-weight-bold">Title: </label>
                  </div>
                  <div class="col">
                    <span id="title" class="text-dark">${post.title}</span>
                  </div>
                </div>
            </li>

            <li class="list-group-item ">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4">
                    <label for="title" class="text-primary font-weight-bold">Price: </label>
                  </div>
                  <div class="col">
                    <span id="title" class="text-dark">${post.price }</span>
                  </div>
                </div>
            </li>
            
            <li class="list-group-item ">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4">
                    <label for="sorr" class="text-primary font-weight-bold">sell or rent: </label>
                  </div>
                  <div class="col">
                    <span id="sorr" class="text-dark">${post.sell_or_rent }</span>
                  </div>
                </div>
            </li>
            
            <li class="list-group-item ">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4">
                    <label for="type" class="text-primary font-weight-bold">property type: </label>
                  </div>
                  <div class="col">
                    <span id="type" class="text-dark">${post.type}</span>
                  </div>
                </div>
            </li>

            <li class="list-group-item ">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4">
                    <label for="title" class="text-primary font-weight-bold">Description: </label>
                  </div>
                  <div class="col">
                    <span id="title" class="text-dark">${post.description}</span>
                  </div>
                </div>
            </li>

            <!-- address starts here-->
            <li class="list-group-item">
                <div class="row pl-5 pt-1">
                  <div class="col-sm-4">
                    <label for="address" class="text-primary font-weight-bold">Address : </label>
                  </div>
                  <div class="col ">
                    <ul class="list-group shadow" id="address">
                      <li class="list-group-item ">
                        <div class="row pl-5 pt-1">
                          <div class="col-sm-4">
                            <label for="pin" class="text-primary font-weight-bold">Pin Code : </label>
                          </div>
                          <div class="col">
                            <span id="pin" class="text-dark">${post.pin}</span>
                          </div>
                        </div>
                      </li>
                      <li class="list-group-item ">
                        <div class="row pl-5 pt-1">
                          <div class="col-sm-4">
                            <label for="state" class="text-primary font-weight-bold">State : </label>
                          </div>
                          <div class="col">
                            <span id="state" class="text-dark">${post.state}</span>
                          </div>
                        </div>
                      </li>
                      <li class="list-group-item ">
                        <div class="row pl-5 pt-1">
                          <div class="col-sm-4">
                            <label for="place" class="text-primary font-weight-bold">Place: </label>
                          </div>
                          <div class="col">
                            <span id="place" class="text-dark">${post.place}</span>
                          </div>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              </li>
              <!-- address ends here-->

              <li class="list-group-item">
                <div class="row ml-5">
                <c:if test="${post.owner_id== sessionScope.mail}">
                  <div class="col">
                    <a href="${pageContext.request.contextPath}/PostController?action=DELETE&mail=${post.owner_id}&title=${post.title}"><button type="button" class="btn btn-primary btn-block">Delete</button></a>
                  </div>
                  </c:if>
                  <c:if test="${post.owner_id== sessionScope.mail}">
                  <div class="col text-center">
                    <button type="button" id="mark" class="btn btn-dark btn-block" data-toggle="modal" data-target="#markModal">Mark as Done</button>
                  </div>
                  <div class="modal fade" id="markModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">Mark as Done</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					        <form action="${pageContext.request.contextPath}/PostController?action=MARK&title=${post.title}" method="POST" id="post-creation" class="mt-5" >
									<ul class="list-group shadow-lg">
									<li class="list-group-item">
						                <div class="row pl-5 pt-1">
						                  <div class="col-sm-4 ">
						                    <label for="mark" class="text-info">Enter the mail of buyer:</label>
						                  </div>
						                  <div class="col">
						                    <input name="mark" id="mark1"  type="text" >
						                  </div>
						                </div>
						            </li>
						            </ul>

    								<button type="submit" class="py-3 mt-4 btn btn-lg btn-dark btn-block">OK</button>
        
     							 </form> 
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					      </div>
					    </div>
					  </div>
					</div>
                  </c:if>
                  
                  <c:if test="${post.owner_id!= sessionScope.mail}">
                  <div class="col">
                    <button type="button" id="follow" onclick="saveFollow('${post.owner_id}','${post.title}')" class="btn btn-dark btn-block">${follow}</button>
                  </div>
                  </c:if>
                  <c:if test="${post.owner_id== sessionScope.mail}">
                  <div class="col">
                    <a href="${pageContext.request.contextPath}/PostController?action=UPDATE&mail=${post.owner_id}&title=${post.title}"><button type="button" class="btn btn-success btn-block">Edit</button></a>
                  </div>
                  </c:if>
                </div>
              </li>
            
          </ul>
    </div>
    </div>
    </div>
      <footer>
    <div id="chat-wrapper" class="chat-wrapper shadow border-top border-left border-right">
    	<div class="chat-title-bar">Chat <span class="chat-title-bar-close"><i class="fa fa-times-circle"></i></span></div>
        
        	<div id="chat" class="chat-log">
        	<c:forEach items="${chats}" var="chat">
        		<p>${chat.name} - ${chat.message}</p>
        	</c:forEach>
        </div>
    
        <form id="chat-form" class="chat-form border-top">
            <div class="row">
            	<div class="col-8">
            		<input type="text" class="chat-field" id="chatField" placeholder="Type a message..." autocomplete="off">
            	</div>
            	<div class="col-1">
            		<button class="btn btn-success" type="button" id="send" onclick="addText('${post.owner_id}','${post.title}')">send</button> 
            	</div>
            </div>
        </form>
        
    </div>
    <button style="display: none;" id="reload" onclick="reloadData('${post.owner_id}','${post.title}')"></button>
        <div id="changeclibutton">
        <button style="display: none;" id="reloadcli" onclick="reloadcli('${post.owner_id}','${post.title}')"></button>
        </div>
  </footer>
    

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
      <c:if test="${post.owner_id!= sessionScope.mail}">
      <script src="./views/js/chat.js"></script>
      <script src="./views/js/follow.js"></script>
      <script>
     setInterval(clickButton, 3000)
     </script>
      </c:if>
      
      <c:if test="${post.owner_id== sessionScope.mail}">
      <script src="./views/js/post.js"></script>
      <script>
     setInterval(clickCliButton,3000)
     </script>
      </c:if>
     
      
    

      
</body>
</html>