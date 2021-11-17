package cho.jaeseong.calendar;

import java.text.ParseException;
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
	

	/*
	 * Plan class: 전 단계에서 구현한 내용을 클래스를 사용해서 다시 구현한다.
	 * PlanItem 클래스를 만든다. -> refactor다.
	 */

	/**
	 * 
	 * @param week 요일명
	 * @return 0 ~ 6 (0 = Sunday, 6= Saturday)
	 */
	public int parseDay(String week) {
		switch (week) {
		case "SU":
			return 0;
		case "MO":
			return 1;
		case "TU":
			return 2;
		case "WE":
			return 3;
		case "TH":
			return 4;
		case "FR":
			return 5;
		case "SA":
			return 6;
		default:
			return 0;
		}
	}

	public void runPrompt() throws ParseException {
		this.printMenu();

		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();
		boolean isLoop = true;
		
		while (isLoop) {
			System.out.println("명령 (1, 2, 3, h, q)");
			String cmd = scanner.next();
			switch (cmd) {
			case "1":
				cmdRegister(scanner, cal);
				break;
			case "2":
				cmdSearch(scanner, cal);
				break;
			case "3":
				cmdCal(scanner, cal);
				break;
			case "h":
				this.printMenu();
				break;
			case "q":
				isLoop = false; 
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

	private void cmdSearch(Scanner scanner, Calendar cal) {
		System.out.println("[일정 검색]");
		System.out.println("날짜를 입력해 주세요(yyyy-MM-dd).");
		String date = scanner.next();
		String plan = "";
		try {
			plan = cal.searchPlan(date);
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("일정 검색 중 오류가 발생했습니다.");
		}
		System.out.println(plan);

	}

	private void cmdRegister(Scanner scanner, Calendar cal) throws ParseException {
		System.out.println("[새 일정 등록] ");
		System.out.println("날짜를 입력해 주세요(yyyy-MM-dd).");
		String date = scanner.next();
		System.out.println("일정을 입력해주세요");
		String text = "";
		scanner.nextLine(); 
		text = scanner.nextLine();
		

		cal.registerPlan(date, text); 

	}

	public static void main(String[] args) throws ParseException {
		Prompt p = new Prompt();
		p.runPrompt();
	}

}
