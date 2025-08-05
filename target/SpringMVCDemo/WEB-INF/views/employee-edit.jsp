<%@ include file="header.jsp" %>

<div class="container mt-4">
    <h2 class="text-center mb-4">Edit Employee</h2>

    <form:form method="post" action="${pageContext.request.contextPath}/api/employees/update" modelAttribute="employee">
        <form:hidden path="employeeId"/>

        <div class="row">
            <div class="col-md-6 mb-3">
                First Name:
                <form:input path="firstName" cssClass="form-control"/>
                <form:errors path="firstName" cssClass="text-danger"/>
            </div>

            <div class="col-md-6 mb-3">
                Last Name:
                <form:input path="lastName" cssClass="form-control"/>
                <form:errors path="lastName" cssClass="text-danger"/>
            </div>

            <div class="col-md-6 mb-3">
                Email:
                <form:input path="email" cssClass="form-control"/>
                <form:errors path="email" cssClass="text-danger"/>
            </div>

            <div class="col-md-6 mb-3">
                Phone Number:
                <form:input path="phoneNumber" cssClass="form-control"/>
                <form:errors path="phoneNumber" cssClass="text-danger"/>
            </div>

            <div class="col-md-6 mb-3">
                Hire Date:
                <form:input path="hireDate" type="date" cssClass="form-control"/>
                <form:errors path="hireDate" cssClass="text-danger"/>
            </div>

            <div class="col-md-6 mb-3">
                Job ID:
                <form:input path="jobId" cssClass="form-control"/>
                <form:errors path="jobId" cssClass="text-danger"/>
            </div>

            <div class="col-md-6 mb-3">
                Salary:
                <form:input path="salary" type="number" cssClass="form-control"/>
                <form:errors path="salary" cssClass="text-danger"/>
            </div>

            <div class="col-md-6 mb-3">
                Commission %:
                <form:input path="commissionPct" cssClass="form-control"/>
                <form:errors path="commissionPct" cssClass="text-danger"/>
            </div>

            <div class="col-md-6 mb-3">
                Manager ID:
                <form:input path="managerId" cssClass="form-control"/>
                <form:errors path="managerId" cssClass="text-danger"/>
            </div>

            <div class="col-md-6 mb-3">
                Department ID:
                <form:input path="departmentId" cssClass="form-control"/>
                <form:errors path="departmentId" cssClass="text-danger"/>
            </div>
        </div>

        <div class="d-flex justify-content-between mt-4">
            <button type="submit" class="btn btn-primary w-50 me-2">Update</button>
            <a href="${pageContext.request.contextPath}/api/employees" class="btn btn-secondary w-50">Cancel</a>
        </div>
    </form:form>
</div>

<%@ include file="footer.jsp" %>
