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

        <style>
            body {
                padding: 10px;
            }
            header {
                background-color: #333;
                color: white;
                padding: 15px;
            }
            nav a {
                color: white;
                margin-right: 15px;
                text-decoration: none;
            }
            footer {
                background-color: #f1f1f1;
                text-align: center;
                padding: 5px;
            }
            .manager-item, .location-item {
                cursor: pointer;
            }
            .manager-item:hover, .location-item:hover {
                background-color: #f8f9fa;
            }
            .modal-body {
                max-height: 400px;
                overflow-y: auto;
            }
        </style>
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