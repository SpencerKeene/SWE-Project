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
function setupCalendar() {
  // define inner functions
  function addEventOnDay() {}

  function addCell(day) {
    const cell = document.createElement("button");
    cell.classList.add("col", "calendar-cell");
    cell.innerText = day;
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
    let isPaddingDay = false;
    let day = i - paddingDaysBefore;
    // if cell is a padding day before
    if (i <= paddingDaysBefore) {
      day += daysInPrevMonth;
      isPaddingDay = true;
    }
    // if cell is a padding day after
    else if (i > paddingDaysBefore + daysInMonth) {
      day -= daysInMonth;
      isPaddingDay = true;
    }

    cell = addCell(day);
    if (isPaddingDay) cell.classList.add("padding-cell");
    if (isThisMonth && !isPaddingDay && day === todaysDate.getDate())
      cell.id = "today";
  }
}

function loadEvents() {}

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
