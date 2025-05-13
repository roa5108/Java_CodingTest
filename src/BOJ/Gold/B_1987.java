package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* ================================================================
 *
 * Problem  : BOJ - 알파벳
 * Author   : 김로아
 * Date     : 2025-05-13
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 세로 R칸, 가로 C칸인 보드 각 칸에는 대문자 알파벳이 하나씩 적혀 있음
 * (0,0) 위치에 말이 놓여 있고, 상하좌우로 한 칸 이동 가능.
 * 같은 알파벳이 적힌 칸을 두 번 지날 수 없을 때, 말이 최대한 몇 칸을 지날 수 있을까? ((0,0) 위치 포함됨.)
 *
 * # 입력
 * 첫째 줄 - R과 C가 빈칸을 사이에 두고 주어짐
 * 둘째 줄 ~ R개 줄 - 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸없이 주어짐
 *
 * # 출력
 * 말이 지날 수 있는 최대의 칸 수
 *
 * 💻 알고리즘 설계
 * DFS + 백트래킹 !
 * 1) DFS 실행해 상하좌우 네 방향 탐색, isValid 조건 만족 시 alphaVisited 방문 여부 체크
 * 2) 방문하지 않았으면 방문 처리 후, 다음 DFS 실행
 * 3) 방문처리 했던 알파벳은 다시 백트래킹(복원)하기 위해 false 처리 (다른 경로 탐색할 때 써야함)
 * 4) DFS 실행 시마다 최대 칸 수를 +1씩 하고, max 값 계산
 *
 * ⏰ 시간복잡도
 * 최악의 경우가 O(26!)
 * ================================================================
 */

public class B_1987 {
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int R, C;
    static boolean[] alphaVisited;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        alphaVisited = new boolean[26];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }

        alphaVisited[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(max);

    }

    public static boolean isValid(int row, int col) {
        return 0 <= row && row < R && 0 <= col && col < C;
    }

    public static void dfs(int r, int c, int depth) {
        max = Math.max(max, depth);

        for (int i = 0; i < 4; i++) {
            int curR = r + dr[i];
            int curC = c + dc[i];

            if (isValid(curR, curC)) {
                int index = map[curR][curC] - 'A';
                if (!alphaVisited[index]) {
                    alphaVisited[index] = true; //이 알파벳은 지금 경로에서 씀
                    dfs(curR, curC, depth + 1);
                    alphaVisited[index] = false; //이 경로는 다 끝났으니, 이 알파벳을 다시 써도 됨(백트래킹)
                }
            }
        }
    }
}
