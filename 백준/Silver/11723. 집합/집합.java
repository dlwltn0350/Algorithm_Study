import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 비트마스킹 풀이
 * x y and or
 * 1 1 1 1
 * 1 0 0 1
 * 0 1 0 1
 * 0 0 0 0
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int M = Integer.parseInt(br.readLine());
        int S = 0; //비트마스킹 용도

        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(br.readLine());
            String str = tokens.nextToken();
            int num = 0;
            if(str.equals("add")){
                num = Integer.parseInt(tokens.nextToken());
                S = S | (1<<num); //추가 (OR연산)
                //num번째에 1을 추가한다
            }else if(str.equals("remove")){
                num = Integer.parseInt(tokens.nextToken());
                S = S & ~(1<<num); //and연산
            }else if(str.equals("check")){
                num = Integer.parseInt(tokens.nextToken());
                if((S&(1<<num)) == 0){
                    sb.append(0).append("\n");
                }else{ //그자리에 1일 경우에만 맞다
                    sb.append(1).append("\n");
                }
            }else if(str.equals("toggle")){
                num = Integer.parseInt(tokens.nextToken());
                S ^= (1<<num);
                //A^B => 둘이 같으면 1 다르면 0
            }else if(str.equals("all")){
                S = (1<<21) -1;
            }else if(str.equals("empty")){
                S = 0;
            }
        }

        System.out.print(sb.toString());
    }
}