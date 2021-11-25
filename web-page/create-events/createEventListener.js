const user = JSON.parse(localStorage.getItem("user"));

var eventName;
var eventTime;
var eventDate;

const eventForm = document.getElementById("eventForm");

eventForm.addEventListener("submit", (e) => {
  e.preventDefault();

  eventName = document.getElementById("eventName").value;
  eventStartTime = document.getElementById("eventStartTime").value;
  eventEndTime = document.getElementById("eventEndTime").value;
  eventDate = document.getElementById("eventDate").value;

  eventStart = `${eventDate.replaceAll("-", " ")} ${eventStartTime}`;
  eventEnd = `${eventDate.replaceAll("-", " ")} ${eventEndTime}`;

  (async () => {
    try {
      const response = await fetch("http://localhost:8080/users/events", {
        method: "POST",
        body: JSON.stringify({
          email: user.email,
          event: {
            name: eventName,
            startDate: eventStart,
            endDate: eventEnd,
          },
        }),
        headers: {
          "Content-Type": "application/json",
        },
      });
      const myJson = await response.json();
      user.assignedEvents.push(myJson);
    } catch (err) {
      console.error(err);
      document.getElementById("createEventError").hidden = false;
      return;
    }
  })();
});
