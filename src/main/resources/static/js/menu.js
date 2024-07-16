console.log("menu.js подключен!")

async function displayMenuUser() {

    let userInfo = document.createElement('div');

    if (arguments[0].status === 200) {

        let response = await arguments[0].json();
        let userName = response.name;
        let roleUser = false;
        let roleAdmin = false;

        response.roles.forEach(role => {
            if (role.roleType === "ROLE_ADMIN") {
                roleAdmin = true;
            }
            if (role.roleType === "ROLE_USER") {
                roleUser = true;
            }
        });

        if (roleAdmin) {
            let adminHref = document.createElement('a');
            adminHref.innerText = "AdminPanel";
            adminHref.classList.add('menu_btn');
            adminHref.classList.add('admin_btn');
            adminHref.setAttribute('href', 'http://localhost:8080/admin');
            userInfo.appendChild(adminHref);
        }

        let userHref = document.createElement('button');
        userHref.innerHTML = userName;
        userHref.classList.add('menu_btn');
        userHref.classList.add('menu_user_name_btn');
        userHref.addEventListener('click', async (e) => {
            let center = document.getElementById('center_content');
            center.innerHTML = ``;
            center.appendChild(await displayUser());
            center.appendChild(await displayUserOrders());
        });
        userInfo.appendChild(userHref);

        let cartBtn = document.createElement('button');
        cartBtn.innerHTML = "Корзина";
        cartBtn.classList.add('menu_btn');
        cartBtn.classList.add('menu_cart_btn');
        cartBtn.addEventListener('click', async (e) => {
            let centerContent = document.getElementById("center_content");
            centerContent.innerHTML = ``;
            centerContent.appendChild(await displayProductInCart());
        });
        userInfo.appendChild(cartBtn);

        let logOut = document.createElement('a');
        logOut.setAttribute('href', 'http://localhost:8080/logout');
        logOut.classList.add('menu_btn');
        logOut.classList.add('exit_btn');
        logOut.innerHTML = "Выйти";
        userInfo.appendChild(logOut);

    } else {

        let cartBtn = document.createElement('button');
        cartBtn.innerText = "Корзина";
        cartBtn.classList.add('menu_btn');
        cartBtn.classList.add('menu_cart_btn');
        cartBtn.addEventListener('click', async (e) => {
            let centerContent = document.getElementById("center_content");
            centerContent.innerHTML = ``;
            centerContent.appendChild(await displayProductInCartNotReg());
        });
        userInfo.appendChild(cartBtn);

        let loginHref = document.createElement('a');
        loginHref.setAttribute('href', 'http://localhost:8080/login');
        loginHref.classList.add('menu_btn');
        loginHref.classList.add('login_href');
        loginHref.innerText = "Войти";
        userInfo.appendChild(loginHref);

    }

    return userInfo;

}