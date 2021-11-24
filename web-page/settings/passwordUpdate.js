var newPassword;

const update = document.getElementById('passwordBtn');

update.addEventListener('click', (e) => {
    newPassword = document.getElementById('changedPassword').value;
    if(newPassword.length > 8){
        //update to server
        alert("password was updated");
    }
    else{
        //don't update
        alert("Password must be greater than 8 characters");
    }
})