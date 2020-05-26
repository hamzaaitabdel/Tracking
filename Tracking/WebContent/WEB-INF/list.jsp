<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ page import="entities.Dossier" %>
        <%@ page import="entities.Responsable" %>
        <%@ page import="entities.Tache" %>
    
    <%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="web.TrackingServlet"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Responsable</title>

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">
  
  <!-- =======================================================
    Template Name: Dashio
    Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>
<body>
<div class="content-panel">
              <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i> Plus d'infos:</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i></i>#</th>
                    <th><i class=" fa fa-edit"></i> Libelle</th>
                    <th><i class=" fa fa-edit"></i> Dauer des Flecks</th>
                    <th><i class=" fa fa-edit"></i> Startdatum</th>
                    <th><i class=" fa fa-edit"></i> Enddatum</th> 
                    <th><i class=" fa fa-edit"></i> Zustand</th> 
                    <th></th>
                  </tr>
                </thead>
                 <%! int i = 0; %>
                <tbody>
                  <c:forEach items="${ tache}" var="tache"> 
                   <% i++; %>
                  <tr>
                  
                   <td>		
			 		
  <c:if test="${tache.valide == true }">
<c:if test="${tache.turn == false }">
   <span class="badge bg-success"><%= i  %></span>
    </c:if>
    <c:if test="${tache.turn == true }">
    <span class="badge bg-success"><%= i  %></span>
  </c:if>
</c:if>
<c:if test="${tache.valide == false }">
   <c:if test="${tache.turn == false }">
    <span class="badge bg-important"><%= i  %></span>
   </c:if>
    <c:if test="${tache.turn == true }">
	<span class="badge bg-success"><%= i  %></span>
   </c:if>
</c:if>
			 		
			 	</td>
			 	
                   <td>${ tache.libelle}</td>  
                    <td>${ tache.duree} Jours</td>
					<td>${ tache.dateDebut}</td>
					<td>${ tache.dateFin}</td>
			 		
			 		
			 <td>		
			 		
  <c:if test="${tache.valide == true }">
<c:if test="${tache.turn == false }">
   <span class="label label-success">Ausgefüllt</span>
    </c:if>
    <c:if test="${tache.turn == true }">
    <span class="label label-primary">Laufend</span>
  </c:if>
</c:if>
<c:if test="${tache.valide == false }">
   <c:if test="${tache.turn == false }">
    <span class="label label-danger">Noch nicht bearbeitet</span>
   </c:if>
    <c:if test="${tache.turn == true }">
	<span class="label label-primary">Laufend</span>
   </c:if>
</c:if>
			 		
			 		
			 		
			 	</td>	
			 		
			 		
                    </c:forEach> 
             <% i=0; %>     
                   
                  
                </tbody>
              </table>
            </div>
</body>
</html>