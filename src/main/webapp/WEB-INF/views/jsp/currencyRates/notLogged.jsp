<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
  <body>
    <jsp:include page="../comps/header.jsp" >
        <jsp:param name="active" value="index" />
    </jsp:include> 
    <div class="container">

        <div class="jumbotron">
            <h1>Welcome to Curr&nbsp;Converter</h1>
            <p><a class="btn btn-lg btn-success" href="./login">Login</span></a></p>
        </div>
        <p class="lead">
            Please login or <a href="./signIn">Sign in</a> to use this system
        </p>

    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
    <script src="${bootstrapJs}"></script>
    
    <jsp:include page="../comps/footer.jsp" /> 
</body>
</html>