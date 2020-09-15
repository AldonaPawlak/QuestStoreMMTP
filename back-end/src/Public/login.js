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
        .then ((responseJson) => {
            if (responseJson.role === "Student") {
                window.location.href = "http://localhost:63342/back-end/src/Public/websiteLogic/students.html?_ijt=q648jnvbkhk07ins1joab5utnn";}
            else if (responseJson.role === "Mentor") {
                window.location.href = "http://localhost:63342/back-end/src/Public/websiteLogic/mentors.html?_ijt=ob378oqekqtl63nv30sgik9p3q";
            }
            else if (responseJson.role === "Creep") {
                window.location.href = "http://localhost:63342/back-end/src/Public/websiteLogic/mentorView.html?_ijt=q648jnvbkhk07ins1joab5utnn";
            }
        })
        .catch(error => console.error(error));


}