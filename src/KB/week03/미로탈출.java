package KB.week03;

import java.util.LinkedList;
import java.util.Queue;

/* ================================================================
 *
 * Problem  : PGS - 미로 탈출
 * Author   : 김로아
 * Date     : 2025-05-10
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 미로를 탈출해야 하는데, 레버를 당겨야만 빠져나갈 수 있다.
 * 한 칸 이동 시 1초가 걸림. 미로 탈출 최단시간 구하기
 * 출구는 레버가 당겨지지 않아도 지나갈 수 있으며, 모든 통로, 출구, 레버, 시작점은 여러 번 지나갈 수 있다.
 *
 * # 입력
 * 미로를 나타낸 문자열 배열 maps
 * maps[i]는 다음 5개의 문자들로만 이루어져 있다.
 * S : 시작 지점
 * E : 출구
 * L : 레버
 * O : 통로
 * X : 벽
 * ["SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"]
 *
 * # 출력
 * 미로를 탈출하는데 필요한 최소 시간. 탈출 불가능 시 -1 리턴
 *
 * 💻 알고리즘 설계
 * 1) 문자열로 이루어져 있는 미로 배열을 char 이차원 배열로 변환
 * 2) start, lever, end 위치를 구함
 * 3) start to lever, lever to end 두 번 BFS 실행을 한 후, 시간 더하기
 *
 * ⏰ 시간복잡도
 * O(N+E) N은 정점, E는 간선
 * ================================================================
 */

class 미로탈출 {
    static int rLength;
    static int cLength;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static boolean isValid(int r, int c) {
        return 0 <= r && r < rLength && 0 <= c && c < cLength;
    }

    public int solution(String[] maps) {
        rLength = maps.length;
        cLength = maps[0].length();
        char[][] graph = new char[rLength][cLength];
        int[] start = new int[2];
        int[] lever = new int[2];
        int[] end = new int[2];

        for (int i = 0; i < rLength; i++) {
            graph[i] = maps[i].toCharArray();
            for (int j = 0; j < cLength; j++) {
                if (graph[i][j] == 'S') start = new int[]{i, j};
                if (graph[i][j] == 'L') lever = new int[]{i, j};
                if (graph[i][j] == 'E') end = new int[]{i, j};
            }
        }


        int toLever = bfs(start, lever, graph);
        int toEnd = bfs(lever, end, graph);

        if (toLever == -1 || toEnd == -1) return -1;
        return toLever + toEnd;

    }

    public int bfs(int[] start, int[] target, char[][] graph) {
        Queue<int[]> q = new LinkedList<>();
        int[][] distance = new int[rLength][cLength];
        boolean[][] visited = new boolean[rLength][cLength];

        q.offer(start);
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            if (curR == target[0] && curC == target[1]) {
                return distance[curR][curC];
            }

            for (int i = 0; i < 4; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (isValid(nextR, nextC) && !visited[nextR][nextC] && graph[nextR][nextC] != 'X') {
                    q.offer(new int[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                    distance[nextR][nextC] = distance[curR][curC] + 1;
                }
            }
        }
        return -1;
    }
}



