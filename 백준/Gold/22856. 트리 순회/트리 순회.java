import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    static boolean[] visited;
    static int cnt = 0, N=0, last = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<>());
        }

        visited =new boolean[N+1];

        for(int i=0; i<N; i++){
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken()); //현재노드
            int b = Integer.parseInt(tokens.nextToken()); //왼쪽노드
            int c = Integer.parseInt(tokens.nextToken()); //오른쪽노드
            graph.get(a).add(new Node(b,true));
            graph.get(a).add(new Node(c,false));
        }

        dfs(1, true); // 마지막 노드가 무엇인지 확인하러 고
        visited =new boolean[N+1];
        cnt = 0;
        dfs(1, false);
    }

    static void dfs(int idx, boolean flag) {
        if(graph.get(idx).get(0).idx!=-1 && !visited[graph.get(idx).get(0).idx]) {
            //왼쪽 노드 자식이 존재하고 아직 방문하지 않았다면
//            System.out.println(idx);
            cnt++;
            dfs(graph.get(idx).get(0).idx, flag);
            cnt++;
        }
        if(!visited[idx]){
            visited[idx] = true;
            if(flag) last = idx;
        }

        if(graph.get(idx).get(1).idx!=-1 && !visited[graph.get(idx).get(1).idx]){
            //오른쪽 자식 노드가 존재하고 아직 방문하지 않앗다면
            cnt++;
            dfs(graph.get(idx).get(1).idx, flag);
            cnt++;
        }

        if(!flag && idx == last){
            System.out.println(cnt);
            System.exit(0);
        }

    }
    static class Node{
        int idx;
        boolean isLeft;

        public Node(int idx, boolean isLeft) {
            this.idx = idx;
            this.isLeft = isLeft;
        }
    }
}