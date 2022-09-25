package Programmers.def;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 24.
@see
@performance
@difficulty 
@category #
@note */
public class 이모티콘 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int[] discount = {10,20,30,40};//10, 20, 30, 40
	static HashMap<Integer, Integer> result = new HashMap<>();
	

	public static void main(String[] args) throws IOException {
		int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
		//x퍼 이상 이모티콘만 구매
		int[] emoticons = {1300,1500,1600,4900};
		solution(users,emoticons);
	}
	
	public static int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        permutationDup(0, new int[emoticons.length], users, emoticons);
        int max = -1;
        for(int keys : result.keySet()) {
        	if(max<keys) {
        		max = keys;
        	}
        }
        
        answer[0] = max;
        answer[1] = result.get(max);
        
        System.out.println(answer[0]+":"+answer[1]);
        return answer;
    }
	
	//중복순열
	public static void permutationDup(int nth, int[] choosed, int[][] users, int[] emoticons) {
		if(nth == emoticons.length) {
			//각 이모티콘에 할인율을 어떻게 적용할건지
			
			int[][] temp = new int[users.length][2];
			for(int i=0; i<users.length; i++) {
				for(int j=0; j<2; j++) {
					temp[i][j] = users[i][j];
				}
			}
			
			int price = 0;
			for(int i=0; i<emoticons.length; i++) {
				for(int j=0; j<temp.length; j++) {
					if(temp[j][0]<=choosed[i]) {
						temp[j][1] -= ((emoticons[i] * (100-choosed[i]))/100);//할인율 적용 가격
						price += ((emoticons[i] * (100-choosed[i]))/100); //총 판매금액
//						System.out.println((emoticons[i] * (100-choosed[i]))/100);
					}
				}
			}
			
			int register = 0;
			for(int i=0; i<users.length; i++) {
				if(temp[i][1]<=0) {
					register ++;
					price -= (users[i][1]-temp[i][1]);
				}
			}
			
			if(result.containsKey(register)) {
				if(price>result.get(register)) {
					result.replace(register, price);
				}
			}else {
				result.put(register, price);
			}
			
			
			return;
		}
		
		for(int i=0; i<discount.length; i++) {
			choosed[nth] = discount[i];
			permutationDup(nth+1,choosed,users,emoticons);
			
		}
	}
}