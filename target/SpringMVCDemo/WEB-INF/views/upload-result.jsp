<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<c:if test="${not empty uploadResult}">
    <ul>
        <c:forEach var="msg" items="${uploadResult}">
            <li style="color:red">${msg}</li>
            </c:forEach>
    </ul>
</c:if>

<a href="${pageContext.request.contextPath}/api/employees" class="btn btn-secondary w-100">Back</a>

