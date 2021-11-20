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

function setupCalendar(yearOffset = 0, monthOffset = 0) {
  console.log("todays date:", todaysDate);

  const month = todaysDate.getMonth() + monthOffset;
  const year = todaysDate.getFullYear() + yearOffset;
  const date = new Date(year, month);

  document.getElementById("calendar-month").innerText = months[month];
  document.getElementById("calendar-year").innerText = year;

  // generate variables for adding calendar cells
  const isThisMonth =
    date.getFullYear() === todaysDate.getFullYear() &&
    date.getMonth() === todaysDate.getMonth();
  const daysInMonth = new Date(year, month + 1, 0).getDate();
  const daysInPrevMonth = new Date(year, month, 0).getDate();
  const paddingDaysBefore = new Date(year, month, 1).getDay();
  const paddingDaysAfter = 7 - new Date(year, month + 1, 1).getDay();

  // add cells
  function addCell(day) {
    const cell = document.createElement("div");
    cell.classList.add("col", "calendar-cell");
    cell.innerText = day;
    calendarGrid.appendChild(cell);
    return cell;
  }
  for (
    let i = 1;
    i <= paddingDaysBefore + daysInMonth + paddingDaysAfter;
    i++
  ) {
    let isPaddingDay = false;
    let day = i - paddingDaysBefore;
    if (i <= paddingDaysBefore) {
      day += daysInPrevMonth;
      isPaddingDay = true;
    } else if (i > paddingDaysBefore + daysInMonth) {
      day -= daysInMonth;
      isPaddingDay = true;
    }

    cell = addCell(day);
    if (isPaddingDay) cell.classList.add("padding-cell");
    if (isThisMonth && !isPaddingDay && day === todaysDate.getDate())
      cell.id = "today";
  }
}

function loadEvents(yearOffset, monthOffset) {}

function loadCalendar(yearOffset = 0, monthOffset = 0) {
  setupCalendar(yearOffset, monthOffset);
  loadEvents();
}

loadCalendar();
