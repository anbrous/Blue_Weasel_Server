<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
   String [] player_top_cards = new String[8],player_left_cards = new String[8],player_bottom_cards = new String[8],player_right_cards = new String[8];
	Arrays.fill(player_top_cards,"none");Arrays.fill(player_left_cards,"none");Arrays.fill(player_bottom_cards,"none");Arrays.fill(player_right_cards,"none");
   if( request.getAttribute("player_top_cards") != null ) {
	   player_top_cards = (String []) request.getAttribute("player_top_cards");
   }
   if( request.getAttribute("player_left_cards") != null ) {
	   player_left_cards = (String []) request.getAttribute("player_left_cards");
   }
   if( request.getAttribute("player_bottom_cards") != null ) {
	   player_bottom_cards = (String []) request.getAttribute("player_bottom_cards");
   }
   if( request.getAttribute("player_right_cards") != null ) {
	   player_right_cards = (String []) request.getAttribute("player_right_cards");
   }
   
   String player_top;
   String player_left;
   String player_bottom;
   String player_right;
   
   String current_trump;
   
   if(request.getAttribute("player_top") != null)
   		player_top = (String)request.getAttribute("player_top");
   else
	   player_top = "awaiting";
   
   if(request.getAttribute("player_left") != null)
	   player_left = (String)request.getAttribute("player_left");
   else
	   player_left = "awaiting";
   
   if(request.getAttribute("player_bottom") != null)
	   player_bottom = (String)request.getAttribute("player_bottom");
   else
	   player_bottom = "awaiting";
   
   if(request.getAttribute("player_right") != null)
	   player_right = (String)request.getAttribute("player_right");
   else
	   player_right = "awaiting";
   
   if(request.getAttribute("current_trump") != null)
	   current_trump = (String)request.getAttribute("current_trump");
   else
	   current_trump = "nochosen";
   
 %>
<name> <%= player_top%> <%= player_left%> <%= player_bottom%> <%= player_right%> </name><br/>played_card_top;${played_card_top}|played_card_left;${played_card_left}|played_card_bottom;${played_card_bottom}|played_card_right;${played_card_right}<br/> <player_top_cards> <%	for(String card : player_top_cards)	{%><%=card%> <%	}%><player_top_cards/> <player_left_cards> <%	for(String card : player_left_cards)	{%><%=card%> <%	}%> <player_left_cards/> <player_bottom_cards> <%	for(String card : player_bottom_cards)	{%><%=card%> <%}%> <player_bottom_cards/> <player_right_cards> <%	for(String card : player_right_cards)	{%><%=card%> <%	}%><player_right_cards/> Status;${game_status}<br/>Trump;<%=current_trump%><br/>TeamScore1;${team1_score}<br/>TeamScore2;${team2_score}