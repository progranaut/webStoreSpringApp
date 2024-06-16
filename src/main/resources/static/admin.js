
let adminContent = document.getElementById('admin_content');

let productBtn = document.getElementById('product_btn');
let categoryBtn = document.getElementById('category_button');
let usersBtn = document.getElementById('user_btn');

//Страница категории
categoryBtn.addEventListener('click', (e)=>{
    adminContent.innerHTML = `
        <form id="add_category">
            <input type="text" value="" name="name" id="name">
        </form>
    `;
    let addCategoryBtn = document.createElement('button');
    addCategoryBtn.innerText = "Добавить";
    addCategoryBtn.addEventListener('click', async (e)=>{
        let form = document.getElementById('add_category');
        let category = {
            categoryType: form.name.value
        }
        console.log(category);
        await fetch('http://localhost:8080/category/add', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(category)
        });
    });
    adminContent.appendChild(addCategoryBtn);
});


//Страница товары
productBtn.addEventListener('click', async (e) => {

    adminContent.innerHTML = `
        <h3>Добавить товар: </h3>
        <form id="add_product">
            <label for="serial_number">Серийный номер: </label>
            <input type="text" name="serial_number" id="serial_number">
            <label for="name">Имя: </label>
            <input type="text" name="name" id="name">
            <label for="price">Цена: </label>
            <input type="text" name="price" id="price">
            <label for="categories">Категория: </label>
            <select id="categories"></select>
            <label for="quantity">Количество: </label>
            <input type="text" name="availability" id="availability">
            <button id="add_product_btn">Добавить</button>
        </form>
        <h3>Товары: </h3>
    `;

    let productTable = document.createElement('table');
    productTable.classList.add('content_table');
    productTable.innerHTML = `
            <tr>
                <td>id</td>
                <td>Серийный номер</td>
                <td>Имя</td>
                <td>Цена</td>
                <td>Категория</td>
                <td>Картинка</td>
                <td>Количество</td>
            </tr>
        `;
    adminContent.appendChild(productTable);

    let addProductBtn = document.getElementById('add_product_btn');
    addProductBtn.addEventListener('click', async (e)=>{

        e.preventDefault();

        let form = document.getElementById('add_product');

        let product = {
            serialNumber: form.serial_number.value,
            name: form.name.value,
            price: form.price.value,
            categoryDto: {
                id: "",
                categoryType: form.categories.value
            },
            availability: form.availability.value
        }

        console.log(product);

        await fetch('http://localhost:8080/products/add', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(product)
        }).then(async response => {
            let product = await response.json();
            console.log(product);
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td class="product_id_td">${product.id}</td>
                <td>${product.serialNumber}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.categoryDto.categoryType}</td>
                <td>img</td>
                <td>${product.availability}</td>
                <td>Добавлено</td>
            `;
            productTable.firstElementChild.appendChild(tr);
        });
    });


    let select = document.getElementById('categories');
    select.replaceWith(await getCategories());

    // fetch('http://localhost:8080/store/all-product-categories').then(async response => {
    //     let category = await response.json();
    //     let categoryArray = Array.from(category);
    //     categoryArray.forEach(category => {
    //         let option = document.createElement('option');
    //         option.setAttribute('data-id', category.id);
    //         option.setAttribute('value', category.categoryType);
    //         option.innerText = category.categoryType;
    //         select.appendChild(option);
    //     });
    // });


    fetch('http://localhost:8080/store/all-products').then(async response => {
        let products = await response.json();
        let productsArray = Array.from(products);
        console.log(productsArray);

        productsArray.forEach(product => {
            let tr = document.createElement('tr');
            tr.innerHTML = `
            <td class="product_id_td">${product.id}</td>
            <td>${product.serialNumber}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.categoryDto.categoryType}</td>
            <td>${product.image}</td>
            <td>${product.availability}</td>
            `;
            let td = document.createElement('td');
            let redactBtn = document.createElement('button');
            redactBtn.innerText = "Редактировать";
            redactBtn.addEventListener('click', async (e) => {
                let tr = td.parentElement;
                let tds = tr.children;
                let tdsArray = Array.from(tds);
                for (let i = 1; i < tdsArray.length; i++) {
                    if (i === 4) {
                        let tmp = tdsArray[i].innerText;
                        tdsArray[i].innerHTML = ``;
                        let select = await getCategories(tmp);
                        tdsArray[i].appendChild(select);
                    } else {
                        let tmp = tdsArray[i].innerText;
                        let input = document.createElement('input');
                        input.value = tmp;
                        tdsArray[i].innerHTML = ``;
                        tdsArray[i].appendChild(input);
                    }
                    if (i === 7) {
                        tdsArray[i].innerHTML = ``;
                        let saveBtn = document.createElement('button');
                        saveBtn.innerText = "Сохранить";
                        saveBtn.addEventListener('click', (e)=>{
                            let saveProduct = {
                                id: tdsArray[0].innerText,
                                serialNumber: tdsArray[1].firstElementChild.value,
                                name: tdsArray[2].firstElementChild.value,
                                price: tdsArray[3].firstElementChild.value,
                                categoryDto: {
                                    id: "",
                                    categoryType: tdsArray[4].firstElementChild.value
                                },
                                imageUrl: tdsArray[5].firstElementChild.value,
                                availability: tdsArray[6].firstElementChild.value
                            }
                            console.log(saveProduct);
                            fetch('http://localhost:8080/products/change', {
                                method: "POST",
                                headers: {
                                    "Content-Type": "application/json"
                                },
                                body: JSON.stringify(saveProduct)
                            }).then(async response=>{
                                let product = await response.json();
                                console.log(product);
                                tdsArray[0].innerText = product.id;
                                tdsArray[1].innerText = product.serialNumber;
                                tdsArray[2].innerText = product.name;
                                tdsArray[3].innerText = product.price;
                                tdsArray[4].innerText = product.categoryDto.categoryType;
                                tdsArray[5].innerText = product.imageUrl;
                                tdsArray[6].innerText = product.availability;
                                tdsArray[7].innerHTML = ``;
                                tdsArray[7].appendChild(redactBtn);
                            });
                        });
                        tdsArray[i].appendChild(saveBtn);
                    }
                }
                console.log(tdsArray);
            });
            td.appendChild(redactBtn);
            tr.appendChild(td);
            productTable.firstElementChild.appendChild(tr);
        });
    });
});

async function getCategories() {
    let select = document.createElement('select');
    select.setAttribute('id', 'categories');
    let response = await fetch('http://localhost:8080/store/all-product-categories');
    let category = await response.json();
    let categoryArray = Array.from(category);
    categoryArray.forEach(category => {
        let option = document.createElement('option');
        option.setAttribute('data-id', category.id);
        option.setAttribute('value', category.categoryType);
        if (arguments[0] != null) {
            if (category.categoryType === arguments[0]) {
                option.setAttribute('selected', arguments[0]);
            }
        }
        option.innerText = category.categoryType;
        select.appendChild(option);
    });
    return select;
}


// Страница пользователи
usersBtn.addEventListener('click', async (e) => {
    let userTable = document.createElement('table');
    userTable.classList.add('content_table');
    userTable.innerHTML = `
        <tr>
            <td>Id</td>
            <td>Имя</td>
            <td>email</td>
            <td>Адрес</td>
            <td>Телефон</td>
        </tr>
    `;
    fetch('http://localhost:8080/users/all').then( async response => {
        let users = await response.json();
        let usersArray = Array.from(users);
        console.log(usersArray);
        usersArray.forEach(user => {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.address}</td>
                <td>${user.phoneNumber}</td>
            `;
            let td = document.createElement('td');
            let userOrderBtn = document.createElement('button');
            userOrderBtn.setAttribute('data-id', user.id);
            userOrderBtn.innerText = "Заказы";
            userOrderBtn.addEventListener('click', (e) => {
                let request = new Request("http://localhost:8080/orders/all-user-order/" + userOrderBtn.getAttribute('data-id'));
                fetch(request).then(async response=>{
                    let orders = await response.json();
                    let ordersArray = Array.from(orders);
                    console.log(orders);
                    console.log(ordersArray);
                    //
                    
                });
            });
            td.appendChild(userOrderBtn);
            tr.appendChild(td);
            userTable.firstElementChild.appendChild(tr);
        });
    });
    adminContent.innerHTML = ``;
    adminContent.appendChild(userTable);
});