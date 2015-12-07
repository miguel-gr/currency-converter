<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

        <div class="docsection">
            <c:if test="${not empty conversion}">
                <div class="conversion">
                    <span class="from">${conversion.originalString}</span> = 
                    <span class="to">${conversion.convertedString}</span>
                </div>
            </c:if>
            <h2>New query</h2>
            <div>
                <form:form commandName="currencyConversionForm" cssClass="form-inline">
                    <div class="form-group">
                        <label for="originalValue">From</label>
                        <form:input type="text" class="form-control" path="originalValue" placeholder="Amount" size="10"/>
                        <form:select class="form-control" path="originalCode">
                            <option value=''>--Currency--</option>
                            <c:forEach items="${currencies}" var="entry">
                                <option value="${entry.key}" ${entry.key eq currencyConversionForm.originalCode ? 'selected' : ''}>${entry.value}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <label for="convertedCode">To</label>
                        <form:select class="form-control" path="convertedCode">
                            <option value=''>--Currency--</option>
                            <c:forEach items="${currencies}" var="entry">
                                <option value="${entry.key}" ${entry.key eq currencyConversionForm.convertedCode ? 'selected' : ''}>${entry.value}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <button type="submit" class="btn btn-success">Convert</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <form:errors path="originalValue" cssClass="alert alert-danger" element="div"/>
                    <form:errors path="originalCode" cssClass="alert alert-danger" element="div"/>
                    <form:errors path="convertedCode" cssClass="alert alert-danger" element="div"/>
                </form:form>
            </div>
        </div>

        <div class="docsection">
            <h2>Your recent queries</h2>
            <table class="recentConversions">
                <c:forEach items="${recentConversions}" var="recConversion">
                    <tr>
                        <td class="from">${recConversion.originalString}</td>
                        <td>=</td>
                        <td class="to">${recConversion.convertedString}</td>  
                        <td><fmt:formatDate value="${recConversion.conversionDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
    <script src="${bootstrapJs}"></script>
    
    <jsp:include page="../comps/footer.jsp" /> 
</body>
</html>