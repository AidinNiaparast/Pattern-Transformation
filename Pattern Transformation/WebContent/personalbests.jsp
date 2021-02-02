<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>personal bests</title>
</head>
<body>

<%

if(request.getSession().getAttribute("username")==null){
	response.sendRedirect("accessdenied.html");
}

File f = new File("C:\\Users\\Aidin\\eclipse-workspace\\project4\\usersDB.txt");
Scanner sc = new Scanner(f);
ArrayList<String> users = new ArrayList<>();
while(sc.hasNextLine()){
	users.add(sc.nextLine());
}
sc.close();
String[] thisuser=null;
String username = (String)request.getSession().getAttribute("username");
for(String s : users){
	thisuser = s.split(" ");
	if(thisuser[0].equals(username)){
		break;
	}
}
for( int i=3 ; i< thisuser.length ; i++){
	out.println("level "+(i-2)+" : best time="+thisuser[i].split(",")[1]+" best move="+thisuser[i].split(",")[0]+"<br/>" );
}


%>
<br/>
<br/>
<a href="userpanel.jsp">home page</a>
</body>
</html>