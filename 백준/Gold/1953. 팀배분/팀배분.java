import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static ArrayList<Integer> A = new ArrayList<>();
    static ArrayList<Integer> B = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 학생들의 수

        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<>());
        }

        visited = new boolean[N+1];

        for(int i=1; i<N+1; i++){
            //서로가 싫어하는 사람들의 정보
            tokens = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(tokens.nextToken());

            for(int j=0; j<index; j++){
                int a = Integer.parseInt(tokens.nextToken());
                
                //서로 싫어하는 사람 등록
                graph.get(i).add(a);
            }
        }

        //팀을 꾸리지 않앗다면 A
        for(int i=1; i<N+1; i++){
            if(!visited[i]){
//                A.add(i);
//                bfs(i);
                visited[i] = true;
                dfs(i,true);
            }
        }

        Collections.sort(A);
        Collections.sort(B);

        if(A.size() == 0){
            A.add(B.get(B.size()));
            B.remove(B.size());
        }else if(B.size() == 0){
            B.add(A.get(A.size()));
            A.remove(A.size());
        }

        System.out.println(A.size());
        for(int i=0; i<A.size(); i++){
            System.out.print(A.get(i)+" ");
        }
        System.out.println();
        System.out.println(B.size());
        for(int i=0; i<B.size(); i++){
            System.out.print(B.get(i)+" ");
        }
        System.out.println();

    }

//    static void bfs(int index){
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(index);
//        visited[index] = true;
//
//        while(!queue.isEmpty()){
//            int idx = queue.poll();
//
//            for(int i=0; i<graph.get(idx).size(); i++){
//                int a = graph.get(idx).get(i);
//
//                if(!visited[a]){
//                    //싫어하는 사람은 반대쪽
//                    if(A.contains(idx)) B.add(a);
//                    else A.add(a);
//
//                    visited[a] = true;
//                }
//            }
//        }
//    }

    static void dfs(int index, boolean check){
        if(check) A.add(index);
        else B.add(index);

        for(int i=0; i<graph.get(index).size(); i++){
            int a = graph.get(index).get(i);

            if(!visited[a]){
                //싫어하는 사람은 반대쪽
                visited[a] = true;
                dfs(a,check?false:true);
            }
        }
    }


}