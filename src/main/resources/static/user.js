async function displayUser () {

    let response = await fetch('http://localhost:8080/store/current-user');
    let user = await response.json();

    console.log(user);

    let centerContent = document.getElementById('center_content');
    centerContent.innerHTML = `
        
        <div id="user_content" class="form_content">
            <form class="form">
            
                <label for="name">Имя:</label>
                <input type="text" value="${user.name}" name="name" id="name">
                
                <label for="email">email:</label>
                <input type="text" value="${user.email}" name="email" id="email">
                
                <label for="phone">Телефон:</label>
                <input type="text" value="${user.phoneNumber}" name="phone" id="phone">
                
                <label for="address">Адрес:</label>
                <input type="text" value="${user.address}" name="address" id="address">
                
                <button id="changeUser">Сохранить</button>
                
            </form>
        </div>
        
    `;
}