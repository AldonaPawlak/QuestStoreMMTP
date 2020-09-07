
const form = document.querySelector("#login-form");

form.addEventListener('submit', function (e) {
    e.preventDefault();
    //name=Agnieszka&surname=Kowalska&email=a.kowalska%40codecool.com
    const data = `name=${this.name.value}&email=${this.email.value}`;
    console.log(data);
    setStudent(data);
});

function setStudent(data) {
    fetch("http://localhost:8050/register",
        {
            mode: 'no-cors',
            method: "POST",
            body: data
        })
        .then(function (response) {
            console.log(response);
        });
}