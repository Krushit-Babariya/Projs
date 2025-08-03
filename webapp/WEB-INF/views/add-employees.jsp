<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<!--<script src="js/common.js"></script>-->

<div class="container mt-4">
    <div class="card">
        <div class="card-header bg-dark text-white">
            <h4>Add New Employee</h4>
        </div>
        <div class="card-body">
            <!--<form action="${pageContext.request.contextPath}/api/addEmployee" method="post">-->
            <form id="formdata">
                <div class="row g-3">
                    <div class="col-md-4">
                        <input type="text" id="firstName" name="firstName" class="form-control" placeholder="First Name"  />
                    </div>
                    <div class="col-md-4">
                        <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Last Name"  />
                    </div>
                    <div class="col-md-4">
                        <input type="email" id="email" name="email" class="form-control" placeholder="Email"  />
                    </div>
                    <div class="col-md-4">
                        <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="Phone Number" />
                    </div>
                    <div class="col-md-4">
                        <input type="date" id="hireDate" name="hireDate" class="form-control" placeholder="Hire Date"  />
                    </div>
                    <div class="col-md-4">
                        <select name="jobId" id="jobSelect" class="form-control" >
                            <option value="">Select Job Type</option>
                            <c:forEach var="job" items="${jobs}">
                                <option value="${job.jobId}">${job.jobTitle}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <input type="number" id="salary" name="salary" class="form-control" placeholder="Salary"  />
                    </div>
                    <div class="col-md-4">
                        <input type="text" id="commissionPct" name="commissionPct" class="form-control" placeholder="Commission %" />
                    </div>

                    <div class="col-md-4">
                        <select name="departmentId" id="departmentSelect" class="form-control" >
                            <option value="">Select Department</option>
                            <c:forEach var="dept" items="${departments}">
                                <option value="${dept.departmentId}">${dept.departmentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="mt-4 text-center">
                    <button onclick="submitform()" type="submit" class="btn btn-dark px-4">Add Employee</button>
                    <a href="${pageContext.request.contextPath}/api/employees" class="btn btn-secondary px-4">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>

<script>

    let url = {
        insertEmployee: "${pageContext.request.contextPath}/api/addEmployee"
    };
//
//    function getallUrl() {
//        return urls;
//    }
//    
//    let url = getallUrl();
    console.log(url);

    function ajaxcall(method, url, isAsync, destination, data) {
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            document.getElementById(destination).innerHTML = this.responseText;
        };
        xhttp.open(method, url, isAsync);
        xhttp.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
        xhttp.send(data);
    }

    function addEmployee() {
        ajaxcall("GET", url.viewform, true, "EmployeeList", null);
    }

    function submitform() {
        alert("In submit form");
        let result = formValidation("email") &&
                formValidation("firstName") &&
                formValidation("lastName") &&
                formValidation("phoneNumber") &&
                formValidation("salary");
        
        alert("Result", result);
        if (result) {
            let formele = document.getElementById("formdata");
            let data = getFormData(formele);
            alert("Form data :: ", data);
            ajaxcall("POST", url.insertEmployee, false, "EmployeeList", data);
            if (document.getElementById("status").value > 0) {
                alert("Employee inserted Successfully");
            } else {
                alert("Please try again");
            }
            console.log(data);
        }
    }

    function formValidation(id) {
        alert("in Formvalidation :: ", id);
        let elementval = document.getElementById(id);
        if (elementval.value.trim() === "") {
            elementval.focus();
            alert("Please enter a Value for " + id);
            return false;
        }
        return true;
    }
</script>