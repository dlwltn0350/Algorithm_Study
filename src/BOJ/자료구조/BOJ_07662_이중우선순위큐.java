package BOJ.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**

@author jisoo
@since 2022. 12. 15.
@see https://www.acmicpc.net/problem/7662
@performance
@category #
@note pq 2개로는 시간초과가 난다. remove할 때 O(n) 연산을 수행하기 때문
	treemap은 자동으로 키가 오름차순 정렬되어 저장된다. firstKey(), lastKey()로 첫번째 원소, 마지막 원소에 접근할 수 있다.
*/
public class BOJ_07662_이중우선순위큐 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			TreeMap<Integer, Integer> treeMap = new TreeMap<>();
			
			int k = Integer.parseInt(br.readLine());
			for(int i=0; i<k; i++) {
				tokens = new StringTokenizer(br.readLine());
				
				String cal = tokens.nextToken();
				int value = Integer.parseInt(tokens.nextToken());
				if(cal.equals("I")) {
					treeMap.put(value, treeMap.getOrDefault(value, 0) + 1); // 현재 저장된 공통된 값이 있다면 개수 늘리기
				}else if(cal.equals("D") && !treeMap.isEmpty()){
					if(value == -1) {
						//최솟값 삭제
						int min = treeMap.firstKey();
						if(treeMap.get(min) == 1) { // 공통된 값이 하나만 존재한다면
							treeMap.remove(min);
                        }else {
                        	treeMap.put(min, treeMap.get(min) - 1);
                        }
						
					}else {
						int max = treeMap.lastKey();
						if(treeMap.get(max) == 1) {
							treeMap.remove(treeMap.lastKey());							
						}else {
							treeMap.put(max, treeMap.get(max) - 1); //하나 줄이기
						}
					}
				}
			}
			
			if(treeMap.isEmpty()) sb.append("EMPTY").append("\n");
			else sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}