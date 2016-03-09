var mesId = 0;
var chosen = false;
var chosenId;

function send() {
    var message = document.createElement('div');
    message.classList.add('me');
    message.id = 'm' + mesId;
    message.setAttribute("onclick", "active(" + mesId + ")");
    var text = document.getElementById("message").value;
    message.appendChild(document.createTextNode(text));
    document.getElementById("chat").appendChild(message);
    document.getElementById("message").value = "";
    mesId++;
}

function active(id) {
    if (chosen && chosenId == id) {
        document.getElementById('m' + chosenId).classList.remove('choose');
        chosen = false;
        document.getElementById('deletebutton').style.visibility = 'hidden';
    } else if (!chosen) {
        chosenId = id;
        document.getElementById('m' + id).classList.add('choose');
        chosen = true;
        document.getElementById('deletebutton').style.visibility = 'visible';
    }
}

function deleted() {
    var to_delete = document.getElementById('m' + chosenId);
    to_delete.classList.remove('me', 'choose');
    to_delete.classList.add('delete');
    to_delete.innerHTML = 'Deleted.';
    to_delete.removeAttribute("onclick");
    chosen = false;
    chosenId = "";
    document.getElementById('deletebutton').style.visibility = 'hidden';
}

