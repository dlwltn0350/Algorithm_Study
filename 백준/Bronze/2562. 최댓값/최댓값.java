import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int max = Integer.MIN_VALUE, index;

    public static void main(String[] args) throws IOException {
        for(int i=0; i<9; i++){
            int a = Integer.parseInt(br.readLine());

            if(max<a){
                max = a;
                index = i+1;
            }
        }

        System.out.println(max);
        System.out.println(index);
    }
}