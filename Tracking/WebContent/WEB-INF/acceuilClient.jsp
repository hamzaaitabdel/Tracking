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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Acceuil</title>
    <!-- ================= Favicon ================== -->
    <!-- Standard -->
    <link rel="shortcut icon" href="http://placehold.it/64.png/000/fff">
    <!-- Retina iPad Touch Icon-->
    <link rel="apple-touch-icon" sizes="144x144" href="http://placehold.it/144.png/000/fff">
    <!-- Retina iPhone Touch Icon-->
    <link rel="apple-touch-icon" sizes="114x114" href="http://placehold.it/114.png/000/fff">
    <!-- Standard iPad Touch Icon-->
    <link rel="apple-touch-icon" sizes="72x72" href="http://placehold.it/72.png/000/fff">
    <!-- Standard iPhone Touch Icon-->
    <link rel="apple-touch-icon" sizes="57x57" href="http://placehold.it/57.png/000/fff">
    <!-- Styles -->
    <link href="assets4/fontAwesome/css/fontawesome-all.min.css" rel="stylesheet">
    <link href="assets4/css/lib/themify-icons.css" rel="stylesheet">
    <link href="assets4/css/lib/mmc-chat.css" rel="stylesheet" />
    <link href="assets4/css/lib/sidebar.css" rel="stylesheet">
    <link href="assets4/css/lib/bootstrap.min.css" rel="stylesheet">
    <link href="assets4/css/lib/nixon.css" rel="stylesheet">
    <link href="assets4/lib/lobipanel/css/lobipanel.min.css" rel="stylesheet">
    <link href="assets4/css/style.css" rel="stylesheet">
</head>

<body>
    <div class="sidebar sidebar-hide-to-small sidebar-shrink sidebar-gestures">
        <div class="nano">
            <div class="nano-content">
                <ul>
                    <li class="active">
                        <a class="sidebar-sub-toggle">  Leitung <span class="sidebar-collapse-icon ti-angle-down"></span></a>
                       
                            <li><a href="http://localhost:8080/Tracking/acceuilClient"><i  class="ti-home" ></i>Startseite</a></li>
                             
                    </li>
                    <li><a href="http://localhost:8080/Tracking/saved"><i class="ti-view-list-alt"></i> Registrierte Dateien</a></li>
                
                    <li><a href="http://localhost:8080/Tracking/profile"><i class="ti-user"></i>Mein Profil</a></li>
                    </ul>
            </div>
        </div>
    </div>
    <!-- /# sidebar -->
    <div class="header">
        <div class="pull-left">
            <div class="logo">
                <a href="http://localhost:8080/Tracking/Acceuil">
                    <img id="logoImg" src="logo/loogo.png" data-logo_big="logo/loogo.png" data-logo_small="logo/loogoSmall.png" alt="Nixon" />
                </a>
            </div>
            <div class="hamburger sidebar-toggle">
                <span class="ti-menu"></span>
            </div>
        </div>
        <div class="pull-right p-r-15">
            <ul>
                
                
               <li class="header-icon dib">
                    <img class="avatar-img" src="assets4/images/avatar/fb.jpg" alt="" /> <span class="user-avatar">${ client.nom } <i class="ti-angle-down f-s-10"></i></span>
                    <div class="drop-down dropdown-profile">
                        <div class="dropdown-content-heading">
                        <c:if test="${client.premium == false }">
                    <span class="text-left">Wollen Sie mehr?</span>
                          <p class="trial-day" ><a href="http://localhost:8080/Tracking/premium?id_client=${ client.id_client }">PREMIUM WERDEN</a></p>
                        </c:if>
                    <c:if test="${client.premium == true }">
                        <span class="text-left">Mein Paket stornieren </span>
                         <p class="trial-day" ><a href="http://localhost:8080/Tracking/endPremium?id_client=${ client.id_client }"  onclick="return confirm('Möchten Sie Ihr Abonnement wirklich kündigen??')">PREMIUM</a></p>
                        </c:if>
                        </div>
                        <div class="dropdown-content-body">
                            <ul>
                                <li><a href="http://localhost:8080/Tracking/profile"><i class="ti-user"></i> <span>Meine Profil</span></a></li>
                                <li><a href="http://localhost:8080/Tracking/saved"><i class="ti-write"></i> <span>Meine Dateien</span></a></li>
                                <li><a href="http://localhost:8080/Tracking/deconnexion"><i class="ti-power-off"></i> <span>Abmelden</span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
     
    <!-- END chat Sidebar-->
    <div class="content-wrap">
        <div class="main">
            <div class="container-fluid">
                        <div class="page-header">
                            <div class="page-title">
                                <h1>Dashboard</h1>
                            </div>
                        </div>
                    </div> 
                    <!-- /# column -->
                </div>
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
                 <div class="card-body">
                                    <div class="icon-section">

                                        <div class="icon-section">
                            <div class="card alert">
                                <div class="card-header">
                                    <h4>Meine Dateien</h4>
                                </div>
                                <div class="card-body  card-content">
                                    <table class="table table-responsive table-hover ">
                                        <thead>
                                            <tr>
                                                <th>Dateinummer</th>
                                                <th>Typ</th>
                                                <th>Dauer</th>
                                                <th>Startdatum</th>
                                                <th>Enddatum</th>
                                                <th>Fortschritt</th>
                                                <th>Zustand</th> 
                                                <th>Fortschritt</th> 
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${ dossier}" var="dossier">
                                            <tr>
                                                <td>${ dossier.id_doc}</td>
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
			  <td><a  href="http://localhost:8080/Tracking/progression?tracking=${dossier.id_doc }"  ><button type="button" class="btn btn-dark btn-outline m-b-10 m-l-5">Verfolgen</button></a></td>
                                            </tr>
                                            </c:forEach>
                                            
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    <!-- /# row -->
                    
     </div>
     <!-- /# main content -->
 </div>
 <!-- /# container-fluid -->
</div>
<!-- /# main -->
</div>
<!-- /# content wrap -->
<script src="assets4/js/lib/jquery.min.js"></script>
<!-- jquery vendor -->
<script src="assets4/js/lib/jquery.nanoscroller.min.js"></script>
<!-- nano scroller -->
<script src="assets4/js/lib/sidebar.js"></script>
<!-- sidebar -->
<script src="assets4/js/lib/bootstrap.min.js"></script>
<!-- bootstrap -->
<script src="assets4/js/lib/mmc-common.js"></script>
<script src="assets4/js/lib/mmc-chat.js"></script>
<!--  Chart js -->
<script src="assets4/js/lib/chart-js/Chart.bundle.js"></script>
<script src="assets4/js/lib/chart-js/chartjs-init.js"></script>
<!-- // Chart js -->
<!--  Datamap -->
<script src="assets4/js/lib/datamap/d3.min.js"></script>
<script src="assets4/js/lib/datamap/topojson.js"></script>
<script src="assets4/js/lib/datamap/datamaps.world.min.js"></script>
<script src="assets4/js/lib/datamap/datamap-init.js"></script>
<script src="assets4/lib/lobipanel/js/lobipanel.js"></script>
<!-- // Datamap -->
<script src="assets4/js/scripts.js"></script>
<!-- scripit init-->
<script>
    $(document).ready(function() {
        $('#lobipanel-custom-control').lobiPanel({
            reload: false,
            close: false,
            editTitle: false
        });
    });
</script>
</body>

</html>