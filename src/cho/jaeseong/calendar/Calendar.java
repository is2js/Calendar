package cho.jaeseong.calendar;

import java.util.Scanner;

/*
 *요구사항
 *윤년판단 로직을 포함해서 일요일->1일부터 달력 출력하기
 */

public class Calendar {
	
	//1. 윤년용 MAX_DAYS 배열을 1개더 선언해서, 윤년시 해당배열을 사용하도록 하면 된다. (2월 28일 -> 29일)
	private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final int[] LEAP_MAX_DAYS = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	//2. 2개의 배열중 어떤배열쓸지를 int year에 의해 결정되며, 여기 class는 그 판단유무를 결정해숴 반환해줘야한다!!!
	// 그걸 판단해서 반환해주는 boolean 메서드를 여기 class에서 정의하고, 사용처에서 사용해준다. ( 외부면 cal.isLeapYear 내부면 this.isLeapyear)
	// - 에러가 짜증나니까, 미리 return false;로 해놓고 작성하자.
	public boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0 )) {
			return true;
		}
		return false;
	}
	
	//3. 윤년판단로직은 getMaxDaysofMonth에서 사용해서, 사용할 배열을 결정하여 maxday를 반환해줘야한다.
	// -> isLeapYear(year) 사용해야하므로 year도 받아주도록 수정해야한다.
	// year가 사용되어야하므로, 매개변수에 int year를 추가해주자. -> 사용처에서도 추가해주자.
//	public int getMaxDaysOfMonth(int month) {
	//5. 
	public int getMaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year)) {
			return LEAP_MAX_DAYS[month-1];
		}
		return MAX_DAYS[month-1];
	}
	
	public void printCalendar(int year, int month) {
		System.out.printf("   <<%4d년%3d월>>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");		
		System.out.println("---------------------");
		int maxDay = this.getMaxDaysOfMonth(year, month); // 4. 사용처에 year추가. -> F3으로 정의부 돌아가기
		for (int i = 1; i <= maxDay; i++) { // 1부터 시작이므로 <= N으로 수정함..
			System.out.printf("%3d", i);
			if (i % 7 == 0 ) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
	
}
