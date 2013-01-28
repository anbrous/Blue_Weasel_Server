<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
    <%
    boolean loggedin = false;
    if(session.getAttribute("login") != null)
	 	loggedin = true;
    	String nickname = (String) session.getAttribute("login");
    %>
<!DOCTYPE html>
<html>
<head>
<title>Blue Weasel </title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/page_style.css"/>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="js/jquery.shuffleLetters.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css"/>

<script type="text/javascript">
	$(document).ready(function() {
		<%
		if(!loggedin) {
			%>
			$('#form1').effect("shake", "slow");
			$('#form1').effect("bounce", 1500);
			<%
			
		}
	%>
	var container = $("#header_text");
	container.shuffleLetters();


	});
</script>
<script>

function xmlhttpPost(strURL ,formName, paramValue1, paramValue2, resultID) {

	var xmlHttpReq = false;
	var self = this;
	// Mozilla/Safari
	if (window.XMLHttpRequest) {
		self.xmlHttpReq = new XMLHttpRequest();
	}
	// IE
	else if (window.ActiveXObject) {
		self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	}
	self.xmlHttpReq.open('POST', strURL, true);
	self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	self.xmlHttpReq.onreadystatechange = function() {
		if (self.xmlHttpReq.readyState == 4) {
			updatepage(self.xmlHttpReq.responseText, resultID);

			if(self.xmlHttpReq.responseText.indexOf("not registered") != -1)
			{
				
				$( "#dialog" ).dialog( "open" );
				$("#dialog").shuffleLetters();
			}
			else if (self.xmlHttpReq.responseText.indexOf("Incorrect") != -1)
			{
				$( "#dialog" ).dialog( "open" );
				$("#dialog").shuffleLetters();
			}
			else if (self.xmlHttpReq.responseText.indexOf("connected") != -1) 
			{
				$( "#dialog" ).dialog( "open" );
				$("#dialog").shuffleLetters();
				setTimeout('$( "#dialog" ).dialog( "close" );',3000 );
				setTimeout('$( "#form1" ).hide("bounce", {}, 2000);',5000 );
				setTimeout('location.reload();',6000 );
			}
			else
			{

				$('#dialog').html('unknown error');
				$( "#dialog" ).dialog( "open" );
				$("#dialog").shuffleLetters();
				setTimeout('$( "#dialog" ).dialog( "close" );',3000 );
			}
		}
	};
	//alert("action=signin&username=anonymous&password2=anypass&"+getquerystring(formName, paramValue1)+"&"+getquerystring(formName, paramValue2));
	self.xmlHttpReq.send("action=signin&username=anonymous&password2=anypass&"+getquerystring(formName, paramValue1)+"&"+getquerystring(formName, paramValue2));
}

function getquerystring(formName, paramValue) {
    var form = document.forms[formName];// utilise le nom du formulaire */
	var word = form.elements[paramValue].value;  // utilise le nom de l'input text
	qstr = paramValue+"="+ escape(word); // NOTE: no '?' before querystring 
								// la valeur saisie sera récupérable avec "req.getParameter("paramValue");"
	return qstr;
}

function updatepage(str, resultID){
	$('#dialog').html(str);
}
$(function() {
	$( "#dialog" ).dialog({
		autoOpen: false,
		 modal: true,
		show: {
			effect: "blind",
			duration: 1000
		},
		hide: {
			effect: "explode",
			duration: 1000
		},
		buttons: {
		Ok: function() {
			$( this ).dialog( "close" );
			}
		}
	});
});
</script>
 <script>
$(function() {
	$( "#dialog" ).dialog({
		autoOpen: false,
		show: {
		effect: "blind",
		duration: 1000
	},
	hide: {
		effect: "explode",
		duration: 1000
	}
	});
});
</script>
</head>
<body>

<div id="dialog" title="Connection">
</div>
<div class="container">
	<div id="header" class="header">
	<h1 id="header_text" >Welcome To Blue Weasel !!</h1>
	</div>
	
	<div id="content" class="content">
	<%
	if(loggedin){
		
		%>
	<div id="nav">
	    <ul>
	        <li><a href="belot/creategameform/">Create a game</a></li>
	        <li><a href="belot/history/">Browse history</a></li>
	        <li><a href="bw/logout/">Logout</a></li>
	    </ul>
    </div>
		
		<%
	}
	else {
		%>
			<br />
			<div id="form1" class="form">
					<h2>Log In</h2>
				<form name="form1" action="" method="post" name=main>
				<input type="hidden" name="action" value="signin"/>
				<input type="hidden" name="username" value="anonymous"/>
				<input type="hidden" name="password2" value="anypwd"/>
					<table>
						<tr>
							<td>Email Address:</td>
							<td><input type="text" name="email"/></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" name="password1"/></td>
						</tr>
						<tr><td></td><td><input id="connect" type="button" name="signin" value="Connexion" onclick='JavaScript:xmlhttpPost("bw/connection/", "form1", "email","password1", "connection_info");'/></td></tr>
						<tr><td></td><td><a href="connection.html">sign up</a></td></tr>
					</table>
				</form>
		</div>		
		<%
		}
	%>
		<%
	if(loggedin){
		
		%>	<ul>
	        <li><a href="admin.html">Administration page</a></li>
	        <li><a href="bw/game_list/">List of available games</a></li>
	        <li><a href="pageDeSelectionListofGames.html">List of games</a></li>
			</ul>
<%
	}
%>
	<br /><br /><br /><br />

	
	
	
	</div>
	<div id="footer" class="footer">
	&copy;Copyright Blue Weasel, Esigetel 2013, All rights reserved.
	</div>
</div>
</body>
</html>