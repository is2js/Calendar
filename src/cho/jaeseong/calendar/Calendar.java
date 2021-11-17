package cho.jaeseong.calendar;

import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Calendar {
	private static final int[] MAX_DAYS = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LEAP_MAX_DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	// 7. HashMap의 value에 문자열1개저장가능 -> Class객체로 여러데이터 저장가능하게 타입을 바꿔준다.
	// private HashMap<Date, String> planMap;
	private HashMap<Date, PlanItem> planMap; // my) 변수만 선언해놨다면, 나중에 생성자에서 초기화할 예정!! -> 생성자에서 받는다~ -> 객체생성은 실제 생성자호출되는
												// 생성시점에 만들자~~

	public Calendar() {
		// planMap = new HashMap<Date, String>();
		planMap = new HashMap<Date, PlanItem>();
	}

	/**
	 * 
	 * @param strDate ex: "2017-06-20"
	 * @param plan
	 * @throws ParseException
	 */
	// 11. new simpledateformt. parse()가 class내부처리되어서 -> 메소드에서는 더이상 throws를 할 필요가
	// 업어졌다.
//	public void registerPlan(String strDate, String plan) throws ParseException {
	public void registerPlan(String strDate, String plan) {
		// 8. strDate -> Date로 변환은, 이제 데이터저장용 객체 생성시 내부에서 알아서 처리된다. 주석처리
//		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		// -> **문제점 발생.. key에 Date타입를 입력하도록 짰는데, value내부에서 처리(strDate->Date)됬으니.. 여기서
		// 또해야할 가능성이..**
		// -> **인스턴스객체 내부의 Date를 getter메소드로 반환하도록 하여, 외부에서 사용가능하도록 -> key로 주기 작전

		// 9. 다시 PlanItem으로 가서 Date를 get할 수 있는 getter 정의하기

		// 10. 데이터를 planMap에 넣어줄텐데, key는 value인 객체를 이용하여 객체.getDate()로 구해서 넣어준다.
		PlanItem p = new PlanItem(strDate, plan);
		// this.planMap.put(date, plan);
		this.planMap.put(p.getDate(), p);
	}

	// 12. 마찬가지로 여기도 planMap 변경에 따른 처리를 해준다. 
	// -> **기존에는 Prompt에서 호출하여, String plan을 반환해줫는데, 지금은 PlanItem에 담겨져있으니**
	// --> **일단 PlanItem객체가 반환될 수 있또록 수정한다.**
	public PlanItem searchPlan(String strDate) throws ParseException {
		// 13. 여기선 PlanItem 객체를 생성할 필요가 없으므로, strDate -> Date를 직접 변환한 뒤, key로 검색할 수있게 한다.
		//Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		// 14. 검색결과는 planItem객체다. 바로 return해주자 (이전에는 String plan을 봅아서 string만 반환해줬었음.)
		//String plan = this.planMap.get(date);
		//return plan;
		
		//15. 13.의 과정을.. 메소드로 처리해버린다. 이 때 **static** 메소드를 통해, 굳이 객체 생성할필요없이 Calendar.메소드()로 바로 쓸 수 있게 한다.
		// -> my) **단순 변환 과정 등, 객체랑 상관없는 유틸성 메소드라면, static메소드로 선언할 수 밖에 없다??**
		// **해당.java파일내 [[[ 객체노상관==주제노상관 자주쓰는 ***유틸***메소드]]]는 static으로 선언해서쓰자!!**
		// -> **만약, 타 파일에서 정의할거면 public static으로 정의해놓고 땡겨쓰자.**
		// -> 16. 날짜변환과 관련되어 <PlanItem 객체 내부에서도 쓰이므로> ---> [PlanItem.java]로 가서 public static으로 정의하고, 여기서 땡겨와서 사용할 것이다.
		
		// 24. PlanItem에 정의(public) 해준 유틸메소드(static)를 이용해 strDate -> Date로 변환시켜보자.
		Date date = PlanItem.getDateFromString(strDate);
		return this.planMap.get(date);
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
		final int STANDARD_WEEKDAY = 4;
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
