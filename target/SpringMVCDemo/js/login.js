function onLoginSubmit(event) {
    event.preventDefault();
    const res = formValidation("email") && formValidation("password");
    console.log("Validation :: ", res);

    if (res) {
        document.getElementById("loginForm").submit();
    }
}

function formValidation(id) {
    const element = document.getElementById(id);
    const value = element.value.trim();

    if (id === "email") {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (value === "" || !emailRegex.test(value)) {
            alert("Please enter a valid email address.");
            element.focus();
            return false;
        }
    } else if (id === "password") {
        if (value === "") {
            alert("Please enter your password.");
            element.focus();
            return false;
        }
    }
    return true;
}
