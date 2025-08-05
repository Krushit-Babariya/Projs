<%--<%@ include file="/WEB-INF/views/header.jsp" %>--%>

<jsp:include page="header.jsp"></jsp:include>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css"/>
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

            <form id="signUpFormData" name="signUpFormData" method="post" action="${pageContext.request.contextPath}/auth/signup">
                <!--<form id="signUpFormData">-->
                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                    <input type="text" id="firstName" name="firstName" class="form-control" placeholder="First Name">
                </div>

                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                    <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Last Name">
                </div>

                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-envelope-fill"></i></span>
                    <input type="email" id="email" name="email" class="form-control" placeholder="Email">
                </div>

                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-telephone-fill"></i></span>
                    <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="Phone Number">
                </div>

                <div class="mb-2 input-group">
                    <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                    <input type="password" id="pass" name="pass" class="form-control" placeholder="Password">
                </div>

                <div class="mb-3 input-group">
                    <label class="input-group-text"><i class="bi bi-person-badge"></i></label>
                    <select name="role" id="role" class="form-select">
                        <option value="">Select Role</option>
                        <option value="ROLE_ADMIN">Admin</option>
                        <option value="ROLE_SUPER_ADMIN">Super Admin</option>
                    </select>
                </div>

                <button onclick="javascript:onSignUpSubmit(event)" type="button" class="btn btn-secondary w-100">Sign Up</button>

                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/index.jsp" class="form-link">Already have an account? Login</a>
                </div>
            </form>
        </div>
    </div>
</div>