<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

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
    function loadCountries() {
        const continentId = document.getElementById("continentDropdown").value;
        getData('/geo-locations-api/getCountries', 'countryDiv', 'continentId=' + continentId);
    }

    function loadStates() {
        const countryId = document.getElementById("countryDropdown").value;
        getData('/geo-locations-api/getStates', 'stateDiv', 'countryId=' + countryId);
    }

    function loadCities() {
        const stateId = document.getElementById("stateDropdown").value;
        getData('/geo-locations-api/getCities', 'cityDiv', 'stateId=' + stateId);
    }

    function getData(dataSource, divID, param) {
        const contextPath = "${pageContext.request.contextPath}";

        var XMLHttpRequestObject = false;
        if (window.XMLHttpRequest) {
            XMLHttpRequestObject = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (XMLHttpRequestObject) {
            var obj = document.getElementById(divID);
            obj.innerHTML = '<center><div><img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50"><br><font color="darkblue"><b>Loading Page... </b></font></div></center>';
            XMLHttpRequestObject.open("POST", contextPath + dataSource);
            XMLHttpRequestObject.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            XMLHttpRequestObject.onreadystatechange = function () {
                if (XMLHttpRequestObject.readyState === 4 && XMLHttpRequestObject.status === 200) {
                    obj.innerHTML = XMLHttpRequestObject.responseText;
                }
            };
            XMLHttpRequestObject.send(param);
        }
    }
</script>
