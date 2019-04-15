<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="/css/style.css">
    <title>SigIn</title>
</head>
<body>
<div class="form-style-3">
    <form method="post">
        <fieldset>
            <legend>Sign In</legend>
            <#if error??>
                <h1>Неправильный логин или пароль</h1>
            </#if>
            <label for="email"><span>Email <span class="required">*</span></span><input type="email"
                                                                                        class="input-field"
                                                                                        name="email"
                                                                                        value=""/></label>
            <label for="password"><span>Password<span class="required">*</span></span><input type="password"
                                                                                           class="input-field"
                                                                                           name="password"
                                                                                           value=""/></label>
        </fieldset>
        <fieldset>
            <label><span> </span><input type="submit" value="Submit"/></label>
        </fieldset>
    </form>
</div>

</body>
</html>