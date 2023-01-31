import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**

 @author jisoo
 @since 2023. 1. 31.
 @see
 @performance
 @category #
 @note */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
    static int V;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    static int result, node = 0;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        V = Integer.parseInt(br.readLine());

        for(int i=0; i<V+1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<V; i++) {

            tokens = new StringTokenizer(br.readLine());
            ArrayList<Integer> input = new ArrayList<>();
            while(true) {
                input.add(Integer.parseInt(tokens.nextToken()));
                if(input.get(input.size()-1) == -1) break;
            }

            int a = input.get(0);

            for(int j=1; j<input.size()-1; j+=2) {
                int b = input.get(j);
                int weight = input.get(j+1);

                graph.get(a).add(new Node(b,weight));
                graph.get(b).add(new Node(a,weight));
            }
        }

        visited = new boolean[V+1];

        // 1에서 가장 먼 정점 구하기
        visited[1] = true;
        dfs(1,0);

        // 가장 먼 정점에서 또 가장 먼 정점 구하기
        visited = new boolean[V+1];
        visited[node] = true;
        dfs(node,0);

        System.out.println(result);

    }

    static void dfs(int n, int cnt) {
        if(cnt > result){
            node = n; //최대 길이
            result = cnt;
        }

        for(int i=0; i<graph.get(n).size(); i++){
            Node a = graph.get(n).get(i);
            if(!visited[a.num]){
                visited[a.num] = true;
                dfs(a.num, cnt + a.weight);
            }
        }
    }

    static class Node{
        int num, weight;

        public Node(int num, int weight) {
            super();
            this.num = num;
            this.weight = weight;
        }

    }
}