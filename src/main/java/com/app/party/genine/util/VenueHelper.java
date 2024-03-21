package com.app.party.genine.util;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class VenueHelper {

	public boolean validateUpdation(Date tillDate) {
		
		LocalDate localDate=tillDate.toLocalDate();
		LocalDate currentdateDate=LocalDate.now();
		
		if(localDate.equals(currentdateDate)) {
			return true;
		}else {
			return false;
		}
		
	}

	
}
