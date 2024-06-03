console.log("testsdfikohjsdohfjklds1");

document.addEventListener("DOMContentLoaded", function (e){

    var request = new Request("http://localhost:8080/users/current-user-name");
    var userP = document.getElementById("user");
    fetch(request).then(response=> response.text())
        .then(text => userP.innerText = text);
});