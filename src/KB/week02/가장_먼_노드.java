package KB.week02;

import java.util.*;

/* ================================================================
 *
 * Problem  : PGS - 가장 먼 노드
 * Author   : 김로아
 * Date     : 2025-05-08
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 1부터 n까지 번호가 적힌 노드를 가진 그래프가 있다. 1번 노드에서 가장 멀리 떨어진 노드의 개수 구하기
 * 가장 멀리 떨어진 노드란? -> 최단경로로 이동 시 간선의 개수가 가장 많은 노드들
 * # 입력
 * 노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 edges
 *
 * # 출력
 * 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return
 * 💻 알고리즘 설계
 * 1) 문제에 최단경로라는 키워드 -> bfs 접근
 * 2) bfs를 할 때 거리를 저장하는 edgeCnt 배열에 해당 노드까지의 거리를 저장
 * 3) edgeCnt 배열의 최댓값을 찾고, 최대 거리인 노드들의 개수를 세면 정답
 *
 * ⏰ 시간복잡도
 * O(n+m) 정점 개수 n, 간선 개수 m
 * ================================================================
 */

class 가장_먼_노드 {
    public static int[] bfs(int n, int start, List<List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] edgeCnt = new int[n + 1];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int q = queue.poll();
            for (int next : graph.get(q)) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    edgeCnt[next] = edgeCnt[q] + 1;
                }
            }
        }

        return edgeCnt;
    }

    public int solution(int n, int[][] edges) {
        int answer = 0;

        // 그래프 생성
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) { // 0번 인덱스도 포함
            graph.add(new ArrayList<>());
        }

        // 간선 추가
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // BFS 실행
        int[] edgeCnt = bfs(n, 1, graph);

        // 최대 거리 찾기
        int max = Arrays.stream(edgeCnt).max().getAsInt();

        // 최대 거리인 노드 개수 세기
        for (int cnt : edgeCnt) {
            if (cnt == max) answer++;
        }

        return answer;
    }
}