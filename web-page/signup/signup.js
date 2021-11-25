//defined values
var fname = "";
var lname = "";
var email = "";
var pw = "";
var valid = false;
const signUpForm = document.getElementById("signupForm");

signUpForm.addEventListener("submit", (e) => {
  e.preventDefault();
  fname = document.getElementById("fname").value;
  lname = document.getElementById("lname").value;
  email = document.getElementById("inputEmail").value;
  pw = document.getElementById("inputPassword").value;
  //add validation and API
  (async () => {
    try {
      const response = await fetch("http://localhost:8080/users/login", {
        method: "POST",
        body: JSON.stringify({
          firstName: fname,
          lastName: lname,
          email: email,
          password: pw,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      });
      const myJson = await response.json();
      window.localStorage.setItem("user", JSON.stringify(myJson));
      window.location.href = "../";
    } catch (err) {
      console.error(err);
      document.getElementById("signupError").hidden = false;
      return;
    }
  })();
});
