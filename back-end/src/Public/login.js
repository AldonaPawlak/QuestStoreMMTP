const loginForm = document.querySelector("#login-form");

loginForm.addEventListener('submit', function (e) {
    e.preventDefault();
    const data = `email=${this.email.value}&password=${this.password.value}`;
    console.log(data);
    login(data);
});

function login(data) {
    fetch("http://localhost:8050/login",
        {
            mode: 'no-cors',
            method: "POST",
            body: data
        })
        .then(function (response) {
            console.log(response.status);
            if (response.status === 404) {
                alert(response.text());
            }
            console.log(response);
        });
}