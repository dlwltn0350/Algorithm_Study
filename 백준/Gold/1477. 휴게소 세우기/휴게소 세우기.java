import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * boj 2110과 비슷하다?
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;
    static int N,M,L;
    static ArrayList<Integer> rest;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken()); //휴게소의 개수
        M = Integer.parseInt(tokens.nextToken()); //더 지으려고 하는 휴게소의 개수
        L = Integer.parseInt(tokens.nextToken()); //고속도로의 길이

        rest = new ArrayList<>();

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            rest.add(Integer.parseInt(tokens.nextToken()));
        }
        rest.add(0);
        rest.add(L);

        Collections.sort(rest);

        //mid는 휴게소 간격
        int left = 1, right = L-1, mid = 0;

        while(left<=right){
            mid = (left+right)/2;

            int cnt = 0;
            for(int i=1; i<rest.size(); i++){
                cnt += (rest.get(i)-rest.get(i-1)-1)/mid;//공유기 사이에 mid 간격으로 설치할 수 있는 공유기 개수
            }

            if (cnt > M) {
                left = mid + 1; //너무 많이 설치한다.
            }else{
                right = mid - 1;
            }
        }

        System.out.println(left);
    }
}