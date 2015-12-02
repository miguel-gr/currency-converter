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

    <jsp:include page="header.jsp" >
        <jsp:param name="active" value="index" />
    </jsp:include>

    <div class="container">
    <div ng-view=""></div>
    </div>

    <jsp:include page="footer.jsp" /> 

    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/resources/core/js/angular.min.js" var="angularJs" />
    <spring:url value="/resources/core/js/angular-route.min.js" var="angularRouteJs" />
    <spring:url value="/resources/core/js/app.js" var="appJs" />
    <spring:url value="/resources/core/js/controllers/main.js" var="mainCtrlJs" />
    <spring:url value="/resources/core/js/controllers/signIn.js" var="signInCtrlJs" />
 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${angularJs}"></script>
    <script src="${angularRouteJs}"></script>
    <script src="${appJs}"></script>
    <script src="${mainCtrlJs}"></script>
    <script src="${signInCtrlJs}"></script>
    
</body>
</html>

