console.log("Скрипт подключен!");

document.addEventListener("DOMContentLoaded", function (e){

    displayProducts();

});

async function displayProducts() {

    const productResponse = await fetch('http://localhost:8080/store/products-all');
    const products = await productResponse.json();

    let centerContent = document.getElementById("center_content");
    centerContent.innerHTML = ``;

    let homeContent = document.createElement("div");
    homeContent.classList.add('home_content');

    for (let product of products) {
        let div = document.createElement("div");
        div.classList.add('home_product');
        div.setAttribute('data-product', product.id);

        div.innerHTML = `
        <p>${product.id}</p>
        <p>${product.serialNumber}</p>
        <p>${product.name}</p>
        <p>${product.price}</p>
        `;

        let btnNotInBasket = document.createElement("button");
        btnNotInBasket.innerText = "Добавить";
        btnNotInBasket.addEventListener('click', async (e) => {
            await fetch("http://localhost:8080/store/add-in-basket/" + product.id, {
                method: "POST"
            });
            let resp = await fetch("http://localhost:8080/store/product-in-basket/" + product.id, {
                method: "HEAD"
            });
            btnNotInBasket.remove();
            div.appendChild(btnInBasket);
        });

        let btnInBasket = document.createElement('button');
        btnInBasket.innerText = "Перейти к заказу";
        btnInBasket.addEventListener('click', (e) => {
            displayProductInCart();
        });

        let response = await fetch("http://localhost:8080/store/product-in-basket/" + product.id, {
            method: "HEAD"
        });

        if (response.status === 200) {

            div.appendChild(btnInBasket);

        } else {

            div.appendChild(btnNotInBasket);

        }

        homeContent.appendChild(div);
    }

    centerContent.appendChild(homeContent);

}

