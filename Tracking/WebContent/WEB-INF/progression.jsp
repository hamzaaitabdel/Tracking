<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ page import="entities.Dossier" %>
        <%@ page import="entities.Responsable" %>
        <%@ page import="entities.Tache" %>
    
    <%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="web.TrackingServlet"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Progression</title>
  <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="css/style44.css">

</head>
<body>
<jsp:include page="acceuilHeader.jsp" />

<br><br>
<br><br>
<br><br>
<p align="left">
<h1>Bienvenue <strong>Mr.${ nom_cl}</strong> </h1>
<c:if test="${numTacheEnCour == maxTache }">
	<h3>Le Traitement de Votre Dossier: <strong>(${ id_doc})</strong> est Terminé</h3>
<h3>Votre Dossier a été traiter a partir du <strong>${ dateDebut}</strong> est a été fini le <strong>${ date}</strong></h3>
<img src="./img/complet.png" alt="" width="150" length="150" align="right">
</c:if>
<c:if test="${numTacheEnCour != maxTache }">
<h3>Progression de Dossier: <strong>(${ id_doc})</strong> </h3>
<h3>Votre Dossier a été traiter a partir du <strong>${ dateDebut}</strong> est prevu d'etre fini le <strong>${ dateFin}</strong></h3>
</c:if>
<table><thead><h3><tr><th><i></i>Temp passé: <strong>(${ elapsed})</strong></th>    <th><i></i>Temp restant: <strong>(${ left})</strong></th>     <th><i></i>Temp total: <strong>(${ totalTime})</strong></th></tr> </h3></thead></table>
</p>
<a href="http://localhost:8080/Tracking/loginClient?id_doc=${ id_doc}">sauvegarder</a>
<%! int i = 0; %>
<!-- partial:index.partial.html -->
<div class="md-stepper-horizontal orange">
<c:forEach items="${ tache}" var="tache"> 
 <% i++; %>
 		

 
<c:if test="${tache.valide == true }">
<c:if test="${tache.turn == false }">
    <div class="md-step active done">
      <div class="md-step-circle"><span><%= i  %></span></div>
      <div class="md-step-title">${ tache.libelle}</div>
      <div class="md-step-optional">${ tache.dateFin}</div>
      <div class="md-step-bar-left"></div>
      <div class="md-step-bar-right"></div>
    </div>
    </c:if>
    <c:if test="${tache.turn == true }">
    <div class="md-step active ">
      <div class="md-step-circle"><span><%= i  %></span></div>
      <div class="md-step-title">${ tache.libelle}</div>
      <div class="md-step-optional">${ tache.dateFin}</div>
      <div class="md-step-bar-left"></div>
      <div class="md-step-bar-right"></div>
    </div>
  </c:if>
</c:if>
<c:if test="${tache.valide == false }">
   <c:if test="${tache.turn == false }">
    <div class="md-step">
      <div class="md-step-circle"><span><%= i  %></span></div>
      <div class="md-step-title">${ tache.libelle}</div>
      <div class="md-step-optional">${ tache.dateFin}</div>
      <div class="md-step-bar-left"></div>
      <div class="md-step-bar-right"></div>
    </div>
   </c:if>
    <c:if test="${tache.turn == true }">
	<div class="md-step active ">
      <div class="md-step-circle"><span><%= i  %></span></div>
      <div class="md-step-title">${ tache.libelle}</div>
      <div class="md-step-optional">${ tache.dateFin}</div>
      <div class="md-step-bar-left"></div>
      <div class="md-step-bar-right"></div>
    </div>
   </c:if>
</c:if>
</c:forEach> 
<% i=0; %>




<br><br>
<br><br>
  </div>
<!-- partial -->


  <script src='https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.5/angular.min.js'></script>
   
</body>
</html>