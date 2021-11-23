//defined values
var fname = "";
var lname = "";
var email = "";
var pw    = "";

const signUpForm = document.getElementById('signupForm');

signUpForm.addEventListener('submit', (e) => {
    e.preventDefault();
    fname = document.getElementById('fname').value;
    lname = document.getElementById('lname').value;
    email = document.getElementById('inputEmail').value;
    pw    = document.getElementById('inputPassword').value;
    //add validation and API
    //add a way to load to the next page if information is valid
});
