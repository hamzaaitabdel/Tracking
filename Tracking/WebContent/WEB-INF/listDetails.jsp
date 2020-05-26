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
                <h4><i class="fa fa-angle-right"></i> Details: (Beachten Sie, dass die Daten nur geschätzt sind.)</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i></i>Dateinummer</th>
                    <th><i class=" fa fa-edit"></i> Anzahl der Flecken</th> 
                    <th><i class=" fa fa-edit"></i> Zeit vergeht</th> 
                    <th><i class=" fa fa-edit"></i> Restliche Zeit</th>
                    <th><i class=" fa fa-edit"></i> Behandlungszeit</th>
                    <th></th>
                  </tr>
                </thead> 
                <tbody> 
                  <tr> 
                   <td>${ id_doc} </td> 
			 		<td>${ maxTache}</td>
					<td>${ elapsed} Jours</td>
					<td>${ left} Jours</td>
                    <td>${ totalTime} Jours</td>
			 		
			 <td>		
			 		
			 		
			 	</td>	 
                  
                </tbody>
              </table>
            </div>
</body>
</html>