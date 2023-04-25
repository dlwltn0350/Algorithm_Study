class Solution {
    static int result = 0;
    public int solution(int[] number) {
        int answer = 0;
        comb(0, new int[number.length], 0, number);
        answer = result;
        return answer;
    }
    
    static void comb(int nth, int[] choosed, int start, int[] number){
        if(nth == 3){
            int cnt = 0;
            for(int i=0; i<number.length; i++){
                cnt += choosed[i];
            }
            
            if(cnt == 0){
                result ++;
            }
            return;
        }
        
        for(int i=start; i<number.length; i++){
            choosed[nth] = number[i];
            comb(nth+1, choosed, i+1, number);
        }
    }
}