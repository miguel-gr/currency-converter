<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Login - Currency Converter</title>
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
        <h2>login</h2>
        <form:form commandName="userLogin">
          <div class="form-group">
            <label for="username">User name</label>
            <form:input type="text" class="form-control" path="username" placeholder="User name"/>
            <form:errors path="username" cssClass="alert alert-danger" element="div"/>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <form:input type="password" class="form-control" path="password" placeholder="Password"/>
            <form:errors path="password" cssClass="alert alert-danger" element="div"/>
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <button type="submit" class="btn btn-success">Submit</button>
        </form:form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
    <script src="${bootstrapJs}"></script>
    
    <jsp:include page="../comps/footer.jsp" /> 
</body>
</html>