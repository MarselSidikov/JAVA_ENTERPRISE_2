<html>
<head>

</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
<#list users as user>
     <tr>
         <td>${user.id}</td>
         <td>${user.name}</td>
     </tr>
</#list>
</table>
<form method="post" action="/users">
    <input type="text" name="name">
    <input type="text" name="age">
    <input type="submit">
</form>
</body>
</html>