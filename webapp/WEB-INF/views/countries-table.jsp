<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-bordered table-striped text-center" id="countryTable">
    <thead class="table-dark">
        <tr>
            <th>Country ID</th>
            <th>Country Name</th>
            <th>Region</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="country" items="${countries}">
            <tr>
                <td>${country.countryId}</td>
                <td class="country-name">${country.countryName}</td>
                <td>${country.regionName}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/countries-api/${country.countryId}" method="post">
                        <button type="submit" class="btn btn-sm btn-secondary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
