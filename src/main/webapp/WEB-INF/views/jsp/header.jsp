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

        <a class="navbar-brand" href=".">Curr Converter</a>
      </div>

      <div class="collapse navbar-collapse" id="js-navbar-collapse">

        <ul class="nav navbar-nav">
            <c:choose>
                <c:when test="${param.active=='index'}">
                    <li class="active"><a href=".">Home</a></li>
                </c:when>    
                <c:otherwise>
                    <li ><a href=".">Home</a></li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${param.active=='signIn'}">
                    <li class="active"><a href="./signIn">Sign in</a></li>
                </c:when>    
                <c:otherwise>
                    <li ><a href="./signIn">Sign in</a></li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${param.active=='login'}">
                    <li class="active"><a href="./logIn">Login</a></li>
                </c:when>    
                <c:otherwise>
                    <li ><a href="./logIn">Login</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
      </div>
    </div>
  </div>
</div>