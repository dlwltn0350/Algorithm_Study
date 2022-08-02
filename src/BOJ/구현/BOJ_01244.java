package BOJ.구현;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * @author jisoo
 * @see https://www.acmicpc.net/problem/1244
 * @performance 15916	144
 * @category #단순구현
 * @memo
 *
 */
public class BOJ_01244 {

	static int n;
	static int[] status;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		
		String[] tmp = br.readLine().split(" ");
		status = new int[n];
		for(int i=0;i<n;i++) {
			status[i]=Integer.parseInt(tmp[i]);
		}
		
		int m = Integer.parseInt(br.readLine()); //학생수
		for(int i=0; i<m; i++) {
			String[] line = br.readLine().split(" ");
			int sex = Integer.parseInt(line[0]);
			int random = Integer.parseInt(line[1]);
			
			
			if(sex==1) { //남자라면 배수번째 switch
				Man(random-1,random);
			}
			else {
				Woman(random-2,random);
				status[random-1]=swap(status[random-1]);
			}
		}
		
		for(int i=0;i<n;i++) {
			sb.append(status[i]+" ");
			if((i+1)%20==0) sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void Man(int index, int random) {
		if(index>=n) {
			return ;
		}
		status[index]=swap(status[index]);
		Man(index+random, random);
	}
	
	static void Woman(int x, int y) {
		if(x<0 || y>=n) return;
		
		if(status[x]==status[y]) {
			//System.out.println(x+" : "+y);
			status[x]=swap(status[x]);
			status[y]=swap(status[y]);
			Woman(x-1,y+1);
		}	
	}
	
	static int swap(int a) {
		if(a==1) return 0;
		else return 1;
	}

}
