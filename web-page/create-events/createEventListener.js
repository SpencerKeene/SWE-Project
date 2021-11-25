var eventName;
var eventTime;
var eventDate;

const eventBtn = document.getElementById('eventBtn');

eventBtn.addEventListener('submit', (e) => {
    eventName = document.getElementById('eventName').value;
    eventTime = document.getElementById('eventTime').value;
    eventDate = document.getElementById('EventDate').value;
    //add if successfully recieved by the server 
    alert("Event created sucessfully");
    //otherwise
    //alert("Event either already exists or is at the same time as an existing event");

});