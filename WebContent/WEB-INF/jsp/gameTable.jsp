<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%
   // initializing players
   String game_info = (String)request.getAttribute("game_info"); 
  	String player_top = "awaiting player", player_left = "awaiting player", player_right = "awaiting player", player_bottom = "awaiting player";
   if( request.getAttribute("player_top") != null ) {
	   player_top = (String)request.getAttribute("player_top");
   }
   if( request.getAttribute("player_left") != null ) {
	   player_left = (String)request.getAttribute("player_left");
   }
   if( request.getAttribute("player_bottom") != null ) {
	   player_bottom = (String)request.getAttribute("player_bottom");
   }
   if( request.getAttribute("player_right") != null ) {
	   player_right = (String)request.getAttribute("player_right");
   }
	// Game variables and Team score
	String game_status = "unknown", current_trump = "unknown";
	if(request.getAttribute("game_status") != null)
		game_status = (String) request.getAttribute("game_status");
	if(request.getAttribute("current_trump") != null)
		current_trump = (String) request.getAttribute("current_trump");
	
	String score1="0", score2 ="0";
	if(request.getAttribute("team1_score") != null)
		score1 =  (String) request.getAttribute("team1_score");
	if(request.getAttribute("team2_score") != null)
		score2 = (String) request.getAttribute("team2_score");
	
   // current cards
   String played_card_top = "b1fv", played_card_left = "b1fv", played_card_bottom = "b1fv", played_card_right = "b1fv";
   
   if( request.getAttribute("played_card_top") != null ) {
	   played_card_top = (String)request.getAttribute("played_card_top");
   }
   if( request.getAttribute("played_card_left") != null ) {
	   played_card_left = (String)request.getAttribute("played_card_left");
   }
   if( request.getAttribute("played_card_bottom") != null ) {
	   played_card_bottom = (String)request.getAttribute("played_card_bottom");
   }
   if( request.getAttribute("played_card_right") != null ) {
	   played_card_right = (String)request.getAttribute("played_card_right");
   }
   
  %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="refresh" content="5" >
	<link rel="stylesheet" href="../../css/game_table_style.css"/>
<title>Belot_Game</title>
<script type="text/javascript" src="../../js/jquery-1.9.0.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//showing current board cards
		
		$("#card_top").css({ 'background-image': 'url(../../img/cards/<%=played_card_top%>.png)'});
		$("#card_left").css({ 'background-image': 'url(../../img/cards/<%=played_card_left%>.png)'});
		$("#card_bottom").css({ 'background-image': 'url(../../img/cards/<%=played_card_bottom%>.png)'});
		$("#card_right").css({ 'background-image': 'url(../../img/cards/<%=played_card_right%>.png)'});
</script>
</head>
<body>
	<div id="game_table">
		
		<div id="top_seat">
			<div id="top_hand">
				<div class="nameplayer"> <%= player_top %></div>
			</div>
		</div>
	
		<div id="middle_seats">
			<div id="left_seat">
				<div id="left_hand">
				<div class="nameplayer"> <%= player_left %></div>
				</div>
			</div>
			<div id="game_area">
			<div id="card_top"></div>
			<div id="card_left"></div>
			<div id="card_bottom"></div>
			<div id="card_right"></div>
			</div>
			<div id="right_seat">
				<div id="right_hand">
				<div class="nameplayer"><%= player_right %></div>
				</div>
			</div>
		</div>
		
		<div id="bottom_seat">
			<div id="bottom_hand">
				<div class="nameplayer"><%= player_bottom %></div>
			</div>
		</div>
		<div id="game_info" class="info_tab">
			<table>
				<tr>
					<td>Game Status:</td><td><%=game_status %></td><td>Current Trump:</td><td><%=current_trump %></td>
				</tr>
				<tr>
					<td>Team1</td><td><%= player_top %></td><td><%= player_bottom %></td><td>Score: <%= score1 %> </td>
				</tr>
				<tr>
					<td>Team2</td><td><%= player_left %></td><td><%= player_right %></td><td>Score: <%= score2 %> </td>
				</tr>
				<tr><td>info:</td><td><%=game_info %></td></tr>
			</table>
		</div>
	</div>
</body>
</html>