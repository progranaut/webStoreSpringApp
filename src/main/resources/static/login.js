let registerBtn = document.getElementById('register_btn');
let loginBtn = document.getElementById('login_btn');
let logReg = document.getElementById('log_reg');
registerBtn.addEventListener('click', (e) => {

    logReg.innerHTML = `
        <h1 id="log_reg_zag">Регистрация</h1>
        <form id="log_reg_form" action="/store/user-registration" method="post">
            <label for="name">Имя: </label>
            <input type="text" name="name" id="name">
            <label for="username">Логин: </label>
            <input type="text" name="username" id="username">
            <label for="password">Пароль: </label>
            <input type="password" name="password" id="password">
            <button id="log_reg_btn" type="submit">Зарегистрировать</button>
        </form>
    `;

    // let h1 = document.getElementById('log_reg');
    // h1.innerHTML = ``;
    // h1.innerText = "Регистрация";
    // let form = document.getElementById('log_reg_form');
    // form.setAttribute('action', '/store/user-registration');
    //
    // let logRegBtn = document.getElementById('log_reg_btn');
    //
    // let label = document.createElement('label');
    // label.setAttribute('for', 'name');
    // label.innerHTML = "Имя: ";
    // form.insertBefore(label, logRegBtn);
    //
    // let input = document.createElement('input');
    // input.setAttribute('type', 'text');
    // input.setAttribute('name', 'name');
    // input.setAttribute('id', 'name');
    // form.insertBefore(input, logRegBtn);
});
loginBtn.addEventListener('click', (e) => {

    logReg.innerHTML = `
        <h1 id="log_reg_zag">Войти</h1>
        <form id="log_reg_form" action="/login" method="post">
            <label htmlFor="username">Логин: </label>
            <input type="text" name="username" id="username">
            <label htmlFor="password">Пароль: </label>
            <input type="password" name="password" id="password">
            <button id="log_reg_btn" type="submit">Войти</button>
        </form>
    `;


    // let h1 = document.getElementById('log_reg');
    // h1.innerHTML = ``;
    // h1.innerText = "Вход";
    // let form = document.getElementById('log_reg_form');
    // form.setAttribute('action', '/login');
});