const node = document.getElementById('eventsList');

function listOfEvents(){
    setTimeout(function(){
        listOfEvents();
    }, 2000); 
    //checks every two seconds for updates and updates the html for those changes
    
    //if events exist
    //for all events
    //innerHTML = get events;
    //else
    node.innerHTML = "<h6>There are no upcoming events!</h6>";
    test++;
}
listOfEvents();