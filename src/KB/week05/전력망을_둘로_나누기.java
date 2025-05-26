package KB.week05;

/* ================================================================
 *
 * Problem  : PGS - 전력망을 둘로 나누기
 * Author   : 김로아
 * Date     : 2025-05-25
 *
 * ================================================================
 * 📌 문제 분석 요약
 * n개의 송전탑이 전선을 통해 하나의 트리 형태를 이룸
 * 전선 하나를 끊었을 때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추기
 *
 * # 입력
 * 송전탑의 개수 n, 전선 정보 2차원 배열 wires
 *
 * # 출력
 * 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)
 *
 * 💻 알고리즘 설계
 * 1) 간선 크기만큼 이차원 리스트 tree 생성
 * 2) 노드 수만큼 tree에 리스트 추가
 * 3) i번째 간선 제외하고, 그 이외 간선들만 추가시켜서 tree 구성 -> 전선 하나 끊은 효과
 * 4) dfs를 돌면서 서브트리 개수 카운트
 * 5) 서브트리 한쪽만 돌면 나머지 서브트리 개수는 n - count이므로 dfs에 node 1일 경우로 한 번만 계산.
 * 6) 송전탑 개수 차이의 절댓값을 계산해 최솟값을 리턴하면 정답
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

        // 간선 하나씩 제거해보는 반복문
        for (int i = 0; i < wires.length; i++) {
            List<List<Integer>> tree = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                tree.add(new ArrayList<>());
            }

            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue; // i번째 그래프에서 j번째 간선 제외함 ex) 1번째 그래프에서 1번 간선 제외
                int a = wires[j][0];
                int b = wires[j][1];
                tree.get(a).add(b);
                tree.get(b).add(a);
            }
            boolean[] visited = new boolean[n + 1]; // 방문 배열 (0번 노드 제외, 1~n까지)
            int count = dfs(1, tree, visited); // 하나의 연결된 송전탑 개수

            int diff = Math.abs(n - 2 * count); //Math.abs((n - count) - count)와 같음;

            answer = Math.min(answer, diff); // 최소 차이 갱신
        }
        return answer;
    }

    public int dfs(int node, List<List<Integer>> tree, boolean[] visited) {
        visited[node] = true;
        int count = 1;
        for (int next : tree.get(node)) {
            if (!visited[next]) {
                count += dfs(next, tree, visited);
            }
        }
        return count;
    }
}