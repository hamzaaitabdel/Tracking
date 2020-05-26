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
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
      <div class="sidebar-toggle-box">
        <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
      </div>
      <!--logo start-->
      <a href="http://localhost:8080/Tracking/Respo" class="logo"><b>WEB<span>TRACK</span></b></a>
      <!--logo end-->
      <div class="nav notify-row" id="top_menu">
        <!--  notification start -->
        <ul class="nav top-menu">
          <!-- settings start -->
          <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle">
              <i class="fa fa-tasks"></i>
              </a>
            <ul class="dropdown-menu extended tasks-bar">
              <div class="notify-arrow notify-arrow-green"></div>
              
              <li>
                <p class="green">Weiterentwicklung:</p>
              </li>
              <c:forEach items="${ tachProg}" var="tache">
              <li>
               <a href="#">
                  <div class="task-info">
                    <div class="desc">Datei: ${ tache.id_doc} ,  ${ tache.d_type}</div>
<%--                  <div class="percent">${ (tache.maxTache/ tache.totalTache)*100}%</div> --%>
                  <div class="progress progress-striped active">
                <div class="progress-bar" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100" style="width: ${ (tache.maxTache/ tache.totalTache)*100}%">
                  
                    
                </div>
                </div>
               </div>
                  
                  </a>
              </li>
              </c:forEach>
              <li>
               
                <a href="http://localhost:8080/Tracking/tacheEnCours">Alle laufende Aufgaben anzeigen </a>
              </li>
            </ul>
          </li>
          <!-------------------------------------------------------------------------------------------------------------------------------->
          <li id="header_inbox_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="http://localhost:8080/Tracking/tacheEnCours">
              <i class="fa fa-envelope-o"></i>
              <span class="badge bg-theme">${ totalAp}</span>
              </a>
            <ul class="dropdown-menu extended inbox">
              <div class="notify-arrow notify-arrow-green"></div>
              <li>
                <p class="green">Laufende Aufgaben</p>
              </li>
             <c:forEach items="${ tachEC}" var="tache">
              <li> 
              <a href="http://localhost:8080/Tracking/tacheEnCours">
              <span class="photo"><img alt="avatar" src="img/doc.jpg"></span>
                  <span class="subject">
                   <span class="from">${ tache.d_type}</span>
                  <span class="time">${ tache.dateFin}</span>
                  </span>
                  <span class="message">${ tache.libelle}
                  </span> 
                  </a>
              </li>
             </c:forEach>
               
             <li>
                <a href="http://localhost:8080/Tracking/tacheEnCours">Alle laufende Aufgaben anzeigen </a>
             </li>
            </ul>
          </li>
<!-------------------------------------------------------------------------------------------------------------------------------->
          <li id="header_notification_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
              <i class="fa fa-bell-o"></i>
              <span class="badge bg-warning">${ totalEn}</span>
              </a>
            <ul class="dropdown-menu extended notification">
              <div class="notify-arrow notify-arrow-yellow"></div>
              <li>
                <p class="yellow">Sie haben ${ totalEn} Dateien zu genehmigen</p>
              </li>
              <li>
                <c:forEach items="${ tachApr}" var="tache"> 
                  <li>
                <a href="http://localhost:8080/Tracking/tacheApprouver">
                  <span class="label label-warning"><i class="fa fa-bell"></i></span>
                  ${ tache.d_type}
                  <span class="small italic">,    ${ tache.libelle} (${ tache.id_doc})</span>
                  </a>
              </li>
               </c:forEach>
              </li>
               <li>
                <a href="http://localhost:8080/Tracking/tacheApprouver">Alle Aufgaben zur Genehmigung anzeigen</a>
              </li>
            </ul>
          </li> 
        </ul> 
      </div>
      <div class="top-menu">
        <ul class="nav pull-right top-menu">
          <li>
            <a class="logout" href="http://localhost:8080/Tracking/deconnexionRespo">Abmelden</a>
          </li>
        </ul>
      </div>
    </header>