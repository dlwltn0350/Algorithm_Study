import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        int M = Integer.parseInt(br.readLine());
        visited = new boolean[21];
        Arrays.fill(visited,false);

        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(br.readLine());
            String str = tokens.nextToken();
            int num = 0;
            if(str.equals("add")){
                num = Integer.parseInt(tokens.nextToken());
                visited[num] = true;
            }else if(str.equals("remove")){
                num = Integer.parseInt(tokens.nextToken());
                visited[num] = false;
            }else if(str.equals("check")){
                num = Integer.parseInt(tokens.nextToken());
                if(visited[num]){
                    sb.append(1).append("\n");
                }else{
                    sb.append(0).append("\n");
                }
            }else if(str.equals("toggle")){
                num = Integer.parseInt(tokens.nextToken());
                if(visited[num])
                    visited[num]= false;
                else visited[num] = true;
            }else if(str.equals("all")){
                for(int j=1; j<visited.length; j++){
                    visited[j] = true;
                }
            }else if(str.equals("empty")){
                for(int j=1; j<visited.length; j++){
                    visited[j] = false;
                }
            }
        }

        System.out.print(sb.toString());
    }
}