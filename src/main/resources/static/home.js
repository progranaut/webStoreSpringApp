console.log("home.js подключен!");

document.addEventListener("DOMContentLoaded", async function (e){

    fetch("http://localhost:8080/store/current-user-name-roll").then(async (response) => {

        console.log(response.status);

        let currentUser = document.getElementById("user");
        let centerContent = document.getElementById('center_content');

        let menu = await displayMenuUser(response);
        currentUser.appendChild(menu);
        centerContent.innerHTML = ``;
        console.log("test");
        let content = await displayProducts(response);
        console.log("test2");
        centerContent.appendChild(content);
        console.log("test3");

    });

});


async function displayProducts() {

    const productResponse = await fetch('http://localhost:8080/store/all-products');
    const products = await productResponse.json();

    let centerContent = document.getElementById("center_content");

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

        if (arguments[0].status == 200) {

            let btnNotInBasket = document.createElement("button");
            btnNotInBasket.innerText = "Добавить";
            btnNotInBasket.addEventListener('click', async (e) => {
                await fetch("http://localhost:8080/store/add-in-basket/" + product.id, {
                    method: "POST"
                });
                let resp = await fetch("http://localhost:8080/store/product-in-basket/" + product.id, {
                    method: "HEAD"
                });
                if (resp.status == 200) {
                    btnNotInBasket.remove();
                    div.appendChild(btnInBasket);
                }
            });

            let btnInBasket = document.createElement('button');
            btnInBasket.innerText = "Перейти к заказу";
            btnInBasket.addEventListener('click', async (e) => {
                let displayProductsInCart = await displayProductInCart();
                centerContent.innerHTML = ``;
                centerContent.appendChild(displayProductsInCart);
            });

            let responsePrd = await fetch("http://localhost:8080/store/product-in-basket/" + product.id, {
                method: "HEAD"
            });

            if (responsePrd.status === 200) {
                div.appendChild(btnInBasket);
            } else {
                div.appendChild(btnNotInBasket);
            }

        } else {

            let btnNotInBasket = document.createElement("button");
            btnNotInBasket.innerText = "Добавить";
            btnNotInBasket.addEventListener('click', async (e) => {

                document.cookie = product.id + "=1";

                btnNotInBasket.remove();
                div.appendChild(btnInBasket);
            });

            let btnInBasket = document.createElement('button');
            btnInBasket.innerText = "Перейти к заказу";
            btnInBasket.addEventListener('click', async (e) => {

                console.log(document.cookie);

            });

            let cook = document.cookie;
            let cookieArray = cook.split(";");
            let cookieContains = false;
            cookieArray.forEach(cook => {
                if (cook.split("=")[0].trim() === /*div.getAttribute('data-product')*/ product.id) {
                    cookieContains = true;
                }
            });

            if (cookieContains) {
                div.appendChild(btnInBasket);
            } else {
                div.appendChild(btnNotInBasket);
            }

        }

        homeContent.appendChild(div);
    }

    return homeContent;

}

