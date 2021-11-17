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
	 * 1) switch case - String
	 * 2) Plan class: 전 단계에서 구현한 내용을 클래스를 사용해서 다시 구현한다.
	 *    PlanItem 클래스를 만든다. -> refactor다.
	 */

	/**
	 * 
	 * @param week 요일명
	 * @return 0 ~ 6 (0 = Sunday, 6= Saturday)
	 */
	public int parseDay(String week) {
		// 1. if else if -> switch case 문 변경해보기
		// java 1.7(JDK 7이상?)부터는 문자열을 변수로 받을 수 있다고 한다.
		// 각 경우마다 break 대신 return을 써주면 함수 탈출이므로 break 대신 return을 switch case문에 쓴다.
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
//		if (week.equals("SU")) {
//			return 0;
//		}
//		if (week.equals("MO")) {
//			return 1;
//		}
//		if (week.equals("TU")) {
//			return 2;
//		}
//		if (week.equals("WE")) {
//			return 3;
//		}
//		if (week.equals("TH")) {
//			return 4;
//		}
//		if (week.equals("FR")) {
//			return 5;
//		}
//		return 6;
	}

	public void runPrompt() throws ParseException {
		this.printMenu();

		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		// 5. 2중 반복(여기선 반복-switchcase)를 빠져나오기 위해서는 
		//-> 1) 백기선 스터디 outer에 이름적어줘서 그걸로빠져나가기??
		//-->   outer: while (true) {    --->   case "q": break outer;  ---> 됨.
		//-> 2) 고전방식: boolean isLoop = true의 flag변수 생성후, while (flag) -> 해당경우에서 flag = false로 업데이트 -> 담 루프 시작전 종료
		//->    cf) 해당레벨까지 BFS:  count = 0  -> while not count -> 해당레벨중간에 count +=1로 업데이트 ->  담 루프 시작전 종료
		//-->  만약, whiel (flag)가 불가능한 for문이라면? 처음시작부터  for  if not flag==true:break 달려있을 듯?
//		outer: while (true) {
//		while (true) {
		//6. 5-2의 플래그변수로 이중 반복문 빠져나오기
		boolean isLoop = true;
//		while (true) {
		while (isLoop) {
			System.out.println("명령 (1, 2, 3, h, q)");
			String cmd = scanner.next();
			//2 . 1,2,3 만 보면 Integer.parseInt써야할 것 같지만, h, q가 섞여있는 경우 문자열로 받아 처리해야함.
			switch (cmd) {
			case "1":
				cmdRegister(scanner, cal);
				//3. if - else if와 다르게, 서로 배반의 상황임을 [case마다 배반]처리하기 위해서는 [추가] break처리가 필요하다.
				// -> while문 처럼, swtich도 break로 빠져나간다!! 
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
				// 4. 여기서는 swtich case뿐만 아니라 whlie문도 빠져나가야하는데, break 1개로는.. switch case밖에 못빠져나간다.
				//break outer;
				// 6-2. isLoop의 flag만 false로 업데이트해주면, while (flag) 로인해 바깥 반복문이 다음루프 떄멈춘다.
				isLoop = false; // 지금 당장이 아니라, 다음루프 검사 때 멈춤!
				break;
			}
		}
		System.out.println("Bye~!"); 
		scanner.close();
	}

		
//			if (cmd.equals("1")) {
//				cmdRegister(scanner, cal); 
//			} else if (cmd.equals("2")) {
//				cmdSearch(scanner, cal);
//			} else if (cmd.equals("3")) {
//				cmdCal(scanner, cal);
//			} else if (cmd.equals("h")) {
//				this.printMenu();
//			} else if (cmd.equals("q")) {
//				break;
//			}
//		}


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
