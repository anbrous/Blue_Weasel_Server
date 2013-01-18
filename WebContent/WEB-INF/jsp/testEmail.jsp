<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
String msg = (String)request.getAttribute("message");

if(msg == "")
{
	%>
	${message}
	<% 
}
else if(msg == "Email not correct...")
{
	%>
	<font color = "red">${message}</font>
	<%
}
else if(msg == "Email correct!")
{
	%>
	<font color = "green">${message}</font>
	<% 
}

%>


