<%--<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ include file="header.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/countries.js"></script>

<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">${errorMessage}</div>
</c:if>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-7">
            <h4 class="mb-3">Country List</h4>

            <input type="text" id="countrySearch" class="form-control mb-3" placeholder="Search by Country Name" onkeyup="filterCountries()">

            <div id="countryTableDiv">
                <jsp:include page="countries-table.jsp" />
            </div>
        </div>

        <div class="col-md-5">
            <h4 class="mb-3">Add New Country</h4>
            <form action="${pageContext.request.contextPath}/countries-api" method="post">
                <div class="mb-3">
                    <label for="countryName" class="form-label">Country Name</label>
                    <input type="text" id="countryName" name="countryName" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="regionId" class="form-label">Select Continent</label>
                    <select id="regionId" name="regionId" class="form-control" required onchange="loadCountriesByRegion(this.value)">
                        <option value="">-- Select Continent --</option>
                        <c:forEach var="region" items="${regions}">
                            <option value="${region.regionId}">${region.regionName}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-secondary w-100">Add Country</button>
            </form>
            <div class="pt-3 d-flex flex-column gap-2">
                <a href="${pageContext.request.contextPath}/regions-api/" class="btn btn-secondary w-100">Add Region</a>
                <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary w-100">Home</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
