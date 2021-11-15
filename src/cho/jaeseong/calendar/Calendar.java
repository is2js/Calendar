package cho.jaeseong.calendar;

import java.util.Scanner;

public class Calendar {
	
	private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	public int getMaxDaysOfMonth(int month) {
		return MAX_DAYS[month-1];
	}
	
	public void printSampleCalendar() {
		System.out.println("일 월 화 수 목 금 토");
		System.out.println("--------------------");
		System.out.println(" 1  2  3  4  5  6  7");
		System.out.println(" 8  9 10 11 12 13 14");
		System.out.println("15 16 17 18 19 20 21");
		System.out.println("22 23 24 25 26 27 28");
	}
	
	public static void main(String[] args) {
		//1. 프롬프트는 안내메세지 이후 ~ scanner작동 직전에  println이 아닌 print로 ">"등을 찍어주는데, 
		// ->  ">" 이외에 매번 찍어주고 싶은 문자열이 있다면 문자열변수를 이용한다.(상수??) 
		String PROMPT = "cal> ";
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar(); // 이건 언제..? 객체 생성은.. 1번만 하면 된다. 반복문에서 제외시켜야함.

		//3. 반복횟수 입력이 없어진다. -> 달> -1입력전까지 무한반복시키자.
		//System.out.println("반복횟수를 입력하세요.");
		//System.out.print(PROMPT);
		//int repeat = scanner.nextInt();
		//for (int i = 0; i < repeat; i++ ) {
		//4. 무한반복이지만, 조건이 있을 경우, ***그 조건변수를 밖으로 빼서*** -> while (true) 대신 while (month != -1)를 사용한다.
		// -> month는 내부에서 업데이트된다 -> 밖에서 ==반복문위에서 업데이트변수를 선언&초기화해놓고, -> 안에서는  i번째항으로 업데이트만 해야지 -> 밖에서 쓸 수 있다.
		int month = 1; //-1 이 아닌 값으로 업데이트될 변수를 초기화해준다.
		
//		while (month != -1) {
		//7-1. while (조건)시 맨마지막에 업데이트가 아니라면, 그냥 while (true)  업데이트직후  if 조건검사 break를 쓰자.
		while (true) { 
			System.out.println("달을 입력하세요.");
			//2. scanner로 받기 직전에 PROMPT 찍어주기
			System.out.print(PROMPT);
			// 5. month가 업데이트변수로 바꼈다. -> 내부에서 선언이 아닌, 내부에서 업데이트만 하게 한다. 선언은 밖에서 했다.
			//int month = scanner.nextInt();
			month = scanner.nextInt();
			//6. Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index -2 out of bounds for length 12
			// **-1을 입력하면, 바로 탈출이 아니라,  받아서 -> month가 -1로 업데이트 ->  getMaxDaysOfMonth(-1)을 한번 실행하고, 그 때 확인하므로,, 실행과정에서 index-1이 들어감. **
			// -> **무한반복을 while (조건)으로 하는 경우, 조건변수 업데이트이후 작업이 이뤄진다면? 탈출상태가 그 상태가 아니면서,,  이번 경우에는 에러도..**
			// -> ****반복문 while (조건변수)는  검사직전인 맨 마지막에 업데이트시켜라. ---> 그게 안되면, while (true) 무한반복 + 조건업데이트직후 if break****를 쓰자.
			
			//7-2. while (조건)시 맨마지막에 업데이트가 아니라면, 그냥 while (true)  업데이트직후  if 조건검사 break를 쓰자.
			if (month == -1)  {
				break;
			} // 1줄이라도  {} 중괄호를 쓰자.
			// 8. 외부에서 받는 값이면 1)종료 필터링 이후-> 2)범위(혹은 index)검사 - continue 반드시 하자. 
			if (!(1<= month && month <= 12)) {
				System.out.println("1~12사이의 값을 입력하세요.");
				continue;
			}
			System.out.printf("%d월은 %d일 까지 있습니다.\n", month, cal.getMaxDaysOfMonth(month));
		}
		System.out.println("Bye~!");
	
		scanner.close();
		
		
	}
}
