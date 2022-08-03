package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author jisoo
 * @see https://www.acmicpc.net/problem/14719 빗물
 * @performance
 * @difficulty Gold5
 *
 */
public class BOJ_14719 {
	
	static int[] map;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int H = Integer.parseInt(str[0]); //세로 (나올 수 있는 최대높이)
		int W = Integer.parseInt(str[1]); //가로 (영역의 끝)
		map = new int[W];
		
		String[] str2 = br.readLine().split(" ");
		for(int i=0;i<str2.length;i++) {
			map[i]=Integer.parseInt(str2[i]);
		}
		
		
		int i=0, j=0;
		int tmp = 0; //영역안의 블록의 갯수
		int cnt = 0;//빗물 수
		boolean check = false;
		
		for(i=0; i<W; i++) {
			for(j=i+1; j<W; j++) {
				if(!check && map[i]==map[j]) break; //똑같으면 무시
				if(map[i]<=map[j]) {
					System.out.println(i+" : "+j+" : "+tmp);
					
					cnt +=(map[i]*(j-i-1)-tmp); //세로 * 가로 넓이 - 블록이 있어서 채워지지 않는 곳
					i=j-1;
					tmp=0;
					check = false;
					break;
				}
				else {
					check=true;
				}
				tmp += map[j];
			}
		}
		System.out.println(cnt);
		
	}

}