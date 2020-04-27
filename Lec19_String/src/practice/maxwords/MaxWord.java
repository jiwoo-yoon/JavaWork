package practice.maxwords;

import java.util.Scanner;
import java.util.StringTokenizer;
/* MaxWrod
	여러문장으로 구성된 문자열을 입력받은뒤 
	문자열에서 가장 단어의 개수가 많은 문장을 찾아서, 
	그 문장과 문장의 단어의 개수를 출력
	'문장'의 구분은  .(마침표) !(느낌표) ?(물음표) 로 한다.
	'단어'구분은 '공백' 으로 한다
	
	hint : StringTokenizer,   split()  사용하면 간편할수도..

	입력예]
	We test coders. Give us a try. Can you make it out? It's awesome.
	
	출력예]
	5개
	Can you make it out
 */
public class MaxWord {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		String maxline = null; // 단어개수가 제일 많은 문자열
		int wordcnt = 0; // 그 문자열의 단어개수
		
		String line = sc.nextLine(); // 엔터키는 line
		
		
		StringTokenizer tk1 = new StringTokenizer(line, ".?!"); // 문장 추출
		while(tk1.hasMoreTokens()) {
			String sentence = tk1.nextToken().trim();  //문장을 담는 변수
			String [] words = sentence.split("\\s+");  // 단어를 배열에 담는다
			if(words.length > wordcnt) {   // 단어의 개수로 비교
				maxline = sentence;     // 여기다가 최대 문장을 담고 
				wordcnt = words.length; //개수로 계속 비교해나간다
			} // end if
		} // end while
		
		if(maxline != null) {
			System.out.println(wordcnt + "개");
			System.out.println(maxline);
		} // end if
		
		sc.close();
	} // end main
} // end class