<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="saisieAutomatique/appelService" method=POST>
	<input type="submit" name="Launch" value="Lancer la lecture de carte automatique"/>
</form>


<form action="saisieAutomatique/sendCardId" method="POST">

	<input type ="text" name="idRFID"/>
	<input type="submit" name="submit" value="Cliquer ici pour envoyer une carte"/>
</form>

</body>

</html>