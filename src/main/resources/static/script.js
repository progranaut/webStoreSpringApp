console.log("Скрипт подключен!");

document.addEventListener("DOMContentLoaded", function (e){

    var request = new Request("http://localhost:8080/users/current-user-name");
    var user = document.getElementById("user");
    fetch(request).then(response=> response.text())
        .then(text => {
            if (text === "") {
                user.innerHTML = '<a href="http://localhost:8080/login">Войти</a>';
            } else {
                user.innerText = "Привет, " + text;
            }
        });

    displayProducts();

});

var btn = document.getElementById("addProductBtn");
btn.addEventListener("click", function (e) {

    // var product = {
    //     id: "d7739532-1310-4936-b941-bcf248ca8b43"
    // };
    // var data = new FormData();
    // data.append("json", JSON.stringify(product));

    fetch("http://localhost:8080/store/add-in-basket", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: `{
        "id": "d7739532-1310-4936-b941-bcf248ca8b43"
        }`
    }).then(response => console.log(response.status));

});

async function displayProducts() {

    const productResponse = await fetch('http://localhost:8080/products/all');
    const products = await productResponse.json();

    let centerContent = document.getElementById("center_content");
    centerContent.innerHTML = ``;
    products.forEach(product => {

        let div = document.createElement("div");
        div.classList.add('product');
        div.innerHTML = `
        <p>${product.id}</p>
        <p>${product.serialNumber}</p>
        <p>${product.name}</p>
        `;

        // let id = document.createElement("p");
        // id.innerText = product.id;
        // div.appendChild(id);
        //
        // let serialNumber = document.createElement("p");
        // serialNumber.innerText = product.serialNumber;
        // div.appendChild(serialNumber);
        //
        // let name = document.createElement("p");
        // name.innerText = product.name;
        // div.appendChild(name);

        centerContent.appendChild(div);

    });

}
