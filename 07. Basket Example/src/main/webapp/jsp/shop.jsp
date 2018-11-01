<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <title>Shop</title>
</head>
<script>
    function sendProductId(productId) {
        $.ajax({
            type: 'post',
            url: '/products',
            data: {
                productId: productId
            }
        }).done(function (data) {
            let contentTableHTML = "<table>";
            contentTableHTML += "<tr>" +
                "<th>Номер</th>" +
                "</tr>";
            for (let i = 0; i < data.length; i++) {
                contentTableHTML += "<tr>";
                contentTableHTML += "<td>" +  [i] + "</td>";
                contentTableHTML += "</tr>";
            }
            contentTableHTML += "</table>";
            let contentTableDiv = document.getElementById("table");
            contentTableDiv.innerHTML = contentTableHTML;
        }).fail(function () {
            alert("НЕ ОЧ");
        });
    }
</script>
<body>
<h1>SHOP</h1>
<div id="products">
    <p onclick="sendProductId(1)">Мыло</p>
    <br>
    <p onclick="sendProductId(2)">Веревка</p>
    <br>
    <p onclick="sendProductId(3)">Стул</p>
</div>
<div id="table">
</div>
</body>
</html>
