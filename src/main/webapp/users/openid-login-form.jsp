<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>OpenID Login</title>

    <!-- Simple OpenID Selector -->
    <link rel="stylesheet" href="<c:url value='../static/style/main.css'/>" />
    <link rel="stylesheet" href="<c:url value='../static/style/openid.css'/>" />

    <script type="text/javascript" src="<c:url value='http://code.jquery.com/jquery-1.2.6.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='../static/js/openid-jquery.js'/>"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            openid.init('openid_identifier');
            //   openid.setDemoMode(true); Stops form submission for client javascript-only test purposes
        });
    </script>
    <!-- /Simple OpenID Selector -->
</head>

<body>

    <c:if test="${param.error!=null}">
        <div id="alert-message">
            Your login attempt was not successful, try again.<br/><br/>
            Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
        </div>
    </c:if>
    <c:if test="${param.logout!=null}">
        <div id="alert-message">You have been logged out.</div>
    </c:if>


    <!-- Simple OpenID Selector -->
    <form action="<c:url value='openid-login'/>" method="post" id="openid_form">
        <input type="hidden" name="action" value="verify" />

        <fieldset>
            <legend>Sign-in or Create New Account</legend>

            <div id="openid_choice">
                <p>Please click your account provider:</p>
                <div id="openid_btns"></div>

            </div>

            <div id="openid_input_area">
                <input id="openid_identifier" name="openid_identifier" type="text" value="http://" />
                <input id="openid_submit" type="submit" value="Sign-In"/>
            </div>
            <noscript>
                <p>OpenID is a service that allows you to log-on to many different websites using a single identity.
                    Find out <a href="http://openid.net/what/">more about OpenID</a> and <a href="http://openid.net/get/">how to get an OpenID enabled account</a>.</p>
            </noscript>
        </fieldset>
    </form>
    <!-- /Simple OpenID Selector -->

</body>
</html>
