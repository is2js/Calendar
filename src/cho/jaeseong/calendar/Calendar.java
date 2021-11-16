package cho.jaeseong.calendar;

/*
 * 요구사항
3단계와 같지만 년도와 월만 입력받는다.
진짜 캘린더에서 나오는 달력과 똑같은 모양의 달력을 출력한다.
추가적으로 입력받아야 하는 내용이 있는지 생각해 보자
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
		// 9. 안쓴 상수or변수는 삭제한다.
		//int sday = 1; 
		
		//5. 상수값으로 변경(final+대문자)
		//int standardWeekday = 3;
//		final int STANDARD_WEEKDAY= 3; // 12. 목요일을 4로 수정 
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

		// 2.
		// System.out.println(count);
		
		//4.이제 지난일수count를 가지고, 기준일의 요일 기준으로, 무슨요일인지 판단해야한다.
		//int weekday = count % 7; // 지난일0 -> 0, 1일지남 -> 1 ... ===> 지난일0일때 기준시작요일(standardWeekday)이 되도록해야한다.
		// - 기준요일을 final상수+대문자로 변경하고 그 상수값을 가져온다.
		//6. 달력출력할때처럼, 7개씩 돌아가면 %7로서 0~6주기를 구하되, 시작점이 오른쪽으로 땡겨간다면, [ 그만큼 먼저 더해놓고 ] % 7을 시행한다. 
		int weekday = (count + STANDARD_WEEKDAY) % 7; // 지난일0 -> 0, 1일지남 -> 1 ... ===> 지난일0일때 기준시작요일(standardWeekday)이 되도록해야한다.
		
		//7.
//		return count;
		return weekday;
	}

	// simple test code here
	public static void main(String[] args) {
		Calendar cal = new Calendar();
		// 1. getWeekDay라는 기준일로부터 지속일을 함수내부에서 찍는게 아니라 return해놓고, 메인에서 찍도록 수정하자. -> 2.
		// 3. **Junit 테스트처럼 예상값을 미리 넣어놓고 비교하여 t/f를 반환하게 찍는다..**
//		cal.getWeekDay(1970, 1, 1);
//		System.out.println(cal.getWeekDay(1970, 1, 1) == 0);
//		System.out.println(cal.getWeekDay(1970, 2, 1) == 31); // 2월까지의 차이는... 직전의1월이 전체지난 거다.. 윤년상관없이 31일..
//		System.out.println(cal.getWeekDay(1970, 3, 1) == (31 + 28)); // 1970년은 윤년X -> 2월달은 28일
//		/*
//		 * true true true
//		 */
//		System.out.println(cal.getWeekDay(1971, 1, 2) == (365 + 1)); // 1971년은.. 직전인 1970년이 간 것 -> 1970년이 윤년이냐?
		
		//10. 이제 [예상되는] 지난일이 아닌 [요일]을 정답초롬 입력해서 t/f를 뽑아보자.
		System.out.println(cal.getWeekDay(1970, 1, 1) == 0+4); // 당일안지났으면 최초의 목요일 0123 -> 01234
//		System.out.println(cal.getWeekDay(1970, 2, 1) == 0);  // 다음달은 바로 안나온다.
		System.out.println(cal.getWeekDay(1971, 1, 1) == 0+4+1); // but 1년지난 것은 365를 7로 나누면 나머지가 1이 되니, 7개씩 까지고 1일 지난 금요일이다. 
		System.out.println(cal.getWeekDay(1972, 1, 1) == 0+4+2); // * 윤년이 아닌 이상 1년지나면 해마다+1일씩 요일이 나아간다*  (윤년은 +2씩 간다)
		System.out.println(cal.getWeekDay(1973, 1, 1) == 0+4+3); // 1973->1972년을 낀 해 -> 윤년이라서 +2가 늘어났을 것이다.
		System.out.println(cal.getWeekDay(1973, 1, 1) == 1); // 4+4->**8 -> 1로 답 수정 **  true
		
		// 11. -> 이제 Prompt에 가서 실행시키자!!(여기는 해당class 테스트용main)
		// 12. weekday 잘못계산한것(Prompy에서는 0이 일요일 -> Calendar는 0이 월요일.. -> 목요일을 3에서 4로 수정해주자.)
		
	}

}
