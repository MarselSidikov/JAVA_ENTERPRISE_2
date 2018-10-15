<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 15.10.2018
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="form-style-6">
    <h1>${locale.get("signup.contact.us")}<a href="/signUp?lang=Ru">RU</a>|<a href="/signUp?lang=En">EN</a> </h1>
    <form method="post">
        <input type="email" name="email" placeholder="${locale.get("signup.email")}" />
        <input type="password" name="password" placeholder="${locale.get("signup.password")}" />
        <input type="text" name="firstName" placeholder="${locale.get("signup.firstname")}" />
        <input type="text" name="lastName" placeholder="${locale.get("signup.lastname")}" />
        <input type="submit" value="${locale.get("signup.signup")}" />
    </form>
</div>
</body>
</html>
