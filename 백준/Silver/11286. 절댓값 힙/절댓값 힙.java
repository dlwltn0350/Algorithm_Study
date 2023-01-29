import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Math.abs(o1) == Math.abs(o2)){
                    return o1 > o2 ? 1 : -1;
                }else{
                    return Math.abs(o1) - Math.abs(o2);
                }
            }
        });

        for(int i=0; i<N; i++){
            int x = Integer.parseInt(br.readLine());

            if(x == 0){
                if(pq.isEmpty()) sb.append("0").append("\n");
                else sb.append(pq.poll()).append("\n");
            }else{
                pq.add(x);
            }
        }

        System.out.print(sb.toString());
    }
}