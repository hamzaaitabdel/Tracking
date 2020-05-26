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
    <meta charset="utf-8" />
    <title>Accueil</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
    <meta name="keywords" content="bootstrap 4, premium, marketing, multipurpose" />
    <meta content="Themesdesign" name="author" />
    <!-- favicon -->
    <link rel="shortcut icon" href="images/favicon.ico">

    <!-- google font -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">

    <!--Slider-->
    <link rel="stylesheet" href="css3/owl.carousel.css" />
    <link rel="stylesheet" href="css3/owl.theme.css" />
    <link rel="stylesheet" href="css3/owl.transitions.css" />

    <!-- Magnific-popup -->
    <link rel="stylesheet" type="text/css" href="css3/magnific-popup.css">

    <!-- css -->
    <link href="css3/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="css3/icons.css" rel="stylesheet" type="text/css" />
    <link href="css3/style.css" rel="stylesheet" type="text/css" />

</head>

<body>

    <!--Navbar Start-->
    <nav class="navbar navbar-expand-lg fixed-top navbar-custom sticky sticky-light" id="navbar">
        <div class="container">
            <!-- LOGO -->
            <a href="http://localhost:8080/Tracking/Acceuil" class="logo-sticky">
                <img src="img/logogo.png" alt="" class="logo-dark" height="32" />
                <img src="img/logogo.png" alt="" class="logo-light" height="32" />
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <i class="remixicon-menu-fill"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mx-auto navbar-center" id="mySidenav">
                </ul>
                <ul class="navbar-nav">
                     <li class="nav-item active">
                        <a href="http://localhost:8080/Tracking/Acceuil" class="nav-link">Startseite</a>
                    </li>
                    <c:if test="${session == null }">
                    <li class="nav-item">
                        <a href="http://localhost:8080/Tracking/loginClient" class="nav-link">Anmelden</a>
                        </li>
                        </c:if>
                    <c:if test="${session != null }">
                    <li class="nav-item">
                        <a href="http://localhost:8080/Tracking/acceuilClient" class="nav-link">Mein Raum</a>
                        </li>
                    <li class="nav-item">
                        <a href="http://localhost:8080/Tracking/deconnexion" class="nav-link">Abmelden</a>
                        </li>
                        </c:if>
                     
                </ul>
            </div>
        </div>
    </nav>
    <!-- Navbar End -->

    <!-- HERO START -->
    <section class="hero-4-bg position-relative bg-gradient" id="home">
   
        <div class="container">
            <div class="row align-items-center hero-4-content">
                <div class="col-lg-5">
                    <h1 class="text-white hero-4-title font-weight-medium mb-4 line-height-1_4">Verfolgen Sie Ihre Datei</h1>
                    <p class="text-white-50 mb-5">WEBTRACK bietet den schnellsten und genauesten Verfolgungsdienst auf der Grundlage einer intelligenten Datenanalyse..</p>
                    <div class="text-center subcribe-form mt-4">
                            <form action="progression" method="post">
                            <c:if test="${id_doc != null }">
                                <input type="text" value="${id_doc }" name="tracking">
                            </c:if>
                             <c:if test="${id_doc == null }">
                                <input type="text" placeholder="Geben Sie die Tracking-Nummer ein..." name="tracking">
                            </c:if>
                                <button type="submit" class="btn rounded-pill btn-purple">Verfolgen </button>
                            </form>
                        </div>
                </div>
                <div class="col-lg-6 offset-lg-1">
                   <div class="hero-4-img mt-5 mt-lg-0">
                        <img src="images/hero-4-img.png" alt="" class="img-fluid mx-auto d-block">
                   </div>
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="home-bottom-shape">
                    <img src="images/home-4-bottom-shape.png" alt="" class="img-fluid mx-auto d-block">
                </div>
            </div>
        </div>
    </section>
    <!-- HERO END -->



    
    <!-- FOOTER END -->

    <!-- javascript -->
    <script src="js2/jquery.min.js"></script>
    <script src="js2/bootstrap.bundle.min.js"></script>
    <script src="js2/scrollspy.min.js"></script>
    <script src="js2/jquery.easing.min.js"></script>
    <!-- COUNTER -->
    <script src="js2/counter.int.js"></script>
    <!-- carousel -->
    <script src="js2/owl.carousel.min.js"></script>
    <!-- Magnific Popup -->
    <script src="js2/jquery.magnific-popup.min.js"></script>
    <script src="js2//magnificpopup-int.js"></script>
    <!-- Main Js -->
    <script src="js2/app.js"></script>

</body>
</html>