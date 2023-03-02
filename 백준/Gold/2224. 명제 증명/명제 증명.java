import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;
    static boolean[][] visited = new boolean[52][52];

    public static void main(String[] args) throws IOException {
        int X = Integer.parseInt(br.readLine());

        for(int i=0; i<X; i++){
            tokens = new StringTokenizer(br.readLine());
            int a = tokens.nextToken().charAt(0)-65;
            tokens.nextToken();
            int b = tokens.nextToken().charAt(0)-65;

            // 알파벳 대문자 65 ~ 90
            // 알파벳 소문자 97 ~ 122

            if(a>=32){
                a-=6;
            }
            if(b>=32){
                b-=6;
            }

            visited[a][b] = true;
        }

        for(int k=0; k<52; k++){
            for(int i=0; i<52; i++){
                for(int j=0; j<52; j++){
                    if(k==i || i==j || k==j || visited[i][j]) continue;
                    if(visited[i][k] && visited[k][j]) visited[i][j] = true; // i => j
                }
            }
        }

        int cnt = 0;
        for(int i=0; i<52; i++){
            for(int j=0; j<52; j++){
                if(i==j || !visited[i][j]) continue;
                cnt ++;
                sb.append(((char)((i+65)>90 ? (i+65+6) : (i+65))) + " => "+((char)((j+65)>90 ? (j+65+6) : (j+65)))).append("\n");
            }
        }
        System.out.println(cnt);
        System.out.print(sb.toString());
    }

}