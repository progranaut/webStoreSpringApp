

async function displayProductInCart() {

    let response = await fetch('http://localhost:8080/store/all-products-in-basket');
    let productsInBasket = await response.json();

    console.log(productsInBasket);

    let cartContent = document.createElement("div");
    cartContent.classList.add('cart_content');

    let relationArray = Array.from(productsInBasket);

    console.log(relationArray);

    if (relationArray.length === 0) {
        cartContent.innerHTML = `
                        <p>Корзина пуста</p>
                        <a href="http://localhost:8080/">Просмотреть товары</a>
                    `;
        return cartContent;
    }

    relationArray.forEach(relation => {

        let div = document.createElement("div");
        div.classList.add('cart_product');
        div.setAttribute('data-product', relation.productDto.id);

        let divProdImg = document.createElement("div");
        divProdImg.classList.add('product_img');
        divProdImg.innerHTML = `
        <img src="${relation.productDto.imageUrl}">
        `;
        div.appendChild(divProdImg);

        let divProdInfo = document.createElement("div");
        divProdInfo.classList.add('product_info');
        divProdInfo.innerHTML = `
        <p>${relation.productDto.id}</p>
        <p>${relation.productDto.serialNumber}</p>
        <p>${relation.productDto.name}</p>
        <p>${relation.productDto.price}</p>
        `;
        div.appendChild(divProdInfo);

        let divQuantityProduct = document.createElement("div");
        divQuantityProduct.classList.add('product_quantity_availability');

        let divQuantity = document.createElement('div');
        divQuantity.classList.add('product_quantity');
        let btnMinus = document.createElement('button');
        btnMinus.classList.add('minus_quantity');
        btnMinus.innerText = "-";
        btnMinus.addEventListener('click', async (e) => {
            let request = new Request("http://localhost:8080/store/delete-product-from-basket/" + relation.productDto.id, {
                method: "DELETE"
            });
            let responseDel = await fetch(request);
            if (responseDel.status != 404) {
                let respJson = await responseDel.json();
                prodQuantity.innerText = respJson.quantity;
            } else {
                div.remove();
                let checkLast = await fetch('http://localhost:8080/store/all-products-in-basket');
                let checkLastJson = await checkLast.json();
                if (checkLastJson.length === 0) {
                    cartContent.innerHTML = `
                        <p>Корзина пуста</p>
                        <a href="http://localhost:8080/">Просмотреть товары</a>
                    `;
                }
            }
        });
        divQuantity.appendChild(btnMinus);

        let prodQuantity = document.createElement('p');
        prodQuantity.innerText = relation.quantity;
        divQuantity.appendChild(prodQuantity);

        let btnPlus = document.createElement("button");
        btnPlus.classList.add('plus_quantity');
        btnPlus.innerText = "+";
        btnPlus.addEventListener('click', async (e)=>{
            let response = await fetch("http://localhost:8080/store/add-in-basket/" + relation.productDto.id, {
                method: "POST"
            });
            if (response.status === 200) {
                let respJson = await response.json();
                prodQuantity.innerText = respJson.quantity;
            }
            if (response.status === 406) {
                divAvailability.style.backgroundColor = "RED";
            }
        });
        divQuantity.appendChild(btnPlus);

        divQuantityProduct.appendChild(divQuantity);

        let divAvailability = document.createElement('div');
        divAvailability.classList.add('product_availability');
        divAvailability.innerHTML = `
            Наличие: ${relation.productDto.availability} шт.
        `;
        divQuantityProduct.appendChild(divAvailability);

        div.appendChild(divQuantityProduct);

        cartContent.appendChild(div);
    });

    let orderBtn = document.createElement('button');
    orderBtn.innerText = "Оформить заказ";
    orderBtn.addEventListener('click', async (e)=>{
        orderBtn.style.visibility = "hidden";
        let dispUser = await displayUser();
        cartContent.appendChild(dispUser);
        let confirmBtn = document.createElement('button');
        confirmBtn.innerText = "Подтвердить заказ";
        confirmBtn.addEventListener('click', (e) => {
            fetch('http://localhost:8080/store/add-order').then(response => {
                if (response.status === 200) {
                    let message = document.createElement('p');
                    message.innerText = "Заказ успешно подтвержден";
                    cartContent.insertBefore(message ,confirmBtn);
                } else {
                    let message = document.createElement('p');
                    message.innerText = "Необходимо заполнить все поля и сохранить их!";
                    cartContent.insertBefore(message ,confirmBtn);
                }
            });
        });
        cartContent.appendChild(confirmBtn);
    });

    cartContent.appendChild(orderBtn);

    return cartContent;

}

class Product {

    constructor(/*id, serialNumber, name, price*/) {
        // this.id = id;
        // this.serialNumber = serialNumber;
        // this.name = name;
        // this.price = price;
    }

}