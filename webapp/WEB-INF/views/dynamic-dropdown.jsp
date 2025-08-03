<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${task eq 'getContinents'}">
        <select name="continentId" id="continentDropdown" onchange="loadCountries()">
            <option value="">--Select Continent--</option>
            <c:forEach items="${dataList}" var="item">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
    </c:when>

    <c:when test="${task eq 'getCountries'}">
        <select name="countryId" id="countryDropdown" onchange="loadStates()">
            <option value="">--Select Country--</option>
            <c:forEach items="${dataList}" var="item">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
    </c:when>

    <c:when test="${task eq 'getStates'}">
        <select name="stateId" id="stateDropdown" onchange="loadCities()">
            <option value="">--Select State--</option>
            <c:forEach items="${dataList}" var="item">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
    </c:when>

    <c:when test="${task eq 'getCities'}">
        <select name="cityId" id="cityDropdown">
            <option value="">--Select City--</option>
            <c:forEach items="${dataList}" var="item">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
    </c:when>
</c:choose>
