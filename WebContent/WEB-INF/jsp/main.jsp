<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String context = application.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page</title>
</head>
<body>

<h2>Contacts</h2>

	<table border="0" cellpadding="2" cellspacing="2">

		<tr>
		<td>
			<a href="<%=context%>/users">Users</a>
		</td>
		<td>
			<a href="<%=context%>/login">Log Out</a>
		</td>
		</tr>
	</table>

</body>
</html>