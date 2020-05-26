<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ page import="entities.Dossier" %>
        <%@ page import="entities.Responsable" %>
        <%@ page import="entities.Tache" %>
        <%@ page import="entities.Messenger" %>
    
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
      <section class="wrapper site-min-height">
        <!-- page start-->
        <div class="chat-room mt">
          <aside class="mid-side">
            <div class="chat-room-head">
              <h3>Chat-Raum: Dossier ${ idD} </h3>
              
            </div>
            
             <c:forEach items="${ messenger}" var="messenger"> 
            <div class="group-rom">
             <c:if test="${ messenger.id_respo == id}">
              <div class="first-part odd">${ messenger.nom_respo}</div>
              <div class="second-part"><a href="http://localhost:8080/Tracking/deleteMsgDbl3ani?id_mess=${ messenger.id_mess}">${ messenger.message}</a></div>
              </c:if>
               <c:if test="${ messenger.id_respo != id}">
               <div class="first-part">${ messenger.nom_respo}</div>
              <div class="second-part">${ messenger.message}</div>
               </c:if>
              <div class="third-part">${ messenger.dat}</div>
            </div>
            </c:forEach>
            <div class="group-rom">
               <div class="first-part"></div>
              <div class="second-part">Chat-Raum</div>
              <div class="third-part"></div>
            </div>
            <footer>
            <form action="messenger" method="post" "pull-right position">
              <div class="chat-txt">
                <input type="text" name="msg" class="form-control">
              </div>
              <div class="btn-group hidden-sm hidden-xs">
                <button type="button" class="btn btn-white"><i ></i></button>
                <button type="button" class="btn btn-white"><i ></i></button>
                 <input class=" form-control" name="id_respo" type="hidden" value="${ id}" />
                 <input class=" form-control" name="id_doc" type="hidden" value="${ idD}" />
              </div>
              
              <button class="btn btn-theme" name="search" >Senden an</button>
                </form>
            </footer>
          </aside>
          <aside class="right-side">
            <div class="user-head">
            </div>
            <div class="invite-row">
              <h4 class="pull-left">Verantwortliche Personen</h4>
            </div>
           
            <c:forEach items="${ respo}" var="respo"> 
             <ul class="chat-available-user">
              <li>
                  <img class="img-circle" src="${ respo.photo}" width="32">
                  ${ respo.nom} 
              </li>
              </ul>
              </c:forEach>
          </aside>
        </div>
        <!-- page end-->
      </section>
      <!-- /wrapper -->
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
