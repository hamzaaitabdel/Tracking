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
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Zu genehmigenden</title>

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="css/style.css" rel="stylesheet">
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
    
     <jsp:include page="respoHeader.jsp" />
    <jsp:include page="respoAside.jsp" />

    <section id="main-content">
      <section class="wrapper">
        </div>
        <!-- row -->
        <div class="row mt">
          <div class="col-md-12">
            <div class="content-panel">
               <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i> Liste der zu genehmigenden Aufgaben</h4>
                <hr>
                <c:if test="${totalEn == 0 }">
                <center><h2>Sie sind auf dem Laufenden!</h2></center>
                <center><img src="./img/emptyty.png" alt=""></center>
                </c:if>
                <c:if test="${totalEn != 0 }">
                <tr>
                    <th><i class="fa fa-bookmark"></i> Dateinummer</th>
                    <th><i class=" fa fa-edit"></i> Libelle </th>
                    <th class="fa fa-edit"> </i>Name des Kunden</th>
                    <th><i class=" fa fa-edit"></i> Typ</th>
                    <th><i class=" fa fa-edit"></i> Dauer</th>
                    <th><i class=" fa fa-edit"></i> Startdatum </th>
                    <th><i class=" fa fa-edit"></i> Enddatum </th>
                    <th><i class=" fa fa-edit"></i> Genehmigen</th>
                    <th></th>
                  </tr>
                </thead>
                
                
                <tbody>
                  <c:forEach items="${ tache}" var="tache"> 
                  <tr>
                  <td>${ tache.id_doc}</td>
                   <td>${ tache.libelle}</td>
                      <td>${ tache.d_nom_cl}</td>
                    <td>${ tache.d_type}</td>
                    <td>${ tache.duree}</td>
					<td>${ tache.dateDebut}</td>
					<td>${ tache.dateFin}</td>
<!--                     <td><button class="btn btn-danger btn-xs"><i class="fa fa-check"></i></button></td> -->
                    <td><a href="http://localhost:8082/Tracking/approuve?id_tache=${ tache.id_tache}"><button class="btn btn-success btn-xs" ><i class="fa fa-check"></i></button></a> </td>
			</tr>
                    </c:forEach> 
                </tbody>
                </c:if>
              </table>
            </div>
            <!-- /content-panel -->
          </div>
          <!-- /col-md-12 -->
        </div>
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
