/**
 * @memo
 * 비트 연산자로 순조부를 푼다고 해서 속도 효율성이 좋아지는 건 아니다.
 * 배열로 푸나 비트 연산자로 푸나 큰 차이가 없지만 후에 문제에서 배열로 관리하기 힘들 때 비트연산자로 관리한다면 효율적인 상황이 나올 수가 있다.
 * 그래도 배열로 순조부 문제 해결하는 게 1순위!!
 * 좋은 아침.java 연습하자
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BitMaskingExercise {
    public static void main(String[] args) {
        setup();
        //sumFirstVisitByArray();
        //sumFirstVisitByBit();
        
        nums = Arrays.copyOf(nums, 4);
        //cnt = 0;
        //permutationByArray(0, new int [3], new boolean[nums.length]);
        //cnt = 0;
        //permutationByBit(0, new int[3], 0);

        //makePowerSet(0, new boolean[nums.length]);
        makePowerSet();
    }

    private static int[] nums;

    // 0~31까지의 정수 100개를 무작위로 만들어본다.
    private static void setup() {
        nums = new int[100];
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(32);
        }
        System.out.println("배열: " + Arrays.toString(nums));
    }

    private static void sumFirstVisitByArray() {
        // TODO:nums에서 처음 나온 배열 요소들만 더해서 그 값을 출력하시오.
    	boolean[] arr = new boolean[32];
    	int sum=0;
    	for(int i=0; i<nums.length; i++) {
    		if(!arr[nums[i]]) {
    			arr[nums[i]] = true;
    			sum+=nums[i];
    		}
    	}
    	
    	System.out.println(sum);
        // END:
    }
    
    private static void sumFirstVisitByBit() {
        // TODO: 위 메서드를 bit 연산자로 처리하시오.
    	int status = 0;
    	int sum=0;
    	for(int i=0; i<nums.length; i++) {
    		if((status & (1<<nums[i]))==0) {//사용중이 아니라면
    			status |= 1<<nums[i];
    			sum+=nums[i];
    		}
    	}
    	System.out.println(sum);
    	
    	
        // END:
    }

    static int cnt = 0;

    // TODO: 배열 기반으로 순열을 작성해보고 visited를 왜 원상복구시키고 있는지 고민해보자. -> visited를(하나의 배열) 공유하여 사용하고 있기 때문에
    static void permutationByArray(int nth, int[] choosed, boolean[] visited) {
    	if(nth == choosed.length) {
    		cnt++;
    		System.out.println(Arrays.toString(choosed));
    		return;
    	}
    	
    	for(int i=0; i<nums.length; i++) {
    		if(!visited[i]) {
    			visited[i]=true;
    			choosed[nth]=nums[i];
    			permutationByArray(nth+1, choosed, visited);
    			visited[i]=false;
    		}
    	}
    }
    // END:

    // TODO: bit 연산을 이용해서 순열을 작성해보자.
    static void permutationByBit(int nth, int[] choosed, int status) {
    	if(nth == choosed.length) {
    		cnt++;
    		System.out.println(Arrays.toString(choosed));
    		return;
    	}
    	
    	for(int i=0; i<nums.length; i++) {
    		if((status&(1<<i))==0) {
    			choosed[nth]=nums[i];
    			permutationByBit(nth+1, choosed, status|(1<<i));
    			
    		}
    	}
    }
    // END:

    // TODO: 중복순열을 이용해서 nums의 부분집합을 만들어보자. ->2^n개
    static void makePowerSet(int nth, boolean[] visited) {
    	if(nth == visited.length) {
    		System.out.println(Arrays.toString(visited));
    		return;
    	}
    	
    	visited[nth]= true;
		makePowerSet(nth+1, visited);
		visited[nth]=false;
		makePowerSet(nth+1, visited);
    }
    
    // END:

    // TODO: bitmasking을 이용해서 부분집합을 구해보자.
    static void makePowerSet() {
    	for(int i=0; i<(1<<nums.length); i++) {
    		List<Integer> include = new ArrayList<>();
    		List<Integer> exclude = new ArrayList<>();
    		System.out.println(i); // 인덱스(i) 자체가 요소들에 대한 포함 여부를 비트를 가지고 있다. (=상태를 가지고 있다)
    		
    		for(int j=0; j<nums.length; j++) {
    			if((i&(1<<j))!=0) {
    				include.add(j);
    			}
    			else {
    				exclude.add(j);
    			}
    		}
    		
    		System.out.printf("include:%s, exclude:%s\n",include,exclude);
    	}
    }
    
    // END:

}
