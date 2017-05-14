<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="bootstrapInclude.html" %>
<script src="js/modifierDestination.js"></script>
<title>Modifier une destination</title>
</head>
<body>
<%@ include file="../headerInclude.html" %>
	<h1>Modifier une destination</h1>
	<div>
		<a href="index.jsp">Page d'accueil</a>
	</div>
	<div class="panel panel-info">
		<div class="panel-heading">Modifier une destination</div>
		<form action="updateDestination" method="post">
			<input type="hidden" name="id" value="${destination.id }">
			<fieldset>
				<div class="form-group">
					<label for="recipient-name" class="control-label">Continent
						* :</label> <input type="text" class="form-control" name="continent"
						id="continent" placeholder="Continent" required="required" value="${destination.continent }">
				</div>
				<div class="form-group">
					<label for="recipient-name" class="control-label">Pays * :</label>
					<input type="text" class="form-control" name="pays" id="pays"
						placeholder="Pays" required="required" value="${destination.pays }">
				</div>
				<div class="form-group">
					<label for="recipient-name" class="control-label">Region *
						:</label> <input type="text" class="form-control" name="region"
						id="region" placeholder="Region" required="required" value="${destination.region }">
				</div>
				<div class="form-group">
					<label for="message-text" class="control-label">Description:</label>
					<textarea class="form-control" name="description" id="description"
						placeholder="Description" rows="5">${destination.description }</textarea>
					(*) Champs obligatoires
				</div>

				<button type="submit" id="submit" class="btn btn-primary">Modifier</button>
				<button type="reset" id="cancel" class="btn btn-default">Annuler</button>
			</fieldset>
		</form>
	</div>

</body>
</html>