// get local storage
const user = JSON.parse(window.localStorage.getItem("user"));

let deleteEventId = undefined;

// define variables
const todaysDate = new Date();
const calendarGrid = document.getElementById("calendar-grid");
const months = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];
let calendarYear = todaysDate.getFullYear();
let calendarMonth = todaysDate.getMonth();

// define functions
function displayDayEvents(event) {
  const cell = event.currentTarget;
  const day = parseInt(cell.getAttribute("day"));
  const month = parseInt(cell.getAttribute("month"));
  const year = parseInt(cell.getAttribute("year"));
  const isToday = cell.getAttribute("id") === "today";

  document.getElementById("calendar-day-modal-title").innerText = `${
    months[month]
  } ${day}, ${year} ${isToday ? "(Today)" : ""}`;

  // TODO - load events for the day
  const dayCalendar = document.getElementById("day-calendar");
  const dayCalendarChildren = dayCalendar.children;
  for (let i = 0; i < dayCalendarChildren.length; i++) {
    const child = dayCalendarChildren[i];
    if (child.classList.contains("day-event")) dayCalendar.removeChild(child);
  }

  eventsList.forEach((event) => {
    var startDate = parseDate(event.startDate);
    var endDate = parseDate(event.endDate);
    if (
      startDate.getDate() === day &&
      startDate.getMonth() === month &&
      startDate.getFullYear() === year
    ) {
      let startHour = startDate.getHours() % 12;
      if (startHour === 0) startHour = 12;
      let startMinute = startDate.getMinutes();
      let startAm = startDate.getHours() < 12;

      let endHour = endDate.getHours() % 12;
      if (endHour === 0) endHour = 12;
      let endMinute = endDate.getMinutes();
      let endAm = endDate.getHours() < 12;

      const startTime = `${startHour}:${startMinute}${startAm ? "am" : "pm"}`;
      const endTime = `${endHour}:${
        endMinute < 10 ? "0" + endMinute : endMinute
      }${endAm ? "am" : "pm"}`;

      const eventElement = document.createElement("button");
      eventElement.classList.add("event", "day-event");
      eventElement.setAttribute("data-bs-toggle", "modal");
      eventElement.setAttribute("data-bs-target", "#delete-event-modal");
      eventElement.onclick = (e) => {
        deleteEventId = event.id;
      };
      const top = (startDate.getHours() + startMinute / 60) * 120;
      eventElement.style.setProperty("top", `${top}px`);
      const height =
        (endDate.getHours() -
          startDate.getHours() +
          (endDate.getMinutes() - startDate.getMinutes()) / 60) *
        120;
      console.log({ height });
      eventElement.style.setProperty("height", `${height}px`);
      eventElement.innerHTML = `
        <div class="day-event-time">${startTime}-${endTime}</div>
        <div class="day-event-name">${event.name}</div>`;
      dayCalendar.appendChild(eventElement);
    }
  });
}

function setupCalendar() {
  // define inner functions
  function addEventOnDay() {}

  function addCell() {
    const cell = document.createElement("button");
    cell.classList.add("col", "calendar-cell");
    cell.onclick = displayDayEvents;
    cell.setAttribute("data-bs-toggle", "modal");
    cell.setAttribute("data-bs-target", "#calendar-day-modal");
    calendarGrid.appendChild(cell);
    return cell;
  }

  // clear calendar grid
  calendarGrid.innerHTML = "";

  // set calendar title
  document.getElementById("calendar-title-month").innerText =
    months[calendarMonth];
  document.getElementById("calendar-title-year").innerText = calendarYear;

  // generate variables for adding calendar cells
  const isThisMonth =
    calendarYear === todaysDate.getFullYear() &&
    calendarMonth === todaysDate.getMonth();
  const daysInMonth = new Date(calendarYear, calendarMonth + 1, 0).getDate();
  const daysInPrevMonth = new Date(calendarYear, calendarMonth, 0).getDate();
  const paddingDaysBefore = new Date(calendarYear, calendarMonth, 1).getDay();
  const paddingDaysAfter = (7 - ((paddingDaysBefore + daysInMonth) % 7)) % 7;

  // add cells
  for (
    let i = 1;
    i <= paddingDaysBefore + daysInMonth + paddingDaysAfter;
    i++
  ) {
    const cell = addCell();

    let day = undefined;
    let month = calendarMonth;
    let year = calendarYear;

    // if cell is a padding day before
    if (i <= paddingDaysBefore) {
      day = i - paddingDaysBefore + daysInPrevMonth;
      month = calendarMonth === 0 ? 11 : calendarMonth - 1;
      year = calendarMonth === 0 ? calendarYear - 1 : calendarYear;

      cell.classList.add("padding-cell");
    }
    // if cell is a padding day after
    else if (i > paddingDaysBefore + daysInMonth) {
      day = i - paddingDaysBefore - daysInMonth;
      month = calendarMonth === 11 ? 0 : calendarMonth + 1;
      year = calendarMonth === 11 ? calendarYear + 1 : calendarYear;

      cell.classList.add("padding-cell");
    }
    // is not a padding day
    else {
      day = i - paddingDaysBefore;
      month = calendarMonth;
      year = calendarYear;

      if (isThisMonth && day === todaysDate.getDate()) cell.id = "today";
    }

    cell.setAttribute("day", day);
    cell.setAttribute("month", month);
    cell.setAttribute("year", year);
    cell.innerText = day;
    const eventsList = document.createElement("ul");
    eventsList.style.setProperty("padding", 0);
    cell.appendChild(eventsList);

    // add events
    displayEvents(eventsList, day, month, year);
  }
}
//nicks start
const events = {
  id: 1,
  name: "example name 1",
  startDate: "2021 11 27 8:30",
  endDate: "2021 11 27 10:30",
};
const events1 = {
  id: 2,
  name: "example name 2",
  startDate: "2021 11 27 14:30",
  endDate: "2021 11 27 16:00",
};
const events2 = {
  id: 3,
  name: "example name 3",
  startDate: "2021 11 26 10:30",
  endDate: "2021 11 26 11:00",
};
const eventsList = [];
eventsList[events.id] = events;
eventsList[events1.id] = events1;
eventsList[events2.id] = events2;

function eventComparator(event1, event2) {
  var date1 = parseDate(event1.startDate);
  var date2 = parseDate(event2.startDate);
  if (date1 > date2) {
    return 1;
  } else if (date1 < date2) {
    return -1;
  } else {
    return 0;
  }
}

function displayEvents(eventListElement, day, month, year) {
  eventsList.sort(eventComparator);
  eventsList.forEach((event) => {
    var eventDate = parseDate(event.startDate);
    if (
      eventDate.getDate() === day &&
      eventDate.getMonth() === month &&
      eventDate.getFullYear() === year
    ) {
      const eventElement = document.createElement("div");
      eventElement.classList.add("event");
      eventElement.innerHTML = `<div class="event-name">${event.name}</div>`;
      eventListElement.appendChild(eventElement);
    }
  });
}

function parseDate(eventDateString) {
  var dateComponents = eventDateString.split(" ");
  var year = dateComponents[0];
  var month = dateComponents[1];
  var day = dateComponents[2];
  var dateTimeComponents = dateComponents[3].split(":");
  var hour = dateTimeComponents[0];
  var minute = dateTimeComponents[1];
  var eventDate = new Date(year, month - 1, day, hour, minute);
  return eventDate;
}
//nicks end

function monthLeft() {
  if (calendarMonth === 0) {
    calendarMonth = 11;
    calendarYear--;
  } else {
    calendarMonth--;
  }
  setupCalendar();
}

function monthRight() {
  if (calendarMonth === 11) {
    calendarMonth = 0;
    calendarYear++;
  } else {
    calendarMonth++;
  }
  setupCalendar();
}

function signOut() {
  window.localStorage.removeItem("user");
  window.location.href = "./loginSignup/signInOut.html";
}

function deleteEvent(e) {
  (async () => {
    try {
      const response = await fetch("http://localhost:8080/users/events", {
        method: "DELETE",
        body: JSON.stringify({
          email: user.email,
          eventId: deleteEventId,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      });
      const myJson = await response.json();
      window.localStorage.setItem("user", JSON.stringify(myJson));
      user = myJson;
      setupCalendar();
    } catch (err) {
      alert("error");
      console.error(err);
      return;
    }
  })();
}

// add methods to DOM elements
document.getElementById("calendar-title-left-btn").onclick = monthLeft;
document.getElementById("calendar-title-right-btn").onclick = monthRight;
document.getElementById("sign-out-btn").onclick = signOut;

// load calendar on page load
setupCalendar();
