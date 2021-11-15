package cho.jaeseong.calendar;

import java.util.Scanner;

/*
 *요구사항
월을 입력하면 해당월의 달력을 출력한다.
달력은 모양은 1단계에서 작성한 모양으로 만든다.
1일은 일요일로 정해도 무방하다. 			-> 쉽게 풀려고 하는 것
-1을 입력받기 전까지 반복 입력받는다.
프롬프트를 출력한다.


입력 및 출력 예시
월을 입력하세요.
> 3
일 월 화 수 목 금 토
--------------------
 1  2  3  4  5  6  7
 8  9 10 11 12 13 14
15 16 17 18 19 20 21
22 23 24 25 26 27 28
29 30 31
월을 입력하세요.
> -1
Bye~ 
 */

public class Calendar {
	
	private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public int getMaxDaysOfMonth(int month) {
		return MAX_DAYS[month-1];
	}
	
	public void printCalendar(int year, int month) {
		System.out.printf("   <<%4d년%3d월>>\n", year, month);
		//2-4. 숫자가 오른쪽정렬 된 3칸을 차지하므로,, 맨앞에 공백을 추가해주자. []SU가 1개 단위 ->  다음줄에도 -를 하ㅏㄴ 추가해주자.
		System.out.println(" SU MO TU WE TH FR SA");		
		System.out.println("---------------------");
		//1. 이 아래부분부터 하드코딩(주석처리)이 아니라 계산해서 구해야한다. 먼저 maxDay가 필요함.
		// - 같은 클래스내이므로, cal.메소드()가 아니라, (static없는 인스턴스메소든데???)도 메소드()로 호출한다?
		// -> 같은 클래스라면, this없이도? 인스턴스메소드라도 편하게 구할 수 있다?
		// -> 원래는 cal.~으로 호출되어야하지만, 같은 내부클래스라면 객체생략(this생략)하고 호출되나보다.
		int maxDay = this.getMaxDaysOfMonth(month);
		
		//2. 1부터 maxDay까지만 출력하면 된다. -> 일단 여기까지 출력해보기
		for (int i = 1; i < maxDay; i++) {
			System.out.printf("%3d", i); // 2-1.공백포함 3글자를 차지하도록 숫자를 나열한다.
			// 2-3. 줄바꿈은 7개 마다 해야한다. -> 7로 나눈 나머지가?? 첫항1 기준으로 총 7개째에 줄바꿈 -> 7일때 줄바꿈 -> 나머지%7이 0일 때 줄바꿈
			if (i % 7 == 0 ) {
				System.out.println();
			}
		}
		//2-2. printf를 모으는데 사용할 거면, 다 모으고나서 -> 개별로 println추가 넣어줘야한다.
		System.out.println();
		
//		System.out.println(" 1  2  3  4  5  6  7");
//		System.out.println(" 8  9 10 11 12 13 14");
//		System.out.println("15 16 17 18 19 20 21");
//		System.out.println("22 23 24 25 26 27 28");
	}
	
	
}
