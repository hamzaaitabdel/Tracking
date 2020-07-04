<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ page import="entities.Dossier" %>
        <%@ page import="entities.Responsable" %>
        <%@ page import="entities.Tache" %>
        <%@ page import="entities.Admin" %>
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
  <title>Admin</title>

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template 
  <link href="css/style1.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">
  
  -->
  
  <style>
        <%@include file="../css/style1.css" %>
        <%@include file="../css/style-responsive.css" %>
        <%@include file="../assets2/css/core.css" %>
        </style>
        <style><%@include file="../assets2/css/app.css" %> </style>
        <style>
        <%@include file="../assets2/css/core.css" %>
        </style>
  
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
      <a href="http://localhost:8082/Tracking/statistics" class="logo"><b>WEB<span>TRACK</span></b></a>
      <!--logo end-->
      <div class="nav notify-row" id="top_menu">
        <!--  notification start -->
        
          </li> 
        </ul> 
      </div>
      <div class="top-menu">
        <ul class="nav pull-right top-menu">
          <li>
            <a class="logout" href="http://localhost:8082/Tracking/deconnexion">Abmelden</a>
          </li>
        </ul>
      </div>
    </header>
    
    <aside>
      <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
          <p class="centered">
            <a ><img src="${admin.photo }" class="img-circle" width="100" height="100"></a>
          </p>
          <h5 class="centered">${admin.nom }</h5>
          <li class="mt">
            <a href="http://localhost:8082/Tracking/statistics">
              <i class="fa fa-dashboard"></i>
              <span>Gesamtansicht</span>
              </a>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>Handlungen</span>
              </a>
            <ul class="sub">
              <li>
                <a href="http://localhost:8082/Tracking/ajoutDoc">Einen Ordner hinzufügen</a>
              </li>
              <li>
                <a href="http://localhost:8082/Tracking/ajoutRespo">Einen Manager hinzufügen</a>
              </li>
              <li>
                <a href="http://localhost:8082/Tracking/ajoutTache">Verwaltung der Aufgaben</a>
              </li>
              <li>
                <a href="http://localhost:8082/Tracking/statistics">Übersicht</a>
              </li>  
            </ul>
          </li> 
           
        </ul>
        <!-- sidebar menu end-->
      </div>
    </aside>
    
    
    <section id="main-content">
      <section class="wrapper">
        </div>
        <div class="row mt">
          <div class="col-md-12">
          <c:if test="${succes != null }">
	<H2 style="color: green" role="alert">
  		<c:out value=" ${succes} "></c:out>
	</H2>
</c:if>
<c:if test="${erreur != null }">
	<H2 style="color: red"" role="alert">
  		<c:out value=" ${erreur} "></c:out>
	</H2>
</c:if>
            <div class="content-panel">
              <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>  Liste der Dateien</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bookmark"></i>Dateinummmer</th>
                    <th><i class=" fa fa-edit"></i> Manager</th>
                    <th><i class=" fa fa-edit"></i> Name des Kunden</th>
                    <th><i class=" fa fa-edit"></i> Typ</th>
                    <th><i class=" fa fa-edit"></i> Dauer</th>
                    <th><i class=" fa fa-edit"></i> Startdatum</th> 
                    <th><i class=" fa fa-edit"></i> Enddatum</th>
                    <th><i class=" fa fa-edit"></i> Fortschritt</th>
                    <th><i class="fa fa-edit"></i>Zustand</th>
                    <th><i class=" fa fa-edit"></i>Aktion</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${ dossier}" var="dossier">
                  <tr>
                    <td>
                      <a>${ dossier.id_doc}</a>
                      <div class="btn-group">
                <button type="button" class="label  label-mini" data-toggle="dropdown">
                  <span class="caret"></span>
                  </button>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="http://localhost:8082/Tracking/viewTache?idST=${ dossier.id_doc}">Konsultieren Sie die Aufgaben</a></li>
        </div>
                    </td>
                    <c:if test="${dossier.maxTache == dossier.totalTache}">
					<td>--------  ---------</td>
			 		 </c:if>
			 		 <c:if test="${dossier.maxTache != dossier.totalTache}">
                    <td>${ dossier.respo} </td>
                    </c:if>
                    <td>${ dossier.nom_cl}</td>
                    <td>${ dossier.type} </td> 
                    <td>${ dossier.dureeTotal} Jours </td>
                     <td>${ dossier.dateDebut}</td>  
                      <td>${ dossier.dateFin}</td>
                      
             <td> 
              <div class="progress progress-striped active">
                <div class="progress-bar" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100" style="width: ${ (dossier.maxTache/ dossier.totalTache)*100}%">
                  <span class="sr-only">${ (dossier.maxTache/ dossier.totalTache)*100}% Complete</span>
                </div>
              </div>
              </td>
              <td>
              <c:if test="${dossier.maxTache == 0}">
		<span class="label label-warning label-mini">zu bestätigen</span>
			  </c:if>
			   <c:if test="${dossier.maxTache > 0}">
			  <c:if test="${dossier.maxTache < dossier.totalTache}">
		<span class="label label-info label-mini">Laufend</span>
			  </c:if>
			  <c:if test="${dossier.maxTache == dossier.totalTache}">
		<span class="label label-success label-mini">Ausgefüllt</span>
			  </c:if>
			   </c:if>
			  </td>
                     <td>
                     <a href="http://localhost:8082/Tracking/editDoc?id_d=${ dossier.id_doc}"> <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button></a>
                     <a href="http://localhost:8082/Tracking/deleteDoc?id_d=${ dossier.id_doc}"  onclick="return confirm('Voulez vous vraiment supprimer ce dossier?')"><button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button></a>
                      </td>
                      </tr>
                  </c:forEach>

                </tbody>
              </table>
            </div>
            <!-- /content-panel -->
          </div>
          <!-- /col-md-12 -->
        </div>
        <!-- row -->
        <div class="row mt">
          <div class="col-md-12">
            <div class="content-panel">
              <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i> Liste der verantwortlichen Personen </h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bookmark"></i>Manager-Nummer</th>
                    <th class="fa fa-edit"> </i>Name</th>
                    <th><i class="fa fa-edit"></i>Email</th>
                    <th><i class=" fa fa-edit"></i> Passwort</th>
                     <th><i class=" fa fa-edit"></i>Aktion</th>
                      <th></th>
                  </tr>
                </thead>
                <tbody>
                
                <c:forEach items="${ responsable}" var="responsable">
                  <tr>
                    <td>${ responsable.id_respo}</td>
                    <td>${ responsable.nom}</td>
                    <td>${ responsable.email}</td> 
                     <td>${ responsable.password}</td>
                     <td>
                     <a href="http://localhost:8082/Tracking/editRespo?id_r=${ responsable.id_respo}"> <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button></a>
                     <a href="http://localhost:8082/Tracking/deleteRespo?id_r=${ responsable.id_respo}"  onclick="return confirm('Voulez vous vraiment supprimer ce responsable?')"><button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button></a>
                      </td>
                 </tr>
                  </c:forEach>
                   
                </tbody>
              </table>
            </div>
            <!-- /content-panel -->
          </div>
          <!-- /col-md-12 -->
        </div>
        <!-- /row -->
        <div class="row mt">
          <div class="col-md-12">
            <div class="content-panel">
              <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i> Kundenliste</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bookmark"></i>Kundennummer</th>
                    <th class="fa fa-edit"> </i>Name</th>
                    <th><i class="fa fa-edit"></i>Email</th>
                    <th><i class=" fa fa-edit"></i> Passwort</th>
                    <th class="fa fa-edit"> </i>Adresse</th>
                    <th><i class="fa fa-edit"></i>Telefonnummer</th>
                    <th><i class=" fa fa-edit"></i>Zustand</th>
                      <th></th>
                  </tr>
                </thead>
                <tbody>
                
                <c:forEach items="${ client}" var="client">
                  <tr>
                    <td>${ client.id_client}</td>
                    <td>${ client.nom}</td>
                     <td>${ client.email}</td>
                    <td>${ client.password}</td> 
                     <td>${ client.adresse}</td>
                     <td>${ client.tel}</td>
                    <td>
                    <c:if test="${client.premium == true}">
		<span class="label label-warning label-mini">PREMIUM</span>
			  </c:if>
			   <c:if test="${client.premium == false}">
		<span class="label label-success label-mini">Standard</span>
			  </c:if>
			  </td> 
                     <td>
                     <a href="http://localhost:8082/Tracking/editClient?id_r=${ client.id_client}"> <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button></a>
                     <a href="http://localhost:8082/Tracking/deleteClient?id_r=${ client.id_client}"  onclick="return confirm('Voulez vous vraiment supprimer ce client?')"><button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button></a>
                      </td>
                 </tr>
                  </c:forEach>
                   
                </tbody>
              </table>
            </div>
            <!-- /content-panel -->
          </div>
          <!-- /col-md-12 -->
        </div>
      </section>
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->
    <!--footer start-->
     
    <!--footer end-->
  </section>
  <!-- js placed at the end of the document so the pages load faster -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
  <script src="lib/jquery.scrollTo.min.js"></script>
  <script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
  <!--common script for all pages-->
  <script src="lib/common-scripts.js"></script>
  <!--script for this page-->
  
</body>

</html>
