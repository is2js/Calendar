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
	
	//1. 무지성 달력출력이 아니라, 입력받은 month에 따라 달라지도록 매개변수 추가하기.
	// -> 아직 사용하는 곳은 없지만, cal.메소드()명으로 사용시 month를 입력받고 -> 그 달을 출력하도록 할 에정이다.
	// + 나중에 year도 받아서 출력시키도록 매개변수를 추가한다. + 메소드명도 sample이라는 말을 뺀다.
//	public void printSampleCalendar() {
	public void printCalendar(int year, int month) {
		System.out.printf("   <<%4d년%3d월>>\n", year, month); // 2. 이제 사용처를 찾으로 Prompt클래스의 main함수 실행로직인 runPrompt 메소드로 간다.
		// printf 사용시 문자열 맨 마지막에 \n 추가 까먹지 말기.
//		System.out.println("일 월 화 수 목 금 토"); // 요일도 줄맞춤을 위해 영어 2글자로 바꿔주자.
		System.out.println("SU MO TU WE TH FR SA");		
		System.out.println("--------------------");
		System.out.println(" 1  2  3  4  5  6  7");
		System.out.println(" 8  9 10 11 12 13 14");
		System.out.println("15 16 17 18 19 20 21");
		System.out.println("22 23 24 25 26 27 28");
	}
	
	
}
