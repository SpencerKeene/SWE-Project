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

    // if cell is a padding day before
    if (i <= paddingDaysBefore) {
      const day = i - paddingDaysBefore + daysInPrevMonth;

      cell.setAttribute("day", day);
      cell.setAttribute("month", calendarMonth === 0 ? 11 : calendarMonth - 1);
      cell.setAttribute(
        "year",
        calendarMonth === 0 ? calendarYear - 1 : calendarYear
      );

      cell.innerText = day;
      cell.classList.add("padding-cell");
    }
    // if cell is a padding day after
    else if (i > paddingDaysBefore + daysInMonth) {
      const day = i - paddingDaysBefore - daysInMonth;

      cell.setAttribute("day", day);
      cell.setAttribute("month", calendarMonth === 11 ? 0 : calendarMonth + 1);
      cell.setAttribute(
        "year",
        calendarMonth === 11 ? calendarYear + 1 : calendarYear
      );

      cell.innerText = day;
      cell.classList.add("padding-cell");
    }
    // is not a padding day
    else {
      const day = i - paddingDaysBefore;

      cell.innerText = day;
      cell.setAttribute("day", day);
      cell.setAttribute("month", calendarMonth);
      cell.setAttribute("year", calendarYear);

      if (isThisMonth && day === todaysDate.getDate()) cell.id = "today";
    }
  }
}

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

function signOut() {
  window.localStorage.removeItem("user");
  window.location.href = "./loginSignup/signInOut.html";
}

// add methods to DOM elements
document.getElementById("calendar-title-left-btn").onclick = monthLeft;
document.getElementById("calendar-title-right-btn").onclick = monthRight;
document.getElementById("sign-out-btn").onclick = signOut;

// load calendar on page load
loadCalendar();
