<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${titre}</title>
</head>
<body>
<h1>List of ${player} 's cards</h1>
<table>
<% 
	String [][] cardslist = (String [][]) request.getAttribute("cardslist");
	int v,c;
	for(v=0;v<=13;v++){ %>
		<tr> <%
		for (c=0;c<=3;c++){
			if (cardslist[v][c] != "null"){
				String imgurl = "/Blue_Weasel_Server/img/cards/"+v+"-"+c+".png";
				%><td><img src="<%=imgurl %>" /></td>
			<%
			}
			else {
				%><td><img src="/Blue_Weasel_Server/img/cards/b1fv.png" /></td>
			<%
			}
		}
	%> </tr>
	<%
	}
%>
</table>
</body>
</html>