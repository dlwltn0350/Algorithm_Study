package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_01094 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int X;
	static List<Integer> list; //막대들을 보관할 리스트
	static int div; //이등분했을 때 막대 길이
	
	public static void main(String[] args) throws IOException {
		//int T = Integer.parseInt(br.readLine()); //테스트케이스 개수 입력
		int T = 1;
		for(int tc = 1; tc<=T; tc++) {
			X = Integer.parseInt(br.readLine());
			list = new ArrayList<>();
			list.add(64); //처음에는 64 막대 하나만 가지고 있다.
			
			while(true) {
				int size = 0; //현재 남아있는 모든 막대의 길이의 합
				for(int i=0; i<list.size(); i++) {
					size += list.get(i);
					
				}
				if(size == X) break;
				
				//가지고 있는 막대 중 길이가 가장 짧은 막대를 이등분한다.
				Collections.sort(list); //작은 막대 순으로 정렬한다.
				div = list.get(0) / 2; //이등분한다.
				list.remove(0); //이등분하기전 막대는 지워버린다.
				list.add(div);
				list.add(div); //막대가 이등분되었으니까 두번 추가
				
				size = 0;
				for(int i=0; i<list.size()-1; i++) { //이등분한 막대 중 하나를 제외하기 위해 list.size()-1
					size += list.get(i); //
				}
				
				//만약, 위에서 이등분한 막대 중 하나를 제외한 나머지의 모든 막대의 길이의 합이 X보다 크거나 같다면, 제외한 조각을 버린다.
				if(size>=X) list.remove(list.size()-1);//이등분한 막대 중 하나를 제외한다.
				
				//그러나 위에서 이등분한 막대 중 하나를 제외한 나머지의 모든 막대의 길이의 합이 X보다 작다면 버리지 않고 위 작업을 반복
				continue;
			}
			
			//sb.append("#").append(tc).append(" ").append(list.size()).append("\n");
		}
		System.out.println(list.size());
		//System.out.println(sb.toString());
	}
}
