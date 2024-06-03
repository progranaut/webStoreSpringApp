console.log("testsdfikohjsdohfjklds1");

document.addEventListener("DOMContentLoaded", function (e){
    var request = new Request("http://localhost:8080/users/current-user-name");
    var user = document.getElementById("user");
    fetch(request).then(response=> response.text())
        .then(text => user.innerText = text);
});

var btn = document.getElementById("addProductBtn");
btn.addEventListener("click", function (e) {

    // var product = {
    //     "id": "4524c062-aef3-4667-96cd-842a3cf07932"
    // };
    // var data = new FormData();
    // data.append("json", JSON.stringify(product));

    fetch("http://localhost:8080/store/add-in-basket", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: `{
        "id": "4524c062-aef3-4667-96cd-842a3cf07932"
        }`
    }).then(response => console.log(response.status));

});