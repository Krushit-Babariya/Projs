<%@ include file="header.jsp" %>
<!--
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>-->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/employees.js"></script>

<div class="container mt-4">
    <div class="d-flex justify-content-between mb-3">
        <div>
            <h2>Employee Report</h2>
        </div>
        <div>
            <a class="btn btn-primary me-2" onclick="bootstrap.Modal.getOrCreateInstance(document.getElementById('uploadBox')).show();">
                <i class="bi bi-file-earmark-excel"></i> Upload Excel Report
            </a>
            <a href="report?type=pdf" target="_blank" class="btn btn-danger me-2">
                <i class="bi bi-file-earmark-pdf"></i> Download PDF Report
            </a>
            <a href="report?type=excel" target="_blank" class="btn btn-success">
                <i class="bi bi-file-earmark-excel"></i> Download Excel Report
            </a>
        </div>
    </div>
    <div class="d-flex gap-2 mb-3">
        <select id="sortBy" class="form-select w-auto">
            <option value="employee_id">Employee ID</option>
            <option value="first_name">First Name</option>
            <option value="last_name">Last Name</option>
            <option value="email">Email</option>
            <option value="hire_date">Hire Date</option>
            <option value="salary">Salary</option>
        </select>

        <select id="sortDir" class="form-select w-auto">
            <option value="asc">Ascending</option>
            <option value="desc">Descending</option>
        </select>

        <button type="button" class="btn btn-primary" onclick="fetchSortedEmployees()">Sort</button>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-striped text-center">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Hire Date</th>
                    <th>Job ID</th>
                    <th>Salary</th>
                    <th>Commission %</th>
                    <th>Manager ID</th>
                    <th>Department ID</th>
                    <th>Action</th>
                    <th>Document</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="emp" items="${employees}">
                    <tr>
                        <td>${emp.employeeId}</td>
                        <td>${emp.firstName}</td>
                        <td>${emp.lastName}</td>
                        <td>${emp.email}</td>
                        <td>${emp.phoneNumber}</td>
                        <td>${emp.hireDate}</td>
                        <td>${emp.jobId}</td>
                        <td>${emp.salary}</td>
                        <td>${emp.commissionPct}</td>
                        <td>${emp.managerId}</td>
                        <td>${emp.departmentId}</td>
                        <td class="d-flex gap-2">
                            <a href="${pageContext.request.contextPath}/api/employees/update?employeeId=${emp.employeeId}" class="btn btn-sm btn-secondary">Edit</a>
                            <button class="btn btn-sm btn-secondary" onclick="openDeleteModal(${emp.employeeId})">Delete</button>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/employee-docs-api/?employeeId=${emp.employeeId}" class="btn btn-sm btn-secondary">View</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="d-flex justify-content-center space-between mb-4">
    <a href="${pageContext.request.contextPath}/api/addEmployee" class="btn btn-secondary w-25 mx-2">Add Employee</a>
    <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary w-25">Home</a>
</div>

<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form:form id="editForm" method="post" action="${pageContext.request.contextPath}/api/employees/update" modelAttribute="employee">
                <div class="modal-header">
                    <h5 class="modal-title">Edit Employee</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <form:hidden path="employeeId"/>
                    <div class="mb-2">First Name: <form:input path="firstName" cssClass="form-control"/></div>
                    <div class="mb-2">Last Name: <form:input path="lastName" cssClass="form-control"/></div>
                    <div class="mb-2">Email: <form:input path="email" cssClass="form-control"/></div>
                    <div class="mb-2">Phone: <form:input path="phoneNumber" cssClass="form-control"/></div>
                    <div class="mb-2">Hire Date: <form:input path="hireDate" type="date" cssClass="form-control"/></div>
                    <div class="mb-2">Job ID: <form:input path="jobId" cssClass="form-control"/></div>
                    <div class="mb-2">Salary: <form:input path="salary" type="number" cssClass="form-control"/></div>
                    <div class="mb-2">Commission %: <form:input path="commissionPct" cssClass="form-control"/></div>
                    <div class="mb-2">Manager ID: <form:input path="managerId" cssClass="form-control"/></div>
                    <div class="mb-2">Department ID: <form:input path="departmentId" cssClass="form-control"/></div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Save</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/api/deleteEmployee">
                <div class="modal-header">
                    <h5 class="modal-title">Delete Employee</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="employeeId" id="delete_employeeId"/>
                    <p>Are you sure you want to delete this employee?</p>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger">Yes, Delete</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="uploadBox" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="upload" enctype="multipart/form-data">
                <div class="modal-header">
                    <h5 class="modal-title">Upload Excel</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <input type="file" name="file" accept=".xlsx" required class="form-control" />
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Upload</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>