package phonebook01.class01;

import java.util.Scanner;

public class PhonebookMain {

	Scanner sc;
	PhonebookModel[] bookData = new PhonebookModel[5];

	public static void main(String[] args) {

		PhonebookMain app = new PhonebookMain();
		app.init(); // 초기화
		app.run(); // 실행
		app.exit(); // 종료

	}// end main

	// 응용프로그램을 초기화하는 메소드
	public void init() {
		sc = new Scanner(System.in);
	}

	// 응용프로그램을 구동하는 메소드
	public void run() {
		System.out.println("전화번호부 v1.0");

		while (true) {
			showMenu();// 메뉴표시

			int menu = sc.nextInt();// 메뉴입력
			sc.nextLine(); // 버퍼비우기

			switch (menu) {
			case 1:
				System.out.println("전화번호부를 입력합니다.");
				insertPhoneBook();
				break;
			case 2:
				System.out.println("전화번호부를 출력(열람)");
				showPhoneBook();
				break;
			case 3:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못입력했유");
			}
		} // end while
	}// end run

	// 응용프로그램을 종료하는 메소드
	public void exit() {

		sc.close();
	}
	
	//전화번호부 입력하는 메소드
	public void insertPhoneBook() {
		//TODO
		//먼저 전화번호부가 다 찼는지 체크 다 찼으면 입력불가 처리
		int i;
		for(i = 0; i<bookData.length; i++) {
			if(bookData[i] == null) break;
		}
		if(i == bookData.length) {
			System.out.println("전화번호부가 다 찼어");
			return;
		}
		//아니면 이름, 전화번호, 이메일 입력
		System.out.println("이름 입력");
		String name = sc.nextLine();
		System.out.println("번호 입력");
		String phoneNum = sc.nextLine();
		System.out.println("메일 입력");
		String email = sc.nextLine();
		// --> PhonebookModel 인스턴스 생성
		PhonebookModel p1 = new PhonebookModel(name, phoneNum, email);
		p1.setUid(i);
		// 배열에 추가
		bookData[i] = p1;
		System.out.println((i+1) + "번째 전화번호부 추가 성공");
	}
	//전화번호부 출력하는 메소드
	public void showPhoneBook() {
		//TODO
		int i;
		for(i = 0; i<bookData.length;i++) {
			PhonebookModel p1 = bookData[i];
			if(p1 == null)break;
			
			System.out.println(p1);
		}
		System.out.println(i+"개의 전화번호부 출력");
	}
	
	
	
	public void showMenu() {
		System.out.println();
		System.out.println("전화번호부 프로그램");
		System.out.println("------------------");
		System.out.println("[1] 입력");
		System.out.println("[2] 열람");
		System.out.println("[3] 종료");
		System.out.println("------------------");
		System.out.print("선택: ");
	}

}// end class
