window.onload = function () {
    fetchContent();
};

function fetchContent() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "countries-table.jsp", true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("content").innerHTML = xhr.responseText;
        }
    };
    xhr.send();
}
