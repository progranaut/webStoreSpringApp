console.log("menu.js подключен!")

async function displayMenuUser() {

    let userInfo = document.createElement('div');

    if (arguments[0].status === 200) {

        let response = await arguments[0].json();
        console.log(response);
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
            adminHref.setAttribute('href', 'http://localhost:8080/admin');
            userInfo.appendChild(adminHref);
        }

        let cartBtn = document.createElement('button');
        cartBtn.innerHTML = "Корзина";
        cartBtn.addEventListener('click', async (e) => {
            let centerContent = document.getElementById("center_content");
            centerContent.innerHTML = ``;
            centerContent.appendChild(await displayProductInCart());
        });
        userInfo.appendChild(cartBtn);

        let userHref = document.createElement('a');
        userHref.innerHTML = userName;
        userHref.addEventListener('click', async (e) => {
            let center = document.getElementById('center_content');
            center.innerHTML = ``;
            center.appendChild(await displayUser());
        });
        userInfo.appendChild(userHref);

        let logOut = document.createElement('a');
        logOut.setAttribute('href', 'http://localhost:8080/logout');
        logOut.innerHTML = "Выйти";
        userInfo.appendChild(logOut);

    } else {

        let cartBtn = document.createElement('button');
        cartBtn.innerText = "Корзина";
        cartBtn.addEventListener('click', async (e) => {
            let centerContent = document.getElementById("center_content");
            centerContent.innerHTML = ``;
            centerContent.appendChild(await displayProductInCartNotReg());
        });
        userInfo.appendChild(cartBtn);

        let loginHref = document.createElement('a');
        loginHref.setAttribute('href', 'http://localhost:8080/login');
        loginHref.innerText = "Войти";
        userInfo.appendChild(loginHref);

    }

    return userInfo;

}