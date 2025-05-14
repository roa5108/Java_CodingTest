package KB.week03;

import java.util.LinkedList;
import java.util.Queue;

/* ================================================================
 *
 * Problem  : NossiDev - 응급차 최단 거리2
 * Author   : 김로아
 * Date     : 2025-05-14
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 0으로만 이동 가능, (0,0)에서 (m-1,n-1)까지 최단 경로 길이 구하기
 * 상하좌우 또는 대각선으로 인접한 칸으로 이동 가능
 *
 * # 입력
 * m x n크기의 이진 행렬로 표현된 도시
 * city = [
    [0, 0, 1, 0],
    [1, 0, 1, 0],
    [1, 0, 0, 0]
]
 *
 * # 출력
 * 응급차가 도착할 수 있는 가장 짧은 경로의 길이
 *
 * 💻 알고리즘 설계
 * bfs에서 거리를 추적하며 도착 시 return
 * 큐에 이동 거리도 같이 넣어서 계산하기! 매우 중요
 *
 * ⏰ 시간복잡도
 * O(m x n) - m x n은 도시의 크기
 * ================================================================
 */

class 응급차_최단_거리2 {
    public static int[] dr = {-1, 1, 0, 0, -1, 1, 1, -1};
    public static int[] dc = {0, 0, -1, 1, 1, 1, -1, -1};
    public static boolean[][] visited;
    public static int m, n;

    public int solution(int[][] city) {
        m = city.length;
        n = city[0].length;
        visited = new boolean[m][n];

        if (city[0][0] == 1 || city[m - 1][n - 1] == 1) {
            return -1;
        }

        return bfs(0, 0, city);
    }

    public boolean isValid(int r, int c) {
        return 0 <= r && r < m && 0 <= c && c < n;
    }

    public int bfs(int r, int c, int[][] city) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c, 1}); // r, c, distance
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int dist = cur[2];

            if (curR == m - 1 && curC == n - 1) {
                return dist;
            }

            for (int i = 0; i < 8; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (isValid(nextR, nextC) && !visited[nextR][nextC] && city[nextR][nextC] == 0) {
                    visited[nextR][nextC] = true;
                    q.offer(new int[]{nextR, nextC, dist + 1});
                }
            }
        }

        return -1; // 도달 불가능
    }
}

