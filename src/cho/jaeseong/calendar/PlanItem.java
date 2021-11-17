package cho.jaeseong.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 1. 새로운 [여러] 데이터저장용  ~Item이름의 public class를 생성한다. -> HashMap의 value로 들어간 String을 대체할 것이다.
public class PlanItem {
	// 2. 데이터 저장용 public 인스턴스 변수 Date(string아님)와 plan을 생성한다.
	// - **데이터 저장용이라도 private으로 선언해야하는게 맞다. 메소드만 public or 생성자를 public로 해서 간접적으로 저장용 인스턴스변수를 활용한다.**
	// - **외부에서 받는 것은 strDate지만,  생성자에서 -> Date -> 저장변수도 Date 타입으로 저장한다.
	// -> private으로 하면 무조건 getter/setter를 지정해야하나보다? 생성자로 바로 할당 못한다??
	// --> 생성자속this.date가 ... private Date date를 사용안한다고 느낌표.. --> 일단 public 인스턴스변수로 진행해보자..
	public Date date; // 담에는 planDate로
	public String plan; // 담에는 planDetail로..?
	// 3. String대신 객체를 저장하는 장점을 살리기 위해, 추가 데이터를 받도록 인스턴스 변수를 추가한다.
	// -> **peoples는 0명부터 추가할건데, 가변배열이 부담스러워서, 빈 문자열""으로만 초기화해놓고**
	// -> **생성자에서는 딱히 안받고 -> 생성자에선 date, plan2개만 받아오됨 -> 왜냐면 이미 초기화되어있어서 생성자시 안받아도됨.**
	// -> **my) 초기화값 없이 선언한 인스턴스 변수는..-> 생성자에서 받아서 초기화할려고 비워두는구나!! (cf final상수도 가능)**
	public String peoples = "";
	
	// 4. [생성자] 를 통해, 객체 생성시에 바로  [외부] 입력데이터 -> [객체 각 인스턴스변수]로 할당시켜 생성하도록 만들자.
	// -> **이 때, strDate -> Date로 변환과정도 필요하니, 지난시간 작성한 SimpleDateFormat 코드를 복붙해서 활용한다..**
	// -> 초기화값없이 선언된 변수2개만 생성자에서 받아 초기화하자.
	// **my) 외부에서 데이터타입 변형해주기보다, 객체만 생성시 strDate만 입력햊면, 알아서 Date로 바뀌어 저장되도록 생성자에서 구현해주자!!** 
	public PlanItem(String strDate, String plan) {
		//18. strDate->Date가 여러번쓰여서 유틸메소드(public static)으로 정의를 따로해줘서 옮기면서 주석처리
//		try {
//			this.date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
//		} catch (ParseException e) {
//			// 4. 날짜받을 때, parse시 에러날 수 있다.  날짜가 이상하게 들어와 parse안될때 무슨 메세지를 띄울까?
//			e.printStackTrace();
//		}
		
		// 24. 이제 strDate->Date를 유틸메소드로 받아와서, date변수 생성자 초기화
		this.date = PlanItem.getDateFromString(strDate);
		this.plan = plan;
	}
	
	// 5. peoples는 public 메소드에 의해  객체속 인스턴스변수에 데이터를 추가한다.(private의 이유인데 원래는..)
	public void addPeople(String name) {
		peoples += name + ","; // 빈문자열에 add함수로 문자열(name, )을 더한다.
	}
	// 6. 여러데이터 저장용 class가 정의완료 -> 가서 HashMap의 제네릭 타입 바꿔주기.
	
	// 9. Date의 getter 만들어주기
	public Date getDate()  { 
		return this.date;
	}
	
	// 16. strDate -> Date의 **유틸(static)메소드를 다른데서 땡겨쓰도록 public static(어디서든 쓰는 유틸)으로 정의하기**
	public static Date getDateFromString(String strDate) {
		//17. 생성자에서도 사용했던 코드 가져와서 여기다가 놓기
		
		//20. 
		//Date date; 
		//22.try catch에 걸려 초기화 안되고 return 될수도 있으니, 미리 값을 null로 넣어둔다.
		//**return type이 Date지만, null은 넘어가나보다!!**
		Date date = null;
		
		try {
			//19. 생성자에서 객체를 의미하는 this.를 섰었는데, 여긴 public이며 객체노상관유틸-static이라서 this 등은 몼슨다.
			// --> **아예 받아서 반환될 Date변수를 선언해주고 그걸로 받는다.**
//			this.date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
			//21.
			date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//21.
		return date;
	}
}
