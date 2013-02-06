<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
  <head><title>Shop</title>
      <meta http-equiv="Pragma" content="no-cache">
      <meta http-equiv="Cache-Control" content="no-cache">
      <meta http-equiv='Expires' content='-1'>

      <link rel="stylesheet" href="../static/style/main.css" type="text/css">




  </head>
  <body>
    <h1>Welcome ${pageContext.request.remoteUser} </h1>



    <p>Please select a category from the list below:</p>
    <ul>
      <li><a href="regular/movies.html">Regular Movies</a> </li>
      <li><a href="specials/movies.html">Our specials Movie Area

      <sec:authorize  access="not(hasRole('ROLE_CASHCOW'))">
         (not for you ${pageContext.request.remoteUser}, you are merely a regular!!)
      </sec:authorize>

      </a> </li>
    </ul>
  </body>
</html>