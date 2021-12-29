const user = JSON.parse(localStorage.getItem("user"));

const node = document.getElementById("eventsList");
var todaysDate = new Date();
var limitDate = new Date(
  todaysDate.getFullYear(),
  todaysDate.getMonth(),
  todaysDate.getDate() + 30
);

function listOfEvents() {
  user.assignedEvents.sort((event1, event2) => {
    var date1 = parseDate(event1);
    var date2 = parseDate(event2);
    if (date1 > date2) {
      return 1;
    } else if (date1 < date2) {
      return -1;
    } else {
      return 0;
    }
  });
  user.assignedEvents.forEach((event) => {
    var eventDate = parseDate(event);
    if (eventDate > todaysDate && eventDate <= limitDate) {
      displayEvent(event);
    }
  });
}

function parseDate(event) {
  var startDateComponents = event.startDate.split(" ");
  var startYear = startDateComponents[0];
  var startMonth = startDateComponents[1];
  var startDay = startDateComponents[2];
  var startDateTimeComponents = startDateComponents[3].split(":");
  var startHour = startDateTimeComponents[0];
  var startMinutes = startDateTimeComponents[1];
  var meridiemStart;
  var eventDate = new Date(
    startYear,
    startMonth - 1,
    startDay,
    startHour,
    startMinutes
  );
  return eventDate;
}

function displayEvent(event) {
  var startDateComponents = event.startDate.split(" ");
  var startYear = startDateComponents[0];
  var startMonth = startDateComponents[1];
  var startDay = startDateComponents[2];
  var startDateTimeComponents = startDateComponents[3].split(":");
  var startHour = startDateTimeComponents[0];
  var startMinutes = startDateTimeComponents[1];
  var meridiemStart;
  if (startHour > 12) {
    startHour %= 12;
    meridiemStart = "PM";
  } else {
    meridiemStart = "AM";
  }

  var endDateComponents = event.endDate.split(" ");
  var endYear = endDateComponents[0];
  var endMonth = endDateComponents[1];
  var endDay = endDateComponents[2];
  var endDateTimeComponents = endDateComponents[3].split(":");
  var endHour = endDateTimeComponents[0];
  var endMinutes = endDateTimeComponents[1];
  var meridiemEnd;
  if (endHour > 12) {
    endHour %= 12;
    meridiemEnd = "PM";
  } else {
    meridiemEnd = "AM";
  }

  var display = document.createElement("div");
  display.classList.add("container", "box");
  display.innerHTML = `<h2>${event.name}</h2>`;
  display.innerHTML += `<h6>Start: ${startYear}/${startMonth}/${startDay} ${startHour}:${startMinutes} ${meridiemStart}</h6>`;
  display.innerHTML += `<h6>End: ${endYear}/${endMonth}/${endDay} ${endHour}:${endMinutes} ${meridiemEnd}</h6>`;
  node.appendChild(display);
}
listOfEvents();

function signOut() {
  window.localStorage.removeItem("user");
  window.location.href = "../loginSignup";
}
document.getElementById("sign-out-btn").onclick = signOut;
