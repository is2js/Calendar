package cho.jaeseong.calendar;

import java.util.Scanner;

public class Prompt {
	private final static String PROMPT = "cal> ";
	
	public void runPrompt() { 
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		int month = 1;
		//6. year도 받아서 사용하도록 하려면, 매 반복문마다 입력값이 달라져서 , month처럼, 미리 선언된 변수에 받아주는 변수가 필요하다.
		// - 반복문에서 입력값을 받아주는 용도일 뿐이므로 초기값은 별로 안중요하다. -1만 아니면 된다.! (year는 딱히 종료조건을 안준다)
		// my) 반복문에서 루프로 받는 input은, 매번 선언해줄수없은, 미리 밖에서 선언해놓고 -> 변수 재활용하면서 input을 받아줘야한다.
		int year = 2000;
		
		while (true) {
			//7.
			System.out.println("연도를 입력하세요.");
			System.out.print(PROMPT);
			year = scanner.nextInt();
			
			System.out.println("달을 입력하세요.");
			System.out.print(PROMPT);
			month = scanner.nextInt();
			if (month == -1) {
				break;
			}
			if (!(1 <= month && month <= 12)) {
				System.out.println("1~12사이의 값을 입력하세요.");
				continue;
			}
			//8. 하드코딩 대신 입력받은 year가 들어간다.
//			cal.printCalendar(2017, month);
			cal.printCalendar(year, month);
			
		}
		
		System.out.println("Bye~!");

		scanner.close();
	}
	
	public static void main(String[] args) {
		Prompt p = new Prompt(); 
		p.runPrompt();
	}

}
