
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

var request = new Request("http://localhost:8080/users/current-user-name");
var user = document.getElementById("user");
fetch(request).then(response=> response.text())
    .then(text => {
        if (text === "") {
            user.innerHTML = '<a href="http://localhost:8080/login">Войти</a>';
        } else {

            let cartBtn = document.createElement('button');
            cartBtn.innerHTML = "Корзина";
            cartBtn.addEventListener('click', (e) => {
                displayProductInCart();
            });
            user.appendChild(cartBtn);

            let userHref = document.createElement('a');
            userHref.setAttribute('href', 'http://localhost:8080/user');
            userHref.innerHTML = text;
            user.appendChild(userHref);

            // let tmpHtml = user.innerHTML;
            // user.innerHTML = `
            //     ${tmpHtml} |
            //     <a href="http://localhost:8080/user">${text}</a>
            //     `;

            // user.innerHTML = `
            //     <a href="http://localhost:8080/cart">Корзина</a> |
            //     <a href="http://localhost:8080/user">${text}</a>
            //     `;
        }
    });




