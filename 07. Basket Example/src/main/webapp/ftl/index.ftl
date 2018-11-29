<!DOCTYPE html>
<html>
<head>
    <title>FreeMarker test</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<table>
    <tr>
        <th>Name</th>
        <th>Price</th>
    </tr>
            <#list users as user>
                <tr>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                </tr>
            </#list>
</table>
</body>
</html>