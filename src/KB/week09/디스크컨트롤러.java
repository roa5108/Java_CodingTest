package KB.week09;

/* ================================================================
 *
 * Problem  : PGS - 디스크 컨트롤러
 * Author   : 김로아
 * Date     : 2025-07-01
 *
 * ================================================================
 * 📌 문제 분석 요약
 * - jobs[i] = [요청시간, 처리시간] 형태로 작업 요청이 들어옴
 * - 한 번에 하나의 작업만 처리 가능하며, 대기 + 처리 시간의 평균을 최소화해야 함
 * - 요청 시간 이전에 도착한 작업들 중에서 처리 시간이 짧은 작업부터 처리해야 최적
 *
 * # 입력
 * - int[][] jobs: 작업 목록 (요청시간, 처리시간)
 *
 * # 출력
 * - 평균 소요 시간 (정수, 소수점 버림)
 *
 * 💻 알고리즘 설계
 * 1) jobs를 요청 시간(jobs[i][0]) 기준으로 정렬
 * 2) 처리 가능한 작업들을 우선순위 큐(PQ)에 넣되, 처리시간이 짧은 순서로 정렬
 * 3) 현재 시점(time)보다 요청 시간이 작거나 같은 작업을 PQ에 넣음
 * 4) 큐가 비었으면 다음 작업 요청 시간까지 time을 점프
 * 5) 작업 완료 시간에서 요청 시간을 뺀 값을 누적
 * 6) 전체 평균을 반환
 *
 * ⏰ 시간복잡도
 * - 정렬: O(N log N)
 * - PQ 삽입/삭제: O(log N) * N
 * - 총합: O(N log N)
 *
 * ================================================================
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class 디스크컨트롤러 {
    public int solution(int[][] jobs) {
        // 요청시간 기준 오름차순 정렬
        Arrays.sort(jobs, Comparator.comparingInt(j -> j[0]));

        // 처리시간 기준 오름차순 정렬을 위한 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(j -> j[1]));
        int time = 0; // 현재 시점
        int totalTime = 0; // 전체 대기+처리 시간 누적
        int idx = 0; // jobs 배열에서 아직 처리 안 한 작업의 인덱스
        int cnt = 0; // 완료된 작업 수

        while (cnt < jobs.length) {
            // 현재 시점 이전에 도착한 작업들을 PQ에 추가
            while (idx < jobs.length && jobs[idx][0] <= time) {
                pq.offer(jobs[idx]);
                idx++;
            }
            if (!pq.isEmpty()) {
                // 처리시간이 가장 짧은 작업 꺼내기
                int[] cur = pq.poll();
                time += cur[1]; // 현재 시점 갱신 (작업 수행)
                totalTime += (time - cur[0]); // 요청부터 종료까지 걸린 시간 누적
                cnt++; // 완료된 작업 수 증가
            } else {
                // 실행 가능한 작업이 없다면, 다음 작업이 들어오는 시간으로 점프
                time = jobs[idx][0];
            }
        }
        // 평균 시간 반환 (정수 나눗셈 → 소수점 버림)
        return totalTime / jobs.length;
    }
}