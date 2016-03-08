var mesId =  0;

function send() {
    var message = document.createElement('div');
    message.classList.add('me');
    mesId++;
    message.id =  mesId;
    message.setAttribute("onclick","active(mesId)");

    var text = document.getElementById("message").value;
    message.appendChild(document.createTextNode(text));
    document.getElementById("chat").appendChild(message);
    document.getElementById("message").value = "";
    }

function active(id){
    alert(id);
    document.getElementById(id).classList.add('choose');

}

function deleted(id){

}

