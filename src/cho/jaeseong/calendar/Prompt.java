package cho.jaeseong.calendar;

import java.util.Scanner;

public class Prompt {
	private final static String PROMPT = "cal> ";
	
	public void runPrompt() { 
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		int month = 1;

		while (true) {
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
			cal.printCalendar(2021, month);
			
		}
		
		System.out.println("Bye~!");

		scanner.close();
	}
	
	public static void main(String[] args) {
		Prompt p = new Prompt(); 
		p.runPrompt();
	}

}
