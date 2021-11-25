var newPassword;

const updatePasswordBtn = document.getElementById("passwordBtn");

updatePasswordBtn.addEventListener("click", (e) => {
  e.preventDefault();
  newPassword = document.getElementById("input-password").value;

  user.password = newPassword;

  (async () => {
    try {
      const response = await fetch(
        "http://localhost:8080/users/change-password",
        {
          method: "PUT",
          body: JSON.stringify(user),
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      const myJson = await response;
      window.localStorage.setItem("user", JSON.stringify(user));
      alert("Password changed");
    } catch (err) {
      console.error(err);
      alert("error");
      return;
    }
  })();
});
