async function displayUser () {

    let response = await fetch('http://localhost:8080/store/current-user');
    let user = await response.json();

    console.log(user);

    let centerContent = document.getElementById('center_content');
    centerContent.innerHTML = `
        
        <div id="user_content" class="form_content">
            <form id="user_form" class="form">
            
                <label for="name">Имя:</label>
                <input type="text" value="${user.name}" name="name" id="name">
                
                <label for="email">email:</label>
                <input type="text" value="${user.email}" name="email" id="email">
                
                <label for="phone">Телефон:</label>
                <input type="text" value="${user.phoneNumber}" name="phone" id="phone">
                
                <label for="address">Адрес:</label>
                <input type="text" value="${user.address}" name="address" id="address">

<!--                <button id="changeUser">Сохранить</button>-->
                
            </form>
        </div>
    `;

    let saveBtn = document.createElement('button');
    saveBtn.addEventListener('click', (e) => {
        e.preventDefault();
        let form = document.getElementById('user_form');
        console.log(form.phone.value);
        let userInfo = {
            id: user.id,
            name: form.name.value,
            phoneNumber: form.phone.value,
            email: form.email.value,
            address: form.address.value,
            securityUserDto: {
                id: "7940ea8e-19ba-4abc-993e-2b3e4fa87415"
            }
        }
        fetch('http://localhost:8080/users/change', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userInfo)
        });
    });
    saveBtn.innerText = 'Сохранить';
    let form = document.getElementById('user_form');
    form.appendChild(saveBtn);

}