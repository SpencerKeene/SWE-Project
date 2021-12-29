var newPassword;

const updatePasswordBtn = document.getElementById("passwordBtn");

updatePasswordBtn.addEventListener("click", (e) => {
  e.preventDefault();
  newPassword = document.getElementById("input-password").value;

  (async () => {
    try {
      const response = await fetch(
        "http://147.182.152.27:8080/users/change-password",
        {
          method: "PUT",
          body: JSON.stringify({
            email: user.email,
            newPassword: newPassword,
          }),
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      const myJson = await response;
      window.localStorage.setItem("user", JSON.stringify(user));
      user.password = newPassword;
      alert("Password changed");
    } catch (err) {
      console.error(err);
      alert("error");
      return;
    }
  })();
});
