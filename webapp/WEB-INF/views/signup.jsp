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
    <div class="login-container w-75">
        <div class="login-left text-center bg-secondary">
            <i class="bi bi-person-plus-fill"></i>
            <h3 class="">Sign Up</h3>
        </div>

        <div class="login-right">
            <h5 class="mb-3">Create an Account</h5>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/auth/signup">
                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                    <input type="text" name="firstName" class="form-control" placeholder="First Name" required>
                </div>

                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                    <input type="text" name="lastName" class="form-control" placeholder="Last Name" required>
                </div>

                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-envelope-fill"></i></span>
                    <input type="email" name="email" class="form-control" placeholder="Email" required>
                </div>

                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-telephone-fill"></i></span>
                    <input type="text" name="phoneNumber" class="form-control" placeholder="Phone Number" required>
                </div>

                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                    <input type="password" name="pass" class="form-control" placeholder="Password" required>
                </div>

                <div class="mb-3 input-group">
                    <label class="input-group-text"><i class="bi bi-person-badge"></i></label>
                    <select name="role" class="form-select" required>
                        <option value="">Select Role</option>
                        <option value="ROLE_ADMIN">Admin</option>
                        <option value="ROLE_SUPER_ADMIN">Super Admin</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-secondary w-100">Sign Up</button>

                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/index.jsp" class="form-link">Already have an account? Login</a>
                </div>
            </form>
        </div>
    </div>
</div>
