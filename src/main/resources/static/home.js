console.log("home.js подключен!");

document.addEventListener("DOMContentLoaded", function (e){

    fetch("http://localhost:8080/store/current-user-name-roll").then(async (response) => {

        if (response.status === 200) {
            await addBasket();
        }

        let currentUser = document.getElementById("user");
        let centerContent = document.getElementById('center_content');

        let menu = await displayMenuUser(response);
        currentUser.appendChild(menu);
        centerContent.innerHTML = ``;
        let content = await displayProducts(response);
        centerContent.appendChild(content);

    });

});

async function addBasket(){
    if (!(document.cookie === "")) {
        let productRelationArray = new Array();
        document.cookie.split(";").forEach(cook => {
            let product = {
                productDto: {
                    id: cook.split("=")[0].trim()
                },
                quantity: cook.split("=")[1].trim()
            }
            productRelationArray.push(product);
            document.cookie = cook.trim() + "; max-age=0";
        });
        await fetch('http://localhost:8080/store/add-basket', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(productRelationArray)
        });
    }
}

async function displayProducts() {

    const productResponse = await fetch('http://localhost:8080/store/all-products');
    const products = await productResponse.json();

    let centerContent = document.getElementById("center_content");

    let homeContent = document.createElement("div");
    homeContent.classList.add('home_content');

    for (let product of products) {
        if (product.availability < 1) {
            continue;
        }
        let div = document.createElement("div");
        div.classList.add('home_product');
        div.setAttribute('data-product', product.id);

        div.innerHTML = `
        <p class="product_image"><img src="${ "/img/" + product.imageUrl + ".jpg"}"></p>
        <p class="product_name">${product.name}</p>
        <p class="product_description">${product.description}</p>
        <p class="product_price">${product.price}</p>
        `;

        if (arguments[0].status === 200) {

            let btnNotInBasket = document.createElement("button");
            btnNotInBasket.innerText = "Добавить";
            btnNotInBasket.classList.add('add_in_basket');
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
            btnInBasket.classList.add('in_to_order_btn');
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
            btnNotInBasket.classList.add('add_in_basket');
            btnNotInBasket.addEventListener('click', async (e) => {

                document.cookie = product.id + "=1";

                btnNotInBasket.remove();
                div.appendChild(btnInBasket);
            });

            let btnInBasket = document.createElement('button');
            btnInBasket.innerText = "Перейти к заказу";
            btnInBasket.classList.add('in_to_order_btn');
            btnInBasket.addEventListener('click', async (e) => {

                centerContent.innerHTML = ``;
                centerContent.appendChild(await displayProductInCartNotReg());

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

