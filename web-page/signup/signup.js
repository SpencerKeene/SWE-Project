//defined values
const signUpForm = document.getElementById("signupForm");

signUpForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const fname = document.getElementById("fname").value;
  const lname = document.getElementById("lname").value;
  const email = document.getElementById("inputEmail").value;
  const pw = document.getElementById("inputPassword").value;
  //add validation and API
  (async () => {
    try {
      const response = await fetch("http://147.182.152.27:8080/users", {
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
