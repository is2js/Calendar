package cho.jaeseong.calendar;

import java.util.Scanner;

public class Prompt {
	
	public void printMenu() {
		System.out.println("+----------------------+");
		System.out.println("| 1. 일정 등록");           
		System.out.println("| 2. 일정 검색");           
		System.out.println("| 3. 달력 보기");
		System.out.println("| h. 도움말 q. 종료");
		System.out.println("+----------------------+");
	}
	
	/**
	 * 
	 * @param week 요일명
	 * @return 0 ~ 6 (0 = Sunday, 6= Saturday)
	 */
	public int parseDay(String week) {
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
		this.printMenu();
		
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		
		while (true) {
			System.out.println("명령 (1, 2, 3, h, q)");
			String cmd = scanner.next();
			if (cmd.equals("1")) {
				cmdRegister();
			} else if (cmd.equals("2")) {
				cmdSearch();
			} else if (cmd.equals("3")) {
				cmdCal(scanner, cal);
			} else if (cmd.equals("h")) {
				this.printMenu(); 
			} else if (cmd.equals("q")) {
				break;
			}
		} 	

		System.out.println("Bye~!");
		scanner.close();
	}
		
			
	private void cmdCal(Scanner scanner, Calendar cal) {
		System.out.println("연도를 입력하세요.");
		System.out.print("YEAR> ");
		int month = 1;
		int year = 2000;
		year = scanner.nextInt();
		System.out.println("달을 입력하세요.");
		System.out.print("MONTH> ");
		month = scanner.nextInt();
		if (!(1 <= month && month <= 12)) {
			System.out.println("1~12사이의 값을 입력하세요.");
			return; 
		}
		cal.printCalendar(year, month);
		
	}

	private void cmdSearch() {
		// TODO Auto-generated method stub
		
	}

	private void cmdRegister() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		Prompt p = new Prompt();
		p.runPrompt();
	}

}
