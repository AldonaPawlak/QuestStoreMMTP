const loginForm = document.querySelector("#login-form");

loginForm.addEventListener('submit', function (e) {
    e.preventDefault();
    const data = `email=${this.email.value}&password=${this.password.value}`;
    console.log(data);
    login(data);
});

function login(data) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/x-www-form-urlencoded");
    fetch("http://localhost:8000/login",
        {
/*            mode: 'no-cors',*/
  /*          credentials: 'same-origin',*/
            method: "POST",
            body: data,
            headers: myHeaders
        })
        .then(function (response) {
            console.log(response.status);
            if (response.status === 404) {
                alert(response.text());
            }
            console.log(response);
        }).catch(function (error) {
        // user NOT authenticated, server return different status than 200-299
        console.log(error)});
}