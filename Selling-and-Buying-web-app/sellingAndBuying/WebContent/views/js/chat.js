class chat{
    constructor(){
        this.chatWrapper = document.querySelector("#chat-wrapper") 
        this.openIcon = document.querySelector("#chat-icon")
        this.chatField= document.querySelector("#chat-field")
        this.chatForm = document.querySelector("#chat-form")
        this.closeIcon = document.querySelector(".chat-title-bar-close")
        this.events()
    }

    //events
    events(){
        this.chatForm.addEventListener("submit",(e)=>{
            e.preventDefault()
        })
        this.openIcon.addEventListener("click", ()=>this.showChat())
        this.closeIcon.addEventListener("click",()=>this.hideChat())
        
    }

    //methods


    hideChat(){
        this.chatWrapper.classList.remove("chat--visible")
    }

    showChat(){
        this.chatWrapper.classList.add("chat--visible")
    }




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
     xmlhttp.open("POST", "ChatController?action=STORECHAT&owner=" + mail + "&msg=" + msg+"&title="+title, true);
     xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            
        }
    }
    xmlhttp.send(null);
    
    
}

function reloadData(owner,title){
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


    xmlhttp.open("POST", "ChatController?action=RELOAD&owner="+owner+"&title="+title, true);

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

function clickButton(){
	document.getElementById('reload').click()
}




new chat()