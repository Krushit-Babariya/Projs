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