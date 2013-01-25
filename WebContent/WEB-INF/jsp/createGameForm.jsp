<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Game</title>
</head>
<body>
	<form method="post" action="createGame/">
	
	<table>
		<tr>
			<td>Game name:</td>
			<td><input name="gamename" type="text" value=""/></td>
		</tr>
		
		<tr>
			<td>Winning score:</td>
			<td><input name="winningscore" type="text" value=""/></td>
		</tr>
		
		<tr>
			<td>Number of virtual player:</td>
			<td>
			<select name="NumberSelected" size="1" onchange="">
				<option value="1">1</option>
				<option value="2">2</option>
			</select>
			</td>
		</tr>

	</table>
	
	</form>
</body>
</html>