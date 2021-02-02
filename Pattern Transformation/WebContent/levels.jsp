<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>levels</title>
</head>
<body>
<%

if(request.getSession().getAttribute("username")==null){
	response.sendRedirect("accessdenied.html");
}

File f = new File("C:\\Users\\Aidin\\eclipse-workspace\\project4\\levelsDB.txt");
Scanner sc = new Scanner(f);
int i=1;
while(sc.hasNextLine()){
	//System.out.println(i);
	out.println("<form action=\"newgame\" method=\"get\">");
	out.print("<button name = \"level\" type=\"submit\" value="+i+">");
	out.print(i);
	out.println("</button>");
	out.println("</form>");
	out.println("<form action=\"leaderboard.jsp\">");
	out.print("<button name = \"level\" type=\"submit\" value="+ i +">");
	out.print("leaderBoard");
	out.println("</button>");
	out.println("</form>");
	out.println("<br/>");
	sc.nextLine();
	i++;
}
sc.close();
%>
<br/>
<br/>
<a href="userpanel.jsp">home page</a>
</body>
</html>