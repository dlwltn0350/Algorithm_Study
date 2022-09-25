package Programmers.def;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 24.
@see
@performance
@difficulty 
@category #
@note 
* 모든 달은 28일
* 
*/
public class 개인정보 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
//		String[] terms = {"A6","B12","C3"};
//		String[] privacies = {"2021.05.02 A","2021.07.01 B","2022.02.19 C","2022.02.20 C"};
//		solution("2022.05.19",terms,privacies);
		
		String[] terms = {"Z3","D5"};
		String[] privacies = {"2019.01.01 D","2019.11.15 Z","2019.08.02 D","2019.07.01 D","2018.12.28 Z"};
		solution("2020.01.01",terms,privacies);
	}
	
	public static List<Integer> solution(String today, String[] terms, String[] privacies) {
        //int[] answer = {};
        
        HashMap<String, Integer> storage = new HashMap<>();
        List<Integer> answer = new ArrayList<>();
        
        for(int i=0; i<terms.length; i++) {
        	storage.put(terms[i].substring(0,1), Integer.parseInt(terms[i].substring(2)));
        }
        
        int key=0, year=0, month =0, day=0;
        int tYear=0, tMonth =0, tDay=0;
        
        for(int i=0; i<privacies.length; i++) {
        	String[] temp = privacies[i].split(" ");
        	String da = temp[0].replace(".",",");
        	String[] date = da.split(",");
        	
        	
        	key = storage.get(temp[1]);
        	year = Integer.parseInt(date[0]);
        	month = Integer.parseInt(date[1]);
        	day = Integer.parseInt(date[2]);
        	
        	year += (key/12);
        	month += (key%12); //key는 개월 수 
        	if(month >12) {
        		month -=12;
        		year ++;
        	}
        	day --;
        	if(day == 0) {
        		day = 28;
        		month --;
        	}
        	
        	String sp = today.replace(".", ",");
        	String[] spl = sp.split(",");
        	tYear = Integer.parseInt(spl[0]);
        	tMonth = Integer.parseInt(spl[1]);
        	tDay = Integer.parseInt(spl[2]);
        	
        	
        	//System.out.println(year+" : "+month+" : "+day);
        	//오늘 날짜로 파기해야할 개인정보
        	if(tYear>year) {
    			answer.add(i+1);
    			continue;
    		} else if(tYear == year) {
    			if(tMonth>month) {
    				answer.add(i+1);
    				continue;
    			}else if(tMonth == month) {
    				if(tDay>day) {
    					answer.add(i+1);
    				}else if(tDay == day) {
    					
    				}
    			}
    		}
        }
        System.out.println(answer.toString());
        
        return answer;
    }
}