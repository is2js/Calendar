package cho.jaeseong.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/*
요구 사항
간단한 콘솔 기반 사용자 UI를 만든다. (일정 검색용)
오늘 날짜를 인식해서 해당 월의 달력을 출력한다.
달력에서 일정이 있는 날 아래에는 . 을 찍어서 보여준다.
일정을 등록, 검색, 변경이 가능하게 해 준다.
오늘의 일정을 표시해 준다.

---입력 및 출력 예시---

+----------------------+
| 1. 일정 등록           
| 2. 일정 검색           
| 3. 달력 보기
| h. 도움말 q. 종료
+----------------------+
명령 (1, 2, 3, h, q)
> 1
[일정 등록] 날짜를 입력하세요.
> 2016-06-05
일정을 입력하세요.
> 자바지기에게 밥 얻어먹기
일정이 등록되었습니다.
명령 (1, 2, 3, h, q)
> 2
[일정 검색] 날짜를 입력하세요.
> 2016-06-05
1개의 일정이 있습니다.
1. 자바지기에게 밥 얻어먹기
명령 (1, 2, 3, h, q)
q
Bye
 */

public class Calendar {
	private static final int[] MAX_DAYS = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LEAP_MAX_DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	//1. 검색 및 저장을 위해 HashMap을 쓴다. 밖에서 접근하면 안되니, private로 선언한다.
	// - 앞에는(key로서) Date가 들어가고, 뒤에는 plan으 Stringㅇ 들어가도록 <제네,릭>으로 자료형을 준다.
	// ***dictionary = {} python과 다르게 ***[객체가 생성되는 순간인 생성자 호출]시*** HashMap을 초기화 해준다.
	private HashMap<Date, String> planMap;

	//2. 이때까지 생성자가 없어서 default생성자를 컴팡ㄹ러한테 받았을 것ㅇ다.
	// -> **객체 생성마다 서로다른 hashMap을 가지도록** **HashMap을 초기화**해주는 생성자를 작성한다.
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
		System.out.println(date);
		
		//3. 이제 input으로 들어올 strDate-> date와 plan을  hashmap에 입력하자.
		planMap.put(date, plan);
	}
	
	//4. 저장후, private한 planMap검색(plan이 반환될?)을 해주는 메소드를 만들자.
	// -> 검색은 strDate를 입력하면, -> date로 변환 -> map에서 찾기 -> plan을 반환시켜줘야한다.
	public String searchPlan(String strDate) throws ParseException {
		//return ""; //에러방지용으로 먼저 선언해두고 구현부 작성해주기.
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		// map 은 .get(key)로서 key가 string아니어도 바로 뽑아낸다.
		String plan = planMap.get(date);
		return plan; // 5. 프롬프트에서 등록 및 검색반환받아보기.
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
		Calendar cal = new Calendar(); // 6-5. 객체 생성마다 map이 초기화되도록 했으니, 실행시마다 비어져있을 것이다.
		cal.registerPlan("2017-06-23", "Let's eat beef!");
		//6. 반환까지 받아보기
		System.out.println(cal.searchPlan("2017-06-23"));
		//7. 결과값
		//Fri Jun 23 00:00:00 KST 2017
		//Let's eat beef!


	}

}
