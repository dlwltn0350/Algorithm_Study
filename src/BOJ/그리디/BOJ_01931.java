package BOJ.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


/**

@author jisoo
@since 2022. 8. 16.
@see https://www.acmicpc.net/problem/1931 회의실 배정
@performance 43772	556
@difficulty S1
@category #
@note 노션(220816) 풀이 확인 
*/
public class BOJ_01931 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		Meeting[] meetings = new Meeting[N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			meetings[i] = new Meeting(a,b);
		}
		
		//List<Meeting> result = getSchedule(meetings);
//		for(Meeting meeting : result) {
//			System.out.println(meeting.start + " "+meeting.end);
//		}
		 getSchedule(meetings);
	}
	
	private static void getSchedule(Meeting[] meetings){
		
		List<Meeting> result = new ArrayList<Meeting>();
		Arrays.sort(meetings);
		result.add(meetings[0]);//첫번째 최적의 해 (첫회의 스케쥴에 추가)
		
		for(int i=1; i<meetings.length; i++) {
			if(result.get(result.size()-1).end <= meetings[i].start) {
				result.add(meetings[i]);
			}
		}
		
		System.out.println(result.size());
		//return result;
	}
	
	static class Meeting implements Comparable<Meeting>{
		int start, end;
		
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) { //종료시간으로 기준으로 오름차순
			//종료시간이 같다면 시작시간 기준으로 오름차순
			
			if(o.end == this.end) {
				return Integer.compare(o.start, this.start)*-1;
			}
			return Integer.compare(o.end, this.end)*-1;
			
			//return this.end != o.end ? this.end-o.end : this.start-o.start;
		}
	}
}