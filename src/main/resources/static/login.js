let registerBtn = document.getElementById('register_btn');
let loginBtn = document.getElementById('login_btn');
registerBtn.addEventListener('click', (e) => {
    let h1 = document.getElementById('log_reg');
    h1.innerHTML = ``;
    h1.innerText = "Регистрация";
    let form = document.getElementById('log_reg_form');
    form.setAttribute('action', '/store/user-registration');

    let logRegBtn = document.getElementById('log_reg_btn');

    let label = document.createElement('label');
    label.setAttribute('for', 'name');
    label.innerHTML = "Имя: ";
    form.insertBefore(label, logRegBtn);

    let input = document.createElement('input');
    input.setAttribute('type', 'text');
    input.setAttribute('name', 'name');
    input.setAttribute('id', 'name');
    form.insertBefore(input, logRegBtn);
});
loginBtn.addEventListener('click', (e) => {
    let h1 = document.getElementById('log_reg');
    h1.innerHTML = ``;
    h1.innerText = "Вход";
    let form = document.getElementById('log_reg_form');
    form.setAttribute('action', '/login');
});