package KB.week03;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* ================================================================
 *
 * Problem  : Leetcode - Shortest Path in Binary Matrix
 * Author   : 김로아
 * Date     : 2025-05-09
 *
 * ================================================================
 * 📌 문제 분석 요약
 * nxn 행렬이 주어짐.
 * 왼쪽 상단(0,0)에서 시작해 오른쪽 하단(n-1,n-1)으로 가는 최단거리를 구하라
 * 단, 0으로만 이동할 수 있음. 1은 벽.
 * 경로가 없으면 -1 반환
 * 8방향으로 이동 가능(상하좌우, 대각선)
 *
 * # 입력
 * nxn 행렬
 * Input: grid = [[0,1],[1,0]]
 *
 * # 출력
 * 왼쪽 위에서 오른쪽 아래까지의 최단거리
 *
 * 💻 알고리즘 설계
 * 1) 상하좌우, 대각선 총 8방향으로 한칸씩 동적으로 이동하면서 탐색
 * 2) visited 대신 distance를 사용해 탐색 시마다 거리 계산
 * 3) bfs 실행 시 조건(r과 c가 0~n 사이인지, 해당 값이 0인지, 거리가 -1(방문 안했을 경우의 초기값)인지) 검사
 * 4) 다음 셀이 조건 검사를 통과하면 큐에 넣고, 현재 거리에서 +1
 * 5) (n-1,n-1) 위치의 거리 반환하면 최소 거리(bfs 사용했으므로)
 *
 * ⏰ 시간복잡도
 * O(n^2) n은 행,열 길이
 * ================================================================
 */

class Shortest_Path_in_Binary_Matrix {
    static int[] dr = {-1, 1, 0, 0, -1, 1, 1, -1};
    static int[] dc = {0, 0, -1, 1, 1, 1, -1, -1};
    static int n; //행과 열의 길이는 n으로 동일

    public static boolean isValid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }

    public static void bfs(int r, int c, int[][] grid, int[][] distance) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int i = 0; i < 8; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (isValid(nextR, nextC) && grid[nextR][nextC] == 0 && distance[nextR][nextC] == -1) {
                    q.offer(new int[]{nextR, nextC});
                    distance[nextR][nextC] = distance[curR][curC] + 1;
                }
            }
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        n = grid.length;
        int[][] distance = new int[n][n];

        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }

        distance[0][0] = 1;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        else {
            bfs(0, 0, grid, distance);
            return distance[n - 1][n - 1];
        }
    }
}
