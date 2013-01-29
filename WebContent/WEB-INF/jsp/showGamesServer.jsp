<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.ArrayList"%>
    <%@page import="model.Game;"%>
    
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

	<li>
		<span class="styleA">Id: <%=game.getGameName() %> / </span>  
		<span class="styleB">Status: <%=game.getGameStatus() %></span>
	</li>
	
<%
}
%>
<br/>
<form action="/Blue_Weasel_Server/belot/game_available_seats_server/" method="POST">

Join a game:
<select  name="gameid" size="1">
<%
	for(Game game : listOfGames)
	{
		if(game.getGameStatus().equals("awaiting")){
%>
			<option value="<%= game.getId() %>"><%= game.getGameName()%></option>
<%
		}
	}
%>
</select>

<input type="submit" name="submit" value="Check available seats"/>

</form>

</ul>
</body>
</html>