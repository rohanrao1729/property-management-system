function saveFollow(owner,title){
	var xmlhttp;
		content = document.getElementById("chat");
	    if (window.XMLHttpRequest)
	    {// code for IE7+, Firefox, Chrome, Opera, Safari
	        xmlhttp = new XMLHttpRequest()
	    }
        else{// code for IE6, IE5
	        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")
	    }
        button = document.getElementById("follow")
        if(button.innerHTML== "following"){
            button.innerHTML= "follow"
            xmlhttp.open("POST", "FollowController?action=DELETEFOLLOW&owner="+owner+"&title="+title, true);
        }else{
            button.innerHTML= "following"

            xmlhttp.open("POST", "FollowController?action=SAVEFOLLOW&owner="+owner+"&title="+title, true);
        
            
        }

            xmlhttp.onreadystatechange = function()
            {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
        
                  console.log("success")
        
                }
        
        
            }
            xmlhttp.send(null);
        
        
    }