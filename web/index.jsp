<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 24.02.2017
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>
</head>
<body>
<%if (request.getAttribute("status") != null &&
        request.getAttribute("status").equals("error")) {%>
Login error! Please try again!
<%} else {%>
<form action="/login" method="POST">
  <label>username</label>
  <input type="text" name="username">
  <label>password</label>
  <input type="password" name="password">
  <button type="submit">Submit</button>
  <%}%>
</form>
</body>
</html>
