<%@page import="javax.management.modelmbean.RequiredModelMBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="3;">

<title>Insert title here</title>

<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="js/jquery.shuffleLetters.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	/* var container = $("#header_text");
	container.shuffleLetters();
	
	if ($("#email").val() || $("#username").val() ){
		JavaScript:xmlhttpPost("bw/checkEmail/", "f1", "email", "email_info");
	    JavaScript:xmlhttpPost("bw/checkEmail/", "f1", "username", "username_info");
	}
 */
 xmlhttpPost("showCardToScanAJAX/", "card_info");
 
});
</script>

<script language="Javascript">

function xmlhttpPost(strURL, resultID) {
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
		}
	};
	self.xmlHttpReq.send("");
}

function updatepage(str, resultID){
	document.getElementById(resultID).innerHTML = str;
}

</script>

</head>
<body>

<form action="saisieAutomatique/appelService" method="POST" name="f3"> 
	<input type="submit" name="Launch" value="Lancer la lecture de carte automatique" onclick="window.open('readerRFID/','mywindow','width=800,height=400');JavaScript:xmlhttpPost('showCardToScanAJAX/','card_info');" />
	<div id="card_info"></div>
</form>

</body>

</html>