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
        <!-- Required Meta Tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">

        <title> Details</title>
        <link rel="icon" type="image/png" href="assets3/images/favicon.png" />

        <!--Core CSS -->
        <link rel="stylesheet" href="assets3/css/bulma.css">
        <link rel="stylesheet" href="assets3/css/app.css">
        <link rel="stylesheet" href="assets3/css/core_lemonade.css">

    </head>
    <body>
        <div class="pageloader"></div>
        <div class="infraloader is-active"></div>        
        <!-- Hero and nav -->
        <div class="hero is-medium">
            
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
                            <c:if test="${session != null }">
                                 <a class="navbar-item is-slide" href="http://localhost:8080/Tracking/acceuilClient">
                                Mein Raum
                            </a>
                                </c:if>
                        </div>
            
                        <!-- Navbar end -->
                        <div class="navbar-end">
                            <!-- Signup button -->
                            <div class="navbar-item">
                            <c:if test="${session == null }">
                                <a id="#signup-btn" href="http://localhost:8080/Tracking/loginClient" class="button button-cta btn-outlined is-bold btn-align primary-btn rounded raised">
                                    Anmelden
                                </a>
                                </c:if>
                                <c:if test="${session != null }">
                                <a id="#signup-btn" href="http://localhost:8080/Tracking/deconnexion" class="button button-cta btn-outlined is-bold btn-align primary-btn rounded raised">
                                    Abmelden
                                </a>
                                </c:if></a>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
            <!-- Hero caption -->
             <div id="main-hero" class="hero-body is-relative no-padding-bottom">
                <div class="container has-text-centered">
                    <div class="columns">
                        <div class="column is-8 is-offset-2 has-text-centered">
                        <c:if test="${numTacheEnCour == maxTache }">
                            <h1 class="title big-title text-bold is-2">
                                Die Bearbeitung Ihrer Datei ist komplett.
                            </h1>
                            <h2 class="subtitle light-subtitle">
                            <c:if test="${prem == false }">
                                Sie können Ihre Datei jederzeit abholen.
                                </c:if>
                            <c:if test="${prem == true }">
                                Sie sind ein PREMIUM-Kunde, Ihre Datei wird an die registrierte Adresse geliefert, Sie werden auch durch eine E-Mail benachrichtigt.
                                </c:if>
                            </h2>
                        </c:if>
                        <c:if test="${numTacheEnCour != maxTache }">
                            <h1 class="title big-title text-bold is-2"> 
                                Ihre Datei wird bearbeitet
                            </h1>
                            <h2 class="subtitle light-subtitle">
                            <c:if test="${prem == false }">
                                Wir werden Sie informieren, sobald die Bearbeitung Ihres Datei abgeschlossen ist. 
                                </c:if>
                               <c:if test="${prem == true }">
                               Sie sind ein PREMIUM-Kunde, Ihre Datei wird nach Abschluss der Bearbeitung an die registrierte Adresse geliefert.
                                </c:if> 
                            </h2>
                        </c:if>
                        
                                <c:if test="${session != null }">
                                <c:if test="${saved == false }">
                               
                           <center> <a  href="http://localhost:8080/Tracking/addSaved?id_doc=${id_doc }&id_client=${id_client }"  ><button type="button" class="btn btn-info btn-flat btn-addon m-b-10 m-l-5"><i class="ti-plus"></i>Speichern</button></a>
                            </center>
                                </c:if> 
                                 </c:if> 
                             </div>
                       
                    
                </div>
            </div>
            <!-- /Hero caption -->
        </div>
        <!-- /Hero and nav -->
        
<!--         nichts ironi -->

        <!-- Pricing tables -->
        <div id="pricing-plans" class="section">
            <div class="container">
                <div class="columns is-vcentered mt-30">
                    <div class="column">
                        <!-- Pricing wrapper -->
                        <div class="pricing-wrapper">
                            <div class="flex-card light-bordered light-raised">
                                    <!-- Plan -->
                                        <div class="condensed-plan">
                                       <jsp:include page="listDetails.jsp" />
                                                 
                                </div>
                            <img class="hr-bg" src="assets3/images/illustrations/mockups/landing3/hrbg.png" alt="">
                        </div>
                        <!-- /Pricing wrapper -->
        
                        <!-- CTA -->
                       
                    </div>
                </div>
            </div>
        
        <!-- /Pricing tables -->
        
         <div class="has-text-centered pt-80">
                            <a href="http://localhost:8080/Tracking/progression?tracking=${id_doc }" class="button button-cta primary-btn rounded raised">Allgemeine Ansicht</a>
                        </div>
                        </div>
                        
        <section class="section is-medium section-feature-grey">
            <c:if test="${session == null }">
        <div class="container">
                <h2 class="title section-subtitle dark-text text-bold has-text-centered is-2 pt-20 pb-20">
                   Haben Sie kein Konto?
                </h2>
        
        
                <div class="content-wrapper">
                    
                    <div class="columns">
                    <div class="column is-4 is-offset-2">
                            <div class="content">
    				 <p style="float: left;"><img src="img/inscrivez.png"></p></div>
   				 </div>
  				  <div class="column is-4">
                            <div class="content">
                                <p class="text-bold dark-text">Melden Sie sich kostenlos an und genießen Sie unsere außergewöhnlichen Dienstleistungen wie z.B:</p>
                                <p class="text-bold dark-text">1. Zugriff auf Ihre Dateien von Ihrem persönlichen Bereich aus </p>
								<p> Sobald Sie sich identifiziert haben, können Sie auf Ihren persönlichen Bereich zugreifen, in dem Ihre Dateien automatisch gespeichert werden, wodurch es einfacher denn je wird, den Überblick zu behalten..</p>
                             </div>
                            <div class="content">
                                <p class="text-bold dark-text">2. Dateien sichern</p>
                                <p>Sie können jeden beliebigen Datein speichern und von Ihrem persönlichen Bereich aus darauf zugreifen. </p>
                            </div>
                            </div></div>
        <div class="has-text-centered pt-80">
                            <a href="http://localhost:8080/Tracking/registration?id_doc=${id_doc }" class="button button-cta primary-btn rounded raised">Jetzt anmelden</a>
                        </div></div> </div>
                     </c:if>
        <c:if test="${session != null }">
        <c:if test="${prem == false }">
        <div class="container">
                <h2 class="title section-subtitle dark-text text-bold has-text-centered is-2 pt-20 pb-20">
                   Was ist ein PREMIUM-Pack?
                </h2>
        
                <div class="content-wrapper">
                    
                    <div class="columns">
                    <div class="column is-4 is-offset-2">
                            <div class="content">
    				 <p style="float: left;"><img src="img/premium1.PNG"  border="1px"></p></div>
   				 </div>
  				  <div class="column is-4">
                            <div class="content">
                            <p class="text-bold dark-text">Werden Sie PREMIUM und genießen Sie unglaubliche Leistungen zu einem vernünftigen Preis.</p>
                                <p class="text-bold dark-text">1. Lieferung nach Hause</p>
                                <p>Ihre Dateien werden nach Abschluss der Bearbeitung an die registrierte Adresse geliefert. Die Lieferung wird nicht länger als 2 Werktage dauern.</p>
                            </div>
                            <div class="content">
                                <p class="text-bold dark-text">2. Verarbeitungspriorität</p>
								<p> Sobald Sie PREMIUM geworden sind, werden Ihre Dateien bei der Bearbeitung vorrangig behandelt, was Ihnen viel Zeit erspart.</p>
                             </div>
                            <div class="content">
                                <p class="text-bold dark-text">3. E-Mail-Benachrichtigung</p>
                                <p>Sie werden per E-Mail benachrichtigt, sobald die Bearbeitung Ihrer Datei vollständig ist</p>
                            </div>
                            </div></div>
        <div class="has-text-centered pt-80">
                            <a href="http://localhost:8080/Tracking/premium?id_client=${id_client }" class="button button-cta primary-btn rounded raised">PREMIUM WERDEN</a>
                        </div></div> </div>
                        </c:if>
                         
                     </c:if>
                     
            <br><br>
            <div class="container">
                <h2 class="title section-subtitle dark-text text-bold has-text-centered is-2 pt-20 pb-20">
                  Haben Sie Fragen?
                </h2>
        
                <div class="content-wrapper">
                    <div class="columns">
                        <div class="column is-4 is-offset-2">
                            <div class="content">
                                <p class="text-bold dark-text">1. Meine Datei ist "Nicht zurückverfolgbar", was kann ich tun?</p>
                                <p>"Nicht zurückverfolgbar" bedeutet, dass wir keine Informationen über diese Tracking-Nummer haben. Wir empfehlen Ihnen, Ihre Tracking-Nummerzu überprüfen.</p>
                            </div>
                            <div class="content">
                                <p class="text-bold dark-text">3. Meine Datei steckt irgendwo fest, was kann ich tun?</p>
                                <p> Wenn wir neue Verfolgungsinformationen haben, aktualisieren wir sie.</p>
                            </div>
                            <div class="content">
                                <p class="text-bold dark-text">5. Können Sie mir mitteilen, wann meine Datei fertig ist?</p>
                                <p> Die Antwort ist ja, wir versenden in mehreren Fällen Benachrichtigungs-E-Mails, dies ist einer dieser Fälle.</p>
                            </div>
                        </div>
                        <div class="column is-4">
                            <div class="content">
                                <p class="text-bold dark-text">2. (PREMIUM) Welche Spediteure betreuen Sie?</p>
								<p> Im Moment kümmern wir uns nur um "Amana", den besten Spediteur in Marokko und den einzigen, der die Lieferung am selben Tag anbietet. "same day delivery"</p>
                             </div>
                            <div class="content">
                                <p class="text-bold dark-text">4. Wann genau bekomme ich meine Datei?</p>
                                <p> Glücklicherweise gibt es für jeden Fall eine geschätzte Dauer. Die Gesamtzeit hängt von der Anzahl der Aufgaben in jedem Detei ab. </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /FAQ -->
        
            <!-- /Navigation menu --></div>
        <!-- /Side navigation -->        <!-- Back To Top Button -->
        <div id="backtotop"><a href="#"></a></div>        <!-- Concatenated jQuery and plugins -->
        <script src="assets3/js/app.js"></script>
        
        <!-- Bulkit js -->
        <script src="assets3/js/components-modals.js"></script>
        <script src="assets3/js/landingv3.js"></script>
        <script src="assets3/js/auth.js"></script>
        <script src="assets3/js/main.js"></script>    </body>  
</html>

        <!-- /Fancy Pricing tables -->
        
     