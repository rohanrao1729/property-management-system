<%
	String mail = (String)session.getAttribute("mail");
	if(mail!=null){
		response.sendRedirect("/sellingAndBuying/UserController?action=DASH");
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
    <title>Document</title>
</head>
<body>

    <nav class="navbar navbar-light bg-dark">
        <a class="navbar-brand">
            <img src="./views/images/sbb.png" width="30" height="30" alt="">
            <span class="navbar-text mb-0 ml-5 h4 text-white">Login or Register</span>
        </a>
        
        <form class="form-inline" action="${pageContext.request.contextPath}/UserController?action=LOGIN" method="post">
          <input class="form-control mr-sm-2" name="email" type="email" placeholder="example@xyz.com" aria-label="Email Id"> 
          <input class="form-control mr-sm-2" name = "password" type="password"  placeholder="password" aria-label="Password">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
        </form>
    </nav>

    <div class="container mb-5">
        <div class="row align-items-center">
           <div class="col-6">
            <h1 class="display-3">Searching for a property?</h1>      
            <p class="lead text-muted">you can buy and sell your properties here</p>
           </div>
           <div class="col">
           <h5>${message}</h5>
            <form action="${pageContext.request.contextPath}/UserController?action=REGISTER" method="POST" id="registration-form" class="mt-5" >

                <div class="form-group">
                  <label for="username-register" class="text-muted mb-1"><small>Username</small></label>
                  <input name="username" id="username-register" class="form-control" type="text" placeholder="Pick a username" autocomplete="off">
                </div>
                
                <div class="form-group">
                  <label for="email-register" class="text-muted mb-1"><small>Email</small></label>
                  <input name="email" id="email-register" class="form-control" type="text" placeholder="you@example.com" autocomplete="off">
                </div>
                
                <div class="form-group">
                  <label for="password-register" class="text-muted mb-1"><small>Password</small></label>
                  <input name="password" id="password-register" class="form-control" type="password" placeholder="Create a password">
                </div>

                <div class="form-group">
                    <label for="phone-register" class="text-muted mb-1"><small>Phone number</small></label>
                    <input name="phone" id="phone-register" class="form-control" type="number" placeholder="Phone Number">
                  </div>
                  <p class="text-center mx-auto">Address</p>
                  <div class="form-group">
                    <label for="state-register" class="text-muted mb-1"><small>state</small></label>
                    <input name="state" id="state-register" class="form-control" type="text" placeholder="state">
                  </div>

                  <div class="form-group">
                    <label for="pin-register" class="text-muted mb-1"><small>pin code</small></label>
                    <input name="pin" id="pin-register" class="form-control" type="text" placeholder="pin">
                  </div>

                  <div class="form-group">
                    <label for="place-register" class="text-muted mb-1"><small>place</small></label>
                    <input name="place" id="place-register" class="form-control" type="text" placeholder="place">
                  </div>

                <button type="submit" class="py-3 mt-4 btn btn-lg btn-dark btn-block">Sign up for OurApp</button>
                
              </form>
        </div>
    </div>
	</div>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>