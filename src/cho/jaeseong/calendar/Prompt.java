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
		//2. 이제 받아오는 것은 plan 문자열이 아니라 PlanItem객체라서..바꿔준다. 
		//String plan = "";
		PlanItem plan;
		
		
		//1. searchPlan은 이제, string  plan이 아니라.. PlanItem객체를 -> Prompt로 반환하도록 바꼈다.
		//   여러데이터를 담고 있으므로 반환을 통채로 일단 해주긴 해줘야하므로.
		//   try-catch도 내부에 처리되고 없으면 default null이 반환되도록 해놨다. -> try-catch문 없애준다.
//		try {
//			plan = cal.searchPlan(date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//			System.err.println("일정 검색 중 오류가 발생했습니다.");
//		}
		
		//3. 원래 map이 아니라면,, null을 반환받았을 때를 대비해줘야하지만, HashMap.get()으로 꺼내므로 자동으로 null반환이 된다.
		//plan = cal.searchPlan(date);
		plan = cal.searchPlan(date);
		//4. 문자열이 아닌 **객체를 찍을 때는, 반드시 [받아온 객체는  null이 아닐때만 찍을 수 있게] 검사를 한번 한다.
		//System.out.println(plan);
		if (plan != null) {
			System.out.println(plan.plan);
		} else {
			System.out.println("일정이 없습니다.");
		}

	}

	private void cmdRegister(Scanner scanner, Calendar cal) throws ParseException {
		System.out.println("[새 일정 등록] ");
		System.out.println("날짜를 입력해 주세요(yyyy-MM-dd).");
		String date = scanner.next();
		//5. 단위단위로 받도록 다시 수정.. 콘솔이 깨지기고 한다고함.
//		System.out.println("일정을 입력해주세요");
//		String text = "";
//		scanner.nextLine(); // ignore 1 line 
//		text= scanner.nextLine();
		
		String text = "";
		System.out.println("일정을 입력해 주세요.(끝문자=;)");
		String word;
		// word의 끝이 ";"가 아닐때까지 무한루프 + @while조건문안에서도 scanner가 작동하여 초기값을 넣어줄 수 있다.@ + 할당받은 word = 에 괄호를 치면 바로 사용할 수 있다.
		// -> word는 초기화를 scanner를 사용 + while문의 조건부에서 초기화 && if검사를 동시에
		while (!(word = scanner.next()).endsWith(";")) {
			// 끝단어가 아니라면 빈문자열  text에다가 공백과 함께 더하기
			// **끝만 ;세미콜론으로 알려주기! -> 루프에서 endsWith("끝알림 문자")로 검사하기!**
			text += word + " ";
		}
		// 6. 마지막 단어상태로 빠져나왔으므로, 한번 더 더해줘야한다. -> 끝알림 문자 삭제하고 넣어주자.
		word = word.replace(";", "");
		text += word;
		
		cal.registerPlan(date, text); 

	}

	public static void main(String[] args) throws ParseException {
		Prompt p = new Prompt();
		p.runPrompt();
	}

}
