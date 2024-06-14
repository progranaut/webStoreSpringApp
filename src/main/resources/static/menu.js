

async function displayMenuUser() {

    let userInfo = document.createElement('div');

    if (!arguments[0].redirected) {

        let txt = await arguments[0].text();

        let cartBtn = document.createElement('button');
        cartBtn.innerHTML = "Корзина";
        cartBtn.addEventListener('click', async (e) => {
            let centerContent = document.getElementById("center_content");
            centerContent.innerHTML = ``;
            centerContent.appendChild(await displayProductInCart());
        });
        userInfo.appendChild(cartBtn);

        let userHref = document.createElement('a');
        userHref.innerHTML = txt;
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

        userInfo.innerHTML = '<a href="http://localhost:8080/login">Войти</a>';

    }

    return userInfo;

}



// ПРЕДПОСЛЕДНЯЯ РАБОЧАЯ ВЕРСИЯ!!!
//
// let currentUser = document.getElementById("user");
//
// fetch("http://localhost:8080/store/current-user-name").then(async (response) => {
//
//     let txt = await response.text();
//
//     if (!response.redirected) {
//         let cartBtn = document.createElement('button');
//         cartBtn.innerHTML = "Корзина";
//         cartBtn.addEventListener('click', async (e) => {
//             let centerContent = document.getElementById("center_content");
//             centerContent.innerHTML = ``;
//             centerContent.appendChild(await displayProductInCart());
//         });
//         currentUser.appendChild(cartBtn);
//
//         let userHref = document.createElement('a');
//         userHref.innerHTML = txt;
//         userHref.addEventListener('click', async (e) => {
//             let center = document.getElementById('center_content');
//             center.innerHTML = ``;
//             center.appendChild(await displayUser());
//         });
//         currentUser.appendChild(userHref);
//
//         let logOut = document.createElement('a');
//         logOut.setAttribute('href', 'http://localhost:8080/logout');
//         logOut.innerHTML = "Выйти";
//         currentUser.appendChild(logOut);
//     } else {
//         currentUser.innerHTML = '<a href="http://localhost:8080/login">Войти</a>';
//     }
//
// });




// console.log(sts);
// if (!sts) {
//     let cartBtn = document.createElement('button');
//     cartBtn.innerHTML = "Корзина";
//     cartBtn.addEventListener('click', async (e) => {
//         let centerContent = document.getElementById("center_content");
//         centerContent.innerHTML = ``;
//         centerContent.appendChild(await displayProductInCart());
//     });
//     currentUser.appendChild(cartBtn);
//
//     let userHref = document.createElement('a');
//     userHref.innerHTML = txt;
//     userHref.addEventListener('click', async (e) => {
//         let center = document.getElementById('center_content');
//         center.innerHTML = ``;
//         center.appendChild(await displayUser());
//     });
//     currentUser.appendChild(userHref);
//
//     let logOut = document.createElement('a');
//     logOut.setAttribute('href', 'http://localhost:8080/logout');
//     logOut.innerHTML = "Выйти";
//     currentUser.appendChild(logOut);
// } else {
//     currentUser.innerHTML = '<a href="http://localhost:8080/login">Войти</a>';
// }




// let currentUser = document.getElementById("user");
//
// displayMenuUser();
//
// async function displayMenuUser() {
//
//     let request = new Request("http://localhost:8080/store/current-user-name");
//     let response = await fetch(request);
//     let txt = await response.text();
//     let sts = response.redirected;
//     console.log(sts);
//     if (!sts) {
//         let cartBtn = document.createElement('button');
//         cartBtn.innerHTML = "Корзина";
//         cartBtn.addEventListener('click', async (e) => {
//             let centerContent = document.getElementById("center_content");
//             centerContent.innerHTML = ``;
//             centerContent.appendChild(await displayProductInCart());
//         });
//         currentUser.appendChild(cartBtn);
//
//         let userHref = document.createElement('a');
//         userHref.innerHTML = txt;
//         userHref.addEventListener('click', async (e) => {
//             let center = document.getElementById('center_content');
//             center.innerHTML = ``;
//             center.appendChild(await displayUser());
//         });
//         currentUser.appendChild(userHref);
//
//         let logOut = document.createElement('a');
//         logOut.setAttribute('href', 'http://localhost:8080/logout');
//         logOut.innerHTML = "Выйти";
//         currentUser.appendChild(logOut);
//     } else {
//         currentUser.innerHTML = '<a href="http://localhost:8080/login">Войти</a>';
//     }
//
// }



