<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<style>
    .login-container {
        max-width: 800px;
        background: #fff;
        display: flex;
        border-radius: 12px;
        overflow: hidden;
        box-shadow: 0 0 20px rgba(0,0,0,0.2);
    }

    .login-left {
        /*background: #3498db;*/
        color: white;
        width: 40%;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        padding: 30px;
    }

    .login-left i {
        font-size: 100px;
    }

    .login-right {
        background: #2c2c2c;
        color: white;
        width: 60%;
        padding: 40px;
    }

    .form-control::placeholder {
        color: #aaa;
    }

    .form-check-label {
        color: white;
    }

    .form-link {
        float: right;
        color: #ddd;
        font-size: 0.9em;
        margin-top: 8px;
    }

    .form-link:hover {
        color: #fff;
        text-decoration: underline;
    }
</style>

<div class="container my-5 d-flex justify-content-center">
    <div class="login-container">
        <div class="login-left text-center bg-secondary">
            <i class="bi bi-person-circle"></i>
            <h3 class="">HR Desk</h3>
        </div>

        <div class="login-right">
            <h5 class="mb-2">Login</h5>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/auth/login">
                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                    <input type="text" name="email" class="form-control" placeholder="Email" required>
                </div>
                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                    <input type="password" name="password" class="form-control" placeholder="Password" required>
                </div>
                <button type="submit" class="btn btn-secondary w-100">Login</button>
                <div class="d-flex justify-content-between mt-1">
                    <a href="${pageContext.request.contextPath}/auth/signup" class="form-link">Don't have account?</a>
                </div>
            </form>
        </div>
    </div>
</div>

<%--<%@ include file="/WEB-INF/views/footer.jsp" %>--%>
