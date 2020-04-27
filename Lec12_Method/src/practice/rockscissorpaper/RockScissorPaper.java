package practice.rockscissorpaper;

import java.util.Scanner;

/*
	 간단한 가위, 바위, 보 게임을 만듭니다.
	(실행화면은 다음 페이지에)
	
	showMenu / inputChoice / displayChoice
	/ computeResult  메소드들을 어떻게 
	구현해볼수 있을까요? 
	
	
	main() 메소드는 오른쪽과 같이 구성하고
	변경하지 않는 상태에서
	나머지 메소드들을 구현하여 완성해보세요
	
	필요하다면 클래스의 멤버변수등을 추가해도
	좋습니다
 */
public class RockScissorPaper {
	public static void main(String[] args) {
		System.out.println("가위 바위 보 게임");
		Scanner sc = new Scanner(System.in);

		while (true) {
			showMenu(); // 메뉴보여주기

			int userChoice = inputChoice(sc); // 사용자 입력
			if (userChoice == 0) {
				break; // 0 이면 종료
			}

			// 컴퓨터 선택: 1 - 가위, 2- 바위, 3 - 보
			int computerChoice = (int) Math.floor(Math.random() * 3) + 1;
			displayChoice(userChoice, computerChoice); // 양측의 선택 보여주기
			computeResult(userChoice, computerChoice); // 승부결과 보여주기
		}
		sc.close();
	} // end main()

	//
	public static void showMenu() {
		System.out.println("-------------");
		System.out.println("1]가위");
		System.out.println("2]바위");
		System.out.println("3]보");
		System.out.println("0] 종료");
		System.out.println("-------------");
		System.out.print("선택:");
	} // end showMenu()

	//
	public static int inputChoice(Scanner sc) {
		int choice = 0;

		while (true) {
			choice = sc.nextInt();
			if (0 <= choice && choice <= 3) {
			}

			return choice;
		}

	} // end inputChoice()

	//
	public static void displayChoice(int user, int com) {
		System.out.println("사용자 VS COM");

		if (user == 1)
			System.out.print("가위 VS");
		else if (user == 2)
			System.out.print("바위 VS");
		else
			System.out.print("보 VS");

		if (com == 1)
			System.out.println(" 가위");
		else if (com == 2)
			System.out.println(" 바위");
		else if (com == 3)
			System.out.println(" 보");

	} // end displayChoice()

	//
	public static String getHandType(int choice) {
		String type = "";
		return type;
	} // end getHandType()

	//
	public static void computeResult(int user, int com) {

		if (user == com) {
			System.out.println("비김");
		} else if ((user == 1 && com == 3) || (user == 2 && com == 1) || (user == 3 && com == 2)) {
			System.out.println("사람 이김");
		} else {
			System.out.println("com 이김");
		}

	} // end computeResult()

} // end class
