<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-7">
            <h4 class="mb-3">Location List</h4>

            <input type="text" id="locationSearch" class="form-control mb-3" placeholder="Search by City" onkeyup="filterLocations()">

            <div class="table-responsive">
                <table class="table table-bordered table-striped text-center" id="locationTable">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Street</th>
                            <th>Postal Code</th>
                            <th>City</th>
                            <th>State</th>
                            <th>Country</th>
                            <th>Region</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="loc" items="${locations}">
                            <tr>
                                <td>${loc.locationId}</td>
                                <td>${loc.streetAddress}</td>
                                <td>${loc.postalCode}</td>
                                <td class="location-city">${loc.city}</td>
                                <td>${loc.stateProvince}</td>
                                <td>${loc.countryName}</td>
                                <td>${loc.regionName}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/locations/delete/${loc.locationId}" method="post" class="mb-1">
                                        <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-md-5">
            <h4 class="mb-3">Add New Location</h4>
            <form action="${pageContext.request.contextPath}/locations-api/add" method="post">
                <div class="mb-3">
                    <label for="streetAddress" class="form-label">Street Address</label>
                    <input type="text" id="streetAddress" name="streetAddress" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="postalCode" class="form-label">Postal Code</label>
                    <input type="number" id="postalCode" name="postalCode" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="city" class="form-label">City</label>
                    <input type="text" id="city" name="city" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="stateProvince" class="form-label">State Province</label>
                    <input type="text" id="stateProvince" name="stateProvince" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="countryId" class="form-label">Select Country</label>
                    <select id="countryId" name="countryId" class="form-control" required>
                        <option value="">-- Select Country --</option>
                        <c:forEach var="country" items="${countries}">
                            <option value="${country.countryId}">${country.countryName} (${country.regionName})</option>
                        </c:forEach>
                    </select>
                </div>
                <c:if test="${not empty locationError}">
                    <div class="alert alert-danger mt-2">
                        ${locationError}
                    </div>
                </c:if>
                <button type="submit" class="btn btn-secondary w-100">Add Location</button>
            </form>
            <div class="d-flex justify-content-center pt-3">
                <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary w-100">Home</a>
            </div>
        </div>
    </div>
</div>

<script>
    function filterLocations() {
        const input = document.getElementById("locationSearch");
        const filter = input.value.toLowerCase();
        const table = document.getElementById("locationTable");
        const rows = table.getElementsByTagName("tr");

        for (let i = 1; i < rows.length; i++) {
            const td = rows[i].querySelector(".location-city");
            if (td) {
                const txtValue = td.textContent || td.innerText;
                rows[i].style.display = txtValue.toLowerCase().includes(filter) ? "" : "none";
            }
        }
    }
</script>

<%@ include file="footer.jsp" %>