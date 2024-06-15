
let adminContent = document.getElementById('admin_content');
let productBtn = document.getElementById('product_btn');
let categoryBtn = document.getElementById('category_button');

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

productBtn.addEventListener('click', (e) => {
    adminContent.innerHTML = `
        <form id="add_product">
        
            <input type="text" name="serial_number" id="serial_number">
            <input type="text" name="name" id="name">
            <input type="text" name="price" id="price">
            <select id="categories">
            </select>
            <input type="text" name="quantity" id="quantity">
        
        </form>
    `;

    let select = document.getElementById('categories');

    fetch('http://localhost:8080/store/all-product-categories').then(async response => {
        let category = await response.json();
        let categoryArray = Array.from(category);
        categoryArray.forEach(category => {
            let option = document.createElement('option');
            option.setAttribute('data-id', category.id);
            option.setAttribute('value', category.categoryType);
            option.innerText = category.categoryType;
            select.appendChild(option);
        });
    });

    let productTable = document.createElement('table');
    productTable.classList.add('product_table');
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

    fetch('http://localhost:8080/store/all-products').then(async response => {
        let products = await response.json();
        let productsArray = Array.from(products);
        console.log(productsArray);

        productsArray.forEach(product => {
            let tr = document.createElement('tr');
            tr.innerHTML = `
            <td>${product.id}</td>
            <td>${product.serialNumber}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.categoryDto.categoryType}</td>
            <td>${product.image}</td>
            <td>${product.availability}</td>
            `;
            productTable.appendChild(tr);
        });
    });
});

