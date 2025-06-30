package KB.week09;

/* ================================================================
 *
 * Problem  : PGS - 합승 택시 요금
 * Author   : 김로아
 * Date     : 2025-06-30
 *
 * ================================================================
 * 📌 문제 분석 요약
 * - 두 사람이 출발 지점 s에서 함께 출발해 중간 지점까지 같이 가다가
 * - 각자 목적지 a, b로 갈라지는 구조
 * - 합승 구간을 적절히 선택해 총 택시 요금의 최소값을 구해야 함
 *
 * # 입력
 * - n (1 ≤ n ≤ 200): 지점 개수
 * - s, a, b (1 ≤ s, a, b ≤ n): 출발점, 목적지 a, 목적지 b
 * - fares (1 ≤ fares.length ≤ 6000): 각 노드 간 비용 정보 (양방향)
 *   fares[i] = [u, v, w] → u <-> v 비용 w
 *
 * # 출력
 * - 출발점 s에서 a, b 각각 목적지까지 가는 총 최소 요금
 *
 * 💻 알고리즘 설계
 * 1) 인접 리스트를 통해 양방향 그래프 구성
 * 2) 다익스트라 알고리즘을 3번 실행:
 *    - s에서 모든 노드까지
 *    - a에서 모든 노드까지
 *    - b에서 모든 노드까지
 * 3) 모든 노드 i에 대해:
 *    - s → i (합승) +  i → a  + i → b의 합계를 계산
 *    - 그 중 최소값을 구함
 *
 * ⏰ 시간복잡도
 * - 다익스트라 1회: O((V + E) log V)
 * - 총 3회 실행 → O(3 * (V + E) log V)
 *
 * ================================================================
 */

import java.util.*;

class 합승택시요금 {
    static int INF = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 양방향 그래프 구성
        for (int[] fare : fares) {
            int u = fare[0], v = fare[1], w = fare[2];
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }


        int totalCost = 0;
        int minCost = INF;

        // 다익스트라 실행: s, a, b 각각에서 시작
        int[] distFromS = dijkstra(s, graph, n);
        int[] distFromA = dijkstra(a, graph, n);
        int[] distFromB = dijkstra(b, graph, n);

        // 모든 노드 i를 합승 분기점으로 고려
        for (int i = 1; i <= n; i++) {
            totalCost = distFromS[i] + distFromA[i] + distFromB[i];
            minCost = Math.min(minCost, totalCost);
        }

        return minCost;
    }

    // 다익스트라 알고리즘 구현
    int[] dijkstra(int start, List<List<Edge>> graph, int n) {
        int[] dists = new int[n + 1];
        Arrays.fill(dists, INF);
        Queue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dists[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            for (Edge next : graph.get(cur.node)) {
                int nextDist = dists[cur.node] + next.cost;
                if (dists[next.node] > nextDist) {
                    pq.offer(new Edge(next.node, nextDist));
                    dists[next.node] = nextDist;
                }
            }
        }
        return dists;
    }

    class Edge implements Comparable<Edge> {
        int node;
        int cost;

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }
}