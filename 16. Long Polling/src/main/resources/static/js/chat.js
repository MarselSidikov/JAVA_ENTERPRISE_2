function sendMessage(pageId, text) {
    let body = {
        pageId: pageId,
        text: text
    };

    $.ajax({
        url: "/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
        }
    });
}
function receiveMessage(pageId) {
    $.ajax({
        url: "/messages?pageId=" + pageId,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            $('#messages').first().after('<li>' + response[0]['text'] + '</li>')
            receiveMessage(pageId);
        }
    })
}

function login(pageId) {
    let body = {
        pageId: pageId,
        text: 'Hi!'
    };

    $.ajax({
        url: "/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
            receiveMessage(pageId);
        }
    });
}
//
// $(document).ready(function () {
//    sendMessage(pageId, 'Hi');
//    receiveMessage(pageId);
// });