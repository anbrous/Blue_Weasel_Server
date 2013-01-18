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
function xmlhttpPost(strURL) {
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
updatepage(self.xmlHttpReq.responseText);
}
}
self.xmlHttpReq.send(getquerystring());
}

function getquerystring() {
var form = document.forms['f1'];
var word = form.word.value;
qstr = 'w=' + escape(word); // NOTE: no '?' before querystring
return qstr;
}

function updatepage(str){
document.getElementById("result").innerHTML = str;
}
</script>

<form name="changef1">
<p>Your name: <input name="word" type="text">
<input value="Go" type="button" onclick='JavaScript:xmlhttpPost("hello.jsp")'></p>
<div id="result"></div>
</form>

<form action="bw/connection/" method="post" name="f1">
<input type="hidden" name="action" value="signup"/>
	<table>
		<tr>
			<td>Nickname:</td>
			<td><input type="text" name="username"/></td>
		</tr>
		<tr>
			<td>Email Address:</td>
			<td><input type="text" name="email" onkeypress='JavaScript:xmlhttpPost("hello.jsp")'/></td>
			
				<% 
				Boolean emailCorrect = true;
				String test = request.getParameter("email");
				if(test != null)
				{
					Matcher matcher = EmailPattern.matcher(test);
					emailCorrect = matcher.matches();
				}
					if(!emailCorrect)
					{
				%>
				<td>
						<font color="red">Email not correct! </font>
						<div id="result"></div>
				</td>
				<%		
					}
				%>
			
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password1"/></td>
		</tr>
		<tr>
			<td>Password2</td>
			<td><input type="password" name="password2"/></td>
		</tr>
		<tr><td></td><td><input type="submit" name="signup" value="Register"></td></tr>
		
	</table>
	
</form>

</body>
</html>