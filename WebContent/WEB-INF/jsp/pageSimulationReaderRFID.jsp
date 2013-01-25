<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script language="Javascript">

function closePage(strURL) {
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
	self.xmlHttpReq.send();
}

function xmlhttpPost(strURL ,formName, paramValue, resultID) {
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
	}
	self.xmlHttpReq.send(getquerystring(formName, paramValue));
}

function getquerystring(formName, paramValue) {
	var form = document.forms[formName];// utilise le nom du formulaire */
	var word = form.elements[paramValue].value;  // utilise le nom de l'input text
	qstr = paramValue+"="+ escape(word); /* NOTE: no '?' before querystring */
								// la valeur saisie sera récupérable avec "req.getParameter("paramValue");"
	return qstr;
}

function updatepage(str, resultID){
	document.getElementById(resultID).innerHTML = str;
}

</script>

</head>
<body onunload="JavaScript:closePage('/Blue_Weasel_Server/admin/saisieAutomatique/quitService')">

	<form method="POST" name="f2"> <!-- action="/Blue_Weasel_Server/admin/saisieAutomatique/sendCard"  -->
	
		<input type ="text" name="idRFID"/>
		<input type="button" name="submit" value="Cliquer ici pour envoyer une carte" onclick='JavaScript:xmlhttpPost("../sendCardAJAX/","f2","idRFID","card_info")'/>
		<div id="card_info"></div>
	</form>

	<form action="/Blue_Weasel_Server/admin/saisieAutomatique/quitService" method=POST onclick="window.close()">
		<input type="submit" name="close" value="Arrêter la lecture de carte"/>
	</form>

</body>
</html>