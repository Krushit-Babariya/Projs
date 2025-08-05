<%@ include file="header.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/regions.js"></script>


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
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="region" items="${regions}">
                            <tr class="${region.isActive ? 'table-danger' : ''}">
                                <td>${region.regionId}</td>
                                <td class="region-name">${region.regionName}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/regions-api/change-status?id=${region.regionId}" method="get">
                                        <input type="hidden" name="id" value="${region.regionId}" />
                                        <button type="submit" class="btn btn-sm ${!region.isActive ? 'btn-warning' : 'btn-success'}">
                                            ${region.isActive ? 'Activate' : 'Dectivate'}
                                        </button>
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
                    <input type="text" id="regionName" name="regionName" value="You can not add continents now" class="form-control" readonly>
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

<%@ include file="footer.jsp" %>
