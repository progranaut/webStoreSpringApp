console.log("Скрипт подключен!");

document.addEventListener("DOMContentLoaded", function (e){

    displayProducts();

});

// var btn = document.getElementById("addProductBtn");
// btn.addEventListener("click", function (e) {
//
//     // var product = {
//     //     id: "d7739532-1310-4936-b941-bcf248ca8b43"
//     // };
//     // var data = new FormData();
//     // data.append("json", JSON.stringify(product));
//
//     fetch("http://localhost:8080/store/add-in-basket", {
//         method: "POST",
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: `{
//         "id": "cdb98bbc-d5b3-4ba4-9a4d-c2a145aacef9"
//         }`
//     }).then(response => console.log(response.status));
//
// });

async function displayProducts() {

    const productResponse = await fetch('http://localhost:8080/products/all');
    const products = await productResponse.json();

    let centerContent = document.getElementById("center_content");
    centerContent.innerHTML = ``;
    products.forEach(product => {

        let div = document.createElement("div");
        div.classList.add('product');
        div.setAttribute('data-product', product.id);

        div.innerHTML = `
        <p>${product.id}</p>
        <p>${product.serialNumber}</p>
        <p>${product.name}</p>
        <p>${product.price}</p>
        `;

        let btn = document.createElement("button");
        btn.innerText = "В корзину";
        btn.addEventListener('click', (e) => {
            //console.log(div.getAttribute("data-product"));
            fetch("http://localhost:8080/store/add-in-basket", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: `{
                "id": "${div.getAttribute("data-product")}"
                }`
            });
        });
        div.appendChild(btn);
        centerContent.appendChild(div);

    });
}

