// get local storage
const user = window.localStorage.getItem("user");

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
  const cell = event.target;
  const day = cell.getAttribute("day");
  const month = cell.getAttribute("month");
  const year = cell.getAttribute("year");
  const isToday = cell.getAttribute("id") === "today";

  document.getElementById("calendar-day-modal-title").innerText = `${
    months[month]
  } ${day}, ${year} ${isToday ? "(Today)" : ""}`;

  // TODO - load events for the day
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
    cell.innerText += "\n" + displayEvents(day, month, year);
  }
}
//nicks start
const events = {
  id: 1,
  name: "example name 1 27th",
  startDate: "2021 11 27 12:30",
  endDate: "2021 11 27 13:00",
};
const events1 = {
  id: 2,
  name: "example name 2 28th",
  startDate: "2021 11 28 14:30",
  endDate: "2021 11 28 16:00",
};
const events2 = {
  id: 3,
  name: "example name 3 26th",
  startDate: "2021 11 26 10:30",
  endDate: "2021 11 26 11:00",
};
const eventsList = [];
eventsList[events.id] = events;
eventsList[events1.id] = events1;
eventsList[events2.id] = events2;

function sortArray() {
  eventsList.sort((event1, event2) => {
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
}

function displayEvents(day, month, year) {
  sortArray();
  var eventName = "";
  eventsList.forEach((event) => {
    var eventDate = parseDate(event);
    if (
      eventDate.getDate() === day &&
      eventDate.getMonth() === month &&
      eventDate.getFullYear() === year
    ) {
      eventName += event.name + "\n";
    }
  });

  return eventName;
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
//nicks end

function loadEvents() {
  // TODO - load events for the month
}

function loadCalendar() {
  setupCalendar();
  loadEvents();
}

function monthLeft() {
  if (calendarMonth === 0) {
    calendarMonth = 11;
    calendarYear--;
  } else {
    calendarMonth--;
  }
  loadCalendar();
}

function monthRight() {
  if (calendarMonth === 11) {
    calendarMonth = 0;
    calendarYear++;
  } else {
    calendarMonth++;
  }
  loadCalendar();
}

// add methods to DOM elements
document.getElementById("calendar-title-left-btn").onclick = monthLeft;
document.getElementById("calendar-title-right-btn").onclick = monthRight;

// load calendar on page load
loadCalendar();
