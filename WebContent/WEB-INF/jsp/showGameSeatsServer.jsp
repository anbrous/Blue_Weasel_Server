<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
    <%@page import="java.util.ArrayList"%>
    <%@page import="model.Game;"%>
<% 
ArrayList<String> seats = new ArrayList<String>();

if(request.getAttribute("seatTop").equals("free"))
	seats.add("top");
if(request.getAttribute("seatLeft").equals("free"))
	seats.add("left");
if(request.getAttribute("seatBottom").equals("free"))
	seats.add("bottom");
if(request.getAttribute("seatRight").equals("free"))
	seats.add("right");
	
String id = (String) request.getParameter("gameid");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>join game</title>
</head>
<body>
Players:
<ul>
<li>${seatTop}</li>
<li>${seatLeft}</li>
<li>${seatBottom}</li>
<li>${seatRight}</li>
</ul>

<table>
<%for(String seat : seats){%>
<tr><td><%= seat %></td><td><form method="post" action="/Blue_Weasel_Server/belot/join_game/">
<input type="hidden" name="gameid" value="<%= id%>">
<input type="hidden" name="position" value="<%= seat%>" /><input type="submit" value="Join the game" /></form></td></tr>
<%}%>
</table>

</body>
</html>