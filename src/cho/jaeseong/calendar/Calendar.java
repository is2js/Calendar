package cho.jaeseong.calendar;

//import java.util.Scanner;

/*
 *요구사항
 *월을 입력받는다.
 *1일의 요일을 입력받는다.
 *출력한다.
 *
 *입력 및 출력 예시
년도를 입력하세요.
YEAR> 2017
달을 입력하세요.
MONTH> 3
			첫번째 요일을 입력하세요. (SU, MO, WE, TH, FR, SA)
			WEEKDAY> WE
			    <<2017년  3월>>
			 SU MO TU WE TH FR SA
			---------------------
			           1  2  3  4
			  5  6  7  8  9 10 11
 */

public class Calendar {
	
	private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final int[] LEAP_MAX_DAYS = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0 )) {
			return true;
		}
		return false;
	}
	
	public int getMaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year)) {
			return LEAP_MAX_DAYS[month-1];
		}
		return MAX_DAYS[month-1];
	}
	// 7. weekday까지 받았으니 -> 시작요일만큼 앞에 공백을 넣어주도록 설계해야한다.
	// -> 일요일(0)일때는 공백0, 월용ㄹ(1)일 때는 공백1개 ... weekday만큼 앞에 공백을 채워주면된다. 
	public void printCalendar(int year, int month, int weekday) {
		System.out.printf("   <<%4d년%3d월>>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");		
		System.out.println("---------------------");
		int maxDay = this.getMaxDaysOfMonth(year, month); 
		//8. 숫자출력 전에, print blank space(3묶음이 1개)
		//print first line
		for (int i = 0; i < weekday; i++ ) { 
			System.out.print("   ");
		}
//		   <<2021년  1월>>
//		   SU MO TU WE TH FR SA
//		  ---------------------
//		                1  2  3  4  5  6  7
		// print form second ine to last
		for (int i = 1; i <= maxDay; i++) { 
			System.out.printf("%3d", i);
			//9. 이제 줄바꿈을 언제해줘야할지 생각해보기. 기준은 weeday고정한 상태로.. i가 언제마다 줄바꿈을 하는지.. 식 찾고.. %7==0으로 표현
			// -> 원래는 i가 7마다 줄바꿈  -> weekday 3 -> 현  3개 덜가도 줄바꿈 -> 
			// @@  weekday고정해놓고 줄바꿈이 일어나는 i 규칙찾기 @@
			// weekday 3 -> 3개 공백후 1234에서 줄바꿈 -> 11에서 -> 18에서 --> @@ 7k-3 @@  i + 3= 7k -> i+weekday % 7 == 0마다 줄바꿈
			// -> @@ 달력처럼 우측으로 몇칸땡겨서 줄바꿈해야된다?  그 줄바꿈의 빈도는 @@ if (day + 전진횟수) % 7 == 0 @@마다 줄바꿈하면 된다. 
//			if (i % 7 == 0 ) {
			if ((i + weekday) % 7 == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
	
}
