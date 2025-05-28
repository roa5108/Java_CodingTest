package KB.week02;

/* ================================================================
 *
 * Problem  : PGS - 네트워크
 * Author   : 김로아
 * Date     : 2025-04-25
 *
 * ================================================================
 * 📌 문제 분석 요약
 * - A-B 연결, B-C 연결 ⇒ A-C 연결
 * - 연결된 네트워크의 개수 세기
 * - 컴퓨터의 개수, 연결된 정보 2차원 배열이 주어짐
 *
 * # 입력
 * 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers
 *
 * # 출력
 * 네트워크의 개수
 *
 * 💻 알고리즘 설계
 * dfs를 구현해 dfs를 실행할 때마다 count를 +1 해서 네트워크 개수를 센다.
 *
 * ⏰ 시간복잡도
 * 만약 그래프가 인접 리스트로 주어졌다면 → 시간복잡도는 O(n + m)
 * 지금처럼 인접 행렬이면 → 항상 O(n²)
 * ================================================================
 */

class 네트워크 {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, visited, computers);
                count++;
            }
        }
        return count;
    }

    public void dfs(int node, boolean[] visited, int[][] computers) {
        visited[node] = true;
        for (int i = 0; i < computers.length; i++) {
            if (computers[node][i] == 1 && !visited[i]) {
                dfs(i, visited, computers);
            }
        }
    }
}