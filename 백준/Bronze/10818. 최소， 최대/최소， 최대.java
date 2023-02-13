import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        tokens = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int a = Integer.parseInt(tokens.nextToken());
            max = Math.max(max,a);
            min = Math.min(min,a);
        }
        System.out.println(min + " " + max);
    }
}