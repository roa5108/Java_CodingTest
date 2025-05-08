package KB.week02;

import java.util.List;

/* ================================================================
 *
 * Problem  : LeetCode - Keys and Rooms
 * Author   : 김로아
 * Date     : 2025-04-25
 *
 * ================================================================
 * 📌 문제 분석 요약
 * - n개의 방이 있고, 0번방을 제외한 나머지 방들은 잠겨있음
 * - 모든 방을 방문하는 것이 목표
 * - 방에는 다른 방의 열쇠가 있음
 *
 * # 입력
 * room을 방문했을 때 얻을 수 있는 키의 집합인 rooms배열
 * Input: rooms = [[1],[2],[3],[]]
 *
 * # 출력
 * 모든 room을 방문할 수 있으면 true, 그렇지 않으면 false 반환
 * Output: true
 *
 * 💻 알고리즘 설계
 * dfs를 실행시켜서 모든 노드가 하나로 연결되어 있으면 true를 리턴, 아니라면 false를 리턴
 *
 * ⏰ 시간복잡도
 * O(N+E) 방(노드)의 수 N, 열쇠(간선)의 총 수 E
 * ================================================================
 */

class Keys_and_Rooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size();
        int count = 0;
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs(i, rooms, visited);
                count++;
            }
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void dfs(int node, List<List<Integer>> rooms, boolean[] visited) {
        visited[node] = true;
        for (int next : rooms.get(node)) {
            if (!visited[next]) dfs(next, rooms, visited);
        }
    }
}