function myFunction() {
    document.getElementById("demo").innerHTML = "confirm pass";
}

function showSignInForm() {
    const confirmPassword = document.querySelector('.container > input');
    confirmPassword.style.display = 'block';
}

const signIn = document.querySelector('.container > button');
signIn.addEventListener("click", showSignInForm)