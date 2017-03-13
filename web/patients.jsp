<%@ page import="user.UserOperations" %>
<%@ page import="org.forgerock.opendj.ldap.Connection" %><%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 01.03.2017
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patients</title>
</head>
<body>
<%! String uid;%>
<%! Connection connection;%>
Your patients:
<%
    uid = (String)request.getAttribute("uid");
    connection = (Connection)request.getAttribute("connection");
    for (String patient : UserOperations.getPatients(uid, connection)) {
%>
        <div><%=patient%></div>
<%
    }
%>
</body>
</html>
