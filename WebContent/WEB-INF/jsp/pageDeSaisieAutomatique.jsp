<%@page import="javax.management.modelmbean.RequiredModelMBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String status = (String) request.getAttribute("status");
	if(status == null)
		status = "Stop";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Status: <%= status %>


<script language="Javascript">

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
	qstr = "action="+paramValue+"&"+paramValue+"="+ escape(word); // NOTE: no '?' before querystring 
								// la valeur saisie sera récupérable avec "req.getParameter("paramValue");"
	return qstr;
}

function updatepage(str, resultID){
	document.getElementById(resultID).innerHTML = str;
}
</script>


<form action="saisieAutomatique/appelService" method=POST name ="f2"> 
	<input type="text" name="cardValue"/>
	<input type="submit" name="Launch" value="Lancer la lecture de carte automatique" onclick= "window.open('http://www.google.fr/','window name','width=400, height=200')"  />
</form>

Card to scanned: <div id="card_info"></div>

<form action="/Blue_Weasel_Server/admin/saisieAutomatique/sendCard" method="POST">

	<input type ="text" name="idRFID"/>
	<input type="submit" name="submit" value="Cliquer ici pour envoyer une carte"/>
</form>

<% 
	if(status == "EnCours")// si le status est en cours, le bouton Stop apparait
	{	
%>
		<form action="/Blue_Weasel_Server/admin/saisieAutomatique/quitService" method=POST>
			<input type="submit" name="close" value="Arrêter la lecture de carte"/>
		</form>
<% 
	}
%>

</body>

</html>