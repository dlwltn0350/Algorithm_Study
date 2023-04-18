import java.util.*;

class Solution {
    static int[][] map;
    static int startX, startY;
    static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
    static int N, M;
    
    public int solution(String[] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length();
        map = new int[N][M];
        for(int i=0; i<N; i++){
            String str = maps[i];
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S'){
                    startX = i;
                    startY = j;
                }
            }
        }
        
        answer = bfs();
        
        return answer;
    }
    
    static int bfs(){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[][][] visited = new boolean[N][M][2];
        queue.add(new Node(startX, startY, 0, 0));
        visited[startX][startY][0] = true;
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            
            if(map[node.x][node.y] == 'E' && node.cnt == 1){
                return node.time;
            }
            
            for(int i=0; i<deltas.length; i++){
                int a = node.x + deltas[i][0];
                int b = node.y + deltas[i][1];
                
                if(isIn(a,b) && map[a][b] == 'L'){
                    visited[a][b][1] = true;
                    map[a][b] = 'O';
                    queue.add(new Node(a,b,1, node.time+1));
                }
                
                else if(isIn(a,b) && !visited[a][b][node.cnt] && map[a][b] != 'X'){
                    visited[a][b][node.cnt] = true;
                    queue.add(new Node(a,b,node.cnt, node.time+1));
                }
            }
        }
        
        return -1;
    }
    
    static boolean isIn(int a, int b){
        return a>=0 && a<N && b>=0 && b<M;
    }
    
    static class Node implements Comparable<Node>{
        int x, y, cnt, time;
        
        public Node(int x, int y, int cnt, int time){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.time = time;
        }
        
        public int compareTo(Node node){
            if(node.cnt == this.cnt){
                return Integer.compare(this.time, node.time);
            }
            return Integer.compare(node.cnt, this.cnt);
        }
    }
}