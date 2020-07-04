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
  <title>Responsable</title>

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
            <div class="content-panel">
              <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i> Liste der Unteraufgabe / Bemerkungen / Motive -- Klicken Sie zum L�schen </h4>
                <hr>
                <c:if test="${total == 0 }">
                <center><h2>Dieser Fleck hat im Moment keine Unteraufgabe!!</h2></center>
                <center><img src="./img/emptyty.png" alt=""></center>
                </c:if>
                <c:if test="${total != 0 }">
                <thead>
                  <tr>
                  <th><i  ></i> #</th>
                    <th><i class=" fa fa-edit"></i> Unteraufgabe(n) / Bemerkung(en) / Motiv(e)</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                <%! int i = 0; %>
                  <c:forEach items="${ tache}" var="tache"> 
                  <% i++; %>
                  <tr>
                  <td><%= i  %></td>
                   <td><a href="http://localhost:8082/Tracking/deleteSTdbsh?idST=${ tache.id_ST}">${ tache.libelle}</a></td>
                    </tr>
                    
                    </c:forEach> 
                    <% i=0; %>
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
