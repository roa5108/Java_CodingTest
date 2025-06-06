package BOJ.Silver;

/* ================================================================
 *
 * Problem  : BOJ 11403 - 경로 찾기
 * Author   : 김로아
 * Date     : 2025-06-06
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서,
 * i에서 j로 가는 길이가 양수인 경로가 있는지 없는지 구하라.
 *
 * # 입력
 * 첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)
 * 둘째 줄 ~ N개 줄에 그래프의 인접 행렬 (1이면 간선 존재, 0이면 존재 X)
 *
 * # 출력
 * 정점 i에서 j로 가는 길이가 양수인 경로가 있으면 i번째 줄의 j번째 숫자를 1로,
 * 없으면 0으로 표현한 인접행렬
 *
 * 💻 알고리즘 설계
 * 중간점인 k 변수를 두고, i에서 k로 갈 수 있고,
 * k에서 j로 갈 수 있으면 i에서 j로 갈 수 있음.
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
