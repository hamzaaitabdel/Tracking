<%@page import="entities.Graphinfo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ page import="entities.Dossier" %>
        <%@ page import="entities.Responsable" %>
        <%@ page import="entities.AdditionalDoc" %>
        
        <%@ page import="entities.Tache" %>
        <%@ page import="entities.Admin" %>
    <%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="web.TrackingServlet"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Statistics</title>
    
    <!-- pie-->
    
      <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="description" content="Gradient Able Bootstrap admin template made using Bootstrap 4. The starter version of Gradient Able is completely free for personal project." />
      <meta name="keywords" content="free dashboard template, free admin, free bootstrap template, bootstrap admin template, admin theme, admin dashboard, dashboard template, admin template, responsive" />
      <meta name="author" content="codedthemes">
      <!-- Favicon icon -->
      <link rel="icon" href="assets/images/logo5.png" type="image/x-icon">
      <!-- Google font-->
      <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" rel="stylesheet">
      <!-- Required Fremwork -->
      <link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css">
      <!-- themify-icons line icon -->
      <link rel="stylesheet" type="text/css" href="assets/icon/themify-icons/themify-icons.css">
	  <link rel="stylesheet" type="text/css" href="assets/icon/font-awesome/css/font-awesome.min.css">
      <!-- ico font -->
      <link rel="stylesheet" type="text/css" href="assets/icon/icofont/css/icofont.css">
      <!-- Style.css -->
      <link rel="stylesheet" type="text/css" href="assets/css/style.css">
      <link rel="stylesheet" type="text/css" href="assets/css/jquery.mCustomScrollbar.css">
  </head>

  <body>
  <body>
	  
       <!-- Pre-loader start -->
    <div class="theme-loader">
        <div class="loader-track">
            <div class="loader-bar"></div>
        </div>
    </div>
    <!-- Pre-loader end -->
    <div id="pcoded" class="pcoded">
        <div class="pcoded-overlay-box"></div>
        <div class="pcoded-container navbar-wrapper">

            <nav class="navbar header-navbar pcoded-header">
               <div class="navbar-wrapper">
                   <div class="navbar-logo">
                       <a class="mobile-menu" id="mobile-collapse" href="#!">
                           <i class="ti-menu"></i>
                       </a>
                       
                       <div class="mobile-search">
                           <div class="header-search">
                               <div class="main-search morphsearch-search">
                                   <div class="input-group">
                                       <span class="input-group-addon search-close"><i class="ti-close"></i></span>
                                       <input type="text" class="form-control" placeholder="Enter Keyword">
                                   </div>
                               </div>
                           </div>
                       </div>
                       <a href="http://localhost:8080/Tracking/statistics">
                           <img class="img-fluid" src="assets/images/logo1.png" height="130px" width="180px" alt="Theme-Logo" />
                       </a>
                       <a class="mobile-options">
                           <i class="ti-more"></i>
                       </a>
                   </div>

                   <div class="navbar-container container-fluid">
                       <ul class="nav-left">
                           <li>
                               <div class="sidebar_toggle"><a href="javascript:void(0)"><i class="ti-menu"></i></a></div>
                           </li>
                           <li class="header-search">
                               <div class="main-search morphsearch-search">
                                   <div class="input-group">
                                       <span class="input-group-addon search-close"><i class="ti-close"></i></span>
                                       <input type="text" class="form-control">
                                       <span class="input-group-addon search-btn"><i class="ti-search"></i></span>
                                   </div>
                               </div>
                           </li>
                           <li>
                               <a href="#!" onclick="javascript:toggleFullScreen()">
                                   <i class="ti-fullscreen"></i>
                               </a>
                           </li>
                       </ul>
                       <ul class="nav-right">
                           <li class="header-notification">
                               <a href="#!">
                                   <i class="ti-bell"></i>
                                   <span class="badge bg-c-pink"></span>
                               </a>
                               <ul class="show-notification">
                                   <li>
                                       <h6>Benachrichtigungen</h6>
                                       <label class="label label-danger">New</label>
                                   </li>
                                   <c:forEach items="${additional }" var="additional">
                                          <li >
                                       <div class="media" onclick="location.href='http://localhost:8080/Tracking/confirmrequest?id_add=${additional.id}';">
                                           <img class="d-flex align-self-center img-radius" src="assets/images/avatar-2.jpg" alt="Generic placeholder image">
                                           <div class="media-body">
                                               <h5 class="notification-user">${additional.type }</h5>
                                               <p class="notification-msg">Die Datei ${additional.id_tache} ben&ouml;tigt "${additional.type }" , um die Behandlung abzuschlie&szlig;en</p>
                                               <span class="notification-time">letzte Verz&ouml;gerung :${additional.date }</span>
                                           </div>
                                       </div>
                                   </li>
                                  </c:forEach>
                               </ul>
                           </li>
                           
                           <li class="user-profile header-notification">
                               <a href="#!">
                                   <img src="${admin.photo }" class="img-radius" alt="User-Profile-Image">
                                   <span>${admin.nom }</span>
                                   <i class="ti-angle-down"></i>
                               </a>
                               <ul class="show-notification profile-notification">
                                   
                                   
                                   <li>
                                       <a href="http://localhost:8080/Tracking/deconnexion">
                                       <i  class="ti-layout-sidebar-left"></i> Ausloggen
                                   </a>
                                   </li>
                               </ul>
                           </li>
                       </ul>
                   </div>
               </div>
           </nav>
            <div class="pcoded-main-container">
                <div class="pcoded-wrapper">
                    <nav class="pcoded-navbar">
                        <div class="sidebar_toggle"><a href="#"><i class="icon-close icons"></i></a></div>
                        <div class="pcoded-inner-navbar main-menu">
                            
                            <ul class="pcoded-item pcoded-left-item">
                                <li class="active">
                                    <a href="http://localhost:8080/Tracking/statistics">
                                        <span class="pcoded-micon"><i class="ti-home"></i><b>D</b></span>
                                        <span class="pcoded-mtext" data-i18n="nav.dash.main">Instrumententafel</span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                                
                            </ul>
                            <div class="pcoded-navigatio-lavel" data-i18n="nav.category.forms">Verwaltung</div>
                            <ul class="pcoded-item pcoded-left-item">
                                <li>
                                    <a href="http://localhost:8080/Tracking/ajoutDoc">
                                        <span class="pcoded-micon"><i class="ti-folder"></i><b></b></span>
                                        <span class="pcoded-mtext" data-i18n="nav.form-components.main">Einen Ordner hinzufugen</span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                                <li>
                                    <a href="http://localhost:8080/Tracking/ajoutRespo">
                                        <span class="pcoded-micon"><i class="ti-user"></i><b></b></span>
                                        <span class="pcoded-mtext" data-i18n="nav.form-components.main">Einen Manager hinzufugen</span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                                <li>
                                    <a href="http://localhost:8080/Tracking/ajoutTache">
                                        <span class="pcoded-micon"><i class="ti-share"></i><b>FC</b></span>
                                        <span class="pcoded-mtext" data-i18n="nav.form-components.main">Verwaltung der Aufgaben<li>
                                    <a href="bs-basic-table.html">
                                        <span class="pcoded-micon"><i class="ti-layers"></i><b>FC</b></span>
                                        <span class="pcoded-mtext" data-i18n="nav.form-components.main">empty</span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li></span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>

                            </ul>

                            <ul class="pcoded-item pcoded-left-item">
                               
                                <li>
                                   
                                </li>
                                <li class="pcoded-hasmenu">
                                    
                                    <ul class="pcoded-submenu">
                                       
                                        <li class=" ">
                                            
                                        </li>
                                        <li class=" ">
                                            
                                        </li>
                                    </ul>
                                </li>

                            </ul>

                            <ul class="pcoded-item pcoded-left-item">
                                <li class="pcoded-hasmenu ">
                                    
                                    
                        </div>
                    </nav>
                    <div class="pcoded-content">
                        <div class="pcoded-inner-content">
                            <div class="main-body">
                                <div class="page-wrapper">

                                    <div class="page-body">
                                      <div class="row">

                                            <!-- order-card start -->
                                            <div class="col-md-6 col-xl-3">
                                                <div class="card bg-c-blue order-card">
                                                    <div class="card-block">
                                                        <h6 class="m-b-20">Gesamtzahl der Dateien</h6>
                                                        <h2 class="text-right"><i class="ti-folder f-left"></i><span>${nbrDossier }</span></h2>
                                                        <p class="m-b-0">vollständige Datei<span class="f-right">${nbrDossier-14}</span></p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-xl-3">
                                                <div class="card bg-c-green order-card">
                                                    <div class="card-block">
                                                        <h6 class="m-b-20">Total verantwortlich</h6>
                                                        <h2 class="text-right"><i class="fa fa-user-circle-o f-left"></i><span>${nbrResponsable }</span></h2>
                                                       <p class="m-b-0">habe einen aktuellen tache<span class="f-right">${nbrResponsableTache }</span></p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-xl-3">
                                                <div class="card bg-c-yellow order-card">
                                                    <div class="card-block">
                                                        <h6 class="m-b-20">Kunden insgesamt</h6>
                                                        <h2 class="text-right"><i class="fa fa-user-circle f-left"></i><span>${nbrClient }</span></h2>
                                                        <p class="m-b-0">registriert<span class="f-right">${registredClient }</span></p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-xl-3">
                                                <div class="card bg-c-pink order-card">
                                                    <div class="card-block">
                                                        <h6 class="m-b-20">Gesamtgewinn</h6>
                                                        <h2 class="text-right"><i class="ti-wallet f-left"></i><span>${premiumAccounts*25}$</span></h2>
                                                        <p class="m-b-0">Premium-Konten<span class="f-right">${premiumAccounts}</span></p>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- order-card end -->

                                            <!-- statustic and process start -->
                                            <div class="col-lg-8 col-md-12">
                                                <div class="card" style="width: 1000px;">
                                                    <div class="card-header">
                                                        <h5>Statistik der pro Tag erstellten Dateien(die letzten 4 Tage)</h5>
                                                        <div class="card-header-right">
                                                            <ul class="list-unstyled card-option">
                                                                <li><i class="fa fa-chevron-left"></i></li>
                                                                <li><i class="fa fa-window-maximize full-card"></i></li>
                                                                <li><i class="fa fa-minus minimize-card"></i></li>
                                                                <li><i class="fa fa-refresh reload-card"></i></li>
                                                                <li><i class="fa fa-times close-card"></i></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="card-block">
                                                        <!-- graph line here-->
                                                        <table id="q-graph"style="margin-left: 40px">
<thead>
<tr>
<th></th>
<th class="sent">Anzahl der Dateien pro Tag erstellen</th>
</tr>
</thead>
<tbody>
  
<c:forEach items="${graphData }" var="data">
<tr class="qtr" id="q${data.index+1}">
<th class="datelabel"cope="row">${data.day} ${data.month}</th>
<td class="sent bar" style="height: ${data.height}px;"><p>${data.operations}</p></td>

</tr>
</c:forEach>
<!--  
<tr class="qtr" id="q1">
<th scope="row">Q1</th>
<td class="sent bar" style="height: 111px;"><p>$18,450.00</p></td>
<td class="paid bar" style="height: 99px;"><p>$16,500.00</p></td>
</tr>
<tr class="qtr" id="q2">
<th scope="row">Q2</th>
<td class="sent bar" style="height: 206px;"><p>$18,340.72</p></td>
<td class="paid bar" style="height: 194px;"><p>$32,340.72</p></td>
</tr>
<tr class="qtr" id="q3">
<th scope="row">Q3</th>
<td class="sent bar" style="height: 259px;"><p>$43,145.52</p></td>
<td class="paid bar" style="height: 193px;"><p>$32,225.52</p></td>
</tr>
<tr class="qtr" id="q4">
<th scope="row">Q4</th>
<td class="sent bar" style="height: 110px;"><p>$18,415.96</p></td>
<td class="paid bar" style="height: 195px;"><p>$32,425.00</p></td>
</tr>
-->
</tbody>
</table>

<div id="ticks"style="margin-left: 40px">
<div class="tick" style="height: 59px;"><p>10</p></div>
<div class="tick" style="height: 59px;"><p>8</p></div>
<div class="tick" style="height: 59px;"><p>6</p></div>
<div class="tick" style="height: 59px;"><p>4</p></div>
<div class="tick" style="height: 59px;"><p>2</p></div>
</div>
<style>




#q-graph {
  display: block; /* fixes layout wonkiness in FF1.5 */
  position: relative; 
  width: 600px; 
  height: 300px;
  margin: 1.1em 0 0; 
  padding: 0;
  background: transparent;
  font-size: 11px;
}

#q-graph caption {
  caption-side: top; 
  width: 600px;
  text-transform: uppercase;
  letter-spacing: .5px;
  top: -40px;
  position: relative; 
  z-index: 10;
  font-weight: bold;
}

#q-graph tr, #q-graph th, #q-graph td { 
  position: absolute;
  bottom: 0; 
  width: 150px; 
  z-index: 2;
  margin: 0; 
  padding: 0;
  text-align: center;
}

#q-graph td {
  transition: all .3s ease;
  }
  td:hover {
 
    background-color: desaturate(#85144b, 100);
    opacity: .9;
    color: white;
  }

  
#q-graph thead tr {
  left: 100%; 
  top: 50%; 
  bottom: auto;
  margin: -2.5em 0 0 5em;}
#q-graph thead th {
  width: 7.5em; 
  height: auto; 
  padding: 0.5em 1em;
}
#q-graph thead th.sent {
  top: 0; 
  left: 0; 
  line-height: 2;
}
#q-graph thead th.paid {
  top: 2.75em; 
  line-height: 2;
  left: 0; 
}

#q-graph tbody tr {
  height: 296px;
  padding-top: 2px;
  border-right: 1px dotted #C4C4C4; 
  color: #AAA;
}
#q-graph #q1 {
  left: 0;
  bottom: 5px;
}
#q-graph #q2 {left: 150px;bottom: 5px;}
#q-graph #q3 {left: 300px;bottom: 5px;}
#q-graph #q4 {left: 450px; border-right: none;bottom: 5px;}
#q-graph tbody th {bottom: -1.75em; vertical-align: top ;color:#277a6d;
font-weight: bolder; right:30px}
#q-graph .bar {
  width: 60px; 
  border: 1px solid; 
  border-bottom: none; 
  color: #000;
}
#q-graph .bar p {
  margin: 5px 0 0; 
  padding: 0;
  opacity: .4;
  font-size: medium;
  font-weight: bold;
}
#q-graph .sent {
  left: 13px; 
  background-color: #39cccc;
  border-color: transparent;
}
#q-graph .paid {
  left: 77px; 
  background-color: #7fdbff;
  border-color: transparent;
}


#ticks {
  position: relative; 
  top: -300px; 
  left: 2px;
  width: 596px; 
  height: 300px; 
  z-index: 1;
  margin-bottom: -300px;
  font-size: 10px;
  font-family: "fira-sans-2", Verdana, sans-serif;
}

#ticks .tick {
  position: relative; 
  border-bottom: 1px dotted #C4C4C4; 
  width: 600px;
}

#ticks .tick p {
  position: absolute; 
  left: -5em; 
  top: -0.8em; 
  margin: 0 0 0 0.5em;
}
</style>

                                                    </div>
                                                </div>
                                            </div>
                                           
                                            <div class="col-lg-4 col-md-12">
                                                <!--

                                                    <div class="card">
                                                    <div class="card-header">
                                                        <h5>Customer Feedback</h5>
                                                    </div>
                                                    <div class="card-block">
                                                        <span class="d-block text-c-blue f-24 f-w-600 text-center">365247</span>
                                                        
                                                        <div id="piechart_3d" style="width: 500px; height: 500px;align-content: center;"></div>
                                                        <div class="row justify-content-center m-t-15">
                                                            <div class="col-auto b-r-default m-t-5 m-b-5">
                                                                <h4>83%</h4>
                                                                <p class="text-success m-b-0"><i class="ti-hand-point-up m-r-5"></i>Positive</p>
                                                            </div>
                                                            <div class="col-auto m-t-5 m-b-5">
                                                                <h4>17%</h4>
                                                                <p class="text-danger m-b-0"><i class="ti-hand-point-down m-r-5"></i>Negative</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                -->
                                            </div>
                                            <!-- statustic and process end -->
											<!-- tabs card start -->
                                            <div class="col-sm-12">
                                                <div class="card tabs-card" >
                                                    <div class="card-block p-0">
                                                        <!-- Nav tabs -->
                                                        <ul class="nav nav-tabs md-tabs" role="tablist">
                                                            <li class="nav-item">
                                                                <a class="nav-link active" data-toggle="tab" href="#home3" role="tab"><i class="fa fa-home"></i>Mitarbeiter</a>
                                                                <div class="slide"></div>
                                                            </li>
                                                            <li class="nav-item">
                                                                <a class="nav-link" data-toggle="tab" href="#profile3" role="tab"><i class="fa fa-key"></i>Client</a>
                                                                <div class="slide"></div>
                                                            </li>
                                                            <li class="nav-item">
                                                                <a class="nav-link" data-toggle="tab" href="#messages3" role="tab"><i class="fa fa-play-circle"></i>Dateien</a>
                                                                <div class="slide"></div>
                                                            </li>
                                                            
                                                        </ul>
                                                        <!-- Tab panes -->
                                                        <div class="tab-content card-block">
                                                            <div class="tab-pane active" id="home3" role="tabpanel">

                                                                <div class="table-responsive">
                                                                    <table class="table">
                                                                        <tr>
                                                                            <th>Id</th>
                                                                            <th>Image</th>
                                                                            <th>Name</th>
                                                                            <th>Email</th>
                                                                            <th>Status</th>
                                                                            <th>password</th>
                                                                        </tr>
                                                                        <c:forEach items="${responsables }" var="respo">
                                                                            <tr>
                                                                            <td>${respo.id_respo}</td>
                                                                            <td><img src="${respo.photo }" alt="prod img" class="img-fluid" style="height: 40px;width: 40px"></td>
                                                                            <td>${respo.nom}</td>
                                                                            <td>${respo.email }</td>
                                                                            <td><span class="${respo.online ? "label label-success" : "label label-danger"}">${respo.online ? "online" : "offline"}</span></td>
                                                                            <td>${respo.password }</td>
                                                                        </tr>
                                                                        </c:forEach>
                                                                       
                                                                    </table>
                                                                </div>
                                                                
                                                            </div>
                                                            <div class="tab-pane" id="profile3" role="tabpanel">

                                                                <div class="table-responsive">
                                                                    <table class="table">
                                                                        <tr>
                                                                            <th>Kundennummer</th>
                                                                            <th>Name</th>
                                                                            <th>Email</th>
                                                                            <th>Passwort</th>
                                                                            <th>Adresse</th>
                                                                            <th>Telefonnummer</th>
                                                                            <th>Zustand</th>
                                                                            <th>Aktion</th>
                                                                        </tr>
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
		<span class="label label-success label-mini">STANDARD</span>
			  </c:if>
			  </td> 
                     <td>
                     <a href="http://localhost:8080/Tracking/editClient?id_r=${ client.id_client}"> <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button></a>
                     <a href="http://localhost:8080/Tracking/deleteClient?id_r=${ client.id_client}"  onclick="return confirm('Voulez vous vraiment supprimer ce client?')"><button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button></a>
                      </td>
                 </tr>
                  </c:forEach>
                                                                    </table>
                                                                </div>
                                                                
                                                            </div>
                                                            <div class="tab-pane" id="messages3" role="tabpanel">

                                                                <div class="table-responsive">
                                                                    <table class="table">
                                                                        <tr>
                                                                            <th>Dateinummmer</th>
                                                                            <th>Manager</th>
                                                                            <th>Name des Kunden</th>
                                                                            <th>Typ</th>
                                                                            <th>Dauer</th>
                                                                            <th>TStartdatum</th>
                                                                            <th>Enddatum</th>
                                                                            <th>Fortschritt	</th>
                                                                            <th>Zustand</th>
                                                                            <th>Aktion</th>
                                                                        </tr>
                                                                        <!--  
                                                                        <tr>
                                                                            <td><img src="assets/images/product/prod1.jpg" alt="prod img" class="img-fluid"></td>
                                                                            <td>PNG002413</td>
                                                                            <td>Jane Elliott</td>
                                                                            <td>06-01-2017</td>
                                                                            <td><span class="label label-primary">Shipping</span></td>
                                                                            <td>#7234421</td>
                                                                        </tr>
                                                                        -->
   <c:forEach items="${ dossier}" var="dossier">
   <tr>
                    <td>
                      <a>${ dossier.id_doc}</a>
                      <div class="btn-group">
                <button type="button" class="label  label-mini" data-toggle="dropdown">
                  <span class="caret"></span>
                  </button>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="http://localhost:8080/Tracking/viewTache?idST=${ dossier.id_doc}">Konsultieren Sie die Aufgaben</a></li>
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
		<span class="label label-warning label-mini">zu best&auml;tigen</span>
			  </c:if>
			   <c:if test="${dossier.maxTache > 0}">
			  <c:if test="${dossier.maxTache < dossier.totalTache}">
		<span class="label label-info label-mini">Laufend</span>
			  </c:if>
			  <c:if test="${dossier.maxTache == dossier.totalTache}">
		<span class="label label-success label-mini">Ausgef&uuml;llt</span>
			  </c:if>
			   </c:if>
			  </td>
                     <td>
                     <a href="http://localhost:8080/Tracking/editDoc?id_d=${ dossier.id_doc}"> <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button></a>
                     <a href="http://localhost:8080/Tracking/deleteDoc?id_d=${ dossier.id_doc}"  onclick="return confirm('Voulez vous vraiment supprimer ce dossier?')"><button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button></a>
                      </td>
                      </tr>
   </c:forEach>
                                                                        
                                                                        
                                                                    </table>
                                                                </div>
                                                                
                                                            </div>
                                                            
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- tabs card end -->

                                            <!-- social statustic start -->
                                            <div class="col-md-12 col-lg-4">
                                                <div class="card">
                                                    <div class="card-block text-center">
                                                        <i class="fa fa-envelope-open text-c-blue d-block f-40"></i>
                                                        <h4 class="m-t-20"><span class="text-c-blue">8.62k</span> Subscribers</h4>
                                                        <p class="m-b-20">Your main list is growing</p>
                                                        <button class="btn btn-primary btn-sm btn-round">Manage List</button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-lg-4">
                                                <div class="card">
                                                    <div class="card-block text-center">
                                                        <i class="fa fa-twitter text-c-green d-block f-40"></i>
                                                        <h4 class="m-t-20"><span class="text-c-blgreenue">+40</span> Followers</h4>
                                                        <p class="m-b-20">Your main list is growing</p>
                                                        <button class="btn btn-success btn-sm btn-round">Check them out</button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-lg-4">
                                                <div class="card">
                                                    <div class="card-block text-center">
                                                        <i class="fa fa-puzzle-piece text-c-pink d-block f-40"></i>
                                                        <h4 class="m-t-20">Business Plan</h4>
                                                        <p class="m-b-20">This is your current active plan</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- social statustic end -->

                                            <!-- users visite and profile start -->
                                            <div class="col-md-4">
                                               
                                            <div class="col-md-8">
                                                <div class="card">
                                                   
                                                   
                                            <!-- users visite and profile end -->

                                        </div>
                                    </div>

                                    <div id="styleSelector">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Warning Section Starts -->
        <!-- Older IE warning message -->
    <!--[if lt IE 9]>
<div class="ie-warning">
    <h1>Warning!!</h1>
    <p>You are using an outdated version of Internet Explorer, please upgrade <br/>to any of the following web browsers to access this website.</p>
    <div class="iew-container">
        <ul class="iew-download">
            <li>
                <a href="http://www.google.com/chrome/">
                    <img src="assets/images/browser/chrome.png" alt="Chrome">
                    <div>Chrome</div>
                </a>
            </li>
            <li>
                <a href="https://www.mozilla.org/en-US/firefox/new/">
                    <img src="assets/images/browser/firefox.png" alt="Firefox">
                    <div>Firefox</div>
                </a>
            </li>
            <li>
                <a href="http://www.opera.com">
                    <img src="assets/images/browser/opera.png" alt="Opera">
                    <div>Opera</div>
                </a>
            </li>
            <li>
                <a href="https://www.apple.com/safari/">
                    <img src="assets/images/browser/safari.png" alt="Safari">
                    <div>Safari</div>
                </a>
            </li>
            <li>
                <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                    <img src="assets/images/browser/ie.png" alt="">
                    <div>IE (9 & above)</div>
                </a>
            </li>
        </ul>
    </div>
    <p>Sorry for the inconvenience!</p>
</div>
<![endif]-->
<!-- Warning Section Ends -->
<!-- Required Jquery -->
<script type="text/javascript" src="assets/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="assets/js/popper.js/popper.min.js"></script>
<script type="text/javascript" src="assets/js/bootstrap/js/bootstrap.min.js"></script>
<!-- jquery slimscroll js -->
<script type="text/javascript" src="assets/js/jquery-slimscroll/jquery.slimscroll.js"></script>
<!-- modernizr js -->
<script type="text/javascript" src="assets/js/modernizr/modernizr.js"></script>
<!-- am chart -->
<script src="assets/pages/widget/amchart/amcharts.min.js"></script>
<script src="assets/pages/widget/amchart/serial.min.js"></script>
<!-- Chart js -->
<script type="text/javascript" src="assets/js/chart.js/Chart.js"></script>
<!-- Todo js -->
<script type="text/javascript " src="assets/pages/todo/todo.js "></script>
<!-- Custom js -->
<script type="text/javascript" src="assets/pages/dashboard/custom-dashboard.min.js"></script>
<script type="text/javascript" src="assets/js/script.js"></script>
<script type="text/javascript " src="assets/js/SmoothScroll.js"></script>
<script src="assets/js/pcoded.min.js"></script>
<script src="assets/js/vartical-demo.js"></script>
<script src="assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
</body>

</html>
