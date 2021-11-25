var newEmail;

const updateEmailBtn = document.getElementById("emailBtn");

updateEmailBtn.addEventListener("click", (e) => {
  e.preventDefault();
  newEmail = document.getElementById("input-email").value;

  (async () => {
    try {
      const response = await fetch("http://localhost:8080/users/change-email", {
        method: "PUT",
        body: JSON.stringify({
          oldEmail: user.email,
          newEmail: newEmail,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      });
      const myJson = await response.ok;
      if (!myJson) throw ErrorEvent;
      user.email = newEmail;
      window.localStorage.setItem("user", JSON.stringify(user));
      alert("Email changed");
    } catch (err) {
      alert("error");
      return;
    }
  })();
});
