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
<%! int i = 0; %>
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