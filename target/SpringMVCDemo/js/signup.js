function onSignUpSubmit(event) {
    event.preventDefault();
    const res = formValidation("firstName")
            && formValidation("lastName")
            && formValidation("email")
            && formValidation("phoneNumber")
            && formValidation("pass")
            && formValidation("role");

    console.log("Validation :: ", res);

    if (res) {
        document.getElementById("signUpFormData").submit();
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