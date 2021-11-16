package cho.jaeseong.calendar;

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

	public static void main(String[] args) {
//		Calendar cal = new Calendar();
	}

}
