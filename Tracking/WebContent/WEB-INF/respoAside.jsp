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
<aside>
      <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
          <p class="centered">
            <a ><img src="${ responsable.photo}" class="img-circle" width="80"></a>
          </p>
          <h5 class="centered">${ responsable.nom}</h5>
          <li class="mt">
            
            <a href="http://localhost:8080/Tracking/Respo">
              <i class="fa fa-dashboard"></i>
              <span>Aufgabenliste</span>
              </a>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>Heimarbeit</span>
              </a>
            <ul class="sub">
              <li>
                <a href="http://localhost:8080/Tracking/tacheEnCours">Zu erledigende Aufgaben</a>
              </li>
              <li>
                <a href="http://localhost:8080/Tracking/tacheApprouver">Zu genehmigende Aufgaben</a>
              </li>
              <li>
                <a href="http://localhost:8080/Tracking/tacheVenir">Kommende Aufgaben</a>
              </li>
              <li>
                <a href="http://localhost:8080/Tracking/tacheFinis">Abgeschlossene Aufgaben</a>
              </li> 
              <li>
                <a href="http://localhost:8080/Tracking/listMessenger">Austausch mit einem Netzwerk von Kollegen</a>
              </li>
            </ul>
          </li> 
           
        </ul>
        <!-- sidebar menu end-->
      </div>
    </aside>