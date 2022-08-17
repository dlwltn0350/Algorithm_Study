import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//순조부
public class PCS {
    static char[] src = { 'a', 'b', 'c', 'd' };

    public static void main(String[] args) {
        // 1. src에서 3개를 뽑아서 만들 수 있는 순열을 모두 출력하시오.
        System.out.println("순열");
        //makePermutation(0, new char[3], new boolean[src.length]);

        // 2. src에서 3개를 뽑아서 만들 수 있는 조합을 모두 출력하시오.
        System.out.println("조합");
        makeCombination(0,new char[3], 0);

        // 3. src로 구성할 수 있는 모든 부분집합을 출력하시오.
        System.out.println("부분집합");
        powerSetDupPer(0, new boolean[3]);

    }

    static void makePermutation(int nth, char[] choosed, boolean[] visited) {
        if(nth == choosed.length) {
        	return;
        }
        
        for(int i=0; i<src.length; i++) {
        	if(!visited[i]) {
        		visited[i]=true;
        		choosed[nth] = src[i];
        		makePermutation(nth+1, choosed, visited);
        		visited[i]=false;
        	}
        }
    }

    static void makeCombination(int nth, char[] choosed, int startIdx) {
        if(nth == choosed.length) {
        	return;
        }
        for(int i=startIdx; i<src.length; i++) {
        	choosed[nth] = src[i];
        	makeCombination(nth+1, choosed, i+1);
        }
    }

    static void powerSetDupPer(int toCheck, boolean[] checked) {
    	if(toCheck == checked.length ) {
    		return;
    	}
    	checked[toCheck] = true;
    	powerSetDupPer(toCheck+1, checked);
    	checked[toCheck] = false;
    	powerSetDupPer(toCheck+1, checked);
    }

    static void print(boolean[] temp) {
        
    }

}
