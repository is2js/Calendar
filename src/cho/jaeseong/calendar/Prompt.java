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

	public void runPrompt() throws ParseException {
		this.printMenu();

		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		while (true) {
			System.out.println("명령 (1, 2, 3, h, q)");
			String cmd = scanner.next();
			if (cmd.equals("1")) {
				// 1. 일정등록 로직으로 넘어갈 때, 역시나 ***[[ Prompt시작시 만드는]]]*** scanner객체 재활용 +
				// cal객체(cal.일정등록/검색 in hashmap) 등 객체들이 필요할 것이다.
				// cf) 객체는 메소드로 넘어가기 전에 미리 생성 -> 인자로 갖다써서 재활용
				// cmdRegister();
				cmdRegister(scanner, cal); // -> 2. F3눌러서 파라미터 추가해주기
			} else if (cmd.equals("2")) {
				// 5. 이제 일정검색 연걸해주기 -> 마찬가지로 입력을받고 (Scanner객체 사용) 캘린더 기능(탐색기능 Calendar객체)를 이용할
				// 예정이니 인자-> 파라미터로 넣어준다.
				// cmdSearch();
				cmdSearch(scanner, cal);
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

	// 6. 필요한 객체를 받는 파라미터를 추가해주고 함수를 구현부를 작성해준다.
	private void cmdSearch(Scanner scanner, Calendar cal) {
		System.out.println("[일정 검색]");
		System.out.println("날짜를 입력해 주세요(yyyy-MM-dd).");
		String date = scanner.next();
		// 7. 이번에는 trycatch로 해주자.
		// String plan;
		// 8. 에러가 날 경우, plan이 초기화가 안된 상태로 println(plan)된다고 에러뜬다. -> 빈 문자열로 초기화해놓자.
		String plan = "";
		try {
			plan = cal.searchPlan(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 9. 에러는 syserr로 찍자
			System.err.println("일정 검색 중 오류가 발생했습니다.");
		}
		System.out.println(plan);

	}

	// 2. Prompt에서 cal.일정등록/검색 메소드을 호출하도록 연결해주는 메소드를 정의해주자.
//	private void cmdRegister() {
	private void cmdRegister(Scanner scanner, Calendar cal) throws ParseException {
		// 3. 여기서는 일단 다시 안내문과 함계 등록할 일정날짜(strDate)를 입력을 받아야한다.
		System.out.println("[새 일정 등록] ");// 한줄로 표시..?
		System.out.println("날짜를 입력해 주세요(yyyy-MM-dd).");
		String date = scanner.next();
		System.out.println("일정을 입력해주세요");
//		//10. 테스트과정에서 nextLine();으로 긴 문장을 받을 수 없다고 한다. -> 한단어씩 word로 받고 + " " 공백 넣어주고 + ;세미콜론들어오면 break하는 무한반복으로 단어받기
//		//String text = scanner.nextLine();
//		String text = "";
//		while (true) {
//			String word = scanner.next();
//			text += word + " ";
//			if (word.endsWith(";")) {
//				break;
//			}
//		}
		//11. 최종수정본
		String text = "";
		scanner.nextLine(); // 입력받은 1줄을 날림 -> 이유는 모름
		text = scanner.nextLine();
		
		

		// 4. strDate와 plan 문자열을 받았으면 cal.일정등록 기능을 이용한다.
		cal.registerPlan(date, text); // throws까지 처리해줘야함. -> 연쇄적으로.. 담부턴 try catch로!@!

	}

	public static void main(String[] args) throws ParseException {
		Prompt p = new Prompt();
		p.runPrompt();
	}

}
