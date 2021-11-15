package cho.jaeseong.calendar;

import java.util.Scanner;

//1. main메소드를 가지는 Prompt 클래스를 만들어서,   Calendar의 메인함수를 옮긴 뒤,  쉘 기능을 구현할 준비를 한다.
public class Prompt {
	// 4. 이제 메인메소드의 내용을 통째로 메소드화 해야하는데, 그전에, 메소드의 지역변수처럼 선언해놓았던 변수들을 --> class의 변수 or 상수(객체용 아니므로 static + 상수이므로 final -> 상수는 해당class에서 final static)로 정의해주자. 
	// -> 옮길 예정이라. .상수화 안했는 듯????????
	// 여기 class내에서만(class속 main에서만) 사용하므로 private  +  상수니까 final static 을 붙혀서 선언한다.
	//String PROMPT = "cal> ";
	private final static String PROMPT = "cal> ";
	
	// 6. 메인메소드 내용 통째로 메서드로 선언해주자. 
	// - 아직까지.. 메소드들은 싹다 public을 붙인다???? static에서 사용하려면 static도 붙여야하지 않나???
	// -> static을 붙혔다면.. 메인로직이 옮겨가면. static이 필요가 없어진다??? 아니면 클래스.메서드로 호출해도 될 것 같긴한데 객체.메서드()호출로 메인용 static메서드를 없애보자.
	public void runPrompt() { 
		// Calendar클래스도.. public class라 편하게.. 어디서든 불러서 사용할 수 있다.
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
			System.out.printf("%d월은 %d일 까지 있습니다.\n", month, cal.getMaxDaysOfMonth(month));
		}
		System.out.println("Bye~!");

		scanner.close();
	}
	
	
	// 3.메인함수를 통째로 옮겨왔는데도 에러가 안난다. -> public class를 통해 객체 만들고, public method로 작성해놨기 때문에, 편하게 불러지기 때문?? 
	// -> my) 메인함수는 결국 옮겨갈 것이니... 핵심 동작은 main이 옮겨가도 동작하도록 class나 method를 public으로 작성하라?
	public static void main(String[] args) {
		// 7. 이제 정의한 메소드를 main메소드에서 부르기만 하면 되는데, static한 메인에서 non-static method를 부를 수 없다.
		//runPrompt(); // Cannot make a static reference to the non-static method runPrompt() from the type Prompt
		// 8. **대박**: 지속적으로 static을 안붙이는 메소드를 통해 public class의 객체생성후 -> 객체.메소드를 통해, 정의한 메소드를 호출한다.
		//  -> static한 main에서의 실행은 중요하지 않나보다. 객체 생서후 호출위주로가보자.
		Prompt p = new Prompt(); // 8-1. non-static 메소드 호출을 public Class객체 생성후 객체에서 해준다.
		p.runPrompt();
		
		//5. 메인메소드 내용 전체를 메소드 1개에 축약하기 잘라내서 메소드로 가져갈 준비를 하자.
//		//String PROMPT = "cal> ";
//		Scanner scanner = new Scanner(System.in);
//		Calendar cal = new Calendar();
//
//		int month = 1;
//
//		while (true) {
//			System.out.println("달을 입력하세요.");
//			System.out.print(PROMPT);
//			month = scanner.nextInt();
//			if (month == -1) {
//				break;
//			}
//			if (!(1 <= month && month <= 12)) {
//				System.out.println("1~12사이의 값을 입력하세요.");
//				continue;
//			}
//			System.out.printf("%d월은 %d일 까지 있습니다.\n", month, cal.getMaxDaysOfMonth(month));
//		}
//		System.out.println("Bye~!");
//
//		scanner.close();

	}

}
