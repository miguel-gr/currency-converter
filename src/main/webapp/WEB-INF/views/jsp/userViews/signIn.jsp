<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Sign in - Currency Converter</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <spring:url value="/resources/core/css/main.css" var="mainCss" />
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${mainCss}" rel="stylesheet" />
  </head>
  <body>
    <jsp:include page="../header.jsp" >
        <jsp:param name="active" value="signIn" />
    </jsp:include> 
    <div class="container">
        <h2>Sign in</h2>
        <form:form commandName="user">
          <div class="form-group">
            <label for="username">User name</label>
            <form:input type="text" class="form-control" path="username" placeholder="User name"/>
            <form:errors path="username" cssClass="alert alert-danger" element="div"/>
          </div>
          <div class="form-group">
            <label for="email">Email address</label>
            <form:input type="email" class="form-control" path="email" placeholder="Email"/>
            <form:errors path="email" cssClass="alert alert-danger" element="div"/>
          </div>
          <div class="form-group">
            <label for="dateOfBirth">Date of birth</label>
            <form:input type="text" class="form-control" path="dateOfBirth" placeholder="dd/mm/yyyy"/>
            <form:errors path="dateOfBirth" cssClass="alert alert-danger" element="div"/>
          </div>
          <div class="form-group">
            <label for="street">Street</label>
            <form:input type="text" class="form-control" path="street" placeholder="Street"/>
            <form:errors path="street" cssClass="alert alert-danger" element="div"/>
          </div>
          <div class="form-group">
            <label for="zip">Zip code</label>
            <form:input type="text" class="form-control" path="zip" placeholder="Zip code"/>
            <form:errors path="zip" cssClass="alert alert-danger" element="div"/>
          </div>
          <div class="form-group">
            <label for="city">City</label>
            <form:input type="text" class="form-control" path="city" placeholder="City"/>
            <form:errors path="city" cssClass="alert alert-danger" element="div"/>
          </div>
          <div class="form-group">
            <label for="country">Country</label>
            <form:select class="form-control" path="country">
                <option value=''>--Please select a country--</option>
                <c:forEach items="${countries}" var="entry">
                    <option value="${entry.key}">${entry.value}</option>
                </c:forEach>
            </form:select>
            <form:errors path="country" cssClass="alert alert-danger" element="div"/>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <form:input type="password" class="form-control" path="password" placeholder="Password"/>
            <form:errors path="password" cssClass="alert alert-danger" element="div"/>
          </div>
          <div class="form-group">
            <label for="passwordVer">Please type the password again</label>
            <form:input type="password" class="form-control" path="passwordVer" placeholder="Password"/>
            <form:errors path="passwordVer" cssClass="alert alert-danger" element="div"/>
          </div>
          <button type="submit" class="btn btn-default">Submit</button>
        </form:form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
    <script src="${bootstrapJs}"></script>
    
    <jsp:include page="../footer.jsp" /> 
</body>
</html>