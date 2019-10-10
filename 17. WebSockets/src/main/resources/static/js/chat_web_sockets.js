var webSocket;

function connect(id) {
    document.cookie = 'X-Authorization=' + '123456' + ';path=/';
    webSocket = new WebSocket('ws://localhost:8080/chat');

    webSocket.onmessage = function receiveMessage(response) {
        let data = response['data'];
        let json = JSON.parse(data);
        $('#messagesList').first().after("<li>" + json['from'] + " " +  json['text'] + "</li>")
    };

    webSocket.onerror = function errorShow() {
        alert('Ошибка авторизации')
    }
}

function sendMessage(from, text) {
    let message = {
        "from": from,
        "text": text
    };

    webSocket.send(JSON.stringify(message));
}