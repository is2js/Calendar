package cho.jaeseong.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanItem {
	public Date date; 
	public String plan; 
	public String peoples = "";
	
	public PlanItem(String strDate, String plan) {
		this.date = PlanItem.getDateFromString(strDate);
		this.plan = plan;
	}
	
	public void addPeople(String name) {
		peoples += name + ",";
	}
	
	public Date getDate()  { 
		return this.date;
	}
	
	public static Date getDateFromString(String strDate) {
		Date date = null;
		
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
