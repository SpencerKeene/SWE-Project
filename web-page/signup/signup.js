//defined values
var fname = "";
var lname = "";
var email = "";
var pw    = "";
var valid = false;
const signUpForm = document.getElementById('signupForm');

signUpForm.addEventListener('submit', (e) => {
    fname = document.getElementById('fname').value;
    lname = document.getElementById('lname').value;
    email = document.getElementById('inputEmail').value;
    pw    = document.getElementById('inputPassword').value;
    //add validation and API
    if(valid == true){
        //allow and send user to their instance of calendar
    }
    else{
        e.preventDefault();
    }
    //add a way to load to the next page if information is valid
});
