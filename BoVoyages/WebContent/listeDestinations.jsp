<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="bootstrapInclude.html" %>
<title>Liste des destinations</title>
</head>
<body>
<%@ include file="../headerInclude.html" %>
	<h2>Liste des destinations</h2>
	<div>
		<a href="index.jsp">Page d'accueil</a>
	</div>
	
	<ul>
		<c:forEach items="${destinations}" var="destination">
			<li><h4>
					<a href="getDestinationDetails?id=${destination.id }">${destination.region }</a>
					<a href="deleteDestination?id=${destination.id }">
					<i class="glyphicon glyphicon-trash"></i></a>
				</h4></li>
			${destination.description }
		</c:forEach>
	</ul>
	
<!-- 	<ul> -->
<%-- 		<c:forEach items="${destinations}" var="destination"> --%>
<!-- 			<li><h4> -->
<%-- 					<a href="#">${destination.region }</a> <a href="deleteDestination" --%>
<!-- 						data-toggle="modal" data-target="#modalSupDest"><i -->
<!-- 						class="glyphicon glyphicon-trash"></i></a> -->
<!-- 				</h4></li> -->
<%-- 			${destination.description } --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->


<!-- 	<!-- Modal -->
<!-- 	<div class="modal fade" id="modalSupDest" tabindex="-1" role="dialog" -->
<!-- 		aria-labelledby="myModalLabel"> -->
<!-- 		<div class="modal-dialog" role="document"> -->
<!-- 			<div class="modal-content"> -->
<!-- 				<div class="modal-header"> -->
<!-- 					<button type="button" class="close" data-dismiss="modal" -->
<!-- 						aria-label="Close"> -->
<!-- 						<span aria-hidden="true">&times;</span> -->
<!-- 					</button> -->
<!-- 					<h4 class="modal-title" id="myModalLabel">Supprimer une -->
<!-- 						destination</h4> -->
<!-- 				</div> -->
<!-- 				<div class="modal-body">Voulez vous vraiment supprimer cette -->
<!-- 					destination?</div> -->
<!-- 				<div class="modal-footer"> -->
<!-- 				<form class="form-inline" action="deleteDestination" method="post"> -->
<%-- 					<input type="hidden" name="id" value="${destination.id }"> --%>
<!-- 					<button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button> -->
<!-- 					<button type="submit" class="btn btn-primary">Supprimer</button> -->
<!-- 				</form> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->


</body>
</html>