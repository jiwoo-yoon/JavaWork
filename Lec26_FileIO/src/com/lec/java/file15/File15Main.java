package com.lec.java.file15;

import java.io.File;

public class File15Main {

	public static void main(String[] args) {
		System.out.println("디렉토리 정보 확인");
		System.out.println();
		// current working directory : 현재작업경로
		String curWorkingDir = System.getProperty("user.dir");
		System.out.println("현재 작업 폴더 : " + curWorkingDir); 
		
		System.out.println();
		// 현재 작업 디렉토리의 파일 리스트 출력
		// File 클래스: 파일(txt, doc, ...) 객체 또는 디렉토리(폴더) 객체
		File curDir = new File(curWorkingDir); // 현재 작업 디렉토리 객체
		File[] list = curDir.listFiles(); // 디렉토리 안에 있는 '파일'과 '디렉토리'를  File배열로 리턴 
		
		System.out.println(list.length); // 디렉토리 5개, 파일 2개
		
		for (int i = 0; i < list.length; i++) {
			if(list[i].isDirectory()) {
				// isDirectory(): File 객체가 디렉토리이면 true 리턴
				// isFile(): File 객체가 파일이면 true 리턴
				System.out.println("DIR\t");
			}else {
				System.out.println("FILE\t");
			}
			System.out.print(list[i].getName() + "\t");
			System.out.println(list[i].length()); // 파일인 경우엔 크기를 보여줌(byte), 디렉토리인 경우엔 숫자가나와도 의미없다.
		}
		
		
		System.out.println();
		// 디렉토리의 내용 출력, enhanced for 문 이용
		//File tempDir = new File("temp"); // 지금은 괄호안에 있는 디렉토리를 찾는다, 현재작업경로기준, 상대경로
		
		//절대경로(absolute path)를 이용한 File객체 생성
		String tempDirPath = System.getProperty("user.dir") + // 현재작업경로 문자열표현 
				File.separator + // 윈도우(\), 리눅스&맥(/)
				"temp"; 
		System.out.println("절대경로 : " + tempDirPath);
		File tempDir = new File(tempDirPath);
		
		
		
		File[] l1 = tempDir.listFiles();
		
		System.out.println(l1.length);
		
		for (int i = 0; i < l1.length; i++) {
			if(l1[i].isFile()) {
				System.out.println("F\t");
			}else {
				System.out.println("D\t");
			}
			System.out.print(l1[i].getName() + "\t");
			System.out.println(l1[i].length());
		}
		
		
		
		System.out.println();
		// 파일 하나에 대한 정보
		String path = "dummy.txt";
		File f = new File(path); // 새로운파일객체 생성
		//파일객체를 생성했다고해서 물리적인파일이 생기는 것은 아님
		System.out.println("파일이름: " + f.getName()); // 정확히는 상대경로가 뽑힘, 문자열을 뽑음
		System.out.println("절대경로 : " + f.getAbsolutePath()); // 절대경로
		System.out.println("존재여부 : " + f.exists()); //물리적으로 존재하는지
		
		System.out.println("\n프로그램 종료");
		
	} // end main()

} // end File11Main

















