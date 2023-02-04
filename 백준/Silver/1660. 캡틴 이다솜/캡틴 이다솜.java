import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 구글링 해서 문제 해결
 * 근데도 이해가 어렵다 8ㅅ8 역시 dp인가
 * 동전문제??
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] dp1 = new int[150]; //층마다 담고 있는 대포수
        int[] dp2 = new int[150]; //사면체의 대포수

        for (int i = 1; i < 150; i++) {
            dp1[i] = dp1[i-1] + i;
            dp2[i] = dp1[i] + dp2[i-1];
        }
        int[] result = new int[N+1];
        Arrays.fill(result, Integer.MAX_VALUE);

        result[0] = 0;
        result[1] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < 122; j++) {
                if(dp2[j] > i) break;
                result[i] = Math.min(result[i], result[i - dp2[j]]+1);
            }
        }
        System.out.println(result[N]);
    }
}