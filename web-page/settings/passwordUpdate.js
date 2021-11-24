var newPassword;

const update = document.getElementById('passwordBtn');

update.addEventListener('click', (e) => {
    newPassword = document.getElementById('changedPassword').value;
    if(newPassword.length > 8){
        alert("password was updated");
    }
    else{
        alert("Password must be greater than 8 characters");
    }
})