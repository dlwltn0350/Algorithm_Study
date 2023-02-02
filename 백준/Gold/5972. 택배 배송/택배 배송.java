import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;
    static int N,M;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<Node>());
        }

        for(int i=0; i<M; i++){
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());

            graph.get(a).add(new Node(b,c));
            graph.get(b).add(new Node(a,c));
        }
        bfs();
    }

    static void bfs(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        pq.add(new Node(1,0));

        while(!pq.isEmpty()){
            Node node = pq.poll();
            visited[node.num] = true;

            if(node.num  == N){
                System.out.println(node.weight);
                return;
            }
            for(int i=0; i<graph.get(node.num).size(); i++){
                Node a = graph.get(node.num).get(i);
                if(!visited[a.num]){
                    pq.add(new Node(a.num, node.weight + a.weight));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int num, weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

}