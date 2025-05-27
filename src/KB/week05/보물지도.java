package KB.week05;

/* ================================================================
 *
 * Problem  : PGS - 보물지도
 * Author   : 김로아
 * Date     : 2025-05-27
 *
 * ================================================================
 * 📌 문제 분석 요약
 *
 * # 입력
 *
 * # 출력
 *
 * 💻 알고리즘 설계
 *
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.util.ArrayDeque;
import java.util.Queue;


class 보물지도 {
    public static int[] dc = {0, 0, -1, 1};
    public static int[] dr = {1, -1, 0, 0};
    public static int[] shoesDc = {0, 0, -2, 2};
    public static int[] shoesDr = {2, -2, 0, 0};

    // 행 : m, 열 : n
    public int solution(int m, int n, int[][] hole) {
        Queue<int[]> queue = new ArrayDeque<>(); // 각 원소는 [r, c, 기회사용여부(0 or 1), time]
        boolean[][][] visited = new boolean[m + 1][n + 1][2]; // 마지막은 usedShoes: 0 또는 1;
        queue.add(new int[]{1, 1, 0, 0});
        visited[1][1][0] = true;
        int time = 0;
        int usedShoes = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int curR = cur[0];
            int curC = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];
                if (nextR == m && nextC == n) {
                    return time;
                }
                if (0 < nextR && nextR <= m && 0 < nextC && nextC <= n && !visited[nextR][nextC][usedShoes]) {
                    if (!isHole(nextR, nextC, hole)) {
                        queue.add(new int[]{nextR, nextC, usedShoes, time + 1});
                    } else {
                        nextR = curR + shoesDr[i];
                        nextC = curC + shoesDc[i];
                        if (nextR == m && nextC == n) {
                            return time;
                        }
                        if (0 < nextR && nextR <= m && 0 < nextC && nextC <= n && !visited[nextR][nextC][usedShoes]) {
                            queue.add(new int[]{nextR, nextC, usedShoes + 1, time + 1});
                        }
                    }
                }
            }
        }
        return -1;
    }

    public boolean isHole(int r, int c, int[][] hole) {
        for (int[] h : hole) {
            if (h[0] == r && h[1] == c) {
                return true;
            }
        }
        return false;
    }
}