const node = document.getElementById("eventsList");
const events = {
    id: 1,
    name:"example name 1",
    startDate:"2021 12 24 12:30",
    endDate:"2021 12 24 13:00"
};
const events1 = {
    id: 2,
    name:"example name 2",
    startDate:"2021 12 11 14:30",
    endDate:"2021 12 11 16:00"
};
const events2 = {
    id: 3,
    name:"example name 3",
    startDate:"2021 12 22 10:30",
    endDate:"2021 12 22 11:00"
};
const eventsList = [];
eventsList[events.id] = events;
eventsList[events1.id] = events1;
eventsList[events2.id] = events2;
var todaysDate = new Date();
var limitDate = new Date(todaysDate.getFullYear(), todaysDate.getMonth(), todaysDate.getDate() + 30);
function listOfEvents(){
    eventsList.sort((event1,event2)=>{
        var date1 = parseDate(event1);
        var date2 = parseDate(event2);
        if(date1 > date2){
            return 1;
        }
        else if(date1 < date2){
            return -1
        }
        else{
            return 0;
        }
    });
    eventsList.forEach(event=>{
        var eventDate = parseDate(event);
        if(eventDate > todaysDate && eventDate <= limitDate){
            displayEvent(event);
        }
        else{
            console.log("event not displayed");
        }
    });
}

function parseDate(event){
    var startDateComponents = event.startDate.split(" ");
        var startYear = startDateComponents[0];
        var startMonth = startDateComponents[1];
        var startDay = startDateComponents[2];
        var startDateTimeComponents = startDateComponents[3].split(":");
        var startHour = startDateTimeComponents[0];
        var startMinutes = startDateTimeComponents[1];
        var meridiemStart;
        var eventDate = new Date(startYear, startMonth-1, startDay, startHour, startMinutes);
        return eventDate;
}

function displayEvent(event){
    var startDateComponents = event.startDate.split(" ");
    var startYear = startDateComponents[0];
    var startMonth = startDateComponents[1];
    var startDay = startDateComponents[2];
    var startDateTimeComponents = startDateComponents[3].split(":");
    var startHour = startDateTimeComponents[0];
    var startMinutes = startDateTimeComponents[1];
    var meridiemStart;
    if(startHour > 12){
        startHour %= 12;
        meridiemStart = "PM";
    }
    else{
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
    if(endHour > 12){
        endHour %= 12;
        meridiemEnd = "PM";
    }
    else{
        meridiemEnd = "AM";
    }

    var display = document.createElement("div");
    display.classList.add("container", "box");
    display.innerHTML = `<h2>${events.name}</h2>`;
    display.innerHTML += `<h6>Start: ${startYear}/${startMonth}/${startDay} ${startHour}:${startMinutes} ${meridiemStart}</h6>`;
    display.innerHTML += `<h6>End: ${endYear}/${endMonth}/${endDay} ${endHour}:${endMinutes} ${meridiemEnd}</h6>`;
    node.appendChild(display);
}
listOfEvents();