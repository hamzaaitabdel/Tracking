<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ page import="entities.Dossier" %>
        <%@ page import="entities.Responsable" %>
        <%@ page import="entities.Tache" %> 
    <%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="web.TrackingServlet"%>
<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="codepixer">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	<!-- Site Title -->
	<title>Acceuil</title>

	<!--
			Google Font
			============================================= -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,500,600" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500i" rel="stylesheet">

	<!--
			CSS
			============================================= -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css2/themify-icons.css">
	<link rel="stylesheet" href="css2/linearicons.css">
	<link rel="stylesheet" href="css2/font-awesome.min.css">
	<link rel="stylesheet" href="css2/bootstrap.css">
	<link rel="stylesheet" href="css2/magnific-popup.css">
	<link rel="stylesheet" href="css2/nice-select.css">
	<link rel="stylesheet" href="css2/animate.min.css">
	<link rel="stylesheet" href="css2/owl.carousel.css">
	<link rel="stylesheet" href="css2/main.css">
</head>

<body>


	<!-- End Header Area -->


	<!-- Start Banner Area -->
	 <jsp:include page="accueil.jsp" />


	<!-- Start Courses Area -->
	<section class="courses-area section-gap">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-5 about-right">
					<h1>
				 Wie funktioniert WEBTRACK?<br>
					</h1>
					<div class="wow fadeIn" data-wow-duration="1s">
						<p>
						Nachdem Sie Ihre Tracking-Nummer eingegeben und auf "Verfolgen" geklickt haben, wird unser System automatisch 
						die Versandinformationen aus unserer internen Quelle für Sie sammeln und Ihnen auf einfache Weise präsentieren, 
						und intuitiv. Dadurch entfällt der ständige Gang ins Büro.</p>
					</div>
					<h1>
						Wer sind wir?<br>
					</h1>
					<div class="wow fadeIn" data-wow-duration="1s">
						<p>
						Wir bieten den besten ALL-IN-ONE-Tracking-Service! 
							Auch wenn WEBTRACK kostenlos ist, bemühen wir uns, Ihnen die bestmögliche Benutzererfahrung zu bieten. 
							professionell, um Sie zufrieden zu stellen.
						</p>
					</div>
				</div>
				<div class="offset-lg-1 col-lg-6">
					<div class="courses-right">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-12">
								<ul class="courses-list">
									<li>
										<a class="wow fadeInLeft"   data-wow-duration="1s" data-wow-delay=".1s">
											<i class="fa fa-book"></i>Kostenlos
										</a>
									</li>
									<li>
										<a class="wow fadeInLeft"   data-wow-duration="1s" data-wow-delay=".3s">
											<i class="fa fa-book"></i>Einfachheit
										</a>
									</li>
									<li>
										<a class="wow fadeInLeft"   data-wow-duration="1s" data-wow-delay=".5s">
											<i class="fa fa-book"></i>Geschwindigkeit
										</a>
									</li>
									<li>
										<a class="wow fadeInLeft"   data-wow-duration="1s" data-wow-delay=".5s">
											<i class="fa fa-book"></i>Skalierbarkeit
										</a>
									</li>
								</ul>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12">
								<ul class="courses-list">
									<li>
										<a class="wow fadeInRight"   data-wow-duration="1s" data-wow-delay="1.3s">
											<i class="fa fa-book"></i>Genauigkeit
										</a>
									</li>
									<li>
										<a class="wow fadeInLeft"   data-wow-duration="1s" data-wow-delay=".5s">
											<i class="fa fa-book"></i>Effizienz
										</a>
									</li>
									<li>
										<a class="wow fadeInRight"   data-wow-duration="1s" data-wow-delay="1.1s">
											<i class="fa fa-book"></i>Ergonomie
										</a>
									</li>
									<li>
										<a class="wow fadeInRight"   data-wow-duration="1s" data-wow-delay=".9s">
										</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section> 

	<!-- ####################### Start Scroll to Top Area ####################### -->
	 
	<script src="js/vendor/jquery-2.2.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	 crossorigin="anonymous"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
	<script src="js/easing.min.js"></script>
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/owl-carousel-thumb.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/parallax.min.js"></script>
	<script src="js/waypoints.min.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/jquery.counterup.min.js"></script>
	<script src="js/mail-script.js"></script>
	<script src="js/main.js"></script>
</body>

</html>