package cho.jaeseong.calendar;

import java.util.Scanner;

public class Prompt {
	// 0. 안써서 삭제
	//private final static String PROMPT = "cal> ";
	
	// 3. 문자열 -> 숫자로 변환해주는 메소드도 따로 뺀다. 
	// - 일단 모두 static이 아닌 객체 생성(cal)해서 호출하는 인스턴스메서드(cal.메소드()) + public으로 쓰나보다.. cf) 변수는 클래스든 인스턴스든 다 public금지?
	// 4. 헤깔리는 메소드는 /**enter로 문서주석을 단다.
	/**
	 * 
	 * @param week 요일명
	 * @return 0 ~ 6 (0 = Sunday, 6= Saturday)
	 */
	public int parseDay(String week) {
		// 문자열 비교는 equals!!쓰기!!
		if (week.equals("SU")) {
			return 0;
		}
		if (week.equals("MO")) {
			return 1;
		}
		if (week.equals("TU")) {
			return 2;
		}
		if (week.equals("WE")) {
			return 3;
		}
		if (week.equals("TH")) {
			return 4;
		}
		if (week.equals("FR")) {
			return 5;
		}
		return 6;
	}
	
	public void runPrompt() { 
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		int month = 1;
		int year = 2000;
		//2. weekday(요일)도 매 반복문마다 input-> 받아줄 변수가 필요해서 초기화해놓는다.
		int weekday = 0; // 일요일을 0으로 보고 토요일 6으로 본다고 가정한다.
		
		while (true) {
			System.out.println("연도를 입력하세요.(exit:-1)");
			System.out.print("YEAR> ");
			year = scanner.nextInt();
			// 10. year도 받자마자 종료조건 필터링 부터 한다.
			if (year == -1) {
				break;
			}
			System.out.println("달을 입력하세요.");
			System.out.print("MONTH> ");
			month = scanner.nextInt();
			// 1. 첫째날의 요일(문자열) 입력받기 -> 문자열로 입력받아서 간단한 메소드를 통해  문자열->숫자로 변환 추출할 것이다. 
			System.out.println("첫째 날의 요일을 입력하세요.(ex> SU, MO, TU, WE, TH, FR, SA)");
			System.out.print("WEEKDAY> ");
			String strWeekday= scanner.next();
			// 5. 정의한 메소드 적용해주기
			weekday = this.parseDay(strWeekday); // 인스턴스메소드는... 같은 클래스 메소드 내에선.. 객체 생성없이..this도 없이? 호출이 되나보다. 하지만 this.찍어서 호출해보자.
			
			if (month == -1) {
				break;
			}
			if (!(1 <= month && month <= 12)) {
				System.out.println("1~12사이의 값을 입력하세요.");
				continue;
			}
			
			//6. 캘린더 출력메소드에.. weekday도 받아서 처리하게 인수(argument)수정 -> 7. 매개변수(parameter) 수정 by [F3]
			//cal.printCalendar(year, month);
			cal.printCalendar(year, month, weekday);
			
		}
		
		System.out.println("Bye~!");

		scanner.close();
	}
	
	public static void main(String[] args) {
		Prompt p = new Prompt(); 
		p.runPrompt();
	}

}
