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
  <!-- Custom styles for this template -->
  <link href="css/style1.css" rel="stylesheet">
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
    <!--header end-->
    <!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
    <aside>
      <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
          <p class="centered">
            <a ><img src="${admin.photo }" class="img-circle" width="100"></a>
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
                <a href="http://localhost:8082/Tracking/ajoutDoc">Einen Ordner hinzuf�gen</a>
              </li>
              <li>
                <a href="http://localhost:8082/Tracking/ajoutRespo">Einen Manager hinzuf�gen</a>
              </li>
              <li>
                <a href="http://localhost:8082/Tracking/ajoutTache">Verwaltung der Aufgaben</a>
              </li> 
            </ul>
          </li> 
           
        </ul>
        <!-- sidebar menu end-->
      </div>
    </aside>
    <!--sidebar end-->
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
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
            <div class="row mt">
          <div class="col-lg-12">
            <h4><i class="fa fa-angle-right"></i>Einen Fleck hinzuf�gen</h4>
            <div class="form-panel">
              <div class=" form">
                <form class="cmxform form-horizontal style-form" method="post" action="ajoutTache">
                  
                  
                  <div class="form-group ">
                    <label for="curl" class="control-label col-lg-2">Nummer des Managers</label>
                    <div class="col-lg-10">
                      <select  name="id_respo" type="text" class="form-control" required>
   						<option val="" disabled>Manager:</option>
   						<c:forEach items="${ responsable}" var="responsable">
    				    <option val="${ responsable.id_respo}">${ responsable.id_respo} (${ responsable.nom})</option>
						</c:forEach>
   					 </select>
                    </div>
                  </div> 
                  <c:if test="${id_doc == null }">
                  <div class="form-group ">
                    <label for="curl" class="control-label col-lg-2">Dateinummer</label>
                    <div class="col-lg-10">
                      <select  name="id_doc" type="text" class="form-control" required>
   						<option val="" disabled>Dateinummer:</option>
   						<c:forEach items="${ dossier}" var="dossier">
    				    <option val="${ dossier.id_doc}">${ dossier.id_doc} (${ dossier.type})</option>
						</c:forEach>
   					 </select>
                    </div>
                  </div> 
                  </c:if>
                  <c:if test="${id_doc != null }">
                  <div class="form-group ">
                    <label for="curl" class="control-label col-lg-2">Dateinummer</label>
                    <div class="col-lg-10">
                      <input class="form-control " type="text" name="id_doc" value="${id_doc }" readonly />
                    </div>
                  </div> 
                  </c:if>
                  <div class="form-group ">
                    <label for="curl" class="control-label col-lg-2">Types</label>
                  <div class="col-lg-10">
                      <select  name="type" type="text" class="form-control" required>
   						<option val="" disabled>types:</option>
   						<c:forEach items="${ types}" var="type">
    				    <option val="${ type.id}">${ type.type}</option>
						</c:forEach>
   					 </select>
                    </div>
                    </div>
                   <div class="form-group">
                  <label class="control-label col-md-3">Zeitraum</label>
                  <div class="col-md-4">
                    <div class="input-group input-large" data-date="01/01/2020" data-date-format="dd/MM/yyyy">
                    <span class="input-group-addon">von</span>
                      <input type="date" class="form-control dpd1" name="dateDebut" required>
                      <span class="input-group-addon">bis</span>
                      <input type="date" class="form-control dpd2" name="dateFin" required>
                    </div>
                    <span class="help-block">W�hlen Sie einen Zeitraum aus.</span>
                  </div>
                </div>
                  <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                      <button class="btn btn-theme" type="submit">Speichern</button>
                     <a  href="http://localhost:8082/Tracking/statistics"><button class="btn btn-theme04" type="button">Abbrechen</button></a>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            <!-- /form-panel -->
          </div>
          <!-- /col-lg-12 -->
        </div>
            <!-- /content-panel -->
          </div>
          <!-- /col-md-12 -->
        </div>
        <!-- row -->
       
        <!-- /row -->
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
