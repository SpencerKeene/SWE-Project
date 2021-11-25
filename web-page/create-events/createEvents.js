function signOut() {
  window.localStorage.removeItem("user");
  window.location.href = "../loginSignup/signInOut.html";
}

document.getElementById("sign-out-btn").onclick = signOut;
