package com.lec.java.regexp03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 정규표현식 에 사용하는 각종 표현식들
 * 	정규표현식		설명
 * 	^			문자열 시작
 * 	$			문자열 종료
 * 	.			임의의 문자 [단 ‘'는 넣을 수 없습니다.]
 * 	*			앞 문자가 0개 이상의 개수가 존재할 수 있습니다.
 * 	+			앞 문자가 1개 이상의 개수가 존재할 수 있습니다.
 * 	?			앞 문자가 없거나 하나 있을 수 있습니다.
 * []			문자의 집합이나 범위를 표현합니다. -기호를 통해 범위를 나타낼 수 있습니다. ^가 존재하면 not을 나타냅니다.
 * {}			횟수 또는 범위를 나타냅니다.
 * ()			괄호안의 문자를 하나의 문자로 인식합니다. (그룹)
 * |			패턴을 OR 연산을 수행할 때 사용합니다.
 * \s			공백 문자
 * \S			공백 문자가 아닌 나머지 문자
 * \w			알파벳이나 숫자
 * \W			알파벳이나 숫자를 제외한 문자
 * \d			[0-9] 숫자
 * \D			숫자를 제외한 모든 문자
 * (?i)			대소문자를 구분하지 않습니다.
 * 
 * 
 * 자바 정규표현식에 사용되는 escaped character 들
 *    \.[]{}()<>*+-=!?^$|
 */
public class RegExp03Main {

	public static void main(String[] args) {
		System.out.println("정규표현식\n");

		String regex, intput, title;
		String [] arrInput;
		
		//─────────────────────────────────────────
		title = "^ : 바로 문자뒤의 문자열로 시작됨";
		regex = "^The"; //The 로 시작하는 문자열패턴
		arrInput = new String[] {
				"The Things",
				"On The Things",
				" The The The", // 공백있으면 x
				"The The the The" // 문자열이기때문에 한개나옴
		};
		
		
		//─────────────────────────────────────────
		title = "$ : 문자열의 마지막이 이 문자열로 마무리 됨";
		regex = "Man$"; // Man으로 끝나는 문자열 패턴 
		arrInput = new String[] {
				"SuperMan",
				"AquaMain",
				"WonderWoman",
				"WonderWoMan",
				"PostMan " // 공백있으면x
		};
					
		//─────────────────────────────────────────
		title = "^표현식$ : 정확하게 전체패턴매칭되는 문자열";
		regex = "^Su...Man$"; 
		arrInput = new String[] {
				"SuperMan",
				"SugarMan",
				"Super Man",
				" SuperMan",
				"SuperMan ",
		};
		//─────────────────────────────────────────
		title = " . : 어떤 문자든지 임의의 '한문자'를 말한다.꼭 하나의 문자와 매칭";
		regex = "x.y"; // 첫번째는 x, y로 끝나는 문자열
		arrInput = new String[] {
				"xyz",
				"xxzdfdk",
				"aa10x9zbxbz",
				"xz",
				"90x zxx_zdf",  // 공백도 하나의 문자다! 매칭된다
				"xbz",				
				"xyyz xyxyxyx yyyxyxyxy xyxyxyyyxyxyyy"
		};
		
		//─────────────────────────────────────────
		title = " * : 바로 앞의 문자가 없거나 한개 이상의 경우를 매칭";
		regex = "ab*"; // a로 시작 b는 있거나없거나 있어도 여러개가능
		arrInput = new String[] {
				"a",
				"abc",
				"ab",
				"abbbaaaabababbab",
				"bbba",
				"cdef"
		};
		
		//─────────────────────────────────────────
		title = " + : 바로 앞의 문자를 나타내면 꼭 한개 혹은 그 이상을 매칭";
		regex = "ab+"; // a로시작, b는 반드시 한개이상 있어야됨
		arrInput = new String[] {
				"a",
				"abc",
				"ab",
				"abbbaaaabababbab",
				"bbba",
				"cdef"
		};
		
		//─────────────────────────────────────────
		title = " ? : 바로 앞의 문자가 한개 있거나 없는것을 매칭";
		regex = "ab?"; // a로시작 b는 없거나 있으면 한개만
		arrInput = new String[] {
				"a", 
				"abc", 
				"kkabcc", 
				"abbbaaaabababbab",
				"bbba"
		};

		//─────────────────────────────────────────
		title = " [] : 안에 존재하는 문자들중 한 문자만을 매칭";
		regex = "[abc]"; // a or b or c 중 한 문자씩 계속 매칭
		arrInput = new String[] {
				"able",
				"bible",
				"cable",
				"xenosys",	
		};
		regex = "[abc]+"; // a b c 한글자이거나 연결된 쌍들
		regex = "[a-z]+"; // a 부터 z 까지([a-z]) 문자 1개이거나 연결된 쌍들
		arrInput = new String[] {
				"abc100",
				"abcDefGHIUJ-KLM123opQrstuz"
			};
		
		regex = "[a-zA-Z]+"; // 소문자 대문자로 구성된 
		regex = "[a-zA-Z0-9]+";
		regex = "[a-zA-Z0-9-]+"; // 뒤에 - 붙이면 - 까지포함
		
		arrInput = new String[] {
				"23",
				"0",
				"-10",
				"012"
		};
		regex = "^[1-9][0-9]*$"; // 자연수표현식(첫째 자릿수는 1부터9 둘째자릿수부터는 있거나 없거나)
		//─────────────────────────────────────────
		title = " {} : 앞에 있는 문자나 문자열의 등장개수를 정함";
		regex = "ab{2}"; // a로시작 b가 2개 매칭
		arrInput = new String[] {
				"abb",
				"abbb",
				"abbbabbbbbbbbabaabab",
		};
		regex = "ab{2,}"; // b 의 개수가 2개 이상
		regex = "ab{3,5}"; // b 의 개수가 3이상 5이하 
		
		//─────────────────────────────────────────
		title = " () : ()안에 있는 글자들을 그룹화 ";
		regex = "a(bc)*"; // (bc) 가 없거나 하나이상
		arrInput = new String[] {
				"abc",
				"abcbcbbc",
				"abcabcabc",
		};
		
		//─────────────────────────────────────────
		title = " | : OR 연산자  역할";
		regex = "a|b"; // a 또는 b 둘중 하나
		arrInput = new String[] {
				"a",
				"b",
				"ab",
				"xyz",
				"acbacbacbacbacbacbacbacbacbacbaaabbccc"
		};
		regex = "(a|b)+";
		
		//─────────────────────────────────────────
		title = "(?i)  : 대소문자 구분안하고 매칭 ";  // 타 언어 정규표현식과 다름
		regex = "(?i)abc"; // 
		arrInput = new String[] {
				"abc",
				"Abc",
				"ABC"
		};
		
		//─────────────────────────────────────────
		title = "\\s : 공백,  \\S : 공백아닌 문자";
		regex = "\\s+"; // 공백(스페이스바, 줄바꿈 , 탭) 한개이상 연결된 쌍(공백을표현)
		arrInput = new String[] {
				"Hello My World",
				"He \tllo My World",
				"\n\t Hello My World\n\n",
		};
		regex = "\\S+";
		
		//─────────────────────────────────────────
		title = "\\w : 알파벳이나 숫자, \\W 알파벳이나 숫자를 제외한 문자";
		regex = "\\w+"; // 
		arrInput = new String[] {
				"This is 2020-03-23 !!"
		};
		regex = "\\W+";

		//─────────────────────────────────────────
		title = "\\d : [0-9] 숫자, \\D 숫자를 제외한 모든 문자";
		regex = "\\d+"; //
		arrInput = new String[] {
				"This is 2020-03-23 !!"
		};
		regex = "\\D+";
		
		//─────────────────────────────────────────
		title = "escaped character 매칭 시키기";
		//regex = ".+";
		regex = "[.]+"; // 점을 매칭시키고 싶으면~
		arrInput = new String[] {
				"My name is ..."
		};
		
		//*****************************************
		// 패턴매칭 수행
		System.out.println(title);
		regExpTest(regex, arrInput);

		System.out.println("프로그램 종료");
	} // end main()
	
	// 도우미 함수
	public static void regExpTest(String regex, String [] arrInput) {
		for(String input : arrInput) regExpTest(regex, input);
	}
	
	public static void regExpTest(String regex, String input) {
		System.out.println("[정규표현식 매칭 테스트]-----------------");
		System.out.println("정규표현식: " + regex);
		System.out.println("입력문자열: " + input);
		
		Matcher matcher = Pattern.compile(regex).matcher(input);
		int groupCount = matcher.groupCount();  // 그룹 개수
		
		int matchCount = 0;		
		while(matcher.find()) {
			matchCount++;
			System.out.println("    매치" + matchCount + ": " + matcher.group() + " {" + matcher.start() + "~" + matcher.end() + "}");
			
			// 그룹이 있으면 group별 출력
			if(groupCount > 0) {
				for(int i = 0; i <= groupCount; i++) {	 // i 범위 주목!	
					System.out.printf("\t group(%d): %s {%d~%d}\n",
							i, matcher.group(i), matcher.start(i), matcher.end(i));
				}
			}
			
		} // end while
		if(matchCount == 0) System.out.println("   Ⅹ매치 없슴Ⅹ");
		System.out.println();
	} // end regExpTest()

} // end class
