<%@ include file="header.jsp" %>

<!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>-->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/departments.js"></script>

<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">${errorMessage}</div>
</c:if>

<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3>Department List</h3>
        <div>
            <button class="btn btn-secondary" onclick="openAddDepartmentDialog()">Add Department</button>
            <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Home</a>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered text-center">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Manager</th>
                    <th>Location</th>
                    <th>Country</th>
                    <th>Region</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dept" items="${departments}">
                    <tr>
                        <td>${dept.departmentId}</td>
                        <td>${dept.departmentName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${empty dept.managerFullName}">
                                    <button class="btn btn-sm btn-warning" onclick="openManagerDialog(${dept.departmentId})">Add Manager</button>
                                </c:when>
                                <c:otherwise>${dept.managerFullName}</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${empty dept.streetAddress}">
                                    <button class="btn btn-sm btn-info" onclick="openLocationDialog(${dept.departmentId})">Add Location</button>
                                </c:when>
                                <c:otherwise>${dept.streetAddress}, ${dept.city}</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${dept.countryName}</td>
                        <td>${dept.regionName}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/departments/delete/${dept.departmentId}" method="post" onsubmit="return confirmDelete();">
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="modal fade" id="managerModal" tabindex="-1">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header"><h5 class="modal-title">Select a Manager</h5><button type="button" class="btn-close" data-bs-dismiss="modal"></button></div>
            <div class="modal-body">
                <input type="text" id="managerSearch" class="form-control mb-2" placeholder="Search..." onkeyup="filterList('managerSearch', 'manager-item')" />
                <ul class="list-group">
                    <c:forEach var="emp" items="${employees}">
                        <li class="list-group-item manager-item" onclick="selectManager(${emp.employeeId}, this)" style="cursor:pointer;">
                            ${emp.firstName} ${emp.lastName}
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" onclick="confirmManager()">Assign</button>
                <button class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="locationModal" tabindex="-1">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header"><h5 class="modal-title">Select a Location</h5><button type="button" class="btn-close" data-bs-dismiss="modal"></button></div>
            <div class="modal-body">
                <input type="text" id="locationSearch" class="form-control mb-2" placeholder="Search..." onkeyup="filterList('locationSearch', 'location-item')" />
                <ul class="list-group">
                    <c:forEach var="loc" items="${locations}">
                        <li class="list-group-item location-item" onclick="selectLocation(${loc.locationId}, this)" style="cursor:pointer;">
                            ${loc.streetAddress}, ${loc.city}, ${loc.stateProvince}, ${loc.countryName} (${loc.regionName})
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" onclick="confirmLocation()">Assign</button>
                <button class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="departmentModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header"><h5 class="modal-title">Add Department</h5><button type="button" class="btn-close" data-bs-dismiss="modal"></button></div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/departments-api/" method="post">
                    <div class="mb-3">
                        <label for="departmentName" class="form-label">Department Name</label>
                        <input type="text" id="departmentName" name="departmentName" class="form-control" required />
                    </div>

                    <div class="mb-3">
                        <label for="managerId" class="form-label">Manager</label>
                        <select id="managerId" name="managerId" class="form-control" required>
                            <option value="">-- Select Manager --</option>
                            <c:forEach var="emp" items="${employees}">
                                <option value="${emp.employeeId}">${emp.firstName} ${emp.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="locationId" class="form-label">Location</label>
                        <select id="locationId" name="locationId" class="form-control" required>
                            <option value="">-- Select Location --</option>
                            <c:forEach var="region" items="${regions}">
                                <optgroup label="${region.regionName}">
                                    <c:forEach var="location" items="${locations}">
                                        <c:if test="${location.regionName eq region.regionName}">
                                            <option value="${location.locationId}">
                                                ${location.streetAddress}, ${location.city}, ${location.stateProvince}, ${location.countryName}
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>

                    <c:if test="${not empty countryError}">
                        <div class="alert alert-danger mt-2">${countryError}</div>
                    </c:if>

                    <button type="submit" class="btn btn-secondary w-100">Add Department</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
