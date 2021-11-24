var newEmail;

const update = document.getElementById('emailBtn');

update.addEventListener('click', (e) => {
    newEmail = document.getElementById('changedEmail').value;
    if(true){//email does not exist

    }
    else{
        e.preventDefault();
        alert("Email already exists");
    }
})