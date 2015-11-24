<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Currency Converter</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <spring:url value="/resources/core/css/main.css" var="mainCss" />
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${mainCss}" rel="stylesheet" />
  </head>
  <body ng-app="uizpApp">

    <!-- Add your site or application content here -->
    <div class="header">
      <div class="navbar navbar-default" role="navigation">
        <div class="container">
          <div class="navbar-header">

            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#js-navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand" href="#/">uizp</a>
          </div>

          <div class="collapse navbar-collapse" id="js-navbar-collapse">

            <ul class="nav navbar-nav">
              <li class="active"><a href="#/">Home</a></li>
              <li><a ng-href="#/about">About</a></li>
              <li><a ng-href="#/">Contact</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
    <div ng-view=""></div>
    </div>

    <div class="footer">
      <div class="container">
        <p>${message}</p>
      </div>
    </div>

    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/resources/core/js/angular.min.js" var="angularJs" />
    <spring:url value="/resources/core/js/angular-route.min.js" var="angularRouteJs" />
    <spring:url value="/resources/core/js/app.js" var="appJs" />
    <spring:url value="/resources/core/js/controllers/main.js" var="mainCtrlJs" />
 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${angularJs}"></script>
    <script src="${angularRouteJs}"></script>
    <script src="${appJs}"></script>
    <script src="${mainCtrlJs}"></script>
</body>
</html>

