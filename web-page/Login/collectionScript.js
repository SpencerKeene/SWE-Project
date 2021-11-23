//defined values
var fname = "";
var lname = "";
var email = "";
var pw    = "";

const signUpBtn = document.getElementById('signupForm');

signUpBtn.addEventListener('submit', (e) => {
    fname = document.getElementById('fname').value;
    lname = document.getElementById('lname').value;
    email = document.getElementById('inputEmail').value;
    pw    = document.getElementById('inputPassword').value;
    alert(fname + ", " + lname + ", " + email + ", " + pw);
    
})

const loginBtn = document.getElementById('loginForm'); 
//add event listener