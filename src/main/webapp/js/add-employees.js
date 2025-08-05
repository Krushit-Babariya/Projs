const url = {
    insertEmployee: "${pageContext.request.contextPath}/api/addEmployee"
};
function submitform(event) {
    event.preventDefault();

    const result =
            formValidation("firstName") &&
            formValidation("lastName") &&
            formValidation("email") &&
            formValidation("phoneNumber") &&
            formValidation("hireDate") &&
            formValidation("jobSelect") &&
            formValidation("salary");

    console.log("Validation result: " + result);

    if (result) {
        const formElement = document.getElementById("formdata");
        const formData = new FormData(formElement);
        const data = new URLSearchParams();

        for (const pair of formData.entries()) {
            data.append(pair[0], pair[1]);
        }
        console.log("Sending form data: " + data.toString());
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            //  alert(this.responseText);
            formElement.reset();
        };
        xhttp.open("POST", url.insertEmployee, true);
        xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhttp.send(data.toString());
    }
}

function formValidation(id) {
    const element = document.getElementById(id);
    if (element.value.trim() === "") {
        element.focus();
        alert("Please enter a value for " + id);
        return false;
    }
    return true;
}