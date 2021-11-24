var newEmail;

const update = document.getElementById('emailBtn');

update.addEventListener('click', (e) => {
    newEmail = document.getElementById('changedEmail').value;
    if(true){//email does not exist
        //update to server
        alert("Email has been updated");
    }
    else{
        e.preventDefault();
        alert("Email already exists");
    }
})