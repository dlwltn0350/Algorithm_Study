import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            long num = Integer.parseInt(br.readLine());
            if(num == 0){
                if(pq.isEmpty()){
                    sb.append("0").append("\n");
                }else{
                    sb.append(pq.poll()).append("\n");
                }
            }else{
                pq.add(num);
            }
        }
        System.out.print(sb.toString());
    }
}