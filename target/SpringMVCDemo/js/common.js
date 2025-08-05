//let url = getallUrl();
//console.log(url);
//
//function ajaxcall(method, url, isAsync, destination, data) {
//    const xhttp = new XMLHttpRequest();
//    xhttp.onload = function () {
//        document.getElementById(destination).innerHTML = this.responseText;
//    };
//    xhttp.open(method, url, isAsync);
//    xhttp.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
//    xhttp.send(data);
//}
//
//function addEmployee() {
//    ajaxcall("GET", url.viewform, true, "EmployeeList", null);
//}
//
//function submitform() {
//    alert("In submit form");
//    let result = formValidation("email") &&
//            formValidation("firstName") &&
//            formValidation("lastName") &&
//            formValidation("phoneNumber") &&
//            formValidation("salary");
//
//    if (result) {
//        let formele = document.getElementById("formdata");
//        let data = getFormData(formele);
//        ajaxcall("POST", url.insertEmployee, false, "EmployeeList", data);
//        if (document.getElementById("status").value > 0) {
//            alert("Employee inserted Successfully");
//        } else {
//            alert("Please try again");
//        }
//        console.log(data);
//    }
//}
//
//function formValidation(id) {
//    let elementval = document.getElementById(id);
//    if (elementval.value.trim() === "") {
//        elementval.focus();
//        alert("Please enter a Value for " + id);
//        return false;
//    }
//    return true;
//}
//
//let urls = {
//    insertEmployee: "${pageContext.request.contextPath}/addEmployee"
//};
//
//function getallUrl() {
//    return urls;
//}