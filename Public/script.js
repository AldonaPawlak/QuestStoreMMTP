/*
function myFunction() {
    document.getElementById("demo").innerHTML = "confirm pass";
}
*/

function showSignInForm() {
    const confirmPassword = document.querySelector('.container > input');
    confirmPassword.style.display = 'block';
}

function initShowSignInForm() {
    const signIn = document.querySelector('section > button');
    signIn.addEventListener("click", showSignInForm)
}

initShowSignInForm();

function openSlideMenu() {
    document.getElementById('menu').style.width = '250px';
}

function closeSlideMenu() {
    document.getElementById('menu').style.width = '0';
}
