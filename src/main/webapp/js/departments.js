const contextPath = '<%= request.getContextPath()%>';
let selectedDepartmentId = null;
let selectedManagerId = null;
let selectedLocationId = null;

function openManagerDialog(deptId) {
    selectedDepartmentId = deptId;
    new bootstrap.Modal(document.getElementById('managerModal')).show();
}

function selectManager(managerId, element) {
    selectedManagerId = managerId;
    document.querySelectorAll('.manager-item').forEach(item => item.classList.remove('active'));
    element.classList.add('active');
}

function confirmManager() {
    if (!selectedManagerId)
        return alert("Please select a manager first.");
    assignManager(selectedManagerId);
}

function assignManager(managerId) {
    fetch(`${contextPath}/departments-api/assign-manager`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({departmentId: selectedDepartmentId, managerId})
    }).then(res => {
        if (res.ok)
            location.reload();
        else
            alert("Failed to assign manager.");
    }).catch(err => {
        alert("Error assigning manager.");
        console.error(err);
    });
}

function openLocationDialog(deptId) {
    selectedDepartmentId = deptId;
    new bootstrap.Modal(document.getElementById('locationModal')).show();
}

function selectLocation(locationId, element) {
    selectedLocationId = locationId;
    document.querySelectorAll('.location-item').forEach(item => item.classList.remove('active'));
    element.classList.add('active');
}

function confirmLocation() {
    if (!selectedLocationId)
        return alert("Please select a location first.");
    assignLocation(selectedLocationId);
}

function assignLocation(locationId) {
    fetch(`${contextPath}/departments-api/assign-location`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({departmentId: selectedDepartmentId, locationId})
    }).then(res => {
        if (res.ok)
            location.reload();
        else
            alert("Failed to assign location.");
    }).catch(err => {
        alert("Error assigning location.");
        console.error(err);
    });
}

function openAddDepartmentDialog() {
    new bootstrap.Modal(document.getElementById('departmentModal')).show();
}

function filterList(searchId, itemClass) {
    const filter = document.getElementById(searchId).value.toLowerCase();
    document.querySelectorAll("." + itemClass).forEach(item => {
        item.style.display = item.textContent.toLowerCase().includes(filter) ? "" : "none";
    });
}

function confirmDelete() {
    return confirm("Are you sure you want to delete this department?");
}