<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user panel</title>
</head>
<body>
<%
if(request.getSession().getAttribute("username")==null){
	response.sendRedirect("accessdenied.html");
	return;
}
%>

Welcome <%=request.getSession().getAttribute("username")%>
<br/>
<a href="editUser.jsp">edit profile</a>
<br/>
<br/>
<a href="levels.jsp">levels</a>
<br/>
<br/>
<a href="personalbests.jsp">personal bests</a>
<br/>
<br/>
<a href="logout">log out</a>

</body>
</html>