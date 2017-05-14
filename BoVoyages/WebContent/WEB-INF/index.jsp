<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../bootstrapInclude.html" %>
<title>Gestion des destinations</title>
</head>
<body>
<%@ include file="../headerInclude.html" %>
	<h1>Index des pages</h1>
	<div>
		<a href="index.jsp">Page d'accueil</a>
	</div>
	
	<div class="container col-sm-12">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4>Liste des destinations</h4>
			</div>
			
			<div class="panel-body">
			
				<div class="row">
					<form class="form-inline" action="showAll" method="get">
						<span class="form-control-static col-sm-10">Gérer toutes les destinations</span>
						<button class="btn btn-primary col-sm-2" type="submit">Gérer</button>
					</form>
				</div>

				<div class="row">
					<form class="form-inline" action="showAllByPays" method="get">
						<div class="col-sm-10">
							<div class="row">
								<span class="form-control-static col-sm-7">Gérer les destination par pays</span>
								<select class="form-control col-sm-5" name="pays" id="pays" required="required">
									<option value="">Choisissez un pays...</option>
									
									<c:forEach items="${allPays }" var="pays">
										<option value="${pays }">${pays }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<button class="btn btn-primary col-sm-2" type="submit">Gérer</button>
					</form>
				</div>

				<div class="row">
					<form class="form-inline" action="creerDestination.jsp" >
						<span class="form-control-static col-sm-10">Créer une nouvelle destination</span>
						<button class="btn btn-primary col-sm-2" type="submit">Créer</button>
					</form>
				</div>
			
			</div>
			
		</div>
	</div>
</body>
</html>