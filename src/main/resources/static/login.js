let registerBtn = document.getElementById('register_btn');
let loginBtn = document.getElementById('login_btn');
let logReg = document.getElementById('log_reg');
registerBtn.addEventListener('click', (e) => {

    logReg.innerHTML = `
        <div class="form_content">
            <form id="log_reg_form" class="form" action="/store/user-registration" method="post">
                 <label for="name">Имя: </label>
                 <input type="text" name="name" id="name">
                 <label for="username">Логин: </label>
                 <input type="text" name="username" id="username">
                 <label for="password">Пароль: </label>
                 <input type="password" name="password" id="password">
                 <button id="log_btn" type="submit">Зарегистрировать</button>
            </form>
        </div>
    `;

});
loginBtn.addEventListener('click', (e) => {

    logReg.innerHTML = `
        <div class="form_content">
            <form id="log_reg_form" class="form" action="/login" method="post">
                <label htmlFor="username">Логин: </label>
                <input type="text" name="username" id="username">
                <label htmlFor="password">Пароль: </label>
                <input type="password" name="password" id="password">
                <button id="reg_btn" type="submit">Войти</button>
            </form>
        </div>
    `;

});