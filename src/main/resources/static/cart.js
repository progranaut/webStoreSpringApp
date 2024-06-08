
// document.addEventListener("DOMContentLoaded", function (e){
//
//     displayProductInCart();
//
// });

async function displayProductInCart() {

    var response = await fetch('http://localhost:8080/store/productInBasket');
    var productsInBasket = await response.json();

    let centerContent = document.getElementById("center_content");
    centerContent.innerHTML = ``;

    let cartContent = document.createElement("div");
    cartContent.classList.add('cart_content');

    let productArray = Array.from(productsInBasket);
    let prdObjArray = new Array();

    let prd = new Product();
    prd.id = productArray[0].id;
    prd.serialNumber = productArray[0].serialNumber;
    prd.name = productArray[0].name;
    prd.price = productArray[0].price;
    prd.quantity = 1;
    prdObjArray.push(prd);

    for (let i = 1; i < productArray.length; i++) {
        let index = -1;
        for (let j = 0; j < prdObjArray.length; j++) {
            if (prdObjArray[j].id === productArray[i].id) {
                index = j;
            }
        }
        if (index < 0) {
            let prdct = new Product();
            prdct.id = productArray[i].id;
            prdct.serialNumber = productArray[i].serialNumber;
            prdct.name = productArray[i].name;
            prdct.price = productArray[i].price;
            prdct.quantity = 1;
            prdObjArray.push(prdct);
        } else {
            prdObjArray[index].quantity++;
        }
    }

    prdObjArray.forEach(product => {

        let div = document.createElement("div");
        div.classList.add('cart_product');
        div.setAttribute('data-product', product.id);

        let divProdImg = document.createElement("div");
        divProdImg.classList.add('product_img');
        divProdImg.innerHTML = `
        <img src="${product.imageUrl}">
        `;
        div.appendChild(divProdImg);

        let divProdInfo = document.createElement("div");
        divProdInfo.classList.add('product_info');
        divProdInfo.innerHTML = `
        <p>${product.id}</p>
        <p>${product.serialNumber}</p>
        <p>${product.name}</p>
        <p>${product.price}</p>
        `;
        div.appendChild(divProdInfo);

        let divQuantityProduct = document.createElement("div");
        divQuantityProduct.classList.add('product_quantity');

        let btnMinus = document.createElement('button');
        btnMinus.classList.add('minus_quantity');
        btnMinus.innerText = "-";
        btnMinus.addEventListener('click', async (e) => {
            let request = new Request("http://localhost:8080/store/delete-product/" + product.id, {
                method: "DELETE"
            });
            await fetch(request);
            await displayProductInCart();
        });
        divQuantityProduct.appendChild(btnMinus);


        let prodQuantity = document.createElement('p');
        prodQuantity.innerText = product.quantity;
        divQuantityProduct.appendChild(prodQuantity);

        let btnPlus = document.createElement("button");
        btnPlus.classList.add('plus_quantity');
        btnPlus.innerText = "+";
        btnPlus.addEventListener('click', async (e)=>{
            await fetch("http://localhost:8080/store/add-in-basket/" + product.id, {
                method: "POST"
            });
            await displayProductInCart();
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