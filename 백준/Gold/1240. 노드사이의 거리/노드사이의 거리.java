import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;
    static int N, M;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<Node>());
        }

        for(int i=0; i<N-1; i++){
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());

            graph.get(a).add(new Node(b,c));
            graph.get(b).add(new Node(a,c));
        }

        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            bfs(a,b);
        }

        System.out.print(sb.toString());
    }

    static void bfs(int a, int b){
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        queue.add(new Node(a,0));
        visited[a] = true;

        while(!queue.isEmpty()){
            Node x = queue.poll();

            if(x.a == b){
                sb.append(x.weight).append("\n");
                return;
            }

            for(int i=0; i<graph.get(x.a).size(); i++){
                Node node = graph.get(x.a).get(i);
                if(!visited[node.a]){
                    visited[node.a] = true;
                    queue.add(new Node(node.a,node.weight+x.weight));
                }

            }
        }
    }

    static class Node{
        int a, weight;

        public Node(int a, int weight) {
            this.a = a;
            this.weight = weight;
        }
    }
}