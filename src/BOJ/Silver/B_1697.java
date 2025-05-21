package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* ================================================================
 *
 * Problem  : BOJ_1697 - 숨바꼭질
 * Author   : 김로아
 * Date     : 2025-05-13
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 수빈이는 점 N, 동생은 점 K에 위치
 * 수빈이는 X-1, X+1, 2*X의 위치로 1초가 걸려 이동 가능
 * 수빈이가 동생을 찾을 수 있는 가장 빠른 시간은 몇초?
 *
 * # 입력
 * 수빈이 위치 N과 동생 위치 K가 정수로 주어짐
 *
 * # 출력
 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력
 *
 * 💻 알고리즘 설계
 * 최단시간을 구해야 함 -> BFS 사용
 * 1) Node 클래스를 만들어 수빈이의 현재 위치와 시간을 저장
 * 2) 동생을 만나지 않았을 경우, {X-1, X+1, 2*X} 세 가지 이동 경우의 수를 배열에 담아 반복문을 돌며 수빈이 위치 이동
 * 3) 큐가 빌 때까지 BFS 반복
 *
 * ⏰ 시간복잡도
 * O(N) N은 가능한 위치, 즉 최대 100001
 * ================================================================
 */

public class B_1697 {
    static boolean[] visited = new boolean[100001];
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        System.out.println(bfs(N, K));

    }

    public static int bfs(int loc, int goal) {
        if (loc == goal) return 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(loc, 0));
        visited[loc] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int curLoc = cur.vertex;
            int curTime = cur.time;

            int[] nextMoves = {curLoc - 1, curLoc + 1, curLoc * 2};
            for (int nextLoc : nextMoves) {
                if (nextLoc == K) {
                    return curTime + 1;
                } else if (0 <= nextLoc && nextLoc <= 100000 && !visited[nextLoc]) {
                    visited[nextLoc] = true;
                    q.offer(new Node(nextLoc, curTime + 1));
                }
            }
        }
        return -1;
    }

    static class Node {
        int vertex;
        int time;

        Node(int vertex, int time) {
            this.vertex = vertex;
            this.time = time;
        }
    }
}