<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

        <a class="navbar-brand" href="./#/">Curr Converter</a>
      </div>

      <div class="collapse navbar-collapse" id="js-navbar-collapse">

        <ul class="nav navbar-nav">
            <c:set var="activeLink" value="${param.active}"/>
            <c:set var="userLogged" value="false"/>
            <c:if test="${not empty isLoggedIn && isLoggedIn==true}">
                <c:set var="userLogged" value="true"/>
            </c:if>
            <%-- TODO create tags for these links --%>
            <jsp:include page="headerLink.jsp" >
                <jsp:param name="active" value="${activeLink}" />
                <jsp:param name="link" value="./#/" />
                <jsp:param name="linkText" value="Home" />
                <jsp:param name="linkName" value="index" />
                <jsp:param name="display" value="true" />
            </jsp:include>
            <jsp:include page="headerLink.jsp" >
                <jsp:param name="active" value="${activeLink}" />
                <jsp:param name="link" value="./signIn" />
                <jsp:param name="linkText" value="Sign in" />
                <jsp:param name="linkName" value="signIn" />
                <jsp:param name="display" value="${!userLogged}" />
            </jsp:include>
            <jsp:include page="headerLink.jsp" >
                <jsp:param name="active" value="${activeLink}" />
                <jsp:param name="link" value="./login" />
                <jsp:param name="linkText" value="Login" />
                <jsp:param name="linkName" value="login" />
                <jsp:param name="display" value="${!userLogged}" />
            </jsp:include> 
            <jsp:include page="headerLink.jsp" >
                <jsp:param name="active" value="false" />
                <jsp:param name="link" value="./logout" />
                <jsp:param name="linkText" value="Logout" />
                <jsp:param name="linkName" value="logout" />
                <jsp:param name="display" value="${userLogged}" />
            </jsp:include>
        </ul>
      </div>
    </div>
  </div>
</div>