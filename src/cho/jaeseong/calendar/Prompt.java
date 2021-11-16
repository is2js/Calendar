package cho.jaeseong.calendar;

import java.util.Scanner;

public class Prompt {
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
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		int month = 1;
		int year = 2000;

		while (true) {
			System.out.println("연도를 입력하세요.(exit:-1)");
			System.out.print("YEAR> ");
			year = scanner.nextInt();
			if (year == -1) {
				break;
			}
			System.out.println("달을 입력하세요.");
			System.out.print("MONTH> ");
			month = scanner.nextInt();

			if (month == -1) {
				break;
			}
			if (!(1 <= month && month <= 12)) {
				System.out.println("1~12사이의 값을 입력하세요.");
				continue;
			}
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
