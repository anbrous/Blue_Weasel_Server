<%@page import="javax.management.modelmbean.RequiredModelMBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String status = (String) request.getAttribute("status"); // trouver un moyen pour recupérer la valeur de status 
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%= status %>

<form action="saisieAutomatique/appelService" method=POST>
	<input type="submit" name="Launch" value="Lancer la lecture de carte automatique"/>
</form>

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