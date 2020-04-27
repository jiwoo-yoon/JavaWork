package com.lec.java.file05;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class File05Main {

	public static void main(String[] args) {
		System.out.println("Buffered Stream + Buffer");
		// file03 패키지 참조
		// try with resource 구문으로 작성
		// in.read(buff) --> bin.read(buff);
		// out.write( , , ) --> bout.write( , , ); 사용
		// finally 필요 없슴
		
		try(
				InputStream in = new FileInputStream("temp/big_text.txt");
				BufferedInputStream bin = new BufferedInputStream(in);
				
				OutputStream out = new FileOutputStream("temp/copy_big_text.txt");
				BufferedOutputStream bout = new BufferedOutputStream(out);
				){
			
			byte [] buff = new byte[1024]; // 버퍼준비
			int lengthRead = 0; //버퍼의 읽어들인 바이트수
			int byteCopied = 0; 
			
			long startTime = System.currentTimeMillis();
			//파일복사
			while(true) {
				// 데이터 읽기
				
				lengthRead = bin.read(buff);
				if(lengthRead == -1) break;
				
				//데이터쓰기
				bout.write(buff, 0, lengthRead); 
				byteCopied += lengthRead;
			}
			
			long endTime = System.currentTimeMillis(); //끝난시간저장
			long elapsedTime = endTime - startTime; // 경과시간
			System.out.println("읽고 쓴 바이트 :" + byteCopied);
			System.out.println("경과시간(ms) :" + elapsedTime);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("\n프로그램 종료");

	} // end main()

} // end class File05Main
















