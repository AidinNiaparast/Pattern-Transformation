<%@page import="java.util.HashMap"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.io.File"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>game page</title>
</head>
<body>
<%
String[][] beginstate =(String[][]) (request.getAttribute("result"));
String[][] endstate = (String[][]) (request.getAttribute("end"));
String username = (String)request.getSession().getAttribute("username");
%>

<TABLE width="210" height="210" border="0"> 
<TR height="30" >
<TD width="30" ></TD>
<TD width="30" >
<form action="gamecenter" method="get">
<button name = "move" type="submit" value=U1>U1</button>
</form>
<br/>
</TD>
<TD width="30" >
<form action="gamecenter" method="get">
<button name = "move" type="submit" value=U2>U2</button>
</form>
<br/>
</TD>
<TD width="30" >
<form action="gamecenter" method="get">
<button name = "move" type="submit" value=U3>U3</button>
</form>
<br/>
</TD> 
<TD width="30" >
<form action="gamecenter" method="get">
<button name = "move" type="submit" value=U4>U4</button>
</form>
<br/>
</TD> 
<TD width="30" >
<form action="gamecenter" method="get">
<button name = "move" type="submit" value=U5>U5</button>
</form>
<br/>
</TD> 
<TD width="30" ></TD>
</TR>



<%
//for(int i=0 , j=0 ; i<5 ; i++){
for(int i=0;i<5;i++){	
	out.println("<TR height=\"30\" >");
	out.println("<TD width=\"30\" >");
	out.println("<form action=\"gamecenter\" method=\"get\">");
	out.print("<button name = \"move\" type=\"submit\" value="+ "L"+(i+1)+">");
	out.print("L"+(i+1));
	out.println("</button>");
	out.println("</form>");
	out.println("<br/>");
	out.println("</TD>");
	for(int j=0;j<5;j++){
		out.println("<TD width=\"30\" bgcolor=\""); 
		out.println(beginstate[i][j]);
		out.println("\"></TD>");
	}
	
	/*for(int k=0 ; k<5 ; k++){
		int toReadIndex = Integer.parseInt(beginstate[j]);
		out.println(map.get(toReadIndex));
		j++;
	}*/
	
	out.println("<TD width=\"30\" >");
	out.println("<form action=\"gamecenter\" method=\"get\">");
	out.print("<button name = \"move\" type=\"submit\" value="+ "R"+(i+1)+">");
	out.print("R"+(i+1));
	out.println("</button>");
	out.println("</form>");
	out.println("<br/>");
	out.println("</TD>");
	out.println("</TR>");
}
%>
<TR height="30" >
<TD width="30" ></TD>
<TD width="30" >
<form action="gamecenter" method="get">
<button name = "move" type="submit" value=D1>D1</button>
</form>
<br/>
</TD>
<TD width="30" >
<form action="gamecenter" method="get">
<button name = "move" type="submit" value=D2>D2</button>
</form>
<br/>
</TD>
<TD width="30" >
<form action="gamecenter" method="get">
<button name = "move" type="submit" value=D3>D3</button>
</form>
<br/>
</TD> 
<TD width="30" >
<form action="gamecenter" method="get">
<button name = "move" type="submit" value=D4>D4</button>
</form>
<br/>
</TD> 
<TD width="30" >
<form action="gamecenter" method="get">
<button name = "move" type="submit" value=D5>D5</button>
</form>
<br/>
</TD> 
<TD width="30" ></TD>
</TR>
</TABLE>

<br/><br/><br/>
<TABLE width="150" height="150" border="0"> 
<%
out.println("final state: ");
out.println("<br/>");
out.println("<br/>");

for(int i=0 ; i<5 ; i++){
	out.println("<TR height=\"30\" >");
	for(int j=0;j<5;j++){
		out.println("<TD width=\"30\" bgcolor=\""); 
		out.println(endstate[i][j]);
		out.println("\"></TD>");
	}
	out.println("</TR>");
}

%>
</TABLE>
<br/><br/><br/>

<a href="resetgame">reset</a><br/>
<a href="gohome">cancel game</a><br/>
</body>
</html>