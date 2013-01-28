<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
    <%@page import="java.util.ArrayList"%>
    <%@page import="model.Game;"%>
<% 
ArrayList<Game> listOfGames = new ArrayList<Game>();
listOfGames = (ArrayList<Game>) request.getAttribute("listOfGames");
%>
Games:
<%for(Game game : listOfGames){%>
<li>Id: <%=game.getGameName() %> / Status: <%=game.getGameStatus() %></li>
<%}%>