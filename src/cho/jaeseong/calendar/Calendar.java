package cho.jaeseong.calendar;

/*
 * 요구사항
3단계와 같지만 년도와 월만 입력받는다.
진짜 캘린더에서 나오는 달력과 똑같은 모양의 달력을 출력한다.
추가적으로 입력받아야 하는 내용이 있는지 생각해 보자
 */

public class Calendar {
	//11. 월과 index를 매칭시키기 위해 앞에 헛개비 0을 추가해준다. (1부터 채워나가며 12번 index도 쓴다.)
	//12. 헛개비 수정후 해당배열을 쓰는 쪽에서 index를 +1해준다. -> 13.
	private static final int[] MAX_DAYS = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LEAP_MAX_DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}

	public int getMaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year)) {
		
			return LEAP_MAX_DAYS[month]; //13.
		}
		return MAX_DAYS[month]; //13.
	}

	public void printCalendar(int year, int month) {
		System.out.printf("   <<%d년 %d월>>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");
		System.out.println("---------------------");
		int maxDay = this.getMaxDaysOfMonth(year, month);
		// TODO: get weekday automatically
		// 1. weekday 구하는 것을 = 0 초기화했던 것을 method로 뺀다.
		// - 년, 월, 그리고 day는 1일의 시작요일을 찾아야하므로 1로 준다.
		// int weekday = 0;
		int weekday = getWeekDay(year, month, 1);
		// 2.
		// method로 빼기로 결정한 뒤, 가정해서 할당까지 해줬으면, 메소드 자동create기능을 이용한다.
		// 이클립스는 자동완성 안되서,, 왼쪽 line에 빨간X클릭(or ctrl+1) 해서 만들어준다.

		for (int i = 0; i < weekday; i++) {
			System.out.print("   ");
		}
		for (int i = 1; i <= maxDay; i++) {
			System.out.printf("%3d", i);
			if ((i + weekday) % 7 == 0) {
				System.out.println();
			}
		}
		System.out.println();

	}

	// 3. 자동완성을 통해 만들어진 메소드다.
	// -> 이 때까지는 변수(상수)는 private method는 public으로 정의해줬는데, 여기서는 private으로 자동완성되네..
	// -> 메서드가 private이면.. 객체.메서드()를 다른데서 호출할 수 있을지?? 사실 이 함수는 같은class내 메서드에서 사용되는
	// 메서드긴한데..
	// --> 인스턴스 메서드를 private으로 정의하면, 다른데서(Prompt)에서 객체.메서드()로 안불러진다! 다른데서 부르는
	// 인스턴스메소드는 no private!!!
	private int getWeekDay(int year, int month, int day) {
		// 4. 무슨요일인지 판단은.. 1970년 1월 1일 기준으로 counting을 통해 알아낸다.
		// 1) counting을 시작하는 기준날짜의 요일은 미리 알아놔야한다. 1970년 1월 1일
		// -> 구글에서 "1970년 1월 1일 달력"을 검색한다. -> 목요일이다.
		// 2) 기준(standard)들을 변수에 담아둔다.(안쓰게 되면 지우면 됨)
		int syear = 1970;
		int smonth = 1;
		int sday = 1;
		int standardWeekday = 3; // 목요일이니까, 월화수목0123 3으로 둔다. // 1970/Jan/1st = Thursday
		// 2일이 지나면 5, 7일이 지나면 (3+7)%7 -> 3 그대로..
		// ***이렇게 [standardWekday에서 지나간 일수를 더한 뒤] -> 맨 마지막에 %7만 해주면 된다.

		// 5. [기준에서부터 지나간] 일/월/연도에 따라 [더해줄 날짜]를 계산한다.
		// ex> 연도.. 1971년 -> 365를 더한 뒤, 나누기7 .. 72년 -> 365*2 ... but 윤년이끼면 366일을 더해야한다.
		// 지난 일수-> day로 환산 / 지난 월수 -> day로 환산 / 지난 연도 전부다 해줘야하기 때문에, totalDay를 담을
		// count변수를 만든다.
		int count = 0;

		// 6. 연도부터 환산하여 totalday인 count에 더해주자. -> 시작연도부터 [포함이 아닌 전진횟수]로서 해당연도까지의 [차이]만
		// 필요하므로
		// -> i= 포함안하는 1970부터 시작 이라면, i=0시작이나 마찬가지 -> i < N까지 -> i < 해당연도 까지의 차이.
		// ex> 1970년이라면 안센다. -> 실직적으로 포함안하는 0부터 시작이다.
		// ex> 1971년이면 1번 돌아야한다. -> i=1970에서 끝나야한다. -> i < 1971
		// my) 1971부터 1루프씩 세야하는데, i=1970을 시작시켰다면 -> [ < N] 를 쓰자.
		for (int i = syear; i < year; i++) {
			// 7. 매 year마다 윤년에 따라 366을 쓸지, 365를 쓸지 그 값을 받아줄 변수를 선언한다(바로 해도 되지만.. 명확성?)
			int delta = isLeapYear(i) ? 366 : 365;
			count += delta;
		}
		// 7. 연도 -> 일로 환산해서 잘 세고 있는지 한번 찍어보자. (return은 0으로 그대로 두고 찍기만 해보자.)
		// -> **main없는 java파일에서도 메인메소드를 만들어서 찍어보면 된다.**
		//System.out.println(count);
		
		// 9. 이제 월 -> 일로 환산해서 더해주기 
		// - 횟수를 아니 for문을 쓰는데, 시작연도~끝연도를 알고 [전진횟수, 차이]만큼 돌아야한다면 i=시작연도, i< 끝연도로 1개 빼야한다.
		// - 전진횟수(차이) m-n 으로서.. 앞뒤포함갯수m-n+1에서 1개 빼야하는데, 그 빼는 것을  끝연도에서 빼도록 한다.
		// ****my) 두수의 차이만큼 반복 -> 뒤에 숫자에서 1개빠지게 하자.****
		for (int i = smonth; i < month; i++) {
			// 10. 여기서는 year가 현재 들어온 year를 기준으로.. 한다. 올해가 윤년이면.. maxday배열 2개 중 윤년배열을 써야한다.
			// **month인 i가.. 배열[i-1]로 들어가야한다. -> maxday배열에 헛개비 0을 맨앞에 주도록 수정하자.** -> 11.로 이동
			// 14.
			//int delta = isLeapYear(syear) ? LEAP_MAX_DAYS[i]:MAX_DAYS[i];
			// 15. 그러보고니.. 윤년여부에 따라 getMaxDaysOfMonth를 통해 해당월의 일수를 반환해주는 함수가 있었다! 이걸 쓰면 되는데!!
			int delta = getMaxDaysOfMonth(year, i);
			count += delta;
		}
		
		// 16. 마지막 지나간 일 -> count에 더한다.
		// 차이니까.. [숫자-숫자] (index라면 index-index)에서 반복문에서는 [시작숫자] 부터  [ < 끝숫자]까지 뒤에서 한개 빠지도록 설정한다.
		// -> 여기선 반복문이 아니라 숫자(day)와 숫자(1일)의 차이를 바로 빼서 구하면 된다.
		// -> 반복문에서는... 숫자와 숫자가 주어졌을때 차이만큼 반복하려면 식을 i=시작숫자, i <끝숫자, i++로 돌려야한다.
		count += (day - 1);
		
		//17. 이제 연도뿐만 아니라 월,일도 잘 count해주지 찍어보자.
		System.out.println(count);
		return 0;
	}

	// simple test code here
	public static void main(String[] args) {
		// 8. 메인메서드 만들어서 찍어보기 ->찍을때는 객체까지 만들어서 객체.메서드()로 찍기!
//		Calendar c = new Calendar();
//		c.getWeekDay(1970, 5, 1); // 루프 안돌아야함.
//		c.getWeekDay(1971, 5, 1); // 루프돌아서 366or365를 count에 더해야함.
//		c.getWeekDay(1972, 5, 1); // 루프돌아서 366or365 * 2를 count에 더해야함.
//		c.getWeekDay(1973, 5, 1); // 루프돌아서 366or365 * 3를 count에 더해야함.
		/*
		 * 0 365 730 1096  -> 1972년이 윤년이라.. 1973년도라면.. 1972로 돌아갈때 +366이 더해진다.
		 * 
		 */
		
		//18. 연+월+일 [기준에서부터] 지나간 일수 다 잘 더해주는지
		//1) 기준일부터 찍어보자. -> 기준일은 0일 지나갔으니 weekday가 0이 나와야한다...?
		Calendar cal = new Calendar();
		cal.getWeekDay(1970, 1, 1); // 0
		cal.getWeekDay(1970, 1, 2); // 1
		cal.getWeekDay(1970, 1, 3); // 2
		
		
		
		
		
	}

}
