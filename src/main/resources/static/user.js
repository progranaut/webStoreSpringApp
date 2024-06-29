async function displayUser () {

    let response = await fetch('http://localhost:8080/store/current-user');
    let user = await response.json();

    let userContent = document.createElement('div');
    userContent.classList.add('user_content')

    let infoHead = document.createElement('div');
    infoHead.innerHTML = '<h2>Личная информация: </h2>';
    userContent.appendChild(infoHead);

    let userInfo = document.createElement('div');
    userInfo.classList.add('form_user_info');
    userContent.appendChild(userInfo);

    userInfo.innerHTML = `
        <form id="user_form" class="form">            
            <label for="name">Имя:</label>
            <input type="text" value="${user.name}" name="name" id="name">
                
            <label for="email">email:</label>
            <input type="text" value="${user.email}" name="email" id="email">
                
            <label for="phone">Телефон:</label>
            <input type="text" value="${user.phoneNumber}" name="phone" id="phone">
                
            <label for="address">Адрес:</label>
            <input type="text" value="${user.address}" name="address" id="address">
        </form>
    `;

    let saveBtn = document.createElement('button');
    saveBtn.addEventListener('click', (e) => {
        let form = document.getElementById('user_form');
        console.log(form.phone.value);
        let userInfo = {
            id: user.id,
            name: form.name.value,
            phoneNumber: form.phone.value,
            email: form.email.value,
            address: form.address.value,
            securityUserDto: {
                id: user.securityUserDto.id
            }
        }

        // TODO Организовать определение security_id по user_id на бэкэ и не гонять sec_id на фронт

        fetch('http://localhost:8080/users/change', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userInfo)
        });
    });
    saveBtn.innerText = 'Сохранить';
    userInfo.appendChild(saveBtn);

    return userContent;
}

async function displayUserOrders() {
    let userContent = document.createElement('div');
    let ordersResponse = await fetch('http://localhost:8080/orders/all-current-user-orders');
    let orders = await ordersResponse.json();
    let ordersArray = Array.from(orders);
    let ordersHead = document.createElement('div');
    ordersHead.innerHTML = '<h2>Оформленные заказы</h2>';
    userContent.appendChild(ordersHead);
    ordersArray.forEach(order => {
        let orderDiv = document.createElement('div');
        orderDiv.classList.add('user_order');
        orderDiv.innerHTML = `
            <div class="order_date_number">
                <p>Дата заказа: ${order.date.split('T')[0]} ${order.date.split('T')[1].split('.')[0]}</p>
                <p>Номер заказа: ${order.orderNumber}</p>
            </div>
        `;

        let orderTable = document.createElement('table');
        orderTable.classList.add('order_table');
        let relationHead = document.createElement('tr');
        relationHead.classList.add('relation_head');
        relationHead.innerHTML = `
            <td class="td_prod_name">Название</td>
            <td class="td_prod_price">Цена</td>
            <td class="td_prod_rel">Количество</td>
        `;
        orderTable.appendChild(relationHead);
        orderDiv.appendChild(orderTable);
        let orderRelationArray = Array.from(order.relations);
        orderRelationArray.forEach(relation => {
            let relationTr = document.createElement('tr');
            //relationTr.classList.add('order_relation');
            relationTr.innerHTML = `
                <td class="td_prod_name">${relation.productDto.name}</td>
                <td class="td_prod_price">${relation.productDto.price}</td>
                <td class="td_prod_rel">${relation.relation}</td>
            `;
            orderTable.appendChild(relationTr);
        });
        userContent.appendChild(orderDiv);
    });
    return userContent;
}