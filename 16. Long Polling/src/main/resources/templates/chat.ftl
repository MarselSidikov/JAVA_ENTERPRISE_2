<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chat</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="js/chat.js"></script>
</head>
<body onload="login('${pageId}')">
<h1>Ваш Id: ${pageId}</h1>
<div>
    <input id="message" placeholder="Ваше сообщение">
    <button onclick="sendMessage('${pageId}',
            $('#message').val())">Отправить</button>
</div>
<div>
    <ul id="messages">

    </ul>
</div>
</body>
</html>