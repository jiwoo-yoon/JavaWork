package phonebook06.db;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import oracle.core.lmx.CoreException;

// CONTROLLER 객체
//   어플리케이션의 동작, 데이터 처리(CRUD), (Business logic 담당)

public class PhonebookManager implements Pb, Closeable {
	
	//TODO
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// singleton적용
	private PhonebookManager() {
		
		//TODO
		//JDBC 프로그래밍
		try {
			//클래스로딩
			Class.forName(DRIVER);
			//커넥션 연결
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//Statement 필요하다면 생성
		
		
	}
	private static PhonebookManager instance = null;
	public static PhonebookManager getInstance() {
		if (instance == null) {
			instance = new PhonebookManager();
		}
		return instance;
	} // end getInstance()	
	
	
	
	// 전화번호부 생성 등록
	@Override
	public int insert(String name, String phoneNum, String memo) {
		
		// 매개변수 검증 : 이름 필수
		if(name == null || name.trim().length() == 0) {
			throw new PhonebookException("insert() 이름입력오류: ", Pb.ERR_EMPTY_STRING);
		}
		
		//TODO
		//SQL_INSERT 사용하여 작성
		//PreparedStatement 작성
		int cnt = 0;
		
			try {
				pstmt = conn.prepareStatement(SQL_INSERT);
				pstmt.setString(1, name);
				pstmt.setString(2, phoneNum);
				pstmt.setString(3, memo);
				cnt = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
			return cnt;
	
		
	}

	@Override
	public PhonebookModel[] selectAll() {
		 ArrayList<PhonebookModel> pblist = new ArrayList<PhonebookModel>();
		//TODO
		//SQL_SELECT_ALL 사용
		//LIST<> 의 toArray
		try {
			
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String date = new SimpleDateFormat("yyyy년MM월dd일").format(rs.getDate(COL_LABEL_REGDATE)) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(rs.getTime(COL_LABEL_REGDATE));
				Date ndate = new SimpleDateFormat("yyyy년MM월dd일 hh:mm:ss").parse(date);
				
				int uid = rs.getInt(COL_LABEL_UID);
				String name = rs.getString(COL_LABEL_NAME);
				String phonenum = rs.getString(COL_LABEL_PHONENUM);
				String memo = rs.getString(COL_LABEL_MEMO);
				//Date regdate = rs.getDate(COL_LABEL_REGDATE);
				pblist.add(new PhonebookModel(uid,name,phonenum,memo,ndate));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		PhonebookModel[] pb = new PhonebookModel[pblist.size()];
//		for (int i = 0; i < pb.length; i++) {
//			pb[i] = pblist.get(i);
//		}
		return pblist.toArray(new PhonebookModel[pblist.size()]);
	}

	// 특정 uid 의 데이터 검색 리턴
	// 못찾으면 null 리턴
	@Override
	public PhonebookModel selectByUid(int uid) {
		
		//uid를 count, 폰북모델에서 특정 유아이디값이 있으면 리턴해주고
		PhonebookModel p1 = null;
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_UID); //쿼리문을담아야되고
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			if(rs.next()) { // 존재한다면, 이건 한번만 확인하면 되니깐 while해줄 필요가 x
				p1 = new PhonebookModel();
				p1.setUid(uid);
				p1.setName(rs.getString(COL_LABEL_NAME));
				p1.setPhoneNum(rs.getString(COL_LABEL_PHONENUM));
				p1.setMemo(rs.getString(COL_LABEL_MEMO));
				p1.setRegDate(rs.getDate(COL_LABEL_REGDATE));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return p1;  // 못찾으면 null 리턴
	}// end selectByUid()

	@Override
	public int updateByUid(int uid, String name, String phoneNum, String memo) {
		
		// 매개변수 검증
		if(uid < 1)
			throw new PhonebookException("update() uid 오류: " + uid, Pb.ERR_UID);
		
		if(name == null || name.trim().length() == 0) // 이름 필수
			throw new PhonebookException("update() 이름입력 오류: ", Pb.ERR_EMPTY_STRING);
		
		int cnt = 0;
		
		//TODO
		//SQL_UPDATE_BY_UID 사용
		try {
			pstmt = conn.prepareStatement(SQL_UPDATE_BY_UID);
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNum);
			pstmt.setString(3, name);
			pstmt.setInt(4, uid);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return cnt;
	}

	@Override
	public int deleteByUid(int uid) {
		// 매개변수 검증
		if(uid < 1) 
			throw new PhonebookException("deleteByUid() uid 오류: " + uid, Pb.ERR_UID);
		
		int cnt = 0;
		
		//TODO
		//SQL_DELETE_BY_UID 사용
		try {
			pstmt = conn.prepareStatement(SQL_DELETE_BY_UID);
			pstmt.setInt(1, uid);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	// 현재 데이터중 가장 큰 uid 값을 찾아서 리턴
	private int getMaxUid() {
		int maxUid = 0;
		
		//TODO 옵션
		
		return maxUid;
	}
	

	@Override
	public void close() throws IOException {
		
		//TODO
		//ReusltSet
		//Statement
		//Connection
		// close() 시키기
		
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	

} // end PhonebookManager

// 예의 클래스 정의
// 예외발생하면 '에러코드' + '에러메세지'를 부여하여 관리하는게 좋다.
class PhonebookException extends RuntimeException {
	
	private int errCode = Pb.ERR_GENERIC;
	
	public PhonebookException() {
		super("Phonebook 예외 발생");
	}
	
	public PhonebookException(String msg) {
		super(msg);
	}
	
	public PhonebookException(String msg, int errCode) {
		super(msg);
		this.errCode = errCode;
	}
	
	
	// Throwable 의 getMessage 를 오버라이딩 가능
	@Override
	public String getMessage() {
		String msg = "ERR-" + errCode + "]" + Pb.ERR_STR[errCode] +
					" " + super.getMessage();
		return msg;
	}
	
} // end PhonebookException