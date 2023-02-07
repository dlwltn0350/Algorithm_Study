import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * S로 T로 만드는 것으로 시도해보았으나, 시간초과
 * T -> S가 더 효율적?
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static String S, T;

    public static void main(String[] args) throws IOException {
        S = br.readLine();
        T = br.readLine();

        // S -> T
        recursion(T);
        System.out.println("0");
    }

    static void recursion(String t){
        if(S.equals(t)){
            System.out.println("1");
            System.exit(0);
        }else if(S.length() >= t.length()){
            return;
        }
        if (t.charAt(t.length() - 1) == 'A') { //끝자리가 A
            recursion(t.substring(0, t.length() - 1)); //그냥 하나 지움
        }

        if (t.charAt(0) == 'B') {
            recursion(new StringBuilder(t.substring(1, t.length())).reverse().toString());
        }

    }
}