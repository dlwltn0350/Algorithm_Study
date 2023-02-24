import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * root 위치에 따라 결정
 * Inorder(중위 순회) : left->root->right
 * Postorder(후위 순회) : left->right->root
 * Preorder(전위 순회) : root->left->right
 * 코드 블로그 참고해서 품 9ㅅ9
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokens;
    static int n;
    static int[] inOrder, postOrder, preOrder;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        inOrder = new int[n];
        postOrder = new int[n];
        preOrder = new int[n];

        //인오더
        tokens = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            inOrder[i] = Integer.parseInt(tokens.nextToken());
        }

        //포스트오더
        tokens = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            postOrder[i] = Integer.parseInt(tokens.nextToken());
        }

        getPreOrder(0, n-1, 0, n-1);
        System.out.println(sb.toString());
    }

    // root-> left-> right
    static void getPreOrder(int in_start, int in_end, int post_start, int post_end){
        if(in_start> in_end || post_start>post_end){
            return;
        }

        int root = postOrder[post_end]; //포스트오더의 마지막
        sb.append(root+" ");

        // 인오더에서 root가 있는 위치(index)를 알아온다.
        int rootIdx = 0;
        for(int i=0; i<n; i++){
            if(inOrder[i] == root){
                rootIdx = i;
                break;
            }
        }

        //인오더에서 root 왼쪽에 있는 노드 개수
        int left = rootIdx - in_start;

        //left 쪽을 탐색한다.
        getPreOrder(in_start, rootIdx-1, post_start, post_start + left -1);

        //right 쪽을 탐색한다.
        getPreOrder(rootIdx +1, in_end, post_start + left, post_end -1);
    }

}