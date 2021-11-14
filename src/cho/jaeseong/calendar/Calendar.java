package cho.jaeseong.calendar;

import java.util.Scanner;

public class Calendar {
	public static void main(String[] args) {
		System.out.println("일 월 화 수 목 금 토");
		System.out.println("--------------------");
		System.out.println(" 1  2  3  4  5  6  7");
		System.out.println(" 8  9 10 11 12 13 14");
		System.out.println("15 16 17 18 19 20 21");
		System.out.println("22 23 24 25 26 27 28");
		
		// 2) Q. 숫자를 입력받아서, 해당 월에 최대일 수 구하기
//		달을 입력하세요.
//		3 (엔터)
//		3월은 31일까지 있습니다.
		Scanner scanner = new Scanner(System.in);
		
//		=== 내 풀이.. year?로 윤년판단해야하나? ====
//		String month = scanner.next();
//		int days = 31;
//		if (month =="4"| month =="6"|month =="9"|month =="11") {
//			days = 30;
//		}
//		else if (month =="2") {
//			
//		}
		System.out.println("달을 입력하세요.");
		int month = scanner.nextInt();
		
		// 1. 배열을 먼저 하나 만든다.
		// - 윤년 판단 없이.. 그냥 12개월 배열을 미리 만든다.
		int[] maxDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		// 2. 입력에 따라 배열숫자를 하나 꺼내준다.
		
		System.out.printf("%d월은 %d일 까지 있습니다.\n", month, maxDays[month-1]);
		
		scanner.close();
		
		
	}
}
