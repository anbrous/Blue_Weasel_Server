<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
    <%
    	String nickname = (String) session.getAttribute("login");
    %>
<!DOCTYPE html>
<html>
<head>
<title>Create a Game</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../../css/page_style.css"/>
<script type="text/javascript" src="../../js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="../../js/jquery.shuffleLetters.js"></script>
<script type="text/javascript" src="../../js/jquery-ui-1.10.0.custom.js"></script>
<link rel="stylesheet" href="../../css/jquery-ui.css"/>

<script type="text/javascript">
$(document).ready(function() {
	var container = $("#header_text");
	container.shuffleLetters();
	dynamic_player();
});
</script>
<script>

function ajax_create(strURL , resultID) {

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
			updatepage_register(self.xmlHttpReq.responseText, resultID);

			if(self.xmlHttpReq.responseText.indexOf("created") != -1)
			{

				$( "#dialog" ).dialog( "open" );
				$("#dialog").shuffleLetters();
				setTimeout('$( "#dialog" ).dialog( "close" );',3000 );
				setTimeout('$( "#f1" ).hide("puff", {}, 2000);',5000 );
				setTimeout('window.location.href="/Blue_Weasel_Server/belot/game_table/"',6000 );
			}
			else
			{
				$( "#dialog" ).dialog( "open" );
				$("#dialog").shuffleLetters();
				
			}
		}
	};
	//alert("action=signin&username=anonymous&password2=anypass&"+getquerystring(formName, paramValue1)+"&"+getquerystring(formName, paramValue2));
	self.xmlHttpReq.send(getquerystring_create());
}

function getquerystring_create() {
	/*var form = document.forms[formName];// utilise le nom du formulaire
	var word = form.elements[paramValue].value;  // utilise le nom de l'input text
	qstr = paramValue+"="+ escape(word); // NOTE: no '?' before querystring 
								// la valeur saisie sera récupérable avec "req.getParameter("paramValue");" */

	var j = $('#nbofrp').val();
    qstr = "nborp="+j+"&gamename="+document.forms['createform'].elements['gamename'].value+"&winningscore="+document.forms['createform'].elements['winningscore'].value;
    for(var i=1;i<=j;i++){
    	qstr += "&player"+i+"="+document.forms['createform'].elements['player'+i].value+"&position"+i+"="+document.forms['createform'].elements['position'+i].value;
    	
    }
	//alert(qstr);
	return qstr;
}

function updatepage_register(str, resultID){
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

function dynamic_player(){
	$('#dynamic_player').html("");
	var nb = $('#nbofrp').val();
	
	for (var i=2;i<=nb;i++){
		$('#dynamic_player').append("<tr><td>Name:<input type='text' name='player"+i+"'</td><td><select name='position"+i+"' id='position"+i+"' onchange='dynamic_position("+i+")' ></select></td><tr />");
		update_selectposition(i);
	}
	
}
function dynamic_position(currentrp){
	var nbofrp = $('#nbofrp').val();
	if( currentrp == 1) {
		dynamic_player();
	} 
	if( currentrp == 2) {
		if( nbofrp == 3) {
			//we need to update the other player position list as well
			update_selectposition(3);
		}
	} 
}
function update_selectposition(current){
	if( current == 2) {
		$('#position2').html("");
		if($('#position1').val() != "1") {
			$('#position2').append('<option value="1">top</option>');
		}
		if($('#position1').val() != "2") {
			$('#position2').append('<option value="2">left</option>');
		}
		if($('#position1').val() != "3") {
			$('#position2').append('<option value="3">bottom</option>');
		}
		if($('#position1').val() != "4") {
			$('#position2').append('<option value="4">right</option>');
		}
		
	}
	if( current == 3) {
		$('#position3').html("");
		if($('#position1').val() != "1" && $('#position2').val() != "1") {
			$('#position3').append('<option value="1">top</option>');
		}
		if($('#position1').val() != "2" && $('#position2').val() != "2") {
			$('#position3').append('<option value="2">left</option>');
		}
		if($('#position1').val() != "3" && $('#position2').val() != "3") {
			$('#position3').append('<option value="3">bottom</option>');
		}
		if($('#position1').val() != "4" && $('#position2').val() != "4") {
			$('#position3').append('<option value="4">right</option>');
		}
		
	}
}
</script>
</head>
<body>
<div class="container">
	<div id="header" class="header">
	<h1 id="header_text" >Welcome To Blue Weasel !!</h1>
	</div>
	
	<div id="content" class="content">
	<div class="creategameform">
		<h2>Create a game</h2>
		
			<form name="createform" method="post" action="">
			
			<table>
				<tr>
					<td>Game name:</td>
					<td><input name="gamename" type="text" value=""/></td>
				</tr>
				
				<tr>
					<td>Winning score:</td>
					<td><input name="winningscore" type="text" value="1000"/></td>
				</tr>
				
				<tr>
					<td>Number of real players:</td>
					<td>
					<select id="nbofrp" name="nbofrp" size="1" onchange="javascript:dynamic_player();">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>Name: <input name="player1" type="text" value="<%=nickname %>" disabled="disabled" /> </td><td><select id="position1" name="position1" onchange="javascript:dynamic_position(1)"><option value="1">top</option><option value="2">left</option><option value="3">bottom</option><option value="4">right</option></select></td>
				</tr>
				</table>
				<table id="dynamic_player" >
				
				</table>
				<table>
				<tr>
					<td> Team1: top - bottom VS Team2: left-right</td><td><input type="button" value="create" onclick="javascript:ajax_create('../creategame/','dialog');" /> </td>
				</tr>
			</table>
			
		</form>
		<div id="dialog" title="Create a game">
		</div>
			<br />
			<br />
			
			
			</div>
	</div>
	<div id="footer" class="footer">
	&copy;Copyright Blue Weasel, Esigetel 2013, All rights reserved.
	</div>
</div>
</body>

</html>

