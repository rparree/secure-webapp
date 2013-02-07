<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../static/style/main.css" type="text/css">
</head>
<body>
    <p>Please provide your credentials below</p>

    <form action="login" method="post">

        <c:if test="${param.error!=null}">
            <div id="alert-message">Failed to login.
                <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                    Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                </c:if>
            </div>
        </c:if>
        <c:if test="${param.logout!=null}">
            <div id="alert-message">You have been logged out.</div>
        </c:if>

        <fieldset>
            <legend>Login</legend>
            <label for="username">Username</label>
            <input type="text" id="username" name="j_username"/><br/>
            <label for="password">Password</label>
            <input type="password" id="password" name="j_password"/><br/>
            <label for="remember">Remember me</label>
            <input type="checkbox" id="remember"
                   name="_spring_security_remember_me"
                   value="true"/>

            <input id="submit" type="submit"/>
        </fieldset>
    </form>
</body>
</html>