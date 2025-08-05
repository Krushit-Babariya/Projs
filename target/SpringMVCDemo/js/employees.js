const contextPath = "${pageContext.request.contextPath}";
function openEditModal(id) {
    window.alert(id);
    window.location.href = contextPath + '/api/employees/update?employeeId=${id}';
}

function openDeleteModal(id) {
    document.getElementById('delete_employeeId').value = id;
    new bootstrap.Modal(document.getElementById('deleteModal')).show();
}

function fetchSortedEmployees() {
    const sortBy = document.getElementById("sortBy").value;
    const sortDir = document.getElementById("sortDir").value;
    fetch(`${pageContext.request.contextPath}/employees/sort?sortBy=${sortBy}&sortDir=${sortDir}`)
            .then(response => response.text())
            .then(html => {
                document.getElementById("employeeTableBody").innerHTML = html;
            })
            .catch(error => {
                console.error("AJAX Error:", error);
                alert("Failed to fetch sorted employee data.");
            });
}
