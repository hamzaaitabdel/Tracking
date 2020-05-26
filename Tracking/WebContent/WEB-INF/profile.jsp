<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ page import="entities.Dossier" %>
        <%@ page import="entities.Responsable" %>
        <%@ page import="entities.Tache" %> 
         <%@ page import="entities.Client" %> 
    <%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="web.TrackingServlet"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
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
    <div class="chat-sidebar">
        <!-- BEGIN chat -->
        <div id="mmc-chat" class="color-default">
            <!-- BEGIN CHAT BOX -->
            <div class="chat-box">
                <!-- BEGIN CHAT BOXS -->
                <ul class="boxs"></ul>
                <!-- END CHAT BOXS -->
                <div class="icons-set">
                    <div class="stickers">
                        <div class="had-container">
                            <div class="row">
                                <div class="s12">
                                    <ul class="tabs" style="width: 100%;height: 60px;">
                                        <li class="tab col s3">
                                            <a href="#tab1" class="active">
                                                <img src="assets4/images/1.png" alt="" />
                                            </a>
                                        </li>
                                        </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END CHAT BOX -->
            <!-- BEGIN SIDEBAR -->
            
            <!-- END SIDEBAR -->
        </div>
        <!-- END chat -->
    </div>
    <!-- END chat Sidebar-->
    <div class="content-wrap">
        <div class="main">
            <div class="container-fluid">
                        <div class="page-header">
                            <div class="page-title">
                                <h1>Profil</h1>
                            </div>
                        </div>
                    </div> 
                    <!-- /# column -->
                </div>
                <!-- /# row -->
                 <div class="main-content">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="card alert">
                                <div class="card-body">
									<div class="user-profile">
										<div class="row">
											
											<div class="col-lg-8">
												<div class="user-profile-name">${ client.nom }</div>
												<div class="user-Location"><i class="ti-location-pin"></i> Meknès, Maroc</div>
												</div>
												<a  href="http://localhost:8080/Tracking/editProfile"  ><div ><button class="btn btn-primary btn-addon" type="button"><i class="ti-settings"></i>Modifier</button></a></div>
												<div class="custom-tab user-profile-tab">
													<ul class="nav nav-tabs" role="tablist">
														<li role="presentation" class="active"><a href="#1" aria-controls="1" role="tab" data-toggle="tab">Über uns</a></li>
														<li role="presentation"><a href="#2" aria-controls="2" role="tab" data-toggle="tab">Bankinformationen:</a></li>
													</ul>
													<div class="tab-content">
														<div role="tabpanel" class="tab-pane active" id="1">
															<div class="contact-information">
																<h4>Persönliche Informationen:</h4>
																<div class="phone-content">
																	<span class="contact-title">Telefonnummer:</span>
																	<span class="phone-number">${ client.tel }</span>
																</div>
																<div class="address-content">
																	<span class="contact-title">Adresse:</span>
																	<span class="mail-address">${ client.adresse }</span>
																</div>
																<div class="email-content">
																	<span class="contact-title">Email:</span>
																	<span class="contact-email">${ email }</span>
																</div>
																<div class="website-content">
																	<span class="contact-title">Passwort:</span>
																	<span class="contact-website">${ client.password }</span>
																</div>
																<div class="website-content">
																	<span class="contact-title">Zustand:</span>
																	<c:if test="${client.premium == false }">
																	<span class="contact-website">Standart</span>
																	</c:if>
																	<c:if test="${client.premium == true }">
																	<span class="contact-website">PREMIUM</span>
																	</c:if>
																</div>
															</div>
															</div>
															<div role="tabpanel" class="tab-pane active" id="2">
															<div class="basic-information">
																
																<c:if test="${client.premium == true }">
																<h4>Bankinformationen:</h4>
																<div class="birthday-content">
																	<span>Bankkartennummer    :</span>
																	<span class="birth-date">"${ client.carte }"</span>
																</div>
																<div class="gender-content">
																	<span class="contact-title">Ccv:</span>
																	<span class="gender">"${ client.ccv }"</span>
																</div>
																<div class="gender-content">
																	<span>Verfallsdatum:    </span>
																	<span class="gender">"${ client.expiry }"</span>
																</div>
																</c:if>
																<c:if test="${client.premium == false }">
																<h4>PREMIUM werden, um von einem PREMIUM-Service zu profitieren</h4>
																<div class="birthday-content">
											<center>	<a  href="http://localhost:8080/Tracking/premium?id_client=${ client.id_client }"  ><div ><button type="button" class="btn btn-success btn-flat btn-addon m-b-10 m-l-5"><i class="ti-user"></i>PREMIUM WERDEN</button></a></div>
															</center>	</div>
																</c:if>
															</div>
														</div>
												</div>
											</div>
										</div>
									</div>
								</div>
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