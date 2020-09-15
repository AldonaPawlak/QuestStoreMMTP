const loginForm = document.querySelector("#login-form");

loginForm.addEventListener('submit', function (e) {
    e.preventDefault();
    const data = `email=${this.email.value}&password=${this.password.value}`;
    console.log(data);
    login(data);
});

function login(data) {
    fetch("http://localhost:8000/login",
        {
            credentials: 'same-origin',
            method: "POST",
            body: data
        })
        .then((response) => {
            /*console.log(response)*/
            return response.json()
        })
        .then((responseJson) => {
            console.log(responseJson);
            document.cookie = "user=" + responseJson.id + " " + responseJson.email + " " +responseJson.role;
            return responseJson;
        })
        .catch(error => console.error(error));


}