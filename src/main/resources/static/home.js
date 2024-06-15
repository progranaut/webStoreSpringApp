console.log("Скрипт подключен!");

document.addEventListener("DOMContentLoaded", function (e){

    fetch("http://localhost:8080/store/current-user-name-roll").then(async (response) => {

        let currentUser = document.getElementById("user");
        let centerContent = document.getElementById('center_content');

        // if (response.redirected) {
        //     currentUser.innerHTML = '<a href="http://localhost:8080/login">Войти</a>';
        // } else {
        //     let menu = await displayMenuUser(response);
        //     currentUser.appendChild(menu);
        //     centerContent.innerHTML = ``;
        //     centerContent.appendChild(await displayProducts());
        // }

        let menu = await displayMenuUser(response);
        currentUser.appendChild(menu);
        centerContent.innerHTML = ``;
        centerContent.appendChild(await displayProducts());

    });

});


// document.addEventListener("DOMContentLoaded", function (e){
//
//     fetch("http://localhost:8080/store/current-user-name").then(async (response) => {
//
//         let currentUser = document.getElementById("user");
//         let centerContent = document.getElementById('center_content');
//         if (response.redirected) {
//             currentUser.innerHTML = '<a href="http://localhost:8080/login">Войти</a>';
//         } else {
//             //let name = await response.text();
//             let menu = await displayMenuUser(response);
//             currentUser.appendChild(menu);
//             centerContent.innerHTML = ``;
//             centerContent.appendChild(await displayProducts());
//         }
//
//     });
//
// });

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
        btnInBasket.addEventListener('click', async (e) => {
            let displayProductsInCart = await displayProductInCart();
            centerContent.innerHTML = ``;
            centerContent.appendChild(displayProductsInCart);
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

    return homeContent;

}

