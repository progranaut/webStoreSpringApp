
var hdr = document.querySelector("header");

hdr.innerHTML = `
      <div>
          <p>WebStore</p>
      </div>
      <div>
          <a href="http://localhost:8080/home">Главная</a>
          <a href="http://localhost:8080/contacts">Контакты</a>
      </div>
      <div>
          <div id="user"></div>
      </div>
`;

var cnt = document.getElementById("content");

cnt.innerHTML = `
      <div id="left_content">dfgdfg</div>
      <div id="center_content">dfgdfgdf</div>
      <div id="right_content">dfgdfg</div>
`;

var user = document.getElementById("user");

displayUser(user);

async function displayUser() {
    var request = new Request("http://localhost:8080/store/current-user-name");

    let response = await fetch(request);
    let sts = response.redirected;
    let txt = await response.text();
    console.log(sts);
    if (!sts) {
        let cartBtn = document.createElement('button');
        cartBtn.innerHTML = "Корзина";
        cartBtn.addEventListener('click', (e) => {
            displayProductInCart();
        });
        user.appendChild(cartBtn);

        let userHref = document.createElement('a');
        userHref.setAttribute('href', 'http://localhost:8080/user');
        userHref.innerHTML = txt;
        user.appendChild(userHref);
    } else {
        user.innerHTML = '<a href="http://localhost:8080/login">Войти</a>';
    }
}

// fetch(request).then(response=> response.text())
//     .then(text => {
//         console.log(stts);
//         if (text === "") {
//             user.innerHTML = '<a href="http://localhost:8080/login">Войти</a>';
//         } else {
//
//             let cartBtn = document.createElement('button');
//             cartBtn.innerHTML = "Корзина";
//             cartBtn.addEventListener('click', (e) => {
//                 displayProductInCart();
//             });
//             user.appendChild(cartBtn);
//
//             let userHref = document.createElement('a');
//             userHref.setAttribute('href', 'http://localhost:8080/user');
//             userHref.innerHTML = text;
//             user.appendChild(userHref);
//
//             // let tmpHtml = user.innerHTML;
//             // user.innerHTML = `
//             //     ${tmpHtml} |
//             //     <a href="http://localhost:8080/user">${text}</a>
//             //     `;
//
//             // user.innerHTML = `
//             //     <a href="http://localhost:8080/cart">Корзина</a> |
//             //     <a href="http://localhost:8080/user">${text}</a>
//             //     `;
//         }
//     });


