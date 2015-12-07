<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${param.display=='true'}">
    <c:choose>
        <c:when test="${param.active==param.linkName}">
            <li class="active"><a href="${param.link}">${param.linkText}</a></li>
        </c:when>    
        <c:otherwise>
            <li ><a href="${param.link}">${param.linkText}</a></li>
        </c:otherwise>
    </c:choose>
</c:if>