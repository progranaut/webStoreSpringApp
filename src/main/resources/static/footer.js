let footer = document.getElementById('footer');

footer.innerHTML = `
    <div id="stack">
        <p>Stack:</p>
        <ul>
            <li>PostgreSQL</li>
            <li>Go</li>
            <li>TelegramAPI</li>
        </ul>
        <ul>
            <li>JavaScript</li>
            <li>HTML</li>
            <li>CSS</li>
        </ul>
        <ul>
            <li>Java</li>
            <li>Spring</li>
            <li>Maven</li>
        </ul>
    </div>
    <div id="store_info">
        <p>Дипломная работа на тему "Веб магазин"</p>
        <p>Группа Java31 Направление Java-Разработчик</p>
        <p>Ивлев Александр</p>
    </div>
    <div id="call_back_div">
        <form id="call_back_form">
            <input type="text" name="name" id="name" placeholder="Ваше имя">
            <input type="text" name="phone" id="phone" placeholder="Ваш телефон">
            <input type="text" name="message" id="message" placeholder="Краткое описание">
        </form>
    </div>
`;

let callOrderBtn = document.createElement('button');
callOrderBtn.innerText = "Заказать звонок";
callOrderBtn.setAttribute('id', 'call_order_btn');
callOrderBtn.addEventListener('click', (e) => {
    e.preventDefault();
    let form = document.getElementById('call_back_form');
    let callBack = {
        name: form.name.value,
        phone: form.phone.value,
        message: form.message.value
    }
    fetch('http://localhost:8080/message/call-order', {
        method: "POST",
        headers: {
            "Content-type":"application/json"
        },
        body: JSON.stringify(callBack)
    })
});

let callBackForm = document.getElementById('call_back_form');
callBackForm.appendChild(callOrderBtn);