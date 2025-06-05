package BOJ.Gold;

/* ================================================================
 *
 * Problem  : BOJ 2458 - 키 순서
 * Author   : 김로아
 * Date     : 2025-06-05
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 1번 ~ N번 학생들에 대해 두 학생끼리 키를 비교한 결과의 일부가 주어짐
 * 키들의 비교를 통해 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇명일까?
 *
 * # 입력
 * - 첫째 줄
 * 학생들의 수 N (2 ≤ N ≤ 500),
 * 두 학생 키를 비교한 횟수 M (0 ≤ M ≤ N(N-1)/2)
 *
 * - 다음 M개의 줄
 * 두 학생의 키를 비교한 결과 ( a b )
 * -> a번이 b번보다 키가 작음
 *
 * # 출력
 * 자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 출력
 *
 * 💻 알고리즘 설계
 * 플로이드-워셜 알고리즘을 사용한 이유?
 * A < B, B < C -> A < C 유추 가능
 * 즉, 직접 연결되지 않아도 중간 학생을 통해 관계 유추 가능
 * 이러한 간접 연결을 전부 찾아야함 -> 모든 정점 쌍 관계를 계산하는 플로이드-워셜 알고리즘 사용
 * (+ 플로이드 워셜은 가중치 없어도 됨. 단순히 경로가 있는지/없는지만 알면 됨)
 *
 * 1) N, M 입력받기
 * 2) 그래프 초기화 (자기 자신은 0, 나머지는 무한대)
 * 3) 키 비교 결과를 그래프에 1로 입력
 * 4) 플로이드-워셜 알고리즘 적용
 * 5) 1~N번 학생 루프를 돌며 각 연결 노드에서 무한대가 아닌 수가 있다면 known++
 * 6) known이 N-1이라면, 자신이 몇 번째인지 아는 것이므로 result++
 *
 *
 * ⏰ 시간복잡도
 * 플로이드-워셜 (삼중 for문) -> O(N^3)
 * ================================================================
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2458 {
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N + 1][N + 1];

        // 그래프 초기화 (자기 자신은 0)
        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        // 입력 받기 + 연결된 노드는 1로 갱신
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            int known = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                // 거리 대신 관계 여부만 판단 (INF인지 아닌지)
                if (graph[i][j] != INF || graph[j][i] != INF) known++;
            }
            // 자신보다 큰 학생 수 + 작은 학생수 = N-1 이면 result++
            if (known == N - 1) result++;
        }
        System.out.println(result);
    }
}
