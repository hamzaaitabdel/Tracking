 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required Meta Tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">

        <title>Premium membership</title>
        <link rel="icon" type="image/png" href="assets2/images/favicon.png" />

        <!--Core CSS -->
        <link rel="stylesheet" href="assets2/css/bulma.css">
        <link rel="stylesheet" href="assets2/css/app.css">
        <link rel="stylesheet" href="assets2/css/core.css">

    </head>
    <body>
        <div class="pageloader"></div>
        <div class="infraloader is-active"></div>        
        <!-- Hero and nav -->
        <div class="hero hero-waves-dark is-fullheight is-relative is-light-grey">
            
            <nav class="navbar navbar-wrapper navbar-default navbar-fade is-transparent">
                <div class="container">
                    <!-- Brand -->
                    <div class="navbar-brand">
                        <a class="navbar-item" href="http://localhost:8080/Tracking/Acceuil">
                            <img src="img/logogo.png" alt="">
                        </a>
            
                        <!-- Sidebar Trigger -->
                        <a id="navigation-trigger" class="navbar-item hamburger-btn" href="javascript:void(0);">
                                    <span class="rotate">
                                    </span>
                                </span>
                            </span>
                        </a>
            
                        <!-- Responsive toggle -->
                        <div class="custom-burger" data-target="">
                            <a id="" class="responsive-btn" href="javascript:void(0);">
                                <span class="menu-toggle">	
                                        <span class="rotate">
                                        </span>
                                    </span>
                                </span>
                            </a>
                        </div>
                        <!-- /Responsive toggle -->
                    </div>
            
                    <!-- Navbar menu -->
                    <div class="navbar-menu">
                        <!-- Navbar Start -->
                        <div class="navbar-start">
                            <!-- Navbar item -->
                            
                            <!-- Navbar item -->
                            <a class="navbar-item is-slide" href="http://localhost:8080/Tracking/CU">
                                Benutzer-Bedingung
                            </a>
                            <a class="navbar-item is-slide" href="http://localhost:8080/Tracking/Propos">
                                Über uns
                            </a>
                        </div>
            
                        <!-- Navbar end -->
                        <div class="navbar-end">
                            <!-- Signup button -->
                            <div class="navbar-item">
                                <a id="#signup-btn" href="http://localhost:8080/Tracking/acceuilClient" class="button button-cta btn-outlined is-bold btn-align primary-btn rounded raised">
                                    Startseite
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
            <!-- Hero -->
            <div id="main-hero" class="hero-body">
                <div class="container has-text-centered">
                    <div class="columns is-vcentered">
                        <div class="column is-6 is-offset-3 z-index-2">
                            <div id="login-card" class="animated preFadeInLeft fadeInLeft">
                                <div class="flex-card wavy-login-card">
                                    <h2 class="text-bold dark-text no-margin-bottom pt-20 pb-20">Persönliche Informationen</h2>
                                    
            	<c:if test="${succes != null }">
	<H2 style="color: green" role="alert">
  		<c:out value=" ${succes} "></c:out>
	</H2>
</c:if>
<c:if test="${erreur != null }">
	<center><H2 style="color: red"" role="alert">
  		<c:out value=" ${erreur} "></c:out>
	</H2></center>
</c:if>
                                    <form class="pt-10 pb-10" method="POST" action="premium">
                                        <div class="control-material is-primary">      
                                            <input class="material-input" type="text" name="adresse" placeholder="Adresse" required>
                                            <span class="material-highlight"></span>
                                            <span class="bar"></span>
                                            <label></label>
                                        </div>
                                        
                                        <div class="control-material is-primary">      
                                            <input class="material-input" type="text" name="zip" placeholder="ZIP Code" required>
                                            <span class="material-highlight"></span>
                                            <span class="bar"></span>
                                            <label></label>
                                        </div>
                                        <div class="control-material is-primary">      
                                            <input class="material-input" type="text" name="numcarte" required placeholder="1234 5678 9012 3456" size="12" length="12">
                                            <span class="material-highlight"></span>
                                            <span class="bar"></span>
                                            <label></label>
                                        </div>
                                        <div class="control-material is-primary">      
                                            <input class="material-input" type="text" name="ccv" required placeholder="ccv" size="3" length="3">
                                            <span class="material-highlight"></span>
                                            <span class="bar"></span>
                                            <label></label>
                                        </div>
                                        <div class="control-material is-primary">      
                                            <input class="material-input" type="text" name="expiry" required placeholder="MM/yy">
                                            <span class="material-highlight"></span>
                                            <span class="bar"></span>
                                            <label></label>
                                        </div>
                                        <div class="control-material is-primary">      
                                            <input class="material-input" type="hidden" name="id_client" value="${id_client }" required>
                                            <span class="material-highlight"></span>
                                            <span class="bar"></span>
                                            <label></label>
                                        </div>
        
                                        <div class="pt-20 pb-20">
                                            <button class="button button-cta btn-align primary-btn is-fullwidth rounded raised no-lh">PREMIUM WERDEN</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
        
                            
        
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Hero image -->
        </div>
        <!-- /Hero and nav -->
        
        
        <div id="backtotop"><a href="#"></a></div>        <!-- Concatenated jQuery and plugins -->
        <script src="assets2/js/app.js"></script>
        
        <!-- Bulkit js -->
        <script src="assets2/js/landing.js"></script>
        <script src="assets2/js/auth.js"></script>
        <script src="assets2/js/main.js"></script>    </body>  
</html>
