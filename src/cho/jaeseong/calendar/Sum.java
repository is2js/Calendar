package cho.jaeseong.calendar;

import java.util.Scanner;

public class Sum {
	public static void main(String[] args) {
		// 입력은 키보드, 출력은 두수의 합을 출력한다.
		// 1. 두수를 받아야한다면, int 변수 2개를 미리 선언한다.
		int a, b;
		Scanner scanner = new Scanner(System.in); // 2. 원래는 파일을 읽는 것인데, System.in으로 키보드 입력을 받는다.
		// 3. 각각의 문자1개씩 입력받을 거라, String변수도 2개를 만든다.
		String s1, s2;
		// 4. 입력받기전에는 안내문을 출력해준다.
		System.out.println("두 수를 1개씩 enter로 입력해주세요.");
		s1 = scanner.next();
		s2 = scanner.next();
		// System.out.println(s1+", "+s2);
		// 5. String -> int 형변환은 안된다. c언어에서는 됨.
		a = Integer.parseInt(s1);
		b = Integer.parseInt(s2);

		// 6. 미리 계산값을 새로운 변수에 선언해놓고, println에는 변수 1개만..
		// int c = a + b;
		// System.out.println("두 수의 합은 " + c + "입니다.");

		// 7. %d부분에 들어갈 정수를 비워두고, 뒤에서 콤마로 하나씩 채운다. 계산도 콤마ㅇ후 인자에서 계산 시키면 된다.
		System.out.printf("두 수(%d와 %d)의 합은 %d입니다.", a, b, a + b);

//		=========내가 짠 것 ==========================
//		Scanner scanner = new Scanner(System.in);
//		
//		System.out.println("두 수를 입력하세요.");
//		String value = scanner.nextLine();
//		String[] values = value.split(" ");
//		
//		int first = Integer.parseInt(values[0]);
//		int second = Integer.parseInt(values[1]);
//		
//		System.out.println("두 수의 합은 " + (first + second) + "입니다.");
//		
		scanner.close();

	}
}
