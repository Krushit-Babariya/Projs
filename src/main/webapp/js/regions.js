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