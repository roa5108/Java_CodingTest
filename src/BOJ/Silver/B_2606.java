package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* ================================================================
 *
 * Problem  : BOJ2606 - 바이러스
 * Author   : 김로아
 * Date     : 2025-05-07
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수
 *
 * # 입력
 * 1행 - 컴퓨터의 수
 * 2행 - 연결되어 있는 컴퓨터 쌍의 수
 * 3행~끝 - 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍
 *
 * # 출력
 * 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수
 *
 * 💻 알고리즘 설계
 * 1) 입력을 받고, 인접 그래프를 생성한다.
 * 2) dfs 함수를 만들고 바이러스에 걸린 컴퓨터 개수인 count를 센 후 dfs() 호출
 *
 * ⏰ 시간복잡도
 * O(N+M) -> 정점의 수 + 간선의 수
 * ================================================================
 */
public class B_2606 {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<List<Integer>> graph = new ArrayList<>();


        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];


        dfs(1, graph, visited);
        System.out.println(count);

    }

    public static void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                count++;
                dfs(next, graph, visited);

            }
        }
    }
}
