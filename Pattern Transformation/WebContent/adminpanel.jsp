<%@page import="java.util.HashMap"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AdminPanel</title>
</head>
<body>

Welcome Admin!
<br/>
<br/>
to add a new level write a String of 50 colors separated with ","<br/>
<form action="addlevel" method="get">
your new level : <input type="text" name="level" /><br/>
<input type="submit" value="submit">
</form><br/>

<br/>
<br/>
<a href="logout">log out</a>
</body>
</html>