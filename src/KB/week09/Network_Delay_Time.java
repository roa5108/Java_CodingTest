package KB.week09;

/* ================================================================
 *
 * Problem  : LeetCode - Network Delay Time
 * Author   : 김로아
 * Date     : 2025-06-30
 *
 * ================================================================
 * 📌 문제 분석 요약
 * - `times[i] = [u, v, w]`는 노드 u에서 노드 v로 가는 데 w시간 걸림
 * - k번 노드에서 모든 노드로 신호가 퍼질 때 걸리는 최대 시간 구하기
 * - 일부 노드로 도달 불가하면 -1 리턴
 *
 * # 입력
 * - times: int[][], 간선 정보 (길이 최대 6000)
 * - n: 전체 노드 수 (1 ≤ n ≤ 100)
 * - k: 시작 노드 번호 (1 ≤ k ≤ n)
 *
 * # 출력
 * 모든 노드까지 신호가 도달하는 데 걸리는 최대 시간 (불가능하면 -1)
 *
 * 💻 알고리즘 설계
 * 1) 인접 리스트로 방향 그래프 구성 (1번 노드부터 시작하므로 크기 n+1)
 * 2) 최소 힙(PriorityQueue)을 사용한 다익스트라(Dijkstra) 알고리즘 적용
 * 3) 거리 배열 `dists`를 사용해 최단 거리 저장
 * 4) 최단 거리들 중 최대값 리턴 (INF 남아 있으면 도달 불가 → -1)
 *
 * ⏰ 시간복잡도
 * - 간선 수: E, 노드 수: N
 * - 다익스트라: O((E + N) log N)
 *
 * ================================================================
 */

import java.util.*;

class Network_Delay_Time {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Edge>> graph = new ArrayList<>(); // 그래프 초기화 (노드 번호 1~n까지)
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 입력값을 이용해 방향 그래프 구성
        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            graph.get(u).add(new Edge(v, w));
        }

        int INF = Integer.MAX_VALUE;
        int[] dists = new int[n + 1]; // 거리 저장 배열
        Arrays.fill(dists, INF); // 모두 무한대로 초기화

        Queue<Edge> pq = new PriorityQueue<>(); // 우선순위 큐 (최소 힙)
        pq.offer(new Edge(k, 0)); // 시작 노드를 큐에 넣음
        dists[k] = 0; // 시작 노드는 거리 0

        while (!pq.isEmpty()) {
            Edge cur = pq.poll(); // 현재 가장 짧은 거리의 노드를 꺼냄
            for (Edge next : graph.get(cur.node)) { // 인접한 간선들 순회
                int nextDist = dists[cur.node] + next.cost;
                if (dists[next.node] > nextDist) {
                    pq.offer(new Edge(next.node, nextDist)); // 더 짧으면 갱신
                    dists[next.node] = nextDist;
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            maxTime = Math.max(maxTime, dists[i]); // 가장 오래 걸리는 노드 시간
        }
        return (maxTime == INF) ? -1 : maxTime; // 모두 도달 가능하면 maxTime 리턴, 아니면 -1
    }

    // 간선 클래스 정의
    class Edge implements Comparable<Edge> {
        int node;
        int cost;

        Edge(int node, int cost) {
            this.node = node; // 도착 노드
            this.cost = cost; // 비용 (시간)
        }

        @Override
        public int compareTo(Edge other) {
            // 비용이 적은 것이 우선순위 높음 (다익스트라용 최소 힙)
            return this.cost - other.cost;
        }
    }
}