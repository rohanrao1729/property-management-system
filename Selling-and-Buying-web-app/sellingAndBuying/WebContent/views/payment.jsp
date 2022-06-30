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
    <link
    rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
    crossorigin="anonymous"
    
  />
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
.checkbox{
    width: 80px;
    text-align: center;
    color: #0000FF;;
    margin: auto;
  }
</style>
</head>
<body>

<nav class="navbar navbar-light bg-primary">
  <a href="#" class="btn " id="menu-toggle"><img src="./views/images/ham.png" width="30" height="30" alt=""></a>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
      <img src="./views/images/sbb.png" width="30" height="30" alt="">
    </a>
    
    <span class="navbar-text mb-0 h4 mx-auto text-dark">Your payment Notes</span>
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
          <table>
            <tr>
              <th>S.No</th>
              <th>Customer Id</th>
              <th>Property</th>
              <th>Payment Paid</th>
            </tr>
            <c:set var ="c" value="checked"></c:set>
            <c:set var ="uc" value=""></c:set>
            <c:forEach items="${ps}" var="p">
            <tr>
              <td>1</td>
              <td>${ p.buyer}</td>
              <td>${p.title}</td>
              <td class="checkbox">
                  <input type="checkbox" ${p.pon ==1?c:uc} >
                </td>
            </tr>
            </c:forEach>
            
            
          </table>
          
          <div class="row">
          	<div class="colsm-4 mx-auto mt-5">
          	<button class="btn btn-primary">Save changes</button>
          	</div>
          

         </div>
         </div>
      </main>
      
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

</body>
</html>