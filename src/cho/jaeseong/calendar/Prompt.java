package cho.jaeseong.calendar;

import java.util.Scanner;

public class Prompt {
	
	//1. UI부터 만들어주기 위해 메소들 정의함.
	// - 메소드 같은 경우, main이라도,  객체.메서드()로 호출할 예정이므로 static필요없고 public으로 호출만 되게 한다.(private만 아니면 객체.메서드()호출될듯)
	public void printMenu() {
		// 2. 프롬프트에 찍힐 텍스트를 그대로 복사해온 뒤, 앞에 println 만 붙혀주면 그대로 출력됨.
		System.out.println("+----------------------+");
		System.out.println("| 1. 일정 등록");           
		System.out.println("| 2. 일정 검색");           
		System.out.println("| 3. 달력 보기");
		System.out.println("| h. 도움말 q. 종료");
		System.out.println("+----------------------+");
		//System.out.println("명령 (1, 2, 3, h, q)");
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
		//3. 필드들 선언하기도 전에 먼저 ui부터 출력되도록하기 -> 메인 메소드에서, printMenu()인스턴스 메소드 호출..이.. 아닌.. 
		// -> 첫 호출되는 p.runPrompt()[인스턴스메소드]안에서..[인스턴스메소드]printMenu()를 편하게 .. 바로 편하게.. 호출하게하네.. 나는 인스턴스를 의미하는 this붙혀주기 
		this.printMenu();
		
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		// 11. 달력input받는 용이라서 따로빼준 메소드 내부로 가지고 들어가기
//		int month = 1;
//		int year = 2000;
		
		while (true) {
			//4. 이제 [무한루프속에서 q할대까지 반복되지만] 달력보기의 전에, 1글자를 입력을 먼저 받아야한다.
			//명령 (1, 2, 3, h, q)
			System.out.println("명령 (1, 2, 3, h, q)"); // 입력받는 곳에서는 멈춘다. 그전에 안내멘트가 나가야한다. 특히 무한반복에서  매번 나올 안내문
			String cmd = scanner.next();
			//5. 각 받은 1글자마다 분기처리를 해줘야하는데, | 1. 일정 등록 | 2. 일정 검색 | 3. 달력 보기 | h. 도움말 q. 종료
			//**method를 미리 주석으로? 아니면 에러나더라도 가상으로 작성해주고 -> [ctrl+1]으로 create 해당메소드로 만들어두자.**
			// - switch case에 문자열이 안되서 if로 한다고 함. / if문은 한줄이면 중괄호 없애고 1line으로 올려도됨. 
			if (cmd.equals("1")) {
				cmdRegister();
			} else if (cmd.equals("2")) {
				cmdSearch();
			} else if (cmd.equals("3")) {
				//17. 파라미터 추가되었으니 인자로 넣어준다.
//				cmdCal();
				cmdCal(scanner, cal);
			} else if (cmd.equals("h")) {
				this.printMenu(); // 도움말은 최초 메뉴 그대로 한번 더 출력하면 된다.
			} else if (cmd.equals("q")) {
				break;
			}
			
			
		} // 6. 무한루프를 도는것은 메뉴UI만 돌아야한다. 달력 출력부분은 이제 무한반복 밖으로 뺀다. while(true)를 닫아버린다!!!
  		  // -> 달력 출력하는 부분은 따로 메소드로 뺄 준비를 하고, 맨 밑에  bye+scanner.close()부분을 while문 안에 넣어준다.	

		//8. 무한루프를 끝나고 나올 메뉸
		System.out.println("Bye~!");
		scanner.close();
	}
		
			
			// 7. 달력 작성부분을 cmdCal()로 옮기자.
//			System.out.println("연도를 입력하세요.(exit:-1)");
//			System.out.print("YEAR> ");
//			year = scanner.nextInt();
//			if (year == -1) {
//				break;
//			}
//			System.out.println("달을 입력하세요.");
//			System.out.print("MONTH> ");
//			month = scanner.nextInt();
//
//			if (month == -1) {
//				break;
//			}
//			if (!(1 <= month && month <= 12)) {
//				System.out.println("1~12사이의 값을 입력하세요.");
//				continue;
//			}
//			cal.printCalendar(year, month);
//		} // 앞에서 닫음. 여긴 삭제해야할 괄호
		



//	private void cmdCal() {
		//9. 옮기고 나니, 객체 scanner를 뺏긴 상태라 필요함. -> 메소드로 들어온 상황이니까, 앞에 있떤 scanner객체 를 파라미터로 받아서 쓰자.
//	private void cmdCal(Scanner scanner) {
	//14. cal객체도 필요하다. 메소드내에서 객체 만들지말고 밖에서 받자. 
	// - scanner는 매번 close도 해줘야하며.. 함수호출 == 해당 기능시마다 객체를 생성하면 안된다.
	// -> 객체는 메소드 내에서 쓸일이 있으면, 미리 밖에서 선언된 것을 파라미터로 받아야한다. 
	private void cmdCal(Scanner scanner, Calendar cal) {
//		System.out.println("연도를 입력하세요.(exit:-1)");
		System.out.println("연도를 입력하세요.");
		System.out.print("YEAR> ");
		//10. 밖에 있을 때, input으로 들어올 month, year를 받아줄 변수를 미리 선언해놨었다. -> 가지고 들어오자.
		int month = 1;
		int year = 2000;
		year = scanner.nextInt();
		//12. 여기서 이제 무한반복break할 없으니까, year, month로 -1체크한 것 + 멘트 + break부분 제거하자.
//		if (year == -1) {
//			break;
//		}
		System.out.println("달을 입력하세요.");
		System.out.print("MONTH> ");
		month = scanner.nextInt();
//
//		if (month == -1) {
//			break;
//		}
		if (!(1 <= month && month <= 12)) {
			System.out.println("1~12사이의 값을 입력하세요.");
			//15. 범위검사에 걸리면 [무한반복내부가 아니므로 break처럼 continue도 제거]해야한다.
			// -> 범위이상하게 들어오면, ***[ 지금 현재 함수를 종료시켜 바깥 작업중이던 곳으로 가기 ]*** 위해 ***return;***만
			//continue;
			return; // 함수 종료시켜 stack비우고 원래 메인으로 가기~
			
		}
		//13. cal객체가 필요다 이것도 메소드 내에서 객체를 만들지말고, 파라미터로 받자. 
		cal.printCalendar(year, month);
		
		//16. 이제 밖에서도 파라미터들을 인자로 받도록 수정해주러 가자.
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
