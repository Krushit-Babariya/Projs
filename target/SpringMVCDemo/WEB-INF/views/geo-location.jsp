<%@ include file="header.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/geo-location.js"></script>

<h2>Select Location</h2>

<div>
    <select id="continentDropdown" name="continentId" onchange="loadCountries()">
        <option value="">-- Select Continent --</option>
        <c:forEach items="${continents}" var="continent">
            <option value="${continent.id}">${continent.name}</option>
        </c:forEach>
    </select>


    <div id="countryDiv"></div>

    <div id="stateDiv"></div>

    <div id="cityDiv"></div>
</div>

<script>

</script>
