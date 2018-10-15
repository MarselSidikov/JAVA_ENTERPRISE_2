<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="form-style-6">
    <h1>${locale.get("signin.login.message")}<a href="/signIn?lang=Ru">RU</a>|<a href="/signIn?lang=En">EN</a>|<a href="/signIn?lang=Po">Po</a> </h1>
    <form method="post">
        <input type="email" name="email" placeholder="${locale.get("signin.email")}" />
        <input type="password" name="password" placeholder="${locale.get("signin.password")}" />
        <input type="submit" value="${locale.get("signin.signin")}" />
    </form>
</div>
</body>
</html>
