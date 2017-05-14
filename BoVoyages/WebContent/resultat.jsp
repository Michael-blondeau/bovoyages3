<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="bootstrapInclude.html" %>
<title>Resultat</title>
</head>
<body>
<%@ include file="../headerInclude.html" %>
	<div>
		<a href="index.jsp">Page d'accueil</a>
	</div>

	<c:choose>
		<c:when test="${success}">
			<h2>Votre destination à bien été enregistrée</h2>
		</c:when>
		<c:otherwise>
			<h2>Votre destination n'a pas pu être enregistrée</h2>
		</c:otherwise>
	</c:choose>
	
	<div>
		<a href="showAll">Retour à la liste</a>
	</div>
	

</body>
</html>