package practice.wordcount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 1] 문서(문자열) 안의 단어의 빈도수를 계수해서 출력하기
 * 	- 대소문자 구분하지 않기 :   The 와 the 는 같은 단어
 *  - 2글자 이상만 계수하기
 *  - 구두점/기호 ",.\"\'`!?;:-()" 잘라내기
 *  - 공백 짤라내기
 * ex)
 * 	an : 234
 * 	the : 314
 * ...
 * 
 * hint]
 * 	split("\\s+")  --> String[]  
 * 	--> StringTokenizer  (혹은 정규표현식)
 *  	  --> HashMap<String, Integer>   <단어, 빈도수>  사용
 * ───────────────────────────────────────────────────────────    
 * 2] 빈도수 내림차순으로 정렬하여 출력하기
 * 	ex)
 *	1 the:113개
 *	2 she:95개
 *	3 to:85개
 *	...   
 *
 * hint]
 * 	Comparable<> 이나 Comparator<> 적용
 */

// TODO : 필요한 객체들 작성
// hint> 빈도수 담을 객체, Comparator<> ..

public class AliceInWonderland {

	public static void main(String[] args) {
		System.out.println("실습: 단어 발생 빈도");
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();

		String[] words = C.ALICE30.trim().toLowerCase().split("\\s+");// 공백, 대소문자 구분까지했고
		
		
		String x = "";
		for (int i = 0; i < words.length; i++) {
			x += words[i] + ",";
		}
			StringTokenizer y = new StringTokenizer(x, ",.\\\"\\'`!?;:-()_*", false);
			while (y.hasMoreTokens()) {
				String k = y.nextToken();

				for (int j = 1; j < k.length()-1; j++) {
					Integer v = hmap.get(k);
					if (v == null) {
						hmap.put(k, 1);
					} else {
						hmap.put(k, v + 1);
					}
				}
			}

		
		
		// 발생빈도 작성
		
//			StringTokenizer y = new StringTokenizer(x, ",.\\\"\\'`!?;:-()_*", false);
//			while (y.hasMoreTokens()) {
//				String k = y.nextToken();
//
//				for (int i = 1; i < k.length() && k.length() >= 2; i++) {
//					Integer v = hmap.get(k);
//					if (v == null) {
//						hmap.put(k, 1);
//					} else {
//						hmap.put(k, v + 1);
//					}
//				}
//			}
		
		
		List<Entry<String, Integer>> l2 = new ArrayList<Entry<String, Integer>>(hmap.entrySet());
		
		Collections.sort(l2, new Comparator<Entry<String, Integer>>(){
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		for(Entry<String, Integer> e : l2) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class
// for (Map.Entry e : hmap.entrySet()) {
//	System.out.println(e.getKey() + " : " + e.getValue());
//}

//List<Map.Entry<String, Integer>> list = new LinkedList<>(hmap.entrySet());
//
//Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
//
//	@Override
//	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
//		int comparision = (o1.getValue() - o2.getValue()) * -1;
//		return comparision;
//	}
//	
//});
//
//Map<String, Integer> sortedMap = new LinkedHashMap<>();
//for(Iterator<Map.Entry<String, Integer>> itr = list.iterator(); itr.hasNext();) {
//	Map.Entry<String, Integer> entry = itr.next();
//	sortedMap.put(entry.getKey(), entry.getValue());
//}
//
//System.out.println(sortedMap);