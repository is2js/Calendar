package cho.jaeseong.calendar;

import java.util.Scanner;

public class Calendar {
	
	//
	private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	// 4. main메소드 호출용 static int 로 만들어도 되지만, **여기선 어디서든 호출가능하게 public -> static이 아니므로 [class로 객체를 만들어서 호출]하도록 해보자.**
	// -> 여기 현재파일 클래스로 객체를 만들어서 호출해야하므로, class를 옮길 때 불편해질 수 있다? 
	// -> 어디 class로 옮겨가든(for class분리) 해당클래스명.메소드로 바로 호출되도록 static를 붙여서 메소드 정의하는게 더 좋을 듯????
	public int getMaxDaysOfMonth(int month) {
		return MAX_DAYS[month-1];
	}
	
	// 8. 역시 비 static 메서드라.. 객체를 만들고 호출해야 찍어볼 수 있다. (앞에서 cal.getMax~ 호출할라고 만든 cal객체로 호출해보자.)
	public void printSampleCalendar() {
		System.out.println("일 월 화 수 목 금 토");
		System.out.println("--------------------");
		System.out.println(" 1  2  3  4  5  6  7");
		System.out.println(" 8  9 10 11 12 13 14");
		System.out.println("15 16 17 18 19 20 21");
		System.out.println("22 23 24 25 26 27 28");
	}
	
	public static void main(String[] args) {
		// 7. 달력 출력본을 메소드로 빼자. (아까우니까 남겨두는 용) -> 8.

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("달을 입력하세요.");
		int month = scanner.nextInt();
		
		//2. 이미 정해진 배열은 메서드처럼 상수로서 class바깥으로 뺀다. 
		//int[] maxDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		//3. month를 받아서 maxDays에서 달의 최대일을 반환해주는 메소드를 class밖에서 만든다. ->  4.
		
		//5. static이 아닌 메소드는 -> 바깥의 Calendar클래스 -> 객체 -> 객체.메소드()로 호출해야한다.
		// - 여기서만 하자. class분리용으로는 별로 안좋을듯?
		Calendar cal = new Calendar();
		System.out.printf("%d월은 %d일 까지 있습니다.\n", month, cal.getMaxDaysOfMonth(month));
		
		
		//9. 비스태틱으로 출력문 빼놓은거 시험삼아 출력
		cal.printSampleCalendar();
		scanner.close();
		
		
	}
}
