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
	
	public void printSampleCalendar() {
		System.out.println("일 월 화 수 목 금 토");
		System.out.println("--------------------");
		System.out.println(" 1  2  3  4  5  6  7");
		System.out.println(" 8  9 10 11 12 13 14");
		System.out.println("15 16 17 18 19 20 21");
		System.out.println("22 23 24 25 26 27 28");
	}
	
	// 2 main메소드 통째로 -> Prompt 클래스로 옮긴다.
	
}
