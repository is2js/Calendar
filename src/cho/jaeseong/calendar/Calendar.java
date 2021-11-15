package cho.jaeseong.calendar;

import java.util.Scanner;

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
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// 1. 반복횟수를 먼저 입력받고, 그만큼 반복하자.
		System.out.println("반복횟수를 입력하세요.");
		int repeat = scanner.nextInt(); 
		
		// 3. index인 repeat를 0과 -를 무지성으로 받아도, 알아서 for반복문은 에러없이 종료잘된다.
		for (int i = 0; i < repeat; i++ ) {
			System.out.println("달을 입력하세요.");
			int month = scanner.nextInt();
			
			Calendar cal = new Calendar();
			System.out.printf("%d월은 %d일 까지 있습니다.\n", month, cal.getMaxDaysOfMonth(month));
		}
		// 2. 정해진 반복이 끝났으면, 알려줘야함.
		System.out.println("Bye~!");
	
		
		//cal.printSampleCalendar();
		scanner.close();
		
		
	}
}
