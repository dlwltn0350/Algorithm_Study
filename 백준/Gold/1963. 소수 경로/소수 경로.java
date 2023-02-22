import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {

        checkPrime();

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            int result = bfs(a,b);
            if(result == -1) sb.append("Impossible").append("\n");
            else sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    static int bfs(int a, int b){
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[10000];
        queue.add(new Node(a,0));
        visited[a] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node.idx == b){
                return node.cnt;
            }

            // i번재 자리의 값을 j로 변경
            for(int i=0; i<4; i++){
                for(int j=0; j<10; j++){
                    if(i == 0 && j == 0) continue;
                    // 값 변경
                    String str = Integer.toString(node.idx);
                    StringBuilder tmp = new StringBuilder();
                    for(int k=0; k<i; k++){
                        tmp.append(str.charAt(k));
                    }
                    tmp.append(j);
                    for(int k=i+1; k<str.length(); k++){
                        tmp.append(str.charAt(k));
                    }
                    int index = Integer.parseInt(tmp.toString());
                    if(!visited[index] && isPrime[index]){
                        visited[index] = true;
                        queue.add(new Node(index, node.cnt+1));
                    }

                }
            }
        }

        return -1;
    }

    static class Node{
        int idx, cnt;

        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    //에라토스테네스의 체
    static void checkPrime(){
        isPrime = new boolean[10000];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

       for(int i=2; i*i<10000; i++){
           if(isPrime[i]){
               //배수 삭제
               for(int j=i*i; j<10000; j+=i){
                   isPrime[j] = false;
               }
           }
       }
    }
}