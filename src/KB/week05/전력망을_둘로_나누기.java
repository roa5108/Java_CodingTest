package KB.week05;

/* ================================================================
 *
 * Problem  : PGS - 전력망을 둘로 나누기
 * Author   : 김로아
 * Date     : 2025-05-25
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

import java.util.ArrayList;
import java.util.List;

class 전력망을_둘로_나누기 {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < wires.length; i++) {

            List<List<Integer>> graph = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;
                int a = wires[j][0];
                int b = wires[j][1];
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            boolean[] visited = new boolean[n + 1]; // 방문 배열
            int count = dfs(1, graph, visited);

            int diff = Math.abs(n - 2 * count);
            answer = Math.min(answer, diff);
        }
        return answer;
    }

    public int dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        int count = 1;
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                count += dfs(next, graph, visited);
            }
        }
        return count;
    }
}