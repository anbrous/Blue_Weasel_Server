<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Saisie Manuelle</title>
</head>
<body>

<%
	ArrayList<String> Colors = new ArrayList<String>();
	Colors = (ArrayList<String>) request.getAttribute("Colors");
	ArrayList<String> Values = new ArrayList<String>();
	Values = (ArrayList<String>) request.getAttribute("Values");
%>

<form action="/Blue_Weasel_Server/admin/saisieManuelle/validationSaisieManuelle" method="post">
Select color:
	<select name="ColorSelected" size="1">
<%
	for(String color : Colors)
	{
%>
		<option value="<%= color %>"><%= color %></option>
<%
	}
%>
	</select>
<br/>
Select value:
	<select name="ValueSelected" size="1">
<%
	for(String value : Values)
	{
%>
		<option value="<%= value %>"><%= value %></option>
<%
	}
%>
	</select>
<br/>
Scan a card:
	<input type ="text" name="idRFID"/>
(le taper pour l'instant)
<br/>
	<input type="submit" name="save" value="Save a card"/>

</form>


</body>
</html>