<%@ page pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>User registration</title>
</head>
<body>
	<p style="color: red;">${errorString}</p>

	<p>If you want to use Contact application you must done
		registration</p>

	<form title="SignUp" method="POST" action="doRegister">
		<input type="hidden" name="operation" value="${user.operation}">
		<input type="hidden" name="idUser" value="${user.idUser}">

		<table border="0">
			<tr>
				<td>User</td>
				<td><input type="text" name="username" value="${user.username}" /><font
					color="red">*</font></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"
					value="${user.password}" /><font color="red">*</font></td>
			</tr>
			<tr>
				<td>First name</td>
				<td><input type="text" name="userFirstname"
					value="${user.userFirstname}" /><font color="red">*</font></td>
			</tr>
			<tr>
				<td>Last name</td>
				<td><input type="text" name="userLastname"
					value="${user.userLastname}" /> <font color="red">*</font></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save" /> &nbsp;<a
					href="${pageContext.request.contextPath}/">Back</a></td>
			</tr>
		</table>
	</form>
	<p>
		<font color="red">*</font> - Required Field
	</p>
</body>
</html>