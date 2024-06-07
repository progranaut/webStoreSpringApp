
document.addEventListener("DOMContentLoaded", function (e){

    displayProductInCart();

});

async function displayProductInCart() {

    var response = await fetch('http://localhost:8080/store/productInBasket');
    var productsInBasket = await response.json();

    let centerContent = document.getElementById("center_content");
    centerContent.innerHTML = ``;
    productsInBasket.forEach(product => {

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
        btn.innerText = "Удалить";
        // btn.addEventListener('click', (e) => {
        //     //console.log(div.getAttribute("data-product"));
        //     fetch("http://localhost:8080/store/add-in-basket", {
        //         method: "POST",
        //         headers: {
        //             'Content-Type': 'application/json'
        //         },
        //         body: `{
        //         "id": "${div.getAttribute("data-product")}"
        //         }`
        //     });
        // });
        div.appendChild(btn);
        centerContent.appendChild(div);

    });

}