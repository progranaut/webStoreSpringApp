

async function displayProductInCart() {

    var response = await fetch('http://localhost:8080/store/all-products-in-basket');
    var productsInBasket = await response.json();

    console.log(productsInBasket);

    let centerContent = document.getElementById("center_content");
    centerContent.innerHTML = ``;

    let cartContent = document.createElement("div");
    cartContent.classList.add('cart_content');

    let productArray = Array.from(productsInBasket);

    console.log(productArray);

    productArray.forEach(product => {

        let div = document.createElement("div");
        div.classList.add('cart_product');
        div.setAttribute('data-product', product.id);

        let divProdImg = document.createElement("div");
        divProdImg.classList.add('product_img');
        divProdImg.innerHTML = `
        <img src="${product.productDto.imageUrl}">
        `;
        div.appendChild(divProdImg);

        let divProdInfo = document.createElement("div");
        divProdInfo.classList.add('product_info');
        divProdInfo.innerHTML = `
        <p>${product.productDto.id}</p>
        <p>${product.productDto.serialNumber}</p>
        <p>${product.productDto.name}</p>
        <p>${product.productDto.price}</p>
        `;
        div.appendChild(divProdInfo);

        let divQuantityProduct = document.createElement("div");
        divQuantityProduct.classList.add('product_quantity');

        let btnMinus = document.createElement('button');
        btnMinus.classList.add('minus_quantity');
        btnMinus.innerText = "-";
        btnMinus.addEventListener('click', async (e) => {
            let request = new Request("http://localhost:8080/store/delete-product-from-basket/" + product.productDto.id, {
                method: "DELETE"
            });
            await fetch(request);

            let response = await fetch("http://localhost:8080/store/product-in-basket/" + product.productDto.id);
            if (response.status != 404) {
                let respJson = await response.json();
                prodQuantity.innerText = respJson.quantity;
            } else {
                div.remove();
            }

            //await displayProductInCart();
        });
        divQuantityProduct.appendChild(btnMinus);


        let prodQuantity = document.createElement('p');
        prodQuantity.innerText = product.quantity;
        divQuantityProduct.appendChild(prodQuantity);

        let btnPlus = document.createElement("button");
        btnPlus.classList.add('plus_quantity');
        btnPlus.innerText = "+";
        btnPlus.addEventListener('click', async (e)=>{
            await fetch("http://localhost:8080/store/add-in-basket/" + product.productDto.id, {
                method: "POST"
            });

            let response = await fetch("http://localhost:8080/store/product-in-basket/" + product.productDto.id);
            let respJson = await response.json();
            prodQuantity.innerText = respJson.quantity;

            //await displayProductInCart();
        });
        divQuantityProduct.appendChild(btnPlus);

        div.appendChild(divQuantityProduct);
        cartContent.appendChild(div);
    });

    centerContent.appendChild(cartContent);

}

class Product {

    constructor(/*id, serialNumber, name, price*/) {
        // this.id = id;
        // this.serialNumber = serialNumber;
        // this.name = name;
        // this.price = price;
    }

}