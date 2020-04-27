package phonebook01.class01;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PhonebookModel {
	//멤버변수
	private int uid; //unique id 고유한식별번호 
	private String name;
	private String phoneNum;
	private String memo;
	private Date regDate; //데이터생성(등록일시)
	
	public PhonebookModel() { //기본생성자
		this.name = "";
		this.phoneNum = "";
		this.memo = "";
		this.regDate = new Date(); // 생성되는 현재시간
	}

	public PhonebookModel(String name, String phoneNum, String memo) { // 매개변수 생성자
		this(); // 공통된(regDate)같은 생성자를 실행 main에서 작업할때 굳이 안껴두댐
		this.name = name;
		this.phoneNum = phoneNum;
		this.memo = memo;
	}
	
	public PhonebookModel(int uid, String name, String phoneNum, String memo, Date regDate) {
		super();
		this.uid = uid;
		this.name = name;
		this.phoneNum = phoneNum;
		this.memo = memo;
		this.regDate = regDate;
	}

	//get&set
	public String getName() {return name;}

	public void setName(String name) {this.name = name;}

	public String getPhoneNum() {return phoneNum;}

	public void setPhoneNum(String phoneNum) {this.phoneNum = phoneNum;}

	public String getMemo() {return memo;}

	public void setMemo(String memo) {this.memo = memo;}
	
	public int getUid() {return uid;}

	public void setUid(int uid) {this.uid = uid;}

	public Date getRegDate() {return regDate;}

	public void setRegDate(Date regDate) {this.regDate = regDate;}

	@Override
	public String toString() {
		String str = String.format("%3d|%s|%s|%s|%20s",
				uid, name, phoneNum, memo,
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(regDate));
		return str;
	}
	
	
	
}
