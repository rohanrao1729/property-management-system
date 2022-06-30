   var curMail = "h"
   
   document.getElementById("address").addEventListener("click",(e)=> {
	openIcon = document.querySelector("#chat-icon")
          chatWrapper = document.querySelector("#chat-wrapper") 
           
        if(e.target && e.target.nodeName == "IMG") {
           
           document.getElementById("close").click()
           chatWrapper.classList.add("chat--visible")
           curMail = e.target.id
        }
    });
    
    
document.querySelector(".chat-title-bar-close").addEventListener("click",(e)=>{
	chatWrapper = document.querySelector("#chat-wrapper") 
	chatWrapper.classList.remove("chat--visible")
});    

document.querySelector("#chat-form").addEventListener("click",(e)=>{
	e.preventDefault();
});    
    


function reloadall(mail,title){
    var xmlhttp;
	content = document.getElementById("address");
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }


    xmlhttp.open("POST", "ChatController?action=RELOADALL&mail="+mail+"&title="+title, true);

    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
			
            content.innerHTML = xmlhttp.responseText;
            //content.scrollTop = content.scrollHeight;
			

        }


    }
    xmlhttp.send(null);
}

function addText(mail,title){
	
	chatField = document.getElementById("chatField")
    var msg = chatField.value
    content = document.getElementById("chat")
    pTag = document.createElement('p');
    pTag.classList.add("text-right")
    pTag.innerHTML = "you ----  "+"<g>" + msg + "</g>";
    content.appendChild(pTag)
    content.scrollTop = content.scrollHeight;
    chatField.value =""
    chatField.focus()
    xmlhttp = new XMLHttpRequest();
     xmlhttp.open("POST", "ChatController?action=STORECHAT&owner=" + mail + "&msg=" + msg+"&title="+title+"&curMail="+curMail, true);
     xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            
        }
    }
    xmlhttp.send(null);
    

}

function reloadcli(mail,title){
	if(curMail!="h"){
	console.log(sessionStorage.getItem("mail"))
	var xmlhttp;
	content = document.getElementById("chat");
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }


    xmlhttp.open("POST", "ChatController?action=RELOAD&owner="+mail+"&title="+title+"&curMail="+curMail, true);

    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            content.innerHTML = xmlhttp.responseText;
            //content.scrollTop = content.scrollHeight;
			

        }


    }
    xmlhttp.send(null);}
}

function clickCliButton(){
	document.getElementById("reloadcli").click()

}