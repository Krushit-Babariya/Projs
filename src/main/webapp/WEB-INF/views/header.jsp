<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Employee Management System</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/signup.js"></script>
    </head>
    <body>
        <header>
            <div style="display: flex; align-items: center; justify-content: center; gap: 20px;">
                <h2>Employee Management System</h2>

                <c:if test="${not empty sessionScope.currUser}">
                    <form action="${pageContext.request.contextPath}/auth/logout" method="get">
                        <button class="btn btn-danger">Logout</button>
                    </form>
                </c:if>
            </div>
        </header>
        <hr>