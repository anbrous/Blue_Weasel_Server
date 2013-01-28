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
 %>
player_top;${player_top}|player_left;${player_left}|player_bottom;${player_bottom}|player_right;${player_right}
<br/>
played_card_top;${played_card_top}|played_card_left;${played_card_left}|played_card_bottom;${played_card_bottom}|played_card_right;${played_card_right}

<br/>		
player_top_cards;

<%
	for(String card : player_top_cards)
	{
%>
		<%=card%>
<%
	}
%>
<br/>		
player_left_cards;

<%
	for(String card : player_left_cards)
	{
%>
		<%=card%>
<%
	}
%>
<br/>		
player_bottom_cards;

<%
	for(String card : player_bottom_cards)
	{
%>
		<%=card%>
<%
	}
%>
<br/>		
player_right_cards;

<%
	for(String card : player_right_cards)
	{
%>
		<%=card%>
<%
	}
%>

<br/>	
Status;${game_status}
<br/>	
Trump;${current_trump}
<br/>	
TeamScore1;${team1_score}
<br/>	
TeamScore2;${team2_score}
