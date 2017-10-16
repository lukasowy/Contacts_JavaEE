<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String context = application.getContextPath();

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users</title>
</head>
<body>

	<p style="color: red;">${errorString}</p>

	<h3>List of registered users</h3>

	<table border="1" cellpadding="4" cellspacing="0">
		<tr>
			<td>ID</td>
			<td>Username</td>
			<td>First name</td>
			<td>Last name</td>
			<td colspan="2">Operations</td>
		</tr>
		
		<c:forEach items="${userList}" var="user" >
			<tr>
				<td>${user.idUser }</td>
				<td>${user.username }</td>
				<td>${user.userFirstname }</td>
				<td>${user.userLastname }</td>
				<td><a href="<%=context %>/reg?mode=M&id=${user.idUser }">Edit</a>
				<td><a href="<%=context %>/del?id=${user.idUser }">Delete</a></td>
			</tr>
		</c:forEach>

	</table>
<p>
		<a href="<%=context %>/reg?mode=D&id=">Add new user</a>
	</p>
	<p/>
	<hr style="height: 1px">
	<a href="<%=context%>/main">Main Page</a> &nbsp;&nbsp;<a href="<%=context%>/login">Log out</a>


</body>
</html>