<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container my-5">
    <div class="row g-4">
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <i class="bi bi-people-fill fs-1 text-secondary"></i>
                    <h5 class="card-title mt-3">Employees</h5>
                    <a href="${pageContext.request.contextPath}/api/employees" class="btn btn-outline-secondary">View</a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <i class="bi bi-flag-fill fs-1 text-secondary"></i>
                    <h5 class="card-title mt-3">Countries</h5>
                    <a href="${pageContext.request.contextPath}/countries-api/" class="btn btn-outline-secondary">View</a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <i class="bi bi-globe-asia-australia fs-1 text-secondary"></i>
                    <h5 class="card-title mt-3">Geo Locations</h5>
                    <a href="${pageContext.request.contextPath}/geo-locations-api/" class="btn btn-outline-secondary">View</a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <i class="bi bi-diagram-3-fill fs-1 text-secondary"></i>
                    <h5 class="card-title mt-3">Departments</h5>
                    <a href="${pageContext.request.contextPath}/departments-api/" class="btn btn-outline-secondary">View</a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <i class="bi bi-geo-alt-fill fs-1 text-secondary"></i>
                    <h5 class="card-title mt-3">Locations</h5>
                    <a href="${pageContext.request.contextPath}/locations-api/" class="btn btn-outline-secondary">View</a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <i class="bi bi-map-fill fs-1 text-secondary"></i>
                    <h5 class="card-title mt-3">Regions</h5>
                    <a href="${pageContext.request.contextPath}/regions-api/" class="btn btn-outline-secondary">View</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
