var chosen = false;
var chosenId;
var messages = [];

function run() {
    messages = loadMessages() || [
            newMessage(true, "Hi!"),
            newMessage(false, "Hello!"),
            newMessage(true, "Bye.")
        ];
    render(messages);
}

function newMessage(me, text) {
    return {
        "me": me,
        "text": text,
        "id": "" + uniqueId(),
        "deleted": false
    }
}

function loadMessages() {
    if (typeof(Storage) == "undefined") {
        alert('localStorage is not accessible');
        return;
    }

    var item = localStorage.getItem("messages");
    return item && JSON.parse(item);
}

function saveMessages() {
    if(typeof(Storage) == "undefined") {
        alert('localStorage is not accessible');
        return;
    }

    localStorage.setItem("messages", JSON.stringify(messages));
}

function uniqueId() {
    var date = Date.now();
    var random = Math.random() * Math.random();

    return Math.floor(date * random);
}

function clearChatSpace() {
    var chat = document.getElementById("chat");
    chat.innerHTML = "<div class='error'>Server/network error.</div>";
}

function render(messages) {
    clearChatSpace();
    for (var i = 0; i < messages.length; i++) {
        renderMessage(messages[i]);
    }
}

function renderMessage(message) {
    var msg = document.createElement('div');
    msg.id = message.id;
    if (message.deleted) {
        msg.classList.add('delete');
        msg.innerHTML = 'Deleted.';
    } else {
        msg.appendChild(document.createTextNode(message.text));
        if (message.me) {
            msg.classList.add('me');
            msg.setAttribute("onclick", "active(" + ("" + message.id) + ")");
        } else {
            msg.classList.add('you');
        }
    }
    document.getElementById("chat").appendChild(msg);
}

function send() {
    var newText = document.getElementById("message").value;
    if (newText.length == 0)
        return;
    var message = newMessage(true, newText);
    messages.push(message);
    saveMessages();
    renderMessage(message);
    document.getElementById("message").value = "";
}

function active(id) {
    if (chosen && chosenId == id) {
        document.getElementById('' + chosenId).classList.remove('choose');
        chosen = false;
        document.getElementById('deletebutton').style.visibility = 'hidden';
    } else if (!chosen) {
        chosenId = id;
        document.getElementById('' + id).classList.add('choose');
        chosen = true;
        document.getElementById('deletebutton').style.visibility = 'visible';
    }
}

function deleted() {
    for (var i=0; i<messages.length; i++) {
        if (messages[i].id == chosenId) {
            messages[i].deleted = true;
            saveMessages();
            chosen = false;
            chosenId = "";
            document.getElementById('deletebutton').style.visibility = 'hidden';
            render(messages);
            return;
        }
    }
}

