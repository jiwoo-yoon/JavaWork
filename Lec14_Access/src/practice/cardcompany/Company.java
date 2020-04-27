package practice.cardcompany;

import com.lec.java.static03.Test;

public class Company {

	private int sNum = 10000; //카드 번호
	
	private Company() {} // 기본 생성자
	
	private static Company instance = null;
	
	public static Company getInstance() {
		if(instance == null) { // 기존에 instance 가 있는지 확인
			instance = new Company(); // 인스턴스 생성
		}
		return instance; // 만약에 null값이 아니면 기존에 있는 값을 보여준다
	}
	
	public Card createCard() {
		sNum++;
		return new Card(sNum);
	}

		
} // end class
