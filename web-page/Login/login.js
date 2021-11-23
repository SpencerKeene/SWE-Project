
var loginEmail = "";
var loginPw    = "";
var valid      = false;
const loginForm = document.getElementById('loginForm'); 

loginForm.addEventListener('submit', (e) => {
    e.preventDefault();
    loginEmail = document.getElementById('inputEmail').value;
    loginPw    = document.getElementById('inputPassword').value;
    //add validation and API
    if(valid == true){
        //allow and send user to their instance of calendar
    }
    else{
        e.preventDefault();
    }
    //add a way to load to the next page if information is valid
});