const user = JSON.parse(localStorage.getItem("user"));

function signOut() {
  window.localStorage.removeItem("user");
  window.location.href = "../loginSignup";
}

document.getElementById("sign-out-btn").onclick = signOut;
