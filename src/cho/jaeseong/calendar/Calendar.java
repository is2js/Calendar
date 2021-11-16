package cho.jaeseong.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	//1. Prompt의 "1" 입력 -> cmdRegister(); ->로 넘어왔을 때, 사용자에게 "strDate"와 "plan"을 입력받을 것이다. **string date는 strDate로 미리 받아두는 sense**
	// 이 때, [[[ scanner로 받은 "strDate"와 "plan"을 등록시켜줄 메소드]]] 의 선언부를 짠다.
	// - 1) 문자열 date와 문자열  plan 2가지를 파라미터로 받는다고 가정한다. -> *date의 string양식을 문서주석으로 ex: "2017-06-20"같은 예시를 달아준다.
	// -> 선언부를 작성하고 문서주석 /**{enter}치면, 파라미터도 다 자동으로 작성되어있다!!
	/**
	 * 
	 * @param strDate ex: "2017-06-20"
	 * @param plan
	 * @throws ParseException 
	 */
	//public void registerPlan(String date, String plan) {
	//4. ctrl+1 -> [ Add throws ]의 결과 -> 문서주석에도 자동 추가된다.
	public void registerPlan(String strDate, String plan) throws ParseException {
		// 2. string-> date로 변환시키기 위해 Date모듈을 가져올 건데, 단축키는 [c+s+m]이다.
		// -> new SimpleDateFormat("미리 날짜포맷") -> .parse("해당포맷 문자열") -> Date형으로 변환시킨다.
		
		// 3. new SimpleDateFormat~에서 exception에러가 뜬다면, 1) 메서드선언부에 Add throws  or 2) try catch 묵어주기가 [ctrl+1]로 안내되는데
		//-> 여기선 씸플하게 throws를 적용해보자. -> [[ 메소드 내부try/catch처리하는ㄱ ㅔ 아니라 메소드를 호출하는 부분에서 처리한다 ]]는 뜻이다.
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		// 4. 찍히는지 한번 보기  ->  **여기class의 Main함수**에서 확인하기
		System.out.println(date); 
		
	}
	
	public boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}

	public int getMaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year)) {

			return LEAP_MAX_DAYS[month]; // 13.
		}
		return MAX_DAYS[month]; // 13.
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
		//5. 작성중인 메서드가 제대로 작동하는지 테스트하기~!
		Calendar cal = new Calendar();
		//cal.registerPlan("2017-06-23", "Let's eat beef!");
		//6. 메서드에서 throws해줬으면,, 호출하는서.. 해줘야한다... Add throws
		cal.registerPlan("2017-06-23", "Let's eat beef!");
		//Fri Jun 23 00:00:00 KST 2017  -> Date객체 형식으로 찍힌다. 근데.. 너무 복잡할듯?

	}

}
