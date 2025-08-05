<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>

<div class="container my-5 d-flex flex-column align-items-center w-75">
    <div class="login-container">
        <div class="login-left text-center bg-secondary">
            <i class="bi bi-person-circle"></i>
            <h3 class="">HR Desk</h3>
        </div>

        <div class="login-right">
            <h5 class="mb-2">Login</h5>

            <form id="loginForm" method="post" action="${pageContext.request.contextPath}/auth/login">
                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                    <input type="text" id="email" name="email" class="form-control" placeholder="Email">
                </div>
                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password">
                </div>
                <button onclick="onLoginSubmit(event)" type="button" class="btn btn-secondary w-100">Login</button>
                <div class="d-flex justify-content-between mt-1">
                    <a href="${pageContext.request.contextPath}/auth/signup" class="form-link">Don't have account?</a>
                </div>
            </form>
        </div>
    </div>
    <c:if test="${not empty error}">
        <div class="mt-3 alert alert-danger w-50 text-center">
            ${error}
        </div>
    </c:if>
</div>

<%--<%@ include file="/WEB-INF/views/footer.jsp" %>--%>
