const contextPath = '${pageContext.request.contextPath}';
function filterCountries() {
    const input = document.getElementById("countrySearch");
    const filter = input.value.toLowerCase();
    const rows = document.getElementById("countryTable").getElementsByTagName("tr");
    for (let i = 1; i < rows.length; i++) {
        const td = rows[i].querySelector(".country-name");
        if (td) {
            const txtValue = td.textContent || td.innerText;
            rows[i].style.display = txtValue.toLowerCase().includes(filter) ? "" : "none";
        }
    }
}

function loadCountriesByRegion(regionId) {
    if (regionId !== "") {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", contextPath + "/countries-api/countries-by-region", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById("countryTableDiv").innerHTML = xhr.responseText;
            }
        };
        xhr.send("regionId=" + regionId);
    }
}