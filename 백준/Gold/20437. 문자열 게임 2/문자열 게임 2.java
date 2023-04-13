import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
    static String W;
    static int K;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc<T; tc++){
            W = br.readLine();
            K = Integer.parseInt(br.readLine());

            if(K == 1) {
                System.out.println("1 1");
                continue;
            }

            int[] alpha = new int[26];

            for(int i=0; i<W.length(); i++){
                // 알파벳 개수
                alpha[W.charAt(i)-'a'] ++;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for(int i=0; i<W.length(); i++){
                int cnt = 1;

                if(alpha[W.charAt(i)-'a'] < K) continue;

                for(int j=i+1; j<W.length(); j++){
                    if(W.charAt(i) == W.charAt(j)) cnt++;
                    if(cnt == K){
                        //문자열 길이
                        min = Math.min(min, j-i+1);
                        max = Math.max(max, j-i+1);
                        break;
                    }
                }
            }

            if(min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) System.out.println("-1");
            else System.out.println(min+" "+max);
        }
    }
}