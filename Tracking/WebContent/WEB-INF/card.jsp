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
 
        <link rel="stylesheet" href="card/styles/skeuocard.reset.css" />
    <link rel="stylesheet" href="card/styles/skeuocard.css" />
    <link rel="stylesheet" href="card/styles/demo.css">
    <script src="card/javascripts/vendor/cssua.min.js"></script>
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
                                <!-- debut -->
                                
                                <div class="wrapper">
      
        <!-- START FORM -->
        
          <div class="signup-card card-block auth-body mr-auto ml-auto">
          <div class="credit-card-input no-js" id="skeuocard"style="left: 800px;">
            <p class="no-support-warning">
              Either you have Javascript disabled, or you're using an unsupported browser, amigo! That's why you're seeing this old-school credit card input form instead of a fancy new Skeuocard. On the other hand, at least you know it gracefully degrades...
            </p>
            <label for="cc_type">Card Type</label>
            <select name="cc_type">
              <option value="">...</option>
              <option value="visa">Visa</option>
              <option value="discover">Discover</option>
              <option value="mastercard">MasterCard</option>
              <option value="maestro">Maestro</option>
              <option value="jcb">JCB</option>
              <option value="unionpay">China UnionPay</option>
              <option value="amex">American Express</option>
              <option value="dinersclubintl">Diners Club</option>
            </select>
            <label for="cc_number">Card Number</label>
            <input type="text" name="cc_number" id="cc_number" placeholder="XXXX XXXX XXXX XXXX" maxlength="19" size="19">
            <label for="cc_exp_month">Expiration Month</label>
            <input type="text" name="cc_exp_month" id="cc_exp_month" placeholder="00">
            <label for="cc_exp_year">Expiration Year</label>
            <input type="text" name="cc_exp_year" id="cc_exp_year" placeholder="00">
            <label for="cc_name">Cardholder's Name</label>
            <input type="text" name="cc_name" id="cc_name" placeholder="John Doe">
            <label for="cc_cvc">Card Validation Code</label>
            <input type="text" name="cc_cvc" id="cc_cvc" placeholder="123" maxlength="3" size="3">
          </div>
          </div>

        <!-- END FORM -->
      


    <script src="card/javascripts/vendor/demo.fix.js"></script>
    <script src="card/javascripts/vendor/jquery-2.0.3.min.js"></script>
    <script src="card/javascripts/skeuocard.js"></script>
    <script>

      $(document).ready(function(){
        var isBrowserCompatible = 
          $('html').hasClass('ua-ie-10') ||
          $('html').hasClass('ua-webkit') ||
          $('html').hasClass('ua-firefox') ||
          $('html').hasClass('ua-opera') ||
          $('html').hasClass('ua-chrome');

        if(isBrowserCompatible){
          window.card = new Skeuocard($("#skeuocard"), {
            debug: false
          });
        }
      });

    </script>
    
                                <!-- fin -->
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
