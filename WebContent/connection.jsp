<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.regex.Matcher"%>
<%@ page import="java.util.regex.Pattern"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>Insert title here</title>

<%
	Pattern EmailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
	+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
%>

</head>
<body>

<h1>Welcome to Blue Weasel</h1>
<h2>Log In</h2>
<form action="bw/connection/" method="post" name=main>
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
		<tr><td></td><td><input type="submit" name="signin" value="Connexion"></td></tr>
	</table>
</form>

<h2>Log Out</h2>
<input type="button" value="log out" onclick="javascript:window.location('bw/logout/')" />
<h2>Sign Up</h2>

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

function xmlhttpPost2(strURL ,formName, paramValue1, paramValue2, resultID) {
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
	/*alert(getquerystring(formName, paramValue1)+"&"+getquerystring(formName, paramValue2));*/
	self.xmlHttpReq.send(getquerystring(formName, paramValue1)+"&"+getquerystring(formName, paramValue2));
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

<form action="bw/connection/" method="post" name="f1">
<input type="hidden" name="action" value="signup"/>
	<table>
		<tr>
			<td>Nickname:</td>
			<td><input type="text" name="username"/></td>
		</tr>
		<tr>
			<td>Email Address:</td>
			<td><input type="text" value="" id="email" name="email" onkeyup='JavaScript:xmlhttpPost("bw/checkEmail/", "f1", "email", "email_info");'/></td>
			<td><div id="email_info"></div></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password1"/></td>
		</tr>
		<tr>
			<td>Password2</td>
			<td><input type="password" name="password2" onkeyup='JavaScript:xmlhttpPost2("bw/checkEmail/", "f1", "password1","password2", "pwd_info");'/></td>	
			<td><div id="pwd_info"></div></td>
		</tr>
		<tr><td></td><td><input type="submit" name="signup" value="Register"></td></tr>
		
	</table>
	
</form>

</body>
</html>