<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Profile</title>
</head>
<%
if(request.getSession().getAttribute("username")==null){
	response.sendRedirect("accessdenied.html");
}
%>
<body>
Successfully changed UserName to <%=request.getSession().getAttribute("username") %>
to delete this account :<br/>
<form action="deleteUser" method="get">
password : <input type="text" name="password" /><br/>
<input type="submit" value="submit">
</form>
<br/>
----------------------------------<br/>
to change your UserName:<br/>
<form action="changeUserName" method = "get">
please enter your new name : <input type="text" name="username" /><br/>
please enter your password : <input type="text" name="password" /><br/>
<input type="submit" value="submit"><br/>
</form>
----------------------------------<br/>
to change your password:<br/>
<form action="changepassword" method = "get">
please enter your old password : <input type="text" name="oldpass" /><br/>
please enter your new password : <input type="text" name="newpass" /><br/>
<input type="submit" value="submit"><br/>
</form>
----------------------------------<br/>
<a href="userpanel.jsp">profile home</a>

</body>
</html>