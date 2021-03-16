<%--
  Created by IntelliJ IDEA.
  User: oleguk
  Date: 28.02.2021
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Guru Result</title>
</head>
<body>
<% String msg = (String)request.getAttribute("message");
    out.println(msg);
%>
<br />
<a href="${pageContext.request.contextPath}/DownloadServlet">Download result</a>
</body>
</html>
