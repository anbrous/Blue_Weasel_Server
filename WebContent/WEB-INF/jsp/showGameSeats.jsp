<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
    <%@page import="java.util.ArrayList"%>
    <%@page import="model.Game;"%>
<% 
ArrayList<String> seats = new ArrayList<String>();
seats = (ArrayList<String>) request.getAttribute("seats");
%>
Seats:
<%for(String seat : seats){%>
<li><%= seat %></li>
<%}%>