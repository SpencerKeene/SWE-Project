
var loginEmail = "";
var loginPw    = "";

const loginForm = document.getElementById('loginForm'); 

loginForm.addEventListener('submit', (e) => {
    e.preventDefault();
    loginEmail = document.getElementById('inputEmail').value;
    loginPw    = document.getElementById('inputPassword').value;
    //add validation and API
    //add a way to load to the next page if information is valid
});