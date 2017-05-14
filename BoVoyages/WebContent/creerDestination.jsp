<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="bootstrapInclude.html" %>
<script src="js/creerDestination.js"></script>
<title>Créer une destination</title>
</head>
<body>
<%@ include file="headerInclude.html" %>
	<h1>Créer une destination</h1>
	<div>
		<a href="index.jsp">Page d'accueil</a>
	</div>
	<div class="panel panel-info">
	<div class="panel-heading">Créer une destination</div>
	<form action="createDestination" method="post">
		<fieldset>
			<div class="form-group">
            	<label for="recipient-name" class="control-label">Continent * :</label>
            	<input type="text" class="form-control" name="continent" id="continent" placeholder="Continent" required="required">
          	</div>
          	<div class="form-group">
            	<label for="recipient-name" class="control-label">Pays * :</label>
            	<input type="text" class="form-control" name="pays" id="pays" placeholder="Pays" required="required">
          	</div>
          	<div class="form-group">
            	<label for="recipient-name" class="control-label">Region * :</label>
            	<input type="text" class="form-control" name="region" id="region" placeholder="Region" required="required">
          	</div>
          	<div class="form-group">
            	<label for="message-text" class="control-label">Description:</label>
            	<textarea class="form-control" name="description" id="description" placeholder="Description"></textarea>
            	(*) Champs obligatoires
          	</div>
          	
          	<button type="submit" id="submit" class="btn btn-primary">Créer</button>
          	<button type="reset" id="cancel" class="btn btn-default">Annuler</button>
		</fieldset>
	</form>
	</div>
	
</body>
</html>