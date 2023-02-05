import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());

        int sum = 0;

        for(int i=0; i<5; i++){
            int a = Integer.parseInt(tokens.nextToken());
            sum += Math.pow(a,2);
        }
        System.out.println(sum%10);
    }
}