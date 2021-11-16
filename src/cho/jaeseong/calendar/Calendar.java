package cho.jaeseong.calendar;

/*
 * 요구사항
3단계와 같지만 년도와 월만 입력받는다.
진짜 캘린더에서 나오는 달력과 똑같은 모양의 달력을 출력한다.
추가적으로 입력받아야 하는 내용이 있는지 생각해 보자
 */

public class Calendar {

	private static final int[] MAX_DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LEAP_MAX_DAYS = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}

	public int getMaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year)) {
			return LEAP_MAX_DAYS[month - 1];
		}
		return MAX_DAYS[month - 1];
	}
	
	//2. 입력받는 parameter의 weekday제거하기 
	// -> but **내부에서 weekday는 계산에 의해 정해져야한다. 내부로직은 살려도록 weekday를 내부에서 하나 선언해놓자.**
	//public void printCalendar(int year, int month, int weekday) {
	public void printCalendar(int year, int month) {
		System.out.printf("   <<%d년 %d월>>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");
		System.out.println("---------------------");
		int maxDay = this.getMaxDaysOfMonth(year, month);
		// 3. parameter weekday는 없어진 대신 계산으로 weekday를 구해서 공백 출력해야한다. 일단 초기화해놓기
		// TODO: get weekday automatically
		int weekday = 0;
		// 4. 2017년 6월 1일이 무슨요일인지.. 자동으로 알아내야하는데 복잡하므로 검색을 통해 가져와야한다. 		
		
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

}
