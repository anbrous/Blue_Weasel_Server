<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.ArrayList"%>
    <%@page import="model.Game;"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% 
ArrayList<Game> listOfGames = new ArrayList<Game>();
listOfGames = (ArrayList<Game>) request.getAttribute("listOfGames");

%>
List of games:
<br/>

<ul>
<%
for(Game game : listOfGames)
{
%>

	<li>Id:<%=game.getId() %> / Status : <%=game.getGameStatus() %></li>

<%
}
%>

</ul>
</body>
</html>