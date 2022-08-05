package BOJ.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author jisoo
 * @see https://www.acmicpc.net/problem/2023 신기한 소수
 * @performance 11936	96
 * @category 완전탐색
 *
 */
public class BOJ_02023 {

	static int[] choosed;
	static int N;
	static StringBuilder sb = new StringBuilder();
	static int cntPrime=0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //뽑아야 하는 횟수!
		choosed = new int[N];
		//중복 순열
		permutationDup(0);
	}
	
	static void permutationDup(int cnt) {
		if(cnt == N) {
			
			for(int i=0; i<choosed.length; i++) {
				System.out.print(choosed[i]);
			}
			System.out.println(); //결과 출력
			
			return;
		}
		
		for(int i=1; i<=9; i++) { //어차피 0은 소수가 아니니까
			choosed[cnt]=i;
			
			for(int j=0; j<=cnt; j++) {
				sb.append(choosed[j]);
			}
			
			if(isPrime(sb.toString())){
				sb.delete(0, sb.length());
				permutationDup(cnt+1);
			}//앞에서 소수일때만
			sb.delete(0, sb.length());
		}
	}
	
	static boolean isPrime(String parm) {
		//2 3 5 7
		int num = Integer.parseInt(parm);
		int ten = 1;
		for(int i=0; i<parm.length()-1; i++) {
			ten*=10;
		}
		
//		if(num/ten == 2 || num/ten == 3 || num/ten == 5 || num/ten == 7) {
//			int cnt=0;
//			
//			for(int i=2; i<num; i++) {
//				if(num%i == 0) cnt++;
//			}
//			if(cnt == 0 && num!=1) return true;
//			else return false;
//		}
//		
//		else {
//			return false;
//		}
		
		
//		if(num/ten == 2 || num/ten == 3 || num/ten == 5 || num/ten == 7) {
//			//에라토스테네스의 체
//			boolean[] isPrime = new boolean[num+1];
//			Arrays.fill(isPrime , true); //미리 1~num의 숫자 모두 false라고 가정
//
//			isPrime[0]=false;
//			isPrime[1]=false;
//
//			for(int i=2; i*i<=num; i++){
//				if(isPrime[i]){
//					for(int j=i*i; j<=num; j+=i) {
//						isPrime[j] = false; //2의배수. 3의 배수. 5의 배수 등 배수를 지워감(소수가 아니므로)               
//					}
//				}
//			}
//			
//			return isPrime[num];
//		}
//		else {
//			return false;
//		}
		
		if(num/ten == 2 || num/ten == 3 || num/ten == 5 || num/ten == 7) {
			int cnt=0;
			
			for(int i=2; i<=Math.sqrt(num); i++) { //N의 약수는 무조건 sqrt(N)의 범위에 존재한다.
				if(num%i == 0) cnt++;
			}
			if(cnt == 0 && num!=1) return true;
			else return false;
		}
		
		else {
			return false;
		}
		
	}
	

}
