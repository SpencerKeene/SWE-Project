var loginEmail = "";
var loginPw = "";
var valid = false;
const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", (e) => {
  e.preventDefault();
  loginEmail = document.getElementById("inputEmail").value;
  loginPw = document.getElementById("inputPassword").value;

  //add validation and API
  (async () => {
    try {
      const response = await fetch("http://localhost:8080/users/login", {
        method: "POST",
        body: JSON.stringify({
          email: loginEmail,
          password: loginPw,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      });
      const myJson = await response.json();
      window.localStorage.setItem("user", myJson);
      window.location.href = "../";
    } catch (err) {
      console.log("wrong password");
      document.getElementById("loginError").hidden = false;
      return;
    }
    console.log("myJson:", myJson);
  })();
});
