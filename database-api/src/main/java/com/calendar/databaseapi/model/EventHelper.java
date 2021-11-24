package com.calendar.databaseapi.model;

import java.util.List;

public abstract class EventHelper {
  public static boolean isValid(Event event) {
		return (new Date(event.getStartDate())).isValid() && (new Date(event.getEndDate())).isValid();
	}
  
  public static boolean existsConflict(Event event, List<Event> otherEvents) {
	  for (Event otherEvent : otherEvents) {
		  if (isConflict(event, otherEvent)) return true;
	  }
	  return false;
  }
  
  public static boolean isConflict(Event event1, Event event2) {
	  Date startDate1 = new Date(event1.getStartDate());
	  Date endDate1 = new Date(event1.getEndDate());
	Date startDate2 = new Date(event2.getStartDate());
	Date endDate2 = new Date(event2.getEndDate());
	
	if(endDate1.isBefore(startDate2)) return false;
	else if(startDate1.isAfter(endDate2)) return false;
	else return true;
  }
}
