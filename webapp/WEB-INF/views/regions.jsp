<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-7">
            <h4 class="mb-3">Region List</h4>

            <input type="text" id="regionSearch" class="form-control mb-3" placeholder="Search by Region Name" onkeyup="filterRegions()">

            <div class="table-responsive">
                <table class="table table-bordered table-striped text-center" id="regionTable">
                    <thead class="table-dark">
                        <tr>
                            <th>Region ID</th>
                            <th>Region Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="region" items="${regions}">
                            <tr>
                                <td>${region.regionId}</td>
                                <td class="region-name">${region.regionName}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/regions-api/${region.regionId}" method="post">
                                        <button type="submit" class="btn btn-sm btn-secondary">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-md-5">
            <h4 class="mb-3">Add New Region</h4>
            <form action="${pageContext.request.contextPath}/regions-api/regions" method="post">
                <div class="mb-3">
                    <label for="regionName" class="form-label">Region Name</label>
                    <input type="text" id="regionName" name="regionName" class="form-control" required>
                </div>
                <c:if test="${not empty regionError}">
                    <div class="alert alert-danger mt-2">
                        ${regionError}
                    </div>
                </c:if>
                <button type="submit" class="btn btn-secondary w-100">Add Region</button>
            </form>
            <div class="d-flex justify-content-center space-between pt-20">
                <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary w-100 mt-2">Home</a>
            </div>
        </div>
    </div>
</div>

<script>
    function filterRegions() {
        const input = document.getElementById("regionSearch");
        const filter = input.value.toLowerCase();
        const table = document.getElementById("regionTable");
        const rows = table.getElementsByTagName("tr");

        for (let i = 1; i < rows.length; i++) {
            const td = rows[i].querySelector(".region-name");
            if (td) {
                const txtValue = td.textContent || td.innerText;
                rows[i].style.display = txtValue.toLowerCase().includes(filter) ? "" : "none";
            }
        }
    }
</script>

<%@ include file="footer.jsp" %>
