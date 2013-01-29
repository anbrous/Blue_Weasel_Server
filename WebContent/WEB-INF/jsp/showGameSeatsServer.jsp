<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
    <%@page import="java.util.ArrayList"%>
    <%@page import="model.Game;"%>
<% 
ArrayList<String> seats = new ArrayList<String>();
seats = (ArrayList<String>) request.getAttribute("seats");
%>
Games:
<%for(String seat : seats){%>
<li><%= seat %><form action="/Blue_Weasel_Server/belot/join_game/"><input type="hidden" name="position" value="<%= seat%>" /><input type="submit" value="Join the game" /></form></li>
<%}%>