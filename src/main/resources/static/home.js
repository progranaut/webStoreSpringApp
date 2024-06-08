console.log("Скрипт подключен!");

document.addEventListener("DOMContentLoaded", function (e){

    displayProducts();

});

async function displayProducts() {

    const productResponse = await fetch('http://localhost:8080/products/all');
    const products = await productResponse.json();

    let centerContent = document.getElementById("center_content");
    centerContent.innerHTML = ``;

    let homeContent = document.createElement("div");
    homeContent.classList.add('home_content');

    products.forEach(product => {

        let div = document.createElement("div");
        div.classList.add('home_product');
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

            fetch("http://localhost:8080/store/add-in-basket/" + product.id, {
                method: "POST"
            });

            // fetch("http://localhost:8080/store/add-in-basket", {
            //     method: "POST",
            //     headers: {
            //         'Content-Type': 'application/json'
            //     },
            //     body: `{
            //     "id": "${div.getAttribute("data-product")}"
            //     }`
            // });
        });
        div.appendChild(btn);
        homeContent.appendChild(div);
    });

    centerContent.appendChild(homeContent);
}

