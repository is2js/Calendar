package cho.jaeseong.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class Calendar {
	private static final int[] MAX_DAYS = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LEAP_MAX_DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	private HashMap<Date, String> planMap;

	public Calendar() {
		planMap = new HashMap<Date, String>();
	}
	
	/**
	 * 
	 * @param strDate ex: "2017-06-20"
	 * @param plan
	 * @throws ParseException 
	 */
	public void registerPlan(String strDate, String plan) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		this.planMap.put(date, plan);
	}
	
	public String searchPlan(String strDate) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		String plan = this.planMap.get(date);
		return plan; 
	}
	
	public boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}

	public int getMaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year)) {

			return LEAP_MAX_DAYS[month]; 
		}
		return MAX_DAYS[month]; 
	}

	public void printCalendar(int year, int month) {
		System.out.printf("   <<%d년 %d월>>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");
		System.out.println("---------------------");
		int maxDay = this.getMaxDaysOfMonth(year, month);
		int weekday = getWeekDay(year, month, 1);

		for (int i = 0; i < weekday; i++) {
			System.out.print("   ");
		}
		for (int i = 1; i <= maxDay; i++) {
			System.out.printf("%3d", i);
			if ((i + weekday) % 7 == 0) {
				System.out.println();
			}
		}
		System.out.println();

	}

	private int getWeekDay(int year, int month, int day) {
		int syear = 1970;
		int smonth = 1;
		final int STANDARD_WEEKDAY= 4; 
		int count = 0;

		for (int i = syear; i < year; i++) {
			int delta = isLeapYear(i) ? 366 : 365;
			count += delta;
		}
		for (int i = smonth; i < month; i++) {
			int delta = getMaxDaysOfMonth(year, i);
			count += delta;
		}

		count += (day - 1);

		int weekday = (count + STANDARD_WEEKDAY) % 7;
		
		return weekday;
	}

	public static void main(String[] args) throws ParseException {
		Calendar cal = new Calendar(); 
		cal.registerPlan("2017-06-23", "Let's eat beef!");
		System.out.println(cal.searchPlan("2017-06-23"));


	}

}
